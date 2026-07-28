# kdaisyui — Copilot Instructions

> **Single source of truth:** [AGENTS.md](../AGENTS.md) — conventions, codegen rules, tooling
> and anti-patterns all live there.

## Critical rules

- **Components, icons and most tests are GENERATED.** Never edit `lib/build/generated/**`;
  change the codegen pipeline instead.
- Package is `io.github.ollin.kdaisyui` — never `com.github...`.
- `daisyui.version` in `gradle.properties` is the single source of truth for the submodule tag,
  the codegen input **and** the webjar CSS served by the example app. Never hardcode a DaisyUI
  version anywhere else.
- PR titles must follow Conventional Commits — `pr-conventional-commits.yml` enforces it.
- **There is no release automation.** Do not assume release-please owns the version; it is not
  configured in this repo.

## Common tasks

| What | Command |
|---|---|
| Unit tests | `just test` |
| E2E tests | `just e2e` |
| All tests | `just test-all` |
| Dev server | `just dev` → http://localhost:8080 |
| Build | `just build` |

A normal Gradle build already regenerates components (`compileKotlin dependsOn
generateComponents, generateHeroicons`). `just generate` is a separate npm path and is not
required for a normal build.

See [AGENTS.md](../AGENTS.md) for everything else.
