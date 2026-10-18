## ADDED Requirements

### Requirement: No function offers a class for an element it does not render

A generated function SHALL NOT offer, as a parameter, a class DaisyUI's examples put only on
another element than the one the function renders, such as a child. Such a class is offered by the
function, class sibling or class-only function for that element.

Written against #347. **Verified** the defect, read 2026-10-18: `daisyMenu` offers `active`,
`disabled` and `dropdownShow`, `daisyDock` offers `active`, `daisyRating` offers `hidden`, and
`daisyList` offers `column`. Each writes its class on the container, and DaisyUI puts every one of
them on a child (`<a>` or `<li>` in a menu, the dock's `<button>`, the rating's first `<input>`,
the children of a `list-row`), where its CSS applies. The gate's documented-element tests found
all eight.

#### Scenario: A menu's active item
- **WHEN** a caller marks the active entry of a menu
- **THEN** it does so on the item, through `daisyMenuItemClasses(active = true)`, and `daisyMenu`
  has no `active` parameter

#### Scenario: A dock's active item
- **WHEN** a caller marks the active button of a dock
- **THEN** the class lands on that button, and `daisyDock` has no `active` parameter
