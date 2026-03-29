# kdaisyUI Project Knowledge Base

**Generated:** 2026-03-29
**Stack:** Kotlin 2.3.x + Gradle 9.x + kotlinx.html + DaisyUI

## OVERVIEW

Type-safe DaisyUI component DSL for Kotlin server-rendered HTML. Wraps Tailwind CSS classes into compile-time-checked Kotlin functions.

## STRUCTURE

```
kdaisyUI/
├── lib/                    # Core DSL library (publishable)
│   └── src/main/kotlin/kdaisyui/
│       ├── components/     # 22 DaisyUI component wrappers
│       └── core/           # ClassNames utility
├── example-app/            # Ktor demo server (port 8080)
├── e2e-tests/              # Playwright E2E tests
├── buildSrc/               # Gradle convention plugins
└── docs/                   # Diátaxis documentation
```

## WHERE TO LOOK

| Task | Location |
|------|----------|
| Add new component | `lib/src/main/kotlin/kdaisyui/components/` |
| Fix component bug | `lib/src/main/kotlin/kdaisyui/components/<Component>.kt` |
| Add tests | `lib/src/test/kotlin/kdaisyui/components/` |
| Demo app routes | `example-app/src/main/kotlin/kdaisyui/example/` |
| E2E tests | `e2e-tests/tests/` |
| Build config | `buildSrc/`, `gradle.properties`, `settings.gradle.kts` |
| Version pins | `gradle.properties`, `.tool-versions` |

## CONVENTIONS

- **Component pattern**: Each component = one file with `FlowContent.daisyXxx()` extension function
- **Enum variants**: `XxxVariant`, `XxxSize` enums with `internal val className`
- **Testing**: KotlinTest with `createHTML().div { }` assertions
- **Build**: Gradle Kotlin DSL, convention plugin in `buildSrc/`
- **Code style**: `kotlin.code.style=official`

## ANTI-PATTERNS

- No `DO NOT`/`NEVER` markers found in codebase
- Avoid hardcoding CSS classes — use enum variants
- Don't skip E2E tests for UI changes

## COMMANDS

```bash
./gradlew :lib:test              # Unit tests
./gradlew :example-app:run       # Demo server (localhost:8080)
cd e2e-tests && npm test         # E2E tests (requires server running)
```

## NOTES

- No published artifacts yet — use composite builds
- Kotlin version split: `gradle.properties` (2.3.10) vs `libs.versions.toml` (2.3.20) — reconcile if issues
- Missing root `build.gradle.kts` — config in subprojects only
