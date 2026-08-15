## Context

The project is a Kotlin 2.4.0 / Gradle 9.x multi-module build. Two modules ship to Maven Central — `:lib` (the DSL, 63 generated components under `lib/build/generated/`, only 2 test files) and `:ktor-integration` (1 test file). `:bom` (java-platform, no code), `:example-app` (demo), and `:e2e-tests` (test code) are not measured production code. There is no coverage tooling today, so coverage can silently regress.

We add `kotlinx-kover` **0.9.8** (latest stable; the version goes into `gradle/libs.versions.toml` per the project's catalog rule). Kover 0.9.x aggregates by declaring `kover(project(...))` dependencies in a merging module — the root — and enforces thresholds with a `koverVerify` rule that fails the build. All DSL below is the verified 0.9.x API.

## Goals / Non-Goals

**Goals:**
- Aggregate coverage of `:lib` + `:ktor-integration` into one merged report at the root.
- Enforce a hard gate: 100% LINE **and** 100% BRANCH for the aggregated scope, failing the build below it.
- Bind the gate to `check` so `./gradlew check` and CI both fail on a shortfall.
- Produce HTML + XML aggregated reports.
- Reach 100% by writing real tests, with a staged rollout so the build is never gratuitously red.

**Non-Goals:**
- Coverage for `:example-app`, `:e2e-tests`, `:bom` (out of scope).
- Changing the published API, artifacts, or the release pipeline.
- Mutation testing or any metric beyond line+branch.
- A separate CI coverage job — the gate rides the existing test pipeline via `check`.

## Decisions

### D1 — Root aggregation via `kover(project(...))`
Apply the Kover plugin to the root and to each measured module, and declare the aggregation in the root `dependencies` block. Running `koverVerify`/`koverHtmlReport`/`koverXmlReport` from the root then covers exactly the listed modules.

```kotlin
// root build.gradle.kts
plugins {
    base
    alias(libs.plugins.jreleaser)
    alias(libs.plugins.kover)        // org.jetbrains.kotlinx.kover, version from catalog
}

dependencies {
    kover(project(":lib"))
    kover(project(":ktor-integration"))
}
```

```kotlin
// lib/build.gradle.kts and ktor-integration/build.gradle.kts
plugins {
    // ...existing...
    alias(libs.plugins.kover)
}
```

```toml
# gradle/libs.versions.toml
[versions]
kover = "0.9.8"
[plugins]
kover = { id = "org.jetbrains.kotlinx.kover", version.ref = "kover" }
```

**Alternative considered:** a dedicated `:coverage` aggregation module — rejected as additive cruft; the root already aggregates JReleaser and is the natural merge point.

### D2 — Hard 100% gate, LINE and BRANCH, explicit bounds
Use an explicit `bound { }` per metric so both LINE and BRANCH are enforced at exactly 100% (`minBound(100)` alone only covers LINE).

```kotlin
// root build.gradle.kts
kover {
    reports {
        total {
            verify {
                rule("100% line coverage") {
                    bound {
                        minValue = 100
                        coverageUnits = CoverageUnit.LINE
                        aggregationForGroup = AggregationType.COVERED_PERCENTAGE
                    }
                }
                rule("100% branch coverage") {
                    bound {
                        minValue = 100
                        coverageUnits = CoverageUnit.BRANCH
                        aggregationForGroup = AggregationType.COVERED_PERCENTAGE
                    }
                }
            }
        }
    }
}
```

`koverVerify` fails the build with a non-zero exit and a per-rule message naming the metric and measured value (e.g. `branch covered percentage ... is 87.50, but expected minimum is 100`).

### D3 — Bind the gate to `check` via `onCheck`
Kover exposes `verify { onCheck = true }` — the idiomatic binding; no manual `tasks.check { dependsOn("koverVerify") }`. HTML/XML also get `onCheck` so a failing `check` leaves an inspectable report.

```kotlin
kover {
    reports {
        total {
            verify { onCheck = true }   // gate runs on every `check`
            html  { onCheck = true }
            xml   { onCheck = true }
        }
    }
}
```

**Alternative considered:** CI-only verification (lib leaves `onCheck=false`, CI calls `koverVerify`). Rejected — the user wants the gate hard everywhere, and `check` already runs in CI.

### D4 — Generated code is in scope; exclusions are explicit
The 63 generated components are real published behavior and stay measured. Tests must exercise them. Only genuinely untestable artifacts get an explicit, documented Kover filter (exclusion precedence over inclusion, `*`/`?` wildcards).

```kotlin
kover {
    reports {
        filters {
            excludes {
                // Example shape only — add a filter ONLY with a written justification;
                // prefer writing a test over excluding generated behavior.
                // classes("io.github.ollin.kdaisyui.SomeUntestable")
                // annotatedBy("*.Generated")
            }
        }
    }
}
```

### D5 — Staged rollout (gate sharpened LAST)
Wire Kover + reports + the rules at `minValue = 100` but keep them inert until tests land — concretely, the **gate rule is added in the final task**, after the in-scope coverage already measures 100%. Until then the build stays green (Kover present, reports available, no failing rule). This honors Risk-Aware commits: every commit can be green; the last commit flips the gate on.

## Risks / Trade-offs

- **100% branch on 63 generated components is a large test effort** → Stage it (D5): measure first, test per component group, sharpen last. Each group is its own small commit.
- **Generated-code branches may be unreachable** (defensive `else`, null-guards the generator emits) → If a branch is provably unreachable, exclude that class with a documented D4 filter rather than fake a test; the spec requires the exclusion be explicit.
- **100% invites coverage-gaming** (asserting nothing just to touch lines) → Tests assert rendered HTML output (the existing `createHTML().div { }` pattern), not mere execution.
- **Kover 0.9.8 vs Kotlin 2.4.0 / Gradle 9 compatibility** → 0.9.8 is current and supports Kotlin 2.x / Gradle 9; verify on first wire-up task; if incompatible, pin the latest 0.9.x that is.
- **Future modules silently unmeasured** → Adding a module requires adding its `kover(project(...))` line; documented in the spec's aggregation requirement.

## Migration Plan

1. Add Kover to the catalog + apply to root, `:lib`, `:ktor-integration`; declare aggregation (no rules yet) — build green.
2. Generate the baseline aggregated report; record real line/branch numbers.
3. Write tests per component group until aggregated coverage reaches 100% line+branch; add documented D4 exclusions only where provably necessary.
4. Add the two 100% verify rules + `onCheck=true` (D2/D3) — the gate goes hard. CI now fails any regression.

**Rollback:** revert the gate-sharpening commit (rules/`onCheck`) to drop back to report-only; revert the plugin commits to remove Kover entirely. No effect on published artifacts at any step.

## Open Questions

- Are any generated branches genuinely unreachable, warranting a D4 exclusion rather than a test? Resolved during step 3 against the real report — not pre-decided.
