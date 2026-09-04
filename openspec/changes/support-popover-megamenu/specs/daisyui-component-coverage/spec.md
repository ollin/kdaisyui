## ADDED Requirements

### Requirement: Megamenu is constructible as DaisyUI documents it
The library SHALL provide generated wrappers sufficient to build DaisyUI's documented megamenu:
a root carrying the `megamenu` class **and** the `popover` attribute, a mandatory
`megamenu-active` indicator, and one popover panel per item. The panels SHALL be reachable from
the DSL even though they carry no DaisyUI class. None of it SHALL require the caller to set the
`popover` attribute through the raw-attribute escape hatch.

**Verified:** `daisyui/skills/daisyui/components/megamenu.md:16` documents the root as
`<div class="megamenu … {MODIFIER} …" id="my-megamenu-1" popover>`, `:19`-`:22` document each item
as a `popovertarget` button paired with `<div id="item-1" popover>`, and `:62` states that the
`megamenu-active` span is mandatory. `lib/generated/…/components/Megamenu.kt` emits no `popover`
attribute anywhere, so the component as generated cannot open.

**Assumed:** that the root's `popover` is unconditional rather than viewport-dependent. *Wrong
if:* a `<div class="megamenu" popover>` cannot be made visible at a desktop viewport from
generated markup alone — DaisyUI hides the trigger button with `sm:hidden` at that breakpoint and
describes the megamenu as "a horizontal menu" there. Checked first, in a browser, because
`megamenu.css` is too intricate to settle by reading.

#### Scenario: The documented markup is reachable
- **WHEN** a megamenu is built from the generated wrappers
- **THEN** the root element carries the `megamenu` class and the `popover` attribute
- **AND** each item's panel is a generated wrapper carrying `popover` and its own id
- **AND** no caller supplies `popover` themselves

#### Scenario: A megamenu opens without JavaScript
- **WHEN** the example application serves a megamenu and its `popovertarget` trigger is clicked
  in a browser with JavaScript disabled
- **THEN** the corresponding panel reaches the `:popover-open` state

### Requirement: A construction method is not delivered until a consumer uses it
A generated wrapper for an alternative construction method SHALL be exercised by the example
application and asserted end-to-end before the change that adds it is considered complete.

This is stated as a requirement rather than left as practice because the failure it prevents has
now occurred twice. A construction method that introduces no new CSS class is invisible to the
generated tests, which assert class strings; to `generated-sources-drift`, which compares the
generator's output against itself; and to the coverage gate, which cannot miss code that was
never generated. A consumer is the only check that fails.

**Verified:** `modal`'s popover method was recorded as delivered by the archived
`adapt-daisyui-5-6` while nothing implemented it, and `megamenu` carries the same defect today
while the example app renders it and the E2E asserts it — passing, because both only look at
classes.

#### Scenario: Absence is detected by a consumer
- **WHEN** a component gains a construction method that adds no new CSS class
- **THEN** an example-app route uses it
- **AND** an end-to-end assertion covers the attribute that defines it, not only its classes
