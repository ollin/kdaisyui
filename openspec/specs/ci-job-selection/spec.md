# ci-job-selection Specification

## Purpose
Which CI jobs a pull request runs. A job runs when what the pull request changes can make it fail,
a skipped job still satisfies the ruleset's required checks, and any doubt about the classification
runs every job.

## Requirements

### Requirement: A pull request runs only the jobs its changes can affect

CI SHALL classify a pull request's changed files into one scope: `none` when every file is a
planning or agent file, `prose` when every file is one of those or prose documentation, and `full`
otherwise. Under `none` no test job SHALL run. Under `prose` exactly `unit-tests` and
`generated-sources-drift` SHALL run. Under `full` all test jobs SHALL run.

**Verified** that the two `prose` jobs are the only readers of prose documentation: the snippet
compile in `doc-snippets/build.gradle.kts` runs in `check`, and the `llms.txt` coverage gate in
`codegen/src/index-docs.ts` runs in `generateReferenceDocs`; every other hit for `README` or
`docs/` in a build script, Kotlin or TypeScript source is a comment or a test fixture (grep,
2026-11-01).

**Verified** that the planning and agent files are read by no test job: every hit in build
scripts, Kotlin and TypeScript is a comment (grep, 2026-11-01). The one job that reads one,
`openspec-config`, parses `openspec/config.yaml` and is not selected by the classification: it
runs on every pull request.

**Verified** end to end on 2026-11-01 with probe PRs: planning only ran no test job (#441), prose
only ran exactly the two (#443), and a broken snippet in a prose-only change still failed
`unit-tests` (#444).

The test jobs are five since `subtract-build-overhead` removed `api-baseline`, whose check
`unit-tests` runs as part of `check`.

*Wrong if:* a job that a `none` or `prose` pull request skipped fails on the next `full` run for a
reason that pull request introduced.

#### Scenario: A change to planning files only

- **WHEN** a pull request changes only files under `openspec/` and `.opencode/`
- **THEN** no test job runs
- **AND** every required check reports success

#### Scenario: A change to the documentation

- **WHEN** a pull request changes `README.md` and a file under `docs/tutorials/`
- **THEN** `unit-tests` and `generated-sources-drift` run
- **AND** `e2e-tests`, `mutation-tests` and `codegen-tests` are skipped

#### Scenario: A change to a generated reference page

- **WHEN** a pull request changes a file under `docs/reference/`
- **THEN** all test jobs run

#### Scenario: A change to source code

- **WHEN** a pull request changes any file outside the planning, agent and prose lists
- **THEN** all test jobs run

### Requirement: A skipped job satisfies its required check

A job SHALL be skipped only by its own job condition, never by a workflow-level path filter, so
that the ruleset's required checks report success instead of staying pending.

**Verified**: GitHub docs, *Troubleshooting required status checks*, read 2026-11-01, table
*Handling skipped but required checks*: a workflow skipped by path filtering leaves its checks
"Pending" and blocks merging; a job skipped by a conditional "reports 'Success'".

#### Scenario: A planning-only pull request is mergeable

- **WHEN** a pull request changes only planning files and its run completes
- **THEN** the pull request is not blocked by a pending required check

### Requirement: The classification fails safe

Every test job SHALL run when the classification did not succeed. The classification SHALL answer
`full` when the diff is empty, cannot be computed, or names a path no rule covers. A renamed file
SHALL count as both its old and its new path. The classifier SHALL check its own rules against
fixed paths with known answers before it classifies a pull request, and SHALL fail when one of
them answers wrong.

**Verified**: GitHub docs, same table: "A job depends on a failed job · The dependent job is
skipped and may not block merging". Without this requirement a broken classifier would let a pull
request merge with no test run at all. Observed on probe PR #442: the self-test failed, and all
six jobs ran.

**Verified** 2026-11-01 on the probe PR #439: a `pull_request` run checks out the merge commit,
whose first parent is `main`, and `git diff --no-renames --name-only HEAD^1 HEAD` listed exactly
the two files the pull request changed.

#### Scenario: The classifier fails

- **WHEN** the classification job fails or is cancelled before it answers
- **THEN** every test job runs

#### Scenario: A source file moves into the documentation

- **WHEN** a pull request renames `lib/…/X.kt` to `docs/X.kt`
- **THEN** the scope is `full`

#### Scenario: A classifier rule is broken

- **WHEN** a rule change makes one of the classifier's fixed paths answer wrong
- **THEN** the classification job fails
- **AND** every test job runs

### Requirement: Pushes to main and superseded runs

A push to `main` SHALL run every job, unclassified. A newer push to the same pull request SHALL
cancel that pull request's older, still running CI run.

**Verified** for pull requests on 2026-11-01: a second push to probe PR #443 cancelled its first run.
**Assumed** for `main`: each push there is its own group, keyed on the run id, so a merge cannot
cancel the previous merge's run. *Wrong if:* a run on `main` ends `cancelled`.

#### Scenario: Two pushes to one pull request

- **WHEN** a second commit is pushed to a pull request while its first run is still going
- **THEN** the first run is cancelled and the second runs

#### Scenario: A merge to main

- **WHEN** a pull request is merged
- **THEN** the run on `main` runs all five test jobs
