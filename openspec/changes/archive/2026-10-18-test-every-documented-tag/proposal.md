## Why

Every element DaisyUI documents is now expressible by its classes and has a generated test, but the
test does not look at the tag. 357 of the tested elements sit on another tag than the function the
test calls renders: `<a class="btn">` is tested as `daisyButton`, a `<button>`. So the claim "kdaisyui
can generate what DaisyUI documents" still holds only for the classes. Oliver decided on 2026-10-18
that the gate checks the element too.

Measured the same day at the pinned tag, it also hides a defect: `daisyCalendar` renders
`<div class="cally">`, and `cally` styles the `<calendar-date>` web component, which a `<div>` is
not (#343).

## What Changes

- **Every documented-element test renders DaisyUI's own tag and asserts it.** Where the function
  the test calls renders that tag, nothing changes. Otherwise the test writes the tag with
  kotlinx.html and adds every group's classes through class siblings, as in
  `a { daisyButtonClasses(color = ButtonColor.Primary) }`. That is the typed spelling a library
  user writes for the same markup.
- **Of several functions with one own class, the one rendering the documented tag claims it**
  when they offer equally much: `<input class="tab">` goes to `daisyTabRadio`, not `daisyTab`.
- **The gate fails on a documented tag that has no kotlinx.html builder** and no generated one.
  At the pinned tag that is `<calendar-date>` alone.
- **BREAKING: `daisyCalendar` renders `<calendar-date class="cally">`,** through a builder in
  core, so the cally web component it styles exists in the page. Closes #343.

Subtractive option, weighed: drop the per-element tests' class-only assertion in favour of comparing
the whole rendered element. Not taken: DaisyUI's examples carry Tailwind utilities and attributes the
test has no reason to reproduce.

## Capabilities

### New Capabilities

(none)

### Modified Capabilities

- `component-model`: MODIFIED "Every expressible documented element is a generated test" to render
  and assert DaisyUI's tag; MODIFIED the gate requirement with the owner tie-break and the
  missing-builder failure.
- `daisyui-component-coverage`: MODIFIED "Each generated function renders the one element its model
  declares": the generated tests now compare the element, and DaisyUI's other tags are reached
  through siblings.

## Out of scope, and who owns it

- Choosing a different default tag for a function, such as `daisyBadge` rendering `<div>` because
  DaisyUI shows it more often. Both tags are expressible; which one a function renders is a model
  decision for a change that needs it.
- The popover dropdown, `<div class="dropdown" popover>` (#357): expressible as
  `div { daisyDropdownClasses() }` after this change, but a construction method of its own is
  #357's.

## Impact

- `codegen/src/documented-elements.ts`, `test-generator.ts`: owner tie-break, the tag in the call,
  the gate on builders.
- `lib/src/main/kotlin/io/github/ollin/kdaisyui/core/`: one hand-written custom element,
  `CALENDAR_DATE`, and its builder.
- `codegen/model/calendar.json`, `lib/generated/**`, both `lib/api/*.api`, README migration entry.
