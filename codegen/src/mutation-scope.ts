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

export function mutationScope(diff: string): string[] {
  let file = ''
  const ranges: string[] = []
  for (const line of diff.split('\n')) {
    const path = NEW_FILE.exec(line)?.groups?.path
    if (path !== undefined) file = path
    const hunk = HUNK.exec(line)?.groups
    if (hunk === undefined) continue
    const start = Number(hunk.start)
    const count = hunk.count === undefined ? 1 : Number(hunk.count)
    ranges.push(`${file}:${start}-${start + count - 1}`)
  }
  return ranges
}
