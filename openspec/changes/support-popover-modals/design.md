# Design

## Decision: `customParts` entries gain a `staticAttributes` field

Task 1.1 established that `customParts` produces the right element and the right class and
stops exactly one attribute short. This change adds that one thing:

```json
"modal": [
  {
    "name": "Popover",
    "element": "DIV",
    "cssClass": "modal",
    "staticAttributes": { "popover": "" }
  }
]
```

`generateCustomPartFunction` emits one `attributes["k"] = "v"` line per entry, placed after
the `id` line and before `addClassNames(extraClasses)`. Ordering is not cosmetic: `attrs()`
still runs last, so a caller retains the ability to override a static attribute — the escape
hatch the rest of the DSL already promises.

**Verified:** DaisyUI documents the popover method as
`<div class="modal" popover id="my_modal">` — `daisyui/skills/daisyui/components/modal.md:39`
and `:52`. The attribute carries no value.

`popover` is an enumerated attribute whose empty string maps to the `auto` state, so
`attributes["popover"] = ""` rendering as `popover=""` is equivalent to the bare `popover` in
the DaisyUI source. **Assumed:** that kotlinx.html 0.12.0 renders an empty attribute value as
`popover=""` rather than dropping it. *Wrong if:* the generated test in task 2.2 does not find
the attribute in the rendered HTML — which is precisely what that test exists to check.

## Alternatives rejected

**Callers pass it through `attrs`.** Forbidden by the requirement, and rightly: a construction
method whose defining attribute is the caller's responsibility is not surfaced by the DSL, it
is merely not prevented by it. This is also what today's code effectively does, and it is the
defect.

**A `popover: true` boolean on `customParts`.** One HTML attribute promoted to a config
keyword. It would carry this change and nothing after it.

**`componentElements`.** Replaces a component's single root element rather than adding a second
one, so it cannot express "modal is *also* buildable as a div". It is what `dropdown` uses today
to pick the non-popover variant, which is a workaround for the same gap, not a solution to it.

## Naming

The generator composes `daisy${componentName}${part.name}`, so the entry named `Popover`
produces `daisyModalPopover`. It reads as "the popover part of a modal" where the truth is
"the modal, constructed the popover way". `daisyPopoverModal` is not expressible without
changing the naming scheme for every custom part, which is a bigger change than the one this
buys. The KDoc carries the distinction instead.

## `id` stays optional

A popover modal is unusable without an `id` — `popovertarget` has to reference it. Making the
parameter required would mean special-casing the otherwise fixed `customParts` parameter list,
and it would make this one wrapper's signature differ from every other generated wrapper for a
reason a reader would have to go looking for. It stays `HtmlId? = null`; the example-app route
in task 5.1 passes one, and the E2E in 5.2 is what proves the pairing works.

## Scope note: this gap is not modal-only

Found while checking the DaisyUI source for this decision — recorded rather than fixed here:

- **`megamenu` has the same defect today.** Its documented syntax is
  `<div class="megamenu …" id="…" popover>` (`megamenu.md:16`, `:34`) and it is opened by a
  `popovertarget` button. `daisyMegamenu` emits no `popover` attribute, so the component as
  generated cannot open.
- **`dropdown` dodged it.** `componentElements: { "dropdown": "details" }` exists because the
  popover variant is documented first and could not be generated usably.

Three components, one missing capability. That argues for the mechanism being general — which
`staticAttributes` on a config entry is — but **not** for widening this change to apply it
everywhere. Megamenu needs its own element decision, its own example-app route and its own E2E,
and folding that in here would put two behaviours under one proposal. It is a follow-up change.
