## Why

v0.9.0 shipped a breaking change, PR #471 `feat!:`, whose body had no `BREAKING CHANGE:` footer.
The release page marked it breaking and gave the reader no pointer to the migration entry, and a
merge commit cannot be edited after the fact. v0.11.0 repeated it the other way round: PR #492
changed `daisyThemeController`'s signature in `lib/api/lib.api` and added a "How to migrate from
0.10.x" entry, but its title was `feat:`, so the release page did not mark it breaking at all.

The rule exists, in `kdaisyui-release` and as the requirement "A breaking change carries a
migration note before release", which already names its own falsifier: *a release ships a
breaking change without the entry would show that a rule nothing enforces is not a rule.* Two of
the four releases since 0.9.0 refuted the part about the release note. Nothing enforces it, so
this change makes it a check that fails the pull request.

## What Changes

- A new job, `breaking-change-footer`, in `.github/workflows/pr-conventional-commits.yml`. It
  calls a pull request breaking when its title carries `!` **or** its diff removes a line from
  `lib/api/lib.api` or `lib/api/components.api`. For a breaking pull request it requires:
  1. the title carries `!`;
  2. the body's last paragraph starts with `BREAKING CHANGE:`;
  3. that paragraph names a `How to migrate from <x>` heading;
  4. `docs/upgrading.md` at the pull request's head has that heading.
  A pull request that is not breaking passes without any of this.
- The decision is a Kotlin program in `:build-checks`, run as `:build-checks:checkBreakingChange`,
  beside `checkGradleProblems`. So the module's 100% Kover and 100% PIT test strength cover it.
- `kdaisyui-release` moves the footer from "ready to tag" to "ready to merge", where it can still
  be fixed, and the requirement in `api-change-detection` changes from Assumed to Verified.
- The ruleset on `main` gains the job as a required check. Only Oliver can change the ruleset.

**Subtractive option, considered and kept beside the gate, not instead of it:** the JReleaser
changelog template could append a fixed link to `docs/upgrading.md` after every 🚨 line. Nobody
would have to remember anything. It would not catch #492, whose title never said it was breaking,
and it would replace the migration sentence with a generic link. Out of scope here; it is a
finding to decide at archive.

**Out of scope:** changing the ruleset itself (Oliver, in GitHub's UI); the changelog template;
any check of `docs/upgrading.md`'s content beyond the heading's existence.

## Capabilities

### New Capabilities

None.

### Modified Capabilities

- `api-change-detection`: "A breaking change carries a migration note before release" gains the
  enforced half: a breaking pull request cannot merge without its footer.

## Impact

- `.github/workflows/pr-conventional-commits.yml`: one new job.
- `build-checks/`: one program, its tests, one `JavaExec` task.
- `.opencode/skills/kdaisyui-release/SKILL.md`: the footer moves to "ready to merge".
- No library code, no generated file, no published artifact changes.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | GitHub's pull-request files API cannot be the diff source | code | confirmed at proposal - `pull_request_read(get_files)` on #471: `lib/api/lib.api` came back with no `patch` (too large), and the list stops at 100 files of 264 |
| A2 | A merge commit on `main` carries the PR title and body, so the checked body is the body JReleaser reads | code | confirmed at proposal - the last six merges on `origin/main` (#510-#515) each carry their PR's description as body |
| A3 | Since v0.9.0 the rule would have fired on exactly #471 and #492, and on nothing else | code | confirmed at proposal - every merge `v0.9.0~1..origin/main` with `!` or a removed `lib/api` line: those two, both without a footer |
| A4 | A removed line in `lib/api/lib.api` counts as breaking even where Kotlin call sites still compile (a new defaulted parameter, #492: a binary break) | user | open - ask at review |
| A5 | A `pull_request_target` job can read the PR head's diff and `docs/upgrading.md` with `git fetch` and `git diff`/`git show` without executing any code from the PR | code | to check in task 1.1 |
| A6 | The job only runs from `main`'s copy of the workflow, so its first real run is on the pull request after this one merges | code | to check in task 5.4 |
| A7 | No requirement of `api-change-detection` or `ci-job-selection` contradicts this change | code | confirmed at proposal - `api-change-detection` asks for exactly this; `ci-job-selection` lists `pr-conventional-commits.yml` as read by no CI job, which stays true, and classifies `build-checks/` as `full`, which is the safe direction |
