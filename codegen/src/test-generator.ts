import fs from 'fs'
import path from 'path'
import {
  everyDeclaredClass,
  getAllComponentDirs,
  toPascalCase,
} from './parser/frontmatter.ts'
import { isSkipped, readModel, type ComponentModel, type MainFunctionConfig, type ModelFunction } from './model.ts'
import { markedElements } from './parser/documented-classes.ts'
import { documentedCall, pageFirst, type DocumentedCall } from './documented-elements.ts'
import { hasSibling, isVoidElement, siblingParameters, type ComponentShape, type ParameterShape, type SiblingShape, type TagClass } from './component-shape.ts'

/**
 * `BuilderName` and `TagName` are the defect that motivated the whole TypeScript port:
 * `add-mutation-testing` task 4.1 asserted `</fieldSet>`, and only `FieldsetCoverageTest`
 * and `TextareaCoverageTest` noticed, because every other component is a `div` where the
 * two names coincide.
 *
 * They live in `html-names.ts` rather than here, since `llms-txt.ts` reads tag names out of
 * DaisyUI's docs and this file writes them into Kotlin — one nominal type, or they cannot
 * be compared at all.
 *
 * Branding costs THREE casts here, each where a name genuinely comes into existence:
 * `parseEmittedBuilder` (read out of a generated body), `htmlTagForFn` (lowercased and
 * immediately validated by round-tripping), and `wrapperTagOf` (derived from a Kotlin
 * receiver type). Everything downstream is checked.
 */
import type { BuilderName, TagName } from './html-names.ts'

// A doc page is scanned line by line, and until now the position in that scan lived in four
// mutable locals threaded through one loop. Collecting them into one object is what lets each
// line kind below be read on its own — the loop no longer has to be simulated in your head to
// know what any branch does.
//
// `language` is deliberately NOT reset when a fence closes. That is load-bearing: the final
// flush consults it, and a heading inside an open fence leaves it standing. Both behaviours
// are pinned by tests in codegen/test/.
const TEST_CASE_HEADING = /^### ~(.+)/

function newScan() {
  // `blockLines` is set by the first heading or fence; nothing reads it before either.
  return { cases: [], current: null, insideBlock: false, language: null }
}

function flushCurrentCase(scan) {
  scan.current.html = scan.blockLines.join('\n')
  scan.cases.push(scan.current)
}

// No flush here: a heading is only read outside a block, and closing an html block already
// flushed its case.
function startCase(scan, name) {
  scan.current = { name: name.trim(), html: null }
  scan.blockLines = []
}

function openBlock(scan, fenceLine) {
  scan.insideBlock = true
  scan.language = fenceLine.slice(3).trim()
  scan.blockLines = []
}

function closeBlock(scan) {
  scan.insideBlock = false
  if (scan.language === 'html' && scan.current) {
    flushCurrentCase(scan)
    scan.current = null
  }
}

// Order matters and is the fix for a real defect. A fence is decided first, then whether we
// are inside one; only text OUTSIDE a block can be a heading. Markdown says a fenced block's
// contents are literal, headings included.
//
// Previously the heading branch ran first, so `### ~x` inside an open block started a case
// AND left `insideBlock` set — the next opening fence was then read as a closing one, the
// new case was flushed with an empty body, and its real content fell outside any block and
// vanished. An empty case survives all the way into a generated Kotlin test that asserts
// nothing.
function scanLine(scan, line) {
  if (line.startsWith('```')) return scan.insideBlock ? closeBlock(scan) : openBlock(scan, line)
  if (scan.insideBlock) return scan.blockLines.push(line)
  const heading = line.match(TEST_CASE_HEADING)
  if (heading) startCase(scan, heading[1])
}

/** A document may end mid-block; that trailing case is still emitted, if it is html. */
function endsInsideUnclosedHtmlBlock(scan) {
  return scan.current !== null && scan.blockLines.length > 0 && scan.language === 'html'
}

function parseTestCases(content) {
  const scan = newScan()
  for (const line of content.split('\n')) scanLine(scan, line)
  if (endsInsideUnclosedHtmlBlock(scan)) flushCurrentCase(scan)
  return scan.cases
}

function toClassName(componentName) {
  return toPascalCase(componentName)
}

/** A construction method as the custom-part tests address it: its suffix and what it writes. */
function customPartsOf(model: ComponentModel) {
  return model.functions.filter(fn => fn.kind === 'custom').map((fn: ModelFunction) => ({
    name: fn.name.slice(`daisy${model.name}`.length),
    functionName: fn.name,
    element: fn.element,
    cssClass: fn.class,
    modifierClasses: fn.always ?? [],
    receiver: fn.receiver,
    // An anchored popover writes `popover` without declaring it; the browser anchors it to its trigger.
    staticAttributes: { ...(fn.anchoredPopover ? { popover: '' } : {}), ...(fn.attributes ?? {}) },
    anchoredPopover: fn.anchoredPopover === true,
  }))
}

function toTestFunctionName(name) {
  let funcName = name
    .toLowerCase()
    .replace(/[^a-z0-9]+/g, '_')
    .replace(/^_|_$/g, '')
  
  if (/^[0-9]/.test(funcName)) {
    funcName = `test_${funcName}`
  }
  
  return funcName
}

/** One `assertTrue` per statically-emitted attribute, indented for a test body. */
function staticAttributeAssertions(attributes) {
  return Object.entries(attributes || {}).map(
    ([name, value]) => `        assertTrue(html.contains("${name}=\\"${value}\\""))`,
  )
}

function customPartAssertions(part, tag) {
  const assertions = [`        assertTrue(html.contains("<${tag}"))`]
  if (part.cssClass) {
    assertions.push(`        assertTrue(html.contains("class=\\"${part.cssClass}"))`)
  }
  for (const cssClass of part.modifierClasses) {
    assertions.push(`        assertTrue(html.contains("${cssClass}"))`)
  }
  assertions.push(...staticAttributeAssertions(part.staticAttributes))
  return assertions.join('\n')
}

/**
 * Pins the attributes the main component emits unconditionally. The class-mismatch tests
 * compare only the class attribute, so an attribute that carries no CSS class would
 * otherwise be invisible to both the generated tests and generated-sources-drift.
 */
function generateComponentAttributeTest(functionName, componentAttributes) {
  const assertions = staticAttributeAssertions(componentAttributes)
  if (assertions.length === 0) return ''

  return `
    @Test
    fun renders_static_attributes() {
        val html = createHTML(prettyPrint = false).div {
            ${functionName} {
            }
        }
${assertions.join('\n')}
    }
`
}

function generateCustomPartTests(customParts) {
  let kotlin = ''
  for (const part of customParts) {
    const funcName = `custom_part_${part.name.toLowerCase()}_renders_${part.element.toLowerCase()}`
    const tag = part.element.toLowerCase()
    
    // Determine wrapping context based on receiver type
    const wrapperTag = wrapperTagOf(part.receiver)
    const wrapperFn = htmlTagFnFor(wrapperTag)

    kotlin += `
    @Test
    fun ${funcName}() {
        val html = createHTML(prettyPrint = false).${wrapperFn} {
            ${part.functionName}${part.anchoredPopover ? '(id = htmlId("custom-part"))' : ''} {
            }
        }
${customPartAssertions(part, tag)}
    }
`
  }
  return kotlin
}

/**
 * The tag a kotlinx.html receiver type emits: `DIV` → `div`, `BUTTON` → `button`.
 *
 * `FlowContent` is the exception and not an element at all — it is the generic content
 * position, which the generated tests wrap in a `<div>`.
 *
 * This exists so the conversion happens in ONE place. Four call sites used to inline the
 * same ternary, and after branding they would each have needed their own cast; naming it
 * costs one cast and deletes four copies of a conditional.
 */
function wrapperTagOf(receiver: string): TagName {
  return (receiver === 'FlowContent' || receiver === 'Tag' ? 'div' : receiver.toLowerCase()) as TagName
}

function htmlTagFnFor(tag: TagName): BuilderName {
  const exceptions = { fieldset: 'fieldSet', textarea: 'textArea' }
  return exceptions[tag] ?? tag
}

/** Suffixes `_2`, `_3`, … until the name is free, and records it as taken. */
function uniqueTestName(usedNames, funcName) {
  let name = funcName
  let counter = 2
  while (usedNames.has(name)) {
    name = `${funcName}_${counter}`
    counter++
  }
  usedNames.add(name)
  return name
}

/** The call of a documented element's function: its arguments, then the other groups' siblings as its block. */
function callStatement({ fn, args, block }: DocumentedCall): string {
  if (block === '') return `${fn.name}(${args})`
  return args === '' ? `${fn.name} { ${block} }` : `${fn.name}(${args}) { ${block} }`
}

/** The builder a test needs around a tag it writes itself: `li` and `legend` take one parent only. */
const TAG_PARENTS: Readonly<Record<string, string>> = { li: 'ul', legend: 'fieldSet' }

/** The builder and statement that render a documented element: its function, or its tag with siblings. */
function renderingOf(call: DocumentedCall): { wrapper: string, statement: string } {
  if (call.rendersTag) return { wrapper: htmlTagFnFor(wrapperTagOf(call.fn.receiver)), statement: callStatement(call) }
  return { wrapper: TAG_PARENTS[call.tag] ?? 'div', statement: `${htmlTagFnFor(call.tag as TagName)} { ${call.onTag} }` }
}

function generateClassTest(call: DocumentedCall, { testName, expectedClasses, caseName, tag }) {
  const { wrapper, statement } = renderingOf(call)
  return `
    @Test
    fun ${testName}() {
        val html = createHTML(prettyPrint = false).${wrapper} {
            ${statement}
        }
        assertDocumented(html, "${tag}", "${expectedClasses}", "${caseName}")
    }
`
}

/**
 * Emitted once per test file with documented-element tests, and called by each of them: the first
 * element carrying a class is the documented one, and it must be DaisyUI's tag with DaisyUI's
 * classes. One call per test instead of two assertion lines, which made every test structurally
 * identical to its neighbours.
 */
const DOCUMENTED_HELPER = `
    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\\"").substringBefore("\\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }
`

/** The imports a test file needs beyond the four every one of them has. */
function extraImportLines(customParts, hasAttributeTest: boolean, classTestImports: ReadonlySet<string>): string {
  const imports = new Set<string>(classTestImports)
  if (customParts.length > 0 || hasAttributeTest) imports.add('import kotlin.test.assertTrue')
  for (const part of customParts) {
    if (part.receiver !== 'FlowContent') imports.add(`import kotlinx.html.${htmlTagFnFor(wrapperTagOf(part.receiver))}`)
    if (part.anchoredPopover) imports.add('import io.github.ollin.kdaisyui.core.htmlId')
  }
  return [...imports].sort().map(line => `\n${line}`).join('')
}

/** What every page's tests are generated against: all models, and every class some component declares. */
interface DocumentedContext {
  readonly models: readonly ComponentModel[]
  readonly componentClasses: ReadonlySet<string>
}

/**
 * One test per expressible documented element of the page, whichever function expresses it,
 * deduplicated across the page, with how many there were and the imports they need.
 */
/** `models` in claiming order, the page's own model first; `mainName` absent for a skipped page. */
function classTests(models: readonly ComponentModel[], context: DocumentedContext, mainName: string | undefined, testCases): { kotlin: string, testCount: number, imports: Set<string> } {
  const seen = new Set<string>()
  const usedNames = new Set()
  const tests = testCases.flatMap(tc => markedElements(tc.html)
    .map(element => documentedCall(models, element, context.componentClasses))
    .filter((call): call is DocumentedCall => call !== null && firstTime(seen, `${call.fn.name}|${call.args}|${call.expectedClasses}`))
    .map(call => ({ call, kotlin: generateClassTest(call, {
      testName: uniqueTestName(usedNames, call.fn.name === mainName ? toTestFunctionName(tc.name) : `${toTestFunctionName(tc.name)}_${call.fn.name}`),
      expectedClasses: call.expectedClasses,
      caseName: tc.name,
      tag: call.tag,
    }) })))
  return { kotlin: tests.map(test => test.kotlin).join(''), testCount: tests.length, imports: new Set(tests.flatMap(test => importsOf(test.call))) }
}

/** The imports a documented-element test needs beyond the file's four: its builders, and `addClassNames`. */
function importsOf(call: DocumentedCall): string[] {
  const builders = [renderingOf(call).wrapper, ...(call.rendersTag ? [] : [htmlTagFnFor(call.tag as TagName)])]
  return [
    ...builders.filter(builder => builder !== 'div').map(builder => `import kotlinx.html.${builder}`),
    ...(!call.rendersTag && call.onTag.includes('addClassNames(') ? ['import io.github.ollin.kdaisyui.core.addClassNames'] : []),
    ...(call.args.includes('htmlId(') ? ['import io.github.ollin.kdaisyui.core.htmlId'] : []),
  ]
}

function firstTime(seen: Set<string>, signature: string): boolean {
  if (seen.has(signature)) return false
  seen.add(signature)
  return true
}

function generateKotlinTest(componentName, testCases, componentModel: ComponentModel, context: DocumentedContext) {
  const className = toClassName(componentName)
  // Every generated model declares exactly one main function; committed-model.test.ts reads them all.
  const main = componentModel.functions.find(fn => fn.kind === 'main')!
  const mainName = main.name
  const customParts = customPartsOf(componentModel)
  const attributeTest = generateComponentAttributeTest(mainName, main.attributes ?? {})
  const { kotlin: classTestKotlin, testCount, imports } = classTests(pageFirst(context.models, componentModel.component), context, mainName, testCases)

  const kotlin = kotlinTestFile(className, extraImportLines(customParts, attributeTest !== '', imports), `${classTestKotlin}${attributeTest}${generateCustomPartTests(customParts)}`)
  return { kotlin, testCount }
}

/** A generated test file: the four imports every one has, the extra ones, and its tests. */
function kotlinTestFile(className: string, extraImports: string, tests: string): string {
  return `package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals${extraImports}

class ${className}Test {
${tests.includes('assertDocumented(html') ? DOCUMENTED_HELPER : ''}${tests}}
`
}

/**
 * The Kotlin of `<Name>Test.kt` for one DaisyUI page, with its test count, or null when a
 * component's page documents no example. A skipped page (no model) has no functions of its own,
 * but its examples are DaisyUI's, so they are tested alone. A pure function of its inputs: the
 * snapshot test compares it with the committed test files.
 */
function documentedTestFor(componentName: string, page: string, model: ComponentModel | null, context: DocumentedContext) {
  const testCases = parseTestCases(page)
  const className = toClassName(componentName)
  if (model === null) {
    // A skipped page has no model to put first and no main function to name tests after.
    const { kotlin, testCount, imports } = classTests(context.models, context, undefined, testCases)
    return { kotlin: kotlinTestFile(className, extraImportLines([], false, imports), kotlin), testCount }
  }
  if (testCases.length === 0) return null
  return generateKotlinTest(componentName, testCases, model, context)
}

// Exhaustive branch-coverage tests: parse each generated component source and
// drive every branch of every function with assertions on the rendered HTML.


// Enum types not declared in the component's source - kotlinx.html's, and the library's own
// `DaisyTheme`: arms can't be enumerated, so the `!= null` branch is driven once with a known value.
const EXTERNAL_ENUM_VALUES = { ButtonType: 'ButtonType.button', DaisyTheme: 'DaisyTheme.Dark' }
const EXTERNAL_ENUM_IMPORTS = { ButtonType: 'import kotlinx.html.ButtonType', DaisyTheme: 'import io.github.ollin.kdaisyui.themes.DaisyTheme' }

function lowerFirst(s) {
  return s.charAt(0).toLowerCase() + s.slice(1)
}

const PARAM_KIND_RULES = [
  [(c) => c.baseType === 'Boolean', 'boolean'],
  [(c) => c.name === 'id', 'id'],
  [(c) => c.name === 'text', 'text'],
  // Not `extraClasses: String?`, whose test would otherwise pass it "x" as if it were a
  // component's own text parameter.
  [(c) => c.nullable && c.baseType === 'String' && c.name !== 'extraClasses', 'nullableString'],
  [(c) => c.nullable && c.enums[c.enumType], 'enumClass'],
  [(c) => c.nullable && EXTERNAL_ENUM_VALUES[c.baseType], 'enumExternal'],
]

function paramKind(ctx) {
  for (const [matches, kind] of PARAM_KIND_RULES) {
    if (matches(ctx)) return typeof kind === 'function' ? kind(ctx) : kind
  }
  // A parameter no rule names, which no test drives.
  return null
}

/**
 * The generated enum a parameter carries, unwrapped from the holder type.
 *
 * An exclusive group arrives as `ClassValues<ButtonSize>?`, not `ButtonSize?`, so that one
 * parameter accepts a bare entry, an entry at a Tailwind variant, and combinations of those. The
 * TEST still has to name `ButtonSize` to write `ButtonSize.Lg`, so the wrapper is peeled here —
 * in one place, rather than at each of the three sites that need the enum's own name.
 *
 * A type that is not a holder is returned unchanged, which is what keeps `ButtonType` (a
 * kotlinx.html enum, never wrapped) matching its own rule.
 */
function groupEnumOf(baseType: string): string {
  return baseType.match(/ClassValues<(\w+)>/)?.[1] ?? baseType
}

function classifyParam({ name, type, cssClass, writes }: ParameterShape, enums) {
  const nullable = type.endsWith('?')
  const baseType = nullable ? type.slice(0, -1) : type
  const enumType = groupEnumOf(baseType)
  const kind = paramKind({ name, baseType, enumType, nullable, enums })
  // The classes a Boolean adds when true: a toggle's own, or those an extra writes.
  const classes = cssClass === undefined ? [...(writes ?? [])] : [cssClass]
  // Read only for an enum parameter, where it is the enum's entries.
  return { name, type, baseType, enumType, nullable, kind, classes, enumEntries: enums[enumType] }
}

/** One overload on a generated scope, and what a test needs to call it. */
interface ScopeMember {
  readonly name: string
  /** The function whose lambda the member lives in — the call has to be written inside it. */
  readonly opener: string
}

/**
 * The HTML tag a kotlinx.html builder emits — the inverse of `htmlTagFnFor`.
 *
 * The two differ: the builder for `<fieldset>` is `fieldSet` and for `<textarea>`
 * is `textArea`, so naively reusing the builder name produces `</fieldSet>`, which
 * matches nothing. Lowercasing fixes those, but not `htmlObject` (tag: `object`).
 *
 * Rather than maintain a second exception table that can drift from the first, the
 * inversion is CHECKED: lowercase, then round-trip through `htmlTagFnFor`. A builder
 * that does not round-trip returns null and its caller emits no assertion — refusing
 * to assert beats asserting something false, which is the failure this whole task
 * exists to remove.
 */
function htmlTagForFn(builder: BuilderName): TagName | null {
  // The second and last point a branded name is created. The cast is safe precisely
  // because the next line checks it: a lowercased builder that does not round-trip
  // through `htmlTagFnFor` is not a tag name, and is rejected rather than returned.
  const tag = builder.toLowerCase() as TagName
  return htmlTagFnFor(tag) === builder ? tag : null
}

/**
 * Assert the emitted element is actually CLOSED.
 *
 * Every other assertion in these tests reads the class attribute, and a class
 * attribute is unaffected by whether the element was ever closed: dropping the
 * closing tag turns `<div><dialog class="modal"></dialog></div>` into
 * `<div><dialog class="modal"></div>`, from which `substringAfter("class=\"")`
 * extracts exactly the same string. Mutation testing found 11 such mutants
 * surviving across Modal, Dropdown, Tooltip and Range.
 *
 * `endsWith` rather than `contains`: the component is the wrapper's only child
 * and the test content adds no child elements, so its closing tag sits directly
 * before the wrapper's. `contains("</div>")` would be satisfied by the wrapper
 * alone and would kill nothing for any div-based component.
 */
/**
 * The HTML attributes a model's `apply` fragments set, split by whether a guard protects them.
 * Every fragment assigns to the tag receiver as `this.<name> = …`, guarded by an `if (…)` or not:
 *
 *     this.type = type                                          // unconditional
 *     if (disabled) this.disabled = true                        // Boolean parameter
 *     if (disabled) { this.disabled = true; addClassNames(…) }   // Boolean parameter, block
 *     if (type != null) this.type = type                        // nullable parameter
 *
 * Only all-lowercase property names are reported. A kotlinx.html property whose name is camelCase
 * generally renames on the way out — `htmlFor` emits `for=` — and there is no table here to
 * invert. Skipping them asserts nothing rather than something false.
 */
const ATTR_ASSIGN = /this\.([a-z][a-z0-9]*) = /g

function parseAttrProps(fragments: string) {
  const unconditional = new Set<string>()
  const guarded = new Set<string>()
  for (const line of fragments.split('\n')) {
    const found = line.startsWith('if (') ? guarded : unconditional
    for (const m of line.matchAll(ATTR_ASSIGN)) found.add(m[1])
  }
  return { unconditional, guarded }
}

function attrAssert(ctx, name) {
  return `assertTrue(html.contains("${name}=\\""), "${ctx.daisyName} sets ${name}")`
}

function closesTagAssert(ctx) {
  const closes = closesSuffix(ctx)
  if (!closes) return null
  return `assertTrue(html.endsWith("${closes}"), "${ctx.daisyName} closes")`
}

/** CSS class(es) a boolean modifier adds via `if (param) addClassNames("...")`. */
function sortedClasses(arr) {
  return [...new Set(arr.filter(Boolean))].sort().join(' ')
}

/**
 * Emitted once per generated coverage class, and called by every case in it.
 *
 * Before this existed, each case repeated a long class-extraction line plus its assertions,
 * which made every `*_defaults` method structurally identical to its siblings — real
 * duplication, flagged as such, in files a human reads exactly when a test fails.
 *
 * `closes` is empty for a void element such as `<input>`, which has no closing tag. It is
 * also omitted by the enum and text cases, which never asserted closure and must not start
 * doing so here: this is a refactoring, and a refactoring does not change an assertion.
 */
const COVERAGE_HELPER = `
    /** What a case must render: its classes, sorted, and the tail it closes with. */
    private class Expected(val label: String, val classes: String, val closes: String = "")

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\\"").substringBefore("\\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "\${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val BLOCK: Tag.() -> Unit = { attributes["data-block"] = "yes" }

    /** Renders one call and asserts the classes it writes. */
    private fun assertRenders(expected: Expected, render: FlowContent.() -> Unit) {
        assertRendered(createHTML(prettyPrint = false).div { render() }, expected)
    }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    /** Renders one call per entry of an axis and asserts the classes each must write. */
    private fun <E> assertEachEntry(label: String, classesOf: Map<E, String>, render: FlowContent.(E) -> Unit) {
        for ((entry, classes) in classesOf) {
            assertRendered(createHTML(prettyPrint = false).div { render(entry) }, Expected("\$label \$entry", classes))
        }
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\\"x-cov-id\\""), "\${expected.label} id")
        assertTrue(html.contains("data-block=\\"yes\\""), "\${expected.label} block")
    }
`

/** The exact tail a correctly closed component leaves, or null when there is none to assert. */
function closesSuffix(ctx) {
  const tag = ctx.tagFn ? htmlTagForFn(ctx.tagFn) : null
  // A void element has no closing tag, so the "element is closed" assertion would assert
  // something false. `isVoidElement` is the generator's own list, in kotlinx.html's casing.
  if (!tag || isVoidElement(tag.toUpperCase() as TagClass)) return null
  return `</${tag}></${ctx.wrapperTag}>`
}

/** The `Expected` a case asserts against, as Kotlin. */
function expectedValue(ctx, classes, label, { withCloses }) {
  const closes = withCloses ? closesSuffix(ctx) : null
  return `Expected("${label}", "${classes}"${closes ? `, closes = "${closes}"` : ''})`
}

function renderedAssert(ctx, classes, label, withCloses) {
  return `assertRendered(html, ${expectedValue(ctx, classes, label, { withCloses })})`
}

function renderTest(funcName, wrapperFn, callArgs, asserts) {
  const argStr = callArgs.length ? `\n${callArgs.map((a) => `                ${a},`).join('\n')}\n            ` : ''
  return `
    @Test
    fun ${funcName}() {
        val html = createHTML(prettyPrint = false).${wrapperFn} {
            daisy${''}REPLACE(${argStr})
        }
${asserts.map((a) => `        ${a}`).join('\n')}
    }
`
}

function coverageContext(fn, enums) {
  const params = fn.parameters.map((parameter) => classifyParam(parameter, enums))
  return {
    params,
    attributes: fn.attributes,
    base: sortedClasses(fn.baseClasses),
    wrapperFn: htmlTagFnFor(wrapperTagOf(fn.receiver)),
    // The wrapper's CLOSING tag, which is the raw receiver name — not `wrapperFn`,
    // because `htmlTagFnFor` renames a few builders away from their tag (`object`
    // becomes `htmlObject`) and `</htmlObject>` is not a thing.
    wrapperTag: wrapperTagOf(fn.receiver),
    // A scoped function opens its scope instead of a builder, and the scope extends the same
    // element — so the closing tag is still assertable.
    tagFn: fn.builder,
    fnBase: lowerFirst(fn.name),
    daisyName: fn.name,
    textParam: params.find((p) => p.kind === 'text'),
    // A class sibling takes no id, extraClasses or block: it adds classes to an element it does
    // not open.
    hasHatches: params.some((p) => p.kind === 'id'),
    // An anchored popover's id has no default, so even the smallest call names one.
    idRequired: params.some((p) => p.kind === 'id' && !p.nullable),
  }
}

/** What every call of the function has to pass: a required id. */
function requiredArgs(ctx): string[] {
  return ctx.idRequired ? ['id = htmlId("x-cov-id")'] : []
}

function wrapTest(ctx, tname, args, asserts) {
  return renderTest(tname, ctx.wrapperFn, args, asserts).replace(`daisyREPLACE`, `daisy${ctx.daisyName}`)
}

function defaultsTest(ctx) {
  const args = requiredArgs(ctx)
  const asserts = ctx.base
    ? [renderedAssert(ctx, ctx.base, `${ctx.daisyName} defaults`, true)]
    : [`assertTrue(!html.contains("class=\\""), "${ctx.daisyName} defaults emits no class")`]
  for (const a of ctx.attributes.unconditional) asserts.push(attrAssert(ctx, a))
  // A component with no base class never reaches the helper, so its closure assertion is
  // still emitted on its own line.
  if (!ctx.base) {
    const closes = closesTagAssert(ctx)
    if (closes) asserts.push(closes)
  }
  return wrapTest(ctx, `${ctx.fnBase}_defaults`, args, asserts)
}

function allFlagsArgs(ctx, boolCss) {
  const args = ctx.hasHatches ? ['id = htmlId("x-cov-id")'] : []
  for (const b of ctx.params.filter((p) => p.kind === 'boolean')) {
    args.push(`${b.name} = true`)
    boolCss.push(...b.classes)
  }
  for (const s of ctx.params.filter((p) => p.kind === 'nullableString')) args.push(`${s.name} = "x"`)
  for (const e of ctx.params.filter((p) => p.kind === 'enumExternal')) args.push(`${e.name} = ${EXTERNAL_ENUM_VALUES[e.baseType]}`)
  if (ctx.hasHatches) args.push('extraClasses = "zz-extra"', 'block = { attributes["data-block"] = "yes" }')
  return args
}

function allFlagsAsserts(ctx, boolCss) {
  // The id/block pair is identical in every all-flags case, so it lives in
  // `assertCommonFlags` rather than being repeated in every case.
  //
  // Kept SEPARATE from `assertRendered` on purpose. Folding both into one helper was tried
  // and reverted: it needed five parameters against Kotlin's threshold of four and traded
  // the duplication for an Excess Number of Function Arguments smell. Two helpers of three
  // and four parameters say the same thing and trip neither rule.
  const classes = sortedClasses([ctx.base, ...boolCss, ...(ctx.hasHatches ? ['zz-extra'] : [])])
  const asserts = [
    `val expected = ${expectedValue(ctx, classes, `${ctx.daisyName} all flags`, { withCloses: true })}`,
    'assertRendered(html, expected)',
    // Read only where the function has hatches: a sibling's test keeps the expected classes alone.
    'assertCommonFlags(html, expected)',
  ]
  for (const s of ctx.params.filter((p) => p.kind === 'nullableString')) {
    asserts.push(`assertTrue(html.contains("${s.name}=\\"x\\""), "${ctx.daisyName} ${s.name}")`)
  }
  const attrs = ctx.attributes
  for (const a of [...attrs.unconditional, ...attrs.guarded]) asserts.push(attrAssert(ctx, a))
  return asserts
}

function allFlagsTest(ctx) {
  const boolCss = []
  const args = allFlagsArgs(ctx, boolCss)
  const asserts = allFlagsAsserts(ctx, boolCss)
  // Inside a plain `div`, the hatches and their assertions are the same in every function, so they
  // go through `allFlags` and the constants; a nested wrapper keeps the call written out.
  if (ctx.wrapperFn !== 'div') return wrapTest(ctx, `${ctx.fnBase}_all_flags`, args, asserts)
  if (!ctx.hasHatches) return siblingAllFlagsTest(ctx, args, asserts)
  const hatched = args.map(arg => HATCH_CONSTANTS[arg] ?? arg).join(', ')
  const rest = asserts.filter(line => !line.startsWith('val expected') && line !== 'assertRendered(html, expected)' && line !== 'assertCommonFlags(html, expected)')
  const expected = asserts[0].replace('val expected = ', '')
  return `
    @Test
    fun ${ctx.fnBase}_all_flags() {
        val html = allFlags(${expected}) { daisy${ctx.daisyName}(${hatched}) }
${rest.map(line => `        ${line}`).join('\n')}${rest.length > 0 ? '\n' : ''}    }
`
}

/** A class sibling's all-flags test: no hatches, so one call when it asserts nothing else. */
function siblingAllFlagsTest(ctx, args, asserts) {
  // A sibling sets no attribute, so its asserts are only the expected classes and their check.
  const expected = asserts[0].replace('val expected = ', '')
  return `
    @Test
    fun ${ctx.fnBase}_all_flags() = assertRenders(${expected}) { daisy${ctx.daisyName}(${args.join(', ')}) }
`
}

/** The all-flags arguments every function passes alike, and the constant each becomes. */
const HATCH_CONSTANTS: Readonly<Record<string, string>> = {
  'id = htmlId("x-cov-id")': 'id = ID',
  'extraClasses = "zz-extra"': 'extraClasses = EXTRA',
  'block = { attributes["data-block"] = "yes" }': 'block = BLOCK',
}

function enumArmTests(ctx) {
  if (ctx.wrapperFn === 'div') return ctx.params.filter((p) => p.kind === 'enumClass').map(e => enumAxisTest(ctx, e)).join('')
  let tests = ''
  for (const e of ctx.params.filter((p) => p.kind === 'enumClass')) {
    for (const { entry, css } of e.enumEntries) {
      // `enumType`, not `baseType`: the parameter is typed `ClassValues<ButtonSize>?` and the
      // value written into the test is `ButtonSize.Lg`.
      const args = [`${e.name} = ${e.enumType}.${entry}`, ...requiredArgs(ctx)]
      const asserts = [renderedAssert(ctx, sortedClasses([ctx.base, css]), `${ctx.daisyName} ${e.name} ${entry}`, false)]
      tests += wrapTest(ctx, `${ctx.fnBase}_${e.name}_${entry.toLowerCase()}`, args, asserts)
    }
  }
  return tests
}

/** One test for an axis: a table from each entry to the classes it must render. */
function enumAxisTest(ctx, e) {
  const table = e.enumEntries.map(({ entry, css }) => `${e.enumType}.${entry} to "${sortedClasses([ctx.base, css])}"`).join(', ')
  const call = `daisy${ctx.daisyName}(${[`${e.name} = it`, ...requiredArgs(ctx)].join(', ')})`
  return `
    @Test
    fun ${ctx.fnBase}_${e.name}() = assertEachEntry("${ctx.daisyName} ${e.name}", mapOf(${table})) { ${call} }
`
}

function textArmTest(ctx) {
  if (!ctx.textParam) return ''
  const asserts = [
    renderedAssert(ctx, ctx.base, `${ctx.daisyName} text`, false),
    `assertTrue(html.contains("txtmark"), "${ctx.daisyName} text content")`,
  ]
  return wrapTest(ctx, `${ctx.fnBase}_text`, ['text = "txtmark"'], asserts)
}

function buildCoverageTests(fn, enums) {
  const ctx = coverageContext(fn, enums)
  return defaultsTest(ctx) + allFlagsTest(ctx) + enumArmTests(ctx) + textArmTest(ctx)
}

/**
 * One call per scope member, with no arguments beyond the ones it cannot do without.
 *
 * No arguments is the point rather than an economy: supplying a parameter means its DEFAULT
 * expression never runs, and those defaults are the lines the aggregated coverage gate counts.
 * One bare call reaches every default AND the delegation body, which is the whole member.
 *
 * The assertion is the marker class, because that is the only thing the member adds over the
 * top-level function its own component already tests.
 */
function scopeMemberTest(member): string {
  return `
    @Test
    fun scope_${member.name}() {
        val html = createHTML(prettyPrint = false).div {
            ${member.opener} {
                ${member.name}()
            }
        }
        assertTrue(html.contains("join-item"), "${member.name} in a ${member.opener}")
    }
`
}

/** What a coverage file needs: the fixed five, each non-flow wrapper, each external enum. */
function coverageImports(funcs, enums): Set<string> {
  const imports = new Set([
    'import io.github.ollin.kdaisyui.core.htmlId',
    'import kotlinx.html.div',
    'import kotlinx.html.stream.createHTML',
    'import kotlin.test.Test',
    'import kotlin.test.assertEquals',
    'import kotlin.test.assertTrue',
    'import kotlinx.html.FlowContent',
    'import kotlinx.html.Tag',
  ])
  for (const fn of funcs) {
    // `div` for FlowContent and Tag, which the set already holds.
    imports.add(`import kotlinx.html.${htmlTagFnFor(wrapperTagOf(fn.receiver))}`)
    for (const declared of fn.parameters) {
      const parameter = classifyParam(declared, enums)
      const external = EXTERNAL_ENUM_IMPORTS[parameter.baseType]
      if (external) imports.add(external)
    }
  }
  return imports
}

/**
 * The Kotlin of `<className>CoverageTest.kt` for one generated component source, or null when the
 * source declares no function. A pure function of its input: the snapshot test compares it with
 * the committed coverage tests, which need neither the submodule nor a regeneration.
 */
/** Each enum of the shape as the coverage tests read it: its entries and the class each writes. */
function enumTableOf(shape: ComponentShape) {
  return Object.fromEntries(shape.enums.map(declared => [declared.name, declared.entries.map(entry => ({ entry: entry.name, css: entry.cssClass }))]))
}

/**
 * Every function the component's file declares, in the order `generateKotlinFile` writes them:
 * each function followed by its class sibling, then the class-only siblings. Named without the
 * `daisy` every name starts with, which the tests put back.
 */
/**
 * The attributes a main function's body sets, in body order: its role and input type, then the
 * extras' fragments, those before the classes first. No other function sets one.
 */
function mainAttributesOf(config: MainFunctionConfig) {
  // The extras before the classes, then every extra: an attribute counts once, where it is first set.
  const extras = [...config.extras.filter(extra => extra.position === 'before_classes'), ...config.extras]
  const fromExtras = parseAttrProps(extras.map(extra => extra.apply).join('\n'))
  const fixed = [...(config.role ? ['role'] : []), ...(config.inputType ? ['type'] : [])]
  return { unconditional: new Set([...fixed, ...fromExtras.unconditional]), guarded: fromExtras.guarded }
}

const NO_ATTRIBUTES = { unconditional: new Set<string>(), guarded: new Set<string>() }

function coverageFunctionsOf(shape: ComponentShape, config: MainFunctionConfig) {
  // A main function writes the component's class; a part, a custom part and a sibling their own.
  const ownClasses = (fn: SiblingShape) => [fn.cssClass, ...fn.modifierClasses]
  // A sibling opens no element, so it has no builder.
  const sibling = (fn: SiblingShape) => ({ name: `${fn.name.slice('daisy'.length)}Classes`, receiver: 'Tag', parameters: siblingParameters(fn), baseClasses: ownClasses(fn), builder: null, attributes: NO_ATTRIBUTES })
  return [
    ...shape.functions.flatMap(fn => [
      { name: fn.name.slice('daisy'.length), receiver: fn.receiver, parameters: fn.parameters, baseClasses: fn.kind === 'main' ? [shape.prefix] : ownClasses(fn), builder: fn.tagBuilder as string as BuilderName, attributes: fn.kind === 'main' ? mainAttributesOf(config) : NO_ATTRIBUTES },
      ...(hasSibling(fn) ? [sibling(fn)] : []),
    ]),
    ...shape.siblingsOnly.map(sibling),
  ]
}

/**
 * The members of the component's scope, so the coverage tests can reach them: each is called
 * inside the main function's block.
 */
function scopeMembersOf(shape: ComponentShape): ScopeMember[] {
  return (shape.scope?.members ?? []).map(member => ({
    name: member.name,
    opener: shape.functions[0].name,
  }))
}

function coverageTestFor(className: string, shape: ComponentShape, config: MainFunctionConfig): string {
  const enums = enumTableOf(shape)
  const funcs = coverageFunctionsOf(shape, config)

  const imports = coverageImports(funcs, enums)

  let body = ''
  for (const fn of funcs) body += buildCoverageTests(fn, enums)
  for (const member of scopeMembersOf(shape)) body += scopeMemberTest(member)

  return `package io.github.ollin.kdaisyui.components

${[...imports].sort().join('\n')}

class ${className}CoverageTest {
${COVERAGE_HELPER}${body}}
`
}

/** Writes one component's coverage test to `outputDir`. */
function writeCoverageTest(outputDir: string, className: string, shape: ComponentShape, config: MainFunctionConfig): void {
  fs.writeFileSync(path.join(outputDir, `${className}CoverageTest.kt`), coverageTestFor(className, shape, config))
}

function documentedContext(): DocumentedContext {
  const directories = getAllComponentDirs()
  const models = directories.map(directory => readModel(directory)).filter((model): model is ComponentModel => !isSkipped(model))
  return { models, componentClasses: everyDeclaredClass(directories) }
}

// Its consumers are the entry point index-test-generator.ts, which reads and writes, and the
// unit tests.
export type { DocumentedContext }
export {
  // Targets of the refactorings in section 2.
  parseTestCases,
  // The documented-element half, compared with its committed output by documented-test-snapshot.test.ts.
  documentedTestFor,
  documentedContext,
  toClassName,
  // The coverage half, compared with its committed output by coverage-test-snapshot.test.ts.
  coverageTestFor,
  writeCoverageTest,
  // Added by add-mutation-testing and still untested — the gap that motivated this change.
  htmlTagForFn,
  parseAttrProps,
  attrAssert,
  closesTagAssert,
}
