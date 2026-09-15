/**
 * Which of a component's class groups become a Kotlin enum, and which stay boolean.
 *
 * The decision is a MEASUREMENT, not a category rule. `codegen/exclusivity.json` records, for
 * every pair of classes in every multi-member group, what a browser did when both were worn at
 * once against DaisyUI's own example markup: one class inert against the other (`exclusive`),
 * a third result neither produces alone (`compose`), or indistinguishable in that example
 * (`same`) — and, per member, whether the class alone changed nothing (`inert`).
 *
 * **A set of classes becomes an enum only when EVERY pair in it is `exclusive`.** The cost of
 * being wrong is asymmetric and that is the whole argument:
 *
 * | wrong as | consequence |
 * |---|---|
 * | enum | a combination DaisyUI permits **cannot be expressed** |
 * | boolean | a combination that does nothing **can be expressed** |
 *
 * The first defeats the library's purpose, which is to reach the CSS DaisyUI ships; the second
 * merely fails to prevent a harmless mistake that `extraClasses` permits anyway. So `same` —
 * "the probe could not tell" — falls to boolean with `compose`, because reading it as
 * exclusivity would be inventing exclusivity, the expensive direction.
 *
 * This REPLACES a rule that made `styles`, `directions` and `placements` enums by virtue of
 * their category. That rule was written from DaisyUI's frontmatter and the measurement refutes
 * it in both directions: `chat.placements` and `carousel.directions` compose, while
 * `card.modifiers` and `list.modifiers` — `modifiers` being the category the old rule
 * distrusted — are exclusive.
 *
 * Which classes form an axis is DERIVED (`axis-derivation.ts`): the cliques of the exclusive
 * graph, inert members placed by what they declare. Nothing here declares a partition. What
 * cannot be derived is a NAME for each axis of a group that has more than one — DaisyUI's word
 * for both of `tooltip`'s is `placements` — and that, and only that, is what `enumNames`
 * holds. A single axis is named after its category (`categoryWord`) with no configuration.
 */
import { toCamelCase } from './classifier.ts'
import type { ClassifiedComponent } from './classifier.ts'
import { deriveAxes } from './axis-derivation.ts'
import type { Axis } from './axis-derivation.ts'
import { GroupKey } from './measurement.ts'
import type { Measurement, MeasuredPairs } from './measurement.ts'

/** The five categories DaisyUI's frontmatter uses, minus `colors` and `sizes`. */
export type GroupCategory = 'styles' | 'modifiers' | 'behaviors' | 'directions' | 'placements'

export const GROUP_CATEGORIES: readonly GroupCategory[] = [
  'styles',
  'modifiers',
  'behaviors',
  'directions',
  'placements',
]

/**
 * One enum to generate: its name, where its members came from, and which they are.
 *
 * `members` are the stripped class suffixes, e.g. `wide` for `btn-wide`.
 */
export interface EnumGroup {
  /** Full Kotlin name, e.g. `MaskStyle` — the component name plus the category word or a named axis. */
  enumName: string
  /** Parameter carrying it, e.g. `style` — the suffix in camelCase. */
  parameterName: string
  category: GroupCategory
  members: readonly string[]
}

export interface GroupClassification {
  enums: readonly EnumGroup[]
  /** Stripped class suffixes that remain individual boolean parameters. */
  booleans: readonly string[]
}

/**
 * `enumNames` from the config: component directory name → category → one name per derived
 * axis, in the order the axes come out (by each axis's first member in DaisyUI's list).
 *
 * Only groups that derive MORE than one axis appear here; an entry for a single-axis group is
 * refused, because that name is derived and a config restating a rule drifts from it.
 */
export type EnumNames = Readonly<Record<string, Readonly<Record<string, readonly string[]>>>>

export class GroupNamingError extends Error {}

/** One group under consideration: who it belongs to and which classes it holds. */
interface Group {
  readonly componentName: string
  readonly key: GroupKey
  readonly members: readonly string[]
}

/**
 * The enum suffix a single-axis group carries: DaisyUI's category word, singular.
 *
 * `loading.styles` is `LoadingStyle`, `alert.directions` is `AlertDirection`. The word is
 * DaisyUI's, so a reader holding its documentation can find the group; nothing here is invented.
 */
export function categoryWord(category: GroupCategory): string {
  const singular = category.replace(/s$/, '')
  return singular.charAt(0).toUpperCase() + singular.slice(1)
}

function enumFor(group: Group, suffix: string, members: Axis): EnumGroup {
  return {
    enumName: `${group.componentName}${suffix}`,
    parameterName: toCamelCase(suffix),
    category: group.key.category as GroupCategory,
    members,
  }
}

function rejectRestatedName(group: Group, entry: readonly string[] | undefined): void {
  if (entry === undefined) return
  throw new GroupNamingError(
    `enumNames.${group.key} names a single-axis group ${JSON.stringify(entry)}, but a single ` +
      `choice is named after its category — ` +
      `${group.componentName}${categoryWord(group.key.category as GroupCategory)} — without ` +
      `configuration. Remove the entry.`,
  )
}

function requireAxisNames(group: Group, axes: readonly Axis[], entry: readonly string[] | undefined): readonly string[] {
  if (entry !== undefined && entry.length === axes.length) return entry
  const shape = axes.map((axis) => `{${axis.join(', ')}}`).join(' and ')
  throw new GroupNamingError(
    `${group.key} derives ${axes.length} axes — ${shape} — and only a human can say what ` +
      `each is called. Add enumNames.${group.key} with ${axes.length} names in that order` +
      (entry === undefined ? '.' : `; it has ${entry.length}.`),
  )
}

/** One enum per derived axis: named after the category alone, or by configured axis names. */
function nameAxes(group: Group, axes: readonly Axis[], entry: readonly string[] | undefined): EnumGroup[] {
  if (axes.length === 1) {
    rejectRestatedName(group, entry)
    return [enumFor(group, categoryWord(group.key.category as GroupCategory), axes[0])]
  }
  const names = requireAxisNames(group, axes, entry)
  return axes.map((axis, index) => enumFor(group, names[index], axis))
}

function classifyGroup(group: Group, measured: MeasuredPairs, entry: readonly string[] | undefined): GroupClassification {
  const derived = deriveAxes(group.members, measured, String(group.key))
  if (derived.axes.length === 0) return { enums: [], booleans: derived.booleans }
  return { enums: nameAxes(group, derived.axes, entry), booleans: derived.booleans }
}

/**
 * Decide, for one component, which groups become enums and which classes stay boolean.
 *
 * @param component the component's directory name, e.g. `button` — the key both `enumNames`
 *   and `exclusivity.json` use
 */
export function classifyGroups(
  classified: ClassifiedComponent,
  component: string,
  enumNames: EnumNames,
  measurement: Measurement,
): GroupClassification {
  const configured = enumNames[component] ?? {}
  const enums: EnumGroup[] = []
  const booleans: string[] = []

  for (const category of GROUP_CATEGORIES) {
    const members = classified[category]
    if (members.length === 0) continue

    const key = new GroupKey(component, category)
    const group: Group = { componentName: classified.componentName, key, members }
    const decided = classifyGroup(group, measurement.forGroup(key), configured[category])

    enums.push(...decided.enums)
    booleans.push(...decided.booleans)
  }

  return { enums, booleans }
}
