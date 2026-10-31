## Why

DaisyUI documents two ways to build a dropdown that the library should offer, and it offers one.
`daisyDropdown` renders `<details class="dropdown">`, which opens with no JavaScript and no
attributes. The second, DaisyUI's "popover API and anchor positioning" method, renders the menu
itself as the dropdown:

```html
<button class="btn" popovertarget="popover-1" style="anchor-name:--anchor-1">Button</button>
<ul class="dropdown menu …" popover id="popover-1" style="position-anchor:--anchor-1">…</ul>
```

It opens on the browser's top layer, so it needs no z-index and cannot be clipped by an ancestor's
overflow, which is the case `<details>` handles badly. kdaisyui has no function for it. The
documented element is "expressed" today only as `ul { daisyDropdownClasses(); daisyMenuClasses() }`:
the right classes, but no `popover`, no `id` and no anchor, so a dropdown built that way never opens.
Issue #357 names this construction method as missing and stays open until it exists.

The trigger side is missing too. Every `popovertarget` in the example app (the popover modal and the
megamenu, four places) is written as an untyped string, `attributes["popovertarget"] = id.id`, and
nothing writes an anchor name.

## What Changes

- **`daisyDropdownPopover(id, side, align, …)`**, generated from `codegen/model/dropdown.json`. It
  renders `<ul class="dropdown …" popover id="…" style="position-anchor:--…">`, the element DaisyUI
  documents. `id` is required, because a popover nobody can target cannot open. `side` and `align`
  are the existing `DropdownSide` and `DropdownAlign`. DaisyUI's placement classes set the
  `--anchor-v`/`--anchor-h` variables its `position-area` reads, so they work on this form.
- **The model learns one fact:** a construction can be an *anchored popover*. Its `id` becomes
  required, and it writes `popover` and the `position-anchor` that matches its trigger. The
  generator, the reference pages and the API dump follow from the model.
- **`Tag.popoverTarget(popover: HtmlId, action: PopoverTargetAction? = null)`**, hand-written in
  core: it writes `popovertarget`, the optional `popovertargetaction`, and the `anchor-name` that
  matches. One helper serves every popover trigger, the dropdown's, the modal's and the megamenu's.
- **The anchor name is derived from the `id` in one place**, `HtmlId.anchorName`, which both sides
  call. `htmlId()` accepts any string, and a character a CSS dashed-ident does not allow would
  break the pairing silently.
- **The example app** gets a page for the popover dropdown, and its four untyped `popovertarget`
  strings become `popoverTarget(…)`.
- **The generated documented-element test** for that `<ul>` calls `daisyDropdownPopover`, so it
  asserts the popover form instead of a written tag.

**Not breaking.** Two functions and one enum are added; nothing changes signature.

### The subtractive option, stated

Document `ul(…) { attributes["popover"] = ""; … }` in `docs/how-to.md` and add nothing. It is
cheaper, but it is exactly the stringly-typed wiring the library exists to replace. And it leaves
the documented element tested without the attribute that makes it work. Rejected.

What this change does remove: the four untyped `popovertarget` strings in the example app.

## Out of scope

- **DaisyUI's third dropdown method**, CSS focus (`<div class="dropdown" tabindex="0">`). It is the
  `<div>` shape of most of the dropdown page's examples. It is a separate construction with its own
  accessibility questions, and it is not part of #357.
- **`state` and `hover` on the popover form.** The popover is opened by its trigger; `dropdown-hover`
  and `dropdown-open`/`-close` are `<details>`/focus behaviours and stay on `daisyDropdown`.
- **Per-component class delivery**, which `per-component-class-delivery` owns.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | No requirement of a main spec this builds on contradicts it. It builds on `component-model` (what the model declares, the naming rule, documented-element tests), `daisyui-component-coverage` and `api-change-detection` | code | to check in task 1.1 - read each requirement against an id-derived attribute and a required id |
| A2 | DaisyUI 5.7.50 documents the popover dropdown on `<ul class="dropdown menu" popover id style="position-anchor:…">` | code | confirmed at proposal - `daisyui/packages/docs/src/routes/(routes)/components/dropdown/+page.md`, "Dropdown using popover API and anchor positioning" |
| A3 | `dropdown-top`/`-end` etc. place a popover dropdown | code | confirmed at proposal - `daisyui/packages/daisyui/src/components/dropdown.css`: `.dropdown` sets `position-area: var(--anchor-v) var(--anchor-h)`, and the placement classes set those variables |
| A4 | The E2E browser supports the popover API and CSS anchor positioning | code | confirmed at proposal - Chromium 156 on this machine; anchor positioning shipped in Chrome 125 |
| A5 | The test generator can call a function whose `id` is required | code | to check in task 1.2 - it may assume every `id` defaults to null |
| A6 | `htmlId()` accepts strings a CSS dashed-ident does not | code | confirmed at proposal - `core/TagId.kt`: `htmlId(id: String)` wraps any string |

## Capabilities

### Modified Capabilities

- `daisyui-component-coverage`: the dropdown's popover method is generated, its trigger is typed,
  and the two are anchored to each other.

## Impact

- `codegen/model/dropdown.json`; `codegen/src/` (model reader, shape, Kotlin and docs generators,
  test generator) for the anchored-popover fact and a required `id`.
- `lib/src/main/kotlin/…/core/`: `popoverTarget`, `PopoverTargetAction`, `HtmlId.anchorName`, and
  their tests.
- Regenerated `lib/generated/**`, `docs/reference/dropdown.md`, `llms.txt`; both API baselines.
- `example-app` (a page, four call sites) and `e2e-tests` (the page opens and is anchored).
