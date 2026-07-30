# kdaisyui — Agent Guide

Type-safe DaisyUI component DSL for Kotlin server-rendered HTML (kotlinx.html). Wraps Tailwind
/ DaisyUI class strings into compile-time-checked extension functions.

Exact versions live in `gradle.properties`, `.tool-versions` and
`gradle/wrapper/gradle-wrapper.properties` — never restate them here.

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

`daisyui.version` in `gradle.properties` is the single source of truth for the submodule tag,
the codegen input and the webjar CSS. It has a hard upper bound — the reason is in that file's
comment and in `kdaisyui-codegen`.

## Skills

| Task | Skill |
|---|---|
| Codegen, component shape, config knobs, adding a component, version ceiling | `kdaisyui-codegen` |
| Any test work, run configurations, `just` recipes, E2E wiring, Cucumber | `kdaisyui-testing` |
| Versioning, publishing, what CI does | `kdaisyui-release` |

## Planning changes — OpenSpec

Non-trivial changes are planned as OpenSpec changes under `openspec/changes/<name>/`.
Slash commands: `/opsx-propose`, `/opsx-apply`, `/opsx-archive`.

**Two schemas, and they are stages rather than alternatives.** Pick by one question:
*do we already know what to build?*

| | Schema | Artifacts |
|---|---|---|
| Yes — the answer is knowable, analysis will find it | `spec-driven` | proposal → specs → design → tasks |
| No — we find out by doing | `probe-driven` | hypothesis → probe → observations → learning |

`probe-driven` (`openspec/schemas/probe-driven/`) is for complex work, where treating an
unknown as if it were merely unresearched produces confident plans that turn out wrong. It
starts from coherence rather than justification, requires the amplify **and** dampen signals
to be written before the probe runs, and treats a probe that fails as a successful probe —
it bought information cheaply. Set it with `schema: probe-driven` in the change's
`.openspec.yaml`.

When a probe reveals a stable, repeatable pattern, that pattern has moved into knowable
territory: `learning.md` names it, and a `spec-driven` change records it.

**`openspec/config.yaml` carries the planning rules** — the project context, per-artifact
rules, and the guidance served when implementation or archival starts (what "green" means
here, the commit cadence, archive-on-the-implementation-branch). Read it rather than
guessing.

A change with no spec-level behaviour delta — pure tooling, refactoring or docs — sets
`skip_specs: true` in its `.openspec.yaml`. Never invent a requirement just to satisfy
`openspec validate`; that puts a false statement into `openspec/specs/`.

## Anti-patterns

- Editing `lib/build/generated/**`
- Hardcoding CSS class strings instead of using the generated enums
- Hardcoding a DaisyUI, Kotlin or Ktor version anywhere but `gradle.properties`
- Assuming release automation exists — it does not, see `kdaisyui-release`
- Package `com.github.ollin`
- Shipping a UI change without E2E
