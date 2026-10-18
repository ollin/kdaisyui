## Context

See `proposal.md` for why. `test-every-documented-element` left 39 `notExpressed` keys: 27 two
components on one element, 8 classes offered on the container instead of the child (#347), 4
classes no function renders. Oliver chose Option A on 2026-10-18: a typed class sibling per
function, over 27 declared composite functions (B) and nested parameter objects (C).

Constraints: one function renders one element (kotlinx.html); `Tag.addClassNames(ClassValues)`
already exists in core; the coverage generator in `test-generator.ts` reads every generated
`fun <Receiver>.daisy<Name>(` back and must drive every branch; Kover is 100% line and branch.

## Goals / Non-Goals

**Goals:** every documented element expressible; no declaration left; no parameter on the wrong
element.

**Non-Goals:** comparing the element's tag (next change); `at()` for variant prefixes.

## Decisions

### D1. The sibling is `Tag.daisy<Name>Classes`, beside its function

```kotlin
fun Tag.daisyButtonClasses(
    color: ClassValues<ButtonColor>? = null,
    size: ClassValues<ButtonSize>? = null,
    …,
    active: Boolean = false,
    disabled: Boolean = false,   // the extra's class only: btn-disabled, no disabled attribute
) {
    addClassNames("btn")
    addClassNames(color)
    …
}
```

Same parameter names, types and order as the function's class-bearing parameters; no `id`,
`text`, `extraClasses`, `attrs` or `content`. Emitted in the same file, right after its function,
so a reader sees both together.

*Rejected:* `Tag.buttonClasses` without the prefix. It breaks the naming rule that every generated
name starts with `daisy`, and autocompletion on `daisyButton` would not show it.
*Rejected:* one generic `Tag.daisy(vararg ClassValues)`. A toggle has no `ClassValues`, and the
own class would have to be passed as a string.

### D2. A class-only function: `kind: "classes"`

```json
{ "kind": "classes", "name": "daisyMenuItem", "receiver": "Tag", "element": null, "class": null,
  "text": false, "parameters": [ { "kind": "toggle", "name": "active", "class": "menu-active" } ] }
```

It generates only `daisyMenuItemClasses`. Its name is declared, because it has no own class to
derive one from; the naming gate already skips functions without an own class. A toggle is named
by its class's suffix after the component's class, as everywhere.

*Rejected:* parts with a null class that render an element, for every case. Right for the dock,
whose items are DaisyUI's `<button>`s, and wrong for the menu, whose state sits on the `<a>` inside
a `<li>` that callers already write with kotlinx.html's own `li { a { } }`.

### D3. Where each of the 12 classes goes

| Class | Today | After |
|---|---|---|
| `menu-active`, `menu-disabled` | `daisyMenu(active, disabled)` | class-only `daisyMenuItem(active, disabled)` |
| `menu-dropdown-show` | `daisyMenu(dropdownShow)` | `daisyMenuDropdown(show)`, `daisyMenuDropdownToggle(dropdownShow)` |
| `dock-active` | `daisyDock(active)` | part `daisyDockItem`, `<button>`, no own class, `active` |
| `rating-hidden` | `daisyRating(hidden)` | class-only `daisyRatingItem(hidden)` |
| `list-col-grow`, `list-col-wrap` | `daisyList(column = ListColumn.…)` | class-only `daisyListCell(colGrow, colWrap)` |
| `list-row` | `notOffered` | part `daisyListRow`, `<li>` |
| `floating-label` | `notOffered` | part `daisyFloatingLabel`, `<label>` |
| `avatar-group` | `notOffered` | part `daisyAvatarGroup`, `<div>` |
| `vc` | `notOffered` | part `daisyVc`, `<div>`, the naming rule's name for `vc` |

`menu-dropdown-show`'s two toggle names follow the toggle rule against each function's own class:
`menu-dropdown-show` after `menu-dropdown` is `show`; it does not extend `menu-dropdown-toggle`, so
after the component's `menu` it is `dropdownShow`.

*Rejected for `vc`:* a declared stem such as `VanillaCalendar`. Stems are for a class that is not
the component's name (`btn`, `cally`); `vc` is the class itself.

### D4. The composition rule, one pass

For an element's counted classes, in page-first order: each function whose own class is present
claims the classes it offers that nothing has claimed yet. What remains must be offered whole by
one class-only function. A class left over is the gap. The test's call is the first claiming
function that renders an element, with the others' siblings in its `attrs`; with none, the
siblings run on a `div`.

*Rejected:* searching all partitions. No documented element needs it: two own classes never offer
one modifier both, as task 1.1 checks.

### D5. Coverage of the siblings

The coverage generator reads `fun Tag.daisy…Classes(` back like any function and drives each
branch on a `div`. A sibling's branches are the same `if (toggle)` and `addClassNames(enum)`
lines as its function's, so each sibling gets the same defaults, all-flags and per-enum-arm tests.

## Enablers and desired changes

| Enabler, zero diff | Desired change |
|---|---|
| the classifier returns groups instead of one function | siblings (D1) with their coverage (D5) |
| `kind: "classes"` read but unused | composition rule (D4) and its tests |
| | class-only functions and the moves (D2, D3), each first deleting its declaration |
| | the four new parts |

## Risks / Trade-offs

- [132 siblings double the generated API surface] → they are one line per class each, and the
  reference pages list them under their function, not as separate entries.
- [Callers of the moved parameters break] → README "How to migrate", one row per move; the
  example app is migrated in the same commit.
- [Coverage tests for siblings miss a branch] → `koverVerify` at 100% fails the build first.

## Migration Plan

Breaking for callers of `daisyMenu(active/disabled/dropdownShow)`, `daisyDock(active)`,
`daisyRating(hidden)` and `daisyList(column)`. Each gets a README row naming its replacement.
