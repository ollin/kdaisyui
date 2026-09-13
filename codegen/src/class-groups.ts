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
import { readFileSync } from 'node:fs'
import path from 'node:path'
import type { ClassifiedComponent } from './classifier.ts'

const EXCLUSIVITY_PATH = path.resolve(import.meta.dirname, '../exclusivity.json')

/** The five categories DaisyUI's frontmatter uses, minus `colors` and `sizes`. */
export type GroupCategory = 'styles' | 'modifiers' | 'behaviors' | 'directions' | 'placements'

export const GROUP_CATEGORIES: readonly GroupCategory[] = [
  'styles',
  'modifiers',
  'behaviors',
  'directions',
  'placements',
]

/** What the browser did when two classes of one group were worn at once. */
export type Verdict = 'exclusive' | 'compose' | 'same'

/** One group's measured pairs, each list holding keys of the form `a|b`. */
export interface MeasuredGroup {
  readonly exclusive?: readonly string[]
  readonly compose?: readonly string[]
  readonly same?: readonly string[]
}

/** `groups` from `exclusivity.json`: directory name → category → measured pairs. */
export type Exclusivity = Readonly<Record<string, Readonly<Record<string, MeasuredGroup>>>>

/**
 * One enum to generate: its name, where its members came from, and which they are.
 *
 * `members` are the stripped class suffixes, e.g. `wide` for `btn-wide`.
 */
export interface EnumGroup {
  /** Full Kotlin name, e.g. `MaskShape` — the component name plus the configured suffix. */
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

/**
 * The committed measurement.
 *
 * Read from disk rather than imported, so a JSON syntax error names the file instead of
 * failing somewhere inside a module graph.
 */
export function loadExclusivity(): Exclusivity {
  return JSON.parse(readFileSync(EXCLUSIVITY_PATH, 'utf8')).groups
}

export class GroupNamingError extends Error {}

/** The measurement and the configuration disagree — one of them has to be corrected. */
export class ExclusivityError extends Error {}

function membersOf(classified: ClassifiedComponent, category: GroupCategory): readonly string[] {
  return classified[category]
}

/**
 * The verdict for one pair, whichever order it was recorded in.
 *
 * `undefined` means the pair was never measured — a group DaisyUI has added since, or a
 * component whose docs carry no usable example. Unmeasured is not established, so callers
 * treat it the way they treat `compose`.
 */
function verdictOf(measured: MeasuredGroup, a: string, b: string): Verdict | undefined {
  for (const verdict of ['exclusive', 'compose', 'same'] as const) {
    const pairs = measured[verdict] ?? []
    if (pairs.includes(`${a}|${b}`) || pairs.includes(`${b}|${a}`)) return verdict
  }
  return undefined
}

/** Every pair of `members` that the measurement does NOT call exclusive. */
function nonExclusivePairs(
  measured: MeasuredGroup,
  members: readonly string[],
): readonly string[] {
  const offenders: string[] = []
  for (let i = 0; i < members.length; i++) {
    for (let j = i + 1; j < members.length; j++) {
      const verdict = verdictOf(measured, members[i], members[j])
      if (verdict === 'exclusive') continue
      offenders.push(`${members[i]}|${members[j]} is ${verdict ?? 'unmeasured'}`)
    }
  }
  return offenders
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

/** Each declared axis must be a clique of `exclusive` verdicts, or the enum is a lie. */
function checkAxisIsExclusive(
  component: string,
  category: GroupCategory,
  measured: MeasuredGroup,
  split: EnumSplit,
): void {
  const offenders = nonExclusivePairs(measured, split.members)
  if (offenders.length === 0) return
  throw new ExclusivityError(
    `enumNames.${component}.${category} makes ${split.members.join(', ')} one choice, but the ` +
      `measurement says ${offenders.join('; ')}. An enum would make that combination ` +
      `impossible to express. Either drop the entry and let these stay boolean, or split the ` +
      `axis so every pair within it is exclusive.`,
  )
}

/**
 * A split has to be earned: some pair from different axes must NOT be exclusive.
 *
 * If every cross pair is exclusive too, the group is one clique and one enum says so more
 * simply — two enums would then let a caller set two answers to the same question.
 */
function checkSplitIsEarned(
  component: string,
  category: GroupCategory,
  measured: MeasuredGroup,
  splits: readonly EnumSplit[],
): void {
  for (let i = 0; i < splits.length; i++) {
    for (let j = i + 1; j < splits.length; j++) {
      for (const left of splits[i].members) {
        for (const right of splits[j].members) {
          if (verdictOf(measured, left, right) !== 'exclusive') return
        }
      }
    }
  }
  throw new ExclusivityError(
    `enumNames.${component}.${category} splits into ${splits.map((s) => s.name).join(' and ')}, ` +
      `but every pair across those axes is exclusive, so the group is a single choice. Use one ` +
      `enum — two would let a caller answer the same question twice.`,
  )
}

function requireName(
  component: string,
  category: GroupCategory,
  members: readonly string[],
): never {
  throw new GroupNamingError(
    `${component}.${category} has ${members.length} members (${members.join(', ')}) and the ` +
      `measurement says every pair of them is mutually exclusive, so they are one choice and ` +
      `must become an enum — and only a human can say what the question is. Add ` +
      `enumNames.${component}.${category}.`,
  )
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
  exclusivity: Exclusivity,
): GroupClassification {
  const configured = enumNames[component] ?? {}
  const measuredCategories = exclusivity[component] ?? {}
  const enums: EnumGroup[] = []
  const booleans: string[] = []

  for (const category of GROUP_CATEGORIES) {
    const members = membersOf(classified, category)
    if (members.length === 0) continue

    const measured = measuredCategories[category] ?? {}
    const entry = configured[category]

    if (entry === undefined) {
      // A single member answers no question on its own, and an unmeasured or composing group
      // is not a choice — either way the classes stay independent flags.
      const exclusiveThroughout = members.length > 1 && nonExclusivePairs(measured, members).length === 0
      if (exclusiveThroughout) requireName(component, category, members)
      booleans.push(...members)
      continue
    }

    const splits = splitsFor(entry, members)
    checkSplitCoverage(component, category, members, splits)
    for (const split of splits) checkAxisIsExclusive(component, category, measured, split)
    if (splits.length > 1) checkSplitIsEarned(component, category, measured, splits)
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
