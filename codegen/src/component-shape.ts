/**
 * What a component's generated API LOOKS LIKE, independent of the language that states it.
 *
 * `model.ts` builds a shape from each committed model file; `generator-new.ts` renders it as
 * Kotlin, `generator-docs.ts` as a reference page, `component-api-dump.ts` as the API baseline.
 * One description of the API, so the three cannot disagree about it.
 *
 * It deliberately does NOT model function BODIES. The Markdown pages document signatures, so a
 * body shape would have one consumer; `generator-new.ts` takes the extras' fragments, the role and
 * the input type from the model beside the shape.
 */


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
 * model field. Whether a component may take content is not a DaisyUI fact and never was —
 * a configuration file used to restate it as a hand-maintained list of component names, which
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
   * parameter. The body emits it when the boolean is true.
   */
  readonly cssClass?: CssClass
  /**
   * The enum this parameter chooses from, where it holds a `ClassValues`, which the Kotlin body
   * passes to `addClassNames` unguarded. Absent for every other parameter.
   */
  readonly enumName?: string
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
  /**
   * Modifier classes this construction ALWAYS writes beside `cssClass`, never optionally.
   *
   * Empty for every function whose modifiers are parameters. A `customParts` entry needs it
   * where DaisyUI documents one markup and that markup carries two classes: `skeleton-text`
   * appears only as `<span class="skeleton skeleton-text">`, so neither class alone describes
   * the function, and a boolean would offer a combination DaisyUI never shows.
   */
  readonly modifierClasses: readonly CssClass[]
  readonly staticAttributes: readonly StaticAttribute[]
  /** Prose sentence preceding the `Renders ...` clause. Empty when there is none. */
  readonly desc: string
  readonly parameters: readonly ParameterShape[]
  /**
   * The lambda receiver of `content`, when it is a generated SCOPE rather than the element.
   *
   * Absent for every function whose children are ordinary content. Present on `daisyJoin`,
   * whose body opens a `JoinScope` instead of a plain `div` so that a component call written
   * directly in the lambda reaches a member that marks it `join-item`.
   */
  readonly contentScope?: string
}

/**
 * A generated class that a component's `content` lambda runs in, carrying member overloads.
 *
 * The one instance is the join's. `.join` writes four corner-radius variables onto its direct
 * children and `.join-item` reads them, so the class is harmful anywhere else — which rules out
 * a parameter, since a parameter is writable wherever its function is. A scope is not: outside
 * the lambda the members do not exist, and `@HtmlTagMarker` hides them inside a nested one.
 */
export interface ScopeShape {
  /** The generated Kotlin class name, e.g. `JoinScope`. */
  readonly name: string
  /** The class every member adds to whatever it renders, e.g. `join-item`. */
  readonly markerClass: CssClass
  /** The functions the scope overloads, each mirroring a top-level one. */
  readonly members: readonly FunctionShape[]
  /**
   * Imports the MEMBERS' signatures need and the host component has no reason to carry.
   *
   * A mirrored parameter list brings its types with it: `daisyButton` declares
   * `type: ButtonType?`, so the join's file needs `kotlinx.html.ButtonType` although nothing
   * the join itself renders mentions one.
   */
  readonly imports: readonly string[]
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
  /**
   * The scope the main function's `content` runs in, when it has one.
   *
   * Attached after the per-component build rather than by `buildComponentShape`, because a
   * scope's members are OTHER components' functions and this shape knows only its own.
   */
  readonly scope?: ScopeShape
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
/**
 * The four parameters every generated function has, plus `text` where the component takes it.
 *
 * `content` is absent for a void element, here rather than at each call site: an element the
 * HTML specification says cannot hold children cannot hold them on a part or a custom part
 * either. The check used to sit on the main function's path alone, so `daisyModalToggle`
 * required a content lambda for the same `<input>` that `daisyInput` correctly refuses one for.
 */
function escapeHatchParameters(element: TagClass, hasTextParam: boolean): ParameterShape[] {
  return [
    ...(hasTextParam ? [TEXT_PARAMETER] : []),
    ID_PARAMETER,
    EXTRA_CLASSES_PARAMETER,
    attrsParameter(element),
    ...(isVoidElement(element) ? [] : [contentParameter(element, hasTextParam)]),
  ]
}

/**
 * Every generated function's parameter list: `text` where it takes one, `id`, the function's own
 * parameters, then the escape hatches. Main functions and parts share the frame; a custom part
 * has no own parameters.
 */
export function functionParameters(element: TagClass, hasTextParam: boolean, own: readonly ParameterShape[]): ParameterShape[] {
  return withOwnClasses(escapeHatchParameters(element, hasTextParam), own)
}

/** A part's own parameters go after `id`, where the main function puts the same two kinds. */
function withOwnClasses(escapeHatches: readonly ParameterShape[], own: readonly ParameterShape[]): ParameterShape[] {
  const afterId = escapeHatches.findIndex(parameter => parameter === ID_PARAMETER) + 1
  return [...escapeHatches.slice(0, afterId), ...own, ...escapeHatches.slice(afterId)]
}
