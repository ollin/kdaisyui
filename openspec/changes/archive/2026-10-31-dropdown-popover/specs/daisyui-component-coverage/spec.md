## ADDED Requirements

### Requirement: The dropdown's popover method is generated

The library SHALL offer `daisyDropdownPopover`, generated from the model, rendering
`<ul class="dropdown …" popover id="…" style="position-anchor:…">` with a required `id` and the
dropdown's `side` and `align` axes.

**Verified** that this is the element DaisyUI documents for the method: the dropdown page of
5.7.50, "Dropdown using popover API and anchor positioning", marks `<ul class="$$dropdown $$menu …"
popover id="popover-1" style="position-anchor:--anchor-1">`.

#### Scenario: The documented element is tested through the function

- **WHEN** the component tests are generated
- **THEN** the test for the documented popover `<ul>` calls `daisyDropdownPopover` and asserts its tag and classes
- **AND** the function's coverage test asserts the `popover` attribute and the `position-anchor`

#### Scenario: A placement reaches the popover

- **WHEN** `daisyDropdownPopover(id = menu, side = DropdownSide.Top, align = DropdownAlign.End)` renders
- **THEN** the element carries `dropdown-top` and `dropdown-end`

### Requirement: A popover's trigger is typed and anchored to it

The library SHALL offer `Tag.popoverTarget(popover: HtmlId, action: PopoverTargetAction?, anchored:
Boolean)`, which writes `popovertarget` with the popover's id and `popovertargetaction` when an
action is given. Only when `anchored` is true does it write an `anchor-name` equal to the
`position-anchor` that popover writes, appended to an existing `style` rather than replacing it.
Off by default, because DaisyUI's megamenu sets `anchor-name` on its triggers itself.

#### Scenario: Trigger and popover agree on an id with a space

- **WHEN** a trigger calls `popoverTarget(htmlId("main menu"), anchored = true)` and `daisyDropdownPopover(id = htmlId("main menu"))` renders
- **THEN** the trigger's `anchor-name` and the popover's `position-anchor` are the same valid dashed-ident

#### Scenario: A megamenu trigger keeps DaisyUI's own anchor

- **WHEN** a trigger calls `popoverTarget(panel)` without `anchored`
- **THEN** it writes no `anchor-name`

#### Scenario: A close button

- **WHEN** a button calls `popoverTarget(modal, PopoverTargetAction.Hide)`
- **THEN** it carries `popovertargetaction="hide"`

### Requirement: The popover dropdown opens beside its trigger without JavaScript

In the example application, clicking the trigger of a `daisyDropdownPopover` SHALL show the menu
next to the trigger, with no script on the page.

**Verified** 2026-10-31 by `e2e-tests/.../dropdown_popover.feature` in Chromium: the open menu's
top edge meets the trigger's bottom edge within 4px. The check was seen to fail with the trigger's
`anchor-name` removed: the menu then opened 80px off.

#### Scenario: Click opens it under the button

- **WHEN** the trigger is clicked
- **THEN** the menu is visible
- **AND** its top edge is at the trigger's bottom edge, within a few pixels
