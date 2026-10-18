## Why

`test-every-documented-element` made every `$$`-marked element in DaisyUI's examples a generated
test and declared 39 elements the API cannot produce. Eight of those are defects: `daisyMenu`,
`daisyDock`, `daisyRating` and `daisyList` offer a class on the container that DaisyUI puts on a
child, where it styles nothing (#347). The rest have no typed spelling at all. Oliver decided on
2026-10-18 that none of them is kept, whatever the change costs the API.

## What Changes

- **A typed class sibling for every function.** Beside `daisyButton(…)` the generator emits
  `Tag.daisyButtonClasses(…)`, which adds the same classes, from the same typed parameters, to
  the element it is called on. Two components on one element become one call plus a sibling:
  `daisyMenu(size = MenuSize.Sm, attrs = { daisyDropdownContentClasses() })`.
- **Class-only functions for children without an own class.** DaisyUI puts `menu-active` on the
  `<a>` inside a `<li>`, `dock-active` on a dock's `<button>` and `list-col-grow` on a child of a
  `list-row`. The model can declare a function with no own class and no element, which generates
  only its class sibling, such as `daisyMenuItemClasses(active = true)`.
- **BREAKING: the eight misplaced parameters move to the child.** `daisyMenu` loses `active`,
  `disabled` and `dropdownShow`, `daisyDock` loses `active`, `daisyRating` loses `hidden`, and
  `daisyList` loses `column`. Each goes to the function or class sibling for the element DaisyUI
  puts it on.
- **The four classes no function renders get one:** `list-row`, `floating-label`, `avatar-group`
  and the calendar library's `vc`.
- **The gate learns the composition rule.** An element is expressible when its classes split into
  groups, each offered by one function whose own class is in the group, or by one class-only
  function. A test calls the first function and adds the rest through class siblings.
- **All 39 `notExpressed` declarations are deleted.** Each fix starts by deleting its declaration,
  so generation fails first and the model change makes it pass.

Subtractive option, weighed: drop `extraClasses` once every documented class has a typed path.
Not taken: it also carries Tailwind utilities, and `at()` for variant-prefixed classes is still
parked.

## Capabilities

### New Capabilities

(none)

### Modified Capabilities

- `component-model`: ADDED the class sibling and the class-only function; MODIFIED the gate's
  definition of expressible, which gains the composition rule.
- `daisyui-component-coverage`: ADDED that no generated function offers a class DaisyUI puts on
  another element than the one the function renders.

## Out of scope, and who owns it

- **The element tag.** The class siblings make every tag reachable, as in
  `a { daisyButtonClasses() }`, but whether the gate and the tests must use DaisyUI's tag is the
  next change, decided by Oliver on 2026-10-18 to follow this one.
- Variant-prefixed classes stay on `extraClasses` until `at()` ships.

## Impact

- `codegen/src/generator-new.ts`, `component-shape.ts`, `model.ts`: the sibling, the class-only
  function.
- `codegen/src/documented-elements.ts`, `test-generator.ts`: the composition rule and its calls.
- `codegen/model/*.json`: eight moves, four new functions, 39 declarations removed.
- `lib/generated/**`, `docs/reference/**`, `llms.txt`, both `lib/api/*.api`: regenerated and
  re-dumped. Breaking: README gains "How to migrate" entries.
- `example-app`, `lib/src/test`: call sites of the moved parameters, if any.
