/**
 * The classes DaisyUI's documented markup puts on ONE element.
 *
 * The generated component tests used to union every `$$` token in a whole example block, which
 * is a model defect rather than a parsing bug: an example block is not one element, and the
 * tokens in it do not all belong to the component. It produced three kinds of false assertion,
 * every one of them asserting markup DaisyUI does not document:
 *
 *     $$menu $$menu-vertical lg:$$menu-horizontal   ->  menu menu-horizontal menu-vertical
 *     three sibling tooltips, top / top+start / top+end  ->  one call with top, start AND end
 *     <div class="$$indicator"><span class="$$indicator-item indicator-start">
 *                                                   ->  the child's placement on the parent
 *
 * The first drops the variant that made the two directions compatible — `menu-vertical` and
 * `lg:menu-horizontal` is ONE class at two breakpoints, which is precisely the pattern this
 * library exists to type. The second and third attribute one element's classes to another.
 *
 * So the unit is the element, and a token keeps the variant it was written with.
 */

import { parseDocument, DomUtils } from 'htmlparser2'

/**
 * What this module needs of a parsed element, and nothing more.
 *
 * Structural rather than `domhandler`'s own `Element`: that package reaches this checkout only
 * as a transitive dependency of `htmlparser2`, and naming it here would make the one dependency
 * `codegen/` deliberately took into two.
 */
interface TaggedNode {
  readonly attribs?: Record<string, string>
}

/** The sentinel DaisyUI's documentation puts in front of its OWN class names. */
const MARKER = '$$'

/**
 * One class token from a documented example, e.g. `lg:$$menu-horizontal`.
 *
 * A token is an ENCODED STRING — a Tailwind variant, a marker and a class name in one — and the
 * thing that made the old extractor wrong was reading it back with a substring match at each of
 * the places that needed one of the three parts. Parsed once, here.
 */
export class DocumentedClass {
  /** The Tailwind variant the class is applied at, e.g. `lg`, or null for an unprefixed class. */
  readonly variant: string | null
  /** The daisyUI class itself, marker stripped, e.g. `menu-horizontal`. */
  readonly className: string

  // Field-then-assign rather than a constructor parameter property: Node STRIPS TypeScript
  // instead of compiling it, and a parameter property is the one annotation that has to emit an
  // assignment. See AGENTS.md.
  constructor(variant: string | null, className: string) {
    this.variant = variant
    this.className = className
  }

  /**
   * The token parsed, or null when it carries no daisyUI marker — a plain Tailwind utility such
   * as `flex`, which this library does not type and must not assert on.
   *
   * A marker that is not at the start and not directly preceded by `:` is refused rather than
   * guessed at. Nothing in DaisyUI's docs writes one, and inventing a reading for it is how an
   * extractor starts asserting things nobody documented.
   */
  static parse(token: string): DocumentedClass | null {
    const marker = token.indexOf(MARKER)
    if (marker < 0) return null
    const className = token.slice(marker + MARKER.length)
    if (className.length === 0) return null
    if (marker === 0) return new DocumentedClass(null, className)
    if (token[marker - 1] !== ':') return null
    return new DocumentedClass(token.slice(0, marker - 1), className)
  }

  /** Whether a Tailwind variant applies the class conditionally. */
  get isPrefixed(): boolean {
    return this.variant !== null
  }

  /** The class as it reaches the page: `menu-horizontal`, or `lg:menu-horizontal`. */
  get rendered(): string {
    return this.variant === null ? this.className : `${this.variant}:${this.className}`
  }
}

/**
 * The class attribute split into tokens.
 *
 * Shared with `documented-element.ts` so one place knows how a class attribute is tokenised.
 */
export function classTokensOf(node: TaggedNode): string[] {
  return (node.attribs?.class ?? '').split(/\s+/).filter(Boolean)
}

/**
 * Every element the documentation gives the component's own class, with the classes it carries.
 *
 * The component class must be UNPREFIXED. An element that carries only `lg:$$tooltip` wears the
 * component class at one breakpoint and not at others, and `daisyTooltip()` always emits it — so
 * a test built from that element would assert output the generator cannot produce.
 */
export function documentedElementClasses(
  html: string,
  componentClass: string,
): DocumentedClass[][] {
  const marker = `${MARKER}${componentClass}`
  return DomUtils.findAll(
    (node: TaggedNode) => classTokensOf(node).includes(marker),
    parseDocument(html).children,
  ).map((element: TaggedNode) => parsedClassesOf(element))
}

function parsedClassesOf(element: TaggedNode): DocumentedClass[] {
  return classTokensOf(element)
    .map(token => DocumentedClass.parse(token))
    .filter((parsed): parsed is DocumentedClass => parsed !== null)
}
