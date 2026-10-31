# documentation-snippets Specification

## Purpose
How code shown in hand-written documentation is kept true: every Kotlin snippet in the prose
documentation is compiled by the build where it stands, or says why it is not, and a snippet that
stops compiling fails the build and names where it is.

## Requirements

### Requirement: Every Kotlin snippet in the prose documentation is compiled

`./gradlew check` SHALL compile every `kotlin` code block in `README.md`, `docs/how-to.md`,
`docs/explanation.md` and `docs/tutorials/*.md` against the library, unless the block is marked as
not compiled.

**Verified** 2026-10-31 by `verify-doc-snippets` (task 1.1 and section 3): of 71 fences, 43
compile in the default context or a top-level file, and only 5 of those need `in <Receiver>`.
The other 28 are migration notes, build scripts, signatures and a pattern, each marked `skip`
with its reason.

#### Scenario: A documented function is renamed

- **WHEN** a function a snippet calls is renamed in the library and the snippet is not updated
- **THEN** `./gradlew check` fails

#### Scenario: A change that touches no snippet's API

- **WHEN** a commit changes only code that no snippet uses
- **THEN** the snippets still compile and `check` passes

### Requirement: A compile failure names the document and the line

When a snippet does not compile, the build failure SHALL name the document and the line of the
snippet's opening fence.

#### Scenario: A snippet passes a removed parameter

- **WHEN** a snippet in `docs/how-to.md` passes a parameter the function no longer has
- **THEN** the failure names `docs/how-to.md` and the line of that snippet's fence

### Requirement: A snippet that is not compiled says why

A snippet SHALL be excluded from compilation only by a marker directly above its fence that
carries a reason. A marker without a reason, or with a directive the build does not know, SHALL
fail the build.

#### Scenario: A migration note shows the old API

- **WHEN** a snippet shows a 0.7.x call to explain a migration
- **AND** a marker above it says it is skipped and why
- **THEN** the build does not compile it and passes

#### Scenario: A skip without a reason

- **WHEN** a marker says a snippet is skipped and gives no reason
- **THEN** the build fails and names the document and the line
