# Findings

## F1 - The popover dropdown is a `<ul>`, not the `<div>` #357 names

- **What happened:** at proposal. #357 says the closing construction renders
  `<div class="dropdown" popover>`. DaisyUI 5.7.50 documents the popover method on
  `<ul class="dropdown menu" popover …>`: the menu is the dropdown.
- **Cause:** the issue described the shape from memory of an older page.
- **Cost:** none; the proposal follows the documented element.
