/**
 * Which of a component's class groups become a Kotlin enum, and which stay boolean.
 *
 * The decision is a MEASUREMENT, not a category rule. `codegen/exclusivity.json` records, for
 * every pair of classes in every multi-member group, what a browser did when both were worn at
 * once against DaisyUI's own example markup: one class inert against the other (`exclusive`),
 * a third result neither produces alone (`compose`), or indistinguishable in that example
 * (`same`).
 *
 * **A group becomes an enum only when EVERY pair in it is `exclusive`.** The cost of being
 * wrong is asymmetric and that is the whole argument:
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
 * Exclusivity is NOT transitive, so an axis is a clique and not a connected component: under
 * `tooltip.placements` both `{top, bottom, left, right}` and `{start, center, end}` are
 * cliques and `top` belongs to both. A partition therefore cannot be derived from the pairs,
 * and the axes of a group that carries two are declared in `enumNames` and CHECKED here
 * against the measurement.
 */
import { toCamelCase } from './classifier.ts'
import type { ClassifiedComponent } from './classifier.ts'
import { ClassPair, GroupKey } from './measurement.ts'
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
  /** Full Kotlin name, e.g. `MaskStyle` — the component name plus the category word or a declared axis. */
  enumName: string
  /** The parameter that carries it, e.g. `shape` — the suffix in camelCase. */
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
 * One axis of a group that splits, e.g. `{ name: 'Vertical', members: ['top','middle','bottom'] }`.
 *
 * Four placement groups carry two independent axes — `indicator` and `toast` are X and Y,
 * `dropdown` and `tooltip` are side and alignment. One enum would force a caller to choose
 * between them.
 */
export interface EnumSplit {
  name: string
  members: readonly string[]
}

/**
 * A group that is part choice and part flags.
 *
 * Needed by exactly one group so far, and the reason is worth keeping: `dropdown.placements`
 * holds a clean alignment axis — `start`, `center`, `end`, every pair exclusive — beside four
 * classes the browser says compose with everything, including each other. Declaring the sides
 * as an axis would make `dropdown-left dropdown-top` inexpressible against a measurement that
 * says the combination reaches CSS neither class reaches alone.
 *
 * The `booleans` list is what keeps the coverage guard usable. Without it, "unassigned" would
 * mean both *deliberately a flag* and *nobody has looked at this yet*, and a class DaisyUI
 * added later would slip through as a boolean by omission — which is the exact failure the
 * guard exists to prevent.
 */
export interface EnumGroupSpec {
  readonly axes: readonly EnumSplit[]
  /** Members that stay individual boolean parameters, named rather than left out. */
  readonly booleans?: readonly string[]
}

/**
 * A configured split: several axes that divide the group, or — where the group is part choice
 * and part flags — both halves named explicitly. A single-axis group is never configured; its
 * name is derived (see `categoryWord`), and a string entry is refused as a restatement.
 */
export type EnumNameEntry = readonly EnumSplit[] | EnumGroupSpec

/** `enumNames` from the config: component directory name → category → name or split. */
export type EnumNames = Readonly<Record<string, Readonly<Record<string, EnumNameEntry>>>>

export class GroupNamingError extends Error {}

/** The measurement and the configuration disagree — one of them has to be corrected. */
export class ExclusivityError extends Error {}

/** One group under consideration: who it belongs to and which classes it holds. */
interface Group {
  readonly componentName: string
  readonly key: GroupKey
  readonly members: readonly string[]
}

function splitsFor(entry: EnumNameEntry): readonly EnumSplit[] {
  if (Array.isArray(entry)) return entry
  return (entry as EnumGroupSpec).axes
}

/** Members the config declares as flags. Empty for every form but the object one. */
function declaredBooleansFor(entry: EnumNameEntry): readonly string[] {
  if (Array.isArray(entry)) return []
  return (entry as EnumGroupSpec).booleans ?? []
}

/** Every pair drawn from two different axes of the same group. */
function crossAxisPairs(splits: readonly EnumSplit[]): ClassPair[] {
  return splits.flatMap((split, index) =>
    splits
      .slice(index + 1)
      .flatMap((other) =>
        split.members.flatMap((left) => other.members.map((right) => new ClassPair(left, right))),
      ),
  )
}

function quoted(members: readonly string[]): string {
  return members.map((member) => `"${member}"`).join(', ')
}

function checkSplitCoverage(
  group: Group,
  splits: readonly EnumSplit[],
  declaredBooleans: readonly string[],
): void {
  const claimed = [...splits.flatMap((split) => split.members), ...declaredBooleans]
  const unknown = claimed.filter((member) => !group.members.includes(member))
  if (unknown.length > 0) {
    throw new GroupNamingError(
      `enumNames.${group.key} names ${quoted(unknown)}, which DaisyUI does not list. ` +
        `Members are: ${group.members.join(', ')}.`,
    )
  }

  const unclaimed = group.members.filter((member) => !claimed.includes(member))
  if (unclaimed.length > 0) {
    throw new GroupNamingError(
      `enumNames.${group.key} leaves ${quoted(unclaimed)} unassigned. Every member of a named ` +
        `group must belong to an axis or be listed under "booleans", otherwise a new DaisyUI ` +
        `class silently becomes a boolean by omission.`,
    )
  }
}

/**
 * Flags the config declares must not be a choice it failed to notice.
 *
 * Only a WHOLE missed enum is reported. Some exclusive pairs among the flags are expected and
 * harmless — `dropdown-top` and `dropdown-bottom` are exclusive while both compose with
 * `dropdown-left`, so the four together are not a choice and leaving them as flags costs
 * nothing but a useless combination.
 */
function checkBooleansAreNotAChoice(
  group: Group,
  measured: MeasuredPairs,
  declaredBooleans: readonly string[],
): void {
  if (declaredBooleans.length < 2) return
  if (measured.describeNonExclusive(declaredBooleans).length > 0) return

  throw new ExclusivityError(
    `enumNames.${group.key} lists ${declaredBooleans.join(', ')} as booleans, but the ` +
      `measurement says every pair of them is exclusive — that is a choice, and leaving it as ` +
      `flags lets a caller set two contradictory answers at once. Give it an axis.`,
  )
}

/** Each declared axis must be a clique of `exclusive` verdicts, or the enum is a lie. */
function checkAxisIsExclusive(group: Group, measured: MeasuredPairs, split: EnumSplit): void {
  const offenders = measured.describeNonExclusive(split.members)
  if (offenders.length === 0) return

  throw new ExclusivityError(
    `enumNames.${group.key} makes ${split.members.join(', ')} one choice, but the measurement ` +
      `says ${offenders.join('; ')}. An enum would make that combination impossible to ` +
      `express. Either drop the entry and let these stay boolean, or split the axis so every ` +
      `pair within it is exclusive.`,
  )
}

/**
 * A split has to be earned: some pair from different axes must NOT be exclusive.
 *
 * If every cross pair is exclusive too, the group is one clique and one enum says so more
 * simply — two enums would then let a caller set two answers to the same question.
 */
function checkSplitIsEarned(group: Group, measured: MeasuredPairs, splits: readonly EnumSplit[]): void {
  const composing = crossAxisPairs(splits).some((pair) => !measured.isExclusive(pair))
  if (composing) return

  throw new ExclusivityError(
    `enumNames.${group.key} splits into ${splits.map((split) => split.name).join(' and ')}, but ` +
      `every pair across those axes is exclusive, so the group is a single choice. Use one ` +
      `enum — two would let a caller answer the same question twice.`,
  )
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

/**
 * A group nobody named stays boolean — unless the measurement says it is a choice, in which
 * case it is one enum named after its category.
 *
 * A single member answers no question on its own, and an unmeasured or composing group is not
 * a choice either.
 */
function unnamedGroup(group: Group, measured: MeasuredPairs): GroupClassification {
  const isChoice = group.members.length > 1 && measured.describeNonExclusive(group.members).length === 0
  if (!isChoice) return { enums: [], booleans: [...group.members] }

  const suffix = categoryWord(group.key.category as GroupCategory)
  return {
    enums: [{
      enumName: `${group.componentName}${suffix}`,
      parameterName: toCamelCase(suffix),
      category: group.key.category as GroupCategory,
      members: group.members,
    }],
    booleans: [],
  }
}

/** A string entry restates the derived name and is refused, so the config carries no rule twice. */
function rejectRestatedName(group: Group, entry: EnumNameEntry): void {
  if (typeof entry !== 'string') return
  throw new GroupNamingError(
    `enumNames.${group.key} names a single-axis group "${entry}", but a single choice is named ` +
      `after its category — ${group.componentName}${categoryWord(group.key.category as GroupCategory)} — ` +
      `without configuration. Remove the entry.`,
  )
}

/** A named group becomes one enum per declared axis, each checked against the measurement. */
function namedGroup(
  group: Group,
  entry: EnumNameEntry,
  measured: MeasuredPairs,
): GroupClassification {
  rejectRestatedName(group, entry)
  const splits = splitsFor(entry)
  const declaredBooleans = declaredBooleansFor(entry)

  checkSplitCoverage(group, splits, declaredBooleans)
  for (const split of splits) checkAxisIsExclusive(group, measured, split)
  if (splits.length > 1) checkSplitIsEarned(group, measured, splits)
  checkBooleansAreNotAChoice(group, measured, declaredBooleans)

  return {
    enums: splits.map((split) => ({
      enumName: `${group.componentName}${split.name}`,
      parameterName: toCamelCase(split.name),
      category: group.key.category as GroupCategory,
      members: split.members,
    })),
    // In DaisyUI's order, not the config's: the generated parameter list should not change
    // because someone reordered a list in the config.
    booleans: group.members.filter((member) => declaredBooleans.includes(member)),
  }
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
    const measured = measurement.forGroup(key)
    const entry = configured[category]
    const decided =
      entry === undefined ? unnamedGroup(group, measured) : namedGroup(group, entry, measured)

    enums.push(...decided.enums)
    booleans.push(...decided.booleans)
  }

  return { enums, booleans }
}
