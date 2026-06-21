# CODESEEKER.md - kdaisyui

> **Full project documentation:** [AGENTS.md](AGENTS.md) — conventions, codegen rules, anti-patterns, and build commands.

This file provides project metadata for CodeSeeker's code analysis. For everything else, see AGENTS.md.

## Project Overview

**Project**: kdaisyui
**Type**: library
**Description**: Type-safe DaisyUI component DSL for Kotlin kotlinx.html — wraps Tailwind CSS classes into compile-time-checked Kotlin extension functions
**Languages**: Kotlin
**Architecture**: Code-generated component wrappers (DSL pattern)
**Testing Strategy**: KotlinTest unit tests + Playwright E2E tests
**Coding Standards**: kotlin.code.style=official, ktlint

## Key Patterns

- **Component pattern**: Each component = one Kotlin file with `FlowContent.daisyXxx()` extension function
- **Enum variants**: `XxxVariant`, `XxxSize` enums with `internal val className: String`
- **All 63 components are GENERATED** by codegen — do NOT hand-edit files in `lib/build/generated/`
- **Only hand-written source**: `lib/src/main/kotlin/io/github/ollin/kdaisyui/core/ClassNames.kt`
- **Codegen pipeline**: DaisyUI frontmatter YAML → classifier → Kotlin generator
- **Package**: `io.github.ollin.kdaisyui.components` (NOT `com.github...`)
- **Core utility**: `io.github.ollin.kdaisyui.core.addClassNames()` — deduplicates and merges CSS class tokens

## Important Files

| Path | Purpose |
|------|---------|
| `lib/src/main/kotlin/io/github/ollin/kdaisyui/core/ClassNames.kt` | Only hand-written Kotlin source |
| `lib/build/generated/.../components/*.kt` | 63 generated component files |
| `codegen/src/generator-new.js` | Kotlin code generator |
| `codegen/src/classifier.js` | DaisyUI class classifier |
| `codegen/src/parser/frontmatter.js` | DaisyUI YAML parser |
| `codegen/src/parser/llms-txt.js` | DaisyUI llms.txt parser |
| `codegen/codegen-config.json` | Extra params, skip list, roles |
| `lib/src/test/kotlin/io/github/ollin/kdaisyui/` | Unit tests |
| `example-app/src/main/kotlin/kdaisyui/example/` | Ktor demo server |
| `e2e-tests/tests/` | Playwright E2E tests |
| `buildSrc/` | Gradle convention plugins |
| `gradle.properties` | Project version (SemVer) |
| `gradle/libs.versions.toml` | Dependency/plugin/submodule versions (Kotlin 2.4.0, kotlinx-html 0.12.0, DaisyUI 5.5.20, Ktor 3.5.0) |

## CodeSeeker Instructions

- Analyze generated components for consistency with the DSL pattern
- Focus on type safety and correct CSS class mapping
- Suggest improvements for enum coverage and modifier support
- Help with codegen modifications (not generated file edits)
- For full project documentation, see [AGENTS.md](AGENTS.md)
