/**
 * The measured exclusivity of DaisyUI's class groups, as types rather than encoded strings.
 *
 * `codegen/exclusivity.json` stores a pair as `"start|top"` and a group as
 * `"indicator.placements"`, because JSON has only strings to key by. Read back as strings they
 * are primitive obsession with a cost that showed up immediately: five `split()` calls across
 * three modules, each re-deriving the same structure, and every question about the data
 * answered by a nest of loops.
 *
 * So the file format stays as it is — it is a committed measurement and readable in a diff —
 * and exactly one place turns it into `ClassPair`, `GroupKey` and `Measurement`.
 *
 * `exclusivity-verdicts.js` also writes this format and CANNOT import these types: it runs in
 * a browser, and nothing here is bundled. It is the one other place allowed to know the format,
 * and it says so.
 */

import { readFileSync } from 'node:fs'
import path from 'node:path'

const EXCLUSIVITY_PATH = path.resolve(import.meta.dirname, '../exclusivity.json')

/** What the browser did when two classes of one group were worn at once. */
export type Verdict = 'exclusive' | 'compose' | 'same'

export const VERDICTS: readonly Verdict[] = ['exclusive', 'compose', 'same']

/**
 * Two classes of one group, unordered.
 *
 * Unordered is the point: the measurement records `top|bottom`, and a caller asking about
 * `bottom|top` is asking the same question. Every earlier lookup had to remember that.
 */
export class ClassPair {
  // Fields declared and assigned rather than written as constructor parameter properties:
  // `codegen` is TypeScript that Node strips rather than compiles, and a parameter property
  // is not erasable syntax. See AGENTS.md.
  readonly left: string
  readonly right: string

  constructor(left: string, right: string) {
    this.left = left
    this.right = right
  }

  static parse(key: string): ClassPair {
    const [left, right] = key.split('|')
    return new ClassPair(left, right)
  }

  /** Every pair drawn from `members`, in the order the probe emits them. */
  static allOf(members: readonly string[]): ClassPair[] {
    return members.flatMap((left, index) =>
      members.slice(index + 1).map((right) => new ClassPair(left, right)),
    )
  }

  equals(other: ClassPair): boolean {
    if (this.left === other.left && this.right === other.right) return true
    return this.left === other.right && this.right === other.left
  }

  mentionsAnythingOutside(known: ReadonlySet<string>): boolean {
    return !known.has(this.left) || !known.has(this.right)
  }

  toString(): string {
    return `${this.left}|${this.right}`
  }
}

/** One class group: a component directory and one of DaisyUI's frontmatter categories. */
export class GroupKey {
  readonly component: string
  readonly category: string

  constructor(component: string, category: string) {
    this.component = component
    this.category = category
  }

  static parse(key: string): GroupKey {
    const separator = key.indexOf('.')
    return new GroupKey(key.slice(0, separator), key.slice(separator + 1))
  }

  equals(other: GroupKey): boolean {
    return this.component === other.component && this.category === other.category
  }

  toString(): string {
    return `${this.component}.${this.category}`
  }
}

/** One group's verdicts, as written in the file. */
export interface MeasuredGroupJson {
  readonly exclusive?: readonly string[]
  readonly compose?: readonly string[]
  readonly same?: readonly string[]
}

/** `groups` from `exclusivity.json`: directory name → category → verdicts. */
export type ExclusivityJson = Readonly<
  Record<string, Readonly<Record<string, MeasuredGroupJson>>>
>

/**
 * Everything measured about ONE group.
 *
 * A group nobody measured is an empty one rather than a missing one, so callers never branch
 * on absence: unmeasured and composing both mean "not established", and conflating them is
 * safe precisely because the asymmetric-cost rule treats them alike.
 */
export class MeasuredPairs {
  private readonly verdicts: ReadonlyMap<string, Verdict>

  private constructor(verdicts: ReadonlyMap<string, Verdict>) {
    this.verdicts = verdicts
  }

  static none(): MeasuredPairs {
    return new MeasuredPairs(new Map())
  }

  static fromJson(json: MeasuredGroupJson): MeasuredPairs {
    const verdicts = new Map<string, Verdict>()
    for (const verdict of VERDICTS) {
      for (const key of json[verdict] ?? []) verdicts.set(key, verdict)
    }
    return new MeasuredPairs(verdicts)
  }

  verdictFor(pair: ClassPair): Verdict | undefined {
    return this.verdicts.get(pair.toString()) ?? this.verdicts.get(new ClassPair(pair.right, pair.left).toString())
  }

  isExclusive(pair: ClassPair): boolean {
    return this.verdictFor(pair) === 'exclusive'
  }

  /** Every pair of `members` the measurement does NOT call exclusive, described for a human. */
  describeNonExclusive(members: readonly string[]): string[] {
    return ClassPair.allOf(members)
      .filter((pair) => !this.isExclusive(pair))
      .map((pair) => `${pair} is ${this.verdictFor(pair) ?? 'unmeasured'}`)
  }

  coversEveryPairOf(members: readonly string[]): boolean {
    return ClassPair.allOf(members).every((pair) => this.verdictFor(pair) !== undefined)
  }

  /** Every recorded pair, whatever verdict it was filed under. */
  pairs(): ClassPair[] {
    return [...this.verdicts.keys()].map(ClassPair.parse)
  }

  /** True when every pair is exclusive, i.e. the group is one choice. */
  isSingleChoice(): boolean {
    if (this.verdicts.size === 0) return false
    return [...this.verdicts.values()].every((verdict) => verdict === 'exclusive')
  }

  count(): number {
    return this.verdicts.size
  }
}

/** The whole committed measurement, keyed by group. */
export class Measurement {
  private readonly groups: ReadonlyMap<string, MeasuredPairs>

  private constructor(groups: ReadonlyMap<string, MeasuredPairs>) {
    this.groups = groups
  }

  static fromJson(json: ExclusivityJson): Measurement {
    const groups = new Map<string, MeasuredPairs>()
    for (const [component, categories] of Object.entries(json)) {
      Measurement.addComponent(groups, component, categories)
    }
    return new Measurement(groups)
  }

  private static addComponent(
    groups: Map<string, MeasuredPairs>,
    component: string,
    categories: Readonly<Record<string, MeasuredGroupJson>>,
  ): void {
    for (const [category, json] of Object.entries(categories)) {
      groups.set(new GroupKey(component, category).toString(), MeasuredPairs.fromJson(json))
    }
  }

  forGroup(key: GroupKey): MeasuredPairs {
    return this.groups.get(key.toString()) ?? MeasuredPairs.none()
  }

  wasMeasured(key: GroupKey): boolean {
    return this.groups.has(key.toString())
  }

  keys(): GroupKey[] {
    return [...this.groups.keys()].map(GroupKey.parse)
  }

  /** The groups that may become an enum as they stand — every pair exclusive. */
  singleChoices(): GroupKey[] {
    return this.keys().filter((key) => this.forGroup(key).isSingleChoice())
  }

  groupCount(): number {
    return this.groups.size
  }

  pairCount(): number {
    return this.keys().reduce((total, key) => total + this.forGroup(key).count(), 0)
  }
}

/**
 * The committed measurement.
 *
 * Read from disk rather than imported, so a JSON syntax error names the file instead of
 * failing somewhere inside a module graph.
 */
export function loadMeasurement(): Measurement {
  return Measurement.fromJson(JSON.parse(readFileSync(EXCLUSIVITY_PATH, 'utf8')).groups)
}
