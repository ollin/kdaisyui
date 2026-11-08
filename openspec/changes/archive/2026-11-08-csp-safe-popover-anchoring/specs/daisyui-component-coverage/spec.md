## MODIFIED Requirements

### Requirement: The dropdown's popover method is generated

The library SHALL offer `daisyDropdownPopover`, generated from the model, rendering
`<ul class="dropdown …" popover id="…">` with a required `id` and the dropdown's `side` and
`align` axes. It SHALL write no `style` attribute and no anchor of any kind.

**Verified** that this is the element DaisyUI documents for the method: the dropdown page of
5.7.50, "Dropdown using popover API and anchor positioning", marks `<ul class="$$dropdown $$menu …"
popover id="popover-1" style="position-anchor:--anchor-1">`. DaisyUI names the anchor in an
inline style; kdaisyui names none, because an inline style is refused under
`Content-Security-Policy: default-src 'self'`, and a popover opened by `popovertarget` is
anchored to its invoker without one (see the requirement on opening beside the trigger).

#### Scenario: The documented element is tested through the function

- **WHEN** the component tests are generated
- **THEN** the test for the documented popover `<ul>` calls `daisyDropdownPopover` and asserts its tag and classes
- **AND** the function's custom-part test asserts the `popover` attribute

#### Scenario: A placement reaches the popover

- **WHEN** `daisyDropdownPopover(id = menu, side = DropdownSide.Top, align = DropdownAlign.End)` renders
- **THEN** the element carries `dropdown-top` and `dropdown-end`

### Requirement: A popover's trigger is typed and anchored to it

The library SHALL offer `Tag.popoverTarget(popover: HtmlId, action: PopoverTargetAction?)`, which
writes `popovertarget` with the popover's id and `popovertargetaction` when an action is given,
and nothing else: no `style` and no anchor name. The browser makes the trigger the popover's
implicit anchor. A `style` the caller set SHALL stay untouched.

The earlier `popoverTarget(popover, action, anchored)` SHALL remain, deprecated with a
replacement, and render exactly what the two-parameter form renders, whatever `anchored` is.
`HtmlId.anchorName` SHALL remain, deprecated: nothing in the library writes it.

**Verified** that removing `anchored`'s effect breaks no anchoring: see the next requirement.

#### Scenario: Trigger and popover agree on an id with a space

- **WHEN** a trigger calls `popoverTarget(htmlId("main menu"))` and `daisyDropdownPopover(id = htmlId("main menu"))` renders
- **THEN** the trigger's `popovertarget` and the popover's `id` are the same string

#### Scenario: A megamenu trigger keeps DaisyUI's own anchor

- **WHEN** a trigger calls `popoverTarget(panel)`
- **THEN** it writes no `anchor-name`, so the one DaisyUI's megamenu CSS sets is the one in effect

#### Scenario: A deprecated anchored trigger writes no anchor

- **WHEN** a trigger calls `popoverTarget(menu, anchored = true)`
- **THEN** it carries `popovertarget` and no `style` and no `data-anchor-name`

#### Scenario: A close button

- **WHEN** a button calls `popoverTarget(modal, PopoverTargetAction.Hide)`
- **THEN** it carries `popovertargetaction="hide"`

#### Scenario: A caller's style survives

- **WHEN** a tag with `style="color:red"` calls `popoverTarget(menu)`
- **THEN** its `style` is still exactly `color:red`

### Requirement: The popover dropdown opens beside its trigger without JavaScript

In the example application, clicking the trigger of a `daisyDropdownPopover` SHALL show the menu
next to the trigger, with no script on the page and no inline style or anchor name in the markup.

**Verified** 2026-11-07 by `e2e-tests/.../dropdown_popover.feature` in Chromium 156: with no
anchor written anywhere, the open menu's top edge meets the trigger's bottom edge within 4px. The
browser anchors it: MDN BCD 8.1.8 records the implicit anchor reference of `popovertarget` from
Chrome 133, Safari 26 and Firefox 147. The check has been seen to fail: on 2026-10-31 a
`position-anchor` naming an anchor that did not exist overrode the implicit one, and the menu
opened 80px off.

#### Scenario: Click opens it under the button

- **WHEN** the trigger is clicked
- **THEN** the menu is visible
- **AND** its top edge is at the trigger's bottom edge, within a few pixels

#### Scenario: The served markup names no anchor

- **WHEN** the popover dropdown page is served
- **THEN** its body pairs the trigger and the menu by `popovertarget` and `id`
- **AND** it contains no `style=`, no `data-anchor-name` and no `data-position-anchor`
