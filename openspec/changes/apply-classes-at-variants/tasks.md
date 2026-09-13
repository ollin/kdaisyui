# Tasks

**Re-scoped 2026-09-13 to option C**, after block 1 measured that 68% of the prefixes land on
boolean parameters, which have no value to pass. Prefixing them requires values; the cheapest
honest way to get values for the groups that need them is the enum conversion already identified
as worth doing on its own, because `daisyTooltip(top = true, bottom = true)` compiles today.

**The original 2.8 h estimate is void, not revised** — it priced a different change. It stands in
the run file as the estimate for the change as first scoped, with the reason it was superseded.
Block 1's 0.2 h stands and was measured against.

New estimate frozen 2026-09-13, priced by open decisions per the calibration finding:

| Block | Open decisions | Estimate |
|---|---|---|
| 2 classify exclusivity | 1 — how axis-split placements are declared | 1.0 h |
| 3 exclusive groups become enums | 1 — naming, e.g. `CardLayout.Side` vs `CardSide` | 1.5 h |
| 4 constants for the two lone flags | 0 | 0.4 h |
| 5 the variant function | 1 — the call shape, carried over as 2.1 | 0.6 h |
| 6 the five genuine parameter gaps | 0 | 0.4 h |
| 7 migration note and docs | 0 | 0.6 h |
| 8 gate, evaluation | 0 | 0.5 h |
| | **Total** | **5.0 h** |

## 1. Measure before designing — 0.2 h — DONE

- [x] 1.1 `. d` Does real code stack two variants? **Refuted** — 0 in our code, 0 on a daisyUI
  class in DaisyUI's examples. One variant per application; the composition decision is gone.
- [x] 1.2 `. d` Is the variant set closeable? **Verified** — 63 prefixed daisyUI tokens use 8
  variants: seven breakpoints and `is-drawer-close`. Nothing open-ended reaches a daisyUI class.
- [x] 1.3 `. d` Triage the 19 unreached classes: 4 are not classes (#345), 2 belong to #342, 8
  are a missing `step-*` part, 5 are this change's business.
- [x] 1.4 `. d` **Added mid-block.** What kind of class receives a prefix? 16 enum-backed, **43
  boolean-backed**, 4 component classes. This is what re-scoped the change.
- [x] 1.5 `. d` **Added mid-block.** How much does option C cover? 11 of 19 directly, 17 with
  placements split by axis, 2 needing a constant. First run of this measurement was wrong and the
  error is recorded in `design.md` — it read `menu-vertical lg:menu-horizontal` as proof a group
  is not exclusive, from evidence that it is.

## 2. Classify exclusivity — 1.0 h

- [ ] 2.1 `. d` **Decide how a two-axis placement is declared.** DaisyUI files `indicator-top` and
  `indicator-start` under one category; they are a vertical and a horizontal axis and each is
  exclusive within itself. Config, or derived from the class names, or per-component override.
- [ ] 2.2 `^ F (internal)` Classify each group as exclusive or independent, and fail the run on a
  group the classifier cannot decide — silence here would reintroduce boolean flags by accident.
- [ ] 2.3 `. r (internal)` Pin the classification in a test against the measured numbers: 53 of 59
  exclusive, `avatar` and `table` modifiers independent.

## 3. Exclusive groups become enums — 1.5 h

- [x] 3.1 `. d` **Decide the naming.** Settled 2026-09-13 with Oliver: name by **intention, not
  implementation**. Three rules and two hard cases, in `design.md`:
  - `directions` → `Orientation`, one decision covering nine groups.
  - **The name is the test**: where no intention name exists the group is wrong, which catches the
    false positives the exclusivity measurement lets through (`Collapse` splits, `Menu` stays
    boolean).
  - Names go in `enumNames` in the config, like `docSummaries`, since DaisyUI supplies classes and
    not group names.
  - `ButtonEmphasis` for outline/dash/soft/ghost/link, and `ButtonLayout` for
    wide/block/square/circle — the latter recorded as the documented exception to the name-is-the-
    test rule.
- [ ] 3.1a `. d` Apply the name-is-the-test rule to the remaining 34 groups. Report which ones
  split or stay boolean, and name the rest. **A group nobody can name is a finding, not a naming
  problem.**
- [ ] 3.2 `^ F` Emit an enum per exclusive group instead of booleans. Test first, against
  `daisyTooltip(placement = TooltipPlacement.Top)` and against the illegal state no longer
  compiling.
- [ ] 3.3 `^ F` Regenerate; read the diff; update both API baselines, reading each.

## 4. The two lone flags — 0.4 h

- [ ] 4.1 `^ F` Generated constants for `drawer-open` and `megamenu-vertical`, which have no group
  to join and are prefixed in real use — `max-sm:megamenu-vertical` is in this repository's own
  example app.

## 5. The variant function — 0.6 h

- [ ] 5.1 `. d` **Decide the call shape.** Oliver chose a FUNCTION over a method, for IntelliJ
  completion from the parameter position — and because a method would force every generated enum
  to gain one. Open: argument order, name, and what it returns.
- [ ] 5.2 `^ F` The seven breakpoints, the states, and DaisyUI's drawer variants as values, with
  the function that applies one to a class value.
- [ ] 5.3 `^ F` Regenerate and prove the measured cases: `lg:btn-lg`, `max-sm:megamenu-vertical`,
  `xl:stats-horizontal`.

## 6. The genuine parameter gaps — 0.4 h

- [ ] 6.1 `^ F` `avatar-group`, `floating-label`, `image-full`, `join-item`, `list-row`. Defects
  from 1.3 go to their issues, not here.

## 7. Migration and documentation — 0.6 h

- [ ] 7.1 `. d` **How to migrate.** This is the largest break the library has shipped — every
  exclusive boolean group becomes an enum parameter. Name every affected function.
- [ ] 7.2 `. d` `README.md` usage example, and the `kdaisyui-codegen` skill.

## 8. Gate and adoption — 0.5 h

- [ ] 8.1 Full green per `openspec/config.yaml`, plus `:lib:checkComponentApi`.
- [ ] 8.2 `openspec validate --all --strict`.
- [ ] 8.3 Write the evaluation under `./tmp/` for Oliver to adopt.
