# Apply a typed class at a variant

## Why

The library types 536 of DaisyUI's 555 classes — as enum entries and boolean parameters — and then
makes you abandon all of it the moment you want one at a breakpoint:

```kotlin
daisyButton(size = ButtonSize.Lg)                     // typed
daisyButton(extraClasses = "lg:btn-lg")               // the same class, one breakpoint later
```

The second line is a string. Nothing checks it. `lg:btn-lgg` compiles, ships, and silently styles
nothing — and this repository has already shipped that exact class of defect four times in
`verify-generator-assertions`.

**Measured, on 2026-09-12, over every `extraClasses` and `addClassNames` call in `example-app`,
`e2e-tests`, `lib/src` and `docs`:**

| | count |
|---|---|
| distinct tokens passed | 47 |
| a known DaisyUI class | 12 |
| **carrying a variant prefix** | **12** |
| not a DaisyUI class at all (Tailwind utilities) | 35 |

Ten of the twelve DaisyUI tokens carry a prefix — `lg:btn-lg`, `max-sm:megamenu-vertical`,
`xl:stats-horizontal`. So the escape hatch is being used overwhelmingly for two things: Tailwind
utilities, which this library has no business typing, and **typed DaisyUI classes that merely need
a prefix**, which it already has everything to type.

This is also the gap `css-delivery` was written about from the other side: `max-sm:card-side` and
`dark:alert-info` once silently did nothing, because DaisyUI's prebuilt stylesheet shipped only
five variant prefixes. The CSS is compiled correctly now. The API still cannot say it.

## Re-scoped 2026-09-13 — this change now also converts exclusive groups to enums

Block 1 measured that **68% of prefixes land on a boolean parameter**, which has no value to pass
to anything. So a variant API alone cannot serve the majority of its own use case.

Getting values for those classes is the enum conversion this repository already had reason to
want: `daisyTooltip(top = true, bottom = true)` compiles today and emits two contradictory
classes. 53 of 59 class groups are mutually exclusive, measured. Making them enums fixes the
illegal state **and** produces the values the variant function needs.

Both halves are one change because neither is much use alone. See `design.md`.

## What Changes

- **A variant type**, covering Tailwind breakpoints (`sm`…`2xl`, and the `max-*` forms), states
  (`hover`, `focus`, `active`, `disabled`, `dark`, …) and DaisyUI's own (`is-drawer-open`,
  `is-drawer-close`).
- **A way to apply a typed class at one or more of them**, so `ButtonSize.Lg` and `Breakpoint.Lg`
  compose into `lg:btn-lg` without a string.
- **Stacking**, because Tailwind stacks: `dark:` and `md:` can both apply to one class. Breakpoints
  are mutually exclusive with each other; states are not.
- The 19 classes no typed parameter reaches today are **triaged**, and the ones that are defects
  are fixed rather than papered over with a new escape hatch.

## Capabilities

- `daisyui-component-coverage` — a class this library types can be applied at any variant DaisyUI
  supports, without leaving the type system.

## Impact

- **Additive, not breaking.** `extraClasses: String?` stays exactly as it is — it is the right
  tool for the 35 Tailwind utilities, and narrowing it would be the wrong lesson from this
  measurement.
- **New public API**, so `lib/api/lib.api` and `lib/api/components.api` both move.
- **Deliberately NOT an enum of all 555 classes**, which was the first shape proposed. Measured:
  536 are already reachable through a typed parameter, so such an enum would restate the existing
  API 536 times and still not express a prefix. See `design.md` decision 1.

## Assumptions — both measured 2026-09-13, one refuted

**Assumed:** that stacking is worth supporting. **REFUTED.** Zero stacked variants on a DaisyUI
class, in our code and in DaisyUI's own examples. Four DaisyUI examples do stack, and every one
applies the stack to a Tailwind utility. Dropped: one variant per application.

**Assumed:** that the variant set can be closed. **Verified**, for this surface. 63 prefixed
DaisyUI tokens in DaisyUI's examples use exactly 8 distinct variants — seven breakpoints and
`is-drawer-close`. Nothing open-ended reaches a DaisyUI class.

**A third question the measurement answered without being asked:** DaisyUI applies **no** state
variant to its own classes. States are in scope anyway, on Oliver's instruction and on one
recorded case — `AGENTS.md` names `dark:alert-info` as a thing that silently did nothing before
`css-delivery` fixed the stylesheet. One attested use, zero documented ones. Thin, but it is
evidence, and dropping stacking makes a state cost the same as a breakpoint.
