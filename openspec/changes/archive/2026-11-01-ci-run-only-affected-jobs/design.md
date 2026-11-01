## Context

See `proposal.md` for the measurements. The constraints that shape the design:

- All six CI jobs and the title check are **required** by the ruleset `main-branch-no-direct-push`,
  with the strict up-to-date policy on. Nothing in this change may leave a required check pending.
- A `pull_request` run checks out the merge commit `refs/pull/<n>/merge`, whose first parent is the
  base branch.
- The house rule for CI checks is a positive control beside every negative assertion: a check
  nobody has seen go red is not evidence (`AGENTS.md`, *RED is two steps*).

## Goals / Non-Goals

**Goals:** a planning-only pull request finishes in well under a minute and a prose-only one in
about three; no pull request can merge with fewer jobs than its change needs; a broken classifier
costs time, never coverage.

**Non-Goals:** the items `proposal.md` lists as out of scope.

## Decisions

### 1. A deny-list of irrelevance, with `full` as the default

**Chosen:** two lists name the files that are *known* not to matter, and anything else means
`full`.

- **Planning and agent files (`none`):** `openspec/**`, `.opencode/**`, `.idea/**`,
  `.codeseeker/**`, `AGENTS.md`, `CLAUDE.md`, `CREDITS.md`, `LICENSE`, `opencode.jsonc`,
  `renovate.json`, `justfile`, `.github/workflows/release.yml`,
  `.github/workflows/renovate.yml`, `.github/workflows/pr-conventional-commits.yml`.
- **Prose documentation (`prose`):** `README.md`, `docs/**` except `docs/reference/**`,
  `codegen/llms-template.md`, `codegen/llms-full-template.md`, `llms.txt`, `llms-full.txt`.

Every pattern is anchored at both ends. `.gitignore` and `.gitattributes` are deliberately absent:
the drift job reads `git status`, which both can change.

**Rejected: an allow-list per job**, such as "`codegen-tests` runs for `codegen/**`". A path nobody
listed would then skip a job, which is the failure this change must not have. With a deny-list, a
path nobody listed runs everything.

### 2. A script beside the workflow, tested by itself in every run

**Chosen:** `.github/ci-scope/classify.sh` reads paths on standard input and prints `none`, `prose`
or `full`. `.github/ci-scope/classify.test.sh` feeds it fixed lists: one positive control per
pattern, the `docs/reference/` exclusion, a rename, an empty list and an unknown path. The `scope`
job runs the test, then the script on the real diff, and writes the scope as a job output. Both
scripts run locally with `bash`.

**Rejected: `dorny/paths-filter`.** A third-party action with write access to the job's token,
for a list of regular expressions; and its filters are allow-lists per output.

**Rejected: the classifier in `build-checks`**, which has Kover and PIT. The `scope` job would need
a JDK and a Gradle start before any other job could begin, adding about a minute to every run,
which is what this change is meant to save.

**Rejected: inline shell in `ci.yml`.** Not testable outside a run, and the self-test would be a
second copy of the rules.

### 3. The job conditions

Every test job declares `needs: scope` and

```yaml
if: ${{ !cancelled() && (needs.scope.result != 'success' || needs.scope.outputs.<job-group> == 'true') }}
```

`!cancelled()` keeps a job running when `scope` failed, where the default `success()` would skip it.
`needs.scope.result != 'success'` is the fail-safe. `scope` sets two outputs, `prose-jobs` and
`full-jobs`, so a condition reads as which group a job belongs to rather than repeating the scope
names. For a `push` event `scope` answers `full` without reading a diff.

**Rejected: `always()`.** It also runs a job after the run was cancelled, which `cancel-in-progress`
would then not stop.

### 4. The diff

`git diff --no-renames --name-only HEAD^1 HEAD` after `actions/checkout` with `fetch-depth: 2`.
`--no-renames` lists a moved file under both paths. A non-zero exit, or no output, answers `full`.

### 5. Concurrency

```yaml
concurrency:
  group: ci-${{ github.event.pull_request.number || github.run_id }}
  cancel-in-progress: ${{ github.event_name == 'pull_request' }}
```

A pull request's runs share a group and the newest wins; each push to `main` is its own group.

## Risks / Trade-offs

- [A new top-level file nobody classified] → it means `full`; the cost is time, not coverage.
- [Someone adds a job and forgets the condition] → it runs on every pull request, as today. Its
  absence from `needs: scope` is visible in review; `kdaisyui-release` says how to add one.
- [A prose file starts to be read by another job] → the requirement's falsifier: a skipped job
  failing on the next `full` run. A `prose` pull request still runs `unit-tests` and the drift job,
  the two that read prose today.
- [The `scope` job lengthens every `full` run] → A6, measured in task 3.4. Its steps are a shallow
  checkout and two short scripts.

## Migration Plan

One pull request. It changes `ci.yml`, so its own run is `full`. Rollback is reverting that commit.
