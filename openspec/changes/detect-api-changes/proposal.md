## Why

The 0.2.0 API delta was found by hand: diffing `lib/generated` between two commits and reading
the result. That worked, but only because someone thought to look. Nothing in the build or CI
would have objected if `TooltipVariant.Neutral` had disappeared silently.

Two things this project already relies on make the gap sharper:

- **The API is generated.** Nobody decides to remove an enum entry — DaisyUI does, and
  regeneration carries it in. The author of the commit may never see it.
- **The releases are automated.** A `v*` tag publishes to Maven Central without a human
  reviewing the API surface. A break reaches consumers at machine speed.

And the more dangerous change is the one a diff reads past. Generated parameters are sorted
alphabetically, so a new modifier is inserted into the *middle* of an existing signature —
`center` and `end` landed between `bottom` and `left` on `daisyTooltip` in 0.2.0. Every
modifier is `Boolean = false`, so a positional call site keeps compiling and changes meaning.
`TooltipVariant.Neutral` at least failed loudly.

## What Changes

- A mechanical check of the public API surface, run in CI, that fails when the API changes
  without the change being recorded.
- A rule that a detected breaking change requires a **How to migrate** entry in `README.md`
  before release — the section 0.2.0 now has, written on purpose rather than on recollection.

## Capabilities

### New Capabilities

- **api-change-detection** — how public API changes are noticed, recorded and communicated.

Specs are deliberately **not** written yet. What the requirements can promise depends on what
section 1 finds the available tooling actually detects; writing them first would be inventing
a contract and then shopping for a tool that satisfies it.

**So `openspec validate` fails on this change until task 3.1 writes them, and that is
expected.** Do not silence it with `skip_specs: true` — this change *does* have a spec-level
delta, it just is not knowable yet. Setting the flag would put a false statement in the
metadata to quiet a warning that is doing its job.

## Assumptions

1. **A suitable tool is available in this build.** Verified to exist:
   `org.jetbrains.kotlinx:binary-compatibility-validator` 0.18.1 (2025-07-09), which dumps the
   public API to committed `api/*.api` files and fails on divergence — the same
   committed-baseline-plus-CI-gate shape as `generated-sources`. **Assumed:** that Kotlin
   2.4.10's built-in `kotlin { abiValidation { } }` is also an option, which would avoid a
   dependency. *Wrong if:* the DSL does not exist in this Kotlin version. Not verifiable from
   the current environment — the Kotlin Gradle Plugin sources are not resolvable here, so an
   earlier "not found" was blindness, not absence.
2. **It catches the parameter-shift hazard, not just removals.** *Wrong if:* inserting a
   `Boolean = false` parameter in the middle of a generated function produces no change in the
   dump. Parameter *names* are not part of an ABI, but the synthetic `$default` overload's
   descriptor should change. This is the half worth having and the half most likely to fail —
   so it is checked before anything is wired into CI.
3. **A committed baseline is the right shape.** It matches `generated-sources` and needs no
   network. *Wrong if:* comparing against the last artifact published to Maven Central
   (japicmp, Revapi) answers the more useful question — "what changed since the release" rather
   than "since the last dump" — for a library whose API moves only when DaisyUI moves.

## Impact

- `build.gradle.kts` or `lib/build.gradle.kts` (whichever the chosen mechanism wants), a
  committed API baseline file, one CI job, and a documented rule in `AGENTS.md`.
- No change to the library's behaviour or its published artifacts.

**Deliberately out of scope**
- Changing the generated parameter ordering to make signatures append-only. That would remove
  the hazard at its root instead of detecting it, and it is a codegen decision with its own
  cost — worth its own change if detection proves the problem recurs.
