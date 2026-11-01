## MODIFIED Requirements

### Requirement: Every Kotlin snippet in the prose documentation is compiled

`./gradlew check` SHALL compile every `kotlin` code block in `README.md`, `docs/how-to.md`,
`docs/explanation.md`, `docs/upgrading.md` and `docs/tutorials/*.md` against the library, unless the block is marked as
not compiled.

**Verified** 2026-10-31 by `verify-doc-snippets` (task 1.1 and section 3): of 71 fences, 43
compile in the default context or a top-level file, and only 5 of those need `in <Receiver>`.
The other 28 are migration notes, build scripts, signatures and a pattern, each marked `skip`
with its reason.

**Verified** 2026-11-01 by `move-migration-guides`: the migration notes moved from `README.md` to
`docs/upgrading.md` with their `skip` markers, and `./gradlew :doc-snippets:check` passed with the
new document listed.

#### Scenario: A documented function is renamed

- **WHEN** a function a snippet calls is renamed in the library and the snippet is not updated
- **THEN** `./gradlew check` fails

#### Scenario: A change that touches no snippet's API

- **WHEN** a commit changes only code that no snippet uses
- **THEN** the snippets still compile and `check` passes
