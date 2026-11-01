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
const HUNK = /^@@ -\S+ \+(\d+)(?:,(\d+))? @@/gm

/** The file a diff section is about, from its `+++ b/<path>` line. A deleted file has none. */
const NEW_FILE = /^\+\+\+ b\/(?<path>.+)$/m

/** TypeScript under `src/`, which the gate mutates. */
const SOURCE = /^src\/.+\.ts$/

/** The entry points, which run only as scripts that no unit test imports. */
const ENTRY_POINT = /^src\/index-[^/]+\.ts$/

const isMutated = (file: string) => SOURCE.test(file) && !ENTRY_POINT.test(file)

export function mutationScope(diff: string): string[] {
  return diff.split(/^(?=diff --git )/m).flatMap(fileRanges)
}

/** The ranges of one file's section of the diff. */
function fileRanges(section: string): string[] {
  const file = NEW_FILE.exec(section)?.groups?.path
  if (file === undefined || !isMutated(file)) return []
  return [...section.matchAll(HUNK)].flatMap(([, start, count]) => addedRange(file, Number(start), count))
}

/** A hunk's added lines as `<file>:<first>-<last>`, or nothing when it only deletes. */
function addedRange(file: string, start: number, count: string | undefined): string[] {
  const lines = count === undefined ? 1 : Number(count)
  return lines > 0 ? [`${file}:${start}-${start + lines - 1}`] : []
}
