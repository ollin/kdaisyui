## Context

See proposal.md, Why. Two writers of inline style exist: `Tag.popoverTarget` in core and the
`anchoredPopover` branch of the Kotlin emitter. Both take the name from `HtmlId.anchorName`.

**Revised 2026-11-07 after F6.** Blocks 1-3 of `tasks.md` implemented the first design below
(data attributes plus shipped rules). The control run after task 3.2 refuted its premise, and
Oliver chose the subtraction. Decision 1 is the design now; the first design is kept as a
rejected alternative with the evidence that rejected it.

## Goals / Non-Goals

**Goals:**

- Anchored popovers work with no inline `style` anywhere in kdaisyui's output.
- The anchoring stays tested in a browser.

**Non-Goals:**

- Nonce or hash support for inline styles. Nothing in kdaisyui should need an inline style.
- Anchoring a popover opened from script. kdaisyui opens popovers by attribute only.

## Decisions

### 1. No anchor at all: the implicit anchor of `popovertarget`

**Chosen:** the trigger writes `popovertarget`, the popover `popover` and `id`, and nothing
names an anchor. HTML makes the invoker the popover's implicit anchor; DaisyUI's dropdown CSS
positions with `position-area`, which uses the default anchor. Evidence: the E2E "sits below"
scenario green with no anchor written anywhere (control after task 3.2), and MDN BCD 8.1.8,
`html.elements.button.popovertarget.implicit_anchor_reference` at Chrome 133, Safari 26,
Firefox 147 — no later than anchor positioning itself in any engine.

**`anchored` stays, deprecated.** Removing the parameter breaks every caller that wrote it, for
no behaviour of theirs that changes. So `popoverTarget(popover, action)` is the function, and
`popoverTarget(popover, action, anchored)` delegates to it under `@Deprecated(ReplaceWith(…))`.
Kotlin resolves `popoverTarget(menu)` to the overload that needs no default for an unnamed
parameter, so plain calls do not warn. `HtmlId.anchorName` is deprecated alongside: it is public,
and nothing in the library writes it any more.

**Rejected: data attributes plus typed `attr()` (the first design, built in blocks 2-3).**
`[data-anchor-name] { anchor-name: attr(data-anchor-name type(<custom-ident>)) }` and its pair,
shipped as `kdaisyui.css` and written by the plugin. It works in Chromium, but typed `attr()`
with `<custom-ident>` is Safari preview only and Firefox 155 (BCD 8.1.8), so it would have
anchored nothing in any released Safari — and the control showed it adds nothing where it
works. It also asked every consumer to import a file.

**Rejected: `'unsafe-hashes'` with a hash per anchor value.** One `sha256-…` per anchored popover
on the page, computed from a string kdaisyui derives. Not a library concern, and fragile.

**Rejected: a nonce on a `<style>` element.** kotlinx.html has no notion of a per-request nonce.

**Rejected: a generated class per anchor.** Ids are runtime values; Tailwind cannot see them.

### 2. Undo the first design in reverse order of dependence

The example app stops importing `kdaisyui.css` before the plugin stops writing it, and the plugin
stops before the resource goes, so every step is green under root TCR. The classpath lookup's
generalisation (`resourceIn(entry, name)`, task 2.2) stays: it reads as well with one caller,
and undoing it is churn.

### 3. `popoverTarget` stops touching `style`

Already true after task 3.2; the subtraction removes the attribute write that replaced it.

## Risks / Trade-offs

- [A popover opened from script anchors nowhere] → out of scope, stated in the how-to: pass
  `source` to `showPopover()`.
- [A browser with anchor positioning but without implicit popover anchors] → none per BCD 8.1.8;
  the floors coincide in all three engines.
- [The E2E check cannot show the implicit anchor is what places the menu] → its counterpart was
  observed: on 2026-10-31 a `position-anchor` naming a missing anchor overrode the implicit one
  and the menu opened 80px off, which the same check reported.
