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
import type { GroupClassification } from './class-groups.ts'
import { usualElementOf } from './parser/documented-element.ts'

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

/**
 * Elements kotlinx.html lets a `FlowContent` receiver open. The rest — `legend` only inside
 * `FIELDSET`, `li` only inside `UL`/`OL`, `option`, `tr`, `td`, … — need their parent as the
 * extension receiver, which a part does not know yet. Until the parent is read from DaisyUI's
 * markup too, a part documented on one of those keeps its heuristic element and its exception,
 * rather than generating a function that does not compile.
 */
const FLOW_CONTENT_CHILDREN: ReadonlySet<string> = new Set([
  'A', 'ABBR', 'ADDRESS', 'ARTICLE', 'ASIDE', 'AUDIO', 'B', 'BDI', 'BDO', 'BLOCKQUOTE', 'BR',
  'BUTTON', 'CANVAS', 'CITE', 'CODE', 'DATA', 'DATALIST', 'DEL', 'DETAILS', 'DFN', 'DIALOG',
  'DIV', 'DL', 'EM', 'EMBED', 'FIELDSET', 'FIGURE', 'FOOTER', 'FORM', 'H1', 'H2', 'H3', 'H4',
  'H5', 'H6', 'HEADER', 'HR', 'I', 'IFRAME', 'IMG', 'INPUT', 'INS', 'KBD', 'LABEL', 'MAIN',
  'MAP', 'MARK', 'METER', 'NAV', 'OBJECT', 'OL', 'OUTPUT', 'P', 'PRE', 'PROGRESS', 'Q', 'RUBY',
  'S', 'SAMP', 'SECTION', 'SELECT', 'SMALL', 'SPAN', 'STRONG', 'SUB', 'SUP', 'TABLE',
  'TEXTAREA', 'TIME', 'U', 'UL', 'VAR', 'VIDEO', 'WBR',
])

export function isFlowContentChild(element: string): boolean {
  return FLOW_CONTENT_CHILDREN.has(element)
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

/** The element a part renders and the receiver its function extends. */
interface PartPlacement {
  readonly element: TagClass
  readonly receiver: string
}

/**
 * The element a part renders: what DaisyUI shows it on when that is one element, otherwise
 * `partElementFor`'s configured-or-guessed answer.
 *
 * The documented element wins over the name heuristic because the heuristic is what put
 * `hero-overlay` on a <label> and `footer-title` on an <h2>. It does not win over
 * `subComponentElements`, whose entries exist because a documented example was NOT enough —
 * `megamenu-active` must be a <span> for `:nth-of-type` reasons the markup does not state.
 *
 * An element kotlinx.html opens only inside its parent — `<legend>`, `<li>` — is taken with
 * the documented parent as receiver, so `daisyFieldsetLegend` extends `FIELDSET`. Where no
 * parent is documented, the heuristic stays: a function that does not compile helps nobody.
 */
function partPlacementByDocs(partClass: CssClass, plan: ClassPlan, config): PartPlacement {
  const configured = config?.subComponentElements?.[partClass]
  if (configured !== undefined) return { element: asTagClass(configured), receiver: 'FlowContent' }
  const shown = usualElementOf(plan.documentedElements?.get(partClass))
  const placed = shown === undefined ? undefined : placeDocumentedElement(shown, plan.documentedParents?.get(partClass))
  return placed ?? { element: partElementFor(partClass, config), receiver: 'FlowContent' }
}

/** Where a documented element can be opened from — or nowhere, when only an unknown parent can. */
function placeDocumentedElement(shown: string, parent: string | undefined): PartPlacement | undefined {
  if (isFlowContentChild(shown)) return { element: asTagClass(shown), receiver: 'FlowContent' }
  if (parent === undefined) return undefined
  return { element: asTagClass(shown), receiver: asTagClass(parent) }
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
/**
 * The classification of a component whose groups nobody measured: nothing is a choice, every
 * class is a flag.
 *
 * Exactly the behaviour that preceded `class-groups.ts`, named so it can be a default. A
 * caller that forgets to pass a real classification therefore produces the OLD output rather
 * than a broken one — and the drift check notices, because the old output is not the committed
 * output once the config lands.
 */
export function allBooleans(classified: ClassifiedComponent): GroupClassification {
  return {
    enums: [],
    booleans: [
      ...classified.styles,
      ...classified.modifiers,
      ...classified.behaviors,
      ...classified.directions,
      ...classified.placements,
    ],
  }
}

export function booleanParameterClasses(
  classified: ClassifiedComponent,
  componentConfig: ComponentConfig,
  groups: GroupClassification = allBooleans(classified),
): string[] {
  const covered = new Set(componentConfig.extras.map(e => e.name))
  const booleans: string[] = []

  // Only the classes the measurement left as flags. Everything else is now an enum constant,
  // and a class appearing in both would be settable two ways at once.
  for (const cls of groups.booleans) {
    if (!covered.has(toCamelCase(cls))) booleans.push(cls)
  }

  for (const cls of componentConfig.additionalBooleans) {
    if (!booleans.includes(cls) && !covered.has(toCamelCase(cls))) booleans.push(cls)
  }

  return booleans.sort()
}

/**
 * The parameter a boolean class arrives as — its camelCase name, and nothing else.
 *
 * `rating-hidden` is `hidden`, however it reads: the class is what a reader coming from
 * DaisyUI's documentation searches for, and DaisyUI's own description of it is the KDoc.
 * Shared with the Kotlin body emitter, which has to write the same identifier it declared.
 */
export function booleanParameterName(cls: string): string {
  return escapeKotlinKeyword(toCamelCase(cls))
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
  /**
   * The DaisyUI class a boolean puts on the element, e.g. `menu-active`; absent for every other
   * parameter. What the element cross-check reads to ask whether this function's element is the
   * one DaisyUI documents the class on.
   */
  readonly cssClass?: CssClass
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
  /**
   * How often DaisyUI shows each of the component's classes on each element, by class name,
   * from `documentedElementTalliesFor`. Decides which FUNCTION a class's parameter is declared
   * on — a class usually shown on a part's element belongs to that part — and which element a
   * part renders. Absent means "everything on the main function, elements by heuristic", which
   * is what a hand-built fixture wants.
   */
  readonly documentedElements?: ReadonlyMap<string, ReadonlyMap<string, number>>
  /**
   * The parent DaisyUI shows each class under, by class name, from `documentedParentsFor`.
   * A part whose element kotlinx.html opens only inside its parent takes that parent as its
   * extension receiver.
   */
  readonly documentedParents?: ReadonlyMap<string, string>
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

/** `verticalPlacement` back to `VerticalPlacement` — the suffix the config actually wrote. */
function capitalised(name: string): string {
  return name.charAt(0).toUpperCase() + name.slice(1)
}

function enumShapes(classified: ClassifiedComponent, groups: GroupClassification): EnumShape[] {
  const documented = Object.keys(classified.descs ?? {}).length > 0

  const build = (name: string, categoryLabel: string, values: readonly string[]): EnumShape | null =>
    values.length === 0
      ? null
      : {
          name,
          prefix: classified.prefix ?? '',
          categoryLabel,
          documented,
          entries: values.map(value => ({
            name: toPascalCase(value),
            cssClass: asCssClass(`${classified.prefix}-${value}`),
            desc: classified.descs?.[value] ?? null,
          })),
        }

  const named = (suffix: string) => `${classified.componentName}${suffix}`

  return [
    build(named('Variant'), 'Color variants', classified.colors),
    build(named('Size'), 'Size variants', classified.sizes),
    // The measured ones. `colors` and `sizes` never needed measuring — a component wears one
    // colour and one size by construction, which is why they were enums from the start.
    // `Style variants`, not `styles variants`: the configured suffix reads as a noun, the
    // frontmatter category it came from is a plural bucket name.
    ...groups.enums.map(group => build(group.enumName, `${capitalised(group.parameterName)} variants`, group.members)),
  ].filter((shape): shape is EnumShape => shape !== null)
}

/**
 * The Kotlin type of a parameter carrying one exclusive group.
 *
 * `ClassValues<ButtonSize>?` rather than `ButtonSize?`, so the one parameter accepts a bare entry,
 * an entry at a Tailwind variant, and any combination of those — `ButtonSize.Lg`,
 * `at(Breakpoint.Lg, ButtonSize.Lg)`, and the `btn-xs sm:btn-sm md:btn-md lg:btn-lg xl:btn-xl`
 * pattern DaisyUI's own button page documents.
 *
 * The enum name stays the type ARGUMENT, and `ClassValues` is invariant in it, so the widening is
 * only in what may be applied and never in which group may answer which parameter.
 */
function groupParameterType(enumName: string): string {
  return `ClassValues<${enumName}>?`
}

/** The enum-typed parameters: the two that were always enums, then the measured ones. */
function enumParameters(
  classified: ClassifiedComponent,
  groups: GroupClassification,
): ParameterShape[] {
  const parameters: ParameterShape[] = []
  if (classified.colors.length > 0) {
    parameters.push({ name: 'variant', type: groupParameterType(`${classified.componentName}Variant`), default: 'null', doc: 'Color variant' })
  }
  if (classified.sizes.length > 0) {
    parameters.push({ name: 'size', type: groupParameterType(`${classified.componentName}Size`), default: 'null', doc: 'Size variant' })
  }
  for (const group of groups.enums) {
    parameters.push({
      name: escapeKotlinKeyword(group.parameterName),
      type: groupParameterType(group.enumName),
      default: 'null',
      doc: `${capitalised(group.parameterName)} variant`,
    })
  }
  return parameters
}

function booleanParameter(classified: ClassifiedComponent, cls: string): ParameterShape {
  return {
    name: booleanParameterName(cls),
    type: 'Boolean',
    default: 'false',
    doc: classified.descs?.[cls] ?? null,
    cssClass: asCssClass(`${classified.prefix}-${cls}`),
  }
}

/**
 * Where every class goes: which are enums, which booleans, which booleans a part owns instead
 * of main — and the documented elements all of that was decided from, which the parts read
 * again to choose their own element.
 */
interface ClassPlan {
  readonly groups: GroupClassification
  readonly placement: ClassPlacement
  readonly documentedElements: ReadonlyMap<string, ReadonlyMap<string, number>> | undefined
  readonly documentedParents: ReadonlyMap<string, string> | undefined
}

function booleanParameters(
  classified: ClassifiedComponent,
  componentConfig: ComponentConfig,
  plan: ClassPlan,
): ParameterShape[] {
  return booleanParameterClasses(classified, componentConfig, plan.groups)
    .filter(cls => plan.placement.belongsToMain(cls))
    .map(cls => booleanParameter(classified, cls))
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

/**
 * Which part function, if any, a boolean class belongs to.
 *
 * A class belongs to the function whose element DaisyUI documents it on. When that element is
 * a part's and not the main function's, the parameter is declared on the part — `dock-active`
 * on `daisyDockItem`'s `<button>`, not on `daisyDock`'s `<div>`. When no part renders the
 * documented element, the class stays on the main function and the element cross-check goes on
 * reporting it: moving it to a part on a DIFFERENT wrong element would fix nothing.
 */
class ClassPlacement {
  /** Class → every part that renders the element DaisyUI shows it on. */
  private readonly owners: ReadonlyMap<string, readonly CssClass[]>

  private constructor(owners: ReadonlyMap<string, readonly CssClass[]>) {
    this.owners = owners
  }

  static none(): ClassPlacement {
    return new ClassPlacement(new Map())
  }

  /**
   * @param booleans the classes the measurement left as flags — an enum member is never moved,
   *   the enum stays on the main function whole
   */
  static from(
    classified: ClassifiedComponent,
    booleans: readonly string[],
    plan: Omit<ClassPlan, 'placement'>,
    config,
  ): ClassPlacement {
    const documented = plan.documentedElements ?? new Map()
    // `timeline-box` is shown on `timeline-start` and `timeline-end` alike, both <div>s; every
    // part rendering that element declares it.
    const partsByElement = new Map<string, CssClass[]>()
    for (const part of classified.parts) {
      const element = partPlacementByDocs(asCssClass(part), plan, config).element
      partsByElement.set(element, [...(partsByElement.get(element) ?? []), asCssClass(part)])
    }
    // Every element DaisyUI shows the COMPONENT on — `dropdown` is a <div> in one example and
    // a <details> in another. A class shown on any of those is on the component, whatever the
    // generator renders it as: `dropdown-close` on the <div>-shaped dropdown is on the
    // dropdown, not on a <div> part that happens to exist.
    const componentElements = new Set((classified.prefix === null ? undefined : documented.get(classified.prefix))?.keys() ?? [])

    const owners = new Map<string, readonly CssClass[]>()
    for (const cls of booleans) {
      const element = usualElementOf(documented.get(`${classified.prefix}-${cls}`))
      if (element === undefined || componentElements.has(element)) continue
      const parts = partsByElement.get(element)
      if (parts !== undefined) owners.set(cls, parts)
    }
    return new ClassPlacement(owners)
  }

  belongsToMain(cls: string): boolean {
    return !this.owners.has(cls)
  }

  classesOf(partClass: CssClass): string[] {
    return [...this.owners].filter(([, parts]) => parts.includes(partClass)).map(([cls]) => cls)
  }
}

function mainFunctionShape(
  classified: ClassifiedComponent,
  element: TagClass,
  componentConfig: ComponentConfig,
  plan: ClassPlan,
): FunctionShape {
  const { hasTextParam } = componentConfig

  // Declaration order, which the generated signatures and their doc comments both follow.
  const parameters: ParameterShape[] = [
    ...(hasTextParam ? [TEXT_PARAMETER] : []),
    ID_PARAMETER,
    ...enumParameters(classified, plan.groups),
    ...booleanParameters(classified, componentConfig, plan),
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
  plan: ClassPlan,
): FunctionShape {
  const { element, receiver } = partPlacementByDocs(partClass, plan, config)
  const hasTextParam = config?.textParams?.includes(partClass) || partClass.includes('title')
  const suffix = toPascalCase(stripPrefix(classified.prefix, partClass))
  const own = plan.placement.classesOf(partClass).sort().map(cls => booleanParameter(classified, cls))

  return {
    kind: 'part',
    name: `daisy${classified.componentName}${suffix}`,
    receiver,
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
    parameters: withOwnClasses(escapeHatchParameters(element, hasTextParam), own),
  }
}

/** A part's own booleans go after `id`, where the main function puts its booleans too. */
function withOwnClasses(escapeHatches: readonly ParameterShape[], own: readonly ParameterShape[]): ParameterShape[] {
  const afterId = escapeHatches.findIndex(parameter => parameter === ID_PARAMETER) + 1
  return [...escapeHatches.slice(0, afterId), ...own, ...escapeHatches.slice(afterId)]
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

/**
 * Everything both emitters need about one component, derived from the classified model.
 *
 * `groups` is the measured decision about which class groups are a choice — see
 * `class-groups.ts`. It is passed in rather than computed here because it needs the whole
 * measurement and the whole `enumNames` config, neither of which is a per-component concern.
 */
export function buildComponentShape(
  classified: ClassifiedComponent,
  source: ComponentSource,
  config,
  groups: GroupClassification = allBooleans(classified),
): ComponentShape {
  const componentConfig = readComponentConfig(config, classified.componentName)
  const rootElement = asTagClass(source.element || 'DIV')
  const evidence = { groups, documentedElements: source.documentedElements, documentedParents: source.documentedParents }
  const placement = source.documentedElements === undefined
    ? ClassPlacement.none()
    : ClassPlacement.from(classified, groups.booleans, evidence, config)
  const plan: ClassPlan = { ...evidence, placement }

  return {
    componentName: classified.componentName,
    componentDir: source.componentDir,
    prefix: classified.prefix === null ? null : asCssClass(classified.prefix),
    enums: enumShapes(classified, groups),
    functions: [
      mainFunctionShape(classified, rootElement, componentConfig, plan),
      ...classified.parts.map(partClass => partFunctionShape(classified, asCssClass(partClass), config, plan)),
      ...componentConfig.customParts.map(part => customPartFunctionShape(classified, part)),
    ],
  }
}
