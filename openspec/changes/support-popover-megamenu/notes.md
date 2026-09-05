# Notes

## 1.1 — the megamenu root's `popover` is unconditional (2026-09-04)

**Assumption 1 confirmed.** The root carries `popover` at every viewport; DaisyUI's CSS is what
makes it visible on desktop rather than the attribute being conditional. So `popover` is a
**static attribute**, and tasks 2.1 onward stand as written.

**Method.** DaisyUI's documented markup (`megamenu.md:14-30`) hand-written at
`/megamenu-reference`, screenshotted at 1280x800 and 390x844, closed and opened. Reviewed by
Oliver against daisyui.com, which is the part that settled it.

### What the four images show

| Viewport | Closed | Opened |
|---|---|---|
| 1280x800 | horizontal bar visible, `Menu` trigger hidden by `sm:hidden` | panel drops below its button |
| 390x844 | `Menu` trigger **and** the bar both visible | bottom sheet with backdrop, first panel's content shown, item buttons hidden |

**The one thing that needed a human.** At 390px the bar is visible next to the `Menu` button. I
expected it hidden, reading `megamenu.css:254` (`&:not([popover]:popover-open) { display: none }`
under `megamenu-vertical`) as applying there. Oliver checked daisyui.com at the same width and
sees the same thing, so **the reference page renders as DaisyUI does** and the expectation was
wrong — that rule sits in a narrower selector context than I read it as. No Tailwind-browser
artifact, nothing to fix.

Worth keeping as a method note: this was not answerable by reading the CSS, and it was not
answerable by any assertion I could have written, because the page was behaving correctly. It
needed someone who knows what the component is supposed to look like.

### Three defects in the probe itself, all found by looking at the pictures

None of these were DaisyUI's, and two of them would have been reported as DaisyUI's if the images
had not been reviewed:

1. **Screenshot taken mid-transition.** `isVisible()` is true the moment an element stops being
   `display: none`, but DaisyUI transitions opacity and scale over 200ms — so the first
   `desktop-panel-open` showed a half-faded panel overlapping the text behind it, which reads
   exactly like a z-index or layout fault. Fixed with `setAnimations(DISABLED)`.
2. **`getByText("Menu")` clicked the heading.** `getByText` is a case-insensitive substring
   match, so it matched *"Mega**menu** reference"* and `.first()` returned the `<h1>`. The click
   landed on the heading, the popover never opened, and the failure was indistinguishable from
   "the megamenu is broken". **This one would have produced a fabricated DaisyUI bug report.**
   Fixed by clicking `getByRole(BUTTON, name, exact = true)`.
3. **No assertion between the click and the screenshot.** A popover that never opened would have
   screenshotted green forever. Fixed by asserting `:popover-open` and visibility first.

The general lesson, and the reason the page is kept rather than thrown away: a screenshot with no
assertion in front of it proves nothing, and an assertion with no screenshot behind it cannot be
sanity-checked by a person. Both are needed, and the second one is what caught defects 1 and 2.
