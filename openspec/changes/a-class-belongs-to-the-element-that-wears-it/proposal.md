# A class belongs to the element that wears it

## Why

The generator decides which **function** a DaisyUI class becomes a parameter of from DaisyUI's
frontmatter **category** — `placements`, `modifiers`, `behaviors`. A category says what a class
*means*. It does not say which element wears it.

Where the two disagree, the generated API offers a parameter that emits a class onto an element
where it styles nothing. It compiles, it renders, and nothing fails.

Measured 2026-09-14 over all 68 component pages, parsing the markup rather than reading the
categories: **13 classes across 5 components are parameters on the container while DaisyUI puts
them on a child.**

| Component | Class | Reachable today as |
|---|---|---|
| dock | `dock-active` | `active = true` |
| indicator | `indicator-top/-middle/-bottom` | `IndicatorVerticalPlacement.*` |
| indicator | `indicator-start/-center/-end` | `IndicatorHorizontalPlacement.*` |
| list | `list-col-grow`, `list-col-wrap` | `ListModifier.*` |
| menu | `menu-active`, `menu-disabled`, `menu-dropdown-show` | `active`/`disabled`/`dropdownShow = true` |
| tab | `tab-content` | `content = true` |

**Two of those are enums that shipped in 0.6.0** — `IndicatorVerticalPlacement`,
`IndicatorHorizontalPlacement` and `ListModifier`. The exclusivity measurement behind them is
correct; `toast` carries the identical two-axis shape and is right in 6 of 6 examples. What is
wrong is only which function carries the answer.

A further **18 classes sit on a child and are reachable from nothing at all** — `avatar-group`,
`join-item`, `list-row`, `carousel-item`, `megamenu-active`, `filter-reset` and others. That is
the milder half of the same defect and was previously filed as "the genuine parameter gaps", a
title this census disproves: they are not gaps in a parameter list, they are missing functions.

The defect has been found three times by hand — #342 (`daisyTab` container and item inverted,
plus `tabActive`/`tabDisabled` on the container), #347 (indicator and menu), and the deferred
block 6 — and each time only because someone looked. Nothing checks it.

Something can. `element-cross-check.ts` already compares the element the generator chose against
the one DaisyUI documents, and fails generation on a disagreement with no filed exception. It
answers that question for the **component class** only. `parser/documented-classes.ts`, added
2026-09-14, makes the element carrying **any** class mechanically readable — which is the
capability the check was missing.

## What changes

1. **The cross-check is generalised from the component class to every class.** A class whose
   documented element is not the element of the function declaring it fails generation, with the
   same exception mechanism: a reason, a tracking issue, and expiry the moment the disagreement
   goes away.
2. **The 13 misplaced parameters move to the function whose element wears the class, or are
   removed** where no such function exists yet.
3. **The 18 unreachable classes get the function they need**, or a reasoned exception saying why
   not. `join-item` is the hard case: DaisyUI puts it on `<button>`, `<input>` and `<select>` —
   arbitrary children — so it is a modifier on any component that can be a join's child, not a
   part of the join.
4. **#342 is resolved by the same rule**: `tabs` is the container class and `tab` the item class,
   so the two functions trade places and the item modifiers move with them.

## Impact

**Breaking, and it removes public API that 0.6.0 introduced.** Two enums move to a different
function; four boolean parameters are removed from `daisyMenu` and `daisyDock`; `daisyTab` and
`daisyTabTab` change places.

**This is why 0.6.0 should not be tagged first.** `gradle.properties` declares 0.6.0 and no tag
exists, so nothing has shipped. Releasing before this change would publish two enums on the wrong
function and then withdraw them one release later, with a migration entry that retracts the
previous one.

Closes #342. Closes #347. Supersedes the deferred block 6 of `apply-classes-at-variants`.
