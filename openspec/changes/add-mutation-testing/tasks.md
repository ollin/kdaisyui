## 1. Wire PIT + smoke-test the kotlin-test bridge (report-only, build green)

- [ ] 1.1 Add `pitest` + `pitest-junit5` to `[versions]` and the `pitest` plugin to `[plugins]` in `gradle/libs.versions.toml` (refactoring; catalog only)
- [ ] 1.2 Apply `alias(libs.plugins.pitest)` to `lib/build.gradle.kts` with a minimal `PitestPluginExtension` scoped to `io.github.ollin.kdaisyui.core.ClassNames` ONLY, `junit5PluginVersion` set, `avoidCallsTo` kotlin.jvm.internal, NO `mutationThreshold` (refactoring; wiring)
- [ ] 1.3 SMOKE TEST: run `./gradlew :lib:pitest` and confirm PIT generates mutants AND runs the kotlin-test suite (not "0 tests"); if the JUnit5 bridge fails, fix `junit5PluginVersion` before proceeding (documentation; capture evidence — this de-risks the whole change)

## 2. Expand scope + baseline measurement

- [ ] 2.1 Expand `targetClasses` to the full core-logic scope (`io.github.ollin.kdaisyui.core.*`) (refactoring; config)
- [ ] 2.2 From the compiled output, identify the 3–5 most branch-rich components (most variant/size/boolean-modifier combinations) and add their `...Kt` classes to `targetClasses`; verify the class names against actual compiled output (refactoring; config + verification)
- [ ] 2.3 Run `./gradlew :lib:pitest` report-only; record the baseline mutation score and list every surviving mutant (class/line/mutator) into the change notes (documentation; no code)

## 3. Kill surviving mutants in core logic

- [ ] 3.1 Strengthen `ClassNames` tests to kill all its surviving mutants AND assert exact output (feature-test; small, re-run pitest on that class)
- [ ] 3.2 Strengthen `TagId`/HtmlId tests to kill all its surviving mutants AND assert (feature-test; small)

## 4. Kill surviving mutants in scoped components

- [ ] 4.1 Strengthen tests for scoped component #1 until all its mutants are killed AND assert rendered class strings (feature-test; small)
- [ ] 4.2 Strengthen tests for scoped component #2 until all its mutants are killed (feature-test; small)
- [ ] 4.3 Strengthen tests for scoped component #3 until all its mutants are killed (feature-test; small)
- [ ] 4.4 Strengthen tests for any remaining scoped components until all their mutants are killed (feature-test; small, one component per commit)

## 5. Resolve residual / equivalent mutants

- [ ] 5.1 Re-run `./gradlew :lib:pitest`; for each remaining surviving mutant, either add an assertion that kills it OR, if provably an equivalent mutant, exclude it via `excludedMethods`/`excludedClasses` with a written justification (feature-test/refactoring; iterate until score is 100%)
- [ ] 5.2 Confirm the report shows 100% mutation score on the full scope before sharpening the gate (documentation; evidence captured)

## 6. Sharpen the gate (LAST — build now fails below threshold)

- [ ] 6.1 Add `mutationThreshold.set(100)` to the `PitestPluginExtension` (feature; the gate goes hard, run `./gradlew :lib:pitest` — must pass at 100%)
- [ ] 6.2 Confirm the gate bites: revert one assertion so a mutant survives, run pitest, expect non-zero exit, then restore (feature-test; proves the threshold fails the build)

## 7. CI + docs

- [ ] 7.1 Add a dedicated `mutation-tests` job to `.github/workflows/ci.yml` running `./gradlew :lib:pitest` (parallel to unit-tests/e2e-tests, NOT bound to local check) (refactoring; CI wiring)
- [ ] 7.2 Document the mutation-testing gate, its scope, and how to run + read the PIT report locally in `AGENTS.md` (documentation)
