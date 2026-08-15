## MODIFIED Requirements

### Requirement: Generated sources are committed and readable

The generated Kotlin components, icons, component tests **and icon render tests** SHALL live
in a committed source root inside the repository, not in build output. A fresh clone SHALL
show the full public API of the library as ordinary readable source.

**Verified**: measured 2026-08-15 after the merge — 457 files under `lib/generated/`: 66
components, 324 icon functions, 66 component tests, 66 branch-coverage tests and one icon
render-coverage test. `:lib:test` runs 1488 tests from them and `koverVerify` passes at 100%
line and branch.

The fourth category is what this change adds: `main` brought
`codegen/src/test-generator-heroicons.js`, which produces `HeroIconsGeneratedTest.kt`. Without
it the aggregated coverage gate cannot be met, so the categories and the gate are not
independent.

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

### Requirement: Committed output that no longer matches its inputs is rejected

CI SHALL regenerate from the committed inputs and fail when the result differs from what is
committed. This replaces the guarantee the build-time dependency used to provide. The
regeneration SHALL cover **every** generator, including the icon render tests.

**Verified, in part**: the drift job was observed failing on a hand-edited generated file
(PR #231, 2026-08-15) while the test jobs passed on the same commit. What is still Assumed is
durability — one green run proves the job works, not that it keeps working.

*Wrong if:* the drift job reports a diff on a run where no input changed.

A generator that is missing from the drift job is worse than one that is absent, because the
committed tree then drifts silently. That failure mode is not hypothetical: a stale input path
in `test-generator.js` removed 66 coverage files and 8% line coverage while every build stayed
green, and it was caught by regenerating rather than by any test.

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
