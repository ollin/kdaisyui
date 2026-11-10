---
name: kdaisyui-testing
description: >-
  Writing or fixing kdaisyui tests - the three test layers (generated component tests,
  hand-written kotlin-test units, and Kotest + Playwright + Cucumber E2E), how the in-process
  Ktor server and system Chromium are wired, and which task runs what.
---

# kdaisyui — Testing

Three layers, three different frameworks. Identify which one you are in before writing a test.

## 1. Generated component tests — `:lib:test`

Produced by `codegen/src/test-generator.ts` into `lib/generated/test/kotlin/…`, which is
**committed**. Compiling does not regenerate them, so `:lib:test` needs no Node and no
submodules. These are the bulk of the suite: `:lib:test` ran 2,633 tests on 2026-10-18, of which
896 are documented-element tests, each asserting DaisyUI's tag as well as its classes, one per distinct call any function makes for an element a
DaisyUI page marks with `$$`, and the rest are the coverage tests.

**Never hand-edit them** — `just generate` overwrites them wholesale and CI's
`generated-sources-drift` job fails any hand edit. Fix `test-generator.ts` instead; see the
`kdaisyui-codegen` skill.

**A compile failure here is a FINDING, not a chore.** Their input is DaisyUI's own documented
markup, so they assert that the generated API can express what DaisyUI documents, element by
element. What it cannot express never reaches them: the documented-element gate fails generation
first, unless the page's model declares the gap (`kdaisyui-codegen`). When an API
change stops them compiling, the question is whether the new API can still reach that markup —
and making them compile again is the proof that it can. Changing the API to silence them, or
excluding a case, throws away the only check of that claim.

## Test files are reviewed as production code

`code_health_review` applies to `*Test.kt` and `*.test.ts` exactly as to `src/`, and Object
Calisthenics applies to both: one level of indentation per function, no abbreviated names,
encoded strings wrapped in a type rather than `split()` at every call site.

Nothing automates this. `koverVerify` and `:lib:pitest` both pass happily on an unreadable
test — one asks whether the line ran, the other whether it was asserted, and neither can ask
whether a human can follow it. Run the review by hand on any test file you touch.

## 2. Hand-written unit tests — `:lib:test`, `:ktor-integration:test`, `:doc-snippets:test`

Framework is **kotlin-test** (`useKotlinTest(versions.kotlin)`), *not* Kotest and not JUnit
directly. The files live in `lib/src/test/` (10), `ktor-integration/src/test/` (1) and
`doc-snippets/src/test/` (5).

Style: render with `createHTML().div { … }` and assert against the produced HTML string.

**A coverage revert can come from Kotlin, not from a missing test.** The gate is 100% *branch*,
measured on bytecode, and every `?.` and every `?:` is a null check with two branches. When the
value a check looks at can never be null, one of its branches is dead: `koverVerify` fails and
TCR reverts the step with every test green. **Do not add a test; no input reaches a dead branch.**
Count the null checks and make it one per value that really can be null:

| Written | The dead branch | Write instead |
|---|---|---|
| `m?.groupValues?.get(1)` | the second `?.`: `groupValues` is never null | `m?.groupValues[1]` |
| `m?.groupValues[1] ?: d` | the `?:`: it checks `groupValues[1]`, which is never null; the `?.` already decided | `regex.findAll(s).map { it.groupValues[1] }.firstOrNull() ?: d`, or `if (m == null) d else m.groupValues[1]` |
| `a?.removePrefix(p) ?: d` | the same: `removePrefix` never returns null | `(a ?: d).removePrefix(p)`, where `d` has no prefix `p` |

The second row is the one that looks safe, because the first row's fix is inside it: `?.` then
`?:` is two checks, and only the first can go both ways. The sign is in Kover's report: the line
shows a missed branch while every test passes and no input you can name would reach it.
`verify-doc-snippets` lost two steps to the first row (its finding F5),
`read-the-gradle-9-9-problems-report` one to the second (its finding F2).

Inlined standard-library functions do the same, out of sight: `bufferedReader()` checks
`this is BufferedReader`, which an `InputStreamReader` never is, so `readBytes().decodeToString()`
is the form without it. Before guessing which call it is, read the line Kover's report names:
`per-component-class-delivery` lost two reverts by blaming `use {}`, which was innocent (its
finding F1). The same inlining shows up in PIT as a survivor no test can kill, `any {}` there;
take the non-inline form (`intersect`) rather than a weaker threshold.

A `when` over constants is the third case. Close values (`1, 7, 9, 10, 11, 12`) compile to a table
switch over the whole range, and Kover counts every gap of it as a branch of its own, which no
real input reaches: `check-the-themes-the-stylesheet-compiles` lost a run to five such gaps (its
finding F3). Write the cases as conditions, or test membership in a set (`tag in REFERENCE_TAGS`).
After such a revert the test of the reverted step is still on disk; `git stash` it before the
refactoring that has to come first, or that refactoring goes red on the old test.

## 2a. Documentation snippets — `:doc-snippets:compileSnippets`

Every Kotlin snippet of `README.md` and `docs/` is compiled against the library, in `check` and
in CI's `unit-tests` job; the marker convention is in `AGENTS.md`. The extractor in
`doc-snippets/src/main` has its own unit tests under `:doc-snippets:test`, and sits under the
aggregated Kover gate. The compiled snippets do not, because they are documentation.

## 3. End-to-end — `:e2e-tests:test`

Two styles coexist in one module, both on the JUnit Platform, both in the same `test` task.
Exact Kotest, Cucumber and Playwright versions live in `e2e-tests/build.gradle.kts` — read them
there rather than restating them here.

**Kotest** specs:
`PlaywrightSpec.kt`, `SharedInfrastructure.kt`, `DashboardShellTest.kt`,
`CardsRow1FragmentTest.kt`, `FormsFragmentTest.kt`, `HtmxProgressiveLoadingTest.kt`,
`StatsFragmentTest.kt`, `TeamFragmentTest.kt`, `WebjarsAssetsTest.kt`

**Cucumber BDD**:
- features: `e2e-tests/src/test/resources/kdaisyui/e2e/steps/*.feature` (7 files)
- steps: `e2e-tests/src/test/kotlin/kdaisyui/e2e/steps/` — `CucumberHooks.kt`,
  `FragmentSteps.kt`, `NavigationSteps.kt`, `PlaywrightWorld.kt`
- runner: `e2e-tests/src/test/java/kdaisyui/e2e/steps/RunCucumberTest.java` (the only Java file
  in the repo)
- scenario naming: `cucumber.junit-platform.naming-strategy=long`

### Environment

- **Playwright Java** — not the Node runner, so there is no `npx playwright`
- `PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1`: the build reuses a **system Chromium**, autodetected
  from `chromium`, `chromium-browser`, `google-chrome-stable`, `google-chrome`. Override with
  `PLAYWRIGHT_CHROMIUM_EXECUTABLE_PATH`.
- `:e2e-tests:playwrightInstall` installs browsers when none are present (CI runs it first)
- `test dependsOn :example-app:classes` — the Ktor server runs **in process**, no external
  server to start

## What none of these layers can see: CSS

Every generated component test asserts a **class string** in rendered HTML. So does most of the
E2E suite. None of it evaluates a stylesheet, which means **a component can pass its entire test
suite and render completely unstyled** — the class is in the markup and no rule matches it.

This is not hypothetical. It has now happened twice:

- `daisyModal` emitted no `popover` attribute for two releases while every test passed, because
  the missing thing added no CSS class;
- `max-sm:megamenu-vertical` sat on the megamenu doing nothing, because the prebuilt DaisyUI
  stylesheet ships only five Tailwind variant prefixes and no test looked at computed styles.

Two step definitions exist for the cases where it matters — `the elements {string} and {string}
have the same {string}` and `… differ in {string}` in `ComputedStyleSteps.kt`. They compare two
elements rather than assert a value, so nothing hard-codes a pixel size that a DaisyUI restyle
would invalidate. Reach for them when a change is about *appearance* rather than markup.

`e2e-tests/build/reports/screenshots/` holds named full-page screenshots, written by
`a screenshot is saved as {string}`. Animations are disabled for them, so what you see is the
settled state. They exist to be looked at: a rendering fault is something a person catches in a
picture and no assertion here would.

## Running tests

Terminal is disabled in this repo — drive Gradle through the Gradle MCP, or use an IDE run
configuration.

| Want | Gradle task |
|---|---|
| Unit tests | `:lib:test` |
| E2E | `:e2e-tests:test` |
| Documentation snippets | `:doc-snippets:compileSnippets` |
| Regenerate components | `:lib:generateComponents` |
| Demo server | `:example-app:run` |

For failures query the Gradle MCP build result with the `testName` and full detail — it returns
the stack trace and captured output. Do not re-read raw console logs.

### IDE run configurations

`kdaisyUI [:lib:test]` · `kdaisyUI [:lib:generateComponents]` · `kdaisyui [:example-app:run]` ·
`ApplicationKt` (Ktor) · `Dashboard shell` (Playwright)

These are **not committed** — there is no `.run/` and no `.idea/runConfigurations/`, so a fresh
checkout will not have them. Fall back to the Gradle tasks above.

### just recipes

`test` · `e2e` · `test-all` · `dev` · `build` (also runs `publishToMavenLocal`) · `generate` ·
`generate-heroicons` · `sync-daisyui` · `sync-heroicons` · `clean` (also removes
`e2e-tests/build`)

**A normal Gradle build does NOT regenerate.** An earlier version of this page said it did, citing
a `compileKotlin dependsOn generateComponents` that does not exist — `lib/build.gradle.kts` says
the opposite in as many words. The generated sources are committed, and `just generate` is the only
thing that rewrites them.

## CI

`.github/workflows/ci.yml`, **six** jobs. Earlier versions of this page said two, then four,
then five — so check the file rather than trusting this count.

| Job | Runs | Needs |
|---|---|---|
| `generated-sources-drift` | the five `:lib:generate*` tasks, `:lib:checkComponentApi`, and `git status --porcelain` | `submodules: recursive`, Node, JDK |
| `codegen-tests` | `npm test` in `codegen/` (`node --test test/`), then StrykerJS on the lines the change adds under `codegen/src/`, at 100% | Node, `submodules: recursive` |
| `api-baseline` | `:lib:checkKotlinAbi` | JDK |
| `unit-tests` | `check -x :e2e-tests:test koverXmlReport`: everything `check` runs but the browser tests; then `:build-checks:checkGradleProblems` | JDK, Docker |
| `mutation-tests` | `:lib:pitest :doc-snippets:pitest :build-checks:pitest :gradle-plugin:pitest`, each gated at 100% **test strength** | JDK |
| `e2e-tests` | `playwrightInstall`, then `:e2e-tests:test` | JDK, **Docker** |

`unit-tests` and `mutation-tests` are a deliberate pair and neither implies the other: Kover
says the line ran, PIT says an assertion would have noticed if it ran differently. See
`AGENTS.md` for the short version and `lib/build.gradle.kts` for why the threshold is test
strength rather than mutation score.

**The drift job and `codegen-tests` need the submodules** — the Kotlin jobs build from committed
sources, which is the point of committing them. **`codegen-tests` is the only job with no JDK at all**: it imports the
generator modules and asserts on pure functions, so it needs neither a compiler nor the DaisyUI
checkout.

### Codegen tests read the pinned submodules, and never skip without them

Until `snapshot-test-generators` (2026-11-01) the rule was the opposite: `codegen-tests` checked out
no submodule, so a test reading one died there with `ENOENT` on a path inside `daisyui/`, which
read like a broken checkout. The job now checks out DaisyUI and Heroicons, as the drift job does
(about 4 s), because the snapshot tests need them: `codegen/test/generator-snapshots.test.ts`
generates every component and every icon in memory and compares each with its committed file, and
`coverage-test-snapshot.test.ts` does the same for the coverage tests. Without them StrykerJS could
not see the generators at all.

Two rules come with it:

- **Find a submodule through `src/submodules.ts`**, never as `../../daisyui` from a source file. That
  path points nowhere inside StrykerJS's sandbox under `codegen/.stryker-tmp/`; `pinnedSubmodule`
  looks from the repository root, the directory holding `.gitmodules`.
- **A missing submodule fails, with the command that fixes it**: `pinnedSubmodule` throws
  `The heroicons submodule is not checked out: git submodule update --init heroicons`. Never skip a
  test when it is absent: that produces a test CI can never fail. A clone without submodules does
  not run these tests anyway, because they are not part of `check`; `just generate` needs the same
  checkout.

The committed model in `codegen/model/` is not a submodule, so `committed-model.test.ts` needs
neither.

Its Node version comes from `node-version-file: .tool-versions`, not a literal — the test
runner's default file patterns have changed between majors, and a mismatch there fails quietly.

### The codegen tests are NOT part of `check`

`./gradlew :lib:testCodegen` runs them locally and is in the `codegen` group, not
`verification`. Wiring them into `check` would break the promise in `AGENTS.md` that a clone
builds and tests with no Node, no npm and no submodules. Both Node-needing jobs stay outside it.

`e2e-tests` needs Docker because `:e2e-tests:test` depends on `:example-app:classes`, which
compiles the app's stylesheet in a Node container through the kdaisyui plugin's `kdaisyuiStylesheet`
task (Testcontainers; no docker CLI needed). npm's cache lives in the named volume
`kdaisyui-npm-cache`; without it a compile installs Tailwind and DaisyUI anew. The plugin's own
`NodeContainerRunTest` starts a container too.
