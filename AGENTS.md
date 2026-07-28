# kdaisyui — Agent Guide

Type-safe DaisyUI component DSL for Kotlin server-rendered HTML (kotlinx.html). Wraps Tailwind
/ DaisyUI class strings into compile-time-checked extension functions.

**Stack:** Kotlin 2.3.10 · kotlinx.html 0.12.0 · DaisyUI 5.5.20 · Heroicons 2.2.0 · Ktor 3.5.0 · JDK 21
Exact pins live in `gradle.properties` and `.tool-versions` — those files win over this one.

## The one rule

**Components, icons and most tests are GENERATED.** Never edit anything under
`lib/build/generated/**` — it is build output, overwritten on every compile. Change the codegen
pipeline instead → skill **`kdaisyui-codegen`**.

Hand-written Kotlin exists only here:

- `lib/src/main/kotlin/io/github/ollin/kdaisyui/core/` — `ClassNames.kt`, `TagId.kt`
- `lib/src/main/kotlin/io/github/ollin/kdaisyui/icons/` — `HeroIconSize.kt`, `HeroIconVariant.kt`
  (the icon *functions* next to them are generated; these two support files are not)
- `ktor-integration/src/main/kotlin/io/github/ollin/kdaisyui/ktor/Resolvable.kt`
- tests: `lib/src/test/…` (2 files), `ktor-integration/src/test/…` (1), `e2e-tests/src/test/…`
- `example-app/…`

Package is `io.github.ollin.kdaisyui` — never `com.github.ollin`.

## Modules

| Module | What |
|---|---|
| `lib` | the library — generated components + icons, hand-written core |
| `ktor-integration` | Ktor Resources integration (`Resolvable`, context parameters) |
| `example-app` | Ktor + htmx demo dashboard, port 8080 |
| `e2e-tests` | Kotest + Playwright + Cucumber |
| `buildSrc` | Gradle convention plugins |

There is no root `build.gradle.kts`; configuration lives in the subprojects and `buildSrc`.

## Component shape

```kotlin
fun FlowContent.daisyButton(
    text: String? = null,
    id: HtmlId? = null,                  // type-safe id from TagId.kt
    variant: ButtonVariant? = null,      // btn-primary, btn-secondary, …
    size: ButtonSize? = null,            // btn-sm, btn-lg, …
    outline: Boolean = false,            // plain modifier → btn-outline
    extraClasses: String? = null,        // escape hatch: raw classes
    attrs: (BUTTON.() -> Unit)? = null,  // escape hatch: raw tag access
    content: (BUTTON.() -> Unit)? = null,
)
```

CSS class → Kotlin: `btn-primary` → `ButtonVariant.Primary`; plain modifiers become booleans.
Never hardcode class strings — use the enums, or `extraClasses` when nothing fits.

## Single source of truth: `daisyui.version`

One property in `gradle.properties` drives the submodule tag, the codegen input **and** the
webjar CSS served by `example-app`. It is capped at 5.5.20 for a real reason — read the ceiling
section in **`kdaisyui-codegen`** before bumping it.

## Tooling — MCP first, terminal is off

The terminal is deliberately disabled here. `bash`, `interactive_bash`, `write`, `edit`,
`patch` and `jetbrains_execute_terminal_command` are denied in `opencode.json`, and only the
MCP servers this repo needs are enabled.

| Need | Use |
|---|---|
| Build, test, dependency graph | `mcp_Gradle_gradle`, `mcp_Gradle_inspect_dependencies` |
| Why a build or test failed | `mcp_Gradle_inspect_build` (`mode="details"`) |
| Run tests, dev server, E2E | `mcp_Jetbrains_execute_run_configuration` |
| Find a Kotlin symbol | `mcp_Jetbrains_search_symbol` |
| Errors and inspections | `mcp_Jetbrains_lint_files`, `mcp_Jetbrains_get_file_problems` |
| Rename across the codebase | `mcp_Jetbrains_rename_refactoring` |
| Edit a file | `mcp_Jetbrains_apply_patch`, `mcp_Jetbrains_create_new_file` |
| PRs, issues, releases | `github` MCP — the remote is github.com |
| Commit, status, diff, submodule state | `git` MCP |
| Which DaisyUI classes exist and what they mean | `daisyui-blueprint` MCP |
| Maintainability check before a PR | `codescene` MCP |
| Browser and visual checks | `playwright` MCP |

### Run configurations

`kdaisyUI [:lib:test]` · `kdaisyUI [:lib:generateComponents]` · `kdaisyui [:example-app:run]` ·
`ApplicationKt` (Ktor) · `Dashboard shell` (Playwright)

### just recipes

`test` · `e2e` · `test-all` · `dev` · `build` (also `publishToMavenLocal`) · `generate` ·
`generate-heroicons` · `sync-daisyui` · `sync-heroicons` · `clean` (also removes
`e2e-tests/build`)

Note `just generate` is a *separate* npm path. A normal Gradle build already regenerates,
because `compileKotlin dependsOn generateComponents, generateHeroicons`.

## Skills

| Task | Skill |
|---|---|
| Codegen, config knobs, adding a component, version ceiling | `kdaisyui-codegen` |
| Any test work, E2E wiring, Cucumber | `kdaisyui-testing` |
| Versioning, publishing, what CI does | `kdaisyui-release` |

## Anti-patterns

- Editing `lib/build/generated/**`
- Hardcoding CSS class strings instead of using the enums
- Hardcoding a DaisyUI or Ktor version in a `build.gradle.kts` — derive it from
  `gradle.properties`, or it drifts silently
- Assuming release automation exists — it does not, see `kdaisyui-release`
- Package `com.github.ollin`
- Shipping a UI change without E2E

## Known drift

- Kotlin appears as 2.3.10 (`gradle.properties`) but 2.3.20/2.3.21 on the IDE classpath
- `codegen/src/{index,generator,classify}.js` look superseded by the `*-new.js` pair — nothing
  in `lib/build.gradle.kts` references them

## AI context files

`AGENTS.md` (this file) is the single source for contributors. `CLAUDE.md` and
`.github/copilot-instructions.md` are thin pointers to it. `llms.txt` targets *users* of the
library, not contributors — keep the two audiences apart.
