/**
 * The lines of `codegen/src/` a pull request adds or changes, as StrykerJS `--mutate` ranges.
 *
 * CI mutates exactly these lines and fails on any survivor (`mutation-testing`, "Changed codegen
 * lines are mutation-tested at 100 %"). The whole directory scores far below that, so a gate on
 * anything wider would either fail every pull request or let new survivors through.
 *
 * Reads the output of `git diff -U0 --relative` run in `codegen/`, so paths read `src/…`.
 */

/** `@@ -<old> +<start>[,<count>] @@`: the new side of a hunk. */
const HUNK = /^@@ -\S+ \+(?<start>\d+)(?:,(?<count>\d+))? @@/

/** The file a diff section is about, from its `+++ b/<path>` line. */
const NEW_FILE = /^\+\+\+ b\/(?<path>.+)$/

/** TypeScript under `src/`, which the gate mutates. */
const SOURCE = /^src\/.+\.ts$/

/** The entry points, which run only as scripts that no unit test imports. */
const ENTRY_POINT = /^src\/index-[^/]+\.ts$/

const isMutated = (file: string) => SOURCE.test(file) && !ENTRY_POINT.test(file)

export function mutationScope(diff: string): string[] {
  let file = ''
  const ranges: string[] = []
  for (const line of diff.split('\n')) {
    const path = NEW_FILE.exec(line)?.groups?.path
    if (path !== undefined) file = path
    const hunk = HUNK.exec(line)?.groups
    if (hunk === undefined || !isMutated(file)) continue
    const start = Number(hunk.start)
    const count = hunk.count === undefined ? 1 : Number(hunk.count)
    if (count > 0) ranges.push(`${file}:${start}-${start + count - 1}`)
  }
  return ranges
}
