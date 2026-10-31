# Tasks

**Estimate, frozen 2026-10-31:** 195 minutes. Block 1: 20, block 2: 60, block 3: 30, block 4: 45,
block 5: 20, block 6: 20.

Ordered by uncertainty: the E2E assumption is the only Assumed requirement and cannot be checked
before the function exists, so section 1 settles the two code assumptions and section 4 runs the
E2E as early as the function allows.

## 1. Open assumptions

- [x] 1.1 `. d` (A1) Read the requirements of `component-model`, `daisyui-component-coverage` and
  `api-change-detection` against a required `id` and an id-derived attribute; record the result.
- [x] 1.2 `. d` (A5) Read how `test-generator.ts` passes `id`, and whether a required one needs a
  change; record the result.

## 2. The model fact, under TCR

- [ ] 2.1 `^ r (internal)` The shape carries `idRequired`, default false; no model sets it, so the
  regeneration diff is empty.
- [ ] 2.2 `. F (internal)` A function with `idRequired` takes `id: HtmlId` without a default and
  writes it unconditionally.
- [ ] 2.3 `. F (internal)` `"anchoredPopover": true` in a model sets `idRequired` and writes
  `popover` and `style="position-anchor:${id.anchorName}"`.
- [ ] 2.4 `. F (internal)` The test generator passes an id to a function whose `id` is required,
  in the documented-element call and in the coverage test's minimal case (1.2 found both).

## 3. Core, under TCR

- [x] 3.1 `. F` `HtmlId.anchorName`: `--` plus the id, characters outside a dashed-ident replaced by `_`.
- [x] 3.2 `. F` `Tag.popoverTarget(popover)` writes `popovertarget` and `anchor-name`.
- [x] 3.3 `. F` It appends `anchor-name` to an existing `style`.
- [x] 3.4 `. F` `PopoverTargetAction` and the `action` parameter write `popovertargetaction`.

## 4. The function, and the E2E that can refute it

- [ ] 4.1 `^ F` `daisyDropdownPopover` in `codegen/model/dropdown.json`, regenerated; the
  documented `<ul>`'s test now calls it.
- [ ] 4.2 `^ F` An example-app page with a trigger and a popover dropdown, no script.
- [ ] 4.3 `^ F` E2E: the click opens the menu, and its top edge meets the trigger's bottom edge.
  If this refutes the Assumed requirement, revise the change before going on.
- [ ] 4.4 `^ r` The example app's four untyped `popovertarget` strings become `popoverTarget(…)`.

## 5. Baselines and docs

- [ ] 5.1 `. d` `just update-api`: read both diffs; additions only.
- [ ] 5.2 `. d` `docs/how-to.md`: a recipe for the popover dropdown, compiled by `:doc-snippets`.

## 6. Gate and adoption

- [ ] 6.1 Full green: `check` with E2E, `koverVerify`, both PIT runs, `analyze_change_set`.
- [ ] 6.2 `openspec validate dropdown-popover --strict`.
- [ ] 6.3 The evaluation under `tmp/dropdown-popover/` for Oliver to adopt.
