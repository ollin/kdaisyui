/**
 * Which component calls mark themselves as join items, derived from DaisyUI's markup.
 *
 * `.join` writes four corner-radius variables onto its direct children and `.join-item` reads
 * them. Without an enclosing `.join` those variables are unset and the class strips the
 * element's own corners — so `join-item` outside a join is HARMFUL rather than inert, and a
 * parameter a caller could write anywhere would let them do that silently. The class is
 * therefore not a parameter at all: inside `daisyJoin { }` the CALL is the marking, through
 * member overloads on a scope type.
 *
 * A member needs a function to overload, and that is what decides the set. DaisyUI shows
 * `join-item` on 72 elements and every one of them also carries a component class — `btn`,
 * `input`, `select`, `card`, `collapse`, `theme-controller`, `validator — so every one has a
 * generated function already. (Measured 2026-09-19 at v5.7.17. The six exceptions this
 * library carries for classes on bare child tags are the opposite shape: 18 sightings, none
 * beside a component class, so none of them can have a member.)
 *
 * The set is DERIVED and not configured, so a DaisyUI release that documents `join-item` on a
 * new component extends the scope by regeneration. A hand-read list cannot reach the output —
 * and the hand-read list this change started from was wrong by two components.
 */

import { documentedElementClasses } from './parser/documented-classes.ts'
import { fencedHtmlFor } from './parser/documented-element.ts'
import type { ComponentName } from './parser/frontmatter.ts'
import type { ComponentShape, CssClass, FunctionShape, ParameterShape } from './component-shape.ts'

/** The class a join's children wear, and the whole reason this scope exists. */
export const JOIN_ITEM_CLASS = 'join-item' as CssClass

/**
 * Every unprefixed daisyUI class DaisyUI shows on an element that ALSO carries `join-item`.
 *
 * The unit is the element, not the example block: a join whose children are a button and a
 * select documents two separate items, and reading the block whole would make each child's
 * class a companion of the other's.
 *
 * A class a Tailwind variant applies conditionally is not a companion. `lg:$$btn` is the
 * button class above `lg` and nothing below it, while `daisyButton` always emits `btn` — so a
 * member derived from that element would mark something DaisyUI does not document as a join
 * item at most widths.
 */
export function classesJoinedWith(html: string): ReadonlySet<string> {
  const companions = new Set<string>()
  for (const element of documentedElementClasses(html, JOIN_ITEM_CLASS)) {
    for (const documented of element) {
      if (documented.isPrefixed) continue
      if (documented.className === JOIN_ITEM_CLASS) continue
      companions.add(documented.className)
    }
  }
  return companions
}

/**
 * `classesJoinedWith` over every component page, because DaisyUI documents `join-item` on
 * eight of them and only 17 of its 72 sightings are on the join's own page.
 */
export function documentedJoinItemCompanions(componentDirs: readonly ComponentName[]): ReadonlySet<string> {
  const companions = new Set<string>()
  for (const componentDir of componentDirs) {
    for (const companion of classesJoinedWith(fencedHtmlFor(componentDir))) companions.add(companion)
  }
  return companions
}

/** The generated Kotlin class the join's `content` lambda runs in. */
const JOIN_SCOPE_NAME = 'JoinScope'

/**
 * Whether this component is the one that DECLARES `join-item`, and so owns the scope.
 *
 * Derived rather than a hardcoded `'join'`: DaisyUI lists `join-item` as the join's second
 * `component:` class, and that entry is the only statement anywhere that the two belong
 * together. It currently reaches no generated function at all — which is precisely the gap the
 * scope fills.
 */
export function declaresJoinItem(frontmatter): boolean {
  return Object.values(frontmatter?.classnames ?? {}).some(
    entries => Array.isArray(entries) && entries.some(entry => entry?.class === JOIN_ITEM_CLASS),
  )
}

/**
 * The join component, with its content lambda moved onto the scope.
 *
 * Only the RECEIVER of `content` changes. `JoinScope` extends the `<div>` the join already
 * rendered, so `attrs` still reaches a `DIV`, every kotlinx.html builder still works inside the
 * lambda, and nothing a caller could write before stops working — except writing `join-item`
 * by hand, which was never a typed option.
 */
export function withJoinScope(
  shape: ComponentShape,
  members: readonly FunctionShape[],
  imports: readonly string[] = [],
): ComponentShape {
  const [main, ...rest] = shape.functions
  return {
    ...shape,
    scope: { name: JOIN_SCOPE_NAME, markerClass: JOIN_ITEM_CLASS, members, imports },
    functions: [inScope(main), ...rest],
  }
}

function inScope(fn: FunctionShape): FunctionShape {
  return {
    ...fn,
    contentScope: JOIN_SCOPE_NAME,
    parameters: fn.parameters.map(parameter =>
      parameter.name === 'content' ? received(parameter) : parameter,
    ),
  }
}

/** The same lambda parameter, on the scope — nullability preserved, since only the type moves. */
function received(content: ParameterShape): ParameterShape {
  const optional = content.type.endsWith('?') ? '?' : ''
  return { ...content, type: `(${JOIN_SCOPE_NAME}.() -> Unit)${optional}` }
}

/**
 * The scope's members: the main function of every component whose class is a companion.
 *
 * The MAIN function only. `join-item` is documented beside `card`, never beside `card-body`,
 * and a part renders an element DaisyUI shows INSIDE a join item rather than as one.
 *
 * A companion class no generated component owns is dropped rather than reported. It is the
 * accepted limit named in the spec — an element this library generates no function for stays
 * on `extraClasses`, because there is nothing to overload.
 *
 * Ordered by name, so the only thing that moves the generated output is a rename or a change
 * in what DaisyUI documents.
 */
export function joinScopeMembers(
  shapes: readonly ComponentShape[],
  companionClasses: ReadonlySet<string>,
): readonly FunctionShape[] {
  return shapes
    .filter(shape => isJoinItemComponent(shape, companionClasses))
    .flatMap(shape => shape.functions.filter(fn => fn.kind === 'main'))
    .sort((left, right) => left.name.localeCompare(right.name))
}

/**
 * Whether this component's own class shares an element with `join-item` in DaisyUI's markup.
 *
 * Exported because the scope needs two different things from the same set of components — the
 * functions to mirror, and the imports those functions' signatures name. A `type: ButtonType`
 * parameter copied into the join's file does not compile without `kotlinx.html.ButtonType`, and
 * the join has no reason of its own to import it.
 */
export function isJoinItemComponent(shape: ComponentShape, companionClasses: ReadonlySet<string>): boolean {
  return shape.prefix !== null && companionClasses.has(shape.prefix)
}
