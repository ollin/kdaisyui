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

- [x] 7.1 `. F` `WhatsNewFragment.kt` renders all three on the dashboard, wired as
      `/fragments/whats-new` and htmx-loaded like the existing fragments
- [x] 7.2 `^ F` `WhatsNewFragmentTest.kt` — three Playwright tests asserting the emitted
      classes in the served HTML (`aura-lg`/`aura-rainbow`, `otp-primary`/`otp-joined` with
      six digit boxes, `megamenu-wide` with its `megamenu-active` popover and nested menu).
      E2E suite 34 → 37, full suite **1525 green**, `koverVerify` green.

      The megamenu test failed first, and on my own bug rather than the component's: I
      asserted `AriaRole.LINK`, but the menu anchors carry no `href` and therefore have no
      link role. Switched to a text assertion, matching how the rest of the suite handles the
      same pattern. Worth keeping in mind — a role-based assertion silently tests something
      other than what it appears to.

## 8. Docs + version

- [x] 8.1 `. d` Three new reference pages (`aura.md`, `otp.md`, `megamenu.md`), all three
      added to the `docs/reference/index.md` table and the `llms.txt` component table, counts
      63 → 66 in both.

      The version lines were **not** updated to the new numbers — they were replaced by
      pointers to `gradle/libs.versions.toml`. `llms.txt` claimed "DaisyUI 5.5.20" through two
      minor releases, and `docs/reference/index.md` plus both tutorials still sent readers to
      `gradle.properties → versions.kotlin`, a key that no longer exists. Restating a version
      in prose is what produced every one of those; AGENTS.md already forbids it.
- [x] 8.2 `AGENTS.md` no longer states a component count or a pinned version — it points at
      `gradle/libs.versions.toml` instead, which cannot go stale
- [x] 8.3 `. F` Bumped `0.1.0` → `0.2.0`: three new components make the next release a minor.

      Checking what that value actually does turned up a false comment, which I had also
      carried into the `kdaisyui-release` skill without verifying it. Both claimed *"JReleaser
      tags the git history with this value at release time"*. It is the other way round —
      `release.yml` triggers **on** a `v*` tag and passes `-Pversion` from the tag name, which
      overrides the file. The evidence was sitting in the releases list: **v0.1.1 and v0.1.2
      both shipped while `gradle.properties` read `0.1.0`.**

      So the bump is a declaration of intent for the next tag, not the thing that sets the
      version. Both files now say so.
