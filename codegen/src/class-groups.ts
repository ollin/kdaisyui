/**
 * Which of a component's class groups become a Kotlin enum, and which stay boolean.
 *
 * DaisyUI's frontmatter files every class under one of five categories. Three of them —
 * `styles`, `directions`, `placements` — group classes that answer ONE question, so a group
 * with two or more members is a choice and becomes an enum. The other two, `modifiers` and
 * `behaviors`, are catch-alls: they group by where a class lives rather than by what it means,
 * so their members are independent flags and stay boolean.
 *
 * That split is not a guess. All 36 multi-member groups were put through the name test — can
 * this group be given a name that says what it decides? The seven that FAILED are six
 * `modifiers` groups and one `behaviors` group; not one `styles`, `directions` or `placements`
 * group failed. The rule is the measurement.
 *
 * A `modifiers` group whose members DO share an intention is promoted by naming it in
 * `enumNames` — seven are today. The default runs the other way from the enum-by-default
 * categories precisely because promotion is the rarer, human-judged case.
 */
import type { ClassifiedComponent } from './classifier.ts'

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
 * Categories whose multi-member groups MUST become an enum.
 *
 * Membership here is what makes an unnamed group a build failure rather than a silent pile of
 * booleans: if a class answers the same question as its siblings, leaving it boolean lets a
 * caller write two contradictory answers.
 */
const ENUM_BY_DEFAULT: readonly GroupCategory[] = ['styles', 'directions', 'placements']

/**
 * One enum to generate: its name, where its members came from, and which they are.
 *
 * `members` are the stripped class suffixes, e.g. `wide` for `btn-wide`.
 */
export interface EnumGroup {
  /** Full Kotlin name, e.g. `ButtonEmphasis` — the component name plus the configured suffix. */
  enumName: string
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

/** A configured name: one suffix for the whole group, or several axes that split it. */
export type EnumNameEntry = string | readonly EnumSplit[]

/** `enumNames` from the config: component directory name → category → name or split. */
export type EnumNames = Readonly<Record<string, Readonly<Record<string, EnumNameEntry>>>>

export class GroupNamingError extends Error {}

function membersOf(classified: ClassifiedComponent, category: GroupCategory): readonly string[] {
  return classified[category]
}

function splitsFor(entry: EnumNameEntry, members: readonly string[]): readonly EnumSplit[] {
  return typeof entry === 'string' ? [{ name: entry, members }] : entry
}

function checkSplitCoverage(
  component: string,
  category: GroupCategory,
  members: readonly string[],
  splits: readonly EnumSplit[],
): void {
  const claimed = splits.flatMap((split) => split.members)
  const unknown = claimed.filter((member) => !members.includes(member))
  if (unknown.length > 0) {
    throw new GroupNamingError(
      `enumNames.${component}.${category} names ${unknown.map((m) => `"${m}"`).join(', ')}, ` +
        `which DaisyUI does not list. Members are: ${members.join(', ')}.`,
    )
  }
  const unclaimed = members.filter((member) => !claimed.includes(member))
  if (unclaimed.length > 0) {
    throw new GroupNamingError(
      `enumNames.${component}.${category} leaves ${unclaimed.map((m) => `"${m}"`).join(', ')} ` +
        `unassigned. Every member of a split group must belong to an axis, otherwise a new ` +
        `DaisyUI class silently becomes a boolean.`,
    )
  }
}

function requireName(
  component: string,
  category: GroupCategory,
  members: readonly string[],
): never {
  throw new GroupNamingError(
    `${component}.${category} has ${members.length} members (${members.join(', ')}) and no ` +
      `enumNames entry. A group in this category answers one question, so its members are ` +
      `mutually exclusive and must become an enum — and only a human can say what the ` +
      `question is. Add enumNames.${component}.${category}.`,
  )
}

/**
 * Decide, for one component, which groups become enums and which classes stay boolean.
 *
 * Throws {@link GroupNamingError} rather than falling back to a derived name: a group nobody
 * could name is the signal that the grouping itself is wrong, and a default like `ButtonStyles`
 * would bury that signal under a plausible-looking enum.
 *
 * @param component the component's directory name, e.g. `button` — the key `enumNames` uses
 */
export function classifyGroups(
  classified: ClassifiedComponent,
  component: string,
  enumNames: EnumNames,
): GroupClassification {
  const configured = enumNames[component] ?? {}
  const enums: EnumGroup[] = []
  const booleans: string[] = []

  for (const category of GROUP_CATEGORIES) {
    const members = membersOf(classified, category)
    if (members.length === 0) continue

    const entry = configured[category]
    if (entry === undefined) {
      // A single member answers no question on its own, so even an enum-by-default category
      // leaves it boolean — `divider-start` alone is a flag, not a choice.
      if (members.length > 1 && ENUM_BY_DEFAULT.includes(category)) {
        requireName(component, category, members)
      }
      booleans.push(...members)
      continue
    }

    const splits = splitsFor(entry, members)
    checkSplitCoverage(component, category, members, splits)
    for (const split of splits) {
      enums.push({
        enumName: `${classified.componentName}${split.name}`,
        category,
        members: split.members,
      })
    }
  }

  return { enums, booleans }
}
