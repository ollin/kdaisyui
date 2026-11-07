## MODIFIED Requirements

### Requirement: The dropdown's popover method is generated

The library SHALL offer `daisyDropdownPopover`, generated from the model, rendering
`<ul class="dropdown …" popover id="…" data-position-anchor="…">` with a required `id` and the
dropdown's `side` and `align` axes. It SHALL write no `style` attribute.

**Verified** that this is the element DaisyUI documents for the method: the dropdown page of
5.7.50, "Dropdown using popover API and anchor positioning", marks `<ul class="$$dropdown $$menu …"
popover id="popover-1" style="position-anchor:--anchor-1">`. DaisyUI writes the anchor as an
inline style; kdaisyui writes the same name as a data attribute, because an inline style is
refused under `Content-Security-Policy: default-src 'self'` and a data attribute is not.

#### Scenario: The documented element is tested through the function

- **WHEN** the component tests are generated
- **THEN** the test for the documented popover `<ul>` calls `daisyDropdownPopover` and asserts its tag and classes
- **AND** the function's custom-part test asserts the `popover` attribute and the `data-position-anchor`

#### Scenario: A placement reaches the popover

- **WHEN** `daisyDropdownPopover(id = menu, side = DropdownSide.Top, align = DropdownAlign.End)` renders
- **THEN** the element carries `dropdown-top` and `dropdown-end`

### Requirement: A popover's trigger is typed and anchored to it

The library SHALL offer `Tag.popoverTarget(popover: HtmlId, action: PopoverTargetAction?, anchored:
Boolean)`, which writes `popovertarget` with the popover's id and `popovertargetaction` when an
action is given. Only when `anchored` is true does it write a `data-anchor-name` equal to the
`data-position-anchor` that popover writes. It SHALL write no `style` attribute and SHALL leave
a `style` the caller set untouched. Off by default, because DaisyUI's megamenu sets `anchor-name`
on its triggers itself.

**Verified** that the attribute route anchors, probe of 2026-11-07 in headless Chromium 156
(`assets/anchor-probe.html` of the change `csp-safe-popover-anchoring`, result in its header):
with the two rules of the `css-delivery` delta in the page, the shown popover's top edge equals
the trigger's bottom edge and its left edge the trigger's left edge.

#### Scenario: Trigger and popover agree on an id with a space

- **WHEN** a trigger calls `popoverTarget(htmlId("main menu"), anchored = true)` and `daisyDropdownPopover(id = htmlId("main menu"))` renders
- **THEN** the trigger's `data-anchor-name` and the popover's `data-position-anchor` are the same valid dashed-ident

#### Scenario: A megamenu trigger keeps DaisyUI's own anchor

- **WHEN** a trigger calls `popoverTarget(panel)` without `anchored`
- **THEN** it writes no `data-anchor-name`

#### Scenario: A close button

- **WHEN** a button calls `popoverTarget(modal, PopoverTargetAction.Hide)`
- **THEN** it carries `popovertargetaction="hide"`

#### Scenario: A caller's style survives

- **WHEN** a tag with `style="color:red"` calls `popoverTarget(menu, anchored = true)`
- **THEN** its `style` is still exactly `color:red`

### Requirement: The popover dropdown opens beside its trigger without JavaScript

In the example application, clicking the trigger of a `daisyDropdownPopover` SHALL show the menu
next to the trigger, with no script on the page and no inline style in the markup.

**Verified** 2026-10-31 by `e2e-tests/.../dropdown_popover.feature` in Chromium: the open menu's
top edge meets the trigger's bottom edge within 4px. The check was seen to fail with the trigger's
`anchor-name` removed: the menu then opened 80px off. **Assumed** that the same scenario passes
once the anchor travels as data attributes and the rules are in the example app's stylesheet;
*wrong if* the scenario reports the menu off its trigger after task 3.2.

#### Scenario: Click opens it under the button

- **WHEN** the trigger is clicked
- **THEN** the menu is visible
- **AND** its top edge is at the trigger's bottom edge, within a few pixels

#### Scenario: The served markup carries no style attribute

- **WHEN** the popover dropdown page is served
- **THEN** its body contains `data-anchor-name` and `data-position-anchor` with the same value
- **AND** no `style=` attribute
