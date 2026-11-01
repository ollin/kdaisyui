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

- [ ] 2.1 `. F (internal)` `@stryker-mutator/core` as an exact `devDependency`,
  `codegen/stryker.config.json`, the `mutate` npm script, Stryker's output in `.gitignore`.
- [ ] 2.2 `. F (internal)` (A5) `installCodegenDeps` runs `npm ci --omit=dev`; `just generate`
  without a diff and `./gradlew :lib:testCodegen` green after a clean `node_modules`.

## 3. The changed-line scope, under TCR

- [ ] 3.1 `^ F (internal)` `mutationScope(diff)` returns one range per added hunk of a
  `codegen/src/` file.
- [ ] 3.2 `^ F (internal)` It skips pure deletions, files outside `src/`, test files and
  `index-*.ts`.
- [ ] 3.3 `^ F (internal)` It merges a file's hunks into one argument list and returns nothing for
  an empty diff.
- [ ] 3.4 `! F (internal)` A CLI wrapper prints the `--mutate` arguments for `HEAD^1..HEAD`.

## 4. The CI gate and its proof

- [ ] 4.1 `! F (internal)` `codegen-tests`: `fetch-depth: 2`, the scope step, and Stryker on the
  ranges when there are any, failing on a survivor.
- [ ] 4.2 Probe pull requests: a test that asserts nothing on a new function fails the job; a
  change outside `codegen/src` runs no mutant. Record durations (A4). Close and delete them.

## 5. Kill the survivors in `llms-index.ts`

- [ ] 5.1 `. r (internal)` One test per survivor, or delete code a survivor shows cannot matter;
  `npx stryker run --mutate src/llms-index.ts` at 100 %.

## 6. Documents, gate and adoption

- [ ] 6.1 `. d` `AGENTS.md` test-quality table, `kdaisyui-codegen`, `kdaisyui-testing`.
- [ ] 6.2 Full green, `openspec validate --strict`, findings, archive on the branch.
