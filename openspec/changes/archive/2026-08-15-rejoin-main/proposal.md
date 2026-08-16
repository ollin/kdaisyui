## Why

`ollins-stuff-at-home` is 168 commits behind `origin/main` and has diverged into a different
project. This was discovered on 2026-08-15 while fast-forwarding local `main` during
`cleanup-dead-code`, *after* 77 commits had already been built on the stale base.

| | `ollins-stuff-at-home` | `origin/main` (`0034960`) |
|---|---|---|
| DaisyUI | 5.5.20, with a documented hard ceiling | **5.7.16** — the ceiling is gone, `llms-txt.js` was reworked |
| Version source | everything in `gradle.properties` | `gradle/libs.versions.toml`; `gradle.properties` holds only `version=0.1.0` |
| Version scheme | `5.5.20-SNAPSHOT`, derived from DaisyUI | `0.1.0` semantic, JReleaser derives it from the git tag |
| Release | none | **JReleaser 1.25.0, `bom/`, root `build.gradle.kts`, `release.yml`** |
| Coverage | none | **`koverVerify`, an aggregated 100% gate in CI** |
| Kotlin / Ktor | 2.3.10 / 3.5.0 | 2.4.10 / 3.5.2 |
| Heroicon tests | none | `test-generator-heroicons.js` |
| Generated sources | **committed, with a CI drift job** | build output only |

The last row is the only one where this branch is ahead, and it is the whole of
`commit-generated-sources`. Everything else is catching up.

Three statements this branch makes about the project are false of the project, and stay
false until this change lands: that there is no release automation (`AGENTS.md`, the
`kdaisyui-release` skill), that DaisyUI cannot go past 5.5.20 (`gradle.properties`,
`renovate.json`, `AGENTS.md`, the `kdaisyui-codegen` skill), and that
`gradle.properties` is the single source of truth for versions.

## What Changes

- Merge `origin/main` into `ollins-stuff-at-home`, keeping this branch's committed generated
  sources and drift job while adopting main's build, release and coverage setup.
- Re-apply the `generated-sources` capability on top of main's `lib/build.gradle.kts`, which
  still couples compilation to the generators.
- Regenerate `lib/generated` from main's codegen at DaisyUI 5.7.16 — different inputs, a
  reworked parser, and a heroicon test generator this branch does not have.
- Correct the four documents that assert the ceiling and the absent release automation.

**Deliberately out of scope**
- Any release itself. This change makes the branch buildable and releasable, it does not cut
  a version.
- The remaining `cleanup-dead-code` items, which stay valid: main still carries the orphaned
  `codegen/src/config/*.yml` and an unused `js-yaml`.

## Assumptions

1. **The merge is mechanical enough to resolve by hand.** *Wrong if:* the conflicts in
   `lib/build.gradle.kts`, `ci.yml` and `settings.gradle.kts` are so entangled that
   reconstructing this branch's four commits on top of main is cheaper than merging. Task 1.1
   settles this in minutes on a throwaway branch, before anything is committed.
2. **The `generated-sources` capability survives unchanged.** Its four requirements were
   verified against *this* branch's build. *Wrong if:* main's build script or coverage gate
   makes "the build does not run the generators" unworkable — for instance if `koverVerify`
   needs a generation step.
3. **DaisyUI 5.7.16 regenerates cleanly here.** Main runs it, so the parser rework works
   there. *Wrong if:* regeneration after the merge fails or produces output that does not
   compile — the submodule jumps seven minor versions.
4. **100% coverage is reachable with 450 committed generated files.** Main pairs the gate
   with `test-generator-heroicons.js`, so the intent is clearly that icons carry tests.
   *Wrong if:* `koverVerify` still fails after regenerating with main's codegen. **Oliver
   accepted this risk explicitly on 2026-08-15**; it is recorded as an assumption rather than
   waved away because it is the one most likely to cost real work.

## Impact

- Every build file, both workflows, the four documents named above, and `lib/generated` in
  full.
- Nothing is pushed yet; all 77 commits of this branch are local, so the merge can be
  attempted and thrown away at no cost.
