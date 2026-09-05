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

### CORRECTION — the small-viewport behaviour is genuinely missing

An earlier version of this note said the 390px rendering matched daisyui.com and my expectation
was wrong. **That was itself wrong**, from misreading a "yes" as being about daisyui.com when it
was about this page. Oliver pushed back after looking at the running app; measurement settled it.

**Measured** at 390px against the live app:

| Where | `.megamenu` | `.megamenu-vertical` | `max-sm:megamenu-vertical` | `sm:hidden` |
|---|---|---|---|---|
| `daisyui.css` webjar (1.1 MB, prebuilt) | present | present | **absent** | absent |
| Tailwind browser `<style>` (6 KB, runtime) | absent | absent | **absent** | present |

So the class sits on the element and **no rule anywhere matches it**. The megamenu never becomes
vertical, never hides when closed, and the bar stays visible next to the `Menu` button.

**Cause.** The example app delivers CSS two ways: DaisyUI's components come prebuilt from the
webjar, and Tailwind's utilities are generated in the browser from the DOM. Tailwind can only
build a variant of a class it owns — it generated `sm:hidden` because `hidden` is its own utility,
and could not generate `max-sm:megamenu-vertical` because `megamenu-vertical` belongs to a
stylesheet it never sees. daisyui.com has no such split; it compiles with the DaisyUI plugin at
build time, where the variant is generated normally.

**Scope.** Not a DaisyUI defect and not a kdaisyui defect — the library only emits class names.
It is a limit of the example app's CSS delivery, and it applies to **every** `variant:daisy-class`
combination, not just this one. Consequences to settle:

- the reference page overstates what it demonstrates while it carries a class that does nothing;
- task 5.1's `@nojs` scenario is unaffected, because it asserts `:popover-open`, which is DOM
  state and needs no CSS;
- whether the example app should compile Tailwind at build time is a real question and **Oliver's
  to decide** — it is a change to the demo's toolchain, not to this change's scope.

**Assumption 1 is unaffected.** The root's `popover` is still unconditional: at 1280px the bar is
visible *with* the attribute present, and that visibility comes from `.megamenu` in the prebuilt
daisyui.css, which is loaded and working.

### Method note: four instrument defects, none in the product

Every "finding" in this task turned out to be a fault in how I was looking:

1. screenshot taken mid-transition — read as a layout fault;
2. `getByText("Menu")` matched the heading *"Mega**menu** reference"* and clicked it — read as the
   popover not opening;
3. a DOM probe that treated `CSSStyleRule.cssRules` as "this is a grouping rule", which is now
   true of *every* rule under nested CSS, so it skipped all of them and reported zero rules
   everywhere — read as "DaisyUI's CSS is not loaded";
4. searching only `<style>` elements for `.megamenu`, when it lives in a linked stylesheet — read
   as "the component CSS is missing".

Three of the four would have been reported as somebody else's bug. The one real finding came from
Oliver looking at a running page and saying it did not match what he expected.

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
