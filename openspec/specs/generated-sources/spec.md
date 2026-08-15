# generated-sources Specification

## Purpose
How generated Kotlin is produced, stored and kept honest: where it lives, that it is
committed and readable without a build, that regenerating it is explicit rather than
automatic, and that committed output which no longer matches its inputs is rejected.

## Requirements

### Requirement: Generated sources are committed and readable

The generated Kotlin components, icons, component tests **and icon render tests** SHALL live
in a committed source root inside the repository, not in build output. A fresh clone SHALL
show the full public API of the library as ordinary readable source.

**Verified**: measured 2026-08-15 after the merge — 457 files under `lib/generated/`: 66
components, 324 icon functions, 66 component tests, 66 branch-coverage tests and one icon
render-coverage test. `:lib:test` runs 1488 tests from them and `koverVerify` passes at 100%
line and branch.

The fourth category arrived with `rejoin-main`:
`codegen/src/test-generator-heroicons.js` produces `HeroIconsGeneratedTest.kt`. Without
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

### Requirement: The build does not run the generators

Compiling and testing the library SHALL NOT invoke the code generators. `compileKotlin`,
`compileTestKotlin` and the test tasks SHALL succeed with no Node.js, no npm and no
initialised git submodules. Regeneration SHALL be an explicit task a person runs.

**Verified**: `lib/build.gradle.kts:194-201` wires `compileKotlin` to `generateComponents`
and `generateHeroicons`, and `compileTestKotlin` to `generateComponentTests`; those tasks
shell out to `npm install --silent && node …` (`:156`, `:171`, `:187`) and depend on the
submodule checkout tasks (`:152`, `:167`, `:183`).

#### Scenario: Building without the toolchain the generators need

- **WHEN** `./gradlew :lib:test` runs in a clone with uninitialised submodules and no Node.js
- **THEN** the build succeeds
- **AND** no generator task executes

#### Scenario: Regenerating on purpose

- **WHEN** a person runs the regeneration task after changing `daisyui.version`
- **THEN** the generators run and rewrite the committed source root
- **AND** the resulting differences are visible in `git status`

### Requirement: Regeneration is deterministic

Running the generators twice against identical inputs SHALL produce byte-identical output,
independently of the machine, the filesystem's directory order, the active locale and the
Node.js minor version.

**Verified** (measured 2026-08-14, tasks 1.1-1.4). All 450 generated files, compared by
aggregate SHA-256:

- two consecutive runs on one machine — identical;
- `LANG=C` and `LANG=de_DE.UTF-8` against the ambient `en_US.UTF-8` — identical;
- a clean `ubuntu-latest` runner against the local baseline `0b7ddefd…` — identical.

The locale test was not vacuous: the emitted import order is demonstrably ICU-collated
rather than byte-ordered (`addClassNames` before `HtmlId`, `button` before `BUTTON` — byte
order reverses both), so `localeCompare` really does decide it. It simply decides it
identically everywhere tried, because the identifiers are pure ASCII.

Contributing factors, both checked rather than assumed: every directory read is sorted
before use (`codegen/src/parser/frontmatter.js:218-223`, `codegen/src/index-heroicons.js:47`),
no timestamps or absolute paths are emitted, and `codegen/package-lock.json` is committed
(since `c7b85cc`) so npm resolution cannot drift.

#### Scenario: Regenerating twice on one machine

- **WHEN** the generators run twice against the same submodule state
- **THEN** the two outputs are byte-identical

#### Scenario: Regenerating under a different locale

- **WHEN** the generators run under two different `LANG` values against the same inputs
- **THEN** the two outputs are byte-identical

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
