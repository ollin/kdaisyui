## Why

The published library modules (`:lib`, `:ktor-integration`) currently have almost no test coverage — `:lib` ships 63 generated components behind only 2 test files, and `:ktor-integration` has 1. Untested code in a Maven Central artifact is a latent defect surface for every downstream consumer, and nothing in the build prevents coverage from regressing. We want coverage to be a hard, measured build contract, not a hope.

## What Changes

- Add the `kotlinx-kover` Gradle plugin, configured for **root-level aggregation** across the published library modules (`:lib`, `:ktor-integration`) into one merged coverage report.
- Add a **hard verification gate**: line AND branch coverage must be **100%** for the aggregated scope, or the build fails. The gate is bound to the standard `check` task so `./gradlew check` (locally and in CI) fails on any shortfall.
- Add HTML + XML aggregated coverage reports for human inspection and CI consumption.
- Write the tests required to bring `:lib` and `:ktor-integration` to 100% line+branch coverage.
- **BREAKING (for contributors, not consumers):** once the gate is sharp, any merge that drops coverage below 100% in scope fails the build. The published artifacts and their API are unchanged.
- Scope boundary: `:example-app` (demo), `:e2e-tests` (test code, not measured production code), and `:bom` (no code) are **excluded** from the coverage gate.
- Staged rollout: wire Kover and reporting first (build stays green), write tests until the real numbers reach 100%, then sharpen the gate to 100% as the final step — so the build is never gratuitously red mid-change.

## Capabilities

### New Capabilities
- `coverage-enforcement`: a measured, root-aggregated code-coverage gate over the published library modules that fails the build below a configured line+branch threshold, with the threshold enforced at 100% and integrated into `check` / CI.

### Modified Capabilities
<!-- None: no existing spec's requirements change. This is a new build/quality capability. -->

## Impact

- **Build config**: root `build.gradle.kts` (apply + aggregate + verify rule + `check` binding); `:lib` and `:ktor-integration` `build.gradle.kts` (apply Kover plugin); `gradle/libs.versions.toml` (Kover plugin version — per the project rule that all versions live in the catalog).
- **CI**: `.github/workflows/ci.yml` — coverage verification runs as part of the existing test pipeline (the gate fails the build on <100%).
- **Tests**: substantial new unit tests under `lib/src/test/` and `ktor-integration/src/test/` to reach 100% line+branch.
- **Generated code**: `lib/build/generated/` (63 components) is in scope — the generator's output must be exercised by tests (or explicitly filtered with justification); Kover `filters` decide inclusion.
- **No impact** on published artifacts, their API, the release pipeline, or downstream consumers.
