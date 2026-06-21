## Why

100% line+branch coverage (the `enforce-100-percent-coverage` change) proves every line and branch is *executed* by tests — but not that the tests *assert* anything meaningful. A test that calls `daisyButton(variant = Primary)` without checking the rendered class string reaches 100% coverage yet would not notice if the generator emitted `btn-secondary`. Mutation testing closes that gap: it systematically alters production code and fails if no test catches the change, measuring test *strength* rather than test *reach*.

## What Changes

- Add the `info.solidsoft.pitest` Gradle plugin (gradle-pitest-plugin 1.19.0, PIT core 1.19.x) to the core-logic module(s).
- Add the `pitest-junit5-plugin` so PIT can run the project's kotlin-test suites (which run on the JUnit Platform via `useKotlinTest`).
- Configure PIT for the **core logic only** — not all 63 components — targeting the classes where "executed" ≠ "correct" (`core/ClassNames`, the `core/TagId` hierarchy, and a small set of branch-rich components).
- Add a **mutation-score threshold gate**: a `mutationThreshold` (target 100% on the chosen scope) that fails the build when too many mutants survive.
- Produce PIT HTML + XML reports for inspecting surviving mutants.
- **BREAKING (for contributors, not consumers):** once sharp, a merge that lets the mutation score fall below the threshold on the scoped classes fails the build. Published artifacts and API are unchanged.
- Staged rollout: wire PIT + report first (no gate), measure surviving mutants, strengthen tests until they are all killed, then sharpen the threshold as the final step.
- Depends on `enforce-100-percent-coverage` (mutation testing assumes the code is already fully covered; it strengthens those tests).

## Capabilities

### New Capabilities
- `mutation-testing`: a PIT-based mutation-testing gate over the project's core logic that fails the build when the mutation score falls below a configured threshold, with the threshold enforced at 100% for the scoped classes and reports produced for surviving-mutant inspection.

### Modified Capabilities
<!-- None: coverage-enforcement is a separate capability; this adds a new, complementary one. -->

## Impact

- **Build config**: the scoped module's `build.gradle.kts` (apply pitest plugin + `PitestPluginExtension` config: `targetClasses`, `targetTests`, `mutators`, `avoidCallsTo` for Kotlin internals, `junit5PluginVersion`, `mutationThreshold`); `gradle/libs.versions.toml` (pitest plugin + junit5-plugin versions).
- **CI**: `.github/workflows/ci.yml` — a mutation step (the `pitest` task) gates the pipeline; may be a dedicated job given PIT's runtime cost (it runs the suite once per mutant).
- **Tests**: strengthened assertions in the scoped tests to kill surviving mutants (building on the 100%-coverage tests).
- **Dependency**: depends on `enforce-100-percent-coverage` being applied first.
- **No impact** on published artifacts, their API, or the release pipeline.
