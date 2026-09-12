/**
 * What a component's generated API LOOKS LIKE, independent of the language that states it.
 *
 * `generator-new.ts` owned this knowledge implicitly: the parameter list existed only as
 * already-rendered Kotlin strings, built inline in three near-identical places. That was fine
 * while Kotlin was the only output. It stops being fine the moment a second emitter needs the
 * same facts — `docs/reference/*.md` documents exactly these signatures, and the alternative to
 * sharing them is a second copy that drifts, which is the defect that whole change exists to
 * remove.
 *
 * So this module answers "what functions does this component have, with what parameters, on what
 * element", and the emitters answer "how does Kotlin / Markdown say that".
 *
 * It deliberately does NOT model function BODIES. The Markdown pages document signatures, so a
 * body shape would have one consumer and would drag `extras[].apply`, roles and input types in
 * with it. `generator-new.ts` keeps that, and keeps reading `classified` and `config` for it.
 */

import { toPascalCase, toCamelCase, type ClassifiedComponent } from './classifier.ts'

/**
 * A kotlinx.html tag CLASS, e.g. `DIV`. Also the lambda receiver type in `attrs` and `content`.
 *
 * Branded for the same reason `frontmatter.ts` brands `ComponentName`: the mistake produces a
 * plausible value rather than an error. Passing a builder name where a class is wanted emits
 * `(div.() -> Unit)`, which reads almost right and does not compile — and branding is what makes
 * that a call-site error instead of a regeneration surprise.
 */
export type TagClass = string & { readonly __brand: 'TagClass' }

/** The kotlinx.html BUILDER for a tag class, e.g. `div` for `DIV`. */
export type TagBuilder = string & { readonly __brand: 'TagBuilder' }

/**
 * The actual HTML element name, e.g. `fieldset`.
 *
 * A third name rather than a second, because for three tags the builder is not the element:
 * kotlinx.html spells them `fieldSet`, `textArea` and `input`. Prose aimed at a reader has to say
 * `<fieldset>`, which is HTML, and not `<fieldSet>`, which is not an element at all — the
 * hand-written reference pages get this right and the generated Kotlin doc comments do not.
 */
export type HtmlTagName = string & { readonly __brand: 'HtmlTagName' }

/** A CSS class name, e.g. `card` or `card-title`. */
export type CssClass = string & { readonly __brand: 'CssClass' }

/**
 * The classifier hands out plain strings. This is the one place they become branded, so the
 * boundary is visible rather than scattered over every call site.
 */
function asTagClass(element: string): TagClass {
  return element.toUpperCase() as TagClass
}

function asCssClass(className: string): CssClass {
  return className as CssClass
}

const KOTLIN_KEYWORDS = new Set(['object', 'class', 'fun', 'val', 'var', 'if', 'else', 'when', 'for', 'while', 'return', 'true', 'false', 'null'])

export function escapeKotlinKeyword(name: string): string {
  return KOTLIN_KEYWORDS.has(name) ? `_${name}` : name
}

/** kotlinx.html names three builders differently from their tag class. */
const TAG_BUILDER_EXCEPTIONS: Record<string, string> = {
  FIELDSET: 'fieldSet',
  INPUT: 'input',
  TEXTAREA: 'textArea',
}

export function tagBuilderFor(element: TagClass): TagBuilder {
  return (TAG_BUILDER_EXCEPTIONS[element] ?? element.toLowerCase()) as TagBuilder
}

export function htmlTagNameFor(element: TagClass): HtmlTagName {
  return element.toLowerCase() as HtmlTagName
}

/**
 * The HTML specification's void elements: they cannot have children, at all.
 *
 * A closed set of fourteen, unchanged since HTML5, and the reason this is a rule rather than a
 * config section. Whether a component may take content is not a DaisyUI fact and never was —
 * `codegen-config.json` used to restate it as a hand-maintained list of component names, which
 * failed in both possible directions: two entries were written in a spelling the lookup never
 * used, and `mask` was simply never added.
 */
const VOID_ELEMENTS: ReadonlySet<string> = new Set([
  'AREA', 'BASE', 'BR', 'COL', 'EMBED', 'HR', 'IMG',
  'INPUT', 'LINK', 'META', 'PARAM', 'SOURCE', 'TRACK', 'WBR',
])

export function isVoidElement(element: TagClass): boolean {
  return VOID_ELEMENTS.has(element)
}

/** Renders static attributes as they read inside a `Renders <tag ...>` clause. */
export function staticAttributeDoc(entries: readonly StaticAttribute[]): string {
  return entries
    .map(([name, value]) => (value === '' ? ` ${name}` : ` ${name}="${value}"`))
    .join('')
}

/** One attribute a generated function always writes, e.g. `popover=""`. */
export type StaticAttribute = readonly [name: string, value: string]

/** An `extras` entry: a hand-written parameter with the Kotlin fragment that applies it. */
export interface ExtraParameter {
  readonly name: string
  readonly type: string
  readonly default: string
  /** Kotlin statements to splice into the function body. */
  readonly apply: string
  /** `before_classes` to run ahead of `addClassNames`; anything else runs after. */
  readonly position?: string
  /** Imports the `type` or the `apply` fragment needs. */
  readonly imports?: readonly string[]
}

/** A `customParts` entry: an extra generated function beside the component's own. */
export interface CustomPart {
  /** Suffix appended to `daisy<Component>`, e.g. `Popover`. */
  readonly name: string
  readonly element: string
  readonly cssClass?: string
  readonly receiver?: string
  readonly staticAttributes?: Record<string, string>
}

/**
 * One component's slice of `codegen-config.json`, with every knob named once.
 *
 * `AGENTS.md` records that nothing type-checks `codegen/`, and the config was the sharpest edge
 * of that: eight sections addressed by string literals from two files, each a typo away from
 * silently reading `undefined` and falling through to a default. `subComponentElements` sat in
 * the file unread for months for exactly that class of reason. Reading it once, here, is both the
 * documentation of the format and the end of those literals.
 */
export interface ComponentConfig {
  readonly extras: readonly ExtraParameter[]
  readonly customParts: readonly CustomPart[]
  /** Whether the component takes a `text` shortcut for inline content. */
  readonly hasTextParam: boolean
  /** Whether the component refuses children. */
  readonly noContent: boolean
  readonly role: string | null
  /** A fixed `InputType`, for components that are always one kind of `<input>`. */
  readonly inputType: string | null
  readonly componentAttributes: readonly StaticAttribute[]
  /** Booleans no class category declares, e.g. a modifier DaisyUI documents only in prose. */
  readonly additionalBooleans: readonly string[]
}

function section(config, name: string, componentName: string, fallback) {
  return config?.[name]?.[componentName.toLowerCase()] ?? fallback
}

function listed(config, name: string, componentName: string): boolean {
  return config?.[name]?.includes(componentName.toLowerCase()) ?? false
}

/** Reads the whole of one component's configuration, so no caller needs a section literal. */
export function readComponentConfig(config, componentName: string): ComponentConfig {
  return {
    extras: section(config, 'extras', componentName, []),
    customParts: section(config, 'customParts', componentName, []),
    hasTextParam: listed(config, 'textParams', componentName),
    noContent: listed(config, 'noContent', componentName),
    role: section(config, 'roles', componentName, null),
    inputType: section(config, 'inputTypes', componentName, null),
    componentAttributes: Object.entries(section(config, 'componentAttributes', componentName, {})),
    additionalBooleans: section(config, 'additionalBooleans', componentName, []),
  }
}

/**
 * The element a sub-component part renders as. `subComponentElements` wins over the heuristic
 * below, which guesses from the part's name and cannot know when the choice is load-bearing —
 * `megamenu-active` must be a `<span>` because the panels beside it are selected by
 * `:nth-of-type`, and no amount of reading its name says so.
 *
 * Keyed by the part's own class name rather than the component's, so it is NOT part of
 * `ComponentConfig`.
 */
export function partElementFor(partClass: CssClass, config): TagClass {
  return asTagClass(config?.subComponentElements?.[partClass] ?? inferPartElement(partClass))
}

/** Parts whose element cannot be guessed from a fragment of their name. */
const PART_ELEMENT_BY_NAME: Record<string, string> = {
  'stat-title': 'DIV',
  'card-title': 'H2',
}

/**
 * Ordered, because the first match wins and several names match more than one fragment.
 *
 * Most entries name the default. They are kept rather than dropped because order is behaviour:
 * a future `drawer-side-overlay` matches `side` before `overlay`, so removing the redundant
 * `side` row would quietly turn it into a `<label>`.
 */
const PART_ELEMENT_BY_FRAGMENT: readonly (readonly [fragment: string, element: string])[] = [
  ['title', 'H2'],
  ['actions', 'DIV'],
  ['body', 'DIV'],
  ['header', 'DIV'],
  ['footer', 'DIV'],
  ['side', 'DIV'],
  ['overlay', 'LABEL'],
  ['content', 'DIV'],
]

function inferPartElement(partClass: CssClass): string {
  const byName = PART_ELEMENT_BY_NAME[partClass]
  if (byName) return byName
  const byFragment = PART_ELEMENT_BY_FRAGMENT.find(([fragment]) => partClass.includes(fragment))
  return byFragment?.[1] ?? 'DIV'
}

/**
 * Every boolean parameter the main function takes, in the order it declares them.
 *
 * Five class categories collapse into one sorted list, minus anything an `extras` entry already
 * covers under the same camelCase name.
 */
export function booleanParameterClasses(
  classified: ClassifiedComponent,
  componentConfig: ComponentConfig,
): string[] {
  const covered = new Set(componentConfig.extras.map(e => e.name))
  const booleans: string[] = []

  for (const group of [
    classified.styles,
    classified.modifiers,
    classified.behaviors,
    classified.directions,
    classified.placements,
  ]) {
    for (const cls of group) {
      if (!covered.has(toCamelCase(cls))) booleans.push(cls)
    }
  }

  for (const cls of componentConfig.additionalBooleans) {
    if (!booleans.includes(cls) && !covered.has(toCamelCase(cls))) booleans.push(cls)
  }

  return booleans.sort()
}

/** One parameter of one generated function. */
export interface ParameterShape {
  readonly name: string
  /** Kotlin type as written, e.g. `CardSize?` or `(DIV.() -> Unit)`. */
  readonly type: string
  /** Default expression, or null for a required parameter. */
  readonly default: string | null
  /**
   * Human text describing the parameter — the DaisyUI class description for a boolean, a fixed
   * sentence for the escape hatches. Null where the generator has nothing to say, which is the
   * case for every `extras` entry and for a boolean whose class carries no description.
   */
  readonly doc: string | null
}

/**
 * Which of the three generated function kinds this is.
 *
 * Not cosmetic: the main function's doc comment lists every parameter and the other two list
 * none, and only the main function carries the component's enums. Both emitters need to know
 * which they are looking at, so the shape says it rather than each of them re-deriving it.
 */
export type FunctionKind = 'main' | 'part' | 'custom'

/** One generated Kotlin function. */
export interface FunctionShape {
  readonly kind: FunctionKind
  /** e.g. `daisyCardTitle`. */
  readonly name: string
  /** Extension receiver, e.g. `FlowContent`. */
  readonly receiver: string
  /** The tag class, which is also the lambda receiver type in `attrs` and `content`. */
  readonly element: TagClass
  /** What the generated Kotlin calls to open the tag. */
  readonly tagBuilder: TagBuilder
  /** What prose should call the element. Differs from `tagBuilder` for exactly three tags. */
  readonly htmlTag: HtmlTagName
  /** The CSS class this function puts on the element; null for a structural wrapper. */
  readonly cssClass: CssClass | null
  readonly staticAttributes: readonly StaticAttribute[]
  /** Prose sentence preceding the `Renders ...` clause. Empty when there is none. */
  readonly desc: string
  readonly parameters: readonly ParameterShape[]
}

/** One generated enum, e.g. `CardSize`. */
export interface EnumShape {
  readonly name: string
  readonly prefix: string
  /** `Color variants` or `Size variants`, as the class-level doc comment words it. */
  readonly categoryLabel: string
  /**
   * Whether the enum carries a class-level doc comment.
   *
   * Faithful to what `generator-new.ts` did: the condition is that the COMPONENT has any class
   * descriptions at all, not that this enum's entries do. Preserved rather than corrected — a
   * refactoring that changes output is not a refactoring.
   */
  readonly documented: boolean
  readonly entries: readonly {
    readonly name: string
    readonly cssClass: CssClass
    readonly desc: string | null
  }[]
}

/** Where a component came from, which the classified model does not carry. */
export interface ComponentSource {
  /**
   * DaisyUI's own directory name, e.g. `file-input`.
   *
   * Not derivable from `classified.componentName`: lower-casing `FileInput` yields `fileinput`,
   * and `components/fileinput/` does not exist. `generator-new.ts` does exactly that today and
   * so emits a `// Source:` line pointing at nothing — preserved there for now, but the reason
   * this field is passed in rather than computed.
   */
  readonly componentDir: string
  /**
   * The component's root element, already resolved by the caller — the `componentElements`
   * override or the `llms.txt` heuristic. Upper-cased here, so either case works in config.
   */
  readonly element: string | undefined
}

/** A component's whole generated API. */
export interface ComponentShape {
  /** PascalCase name, e.g. `FileInput`. */
  readonly componentName: string
  /** DaisyUI's directory name, e.g. `file-input`. */
  readonly componentDir: string
  /** The component's own CSS class, e.g. `card`. */
  readonly prefix: CssClass | null
  readonly enums: readonly EnumShape[]
  /** The main function first, then parts in declaration order, then custom parts. */
  readonly functions: readonly FunctionShape[]
}

const ID_PARAMETER: ParameterShape = {
  name: 'id',
  type: 'HtmlId?',
  default: 'null',
  doc: 'Type-safe HTML id attribute from [HtmlId] hierarchy',
}

const TEXT_PARAMETER: ParameterShape = {
  name: 'text',
  type: 'String?',
  default: 'null',
  doc: 'Shortcut for inline text content (mutually exclusive with [content])',
}

const EXTRA_CLASSES_PARAMETER: ParameterShape = {
  name: 'extraClasses',
  type: 'String?',
  default: 'null',
  doc: 'Additional CSS classes appended after the generated ones',
}

function attrsParameter(element: TagClass): ParameterShape {
  return {
    name: 'attrs',
    type: `(${element}.() -> Unit)?`,
    default: 'null',
    doc: 'Direct access to the underlying kotlinx.html tag attributes',
  }
}

/** Optional when a `text` parameter competes with it, required otherwise. */
function contentParameter(element: TagClass, hasTextParam: boolean): ParameterShape {
  if (hasTextParam) {
    return {
      name: 'content',
      type: `(${element}.() -> Unit)?`,
      default: 'null',
      doc: 'Nested HTML content (takes precedence over [text] if both are set)',
    }
  }
  return { name: 'content', type: `(${element}.() -> Unit)`, default: null, doc: 'Nested HTML content' }
}

/** The four parameters every part and custom part shares, in declaration order. */
function escapeHatchParameters(element: TagClass, hasTextParam: boolean): ParameterShape[] {
  return [
    ...(hasTextParam ? [TEXT_PARAMETER] : []),
    ID_PARAMETER,
    EXTRA_CLASSES_PARAMETER,
    attrsParameter(element),
    contentParameter(element, hasTextParam),
  ]
}

function enumShapes(classified: ClassifiedComponent): EnumShape[] {
  const documented = Object.keys(classified.descs ?? {}).length > 0

  const build = (suffix: string, categoryLabel: string, values: readonly string[]): EnumShape | null =>
    values.length === 0
      ? null
      : {
          name: `${classified.componentName}${suffix}`,
          prefix: classified.prefix ?? '',
          categoryLabel,
          documented,
          entries: values.map(value => ({
            name: toPascalCase(value),
            cssClass: asCssClass(`${classified.prefix}-${value}`),
            desc: classified.descs?.[value] ?? null,
          })),
        }

  return [
    build('Variant', 'Color variants', classified.colors),
    build('Size', 'Size variants', classified.sizes),
  ].filter((shape): shape is EnumShape => shape !== null)
}

/** The enum-typed parameters, one per populated class category. */
function enumParameters(classified: ClassifiedComponent): ParameterShape[] {
  const parameters: ParameterShape[] = []
  if (classified.colors.length > 0) {
    parameters.push({ name: 'variant', type: `${classified.componentName}Variant?`, default: 'null', doc: 'Color variant' })
  }
  if (classified.sizes.length > 0) {
    parameters.push({ name: 'size', type: `${classified.componentName}Size?`, default: 'null', doc: 'Size variant' })
  }
  return parameters
}

function booleanParameters(
  classified: ClassifiedComponent,
  componentConfig: ComponentConfig,
): ParameterShape[] {
  return booleanParameterClasses(classified, componentConfig).map(cls => ({
    name: escapeKotlinKeyword(toCamelCase(cls)),
    type: 'Boolean',
    default: 'false',
    doc: classified.descs?.[cls] ?? null,
  }))
}

/**
 * `extras` entries carry no description: they are hand-written config fragments, and the config
 * has nowhere to put one. Their doc line is the bare `@param name`.
 */
function extraParameters(extras: readonly ExtraParameter[]): ParameterShape[] {
  return extras.map(extra => ({
    name: extra.name,
    type: extra.type,
    default: extra.default,
    doc: null,
  }))
}

function mainFunctionShape(
  classified: ClassifiedComponent,
  element: TagClass,
  componentConfig: ComponentConfig,
): FunctionShape {
  const { hasTextParam } = componentConfig

  // Declaration order, which the generated signatures and their doc comments both follow.
  const parameters: ParameterShape[] = [
    ...(hasTextParam ? [TEXT_PARAMETER] : []),
    ID_PARAMETER,
    ...enumParameters(classified),
    ...booleanParameters(classified, componentConfig),
    ...extraParameters(componentConfig.extras),
    EXTRA_CLASSES_PARAMETER,
    attrsParameter(element),
    // Derived from the element, not configured: an element the HTML specification calls void
    // cannot hold children, so offering a lambda that writes some is offering a lie.
    ...(isVoidElement(element) ? [] : [contentParameter(element, hasTextParam)]),
  ]

  return {
    kind: 'main',
    name: `daisy${classified.componentName}`,
    receiver: 'FlowContent',
    element,
    tagBuilder: tagBuilderFor(element),
    htmlTag: htmlTagNameFor(element),
    cssClass: classified.prefix === null ? null : asCssClass(classified.prefix),
    staticAttributes: componentConfig.componentAttributes,
    desc: classified.desc ?? '',
    parameters,
  }
}

function partFunctionShape(
  classified: ClassifiedComponent,
  partClass: CssClass,
  config,
): FunctionShape {
  const element = partElementFor(partClass, config)
  const hasTextParam = config?.textParams?.includes(partClass) || partClass.includes('title')
  const suffix = toPascalCase(stripPrefix(classified.prefix, partClass))

  return {
    kind: 'part',
    name: `daisy${classified.componentName}${suffix}`,
    receiver: 'FlowContent',
    element,
    tagBuilder: tagBuilderFor(element),
    htmlTag: htmlTagNameFor(element),
    cssClass: partClass,
    staticAttributes: [],
    // Always empty in practice, and deliberately left so. `descs` is keyed by the class name
    // with the component prefix STRIPPED (`title`), while `parts` holds it unstripped
    // (`card-title`), so this lookup has never resolved. Correcting it would add a sentence to
    // every part's doc comment — a change in output, which belongs in its own commit.
    desc: classified.descs?.[partClass] ?? '',
    parameters: escapeHatchParameters(element, hasTextParam),
  }
}

function stripPrefix(prefix: string | null, className: CssClass): string {
  return prefix && className.startsWith(`${prefix}-`) ? className.slice(prefix.length + 1) : className
}

function customPartFunctionShape(classified: ClassifiedComponent, part: CustomPart): FunctionShape {
  const element = asTagClass(part.element)

  return {
    kind: 'custom',
    name: `daisy${classified.componentName}${part.name}`,
    receiver: part.receiver || 'FlowContent',
    element,
    tagBuilder: tagBuilderFor(element),
    htmlTag: htmlTagNameFor(element),
    cssClass: part.cssClass === undefined ? null : asCssClass(part.cssClass),
    staticAttributes: Object.entries(part.staticAttributes ?? {}),
    desc: '',
    parameters: escapeHatchParameters(element, false),
  }
}

/** Everything both emitters need about one component, derived from the classified model. */
export function buildComponentShape(
  classified: ClassifiedComponent,
  source: ComponentSource,
  config,
): ComponentShape {
  const componentConfig = readComponentConfig(config, classified.componentName)
  const rootElement = asTagClass(source.element || 'DIV')

  return {
    componentName: classified.componentName,
    componentDir: source.componentDir,
    prefix: classified.prefix === null ? null : asCssClass(classified.prefix),
    enums: enumShapes(classified),
    functions: [
      mainFunctionShape(classified, rootElement, componentConfig),
      ...classified.parts.map(partClass => partFunctionShape(classified, asCssClass(partClass), config)),
      ...componentConfig.customParts.map(part => customPartFunctionShape(classified, part)),
    ],
  }
}
