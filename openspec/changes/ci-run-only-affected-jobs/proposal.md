## Why

Every pull request runs all six CI jobs, whatever it touches. PR #438 changed documentation, a
build script and one message string, and its run took 5 min 6 s, set by `e2e-tests`. Measured
2026-11-01 on that run:

| Job | Duration |
|---|---|
| e2e-tests | 5:06 |
| mutation-tests | 3:16 |
| unit-tests | 2:43 |
| generated-sources-drift | 2:28 |
| api-baseline | 2:27 |
| codegen-tests | 0:12 |

Of the 99 pull requests merged since 2026-09-01, **19 touched only planning and agent files**
(`openspec/`, `.opencode/`, `AGENTS.md`, Renovate's own workflow) and **3 more touched only prose
documentation** besides those. Measured by classifying each merge's diff against its first parent.
For the 19, no job can fail on what they change. For the 3, only two jobs read what they change.
All of them waited the full five minutes.

## What Changes

- **A first job, `scope`, classifies the pull request's changed files** into one of three scopes
  and every other job reads it:

  | Scope | When every changed file is | Jobs that run |
  |---|---|---|
  | `none` | planning and agent files (list in `design.md`) | none |
  | `prose` | those, or prose documentation: `README.md`, `docs/**` except `docs/reference/**`, the two `llms` templates and their outputs | `unit-tests` (compiles the snippets), `generated-sources-drift` (runs the `llms.txt` gate) |
  | `full` | anything else | all six, as today |

- **Skipped jobs are skipped by a job condition, never by a workflow path filter.** GitHub reports
  a job skipped by its condition as successful for a required check; a workflow skipped by a path
  filter leaves its checks pending and blocks the merge. The ruleset stays unchanged.
- **The classification fails safe.** A job runs whenever `scope` did not succeed, and `scope`
  answers `full` for an empty diff, a diff it could not compute, or any path it does not know. It
  lists renames as a deletion and an addition, so moving a source file into `docs/` is not prose.
- **`scope` tests itself first**, on fixed paths with known answers, one positive control per rule,
  before it classifies the real diff.
- **Pushes to `main` stay unfiltered**, and **a newer push to the same pull request cancels the
  older run**.
- `kdaisyui-release`'s CI section says which jobs run when, and how to add a job.

**Subtractive option considered:** drop the `push: main` trigger, since the strict up-to-date rule
means the merged tree was already tested as the pull request's merge commit. Rejected for this
change: nobody waits on that run, and it is the only record of `main` itself being green.

## Deliberately out of scope

- **A `codegen-only` scope** that would skip the Kotlin jobs for a change under `codegen/` alone.
  Measured: 2 of 99 pull requests would have qualified, and proving that no Gradle task reads
  `codegen/` is more work than they would save.
- **Narrowing `unit-tests` to the snippet compile for a `prose` change.** Its wall time would not
  drop, because `generated-sources-drift` runs as long as it does.
- **Turning off the ruleset's strict up-to-date policy.** It forces a rerun of every open pull
  request after each merge, and GitHub's *Update branch* button answers it with the merge-main-into-
  topic commit `AGENTS.md` calls an anti-pattern. Turning it off risks two pull requests that pass
  alone and fail together, so it is a decision for Oliver, not a safe improvement.
- **Gradle's build cache in CI** (`gradle/actions/setup-gradle`). It would speed every job, but a
  task whose inputs are under-declared would then be restored stale. `generateReferenceDocs` lacked
  README as an input until `llms-txt-index`. That needs its own audit.
- **The mutation job for `codegen/`** on `chore/mutation-test-codegen`. When it lands it gets the
  `full` condition like every other test job.

**Not breaking.** No published artifact, no ruleset and no required check changes.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | A job skipped by its `if` condition satisfies a required status check | reading | verified at proposal: GitHub docs, *Troubleshooting required status checks*, table *Handling skipped but required checks*: "A job is skipped by a conditional · The job reports 'Success'"; and *Using conditions to control job execution*: "It will not prevent a pull request from merging, even if it is a required check" |
| A2 | A job skipped because a job it needs failed may also pass a required check, so a broken `scope` must make every job run, not skip | reading | verified at proposal: same table, "A job depends on a failed job · The dependent job is skipped and may not block merging · Use `always()` with `needs`" |
| A3 | No CI job reads `openspec/`, `.opencode/`, `AGENTS.md`, `CLAUDE.md`, `CREDITS.md`, `LICENSE`, `opencode.jsonc`, `renovate.json`, `justfile`, `.idea/`, `.codeseeker/` or the three other workflow files | grep | verified at proposal: every hit in build scripts, Kotlin and TypeScript is a comment |
| A4 | Prose documentation is read only by the snippet compile (in `check`, so `unit-tests`) and by the `llms.txt` gate (in `generateReferenceDocs`, so `generated-sources-drift`) | grep | verified at proposal: `doc-snippets/build.gradle.kts` and `codegen/src/index-docs.ts`; the `doc-snippets` and `codegen` tests build their own fixtures. One more check in task 1.1 |
| A5 | On a `pull_request` run, the checked-out merge commit's first parent is the base, so `git diff HEAD^1 HEAD` is exactly what the pull request changes | probe | open, task 1.1 |
| A6 | The `scope` job adds at most about 15 s to the critical path of a `full` run | measurement | open, task 3.4 |

## Capabilities

### New Capabilities

- `ci-job-selection`: which CI jobs run for which pull request, how a skipped job still satisfies
  the ruleset, and why a classification failure runs everything.

### Modified Capabilities

<!-- none -->

## Impact

- **Edited**: `.github/workflows/ci.yml`: a `scope` job, a condition and `needs` on each of the six
  jobs, and a `concurrency` group.
- **New**: `.github/ci-scope/classify.sh` and `.github/ci-scope/classify.test.sh`.
- **Edited**: `.opencode/skills/kdaisyui-release/SKILL.md`, the CI section.
- No change to the ruleset, the required checks, any Gradle build or any published artifact.
