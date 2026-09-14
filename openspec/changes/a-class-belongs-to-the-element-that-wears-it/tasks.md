# Tasks

Estimate frozen 2026-09-14, priced by open decisions per the calibration finding — a block's cost
tracks how many of its decisions already exist, not how much code it touches.

| Block | Open decisions | Estimate |
|---|---|---|
| 1 the census, as a committed check | 1 — what an exception must carry | 1.2 h |
| 2 move the 13 misplaced parameters | 0 — the census says where each goes | 1.5 h |
| 3 the 18 unreachable classes | 2 — `join-item`'s shape, and part-vs-modifier | 1.5 h |
| 4 `tab`: container and item trade places (#342) | 1 — button, anchor or radio input | 1.0 h |
| 5 migration, baselines, gate | 0 | 0.8 h |
| | **Total** | **6.0 h** |

Not pre-corrected by the known ratios. Runs so far came in at 0.63 / 0.27 / 0.21 / 0.26 / 0.33
and this change's predecessor at 0.43 / 0.22 / 0.50, so treat 6.0 h as an upper bound — but the
number above is the honest raw one, because a ledger that adjusts its own input measures nothing.

**Block 0 is already done and is not estimated**, because it happened before the estimate existed
and retro-fitting is forbidden: the census that produced the 13 and the 18.

## 0. The census — DONE, before this change existed

- [x] 0.1 Parse every fenced html example on all 68 component pages and, for each `$$` class,
  record whether it sits on the element carrying the component class. **414 classes examined, 80
  never on the component element.**
- [x] 0.2 Intersect those 80 with `lib/api/components.api` to separate the ones that are
  nevertheless container parameters from the ones that are merely unreachable. **13 and 18.**

## 1. The census becomes a check — 1.2 h

- [ ] 1.1 `^ F (internal)` Generalise `element-cross-check.ts` from the component class to every
  class, reusing `parser/documented-classes.ts`. Same three failure modes it already has: a
  disagreement with no exception, a class DaisyUI documents no element for, and an exception no
  longer needed.
- [ ] 1.2 `. d` Record the 13 + 18 as exceptions naming this change, so the check lands green and
  then fails as each is fixed. **An exception must expire on its own** — that is what stops the
  list becoming the hand-maintained list this project has deleted twice.
- [ ] 1.3 `^ F (internal)` Wire it beside `checkComponentApi` in CI, NOT into `check`: it reads
  the submodule, and a clone must compile and test without one.

## 2. The 13 misplaced parameters — 1.5 h

Ordered by blast radius, largest first, so the shape is settled before the small ones follow.

- [ ] 2.1 `^ F` `indicator`: both placement enums move from `daisyIndicator` to
  `daisyIndicatorItem`. Six enum members, shipped in 0.6.0.
- [ ] 2.2 `^ F` `menu`: `active`, `disabled` and `dropdownShow` leave `daisyMenu`.
  `menu-dropdown-show` belongs on `daisyMenuDropdown` and `daisyMenuDropdownToggle`, which exist;
  `menu-active` and `menu-disabled` sit on an `<a>`/`<li>` the library does not generate — block 3
  decides whether it should.
- [ ] 2.3 `^ F` `list`: `ListModifier` moves from `daisyList` to the row. Shipped in 0.6.0.
- [ ] 2.4 `^ F` `dock-active` and `tab-content`.

## 3. The 18 unreachable classes — 1.5 h

- [ ] 3.1 `. d` **Decide part vs. modifier.** A class on a child that always has the same parent
  is a part and gets a function. `join-item` is not that: DaisyUI puts it on `<button>`, `<input>`
  and `<select>`, so it is a modifier on **any** component that can be a join's child. Decide
  whether that is a parameter on every such component, a wrapper function, or `extraClasses` with
  a documented constant.
- [ ] 3.2 `^ F` Generate the parts that are plainly parts: `avatar-group`, `list-row`,
  `carousel-item`, `megamenu-active`, `filter-reset` and the rest of the 18.
- [ ] 3.3 `^ F` Apply 3.1's answer to `join-item`.

## 4. `tab` — container and item trade places — 1.0 h

- [ ] 4.1 `. d` **Decide the item element.** DaisyUI documents `<button>`, `<a>` and
  `<input type="radio">`; #342 suggests `customParts` since there is more than one.
- [ ] 4.2 `^ F` `daisyTab` renders `<div class="tabs">`, the item renders the focusable element
  carrying `tab`, and `tabActive`/`tabDisabled` move to the item.
- [ ] 4.3 `. d` Remove the cross-check exception #341 recorded for `tab`; it must fail if left.

## 5. Migration, baselines, gate — 0.8 h

- [ ] 5.1 `. d` **How to migrate**, naming every moved parameter. This withdraws public API that
  0.6.0 introduced — say so plainly rather than describing it as a refinement.
- [ ] 5.2 `. d` Re-dump both baselines with `just update-api` and read each diff.
- [ ] 5.3 Full green per `openspec/config.yaml`, plus the new check and `:lib:pitest`.
- [ ] 5.4 Write the evaluation under `tmp/`.

## Not in this change

**Tagging 0.6.0.** `gradle.properties` declares it and no tag exists, so nothing has shipped.
Releasing first would publish two enums on the wrong function and withdraw them one release later.
The release follows this change.
