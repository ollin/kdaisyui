## Why

`daisyMegamenu` renders `<div class="megamenu …">` and nothing else. DaisyUI's megamenu is
`<div class="megamenu …" id="…" popover>`, opened by a `popovertarget` button and containing one
`<div id="…" popover>` panel per item. **As generated, the component cannot open.**

Found while implementing `support-popover-modals`, which fixed the same class of defect on
`modal`. It is the same root cause and the same blind spot: the popover method introduces **no
new CSS class**, so no generated test fails, `generated-sources-drift` stays green because the
generator is consistently wrong, and the coverage gate is satisfied by code that was never
generated. The example app already renders a megamenu and the E2E already asserts it — but only
its *classes*, which is precisely what this defect does not touch.

This is the third instance of one gap. `dropdown` was the second, and it was worked around rather
than fixed: `componentElements: { "dropdown": "details" }` exists because DaisyUI documents the
popover variant first and the generator could not produce it usably.

## What Changes

- Make DaisyUI's documented megamenu markup expressible from Kotlin, generated rather than
  hand-written.
- Extend the `staticAttributes` mechanism from `support-popover-modals` — which today reaches only
  `customParts` entries — so it can also apply to a component's **main** wrapper. The megamenu's
  `popover` belongs on `daisyMegamenu` itself, which carries the size and modifier parameters.
- Add a wrapper for the inner popover panels. These carry **no DaisyUI class at all** — they are
  `<div id="…" popover>` — so they need a `customParts` entry with `cssClass: null`, a shape
  `breadcrumbs` already uses.
- Prove it opens in a browser, with the `@nojs` E2E path that `support-popover-modals` added.

**Considered and rejected — the subtractive option:** drop `megamenu` from generation entirely
(`skip`, as `accordion` and `pagination` are) and let callers hand-write it. It is genuinely
tempting: megamenu is one component, its markup is intricate, and a wrapper that only *half*
works is worse than none. Rejected because the same argument would have removed `modal`'s popover
method, and because the mechanism this needs now exists — the cost is configuration plus one
generator change, not a new subsystem. If the first task shows the markup cannot be expressed
without hand-writing, this option becomes live again.

Out of scope:

- `dropdown`. Its `componentElements` override is a *deliberate, working* choice of the other
  documented method, not a defect. Revisiting it is a separate decision.
- A general mechanism for components with more than one root element. Modal needed a second
  wrapper, megamenu needs an attribute on its existing one — related, but not the same problem.
- The `megamenu-active` element type (see Assumptions).

## Capabilities

### Modified Capabilities

- `daisyui-component-coverage`: the popover-method requirement added by `support-popover-modals`
  is stated for `modal` specifically. Generalise it, or add megamenu's own requirement beside it,
  so "the component can be constructed as documented" is the standard rather than a per-component
  favour.

## Assumptions

Ordered by how much rests on them; `tasks.md` checks them in this order.

1. **The megamenu root needs `popover` unconditionally.** DaisyUI's rules say the button is hidden
   on large screens (`sm:hidden`) and the megamenu "will be a horizontal menu", while the same
   element carries `popover` in every documented example. `popover` implies `display: none` until
   opened, so something in the CSS must restore it at that breakpoint.
   *Wrong if:* a `<div class="megamenu" popover>` is invisible on a desktop viewport, in which
   case the attribute is not unconditional and the wrapper needs it as a **parameter** rather than
   a static attribute. **Not verifiable by reading**: `megamenu.css` is 316 lines of nested
   `:popover-open`, `@starting-style` and `position-area` rules, and the honest check is to render
   it and look. This is task 1.1 and it is a browser task.
2. **The inner panels can be a `customParts` entry with `cssClass: null`.** `breadcrumbs` already
   generates class-less parts, and `staticAttributes` landed with `support-popover-modals`.
   *Wrong if:* the generated function cannot be given the distinct `id` each panel needs — but
   every custom part already takes `id: HtmlId?`, so this is expected to hold.
3. **`staticAttributes` can be lifted to the main component function.** Today
   `generateCustomPartFunction` reads it; `generateComponentFunction` does not.
   *Wrong if:* the main function's body is assembled somewhere that cannot accept it — unlikely,
   it is the same generator file.
4. **`megamenu-active` should be a `<span>`, not a `<div>`.** DaisyUI documents
   `<span class="megamenu-active"></span>` and calls it mandatory; `daisyMegamenuActive` emits a
   `div`. *Wrong if:* the CSS does not care, which it may not. Recorded rather than fixed here —
   it is a `subComponentElements` entry if it matters, and noise if it does not.

## Impact

- `codegen/src/generator-new.js` — `staticAttributes` on the main component path.
- `codegen/codegen-config.json` — the megamenu entries.
- `lib/generated/…/components/Megamenu.kt` and its generated tests — regenerated output.
- `example-app` — the existing megamenu card in `WhatsNewFragment.kt` becomes a real consumer, or
  gains a route of its own; the current card renders a megamenu that cannot open.
- `e2e-tests` — a scenario that opens it, following the `@nojs` pattern.
- `lib/api/lib.api` — the API baseline moves, which is now a gated, deliberate act.
- No change to `gradle/libs.versions.toml`, and no release of its own.
