# Tasks

Ordered by uncertainty: section 1 checks the two assumptions a reading cannot settle. The
classifier is shell, which `mcp_Tcr` cannot run, so its TCR cycle is run by hand with
`bash .github/ci-scope/classify.test.sh` and said out loud.

**Estimate, frozen 2026-11-01 at proposal, raw and uncorrected:** 140 minutes.

| Section | Minutes |
|---|---|
| 1. Open assumptions | 20 |
| 2. The classifier, under a hand-run TCR | 40 |
| 3. The workflow | 30 |
| 4. Proof on real pull requests | 30, plus CI waits logged as `machine` |
| 5. Documents, gate and adoption | 20 |

## 1. Open assumptions

- [x] 1.1 `. d` (A4, A5) On a throwaway draft pull request, print `git diff --no-renames
  --name-only HEAD^1 HEAD` in a one-step workflow, change one file, and confirm the list is exactly
  that file. Grep once more for any reader of `README.md` or `docs/` in `e2e-tests/`,
  `example-app/` and `gradle-plugin/`. Close the pull request, delete its branch.

## 2. The classifier, under a hand-run TCR

- [x] 2.1 `. F (internal)` `classify.sh` answers `full` for an empty list and for an unknown path,
  with its test.
- [x] 2.2 `. F (internal)` It answers `none` when every path is a planning or agent file: one
  positive control per pattern.
- [x] 2.3 `. F (internal)` It answers `prose` for prose documentation: one positive control per
  pattern, and `full` for `docs/reference/…`.
- [x] 2.4 `. F (internal)` A mixed list takes the widest scope: planning plus prose is `prose`,
  anything plus a source path is `full`.

Section 2 took six commits instead of four: one hand-run revert, and a defect the hand-made
mutants found in the test harness itself (`findings.md`, F1 and F2).

## 3. The workflow

- [x] 3.1 `! F (internal)` A `scope` job: shallow checkout with `fetch-depth: 2`, the self-test,
  the classification, the two outputs; `full` for a `push`.
- [x] 3.2 `! F (internal)` Each of the six jobs gets `needs: scope` and the fail-safe condition of
  its group.
- [x] 3.3 `! F (internal)` The `concurrency` group.
- [ ] 3.4 `. d` Record the `scope` job's duration and the critical path of this pull request's own
  `full` run (A6).

## 4. Proof on real pull requests

Each one a draft pull request from a throwaway branch, closed and deleted afterwards. Record the
jobs that ran, their durations, and whether the pull request showed as mergeable.

- [ ] 4.1 Only `openspec/` changed: no test job runs, every required check is green.
- [ ] 4.2 Only `docs/how-to.md` changed: `unit-tests` and `generated-sources-drift` run.
- [ ] 4.3 Classifier forced to exit 1: every test job runs.
- [ ] 4.4 Two pushes in quick succession to the pull request of 4.2: the first run is cancelled.
- [ ] 4.5 A broken snippet in `docs/how-to.md`: `unit-tests` runs and fails, so the `prose` scope
  still catches what it is meant to.

## 5. Documents, gate and adoption

- [ ] 5.1 `^ d` `kdaisyui-release`, CI section: the three scopes, the fail-safe, and how to add a
  job.
- [ ] 5.2 `openspec validate ci-run-only-affected-jobs --strict`; findings and evaluation; archive
  on the branch.
