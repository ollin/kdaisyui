# Tasks

Split out of `a-class-belongs-to-the-element-that-wears-it` on 2026-09-19. `tab` is the one
component whose cross-check finding is not a class on the wrong function but two functions
inverted, so it needs its own decision about the item element. Tracked as #342 and held by the
standing `elementCrossCheckExceptions` entry keyed `tab`, which must fail once this lands.

## 1. The item element — 1.0 h

- [ ] 1.1 `. d` **Decide the item element.** DaisyUI documents `<button>`, `<a>` and
  `<input type="radio">`; #342 suggests `customParts` since there is more than one.
- [ ] 1.2 `^ F` `daisyTab` renders `<div class="tabs">`, the item renders the focusable element
  carrying `tab`, and `tabActive`/`tabDisabled` move to the item.
- [ ] 1.3 `. d` Remove the cross-check exception #341 recorded for `tab`; it must fail if left.

## 2. Wrap-up

- [ ] 2.1 `. d` **How to migrate**: `daisyTab`'s element and the moved `tabActive`/`tabDisabled`.
- [ ] 2.2 `. d` Re-dump both baselines and read each diff.
- [ ] 2.3 Full green per `openspec/config.yaml`, plus `:lib:pitest`.
- [ ] 2.4 `. d` Write the evaluation under `tmp/`.
