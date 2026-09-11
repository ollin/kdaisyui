import fs from 'fs'
import path from 'path'
import { pathToFileURL } from 'node:url'
import { getAllComponentDirs, readComponentFrontmatter, toPascalCase } from './parser/frontmatter.js'
import { toCamelCase } from './classifier.js'

/**
 * The two string kinds this generator confuses most easily, and the defect that motivated
 * the whole TypeScript port.
 *
 * A kotlinx.html BUILDER is the Kotlin function you call — `fieldSet`, `textArea`, `div`.
 * A TAG is what it emits — `fieldset`, `textarea`, `div`. They are equal for most elements
 * and different for exactly the ones that matter, so a mistake survives every test that
 * uses a `div` and fails on the two that do not. That is precisely what happened in
 * `add-mutation-testing` task 4.1: `</fieldSet>` was asserted, and only `FieldsetCoverageTest`
 * and `TextareaCoverageTest` noticed.
 *
 * Branding them turns that into a call-site error. The total cost is THREE casts, each at a
 * point where a name genuinely comes into existence: `parseEmittedBuilder` (read out of a
 * generated body), `htmlTagForFn` (lowercased, and immediately validated by round-tripping),
 * and `wrapperTagOf` (derived from a Kotlin receiver type). Everything downstream is checked.
 */
export type BuilderName = string & { readonly __brand: 'BuilderName' }
export type TagName = string & { readonly __brand: 'TagName' }

const DOCS_DIR = path.resolve(import.meta.dirname, '../../daisyui/packages/docs/src/routes/(routes)/components')
// Committed generated root — a sibling of lib/src/, never inside it.
// Gradle passes --output-dir explicitly; this default is for a bare `node` run.
const DEFAULT_OUTPUT_DIR = path.resolve(import.meta.dirname, '../../lib/generated/test/kotlin/io/github/ollin/kdaisyui/components')

function parseArg(flag, fallback) {
  const prefix = `--${flag}=`
  for (const arg of process.argv) {
    if (arg.startsWith(prefix)) {
      return arg.slice(prefix.length)
    }
  }
  return fallback
}

const OUTPUT_DIR = parseArg('output-dir', DEFAULT_OUTPUT_DIR)
const CONFIG_PATH = path.resolve(import.meta.dirname, '../codegen-config.json')

function loadConfig() {
  if (!fs.existsSync(CONFIG_PATH)) {
    return { skip: [] }
  }
  return JSON.parse(fs.readFileSync(CONFIG_PATH, 'utf8'))
}

// A doc page is scanned line by line, and until now the position in that scan lived in four
// mutable locals threaded through one loop. Collecting them into one object is what lets each
// line kind below be read on its own — the loop no longer has to be simulated in your head to
// know what any branch does.
//
// `language` is deliberately NOT reset when a fence closes. That is load-bearing: the final
// flush consults it, and a heading inside an open fence leaves it standing. Both behaviours
// are pinned by tests in codegen/test/.
const TEST_CASE_HEADING = /^### ~(.+)$/

function newScan() {
  return { cases: [], current: null, insideBlock: false, blockLines: [], language: null }
}

function flushCurrentCase(scan) {
  scan.current.html = scan.blockLines.join('\n')
  scan.cases.push(scan.current)
}

function startCase(scan, name) {
  if (scan.current && scan.blockLines.length > 0) flushCurrentCase(scan)
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
  scan.blockLines = []
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

function extractDaisyClasses(html) {
  const classes = []
  const match = html.match(/\$\$([a-z-]+)/g)
  if (match) {
    for (const m of match) {
      classes.push(m.slice(2))
    }
  }
  return [...new Set(classes)]
}

function toClassName(componentName) {
  return toPascalCase(componentName)
}

// The five frontmatter sections whose entries become component parameters. `component` is
// not among them: it names the element itself, not a modifier of it.
const MODIFIER_CATEGORIES = ['placement', 'modifier', 'direction', 'behavior', 'style']

/**
 * Every class named under the modifier categories, in document order.
 *
 * A category that is not an array is skipped rather than trusted — the frontmatter is
 * hand-written YAML in a submodule we do not control, and a scalar where a list belongs
 * should not take the build down.
 */
function modifierClasses(classnames) {
  return MODIFIER_CATEGORIES
    .flatMap((category) => {
      const items = classnames?.[category]
      return Array.isArray(items) ? items : []
    })
    .filter((item) => item.class)
    .map((item) => item.class)
}

function buildClassMappings(frontmatter) {
  const componentClass = frontmatter.classnames?.component?.[0]?.class
  const allowedClasses = new Set(componentClass ? [componentClass] : [])
  const classToParam = {}
  const paramToGeneratedClass = {}

  for (const cssClass of modifierClasses(frontmatter.classnames)) {
    allowedClasses.add(cssClass)
    // `replace` with a STRING replaces the first occurrence only, so `btn-btn-x` yields
    // `btnX` rather than `x`. Pinned by test; do not reach for a regex here.
    const paramName = toCamelCase(cssClass.replace(`${componentClass}-`, ''))
    classToParam[cssClass] = paramName
    paramToGeneratedClass[paramName] = cssClass
  }

  return { allowedClasses, classToParam, paramToGeneratedClass, componentClass }
}

function filterContainerClasses(classes, allowedClasses) {
  return classes.filter(c => allowedClasses.has(c))
}

function toTestFunctionName(name) {
  let funcName = name
    .toLowerCase()
    .replace(/[^a-z0-9]+/g, '_')
    .replace(/^_|_$/g, '')
    .replace(/_+/g, '_')
  
  if (/^[0-9]/.test(funcName)) {
    funcName = `test_${funcName}`
  }
  
  return funcName
}

function mapClassesToParams(classes, classToParam) {
  const params = {}
  for (const cls of classes) {
    if (classToParam[cls]) {
      params[classToParam[cls]] = true
    }
  }
  return params
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
  assertions.push(...staticAttributeAssertions(part.staticAttributes))
  return assertions.join('\n')
}

/**
 * Pins the attributes the main component emits unconditionally. The class-mismatch tests
 * compare only the class attribute, so an attribute that carries no CSS class would
 * otherwise be invisible to both the generated tests and generated-sources-drift.
 */
function generateComponentAttributeTest(className, componentAttributes) {
  const assertions = staticAttributeAssertions(componentAttributes)
  if (assertions.length === 0) return ''

  return `
    @Test
    fun renders_static_attributes() {
        val html = createHTML(prettyPrint = false).div {
            daisy${className} {
            }
        }
${assertions.join('\n')}
    }
`
}

function generateCustomPartTests(className, customParts) {
  if (!customParts || customParts.length === 0) return ''

  let kotlin = ''
  for (const part of customParts) {
    const funcName = `custom_part_${part.name.toLowerCase()}_renders_${part.element.toLowerCase()}`
    const tag = part.element.toLowerCase()
    const receiver = part.receiver || 'FlowContent'
    
    // Determine wrapping context based on receiver type
    const wrapperTag = wrapperTagOf(receiver)
    const wrapperFn = htmlTagFnFor(wrapperTag)

    kotlin += `
    @Test
    fun ${funcName}() {
        val html = createHTML(prettyPrint = false).${wrapperFn} {
            daisy${className}${part.name} {
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
  return (receiver === 'FlowContent' ? 'div' : receiver.toLowerCase()) as TagName
}

function htmlTagFnFor(tag: TagName): BuilderName {
  const exceptions = { fieldset: 'fieldSet', textarea: 'textArea' }
  return exceptions[tag] ?? tag
}

/** Reads a component-keyed config section, e.g. `customParts.modal`. */
function configSection(config, section, componentName, fallback) {
  return config?.[section]?.[componentName] ?? fallback
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

/** The classes the generator will emit for the classes a doc example carries. */
function expectedClassesFor(containerClasses, componentClass) {
  const generated = [componentClass]
  for (const c of containerClasses) {
    if (c === componentClass) continue
    generated.push(c.startsWith(`${componentClass}-`) ? c : `${componentClass}-${c}`)
  }
  return generated.sort().join(' ')
}

function paramsToArgs(params) {
  return Object.entries(params)
    .filter(([k, v]) => v === true)
    .map(([k, v]) => `${k} = true`)
    .join(', ')
}

/** Asserts the component emits exactly the classes the doc example shows. */
function generateClassTest(className, { testName, args, expectedClasses, caseName }) {
  return `
    @Test
    fun ${testName}() {
        val html = createHTML(prettyPrint = false).div {
            daisy${className}(${args}) {
            }
        }
        val expectedClasses = "${expectedClasses}"
        val actualClasses = html.substringAfter("class=\\"").substringBefore("\\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for ${caseName}")
    }
`
}

function generateKotlinTest(componentName, testCases, frontmatter, config) {
  const className = toClassName(componentName)
  const { allowedClasses, classToParam, componentClass } = buildClassMappings(frontmatter)
  const customParts = configSection(config, 'customParts', componentName, [])
  const attributeTest = generateComponentAttributeTest(className, configSection(config, 'componentAttributes', componentName, {}))
  
  const extraImports = new Set()
  if (customParts.length > 0 || attributeTest) {
    extraImports.add('import kotlin.test.assertTrue')
  }
  for (const part of customParts) {
    const receiver = part.receiver || 'FlowContent'
    if (receiver !== 'FlowContent') {
      extraImports.add(`import kotlinx.html.${htmlTagFnFor(wrapperTagOf(receiver))}`)
    }
  }
  const extraImportLines = [...extraImports].sort().join('\n')
  
  let kotlin = `package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals${extraImportLines ? '\n' + extraImportLines : ''}

class ${className}Test {
`
  
  const usedNames = new Set()
  
  for (const tc of testCases) {
    const containerClasses = filterContainerClasses(extractDaisyClasses(tc.html), allowedClasses)
    kotlin += generateClassTest(className, {
      testName: uniqueTestName(usedNames, toTestFunctionName(tc.name)),
      args: paramsToArgs(mapClassesToParams(containerClasses, classToParam)),
      expectedClasses: expectedClassesFor(containerClasses, componentClass),
      caseName: tc.name,
    })
  }
  
  kotlin += attributeTest
  kotlin += generateCustomPartTests(className, customParts)
  
  kotlin += `}
`
  
  return kotlin
}

function generateForComponent(componentName, config) {
  const pageFile = path.join(DOCS_DIR, componentName, '+page.md')
  
  if (!fs.existsSync(pageFile)) {
    return { success: false, error: 'File not found' }
  }
  
  const frontmatter = readComponentFrontmatter(componentName)
  if (!frontmatter) {
    return { success: false, error: 'No frontmatter' }
  }
  
  const content = fs.readFileSync(pageFile, 'utf8')
  const testCases = parseTestCases(content)
  
  if (testCases.length === 0) {
    return { success: false, error: 'No test cases' }
  }
  
  const kotlin = generateKotlinTest(componentName, testCases, frontmatter, config)
  const className = toClassName(componentName)
  const outFile = path.join(OUTPUT_DIR, `${className}Test.kt`)
  
  fs.writeFileSync(outFile, kotlin)
  return { success: true, testCount: testCases.length }
}

// Exhaustive branch-coverage tests: parse each generated component source and
// drive every branch of every function with assertions on the rendered HTML.

// Where the generated component sources are READ BACK from, to drive every branch
// of every function. Gradle passes --components-dir so lib/build.gradle.kts stays
// the single source of that path; the default only serves a bare `node` run.
//
// This used to be a second hardcoded copy of the path, and when the real one moved
// it silently produced no coverage tests at all — see generateAllCoverage below.
const GENERATED_MAIN_DIR = parseArg(
  'components-dir',
  path.resolve(import.meta.dirname, '../../lib/generated/main/kotlin/io/github/ollin/kdaisyui/components'),
)

// Enum types from kotlinx.html (not declared in the source): arms can't be
// enumerated, so the `!= null` branch is driven once with a known value.
const EXTERNAL_ENUM_VALUES = { ButtonType: 'ButtonType.button' }
const EXTERNAL_ENUM_IMPORTS = { ButtonType: 'import kotlinx.html.ButtonType' }

function lowerFirst(s) {
  return s.charAt(0).toLowerCase() + s.slice(1)
}

/** Walk from an opening delimiter to its matching close, respecting nesting. */
function matchDelimiter(str, openIdx, open, close) {
  let depth = 0
  for (let i = openIdx; i < str.length; i++) {
    if (str[i] === open) depth++
    else if (str[i] === close && --depth === 0) return i
  }
  throw new Error(`Unbalanced ${open} from index ${openIdx}`)
}

/** Map enumTypeName -> [{ entry, css }] for class-mapping enums in the file. */
function parseEnumDefinitions(content) {
  const enums = {}
  const re = /enum class (\w+)\(internal val className: String\)\s*\{([\s\S]*?)\n\}/g
  let m
  while ((m = re.exec(content)) !== null) {
    const entries = []
    const entryRe = /^\s*(\w+)\("([^"]+)"\),?\s*$/gm
    let em
    while ((em = entryRe.exec(m[2])) !== null) {
      entries.push({ entry: em[1], css: em[2] })
    }
    enums[m[1]] = entries
  }
  return enums
}

/** Extract every generated `fun <Receiver>.daisy<Name>(...) { ... }` block. */
function findFunctions(content) {
  const funcs = []
  const headerRe = /fun\s+(\w+)\.daisy(\w+)\s*\(/g
  let m
  while ((m = headerRe.exec(content)) !== null) {
    const parenStart = headerRe.lastIndex - 1
    const parenEnd = matchDelimiter(content, parenStart, '(', ')')
    const braceStart = content.indexOf('{', parenEnd)
    const braceEnd = matchDelimiter(content, braceStart, '{', '}')
    funcs.push({
      receiver: m[1],
      name: m[2],
      paramBlock: content.slice(parenStart + 1, parenEnd),
      body: content.slice(braceStart + 1, braceEnd),
    })
    headerRe.lastIndex = braceEnd
  }
  return funcs
}

/** Split a param block on top-level commas (parens protect lambda types). */
function splitParams(block) {
  const parts = []
  let depth = 0
  let last = 0
  for (let i = 0; i < block.length; i++) {
    const ch = block[i]
    if (ch === '(') depth++
    else if (ch === ')') depth--
    else if (ch === ',' && depth === 0) {
      parts.push(block.slice(last, i))
      last = i + 1
    }
  }
  const tail = block.slice(last)
  if (tail.trim()) parts.push(tail)
  return parts.map((p) => p.trim()).filter(Boolean)
}

const PARAM_KIND_RULES = [
  [(c) => c.baseType === 'Boolean', 'boolean'],
  [(c) => c.name === 'id', 'id'],
  [(c) => c.name === 'extraClasses', 'extraClasses'],
  [(c) => c.name === 'attrs', 'attrs'],
  [(c) => c.name === 'content', (c) => (c.nullable ? 'contentOptional' : 'contentRequired')],
  [(c) => c.name === 'text' && c.baseType === 'String', 'text'],
  [(c) => c.nullable && c.baseType === 'String', 'nullableString'],
  [(c) => c.nullable && c.enums[c.baseType], 'enumClass'],
  [(c) => c.nullable && EXTERNAL_ENUM_VALUES[c.baseType], 'enumExternal'],
  [(c) => !c.nullable && c.hasDefault, 'presetNonNull'],
]

function paramKind(ctx) {
  for (const [matches, kind] of PARAM_KIND_RULES) {
    if (matches(ctx)) return typeof kind === 'function' ? kind(ctx) : kind
  }
  return 'other'
}

function classifyParam(raw, enums) {
  const colon = raw.indexOf(':')
  const name = raw.slice(0, colon).trim()
  const rest = raw.slice(colon + 1).trim()
  const eq = rest.indexOf('=')
  const type = (eq >= 0 ? rest.slice(0, eq) : rest).trim()
  const nullable = type.endsWith('?')
  const baseType = (nullable ? type.slice(0, -1) : type).trim()
  const kind = paramKind({ name, baseType, nullable, hasDefault: eq >= 0, enums })
  return { name, type, baseType, nullable, hasDefault: eq >= 0, kind, enumEntries: kind === 'enumClass' ? enums[baseType] : null }
}

/** The single unguarded `addClassNames("...")` that names this element. */
function parseBaseClass(body) {
  const m = body.match(/^\s*addClassNames\("([^"]+)"\)\s*$/m)
  return m ? m[1] : null
}

/**
 * The kotlinx.html BUILDER the function opens with. Every generated component body
 * starts with exactly one `<builder> {` line.
 *
 * A builder name is not an HTML tag name — see `htmlTagForFn`.
 */
function parseEmittedBuilder(body: string): BuilderName | null {
  const m = body.match(/^\s*([a-z][\w]*)\s*\{\s*$/m)
  // One of the two points a branded name is created: everything downstream is checked.
  return m ? (m[1] as BuilderName) : null
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
 * HTML void elements. They have no closing tag, so the "element is closed"
 * assertion below does not apply to them and would assert something false.
 */
// Deliberately `Set<string>` rather than `Set<TagName>`: it is only ever queried with a
// `TagName`, which is assignable to `string`, so branding the literals would buy nothing
// and cost fourteen casts.
const VOID_TAGS: Set<string> = new Set([
  'area', 'base', 'br', 'col', 'embed', 'hr', 'img', 'input',
  'link', 'meta', 'param', 'source', 'track', 'wbr',
])

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
 * The HTML attributes a component body sets, split by whether a guard protects them.
 *
 * Three shapes occur in generated bodies, and all three are attribute assignments to
 * the kotlinx.html tag receiver:
 *
 *     type = InputType.range                                    // unconditional
 *     if (disabled) this.disabled = true                        // boolean param
 *     if (disabled) { this.disabled = true; addClassNames(…) }   // boolean param, block
 *     if (type != null) this.type = type                        // nullable param
 *
 * `attributes["id"] = …` is deliberately NOT matched: `attributes` is followed by `[`
 * rather than `=`, and the id is already asserted separately.
 *
 * Only all-lowercase property names are reported. A kotlinx.html property whose name
 * is camelCase generally renames on the way out — `htmlFor` emits `for=` — and there
 * is no table here to invert. Skipping them asserts nothing rather than something
 * false, the same trade `htmlTagForFn` makes.
 */
const ATTR_ASSIGN = /(?:this\.)?\b([a-z][a-z0-9]*)\s*=\s*[^=]/g

function parseAttrProps(body) {
  const unconditional = new Set()
  const guarded = new Set()
  for (const line of body.split('\n')) {
    const trimmed = line.trim()
    if (!trimmed || trimmed.startsWith('addClassNames')) continue
    const isGuarded = trimmed.startsWith('if (')
    // On a guarded line, only look at what follows the condition.
    const scanned = isGuarded ? trimmed.slice(trimmed.indexOf(')') + 1) : trimmed
    for (const m of scanned.matchAll(ATTR_ASSIGN)) {
      ;(isGuarded ? guarded : unconditional).add(m[1])
    }
  }
  return { unconditional, guarded }
}

/**
 * Assert an attribute is PRESENT, by name, without asserting its value.
 *
 * The value is deliberately not checked. Doing so would mean predicting how
 * kotlinx.html renders each enum, and the entry name is not the rendered value —
 * `InputType.checkBox` emits `checkbox`. Replicating that mapping here would be a
 * second source of truth that can disagree with the library.
 *
 * Presence is exactly strong enough for what mutation testing found: every one of
 * these mutants REMOVES the setter call, and a removed call leaves no attribute at
 * all. A negated guard has the same effect on the all-flags case, which sets every
 * parameter.
 */
function attrAssert(ctx, name) {
  return `assertTrue(html.contains("${name}=\\""), "${ctx.daisyName} sets ${name}")`
}

function closesTagAssert(ctx) {
  const closes = closesSuffix(ctx)
  if (!closes) return null
  return `assertTrue(html.endsWith("${closes}"), "${ctx.daisyName} closes")`
}

/** CSS class(es) a boolean modifier adds via `if (param) addClassNames("...")`. */
function boolClassesFor(body, param) {
  const line = body.match(new RegExp(`^\\s*if \\(${param}\\)(.*)$`, 'm'))
  if (!line) return []
  const css = []
  const re = /addClassNames\("([^"]+)"\)/g
  let m
  while ((m = re.exec(line[1])) !== null) css.push(m[1])
  return css
}

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
    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\\"").substringBefore("\\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\\"x-cov-id\\""), "$label id")
        assertTrue(html.contains("data-attrs=\\"yes\\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\\"yes\\""), "$label content")
    }
`

/** The exact tail a correctly closed component leaves, or null when there is none to assert. */
function closesSuffix(ctx) {
  const tag = ctx.tagFn ? htmlTagForFn(ctx.tagFn) : null
  if (!tag || VOID_TAGS.has(tag)) return null
  return `</${tag}></${ctx.wrapperTag}>`
}

function renderedAssert(ctx, classes, label, withCloses) {
  const closes = withCloses ? closesSuffix(ctx) : null
  return `assertRendered(html, "${classes}", "${label}"${closes ? `, closes = "${closes}"` : ''})`
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
  const params = splitParams(fn.paramBlock).map((raw) => classifyParam(raw, enums))
  return {
    params,
    body: fn.body,
    base: parseBaseClass(fn.body) || '',
    wrapperFn: htmlTagFnFor(wrapperTagOf(fn.receiver)),
    // The wrapper's CLOSING tag, which is the raw receiver name — not `wrapperFn`,
    // because `htmlTagFnFor` renames a few builders away from their tag (`object`
    // becomes `htmlObject`) and `</htmlObject>` is not a thing.
    wrapperTag: wrapperTagOf(fn.receiver),
    tagFn: parseEmittedBuilder(fn.body),
    fnBase: lowerFirst(fn.name),
    daisyName: fn.name,
    required: params.find((p) => p.kind === 'contentRequired'),
    hasContent: params.some((p) => p.kind === 'contentRequired' || p.kind === 'contentOptional'),
    textParam: params.find((p) => p.kind === 'text'),
  }
}

function wrapTest(ctx, tname, args, asserts) {
  return renderTest(tname, ctx.wrapperFn, args, asserts).replace(`daisyREPLACE`, `daisy${ctx.daisyName}`)
}

function defaultsTest(ctx) {
  const args = ctx.required ? ['content = { }'] : []
  const asserts = ctx.base
    ? [renderedAssert(ctx, ctx.base, `${ctx.daisyName} defaults`, true)]
    : [`assertTrue(!html.contains("class=\\""), "${ctx.daisyName} defaults emits no class")`]
  for (const a of parseAttrProps(ctx.body).unconditional) asserts.push(attrAssert(ctx, a))
  // A component with no base class never reaches the helper, so its closure assertion is
  // still emitted on its own line.
  if (!ctx.base) {
    const closes = closesTagAssert(ctx)
    if (closes) asserts.push(closes)
  }
  return wrapTest(ctx, `${ctx.fnBase}_defaults`, args, asserts)
}

function allFlagsArgs(ctx, boolCss) {
  const args = ['id = htmlId("x-cov-id")']
  for (const b of ctx.params.filter((p) => p.kind === 'boolean')) {
    args.push(`${b.name} = true`)
    boolCss.push(...boolClassesFor(ctx.body, b.name))
  }
  for (const s of ctx.params.filter((p) => p.kind === 'nullableString')) args.push(`${s.name} = "x"`)
  for (const e of ctx.params.filter((p) => p.kind === 'enumExternal')) args.push(`${e.name} = ${EXTERNAL_ENUM_VALUES[e.baseType]}`)
  args.push('extraClasses = "zz-extra"', 'attrs = { attributes["data-attrs"] = "yes" }')
  if (ctx.hasContent) args.push('content = { attributes["data-content"] = "yes" }')
  return args
}

function allFlagsAsserts(ctx, boolCss) {
  // The id/attrs/content trio is identical in every all-flags case, so it lives in
  // `assertCommonFlags` rather than being repeated three times per component.
  //
  // Kept SEPARATE from `assertRendered` on purpose. Folding both into one helper was tried
  // and reverted: it needed five parameters against Kotlin's threshold of four and traded
  // the duplication for an Excess Number of Function Arguments smell. Two helpers of three
  // and four parameters say the same thing and trip neither rule.
  const asserts = [
    renderedAssert(ctx, sortedClasses([ctx.base, ...boolCss, 'zz-extra']), `${ctx.daisyName} all flags`, true),
    `assertCommonFlags(html, "${ctx.daisyName}"${ctx.hasContent ? '' : ', content = false'})`,
  ]
  for (const s of ctx.params.filter((p) => p.kind === 'nullableString')) {
    asserts.push(`assertTrue(html.contains("${s.name}=\\"x\\""), "${ctx.daisyName} ${s.name}")`)
  }
  const attrs = parseAttrProps(ctx.body)
  for (const a of [...attrs.unconditional, ...attrs.guarded]) asserts.push(attrAssert(ctx, a))
  return asserts
}

function allFlagsTest(ctx) {
  const boolCss = []
  const args = allFlagsArgs(ctx, boolCss)
  return wrapTest(ctx, `${ctx.fnBase}_all_flags`, args, allFlagsAsserts(ctx, boolCss))
}

function enumArmTests(ctx) {
  let tests = ''
  for (const e of ctx.params.filter((p) => p.kind === 'enumClass')) {
    for (const { entry, css } of e.enumEntries) {
      const args = ctx.required ? [`${e.name} = ${e.baseType}.${entry}`, 'content = { }'] : [`${e.name} = ${e.baseType}.${entry}`]
      const asserts = [renderedAssert(ctx, sortedClasses([ctx.base, css]), `${ctx.daisyName} ${e.name} ${entry}`, false)]
      tests += wrapTest(ctx, `${ctx.fnBase}_${e.name}_${entry.toLowerCase()}`, args, asserts)
    }
  }
  return tests
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

function generateCoverageForFile(fileName) {
  const filePath = path.join(GENERATED_MAIN_DIR, fileName)
  const content = fs.readFileSync(filePath, 'utf8')
  const className = fileName.replace(/\.kt$/, '')
  const enums = parseEnumDefinitions(content)
  const funcs = findFunctions(content)
  if (funcs.length === 0) return { success: false, error: 'No functions' }

  const imports = new Set([
    'import io.github.ollin.kdaisyui.core.htmlId',
    'import kotlinx.html.div',
    'import kotlinx.html.stream.createHTML',
    'import kotlin.test.Test',
    'import kotlin.test.assertEquals',
    'import kotlin.test.assertTrue',
  ])
  for (const fn of funcs) {
    if (fn.receiver !== 'FlowContent') imports.add(`import kotlinx.html.${htmlTagFnFor(wrapperTagOf(fn.receiver))}`)
    for (const raw of splitParams(fn.paramBlock)) {
      const p = classifyParam(raw, enums)
      if (p.kind === 'enumExternal' && EXTERNAL_ENUM_IMPORTS[p.baseType]) imports.add(EXTERNAL_ENUM_IMPORTS[p.baseType])
    }
  }

  let body = ''
  for (const fn of funcs) body += buildCoverageTests(fn, enums)

  const kotlin = `package io.github.ollin.kdaisyui.components

${[...imports].sort().join('\n')}

class ${className}CoverageTest {
${COVERAGE_HELPER}${body}}
`
  fs.writeFileSync(path.join(OUTPUT_DIR, `${className}CoverageTest.kt`), kotlin)
  return { success: true, funcCount: funcs.length }
}

function generateAllCoverage() {
  // FAIL, never skip. This used to warn and return, so a wrong path produced zero
  // coverage tests while the build stayed green and the other generators kept the
  // test count looking plausible. That cost 8% line and 9% branch coverage without
  // turning anything red. A missing input is a broken build, not a warning.
  if (!fs.existsSync(GENERATED_MAIN_DIR)) {
    throw new Error(
      `Generated component sources not found at ${GENERATED_MAIN_DIR}. ` +
      `Coverage tests are read back from them, so this cannot be skipped. ` +
      `Pass --components-dir=<path>, or check generatedMainDir in lib/build.gradle.kts.`
    )
  }
  const files = fs.readdirSync(GENERATED_MAIN_DIR).filter((f) => f.endsWith('.kt')).sort()
  if (files.length === 0) {
    throw new Error(`No .kt files in ${GENERATED_MAIN_DIR}; generateComponents must run first.`)
  }
  let generated = 0
  for (const file of files) {
    const result = generateCoverageForFile(file)
    if (result.success) generated++
    else console.log(`  ⊘ coverage ${file}: ${result.error}`)
  }
  console.log(`Generated coverage tests for ${generated} component files`)
}

/**
 * Generate one component and print its progress line.
 * @returns whether it produced tests — the caller only needs the tally.
 */
function generateAndReport(componentName, config) {
  if (config.skip?.includes(componentName)) {
    console.log(`  ⊘ ${componentName}: Skipped (alias)`)
    return false
  }
  const result = generateForComponent(componentName, config)
  console.log(
    result.success
      ? `  ✓ ${componentName}: ${result.testCount} tests`
      : `  ⊘ ${componentName}: ${result.error}`,
  )
  return result.success
}

function generateAllComponents(config) {
  console.log('Generating tests for all components...\n')

  let generated = 0
  let skipped = 0
  for (const componentName of getAllComponentDirs()) {
    if (generateAndReport(componentName, config)) generated++
    else skipped++
  }

  console.log(`\nGenerated tests for ${generated} components, skipped ${skipped}`)
  generateAllCoverage()
}

/** Single-component mode. Unlike the bulk mode, a failure here is fatal: it was asked for. */
function generateSingleComponent(componentName, config) {
  if (config.skip?.includes(componentName)) {
    console.error(`Error: ${componentName} is skipped (alias)`)
    process.exit(1)
  }
  const result = generateForComponent(componentName, config)
  if (!result.success) {
    console.error(`Error: ${result.error}`)
    process.exit(1)
  }
  console.log(`Generated ${result.testCount} tests for ${componentName}`)
}

function printUsageAndExit() {
  console.log('Usage: node test-generator.ts <component-name|all>')
  console.log('Examples:')
  console.log('  node test-generator.ts dropdown')
  console.log('  node test-generator.ts all')
  process.exit(1)
}

function main() {
  const [mode] = process.argv.slice(2)
  const config = loadConfig()

  if (mode === 'all') return generateAllComponents(config)
  if (mode) return generateSingleComponent(mode, config)
  printUsageAndExit()
}

// Run only when invoked directly — `node src/test-generator.ts all …`, which is how Gradle
// calls it. Without this guard, importing the module to test one function would regenerate
// all 66 components as a side effect, so no unit test could exist. That is why this file
// has none today.
if (process.argv[1] && import.meta.url === pathToFileURL(process.argv[1]).href) {
  main()
}

// Exported for tests only. This module has no other consumer: Gradle runs it as a script,
// and nothing in codegen/ imports it.
export {
  // Targets of the refactorings in section 2.
  parseTestCases,
  buildClassMappings,
  // Added by add-mutation-testing and still untested — the gap that motivated this change.
  parseEmittedBuilder,
  htmlTagForFn,
  parseAttrProps,
  attrAssert,
  closesTagAssert,
}
