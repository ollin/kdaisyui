## MODIFIED Requirements

### Requirement: The popover dropdown opens beside its trigger without JavaScript

In the example application, clicking the trigger of a `daisyDropdownPopover` SHALL show the menu
next to the trigger, with no script on the page and no inline style or anchor name in the markup.

**Verified** 2026-11-07 by `e2e-tests/.../dropdown_popover.feature` in Chromium 156: with no
anchor written anywhere, the open menu's top edge meets the trigger's bottom edge within 4px. The
browser anchors it: MDN BCD 8.1.8 records the implicit anchor reference of `popovertarget` from
Chrome 133, Safari 26 and Firefox 147. The check has been seen to fail: on 2026-10-31 a
`position-anchor` naming an anchor that did not exist overrode the implicit one, and the menu
opened 80px off.

`run-e2e-on-kotest-alone` ported that feature to a Kotest spec with the same two checks and the
same 4px tolerance. **Assumed** for the port: *wrong if* the ported test still passes when the
menu's expected top edge is moved by 80px, the offset the 2026-10-31 defect produced. The port's
task runs that control and records the spec's name.

#### Scenario: Click opens it under the button

- **WHEN** the trigger is clicked
- **THEN** the menu is visible
- **AND** its top edge is at the trigger's bottom edge, within a few pixels

#### Scenario: The served markup names no anchor

- **WHEN** the popover dropdown page is served
- **THEN** its body pairs the trigger and the menu by `popovertarget` and `id`
- **AND** it contains no `style=`, no `data-anchor-name` and no `data-position-anchor`
