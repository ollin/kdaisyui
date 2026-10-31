## Context

The model declares static attributes (`"attributes": { "popover": "" }` on `daisyModalPopover`),
and every generated function takes `id: HtmlId? = null`. The popover dropdown needs two things
neither provides: an `id` that is required, and an attribute whose value comes from that `id`.

## Decisions

### 1. One model fact, "anchored popover", not a template language

**Chosen:** a construction declares `"anchoredPopover": true`. It makes `id` required and writes
`popover` and `style="position-anchor:<anchor name>"`.

**Rejected: a generic id-template attribute**, such as
`"idAttributes": { "style": "position-anchor:--{id}" }` with a separate `"idRequired": true`. It is
more general, and it is a small template language inside JSON that nothing else needs. It would
also let a model write an anchor name that does not match the trigger's. One named fact keeps the
pairing in code, where both sides call the same function.

**Rejected: a hand-written `daisyDropdownPopover` in core.** It would escape the completeness
gate, the reference pages, `llms.txt` and the generated tests, which is the drift the model exists
to stop.

### 2. The trigger is a core helper on any tag, not a generated trigger function

**Chosen:** `fun Tag.popoverTarget(popover: HtmlId, action: PopoverTargetAction? = null)`, called
in the trigger's `attrs` or on any tag: `daisyButton("Menu", attrs = { popoverTarget(menuId) })`.
It writes `popovertarget`, `popovertargetaction` when given, and `anchor-name`. The anchor name is
appended to an existing `style`, never replacing it.

**Rejected: `daisyDropdownTrigger(…)`.** The trigger is whatever the author clicks, usually a
button but just as well a link or an avatar. A generated trigger would fix one element, and it
would not serve the modal or the megamenu, which need the same `popovertarget`.

**Rejected: the popover writing its own trigger.** A function renders one element, and the
trigger is not inside the popover.

Writing `anchor-name` on a modal or megamenu trigger, which has no anchored popover, is harmless:
nothing reads it.

### 3. The anchor name is derived in one place

`val HtmlId.anchorName: String` in `core/TagId.kt`: `--` plus the id, with every character a CSS
dashed-ident does not allow replaced by `_`. The generated `daisyDropdownPopover` and the
hand-written `popoverTarget` both call it, so the two cannot disagree. The alternative, writing
`--${id.id}` in both places, breaks silently on an id like `htmlId("main menu")`.

### 4. Enablers before the desired change

The required `id` is an enabler: the shape gains `idRequired`, and no model sets it, so the
regeneration diff must be empty (tasks 2.1, 2.2). The desired changes are the anchored-popover
fact, the model entry and the trigger helper.

## Risks / trade-offs

- **Browser support.** Anchor positioning is Chromium 125+, Safari 26+ and Firefox 147+. DaisyUI's
  CSS has an `@supports not (position-area: …)` fallback. The KDoc says the method is DaisyUI's
  "new" one and names the fallback, and `daisyDropdown` stays the no-surprises choice.
- **A second dropdown function.** Two constructions of one component exist already: `modal` has
  `daisyModal` and `daisyModalPopover`. The component-model spec counts that case.
