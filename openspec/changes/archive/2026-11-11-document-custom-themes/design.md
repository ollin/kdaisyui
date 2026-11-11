## Context

The how-to's recipe "Let users choose a theme" covers the 35 built-in themes. A theme of one's own
gets one sentence. The facts the new text states were established at proposal: a custom theme
compiles in the plugin's container and is reachable by `data-theme` and by a theme controller
(A1), and the theme check cannot see it (A2).

## Goals / Non-Goals

**Goals:** a human and an agent can add a branded theme and use it from Kotlin without reading
DaisyUI's docs first, and know where kdaisyui's typed theme API stops.

**Non-Goals:** any API, any change to the example app, customising built-in themes (proposal,
*Out of scope*).

## Decisions

### D1 - A sub-section of the existing theme recipe, not a recipe of its own

**Chosen:** "A theme of your own" inside "Let users choose a theme", where the sentence it
replaces stands, between the stylesheet step and the page step.

**Rejected:** a separate `## Add a theme of your own` recipe. The stylesheet entry, the warning
and the `data-theme` attribute are explained in the recipe already; a second recipe would repeat
them or point back, and `llms.txt`'s note lists recipes, so the index would grow by one more item
for the same topic.

### D2 - The name as a Kotlin constant in the snippet

**Chosen:** `const val ACME_THEME = "acme"`, used for `data-theme` and the controller's `value`.
It is the one place the Kotlin side spells the name the CSS declares, which is as close to typing
as documentation can get without an API.

**Rejected:** the literal string at each call. It is what the old sentence showed, and it is the
pattern that lets a typo render the default theme with no error.

### D3 - The CSS block is short, and points at DaisyUI for the variables

**Chosen:** the block shows `name`, `color-scheme` and a few colour variables with a comment that
DaisyUI's theme page and its theme generator list the rest. The CSS fence is not compiled by
`:doc-snippets`, which compiles Kotlin only; its shape is the one A1 compiled.

**Rejected:** copying DaisyUI's full 30-line block. It duplicates a page that changes with DaisyUI,
and a stale copy here would be wrong silently.

## Risks / Trade-offs

- [A DaisyUI bump changes the `@plugin "daisyui/theme"` syntax] → the how-to links DaisyUI's page
  for the variables; the block itself would be wrong until someone reads it. No gate compiles CSS
  from the docs; accepted, as for the existing `@plugin "daisyui"` line.
