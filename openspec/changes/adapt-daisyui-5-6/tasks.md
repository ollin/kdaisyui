## 1. Version bump + submodule sync (build stays green)

- [ ] 1.1 Bump `daisyui = "5.5.20"` → `"5.6.3"` in `gradle/libs.versions.toml` and update the catalog comment to note 5.6.3 is the highest webjar-backed version (refactoring; catalog only)
- [ ] 1.2 Bump `webjar-tailwindcss-browser = "4.3.0"` → `"4.3.1"` in `gradle/libs.versions.toml` (DaisyUI 5.6.3 build target) (refactoring; catalog only)
- [ ] 1.3 Run `just sync-daisyui` to check out the `daisyui` submodule at `v5.6.3` and commit the updated submodule pointer (refactoring; submodule pointer)
- [ ] 1.4 Run `just generate` and `./gradlew :lib:test` to confirm the existing 63 components still regenerate and build green at 5.6.3 before adding anything new (refactoring; verification, no new behavior)

## 2. Pick up new modifiers on existing components

- [ ] 2.1 Regenerate and inspect the diff to existing component wrappers; confirm the new modifiers (`range-vertical`, `tooltip-start/center/end`, `modal` popover) appear as new enum entries / params with backward-compatible defaults (documentation; record the surfaced modifiers in the change notes)
- [ ] 2.2 Run `./gradlew check`; for every NEW modifier branch on an existing component that drops coverage below 100%, extend the codegen test generator so the generated tests cover it; iterate until `:lib`+`:ktor-integration` aggregated coverage is 100/100 (feature-test; gated on `./gradlew check` green)

## 3. Wrap `aura` (trivial — div wrapper)

- [ ] 3.1 Regenerate; verify the generated `daisyAura()` wrapper renders `<div class="aura ...">` with all `aura-*` style+size classes and nested content, asserting against the DaisyUI HTML example (feature; verify generated output, add config in `codegen-config.json` ONLY if needed)
- [ ] 3.2 Run `./gradlew check`; ensure the generated tests cover every `aura` variant/size/extraClasses/attrs/content branch to 100% line+branch; extend `test-generator.js` if any branch is uncovered (feature-test; gated on `./gradlew check` green at 100/100)

## 4. Wrap `otp` (medium — label element + slot spans)

- [ ] 4.1 Add the `otp` config to `codegen-config.json` (element override to `LABEL`; slot-span / custom-part handling as the generated output requires), regenerate, and verify `daisyOtp()` renders `<label class="otp ...">` with the `otp-*` modifier/size/color classes (feature; element-override + config)
- [ ] 4.2 Run `./gradlew check`; ensure generated tests cover every `otp` modifier/size/color/extraClasses/attrs branch to 100% line+branch, extending `test-generator.js` for the `LABEL`/slot structure if needed (feature-test; gated on `./gradlew check` green at 100/100)

## 5. Wrap `megamenu` (complex — popover-based multi-part nav)

- [ ] 5.1 Add the `megamenu` config to `codegen-config.json` (custom parts for the popover trigger/menu sub-elements + the container `popover` attribute), regenerate, and verify `daisyMegamenu()` renders `<div class="megamenu ..." popover>` with `megamenu-*` modifier/direction/size classes and its sub-parts (feature; custom parts + popover attribute)
- [ ] 5.2 If the test generator cannot express the `popover`/custom-part structure, extend `codegen/src/generator-new.js` / `test-generator.js` to support it (refactoring; codegen capability — keep existing components' output unchanged)
- [ ] 5.3 Run `./gradlew check`; ensure generated tests cover every `megamenu` branch to 100% line+branch. ONLY if a branch is genuinely beyond the generator AND a hole remains, add ONE documented hand-written test file for `megamenu` with a written justification (last-resort exception per design D4) (feature-test; gated on `./gradlew check` green at 100/100)

## 6. Full coverage verification

- [ ] 6.1 Run the full `./gradlew check`; confirm the aggregated Kover report reads exactly 100% LINE and 100% BRANCH with the 3 new components + new modifiers present, and capture the evidence (documentation; evidence captured)
- [ ] 6.2 Confirm no new user-facing component class was added to a Kover `excludes` filter (the only allowed exclusion remains the synthetic `*$DefaultImpls`) (refactoring; audit, no code if clean)

## 7. E2E smoke coverage for the new components

- [ ] 7.1 Add example-app routes/fragments rendering `daisyAura`, `daisyOtp`, and `daisyMegamenu` (feature; demo wiring in `example-app`)
- [ ] 7.2 Add Playwright E2E assertions verifying each new component's expected class is present in the served HTML; run `just e2e` and confirm result files exist and the suite is green (feature-test; e2e proof)

## 8. Docs + version

- [ ] 8.1 Update `llms.txt` and `docs/reference.md` to include `aura`, `otp`, `megamenu` (66 components) (documentation)
- [ ] 8.2 Update the component count (63 → 66) and the pinned DaisyUI version references in `AGENTS.md` (documentation)
- [ ] 8.3 Bump the project SemVer `minor` in `gradle.properties` (new components = minor) (feature; user-visible version bump)
