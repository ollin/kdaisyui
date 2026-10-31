# Findings

## F1 - The popover dropdown is a `<ul>`, not the `<div>` #357 names

- **What happened:** at proposal. #357 says the closing construction renders
  `<div class="dropdown" popover>`. DaisyUI 5.7.50 documents the popover method on
  `<ul class="dropdown menu" popover …>`: the menu is the dropdown.
- **Cause:** the issue described the shape from memory of an older page.
- **Cost:** none; the proposal follows the documented element.

## F2 - The test generator assumes every `id` is optional, twice

- **What happened:** task 1.2. The documented-element test calls a function without an `id`
  (`daisyModalPopover() {`), and so does the coverage test's minimal case. A required `id` breaks
  both. The proposal had said "may assume", in one place.
- **Cause:** every `id` has defaulted to null since `id` was introduced.
- **Cost:** task 2.4 grows to two call sites; and the spec's scenario moved its attribute assertion
  to the coverage test, which is the only test kind that asserts static attributes.

## F3 - Four call sites, not two, assumed an optional `id`

- **What happened:** task 4.1's regeneration. Besides the documented-element call and the coverage
  test's minimal case, which 1.2 found, the coverage test's per-entry enum cases and the custom-part
  test called the function without an `id`. The custom-part test also asserts only declared
  attributes, so it now asserts `popover` and `position-anchor` too.
- **Cause:** 1.2 read the generator, not its output; only regenerating with a required `id` shows
  every site.
- **Cost:** about 10 minutes, inside task 4.1.
