## ADDED Requirements

### Requirement: `tab`'s container and item are the elements DaisyUI names

`daisyTab` SHALL render the container carrying `tabs`, and a separate item function SHALL render
the focusable element carrying `tab`, with `tab-active` and `tab-disabled` declared on the item.

This is the one component where the element cross-check's finding is not that a class sits on the
wrong function, but that the two functions are inverted: the generator picked `tab` for the
container from DaisyUI's first example, so the container wears the item's class and the item does
not exist. Tracked as #342, and carried by the standing exception `elementCrossCheckExceptions`
keys `tab`.

**Verified** that the disagreement is real and still present: generation reports `tab` as an
element disagreement on every run, excused only by that exception, which names #342.

**Assumed** that the repair is a second function rather than a changed element on the existing
one, by analogy with every other container/item pair this library generates.
*Wrong if:* DaisyUI documents a single-element tab construction that carries both classes — then
the repair is an element change and not a new function.

#### Scenario: The container and item of `tab` trade places

- **WHEN** `daisyTab` is called
- **THEN** it renders the container carrying `tabs`, and the item function renders the focusable
  element carrying `tab`, with `tab-active` and `tab-disabled` declared on the item

#### Scenario: The standing exception expires with the repair

- **WHEN** `tab` renders the container's element and the item function carries `tab`
- **THEN** the `tab` entry in `elementCrossCheckExceptions` is no longer needed and generation
  fails while it remains
