## MODIFIED Requirements

### Requirement: Generated sources are committed and readable

The generated Kotlin components, icons, component tests, icon render tests **and the
per-component reference documentation** SHALL live in a committed source root inside the
repository, not in build output. A fresh clone SHALL show the full public API of the library as
ordinary readable source, and its reference documentation as ordinary readable Markdown.

Generated Markdown SHALL carry the same do-not-edit attribution the generated Kotlin carries,
naming the input it was produced from.

**Verified**: measured 2026-08-15 after the merge — 457 files under `lib/generated/`: 66
components, 324 icon functions, 66 component tests, 66 branch-coverage tests and one icon
render-coverage test. `:lib:test` runs 1488 tests from them and `koverVerify` passes at 100%
line and branch.

The fourth category arrived with `rejoin-main`:
`codegen/src/test-generator-heroicons.ts` produces `HeroIconsGeneratedTest.kt`. Without
it the aggregated coverage gate cannot be met, so the categories and the gate are not
independent.

**Assumed** — that the reference pages contain nothing a reader needs which the codegen does
not already hold. A survey on 2026-09-12 found every element of all 66 pages derivable from the
parsed DaisyUI frontmatter and the classified component model: heading, DaisyUI docs link,
description, rendered tag, and one signature block per generated function.
*Wrong if:* regenerating a page loses information a reader relied on — the check is a diff of
all 66 generated pages against the hand-written ones, where every difference must be either a
correction or a deliberate template decision, never a loss.

#### Scenario: Reading a component signature without building

- **WHEN** a reader clones the repository and opens the committed source root
- **THEN** the parameter list of every generated component is readable
- **AND** no Gradle build, npm install or submodule checkout has been run

#### Scenario: Generated files are marked as generated

- **WHEN** the committed generated files are viewed in a pull request on GitHub
- **THEN** they are attributed as generated and collapsed by default
- **AND** they do not count toward the repository's language statistics

#### Scenario: Every generated category carries its tests

- **WHEN** the generators run
- **THEN** components, component tests, branch-coverage tests and icon render tests are all
  produced into the committed root
- **AND** the aggregated coverage gate passes at 100% line and branch

#### Scenario: A reference page states the element a component renders

- **WHEN** a generated component renders an HTML element other than `<div>`
- **THEN** its reference page names that element
- **AND** the lambda receiver type in the documented signature is the one the generated Kotlin
  declares

#### Scenario: A component gains a new function

- **WHEN** a DaisyUI release adds a part that the codegen turns into a new generated function
- **THEN** that function appears in the component's reference page without anyone editing it

### Requirement: Committed output that no longer matches its inputs is rejected

CI SHALL regenerate from the committed inputs and fail when the result differs from what is
committed. This replaces the guarantee the build-time dependency used to provide. The
regeneration SHALL cover **every** generator, including the icon render tests **and the
reference documentation**.

**Verified, in part**: the drift job was observed failing on a hand-edited generated file
(PR #231, 2026-08-15) while the test jobs passed on the same commit. What is still Assumed is
durability — one green run proves the job works, not that it keeps working.

*Wrong if:* the drift job reports a diff on a run where no input changed.

A generator that is missing from the drift job is worse than one that is absent, because the
committed tree then drifts silently. That failure mode is not hypothetical: a stale input path
in `test-generator.ts` removed 66 coverage files and 8% line coverage while every build stayed
green, and it was caught by regenerating rather than by any test.

Documentation left outside the job is the same hazard with a slower fuse, and it has already
fired: `docs/reference/megamenu.md` documented a `DIV` lambda receiver for five days after the
element became a `<span>`, and shipped wrong in v0.4.0 on 2026-09-12.

#### Scenario: Someone edits generated output by hand

- **WHEN** a commit modifies a committed generated file without changing any generator input
- **THEN** the drift check fails
- **AND** the failure names the files that differ

#### Scenario: Someone bumps DaisyUI without regenerating

- **WHEN** a commit raises the `daisyui` version but leaves the generated sources untouched
- **THEN** the drift check fails

#### Scenario: An ordinary change touching no generated input

- **WHEN** a commit changes only hand-written Kotlin
- **THEN** the drift check passes

#### Scenario: A generator input directory is missing

- **WHEN** a generator's declared input directory does not exist
- **THEN** the build fails
- **AND** it does not silently produce a partial set of generated files

#### Scenario: A component changes its rendered element without its page being regenerated

- **WHEN** a commit changes what a component renders but leaves `docs/reference/` untouched
- **THEN** the drift check fails
- **AND** it names the reference page that differs
