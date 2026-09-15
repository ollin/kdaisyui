/**
 * The axes of one class group, derived from the measurement rather than declared.
 *
 * An axis is a set of classes of which a caller may wear exactly one, so it is a CLIQUE of
 * `exclusive` verdicts. The derivation:
 *
 *   1. leave the inert members aside — a class that changed nothing against the baseline
 *      reads `exclusive` with everything, which is not membership of anything;
 *   2. take the connected components of the exclusive graph over the rest;
 *   3. put each inert member back into the component it is exclusive with every member of
 *      — a default such as `loading-spinner` rejoins its choice this way;
 *   4. a component of two or more that is a clique is an axis; everything else stays boolean.
 *
 * Step 4 is the not-established rule from `class-groups.ts` applied per component: a
 * component that is connected but not a clique (`button.styles`) is not an inconsistent
 * measurement, it is a group nobody may call a choice.
 *
 * Step 3 has one ambiguous case, `tooltip-top`: exclusive with both cliques of its group
 * because it changes nothing. The tie is broken by what the class DECLARES — the property
 * names the stylesheet sets under it, recorded beside the verdict — and `tooltip-top`
 * declares exactly what `tooltip-bottom` does. An inert member still ambiguous after that
 * fails generation, because it would otherwise land somewhere nobody chose. One exclusive
 * with no component at all — `carousel-horizontal`, whose pair with `vertical` composes —
 * stays boolean by the same rule as any composing class.
 */
import { ClassPair } from './measurement.ts'
import type { MeasuredPairs } from './measurement.ts'

export class AxisDerivationError extends Error {}

/** One derived axis: its members, in DaisyUI's order. */
export type Axis = readonly string[]

export interface DerivedAxes {
  /** Cliques of two or more, ordered by their first member's position in DaisyUI's list. */
  readonly axes: readonly Axis[]
  /** Every other member, in DaisyUI's order. */
  readonly booleans: readonly string[]
}

/** A component under construction: members in DaisyUI's order, membership by set. */
class Component {
  private readonly members: string[] = []

  add(member: string): void {
    this.members.push(member)
  }

  has(member: string): boolean {
    return this.members.includes(member)
  }

  isExclusiveWith(member: string, measured: MeasuredPairs): boolean {
    return this.members.every((own) => measured.isExclusive(new ClassPair(own, member)))
  }

  declaresLike(member: string, measured: MeasuredPairs): boolean {
    const wanted = measured.declaredProperties(member).join(' ')
    return this.members.some((own) => measured.declaredProperties(own).join(' ') === wanted)
  }

  isClique(measured: MeasuredPairs): boolean {
    return measured.describeNonExclusive(this.members).length === 0
  }

  size(): number {
    return this.members.length
  }

  sortedBy(order: readonly string[]): Axis {
    return [...this.members].sort((left, right) => order.indexOf(left) - order.indexOf(right))
  }
}

function connectedComponents(live: readonly string[], measured: MeasuredPairs): Component[] {
  const components: Component[] = []
  const placed = new Set<string>()
  for (const seed of live) {
    if (placed.has(seed)) continue
    components.push(grow(seed, live, measured, placed))
  }
  return components
}

function grow(seed: string, live: readonly string[], measured: MeasuredPairs, placed: Set<string>): Component {
  const component = new Component()
  const frontier = [seed]
  placed.add(seed)
  while (frontier.length > 0) {
    const current = frontier.pop() as string
    component.add(current)
    for (const other of neighbours(current, live, measured, placed)) frontier.push(other)
  }
  return component
}

function neighbours(of: string, live: readonly string[], measured: MeasuredPairs, placed: Set<string>): string[] {
  const found = live.filter((other) => !placed.has(other) && measured.isExclusive(new ClassPair(of, other)))
  for (const other of found) placed.add(other)
  return found
}

function homeFor(member: string, components: readonly Component[], measured: MeasuredPairs, group: string): Component | null {
  const exclusive = components.filter((component) => component.isExclusiveWith(member, measured))
  if (exclusive.length <= 1) return exclusive[0] ?? null

  const declaring = exclusive.filter((component) => component.declaresLike(member, measured))
  if (declaring.length === 1) return declaring[0]

  throw new AxisDerivationError(
    `${group}: "${member}" is inert and exclusive with ${exclusive.length} axes, and what it ` +
      `declares matches ${declaring.length} of them. It cannot be placed by measurement; ` +
      `re-measure, or DaisyUI has changed what the class does.`,
  )
}

/**
 * Every inert member's home is decided against the LIVE components, and only then are they
 * added — deciding one at a time against components already grown by earlier inert members
 * made the result depend on iteration order: under `menu.modifiers` the four invisible
 * classes would have joined `{paged}` or not depending on which came first.
 */
function placeInert(inert: readonly string[], components: readonly Component[], measured: MeasuredPairs, group: string): void {
  const homes = inert.map((member) => [member, homeFor(member, components, measured, group)] as const)
  for (const [member, home] of homes) {
    if (home !== null) home.add(member)
  }
}

/**
 * @param members the group's classes in DaisyUI's order
 * @param group the group's key, for messages
 */
export function deriveAxes(members: readonly string[], measured: MeasuredPairs, group: string): DerivedAxes {
  const inert = members.filter((member) => measured.isInert(member))
  const live = members.filter((member) => !measured.isInert(member))
  const components = connectedComponents(live, measured)
  placeInert(inert, components, measured, group)

  const axes = components
    .filter((component) => component.size() > 1 && component.isClique(measured))
    .map((component) => component.sortedBy(members))
    .sort((left, right) => members.indexOf(left[0]) - members.indexOf(right[0]))
  const inAxis = new Set(axes.flat())
  return { axes, booleans: members.filter((member) => !inAxis.has(member)) }
}
