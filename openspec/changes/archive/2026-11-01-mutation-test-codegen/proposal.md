## Why

`AGENTS.md` makes mutation testing the proof that a test can fail, and the four Kotlin modules
meet it at 100 % test strength. `codegen/`, about 5,000 lines of TypeScript and the most-changed
directory after `openspec/` (127 of the commits since 2026-10-01), has no mutation tool. Every
merge request touching it has said so instead, most recently `llms-txt-index` (finding F4), whose
own new file `llms-index.ts` turned out to leave 7 of 146 mutants alive.

Measured 2026-11-01 with StrykerJS 10.1.1 and its `command` runner over `codegen/src`, entry
points excluded: **3,530 mutants in 418 s, 1,544 killed, 1,986 survived, a score of 43.7 %**. Most
survivors sit in code the unit tests do not run at all: `test-generator.ts` (1,024),
`generator-new.ts` (199), `generator-heroicons.ts` and `svg-heroicons.ts` (177 together). The
generators are proven end to end by the drift job, not by unit tests.

So a 100 % gate over all of `codegen/` would fail on day one, and a gate at 43 % would let new
code with survivors through. What the house rule asks for is narrower: **a surviving mutant in
new or changed code is a finding.**

## What Changes

- **StrykerJS joins `codegen/` as its first `devDependency`**, pinned exactly, with the `command`
  runner on `node --test test/`. `installCodegenDeps` installs with `--omit=dev`, so `just
  generate` and the drift job do not download it.
- **CI mutates only the lines a pull request changes in `codegen/src/`, and requires 100 %.** A
  new step in the `codegen-tests` job computes the changed line ranges against the base and passes
  them to Stryker; any survivor fails the job. No changed source line means the step does nothing.
  `codegen-tests` is already a required check and already in the `full` scope, so the ruleset does
  not change.
- **`npm run mutate` mutates all of `codegen/src` locally** and reports the score. It gates
  nothing, so the 1,986 survivors are visible without blocking anyone.
- **The 7 survivors in `llms-index.ts` are killed**, because that file is the last change that
  shipped with the gap.
- `AGENTS.md`'s test-quality table, `kdaisyui-codegen` and `kdaisyui-testing` name the new gate.

**Subtractive option considered:** no tool, and keep writing "no mutation tool" in each merge
request. Rejected: Oliver chose the tool on 2026-11-01, and the 7 survivors in the newest file show
what the sentence hides.

## Deliberately out of scope

- **Raising the baseline.** Killing 1,986 survivors, or deciding which are generator code that only
  the drift job should prove, is file-by-file work for later changes.
- **Per-test coverage** through the `tap-runner`. It would be faster, but the house rule names the
  `command` runner; a full run takes 7 minutes and is not part of CI.
- **Mutating `codegen/test/` or the `index-*.ts` entry points.** Tests are not mutated, and the
  entry points run only as scripts, which no unit test imports.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | Stryker's `command` runner works on TypeScript that Node runs directly, with no build step | probe | verified 2026-11-01: 146 mutants of `llms-index.ts` in 19 s, then the full run above |
| A2 | Stryker accepts line ranges in `--mutate` and instruments only those lines | probe | verified 2026-11-01: `--mutate src/llms-index.ts:60-70` instrumented 18 mutants and ran in 3 s |
| A3 | On a `pull_request` run, `git diff -U0 HEAD^1 HEAD` names exactly the pull request's changed lines | probe | verified for file names on PR #439 (`ci-run-only-affected-jobs`); the hunk headers come from the same diff |
| A4 | A typical pull request's changed lines mutate in under a minute in CI | measurement | refuted, then fixed, in task 4.2: 54 mutants took 77 s in CI with one process per test file; in one process 81 mutants took 13 s and the job 27 s (finding F4) |
| A5 | `npm ci --omit=dev` leaves the generators and the codegen tests working | measurement | verified in task 2.2: 6 packages instead of 113, `:lib:testCodegen` 200 green, `just generate` without a diff |

## Capabilities

### New Capabilities

<!-- none -->

### Modified Capabilities

- `mutation-testing`: a requirement for changed lines in `codegen/src/` at 100 %, beside the PIT
  gate for the Kotlin modules.

## Impact

- **Edited**: `codegen/package.json` and `codegen/package-lock.json` (one `devDependency`, the
  `mutate` script), `lib/build.gradle.kts` (`installCodegenDeps` with `--omit=dev`),
  `.github/workflows/ci.yml` (`codegen-tests`: history depth and one step).
- **New**: `codegen/stryker.config.json`, `codegen/src/mutation-scope.ts` and its test (the
  changed-line ranges), `.gitignore` entries for Stryker's report and sandbox.
- **Edited**: `codegen/src/llms-index.ts` tests, `AGENTS.md`, `kdaisyui-codegen`, `kdaisyui-testing`.
- No change to the ruleset, any published artifact, or `./gradlew check`.
