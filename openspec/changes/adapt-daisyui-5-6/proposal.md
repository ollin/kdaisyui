## Why

DaisyUI shipped 5.6 (latest 5.6.6; we are pinned to 5.5.20), which adds three new components — `aura`, `megamenu`, `otp` — and new modifiers on existing components (`range-vertical`, `tooltip-start/center/end`, the `modal` popover attribute). kdaisyui's value is being a *complete*, type-safe wrapper over DaisyUI; falling a minor version behind means downstream users cannot reach the new components from Kotlin. We adapt now while the delta is small and purely additive (no components removed, no class renames).

## What Changes

- Bump DaisyUI from **5.5.20 → 5.6.3** in `gradle/libs.versions.toml`. **HARD CONSTRAINT:** 5.6.3 is the highest DaisyUI version with a published Maven webjar (`org.webjars.npm:daisyui`); 5.6.4–5.6.6 have no webjar yet and are pure bugfixes with no new components, so 5.6.3 already contains everything in scope.
- Bump the Tailwind webjar **4.3.0 → 4.3.1** (the exact Tailwind version DaisyUI 5.6.3 was built against).
- Sync the `daisyui` git submodule to `v5.6.3` and regenerate all components from it.
- Wrap the **3 new components** as generated `FlowContent.daisyXxx()` DSL functions: `aura` (border-light wrapper), `otp` (one-time-password input group), `megamenu` (popover-based mega navigation). Component count goes 63 → 66.
- Pick up the **new modifiers on existing components** that the regeneration surfaces automatically from frontmatter (e.g. `range-vertical`, `tooltip-start/center/end`, `modal` popover) — these become new enum entries / boolean params on the existing wrappers.
- Extend the codegen and `codegen-config.json` only where the 3 new components deviate from convention (notably `otp`'s `<label>` element + slot spans, and `megamenu`'s custom popover sub-parts).
- **Keep the hard 100% line+branch coverage gate green.** Every new component and every new modifier branch MUST be exercised by the codegen test generators so `./gradlew check` stays at 100/100 — this is a first-class acceptance criterion of this change, not an afterthought.
- Add E2E smoke coverage (example-app routes + Playwright assertions) for the 3 new components, per the project convention that UI changes ship with E2E.
- Update user-facing docs (`llms.txt`, `docs/reference.md`) and the contributor component count (`AGENTS.md`), and bump the project SemVer `minor` in `gradle.properties`.
- **Non-breaking:** purely additive. The published API only gains symbols; existing wrappers gain optional params/enum entries with backward-compatible defaults. No artifact, package, or API removal.

## Capabilities

### New Capabilities
- `daisyui-component-coverage`: kdaisyui exposes a type-safe Kotlin DSL wrapper for every non-skipped DaisyUI component in the pinned DaisyUI release, generated from the DaisyUI source, fully exercised by generated tests, and pinned to a DaisyUI version that has a published Maven webjar. This change establishes that capability and brings it up to DaisyUI 5.6.3 (adds `aura`, `otp`, `megamenu`).

### Modified Capabilities
<!-- None. The existing `coverage-enforcement` capability is NOT modified — its requirements are unchanged; this change must continue to satisfy them (the new components/branches keep aggregated coverage at 100%). -->

## Impact

- **Versions/build**: `gradle/libs.versions.toml` (`daisyui`, `webjar-tailwindcss-browser`); `gradle.properties` (`version` minor bump); the `daisyui` git submodule pointer (→ `v5.6.3`).
- **Codegen**: `codegen/codegen-config.json` (per-component config for `otp`, `megamenu`; `aura` likely needs none); possibly `codegen/src/classifier.js` / `generator-new.js` / `test-generator.js` if a new component's structure (e.g. `popover` attribute, slot spans) isn't yet expressible.
- **Generated sources/tests**: `lib/build/generated/` gains 3 component files + their generated tests; existing component files gain new modifier branches.
- **Coverage**: the aggregated Kover gate (`:lib` + `:ktor-integration`) must remain 100% line+branch — new branches require new generated test coverage.
- **E2E / demo**: `example-app` routes + `e2e-tests` for the 3 new components.
- **Docs**: `llms.txt`, `docs/reference.md`, `AGENTS.md` (component count 63 → 66).
- **No impact** on the publishing pipeline, the Ktor integration API, or downstream consumers beyond newly available symbols.
