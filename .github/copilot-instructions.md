# kdaisyui — Copilot Instructions

> **Single source of truth:** [AGENTS.md](../AGENTS.md) — read that for all project conventions, codegen rules, and anti-patterns.

## Critical Rules

- **Components are GENERATED** by codegen (`just generate`). Do NOT edit files in `lib/build/generated/`.
- Package is `io.github.ollin.kdaisyui.components` (NOT `com.github...`)
- Conventional commits required for PR titles (CI validates)
- Project version (SemVer) lives in `gradle.properties` → `version=`; bump it there, then tag `v<version>` to release via JReleaser

## Quick Reference

| What | Command |
|------|---------|
| Unit tests | `just test` |
| E2E tests | `just e2e` |
| All tests | `just test-all` |
| Dev server | `just dev` (http://localhost:8080) |
| Build | `just build` |
| Regenerate components | `just generate` |

See [AGENTS.md](../AGENTS.md) for full documentation.