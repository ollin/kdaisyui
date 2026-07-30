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

**Every change enters through `gate.md`** — the first artifact of both schemas. It asks one
question, and refuses a yes/no answer:

> **Name the source and the cost.** Where exactly does the information live, and what does
> it take to fetch it?

Can you name both? Then the answer already exists — **complicated** — go get it and use
`spec-driven`. Can you not name a source? Then you have no research ahead of you, only a
guess — **complex** — use `probe-driven`. "Probably findable" and "we could analyse it" are
not sources.

**The default is complex.** Complicated has to be earned by naming a source; undecided
counts as complex. The two errors cost differently: treating complex work as complicated
produces confident plans that are wrong and expensive to unwind, while treating complicated
work as complex costs about twenty minutes before someone reads the manual.

**The agent does not decide the gate alone.** It lays out both sides with evidence and
stops; Oliver decides; the reasoning is recorded, not just the verdict.

| | Schema | Artifacts after the gate |
|---|---|---|
| complicated | `spec-driven` | proposal → specs → design → tasks |
| complex | `probe-driven` | hypothesis → probe → observations → learning |

`probe-driven` starts from coherence rather than justification, requires the amplify **and**
dampen signals to be written before the probe runs, and treats a probe that fails as a
successful probe — it bought information cheaply.

**"Done" means different things and each schema defines its own** (in its `apply`
instruction — `openspec/config.yaml` only holds what is true either way). Under
`spec-driven` it is the full suite including e2e, and a red test is a defect. Under
`probe-driven` it is a usable signal, and a signal that refutes the hypothesis is a good
outcome; the only real failure is **no** signal, which is a defect in the probe's design.
How much must still work scales with blast radius, declared in `probe.md` before running.

In `spec-driven`, every requirement must name its provenance: **Given** (decided outside
this project, with a citation) or **Earned** (a probe established it). **Assumed is not
allowed** — a guess written as SHALL gets cited, defended and generates work.

The gate is a checkpoint, not a lock. A plan that stalls because its assumed information
does not exist is **demoted** to a probe; a probe that reveals a stable pattern is
**promoted** to a spec. Both are appended to `gate.md` as dated entries. Neither is a
failure — being unsure at the start is the normal case.

`openspec new change` always scaffolds `spec-driven`; set `schema: probe-driven` in the
change's `.openspec.yaml` when the gate says so.

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
