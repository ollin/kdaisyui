## Context

kdaisyui is a Kotlin 2.4 / Gradle multi-module library that wraps DaisyUI components as type-safe `FlowContent.daisyXxx()` DSL functions. Components are **generated** by the codegen pipeline (`codegen/src/`) from the DaisyUI git submodule's per-component docs (`daisyui/packages/docs/src/routes/(routes)/components/<name>/+page.md`), driven by `codegen-config.json`. We are pinned to DaisyUI **5.5.20** (63 wrapped components; `accordion` + `pagination` skipped). DaisyUI 5.6 is out.

Two facts shape this design, both established by research against the DaisyUI repo + Maven Central:

1. **Latest is 5.6.6, but the Maven webjar caps at 5.6.3.** The catalog rule (`gradle/libs.versions.toml`) requires pinning only a DaisyUI version that has a published `org.webjars.npm:daisyui` webjar, so generated class names never reference CSS absent from the served webjar. 5.6.4–5.6.6 are pure bugfixes (no new components, no class renames), so 5.6.3 already contains the entire in-scope delta.
2. **The delta is additive: three new components** — `aura`, `otp`, `megamenu` (introduced in 5.6.0) — plus new modifiers on existing components (`range-vertical`, `tooltip-start/center/end`, `modal` popover). Nothing removed; no class renamed.

The decisive constraint that did NOT exist at the last DaisyUI bump: the **hard 100% line+branch coverage gate** (capability `coverage-enforcement`) is now active and bound to `check`. Every new component and every new modifier branch must be fully covered by the **generated** tests, or `./gradlew check` fails.

## Goals / Non-Goals

**Goals:**
- Pin DaisyUI to 5.6.3 (highest webjar-backed version) and Tailwind webjar to 4.3.1, sync the submodule, regenerate.
- Wrap the 3 new components as generated DSL functions, configured the minimum amount needed.
- Surface the new modifiers on existing components automatically via regeneration.
- Keep aggregated `:lib` + `:ktor-integration` coverage at **100% line AND branch** — new behavior covered by the codegen test generators, not hand-written tests.
- Provide E2E smoke coverage for the 3 new components (example-app + Playwright).
- Update docs (`llms.txt`, `docs/reference.md`, `AGENTS.md` count) and bump the project SemVer minor.

**Non-Goals:**
- Reaching 5.6.4–5.6.6 (no webjar; no new components). Revisit when a later webjar publishes.
- Wrapping `accordion` / `pagination` (still skipped — out of scope, unchanged decision).
- Changing the Ktor integration API, the publishing pipeline, or the coverage-gate mechanism itself.
- Hand-writing component wrappers or component tests (the codegen route is mandatory per AGENTS.md; a hand-written escape hatch is a last resort only — see D4).

## Decisions

### D1 — Pin to DaisyUI 5.6.3, Tailwind 4.3.1
`daisyui = "5.6.3"` in the catalog (webjar exists), `webjar-tailwindcss-browser = "4.3.1"` (DaisyUI 5.6.3's build target; 4.3.x patches are non-breaking). The submodule `v5.6.3` tag is already fetched locally. `just sync-daisyui` checks it out; `just generate` regenerates.
**Alternative considered:** chase 5.6.6 and wait for/insist on a webjar — rejected: violates the catalog rule and gains nothing (bugfixes only).

### D2 — Convention-first generation; config only for deviations
The generator auto-discovers any component dir with a `+page.md` and classifies its frontmatter into known categories (`color`, `style`, `size`, `modifier`, `behavior`, `part`, `direction`, `placement`). The three new components map to existing categories, so the default path produces wrappers. Per-component config is added ONLY where structure deviates:
- **`aura`**: `<div class="aura …">` wrapping arbitrary content; categories `style` + `size`. Expected to need **no** config — verify the generated output renders correctly and move on.
- **`otp`**: `<label class="otp">` containing slot `<span>`s and a child `<input>`; categories `modifier` + `size` + `color`. The element is `LABEL` (not the default `div`, and not a self-closing input). Needs an element-override (the `subComponentElements`-style mechanism) and possibly `customParts` for the slot spans. The `<input>` is a CHILD, so `otp` itself is NOT an `inputTypes`/`noContent` entry.
- **`megamenu`**: popover-based mega-nav — `<div class="megamenu" popover>` + `megamenu-active` span + nested `popover` trigger/menu pairs; categories `modifier` + `direction` + `size`. Most complex: needs `customParts` (like `breadcrumbs`/`dropdown`) and the HTML `popover` attribute on the container.
**Alternative considered:** hand-write all three — rejected: breaks the "components are generated" rule and the regenerable-tests property the coverage gate relies on.

### D3 — New modifiers on existing components come free via regeneration
`range-vertical`, `tooltip-start/center/end`, and the `modal` popover attribute are declared in the updated frontmatter, so regeneration adds them as new enum entries / boolean params on the existing wrappers automatically. No manual wrapper edits. They DO add new branches that the coverage gate will demand tests for (handled by D5).

### D4 — Coverage is met by extending the codegen TEST generators, never by hand-written tests
The component test generator (`codegen/src/test-generator.js`) already parses generated `.kt` signatures and emits exhaustive branch tests (every boolean=true, id, extraClasses, attrs, text/content, every enum arm). For the 3 new components and the new modifier branches, the generated tests must reach 100% line+branch. The generator is EXTENDED where a new structure isn't yet expressible (e.g. `megamenu`'s `popover` attribute, `otp`'s slot spans / `LABEL` element, custom parts). Only if a component's structure is genuinely beyond the generator AND a coverage hole remains is a single hand-written test file for THAT component allowed — and that is a documented exception, not the default.
**Alternative considered:** a documented Kover exclusion for hard-to-cover new code — rejected: exclusions are reserved for provably-unreachable synthetic code (the existing `*$DefaultImpls`), not for new user-facing components.

### D5 — Staged rollout that keeps `check` green at every commit
The coverage gate makes a "regenerate everything then fix later" approach fail loudly mid-change. Stage it so each commit is green:
1. Version bump + submodule sync + regenerate, configuring components one at a time. After each new component is wrapped, regenerate the component tests and run `./gradlew check` — only commit when 100/100 holds.
2. Cover new modifier branches on existing components in the same gated way.
3. E2E + docs + SemVer bump last.
Each task = one Risk-Aware (Arlo) commit. A component whose tests don't yet reach 100% is NOT committed until its generator support lands.

### D6 — E2E smoke coverage per convention
Add example-app routes rendering each new component and Playwright assertions (the existing in-process-Ktor + Kotest pattern). E2E is required for UI changes by project convention; it is separate from the unit coverage gate (`:e2e-tests` is out of the coverage scope) but is part of the full verification gate.

## Risks / Trade-offs

- **`megamenu`'s popover structure may exceed the generator** → Mitigation: D4 — extend `test-generator.js`/`generator-new.js` for the `popover` attribute first; if a coverage hole genuinely remains, a single documented hand-written test for `megamenu` only, recorded as an explicit exception. Treat `megamenu` as the schedule risk and sequence it last among the three.
- **New modifier branches silently drop coverage below 100%** → Mitigation: the gate catches this automatically; D5 runs `check` after every regeneration so no commit lands red. Inspect the per-class Kover detail to find any missed new branch.
- **Webjar 5.6.3 vs generated 5.6.3 class drift** → Mitigation: same source pin for both (catalog drives submodule tag AND webjar version); regeneration uses exactly the submodule the webjar mirrors.
- **Tailwind 4.3.0 → 4.3.1 visual regressions** → Mitigation: patch bump, no breaking changes; the E2E screenshots catch rendering regressions.
- **Generator change accidentally alters existing 63 components' output** → Mitigation: regenerate all, run the full suite + coverage; any diff to existing component tests/output is reviewed before commit (the generator extensions must be additive).

## Migration Plan

1. Bump catalog (`daisyui` 5.6.3, tailwind webjar 4.3.1); `just sync-daisyui`; `just generate` — build stays green.
2. Wrap + configure the 3 new components one at a time (`aura` → `otp` → `megamenu`), regenerating + `check` after each, extending codegen where needed, until aggregated coverage is 100/100.
3. Confirm new modifiers on existing components are covered (regen + `check`).
4. Add example-app routes + E2E for the 3 new components; run the full suite incl. e2e.
5. Update `llms.txt`, `docs/reference.md`, `AGENTS.md` (66 components); bump `gradle.properties` SemVer minor.

**Rollback:** revert the catalog + submodule-pointer commit to drop back to 5.5.20; the generated output and tests regenerate from whatever the submodule points at. No effect on already-published artifacts.

## Open Questions

- Does `otp` need only a `LABEL` element-override, or also `customParts` for the slot `<span>`s? Resolved during D2 step 2 against the real generated output, not pre-decided.
- Can the test generator cover `megamenu`'s `popover` parts without a hand-written supplement? Resolved during D4 against the real Kover report; the answer decides whether the documented hand-written exception is needed.
