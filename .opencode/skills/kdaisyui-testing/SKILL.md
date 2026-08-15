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

Produced by `codegen/src/test-generator.js` into `lib/generated/test/kotlin/…`, which is
**committed**. Compiling does not regenerate them, so `:lib:test` needs no Node and no
submodules. These are the bulk of the suite (`:lib:test` runs ~566 tests).

**Never hand-edit them** — `just generate` overwrites them wholesale and CI's
`generated-sources-drift` job fails any hand edit. Fix `test-generator.js` instead; see the
`kdaisyui-codegen` skill.

## 2. Hand-written unit tests — `:lib:test`, `:ktor-integration:test`

Framework is **kotlin-test** (`useKotlinTest(versions.kotlin)`), *not* Kotest and not JUnit
directly. Only three files exist:

- `lib/src/test/kotlin/io/github/ollin/kdaisyui/core/HtmlIdTest.kt`
- `lib/src/test/kotlin/io/github/ollin/kdaisyui/DashboardTest.kt`
- `ktor-integration/src/test/kotlin/io/github/ollin/kdaisyui/ktor/ResolvableTest.kt`

Style: render with `createHTML().div { … }` and assert against the produced HTML string.

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

## Running tests

Terminal is disabled in this repo — drive Gradle through the Gradle MCP, or use an IDE run
configuration.

| Want | Gradle task |
|---|---|
| Unit tests | `:lib:test` |
| E2E | `:e2e-tests:test` |
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

`just generate` is a *separate* npm path. A normal Gradle build already regenerates, because
`compileKotlin dependsOn generateComponents, generateHeroicons`.

## CI

`.github/workflows/ci.yml`, two jobs: `unit-tests` (`:lib:test`) and `e2e-tests`
(`playwrightInstall` then `:e2e-tests:test`). Both JDK 21 temurin with
`submodules: recursive` — the DaisyUI and Heroicons submodules must be present or codegen
fails before any test runs.
