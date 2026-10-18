## Why

The generated tests check one element per DaisyUI example: the one carrying the component's own
class. Everything else DaisyUI marks with `$$` (parts, nested components, the classes on a child)
is never compared with the API, so "kdaisyui can generate what DaisyUI documents" is a claim
nobody checks. Measured 2026-10-18 at the pinned tag: 2,571 marked elements, of which the 606
generated example tests reach the main-class ones only, and a first count, before excluding the
`rounded-box` utility, found 188 that no function can express by their classes.

## What Changes

- **Every marked element becomes a test.** For each element in a `+page.md` example that carries
  a `$$`-marked class, the test generator emits a call to the function whose class the element
  wears, with typed arguments for every other marked class, and asserts the classes DaisyUI wrote.
  Parts and other components' functions included, not only the main function.
- **Generation fails on an element no function can express by its classes**, naming the page, the
  element and the classes. Expressible means: one function's own class is among the element's
  marked classes, and that function offers every other marked class as an axis member, a toggle, a
  class an extra writes, a class it always writes, or `join-item` where the join scope marks it.
- **A gap is closed by the model, nowhere else**: either the model gains what the element needs,
  or the page's model file declares the gap, by its key, as not expressed, with a reason.
  Generation also fails on a declaration no example needs any more, so the list cannot rot.
- **Corrected:** the requirement on elements says the model records `badge` and `dropdown` as
  element divergences. It records none, and 322 marked elements in 80 distinct shapes sit on a
  different element than the function renders. The requirement is replaced by one that says what is true:
  one function renders one declared element, and DaisyUI's other elements are not checked yet.

Subtractive option, weighed: delete today's main-class-only example tests and generate the new
ones in their place, rather than adding a second kind of example test beside them. Taken: the new
tests are a superset, so the old path goes.

## Capabilities

### New Capabilities

(none)

### Modified Capabilities

- `component-model`: ADDED a requirement that every marked documentation element is expressible
  by its classes or declared not expressed; ADDED that every expressible one is a generated test.
- `daisyui-component-coverage`: REMOVED "The model places each class on an element DaisyUI
  documents it on", which states a divergence record that does not exist; ADDED "Each generated
  function renders the one element its model declares" in its place.

## Out of scope, and who owns it

- **The element.** An element whose classes are expressible but that DaisyUI shows on another tag
  (`btn` on `<a>`, `mask` on `<input>`, `dropdown` on `<div popover>`) passes this gate. Whether
  every such element needs its own function is a design question for a later change; Oliver chose
  the class-only gate on 2026-10-18.
- **New API for the gaps.** This change declares each gap with its reason and, where an API is
  wanted, a tracking issue. Writing that API (`list-row`, `floating-label`, the menu's children
  from #347, the calendar from #343) is each its own change.
- Variant-prefixed marked classes (`is-drawer-close:$$tooltip`) and marked classes no frontmatter
  declares (`rounded-box`, a utility): not component classes, not counted.

## Impact

- `codegen/src/test-generator.ts`: the example tests, rewritten to address every function.
- `codegen/src/model-completeness.ts` (or a sibling): the expressibility gate.
- `codegen/src/model.ts`: one optional field per model file for declared gaps.
- `codegen/model/*.json`: the declarations, about 40 distinct class sets on first count.
- `lib/generated/test/**`: many more generated tests; `:lib:test` grows from 1,679.
- No change to the public Kotlin API; `lib/api/*.api` stay as they are.
