## Context

`enforce-100-percent-coverage` makes every line+branch of `:lib` and `:ktor-integration` *executed*. Mutation testing measures whether the tests *assert* enough to catch a defect. PIT (pitest) is the only mature JVM mutation engine; for Gradle/Kotlin the standard wrapper is `info.solidsoft.pitest` (gradle-pitest-plugin). The reference `rogervinas/mutation-testing` proves PIT + Kotlin 2.x + JUnit5 works; we use the current plugin **1.19.0** (their `1.9.11` is stale) and PIT core 1.19.x.

The project's tests use `kotlin.test` configured via `useKotlinTest(kotlinVersion)` in the `kdaisyui.kotlin-library-conventions` precompiled plugin — these run on the **JUnit Platform**, so PIT needs the `pitest-junit5-plugin` to drive them. kdaisyui components are mostly CSS-class string composition: exactly the code where "line executed" ≠ "correct class emitted", so mutation testing has high value — but PIT runs the whole suite once per mutant, so scope must be deliberate.

## Goals / Non-Goals

**Goals:**
- Run PIT over a deliberately small **core-logic scope** (`core/ClassNames`, `core/TagId`, a few branch-rich components), not all 63 components.
- Drive the existing kotlin-test suites via `pitest-junit5-plugin`.
- Enforce a `mutationThreshold` (100% on the scope) that fails the build below it.
- Produce HTML + XML PIT reports.
- Stage the rollout so the gate is sharpened last.

**Non-Goals:**
- Mutating the full 63-component surface (cost vs. value; the components are near-identical string builders — a representative subset is enough).
- Mutation testing `:example-app`, `:e2e-tests`, `:bom`.
- Replacing coverage — this complements `coverage-enforcement`, depends on it.
- Running PIT on every local `check` (too slow); it gates CI and is runnable on demand locally.

## Decisions

### D1 — Plugin + versions (catalog)
```toml
# gradle/libs.versions.toml
[versions]
pitest = "1.19.0"            # gradle-pitest-plugin
pitest-junit5 = "1.2.1"      # pitest-junit5-plugin (verify latest compatible at wire-up)
[plugins]
pitest = { id = "info.solidsoft.pitest", version.ref = "pitest" }
```
Applied to `:lib` (and `:ktor-integration` if its `Resolvable` logic warrants it; start with `:lib`).

### D2 — PIT configuration (scoped, JUnit-Platform aware)
```kotlin
// lib/build.gradle.kts
plugins {
    alias(libs.plugins.pitest)
}
configure<info.solidsoft.gradle.pitest.PitestPluginExtension> {
    junit5PluginVersion.set(libs.versions.pitest.junit5.get())   // run kotlin-test on JUnit Platform
    avoidCallsTo.set(setOf("kotlin.jvm.internal"))               // ignore Kotlin-emitted internals
    mutators.set(setOf("STRONGER"))
    targetClasses.set(setOf(
        "io.github.ollin.kdaisyui.core.*",
        // representative branch-rich components added explicitly, e.g.:
        // "io.github.ollin.kdaisyui.components.ButtonKt",
    ))
    targetTests.set(setOf("io.github.ollin.kdaisyui.*"))
    threads.set(Runtime.getRuntime().availableProcessors())
    outputFormats.set(setOf("XML", "HTML"))
    timestampedReports.set(false)
    // mutationThreshold left UNSET until the gate-sharpening task (D4)
}
```

**Alternative considered:** `mutators = ALL` — rejected; `STRONGER` is the curated strong set (matches the reference) and avoids noisy equivalent mutants.

### D3 — `targetClasses` is explicit and documented
The scope is an explicit allow-list, not a wildcard over all components. Adding a component to mutation scope is a deliberate edit. Kotlin top-level functions compile to `<File>Kt` classes (e.g. `ButtonKt`), so component targets use that suffix — verified at wire-up against the actual compiled class names.

### D4 — Staged gate (sharpen LAST)
Wire PIT with **no `mutationThreshold`** first (report-only, build green). Measure surviving mutants, strengthen assertions until the scope kills 100%, then set `mutationThreshold.set(100)` as the final commit. Honors Risk-Aware commits: every commit stays green; the last one flips the gate.

### D5 — CI placement
PIT is slow (suite × mutants). Run it as its own CI job (parallel to `unit-tests`/`e2e-tests`), not bound to local `check`. Locally it's `./gradlew pitest` on demand. The job fails the pipeline below threshold.

## Risks / Trade-offs

- **PIT + kotlin-test/JUnit-Platform incompatibility** → The single biggest risk. Mitigate with a wire-up smoke task (D-first task) that runs `./gradlew pitest` on the tiny `core/ClassNames` scope and confirms mutants are generated AND tests run; if `junit5PluginVersion` is wrong, fix before scaling scope.
- **Equivalent mutants** (a mutation that cannot change observable behavior, so unkillable) → would make 100% impossible. Mitigate: keep scope to logic with observable output (class-string composition); if a provably-equivalent mutant blocks 100%, exclude it via PIT's `excludedMethods`/`excludedClasses` with a written justification (mirrors the coverage change's explicit-exclusion rule).
- **Runtime cost in CI** → scope is small + `threads` parallelism + dedicated job; acceptable. If it grows slow, narrow scope further.
- **`STRONGER` mutator surprises on Kotlin** (null-checks, `when` exhaustiveness the compiler injects) → `avoidCallsTo` + measuring before gating (D4) surfaces these before they can break CI.
- **100% mutation score is strict** → it is intentional for a *small* scope; this is why scope is core logic only, not all 63 components.

## Migration Plan

1. Add catalog entries + apply plugin to `:lib`; configure PIT report-only on the smallest scope (`core/ClassNames`) — smoke-test that PIT runs kotlin-test (build green).
2. Expand `targetClasses` to the full core-logic scope; run report-only; record surviving mutants.
3. Strengthen assertions until the scope kills 100% of mutants; document any equivalent-mutant exclusions.
4. Set `mutationThreshold.set(100)` (gate hard) + add the CI job.

**Rollback:** revert the threshold commit → report-only; revert the plugin commits → remove PIT entirely. No effect on published artifacts at any step.

## Open Questions

- Which components beyond `core/*` enter scope? Decided in step 2 against the real report — pick the 3–5 with the most branches (boolean modifiers / variant+size combinations), not all 63.
- Does `:ktor-integration`'s `Resolvable` carry enough logic to mutation-test, or is `:lib` core sufficient for v1? Resolved at step 2.
