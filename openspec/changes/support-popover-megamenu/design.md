# Design

## Decision: `popover` on the megamenu root is a static attribute

Task 1.1 settled the question the change hung on. The root carries `popover` at **every**
viewport — DaisyUI's own CSS is what makes it a visible horizontal bar on desktop, not the absence
of the attribute. So there is nothing viewport-dependent for a caller to decide, and nothing to
expose as a parameter.

**Verified** by rendering DaisyUI's documented markup at 1280x800 and 390x844 and reviewing the
screenshots, which match daisyui.com at both widths. Evidence in `notes.md`.

### The mechanism: a new `componentAttributes` config key

`staticAttributes` already exists, added by `support-popover-modals`, but
`generateCustomPartFunction` is the only thing that reads it — it applies to a component's *extra*
wrappers. The megamenu's `popover` belongs on `daisyMegamenu` itself, which is the function
carrying the size and modifier parameters, so the same idea has to reach the main component path:

```json
"componentAttributes": {
  "megamenu": { "popover": "" }
}
```

Emitted exactly as `staticAttributes` is: after `id`, before `extraClasses`, so `attrs()` still
runs last and a caller can override.

**Two keys rather than one** because they address different functions — `customParts` entries are
separate generated wrappers, the main component is not. Merging them would mean one key whose
meaning depends on where it appears. If a third case turns up, that is the moment to unify them.

### The panels: a class-less `customParts` entry

Each item's panel is `<div id="…" popover>` with **no DaisyUI class at all**. `customParts`
already supports `cssClass: null` (`breadcrumbs` uses it), and now also `staticAttributes`, so the
panel needs no new mechanism:

```json
"megamenu": [
  { "name": "Panel", "element": "DIV", "cssClass": null, "staticAttributes": { "popover": "" } }
]
```

That produces `daisyMegamenuPanel(id = …) { … }`. The `id` is not optional in practice — a panel
nothing can target is inert — but it stays `HtmlId? = null` to match every other generated
wrapper, and the E2E is what proves the pairing works. Same reasoning as `daisyModalPopover`.

### The trigger buttons stay plain

`<button popovertarget="…">` needs no wrapper. `daisyButton(attrs = { … })` already expresses it,
and inventing a `daisyMegamenuTrigger` would add API for a one-attribute button.

Note for whoever writes the demo: pass the attribute block as **`attrs`**, not as a trailing
lambda. The trailing lambda binds to `content`, which is documented to take precedence over
`text`, so `daisyButton(text = "Components") { … }` renders a button with no label. Pinned by
`TextAndContentPrecedenceTest`.

## What this does not decide

- **`megamenu-active` as `<span>` vs `<div>`.** DaisyUI documents a `<span>` and calls it
  mandatory; the generator emits a `div`. The reference page uses a `span` and renders correctly,
  but that does not establish the `div` is wrong. Left to task 6.1, which should check whether the
  CSS actually cares before adding a `subComponentElements` entry for it.
- **Whether the demo lives in `WhatsNewFragment` or on its own route.** Task 5.1. The modal needed
  its own route because an htmx-loaded fragment is unreachable with JavaScript disabled; the
  megamenu has the same constraint if its scenario is to be tagged `@nojs`.

## Alternatives rejected

**`popover` as a Boolean parameter.** Would let a caller build a megamenu that cannot open, which
is the defect this change exists to remove. Rejected on the evidence: there is no documented
megamenu without it.

**Reusing `staticAttributes` for the main component.** One key, two meanings depending on which
part of the config it sits in. Rejected as the more confusing of two small options.

**`skip`-ping megamenu entirely** — the subtractive option the proposal named. Still available and
still coherent: megamenu is one intricate component, and a wrapper that half-works is worse than
none. Rejected because task 1.1 showed the markup is expressible and the mechanism is one config
key plus one generator change, not a new subsystem.
