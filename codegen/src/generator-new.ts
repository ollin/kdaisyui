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

import { toCamelCase, type ClassifiedComponent } from './classifier.ts'
import {
  booleanParameterClasses,
  buildComponentShape,
  escapeKotlinKeyword,
  readComponentConfig,
  staticAttributeDoc,
  type ComponentConfig,
  type ComponentShape,
  type ComponentSource,
  type EnumShape,
  type ExtraParameter,
  type FunctionShape,
  type ParameterShape,
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
  return `${kdoc}enum class ${shape.name}(internal val className: String) {\n${entries}\n}\n`
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
  return shape.cssClass === null
    ? `Structural wrapper. Renders \`<${shape.htmlTag}${attrs}>\`.`
    : `Renders \`<${shape.htmlTag} class="${shape.cssClass} ..."${attrs}>\`.`
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

function renderFunction(shape: FunctionShape, body: string): string {
  const params = shape.parameters.map(renderParameter).join('\n')
  return `${renderKdoc(shape)}fun ${shape.receiver}.${shape.name}(\n${params}\n) {\n    ${shape.tagBuilder} {\n${body}\n    }\n}`
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
function contentLines(hasTextParam: boolean): string[] {
  if (!hasTextParam) return ['        content()']
  return [
    '        when {',
    '            content != null -> content()',
    '            text != null -> +text',
    '        }',
  ]
}

function mainFunctionBody(
  classified: ClassifiedComponent,
  shape: FunctionShape,
  componentConfig: ComponentConfig,
): string {
  const { prefix } = classified
  const { extras, hasTextParam, role, inputType } = componentConfig
  // The body follows the SIGNATURE rather than re-deriving the rule: if the shape declares no
  // `content` parameter, there is nothing to call, and the two can never disagree.
  const takesContent = shape.parameters.some(parameter => parameter.name === 'content')

  const lines: string[] = ['        if (id != null) attributes["id"] = id.id']
  lines.push(...staticAttributeLines(shape.staticAttributes))
  if (role) lines.push(`        role = "${role}"`)
  if (inputType) lines.push(`        type = InputType.${inputType}`)
  lines.push(...applyLines(extras.filter(extra => extra.position === 'before_classes')))

  lines.push(`        addClassNames("${prefix}")`)
  if (classified.colors.length > 0) lines.push('        if (variant != null) addClassNames(variant.className)')
  if (classified.sizes.length > 0) lines.push('        if (size != null) addClassNames(size.className)')
  for (const cls of booleanParameterClasses(classified, componentConfig)) {
    lines.push(`        if (${escapeKotlinKeyword(toCamelCase(cls))}) addClassNames("${prefix}-${cls}")`)
  }
  lines.push(...applyLines(extras.filter(extra => extra.position !== 'before_classes')))

  lines.push('        addClassNames(extraClasses)')
  lines.push('        if (attrs != null) attrs()')
  if (takesContent) lines.push(...contentLines(hasTextParam))

  return lines.join('\n')
}

/** Parts and custom parts share one body: id, attributes, classes, attrs, content. */
function secondaryFunctionBody(shape: FunctionShape): string {
  const hasTextParam = shape.parameters.some(parameter => parameter.name === 'text')

  const lines: string[] = ['        if (id != null) attributes["id"] = id.id']
  lines.push(...staticAttributeLines(shape.staticAttributes))
  if (shape.cssClass !== null) lines.push(`        addClassNames("${shape.cssClass}")`)
  lines.push('        addClassNames(extraClasses)')
  lines.push('        if (attrs != null) attrs()')
  lines.push(...contentLines(hasTextParam))

  return lines.join('\n')
}

function renderBody(
  shape: FunctionShape,
  classified: ClassifiedComponent,
  componentConfig: ComponentConfig,
): string {
  return shape.kind === 'main'
    ? mainFunctionBody(classified, shape, componentConfig)
    : secondaryFunctionBody(shape)
}

function collectImports(shape: ComponentShape, componentConfig: ComponentConfig): string[] {
  const imports = new Set([
    'io.github.ollin.kdaisyui.core.HtmlId',
    'io.github.ollin.kdaisyui.core.addClassNames',
    'kotlinx.html.FlowContent',
  ])

  for (const fn of shape.functions) {
    imports.add(`kotlinx.html.${fn.element}`)
    imports.add(`kotlinx.html.${fn.tagBuilder}`)
    if (fn.receiver !== 'FlowContent') imports.add(`kotlinx.html.${fn.receiver}`)
  }

  if (componentConfig.role) imports.add('kotlinx.html.role')
  if (componentConfig.inputType) imports.add('kotlinx.html.InputType')
  for (const extra of componentConfig.extras) {
    for (const imported of extra.imports ?? []) imports.add(imported)
  }

  // Plain collation. The kdaisyui package (io.github.ollin.kdaisyui) already sorts
  // ahead of kotlin/kotlinx, so no special-casing is needed to group it first.
  return [...imports].sort((a, b) => a.localeCompare(b))
}

/**
 * Emit one component's Kotlin file.
 *
 * The second parameter is the `ComponentSource` the shape already models: where the component
 * was read from, and which element it renders. It used to be an ad-hoc
 * `Pick<ElementRule, 'primaryElement'>` carrying only the element, which is why the
 * attribution had to invent a directory name and invented a wrong one.
 *
 * `config` is deliberately left to inference: it is the whole of `codegen-config.json`,
 * a large object with per-component sections, and modelling it properly is its own piece
 * of work rather than a side effect of a rename.
 */
export function generateKotlinFile(
  classified: ClassifiedComponent,
  source: ComponentSource,
  config,
) {
  const shape = buildComponentShape(classified, source, config)
  const componentConfig = readComponentConfig(config, classified.componentName)

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
  const functions = shape.functions.map(fn => renderFunction(fn, renderBody(fn, classified, componentConfig)))
  const body = [enums, ...functions].filter(Boolean).join('\n\n')

  return `${header}\n\n${body}\n`
}
