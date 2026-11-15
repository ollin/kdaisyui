## MODIFIED Requirements

### Requirement: A breaking change carries a migration note before release
A public API change identified as breaking SHALL have a **How to migrate** entry in
`docs/upgrading.md` before the release that ships it, and the release documentation SHALL treat the API check and
that entry as part of what "ready to tag" means.

**Verified:** `README.md` already carries a "How to migrate" section for 0.2.0, written after the
fact, which is the shape the rule generalises. **Refuted 2026-11-15:** the assumption that this
could stay a documented rule rather than an automated one. Its falsifier was met twice: v0.9.0
(#471) shipped without the release-note footer, and v0.11.0 (#492) shipped a changed signature
under a title that did not say it was breaking. "A breaking pull request cannot merge without its
footer" below is the automated half.

The entries lived in `README.md` until `move-migration-guides` (2026-11-01) moved them to
`docs/upgrading.md`, which README links.

#### Scenario: A reader of a breaking release can act on it
- **WHEN** a release removes or changes a public declaration
- **THEN** `docs/upgrading.md` explains what to change at the call site
- **AND** the entry exists before the tag is pushed, not after

## ADDED Requirements

### Requirement: A breaking pull request cannot merge without its footer

A required check on every pull request to `main` SHALL fail when the pull request is breaking and
its title, body or `docs/upgrading.md` does not say so. A pull request is breaking when its title
carries `!` after the type, or when its diff removes a line from `lib/api/lib.api` or
`lib/api/components.api`. A breaking pull request SHALL have a title with `!`, a body whose last
paragraph starts with `BREAKING CHANGE:` and names a `How to migrate from <x>` heading, and that
heading in `docs/upgrading.md` at the pull request's head.

**Verified** why the body is what counts, measured 2026-11-15: the last six merge commits on
`main` carry their pull request's description as body, and JReleaser renders the merge commit's
breaking footer on the release page (`build.gradle.kts`, `conventionalCommitBreakingChangeContent`).

**Verified** that it would have caught both misses since v0.9.0 and nothing else, measured the same
day over every merge from `v0.9.0~1` to `origin/main`: #471 (`!`, 396 removed API lines, no
footer) and #492 (no `!`, 5 removed API lines, no footer).

**Assumed** that a removed API line is breaking even when Kotlin call sites still compile, such as
a new parameter with a default (#492). *Wrong if:* Oliver decides that only source-breaking
changes count; then the check needs a way to tell the two apart before it can be required.

#### Scenario: A breaking title without a footer
- **WHEN** a pull request titled `feat!: …` has a body that does not end with `BREAKING CHANGE:`
- **THEN** the check fails and names the missing footer

#### Scenario: A removed API line under a non-breaking title
- **WHEN** a pull request titled `feat: …` removes a line from `lib/api/lib.api`
- **THEN** the check fails and names the missing `!`

#### Scenario: A footer naming a heading that does not exist
- **WHEN** the footer names `How to migrate from 0.12.x` and `docs/upgrading.md` has no such heading
- **THEN** the check fails and names the heading

#### Scenario: A complete breaking pull request
- **WHEN** a pull request titled `feat!: …` ends its body with `BREAKING CHANGE: … See "How to migrate from 0.12.x" in docs/upgrading.md.` and that heading exists
- **THEN** the check passes

#### Scenario: A pull request that breaks nothing
- **WHEN** the title has no `!` and no line of either API baseline is removed
- **THEN** the check passes, whatever the body says

#### Scenario: A footer that is not last
- **WHEN** the `BREAKING CHANGE:` paragraph is followed by another paragraph
- **THEN** the check fails, because that paragraph would be rendered as part of the breaking note
