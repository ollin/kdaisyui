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

## Assumptions, and what would refute them

**Assumed:** that stacking is worth supporting. *Wrong if:* the measurement in task 1.1 finds no
real call site combining two variants, in which case a single-variant API is simpler and enough.

**Assumed:** that the variant set can be closed. *Wrong if:* Tailwind's variant list turns out to
be open-ended in practice — `group-hover`, `peer-checked`, arbitrary `data-*` — in which case the
type has to admit an escape hatch of its own, and the design must say so rather than pretend.
