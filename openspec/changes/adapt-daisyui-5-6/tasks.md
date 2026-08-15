> **Reality check, 2026-08-15 (from `rejoin-main` task 6.2).** Most of this change happened
> without being ticked here, and it happened at **5.7.16**, not 5.6.3 — the version this plan
> treated as a hard ceiling was overtaken twice. Sections 1-6 are done and verified below.
> Sections 7 and 8 are **genuinely outstanding**: neither `example-app` nor the E2E suite
> mentions `aura`, `otp` or `megamenu`, and the docs still say 63 components / DaisyUI 5.5.20.
>
> So this change is neither finished nor untouched. It is not archivable as it stands —
> archiving would publish a requirement pinning 5.6.3 and claim E2E coverage that does not
> exist.

## 1. Version bump + submodule sync (build stays green)

- [x] 1.1 **Superseded**: `daisyui = "5.7.16"` in `gradle/libs.versions.toml`, past the 5.6.3
      this task assumed was the ceiling
- [x] 1.2 **Superseded**: `webjar-tailwindcss-browser = "4.3.3"`
- [x] 1.3 Submodule is at the matching tag; pointer committed in `2297257`
- [x] 1.4 Regenerated and green — 1488 tests, `koverVerify` at 100/100

## 2. Pick up new modifiers on existing components

- [x] 2.1 Confirmed in the committed diff: `range-vertical` and `tooltip-start/center/end`
      appear as new params with `false` defaults. **One deviation this task did not predict:**
      `TooltipVariant.Neutral` was *removed* — DaisyUI dropped `tooltip-neutral`, so the change
      is not purely additive as the proposal claims
- [x] 2.2 Aggregated coverage is 100/100 with the new modifiers present; no generator change
      was needed for them

## 3. Wrap `aura` (trivial — div wrapper)

- [x] 3.1 `Aura.kt` generated and committed; no `codegen-config.json` entry was needed
- [x] 3.2 Covered by the generated `AuraTest.kt` + `AuraCoverageTest.kt`; gate green

## 4. Wrap `otp` (medium — label element + slot spans)

- [x] 4.1 `Otp.kt` generated and committed. The predicted `LABEL` element override was not
      needed — the heuristic resolved it without config
- [x] 4.2 Covered by `OtpTest.kt` + `OtpCoverageTest.kt`; gate green

## 5. Wrap `megamenu` (complex — popover-based multi-part nav)

- [x] 5.1 `Megamenu.kt` generated and committed; no config entry was needed
- [x] 5.2 No generator extension was required
- [x] 5.3 Covered by `MegamenuTest.kt` + `MegamenuCoverageTest.kt`; gate green, and **no**
      hand-written test file was needed — the last-resort exception in design D4 stays unused

      Worth carrying forward: sections 3-5 each predicted config or generator work, and none
      of it was necessary. The three components came out of regeneration unaided. What *did*
      need a `codegen-config.json` entry was a component nobody flagged — `dropdown`, whose
      element heuristic picked an unusable variant at 5.7.16.

## 6. Full coverage verification

- [x] 6.1 `koverVerify` passes at 100% line and 100% branch with all three components present,
      1488 unit tests / 1522 including e2e
- [x] 6.2 Audited: `*$DefaultImpls` remains the only exclusion in `build.gradle.kts:48-52`

## 7. E2E smoke coverage for the new components

- [ ] 7.1 Add example-app routes/fragments rendering `daisyAura`, `daisyOtp`, and `daisyMegamenu` (feature; demo wiring in `example-app`)
- [ ] 7.2 Add Playwright E2E assertions verifying each new component's expected class is present in the served HTML; run `just e2e` and confirm result files exist and the suite is green (feature-test; e2e proof)

## 8. Docs + version

- [ ] 8.1 Update `llms.txt` (still says "DaisyUI 5.5.20" at line 561) and `docs/reference.md`
      (still says "63 components" at line 7) to include `aura`, `otp`, `megamenu` and the real
      pinned version (documentation)
- [x] 8.2 `AGENTS.md` no longer states a component count or a pinned version — it points at
      `gradle/libs.versions.toml` instead, which cannot go stale
- [ ] 8.3 Bump the project SemVer `minor` in `gradle.properties` — note it is `version=0.1.0`
      now, and JReleaser derives the release version from the git tag (feature)
