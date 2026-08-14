## Purpose

How generated Kotlin is produced, stored and kept honest: where it lives, that it is
committed and readable without a build, that regenerating it is explicit rather than
automatic, and that committed output which no longer matches its inputs is rejected.

## ADDED Requirements

### Requirement: Generated sources are committed and readable

The generated Kotlin components, icons and component tests SHALL live in a committed source
root inside the repository, not in build output. A fresh clone SHALL show the full public
API of the library as ordinary readable source.

**Verified**: measured 2026-08-14 — 63 component files and 63 test files exist only under
`lib/build/generated/sources/kotlin/{main,test}/io/github/ollin/kdaisyui/components/`, which
`.gitignore:6` (`build`) excludes. The icons are claimed to be 324 files and were not
re-counted.

#### Scenario: Reading a component signature without building

- **WHEN** a reader clones the repository and opens the committed source root
- **THEN** the parameter list of every generated component is readable
- **AND** no Gradle build, npm install or submodule checkout has been run

#### Scenario: Generated files are marked as generated

- **WHEN** the committed generated files are viewed in a pull request on GitHub
- **THEN** they are attributed as generated and collapsed by default
- **AND** they do not count toward the repository's language statistics

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

**Verified, in part** (measured 2026-08-14, task 1.1-1.3): regenerating all 450 files twice
on one machine produced a byte-identical result, as did regenerating under `LANG=C` and
`LANG=de_DE.UTF-8`. The locale dimension is settled, and the test was not vacuous — the
emitted import order is demonstrably ICU-collated rather than byte-ordered
(`addClassNames` before `HtmlId`; `button` before `BUTTON`), so `localeCompare` really is
deciding the order; it simply decides it the same way in every locale tried, because the
identifiers are pure ASCII.

**Assumed, for the remainder**: that the same holds across machines, ICU builds and Node
versions. Nothing here has been checked on a second machine.

*Wrong if:* the CI runner produces a diff against the local baseline (task 1.4). The residual
risk is a Node built against a different ICU version, or a small-icu build, collating these
ASCII identifiers differently — not the locale, which is now measured.

#### Scenario: Regenerating twice on one machine

- **WHEN** the generators run twice against the same submodule state
- **THEN** the two outputs are byte-identical

#### Scenario: Regenerating under a different locale

- **WHEN** the generators run under two different `LANG` values against the same inputs
- **THEN** the two outputs are byte-identical

### Requirement: Committed output that no longer matches its inputs is rejected

CI SHALL regenerate from the committed inputs and fail when the result differs from what is
committed. This replaces the guarantee the build-time dependency used to provide.

**Assumed**: this depends entirely on the determinism requirement above. If output is not
byte-identical across machines, the check fails for reasons no author caused, and a check
that cries wolf is one people learn to bypass.

*Wrong if:* the drift job reports a diff on a run where no input changed.

#### Scenario: Someone edits generated output by hand

- **WHEN** a commit modifies a committed generated file without changing any generator input
- **THEN** the drift check fails
- **AND** the failure names the files that differ

#### Scenario: Someone bumps DaisyUI without regenerating

- **WHEN** a commit raises `daisyui.version` but leaves the generated sources untouched
- **THEN** the drift check fails

#### Scenario: An ordinary change touching no generated input

- **WHEN** a commit changes only hand-written Kotlin
- **THEN** the drift check passes
