# generated-sources Specification

## Purpose
How generated Kotlin is produced, stored and kept honest: where it lives, that it is
committed and readable without a build, that regenerating it is explicit rather than
automatic, and that committed output which no longer matches its inputs is rejected.

## Requirements

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

The fifth arrived with `generate-component-reference` and is the only one that is not Kotlin:
`codegen/src/index-docs.ts` produces 66 pages plus `index.md` under `docs/reference/`, measured
2026-09-12. Every page carries the attribution as an HTML comment naming its `+page.md` source,
and `docs/reference/**` is marked `linguist-generated=true` alongside `lib/generated/**`.

A reference page's inputs are the parsed DaisyUI frontmatter, the classified component model,
and a committed editorial summary per component. The summary is an input to the generator like
any other, not an exception to generation.

**Verified, and it refuted the assumption it replaces.** The original wording claimed every
element of all 66 pages was derivable from the frontmatter and the model alone, naming the
description among them. Measured over all 66 non-skipped components on 2026-09-12: no page
carries a `##` heading, so there is no prose, example or note outside the template — but **0 of
66 descriptions match the frontmatter `desc`**. All 66 are hand-written editorial summaries
(`card`: "Content containers with body and title" against DaisyUI's "Cards are used to group and
display content in a way that is easily readable"). The description is editorial, and no parser
can derive it.

It is not derivable and it cannot be dropped either, for a mechanical reason: `index.md` is a
66-row table with a one-cell Description column, and DaisyUI's `desc` runs to 48 words for
`aura`. Hence the third input. 63 of 66 components need one line of it; `aura`, `megamenu` and
`otp` say more on the page than in the table and carry a second, longer field.

*Wrong if:* regenerating a page loses information a reader relied on — the check is a diff of
all 66 generated pages against the hand-written ones, where every difference must be either a
correction or a deliberate template decision, never a loss.

The same measurement established what generating **corrects**, which is the requirement's reason
for existing: `id: HtmlId? = null` is the second parameter of every generated function and
appears in **no** hand-written signature on **any** page; `drawer.md` omits `daisyDrawerButton`;
`megamenu.md` omits `daisyMegamenuPanel`, types `daisyMegamenuActive`'s lambda as `DIV` where the
code says `SPAN`, and omits the `popover` attribute.

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

#### Scenario: A new component arrives with no editorial summary

- **WHEN** a DaisyUI release adds a component for which no editorial summary is committed
- **THEN** its reference page is still generated, describing it from the first sentence of its
  DaisyUI description
- **AND** the build does not fail for the missing summary

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
before use (`codegen/src/parser/frontmatter.ts:336-342`, `codegen/src/index-heroicons.ts:49`),
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
regeneration SHALL cover **every** generator, including the icon render tests **and the
reference documentation**.

**Verified, in part**, and separately for each scope the requirement claims. Kotlin: the drift
job was observed failing on a hand-edited generated file (PR #231, 2026-08-15) while the test
jobs passed on the same commit. Reference documentation: the same observation was repeated for
the extended scope on PR #339, 2026-09-12 — a hand-edited `docs/reference/megamenu.md` failed the
job, which named the file, while `unit-tests`, `codegen-tests`, `mutation-tests` and
`api-baseline` all passed on that commit.

The second observation is the one that measures the gap this requirement exists to close: four
other gates read a page documenting the wrong lambda receiver and none of them objected.

What is still Assumed is durability — two green runs prove the job works, not that it keeps
working.

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

### Requirement: A generated file's attribution resolves

The `// Source:` line every generated file carries SHALL name a path that exists in the pinned
DaisyUI submodule. Generated prose SHALL name the HTML element a function renders, not the
kotlinx.html builder that opens it.

An attribution that resolves to nothing is worse than none: it removes the reader's only way to
check generated output against its input while looking as though it provides one.

**Verified**, both halves, on 2026-09-12 against DaisyUI 5.7.17 — measuring what was wrong before
this requirement existed. Both are now fixed:

- **10 of 66** generated component files cited a directory that does not exist — every multi-word
  component, because the header lower-cased the PascalCase name (`FileInput.kt` cited
  `components/fileinput/`; the directory is `file-input`). The correct value was already being
  carried as `ComponentSource.componentDir` and is now used.
- **2 files** named a kotlinx.html builder in prose rather than an element: `Fieldset.kt` said
  `Renders <fieldSet …>` and `Textarea.kt` said `<textArea …>`. The shape already distinguished
  `tagBuilder` from `htmlTag`; only the Kotlin emitter conflated them.

*Wrong if:* a cited path stops resolving after a DaisyUI bump — which would mean the citation is
built from something other than the directory the component was read from.

#### Scenario: A multi-word component's attribution

- **WHEN** the codegen generates a component whose directory name contains a hyphen
- **THEN** its `// Source:` line names that directory
- **AND** the path exists in the pinned submodule

#### Scenario: Prose naming an element whose builder is spelled differently

- **WHEN** a generated function renders `<fieldset>`, `<textarea>` or any element kotlinx.html
  opens under a different name
- **THEN** its doc comment names the HTML element
- **AND** the emitted code still calls the kotlinx.html builder

### Requirement: Configuration that is never read is an error

The codegen SHALL fail when `codegen-config.json` holds a component-keyed entry that no lookup
consumed during a generation run.

A mis-keyed entry is indistinguishable from an absent one at runtime: the lookup returns
`undefined`, the caller's fallback swallows it, and the component is generated as though nothing
had been configured. Nothing fails, and the class-based safety net cannot help — a knob that
changes no CSS class is invisible to both the generated tests and the drift job.

**Verified** that this is the mechanism behind a shipped defect: `noContent` held `file-input` and
`theme-controller` while the lookup asked for `fileinput` and `themecontroller`, so
`daisyFileInput` and `daisyThemeController` demanded a content lambda on a void `<input>` for as
long as the entries existed.

**Verified** that the guard starts green: measured across all seven component-keyed sections on
2026-09-12, exactly two entries were unreachable, both in `noContent`, which is deleted. The other
six sections are clean, so a future failure is a new finding rather than inherited debt.

A key-**existence** check would not have caught it: `file-input` is a real DaisyUI directory. The
entry is not a typo, it is unread, and only consumption distinguishes the two.

*Wrong if:* the guard fires on an entry that is legitimately unread in some runs but not others,
which would mean consumption depends on run-time conditions rather than the component set.

#### Scenario: A config entry no lookup can reach

- **WHEN** a generation run completes with a component-keyed config entry that nothing read
- **THEN** the generator fails
- **AND** it names the section and the key

#### Scenario: A clean configuration

- **WHEN** every component-keyed config entry was consumed during the run
- **THEN** the generator succeeds

### Requirement: The Kotlin-level API surface is a committed, explicitly-updated artefact

The generated components' Kotlin signatures SHALL be dumped to a committed baseline covering
function name, extension receiver, parameter names, parameter types including lambda receiver
types, parameter order and default values. CI SHALL fail when the dump differs from the baseline.
The baseline SHALL be rewritten only by an explicit task, and SHALL NOT be part of the ordinary
regeneration command.

**Verified** that the existing JVM baseline cannot carry these facts. `lib/api/lib.api` is a dump
of JVM descriptors, which have no representation for receiver types, parameter names or default
values. Measured 2026-09-12:

```
daisyOtp (Lkotlinx/html/FlowContent;L…/HtmlId;L…/OtpVariant;L…/OtpSize;Z
          Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V
```

Changing a lambda's receiver from `DIV` to `LABEL` is source-breaking for every caller and
produces no diff there. In Kotlin, parameter names are equally part of the API because of named
arguments, and are equally absent.

**Verified** that the JVM baseline is not thereby useless: removing a parameter *is* visible, since
a `Function1` disappears and the arity changes. The two baselines answer different questions and
both are kept.

The explicit-update rule is what makes the artefact a gate rather than a mirror: a baseline
rewritten by the same command that regenerates the code would follow every change silently, which
is precisely the failure being fixed.

*Wrong if:* the dump is unstable across machines or runs — it is derived from the same model the
Kotlin is generated from, so instability there would already show as generated-source drift.

#### Scenario: A changed lambda receiver

- **WHEN** a component's rendered element changes, changing its lambda receiver type
- **THEN** the Kotlin API check fails
- **AND** the diff names the function and both receiver types

#### Scenario: A renamed parameter

- **WHEN** a generated function's parameter is renamed
- **THEN** the Kotlin API check fails, because named arguments make the name part of the API

#### Scenario: An intended API change

- **WHEN** a maintainer runs the explicit update task and commits the new baseline
- **THEN** the check passes
- **AND** the committed diff is the record of what changed
