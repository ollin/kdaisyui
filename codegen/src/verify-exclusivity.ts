/**
 * Fail when `codegen/exclusivity.json` no longer describes the DaisyUI in the submodule.
 *
 * The measurement decides the library's public shape — which class groups become an enum and
 * which stay boolean — and it is a snapshot of one DaisyUI version. A bump that adds a
 * component, adds a class to a group, or removes one leaves that snapshot quietly wrong, and
 * every failure mode is silent: a new unmeasured group falls to boolean by the
 * not-established rule, and a removed class leaves a verdict about a class nobody can write.
 *
 * So this compares the two mechanically and says exactly what to re-measure.
 *
 * It reads the submodule, which is why it runs in `generated-sources-drift` beside
 * `checkComponentApi` and NOT in the codegen unit tests — that job is deliberately free of
 * submodules and a JDK, and `.github/workflows/ci.yml` says so at the job itself.
 */
import { getAllComponentDirs, readComponentFrontmatter } from './parser/frontmatter.ts'
import { classifyFromFrontmatter } from './classifier.ts'
import { PROBE_CATEGORIES } from './exclusivity-probe.ts'
import { ClassPair, GroupKey } from './measurement.ts'
import type { Measurement, MeasuredPairs } from './measurement.ts'

/** One class group as DaisyUI documents it right now. */
export interface LiveGroup {
  readonly key: GroupKey
  readonly members: readonly string[]
}

export interface Mismatch {
  readonly group: GroupKey
  readonly message: string
}

/** Every multi-member class group in the submodule — the set the measurement must cover. */
export function liveGroups(): LiveGroup[] {
  return getAllComponentDirs().flatMap(groupsOfComponent)
}

function groupsOfComponent(directory: string): LiveGroup[] {
  const frontmatter = readComponentFrontmatter(directory)
  if (!frontmatter) return []

  const classified = classifyFromFrontmatter(frontmatter, directory)
  if (!classified.prefix) return []

  return PROBE_CATEGORIES.filter((category) => classified[category].length > 1).map((category) => ({
    key: new GroupKey(directory, category),
    members: classified[category],
  }))
}

function notMeasured(group: LiveGroup): Mismatch {
  return {
    group: group.key,
    message:
      `is not measured. DaisyUI groups ${group.members.length} classes here ` +
      `(${group.members.join(', ')}), so whether they are one choice or independent flags is ` +
      `unknown — and unknown falls to boolean, which silently drops an enum.`,
  }
}

function missingPairs(group: LiveGroup, measured: MeasuredPairs): Mismatch[] {
  const missing = ClassPair.allOf(group.members).filter(
    (pair) => measured.verdictFor(pair) === undefined,
  )
  if (missing.length === 0) return []

  return [
    {
      group: group.key,
      message: `has no verdict for ${missing.join(', ')} — DaisyUI added those classes.`,
    },
  ]
}

function stalePairs(group: LiveGroup, measured: MeasuredPairs): Mismatch[] {
  const known = new Set(group.members)
  const stale = measured.pairs().filter((pair) => pair.mentionsAnythingOutside(known))
  if (stale.length === 0) return []

  return [
    {
      group: group.key,
      message: `still records ${stale.join(', ')}, naming classes DaisyUI no longer ships.`,
    },
  ]
}

function mismatchesFor(group: LiveGroup, measurement: Measurement): Mismatch[] {
  if (!measurement.wasMeasured(group.key)) return [notMeasured(group)]

  const measured = measurement.forGroup(group.key)
  return [...missingPairs(group, measured), ...stalePairs(group, measured)]
}

function removedGroups(measurement: Measurement, live: readonly LiveGroup[]): Mismatch[] {
  return measurement
    .keys()
    .filter((key) => !live.some((group) => group.key.equals(key)))
    .map((key) => ({ group: key, message: 'is measured but DaisyUI no longer groups it.' }))
}

/** Compare the committed measurement against the class groups DaisyUI documents today. */
export function findMismatches(measurement: Measurement, live: readonly LiveGroup[] = liveGroups()): Mismatch[] {
  const stale = live.flatMap((group) => mismatchesFor(group, measurement))
  return [...stale, ...removedGroups(measurement, live)]
}

/** The message the verification dies with. One line per mismatch, so none of them is buried. */
export function describeMismatches(mismatches: readonly Mismatch[]): string {
  return [
    `codegen/exclusivity.json is out of date (${mismatches.length}):`,
    ...mismatches.map((mismatch) => `  ✗ ${mismatch.group} ${mismatch.message}`),
    ``,
    `Re-measure with \`just measure-exclusivity\`, then READ the diff: a pair that changed`,
    `verdict changes the public API. See the kdaisyui-daisyui-upgrade skill.`,
  ].join('\n')
}
