# Tasks

A1 to A3 were probed before this plan was written. Section 2 checks A5 first, because the
dependency placement depends on it.

**Estimate, frozen 2026-11-01 at proposal, raw and uncorrected:** 150 minutes.

| Section | Minutes |
|---|---|
| 1. Probes (done before the estimate, excluded) | - |
| 2. The dependency | 20 |
| 3. The changed-line scope, under TCR | 50 |
| 4. The CI gate and its proof | 40, plus CI waits logged as `machine` |
| 5. Kill the survivors in `llms-index.ts` | 20 |
| 6. Documents, gate and adoption | 20 |

## 1. Probes

- [x] 1.1 `. d` (A1) Stryker's `command` runner on `llms-index.ts`, then on all of `codegen/src`:
  43.7 %, 1,986 survivors, 418 s.
- [x] 1.2 `. d` (A2) `--mutate` with a line range.

## 2. The dependency

- [x] 2.1 `. F (internal)` `@stryker-mutator/core` as an exact `devDependency`,
  `codegen/stryker.config.json`, the `mutate` npm script, Stryker's output in `.gitignore`.
- [x] 2.2 `. F (internal)` (A5) `installCodegenDeps` runs `npm ci --omit=dev`; `just generate`
  without a diff and `./gradlew :lib:testCodegen` green after a clean `node_modules`. Measured:
  6 packages installed instead of 113, no Stryker, 200 tests green, no generation diff.

## 3. The changed-line scope, under TCR

- [x] 3.1 `^ F (internal)` `mutationScope(diff)` returns one range per added hunk of a
  `codegen/src/` file.
- [x] 3.2 `^ F (internal)` It skips pure deletions, files outside `src/`, test files and
  `index-*.ts`.
- [x] 3.3 `^ F (internal)` It merges a file's hunks into one argument list and returns nothing for
  an empty diff.
- [x] 3.4 `! F (internal)` A CLI wrapper prints the `--mutate` arguments for `HEAD^1..HEAD`.

Section 3 landed as one `!` feature, one `!` scope rule, the CLI and five refactorings: one
revert (finding F1), and StrykerJS on the module itself left 14 survivors, which tests and
rewrites took to 54 of 54 killed (F2). 3.3 needed no code: an empty diff already gave nothing.

## 4. The CI gate and its proof

- [x] 4.1 `! F (internal)` `codegen-tests`: `fetch-depth: 2`, the scope step, and Stryker on the
  ranges when there are any, failing on a survivor.
- [x] 4.2 Probe pull requests: a test that asserts nothing on a new function fails the job; a
  change outside `codegen/src` runs no mutant. Record durations (A4). Close and delete them.
  PR #446: `codegen-tests` failed in 19 s, naming the survivors in `src/probe-weak.ts:3`.
  PR #447 (`codegen/test/` only): "nothing to mutate", green in 14 s. This PR, first run: 54
  mutants in 77 s; after `--test-isolation=none` (finding F4): 81 mutants in 13 s, job 27 s.

## 5. Kill the survivors in `llms-index.ts`

- [x] 5.1 `. r (internal)` One test per survivor, or delete code a survivor shows cannot matter;
  `npx stryker run --mutate src/llms-index.ts` at 100 %.
  Done: 146 of 146 killed. Six survivors needed a test; the optional chaining on a regex group
  went in a refactoring; `sort()` survived every filesystem test, because this machine's
  `readdir` already returns sorted entries, so the pure part was split out and tested with an
  unsorted list.

## 6. Documents, gate and adoption

- [x] 6.1 `. d` `AGENTS.md` test-quality table, `kdaisyui-codegen`, `kdaisyui-testing`.
- [x] 6.2 Full green, `openspec validate --strict`, findings, archive on the branch.
