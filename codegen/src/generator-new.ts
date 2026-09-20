/**
 * How KOTLIN states a component's generated API.
 *
 * What that API IS now lives in `component-shape.ts`, because `docs/reference/*.md` states the
 * same thing in Markdown and the two must not disagree. This file renders; it no longer decides.
 *
 * The one exception is function BODIES, which stay here in full. They need `extras[].apply`,
 * roles and input types, none of which a Markdown page documents, so modelling them would add a
 * shape with exactly one consumer.
 */

import { toCamelCase } from './classifier.ts'
import {
  readComponentConfig,
  staticAttributeDoc,
  type ComponentConfig,
  type ComponentShape,
  type CssClass,
  type EnumShape,
  type ExtraParameter,
  type FunctionShape,
  type ParameterShape,
  type ScopeShape,
  type StaticAttribute,
} from './component-shape.ts'

function renderEnum(shape: EnumShape): string {
  const kdoc = shape.documented
    ? `/** ${shape.categoryLabel} for this component (CSS prefix: \`${shape.prefix}-\`) */\n`
    : ''
  const entries = shape.entries
    .map(entry => {
      const kdocParts = [`CSS: \`${entry.cssClass}\``]
      if (entry.desc) kdocParts.push(entry.desc)
      return `    /** ${kdocParts.join(' — ')} */\n    ${entry.name}("${entry.cssClass}"),`
    })
    .join('\n')
  // `: ClassValues<Self>` is what lets `size = ButtonSize.Lg` and
  // `size = ButtonSize.Xs and at(Breakpoint.Lg, ButtonSize.Lg)` share one parameter. The type
  // argument is the enum itself, and `ClassValues` is invariant in it, so a toast placement is
  // not assignable to a button size.
  //
  // `className` stays internal; `classNames` is the single public accessor the variant API needs.
  const members = `    override val classNames: List<String> get() = listOf(className)`
  return (
    `${kdoc}enum class ${shape.name}(internal val className: String) : ClassValues<${shape.name}> {\n` +
    `${entries}\n    ;\n\n${members}\n}\n`
  )
}

function renderParameter(parameter: ParameterShape): string {
  const defaulted = parameter.default === null ? '' : ` = ${parameter.default}`
  return `    ${parameter.name}: ${parameter.type}${defaulted},`
}

/**
 * The `Renders <tag ...>` clause every generated function's summary line ends with.
 *
 * `htmlTag` and not `tagBuilder`: kotlinx.html spells three tags differently from the HTML it
 * emits, so the builder name is not an element name. This comment used to say
 * `Renders <fieldSet class="fieldset ...">`, and `<fieldSet>` is not an element — a reader
 * copying it into markup gets nothing. The reference pages have always named the element; only
 * this emitter conflated the two.
 *
 * The emitted CODE still calls `tagBuilder`, because that is the function that exists.
 */
function rendersClause(shape: FunctionShape): string {
  const attrs = staticAttributeDoc(shape.staticAttributes)
  const classes = [shape.cssClass, ...shape.modifierClasses].join(' ')
  return shape.cssClass === null
    ? `Structural wrapper. Renders \`<${shape.htmlTag}${attrs}>\`.`
    : `Renders \`<${shape.htmlTag} class="${classes} ..."${attrs}>\`.`
}

function summaryLine(shape: FunctionShape): string {
  const clause = rendersClause(shape)
  return shape.desc ? `${shape.desc} ${clause}` : clause
}

/**
 * Only the main function documents its parameters. Parts and custom parts get a one-line
 * comment — they all take the same four or five escape-hatch parameters, and repeating those
 * `@param` lines across every part of every component says nothing a reader does not know.
 */
function renderKdoc(shape: FunctionShape): string {
  const lines = [summaryLine(shape)]
  if (shape.kind === 'main') {
    for (const parameter of shape.parameters) {
      lines.push(parameter.doc ? `@param ${parameter.name} — ${parameter.doc}` : `@param ${parameter.name}`)
    }
  }
  if (lines.length === 1) return `/** ${lines[0]} */\n`
  return `/**\n * ${lines.join('\n * ')}\n */\n`
}

/**
 * How the body opens its element.
 *
 * Normally the kotlinx.html builder. A function whose `content` runs in a generated scope
 * constructs that scope instead and `visit`s it — which is what `div { }` itself expands to,
 * with the scope class in place of `DIV`. The tag is the scope, rather than the scope wrapping
 * a tag, because `.join` writes its corner-radius variables onto DIRECT children: a wrapper
 * would put an element between the two and the variables would not reach it.
 */
function tagOpening(shape: FunctionShape): string {
  if (shape.contentScope === undefined) return `${shape.tagBuilder} {`
  return `${shape.contentScope}(emptyMap(), consumer).visit {`
}

function renderFunction(shape: FunctionShape, body: string): string {
  const params = shape.parameters.map(renderParameter).join('\n')
  return `${renderKdoc(shape)}fun ${shape.receiver}.${shape.name}(\n${params}\n) {\n    ${tagOpening(shape)}\n${body}\n    }\n}`
}

/** `join-item` -> `joinItem`, the private helper a member calls to prepend the marker. */
function markerFunctionName(markerClass: string): string {
  return toCamelCase(markerClass)
}

/**
 * One member: the same signature as the top-level function, delegating to it with the marker
 * class added.
 *
 * The delegation goes through `flow`, whose declared type is `FlowContent`. Calling
 * `daisyButton(...)` unqualified here would resolve to this very member and recurse — a member
 * beats an extension, which is the same rule that makes the scope work at all. An explicit
 * receiver typed as the extension's own receiver is what reaches past it.
 *
 * Arguments are named rather than positional: the two parameter lists are generated from one
 * shape and cannot disagree about order, but a reader cannot see that, and a named argument
 * costs nothing.
 */
function renderScopeMember(shape: FunctionShape, markerClass: CssClass): string {
  const params = shape.parameters.map(parameter => `    ${renderParameter(parameter)}`).join('\n')
  const marker = markerFunctionName(markerClass)
  const args = shape.parameters
    .map(parameter =>
      parameter.name === 'extraClasses'
        ? `            extraClasses = ${marker}(extraClasses),`
        : `            ${parameter.name} = ${parameter.name},`,
    )
    .join('\n')
  return (
    `    /** [${shape.name}], marked \`${markerClass}\` because the call sits directly in the join. */\n` +
    `    fun ${shape.name}(\n${params}\n    ) {\n` +
    `        ${shape.name.replace(/^daisy/, 'flow.daisy')}(\n${args}\n        )\n` +
    `    }`
  )
}

/**
 * The scope class: the component's own element, plus one member per join item.
 *
 * `internal constructor`, so the only way to obtain one is to be inside the lambda. That is
 * what makes the marker class unwritable elsewhere — it is not merely undocumented outside a
 * join, it does not exist there.
 */
function renderScope(scope: ScopeShape, element: string): string {
  const marker = markerFunctionName(scope.markerClass)
  const members = scope.members.map(member => renderScopeMember(member, scope.markerClass)).join('\n\n')
  return (
    `/**\n` +
    ` * The content of a join. Extends [${element}], so every kotlinx.html builder still works here.\n` +
    ` *\n` +
    ` * A component call written DIRECTLY in this lambda resolves to the member below and emits\n` +
    ` * \`${scope.markerClass}\`; the same call nested inside another builder does not, because\n` +
    ` * kotlinx.html's \`@HtmlTagMarker\` hides this receiver there. Write \`this@daisyJoin.\` to reach\n` +
    ` * a member from a nested lambda deliberately.\n` +
    ` */\n` +
    `class ${scope.name} internal constructor(\n` +
    `    initialAttributes: Map<String, String>,\n` +
    `    consumer: TagConsumer<*>,\n` +
    `) : ${element}(initialAttributes, consumer) {\n\n` +
    `    /** This element as plain flow content, so a member below reaches the top-level function. */\n` +
    `    private val flow: FlowContent get() = this\n\n` +
    `${members}\n}\n\n` +
    `/** \`${scope.markerClass}\`, ahead of whatever the caller wrote. */\n` +
    `private fun ${marker}(extraClasses: String?): String =\n` +
    `    if (extraClasses == null) "${scope.markerClass}" else "${scope.markerClass} $extraClasses"\n`
  )
}

/** Renders static attributes as kotlinx.html body lines, indented for a tag block. */
function staticAttributeLines(entries: readonly StaticAttribute[]): string[] {
  return entries.map(
    ([name, value]) => `        attributes[${JSON.stringify(name)}] = ${JSON.stringify(value)}`,
  )
}

function applyLines(extras: readonly ExtraParameter[]): string[] {
  return extras.flatMap(extra => extra.apply.trim().split('\n').map(line => `        ${line}`))
}

/** The `content` / `text` tail shared by every function that can take children. */
/**
 * How the body writes the children — read off the SIGNATURE, like the classes above it.
 *
 * A function with no `content` parameter writes none: its element is void and cannot hold any.
 * The body used to call `content()` unconditionally, so the day the void rule reached parts and
 * custom parts, three generated files stopped compiling.
 */
function contentLines(shape: FunctionShape, hasTextParam: boolean): string[] {
  if (!shape.parameters.some(parameter => parameter.name === 'content')) return []
  if (!hasTextParam) return ['        content()']
  return [
    '        when {',
    '            content != null -> content()',
    '            text != null -> +text',
    '        }',
  ]
}

function mainFunctionBody(
  prefix: string,
  shape: FunctionShape,
  componentConfig: ComponentConfig,
): string {
  const { extras, hasTextParam, role, inputType } = componentConfig
  // The body follows the SIGNATURE rather than re-deriving the rule: if the shape declares no
  // `content` parameter, there is nothing to call, and the two can never disagree.

  const lines: string[] = ['        if (id != null) attributes["id"] = id.id']
  lines.push(...staticAttributeLines(shape.staticAttributes))
  if (role) lines.push(`        role = "${role}"`)
  if (inputType) lines.push(`        type = InputType.${inputType}`)
  lines.push(...applyLines(extras.filter(extra => extra.position === 'before_classes')))

  lines.push(`        addClassNames("${prefix}")`)
  lines.push(...classValuesLines(shape))
  lines.push(...booleanLines(shape))
  lines.push(...applyLines(extras.filter(extra => extra.position !== 'before_classes')))

  lines.push('        addClassNames(extraClasses)')
  lines.push('        if (attrs != null) attrs()')
  lines.push(...contentLines(shape, hasTextParam))

  return lines.join('\n')
}

/**
 * One `addClassNames` per `ClassValues` parameter the SIGNATURE declares — `variant`, `size`
 * and the measured enums, in declaration order. Read off the shape for the same reason the
 * booleans are: an enum the shape moved to a part is emitted by that part and by nothing else.
 *
 * Unguarded, because the `ClassValues?` overload of `addClassNames` returns on null. The guard
 * used to be emitted here, once per parameter, and every copy of it was a branch the coverage
 * and mutation gates had to drive separately to establish the same fact.
 */
function classValuesLines(shape: FunctionShape): string[] {
  return shape.parameters
    .filter(parameter => parameter.enumName !== undefined)
    .map(parameter => `        addClassNames(${parameter.name})`)
}

/**
 * One guarded `addClassNames` per boolean the SIGNATURE declares. The body follows the shape
 * rather than re-deriving which booleans exist: a boolean the shape moved to a part is emitted
 * by that part and by nothing else, and the two can never disagree.
 */
function booleanLines(shape: FunctionShape): string[] {
  return shape.parameters
    .filter(parameter => parameter.cssClass !== undefined)
    .map(parameter => `        if (${parameter.name}) addClassNames("${parameter.cssClass}")`)
}

/** Parts and custom parts share one body: id, attributes, classes, attrs, content. */
function secondaryFunctionBody(shape: FunctionShape): string {
  const hasTextParam = shape.parameters.some(parameter => parameter.name === 'text')

  const lines: string[] = ['        if (id != null) attributes["id"] = id.id']
  lines.push(...staticAttributeLines(shape.staticAttributes))
  if (shape.cssClass !== null) lines.push(`        addClassNames("${shape.cssClass}")`)
  lines.push(...shape.modifierClasses.map(cssClass => `        addClassNames("${cssClass}")`))
  lines.push(...classValuesLines(shape))
  lines.push(...booleanLines(shape))
  lines.push('        addClassNames(extraClasses)')
  lines.push('        if (attrs != null) attrs()')
  lines.push(...contentLines(shape, hasTextParam))

  return lines.join('\n')
}

function renderBody(
  fn: FunctionShape,
  prefix: string,
  componentConfig: ComponentConfig,
): string {
  return fn.kind === 'main'
    ? mainFunctionBody(prefix, fn, componentConfig)
    : secondaryFunctionBody(fn)
}

/** What one function's own signature and body name: its element, its builder, its receiver. */
function functionImports(fn: FunctionShape): string[] {
  return [
    `kotlinx.html.${fn.element}`,
    // A scoped function constructs its tag rather than calling the builder, so importing the
    // builder would leave the generated file with an import nothing uses.
    ...(fn.contentScope === undefined ? [`kotlinx.html.${fn.tagBuilder}`] : []),
    ...(fn.receiver !== 'FlowContent' ? [`kotlinx.html.${fn.receiver}`] : []),
  ]
}

/**
 * What a scope needs: the two pieces `div { }` uses internally to construct and visit a tag,
 * each member's element, and whatever the members' mirrored parameter lists name.
 */
function scopeImports(scope: ScopeShape): string[] {
  return [
    'kotlinx.html.TagConsumer',
    'kotlinx.html.visit',
    ...scope.members.map(member => `kotlinx.html.${member.element}`),
    ...scope.imports,
  ]
}

/** What the per-component config asks the body to call. */
function configImports(componentConfig: ComponentConfig): string[] {
  return [
    ...(componentConfig.role ? ['kotlinx.html.role'] : []),
    ...(componentConfig.inputType ? ['kotlinx.html.InputType'] : []),
    ...componentConfig.extras.flatMap(extra => extra.imports ?? []),
  ]
}

function collectImports(shape: ComponentShape, componentConfig: ComponentConfig): string[] {
  const imports = new Set([
    'io.github.ollin.kdaisyui.core.HtmlId',
    'io.github.ollin.kdaisyui.core.addClassNames',
    'kotlinx.html.FlowContent',
    // Every generated enum implements it, and every enum parameter is typed by it.
    ...(shape.enums.length > 0 ? ['io.github.ollin.kdaisyui.core.ClassValues'] : []),
    ...shape.functions.flatMap(functionImports),
    ...(shape.scope === undefined ? [] : scopeImports(shape.scope)),
    ...configImports(componentConfig),
  ])

  // Plain collation. The kdaisyui package (io.github.ollin.kdaisyui) already sorts
  // ahead of kotlin/kotlinx, so no special-casing is needed to group it first.
  return [...imports].sort((a, b) => a.localeCompare(b))
}

/**
 * Emit one component's Kotlin file from the shape that describes it.
 *
 * It used to take the shape's INPUTS and rebuild the shape itself, which made it impossible to
 * hand it a shape anything had added to — the join scope is attached after the per-component
 * build, because its members are other components' functions, and a rebuild inside here would
 * have discarded it.
 *
 * `config` is deliberately left to inference: it is the whole of `codegen-config.json`,
 * a large object with per-component sections, and modelling it properly is its own piece
 * of work rather than a side effect of a rename.
 */
export function generateKotlinFile(shape: ComponentShape, config) {
  const componentConfig = readComponentConfig(config, shape.componentName)

  const header = [
    `// GENERATED — DO NOT EDIT`,
    `// Source: daisyui/packages/docs/src/routes/(routes)/components/${shape.componentDir}/+page.md`,
    `// Regenerate: cd codegen && npm run generate`,
    ``,
    `package io.github.ollin.kdaisyui.components`,
    ``,
    ...collectImports(shape, componentConfig).map(i => `import ${i}`),
  ].join('\n')

  const enums = shape.enums.map(renderEnum).join('\n')
  const functions = shape.functions.map(fn =>
    renderFunction(fn, renderBody(fn, shape.prefix ?? '', componentConfig)),
  )
  const scope = shape.scope === undefined ? '' : renderScope(shape.scope, shape.functions[0].element)
  const body = [enums, ...functions, scope].filter(Boolean).join('\n\n')

  return `${header}\n\n${body}\n`
}
