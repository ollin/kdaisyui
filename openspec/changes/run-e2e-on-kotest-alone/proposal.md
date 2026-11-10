## Why

`:e2e-tests` runs two test frameworks in one `test` task: Kotest specs and Cucumber features. Two
setups do the same job twice. Each has its own browser-context lifecycle (`PlaywrightSpec` and
`CucumberHooks`), its own console-error teardown, its own screenshot and trace code, and its own
JUnit Platform engine. A third engine, `junit-platform-suite`, exists only to start Cucumber, from
the repository's one Java file. Seven of the fifteen features check what a Kotest spec next to
them already checks.

Measured for this proposal on 2026-11-10 with `./gradlew :e2e-tests:test --rerun` (37 s wall
clock, exit 0), counted from `e2e-tests/build/test-results/test/*.xml`:

| | Tests | Time in tests |
|---|---|---|
| Cucumber, 15 features | 51 (47 scenarios, one outline with 5 examples) | 13.3 s |
| Kotest, 10 specs | 22 | 8.0 s |

Of the 51 Cucumber tests, **16 duplicate a Kotest spec** scenario for scenario. The duplicated
features are `cards`, `dashboard_shell`, `forms`, `progressive_loading`, `stats`, `team`, and two
of the three scenarios in `webjars_assets`. The other **35 tests check something only Cucumber
checks**, so they are ported, not dropped.

Oliver chose Kotest, on 2026-11-10.

## What Changes

- **The 16 duplicated scenarios are deleted.** First, each pair is compared assertion by
  assertion. Where a feature asserts something its Kotest twin does not, the twin gains that
  assertion before the feature goes.
- **The 35 Cucumber-only tests become Kotest specs.** Each feature becomes one spec, or two when
  it mixes runs with and without JavaScript. Every scenario keeps its name as the test name, so
  the behaviour descriptions survive. The outline becomes one test per example.
- **`PlaywrightSpec` gains what only `CucumberHooks` had.** That is a browser context with
  JavaScript disabled, which `@nojs` used to select. It also gains the named, animation-free
  screenshot that `ScreenshotSteps` wrote to `build/reports/screenshots/`.
- **Removed:**
  - `RunCucumberTest.java`, `CucumberHooks`, `PlaywrightWorld` and the seven step classes;
  - the 15 `.feature` files and the `steps` packages;
  - the `cucumber.junit-platform.naming-strategy` property;
  - five catalog libraries: `cucumber-java`, `cucumber-junit-platform-engine`,
    `cucumber-picocontainer`, `junit-platform-suite` and `junit-platform-suite-engine`;
  - the `cucumber` and `junit-platform` versions in `gradle/libs.versions.toml`.
- Texts that name Cucumber are updated: `AGENTS.md`, `docs/how-to.md`, `openspec/config.yaml`,
  `.opencode/skills/kdaisyui-testing`.

**Additive option considered and rejected:** keep both frameworks and only delete the 16
duplicates. That halves the duplication of tests but keeps every duplicated mechanism, and that
mechanism is the cost.

**Other subtractive option rejected:** Cucumber instead of Kotest. It would port 22 tests instead
of 35. But it keeps four libraries and a Java runner, and a Gherkin layer whose step definitions
are Kotlin anyway. Oliver decided.

## Deliberately out of scope

- **New E2E coverage.** A port that also changes what is asserted cannot be reviewed as a port.
- **Kotest data-driven testing (`withData`).** One outline does not justify it; a loop over the
  examples gives one named test each.
- The generated component tests and the other items of the 2026-11-10 subtraction analysis.
  `subtract-build-overhead` (PR #503) owns the build and CI ones.

**Not breaking.** No published artifact changes. E2E coverage stays at the 35 + 22 tests'
assertions minus nothing: every deleted assertion has a twin, and task 1.1 shows it.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | Each of the 16 duplicated Cucumber tests asserts nothing its Kotest twin does not | reading, pair by pair | **assumed**; *wrong if* a feature step has no counterpart in the twin. Task 1.1 compares and adds what is missing |
| A2 | A context created with `setJavaScriptEnabled(false)` in `PlaywrightSpec` behaves as the `@nojs` hook's did | probe | **assumed**; *wrong if* the dashboard still loads its htmx fragments in such a context. Task 1.2 asserts they do not, as a positive control |
| A3 | Removing the Cucumber and suite engines leaves Kotest's discovery untouched, so the 22 existing tests still run | run | **assumed**; *wrong if* the test count after task 4.1 is below 22 plus the ported tests. Task 4.1 counts |
| A4 | Nothing outside `e2e-tests/` uses the five libraries | grep | verified at proposal: `grep -rn "cucumber\|junit-platform-suite" --include=*.kts` lists only `e2e-tests/build.gradle.kts`, and the catalog |

## Capabilities

### New Capabilities

<!-- none -->

### Modified Capabilities

- `content-security-policy`: "A page that logs an error fails its scenario" names Cucumber and
  Kotest. It becomes every E2E test.
- `daisyui-component-coverage`: "The popover dropdown opens beside its trigger without
  JavaScript" cites `dropdown_popover.feature` as its evidence. It now cites the Kotest spec that
  replaces it.

## Impact

- **Edited**: `e2e-tests/build.gradle.kts`, `gradle/libs.versions.toml`,
  `e2e-tests/src/test/kotlin/kdaisyui/e2e/PlaywrightSpec.kt`, five Kotest specs where task 1.1
  finds a missing assertion, `AGENTS.md`, `docs/how-to.md`, `openspec/config.yaml`,
  `.opencode/skills/kdaisyui-testing/SKILL.md`.
- **New**: about ten Kotest specs under `e2e-tests/src/test/kotlin/kdaisyui/e2e/`.
- **Deleted**: `e2e-tests/src/test/java/`, `e2e-tests/src/test/kotlin/kdaisyui/e2e/steps/`,
  `e2e-tests/src/test/resources/kdaisyui/e2e/steps/`.
- `renovate.json` needs no edit: it reads the catalog.
