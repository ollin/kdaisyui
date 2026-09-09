## 1. Wire PIT + smoke-test the kotlin-test bridge (report-only, build green)

- [x] 1.1 Add `pitest` + `pitest-junit5` to `[versions]` and the `pitest` plugin to `[plugins]` in `gradle/libs.versions.toml` (refactoring; catalog only) — the version key is `pitest-plugin`, not `pitest`, so the leaf does not collide with `pitest-junit5`
- [x] 1.2 Apply `alias(libs.plugins.pitest)` to `lib/build.gradle.kts` with a minimal `PitestPluginExtension` scoped to `io.github.ollin.kdaisyui.core.ClassNamesKt` ONLY, `junit5PluginVersion` set, `avoidCallsTo` kotlin.jvm.internal, NO `mutationThreshold` (refactoring; wiring) — also sets `outputFormats` to HTML+XML, since task 2.3 has to read the surviving-mutant list mechanically
- [x] 1.3 SMOKE TEST: run `./gradlew :lib:pitest` and confirm PIT generates mutants AND runs the kotlin-test suite (not "0 tests"); if the JUnit5 bridge fails, fix `junit5PluginVersion` before proceeding (documentation; capture evidence — this de-risks the whole change)

  **PASSED.** PIT 1.19.0 plugin, `pitest-junit5-plugin` 1.2.3, JDK 21, Kotlin 2.4.20.
  Evidence from the run on `ClassNamesKt`:
  - `Created 1 mutation test units in pre scan` / `Generated 17 mutations`
  - tests execute on the JUnit Platform: `[engine:junit-jupiter]/[class:io.github.ollin.kdaisyui.core.ClassNamesTest]`
  - `Ran 20 tests (1.18 tests per mutation)` — the "0 tests" failure mode did NOT occur
  - `Line Coverage (for mutated classes only): 34/34 (100%)`
  - `Killed 16 (94%)`, 1 survivor (`ConditionalsBoundaryMutator`), 0 without coverage

  Two plan corrections came out of this and are recorded on 1.2 and 2.1: the target is
  `ClassNamesKt`, and `targetTests` must be set explicitly. Both failure modes report as
  a clean run rather than an error, so neither would have been caught by a green build.

  The bridge assumption the whole change rested on is now **Verified**, not Assumed.

## 2. Expand scope + baseline measurement

- [x] 2.1 Expand `targetClasses` to the full core-logic scope (`io.github.ollin.kdaisyui.core.*`) (refactoring; config) — the wildcard is required, not stylistic: file-level functions compile to `<File>Kt` classes, so a name taken from a `.kt` filename matches nothing (see 1.3). `targetTests` had to widen to `io.github.ollin.kdaisyui.*` in the same step, or every test outside `core` counts as non-existent when deciding whether a mutant was killed.
- [x] 2.2 From the compiled output, identify the 3–5 most branch-rich components (most variant/size/boolean-modifier combinations) and add their `...Kt` classes to `targetClasses`; verify the class names against actual compiled output (refactoring; config + verification) — chose `ButtonKt` (18 conditionals), `ModalKt` (23 across 7 functions), `DropdownKt` (12), `TooltipKt` (11), `RangeKt` (10); all five verified present in `lib/build/classes/kotlin/main/...`
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

## 8. Gradle 10 readiness (added after section 1; no spec delta)

Not planned up front. Running `:lib:pitest` surfaced the build's existing deprecation
warnings, and the message they end with — "making it incompatible with Gradle 10" — is
not something to leave standing while adding a Gradle plugin and a CI job (7.1) to the
same build. Numbered 8 rather than inserted before 2 so the mirrored issues #135–#152
keep pointing at the tasks they were written for.

These four carry no requirement of their own: nothing a consumer can observe changes, so
there is no delta in `specs/`. They are recorded here rather than left in the git log
because `tasks.md` is what the archive keeps.

Gate for all four: `gradle check --warning-mode all --rerun-tasks` → 0 problems, 1555
tests passing, e2e included.

- [x] 8.1 Replace the `val x by getting(Type::class)` test-suite delegates with `getByName<Type>("x")` in `buildSrc`, `:lib` and `:ktor-integration` (refactoring) — Gradle 9.6 deprecated every Kotlin DSL property delegate for removal in 10
- [x] 8.2 Replace the `val x by tasks.registering(Type::class)` jar delegates with `tasks.register<Type>("x")` in `:lib` and `:ktor-integration` (refactoring)
- [x] 8.3 Apply the foojay resolver in `buildSrc/settings.gradle.kts` (refactoring) — buildSrc is a separate build, so the root settings' resolver never reached it; declared without a version because buildSrc inherits the root's plugin classpath
- [x] 8.4 Drop `-Xcontext-parameters` from `:ktor-integration` and `:example-app` (refactoring) — redundant at Kotlin language version 2.4, which is what the compiler now reports

No issues mirrored for 8.1–8.4: all four were implemented before being written down, and
a mirror that exists only to be closed in the same merge request is noise rather than
tracking.
