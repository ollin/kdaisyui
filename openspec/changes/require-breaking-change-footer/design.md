## Context

`pr-conventional-commits.yml` runs as `pull_request_target`, on `opened`, `edited` and
`synchronize`. So it runs from `main`'s copy of the workflow, with a token, and re-runs when only
the body is edited. That is the trigger this check needs: fixing a footer must turn it green
without a push. `:build-checks` already holds one program run as a `JavaExec` task,
`checkGradleProblems`, under 100% Kover and 100% PIT test strength.

## Goals / Non-Goals

**Goals:** a breaking pull request cannot reach `main` without `!`, a last-paragraph footer and an
existing migration heading; the decision is tested like the rest of the repository.

**Non-Goals:** judging whether a migration entry is good; editing a footer automatically; telling
source breaks from binary breaks (A4).

## Decisions

### 1. The decision is Kotlin in `:build-checks`, the workflow only gathers inputs

The workflow writes four files, the title, the body, the API diff and the head's
`docs/upgrading.md`, and runs `:build-checks:checkBreakingChange` on them. The program is a pure
function from those four texts to a list of findings, and prints each one.

*Rejected:* a bash script beside `.github/ci-scope/classify.sh`. That has a precedent and runs in
seconds, but `mcp_Tcr` cannot run its tests and no mutation tool covers it, so a check whose
failure is a release note nobody can repair would have the weakest tests in the repository. The
cost of Kotlin is a JDK and a Gradle start in a job that today takes seconds, about a minute with
the build cache.

*Rejected:* parsing the body with `amannn/action-semantic-pull-request`. It validates the title
only.

### 2. The diff comes from git, not from GitHub's files API

The job checks out `main` (the default for `pull_request_target`), fetches
`refs/pull/<n>/head`, and reads `git diff <base>...<head> -- lib/api` and
`git show <head>:docs/upgrading.md`. Nothing from the head is checked out into the working tree or
executed: the program and the workflow both come from `main`.

*Rejected:* `pulls/<n>/files`. On #471 it returned `lib/api/lib.api` with no patch and stopped at
100 of 264 files (A1); a missing patch would have read as "nothing removed".

### 3. The body reaches the program as a file written from an environment variable

`PR_BODY: ${{ github.event.pull_request.body }}` in `env:`, then `printf '%s' "$PR_BODY" > body.txt`.
Never `${{ }}` inside `run:`, which under `pull_request_target` is a script injection from any
pull request author.

### 4. "Last paragraph" means after the last blank line, trailing whitespace ignored

The Conventional Commits footer runs to the end of the body, so the paragraph that has to start
with `BREAKING CHANGE:` is the last one. A footer followed by a closing sentence fails, because
JReleaser would render that sentence as part of the breaking note (the v0.2.0 release page).

### 5. A heading is named by its text, matched exactly

The footer must contain `How to migrate from <x>` and `docs/upgrading.md` must contain the line
`## How to migrate from <x>`. Exact text, because it is what a reader searches for; no anchor slug.

## Risks / Trade-offs

- [The check is only effective once required] → Oliver adds it to ruleset 14483746; task 5.5.
- [The new job cannot run on its own pull request] → it runs from `main` (A6). The program's tests
  run in `unit-tests` and `mutation-tests` on this PR; the first live run is the next pull request,
  task 5.4.
- [A4 decided the other way] → the check would block a defaulted-parameter addition. The footer
  is cheap to write, so strict is the safe default until Oliver says otherwise.
- [Renovate's major bumps] → a dependency PR touches no API baseline and its title has no `!`, so
  it passes. Measured over A3's range: no Renovate merge removed an API line.

## Migration Plan

Merge, then make the check required. Rollback: remove the job and the requirement.
