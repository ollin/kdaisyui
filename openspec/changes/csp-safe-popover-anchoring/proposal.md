## Why

An application behind `Content-Security-Policy: default-src 'self'` has no `style-src
'unsafe-inline'`, so the browser drops every inline `style` attribute. kdaisyui writes two:
`popoverTarget(…, anchored = true)` writes `style="anchor-name:--…"` on the trigger
(`lib/src/main/kotlin/io/github/ollin/kdaisyui/core/Popover.kt:14`) and the generated
`daisyDropdownPopover` writes `style="position-anchor:--…"` on the menu
(`codegen/src/generator-new.ts:280`). Under that policy an anchored popover dropdown opens in the
middle of the viewport, with no error anywhere but the console. The Deploy-Radar
(fwb-application, kdaisyui 0.9.0, that exact header) therefore wrote its dropdown as `<details>`
with raw class strings instead; this is item 2 of its proposals to kdaisyui
(`~/ai/kdaisyui-improvements-from-deploy-radar.md`, 2026-11-07).

Oliver's priority for the library: it must work completely from the application's own origin,
and under that policy. This is the one place where kdaisyui's own markup cannot.

## What Changes

- **The anchor pair moves from inline style to data attributes.** The trigger writes
  `data-anchor-name="--…"`, the popover `data-position-anchor="--…"`. The value is still
  `HtmlId.anchorName`, derived in one place, so the two cannot disagree.
- **Two CSS rules turn the attributes into the anchor properties**, with CSS `attr()`:

  ```css
  [data-anchor-name] { anchor-name: attr(data-anchor-name type(<custom-ident>)); }
  [data-position-anchor] { position-anchor: attr(data-position-anchor type(<custom-ident>)); }
  ```

  They ship as a resource in the `kdaisyui` jar, `kdaisyui.css`, beside the class list. The
  Gradle plugin's `kdaisyuiClasses` task writes it to `build/kdaisyui/kdaisyui.css` next to
  `kdaisyui-classes.txt`, and the consumer's entry stylesheet imports it. The example app does.
- **`popoverTarget` no longer touches `style`.** The branch that appended `anchor-name` to an
  existing `style` goes.
- **The documented-element and coverage tests follow**, generated: they assert the attribute, not
  the style.
- **BREAKING for anchored popovers:** a consumer who compiled a stylesheet without the two rules
  loses the anchoring on upgrade. `docs/upgrading.md` gets the "How to migrate from 0.9.x" entry,
  and the README's CSS section names the import.

### The subtractive option, stated

Keep the inline styles and document that anchored popovers need
`style-src-attr 'unsafe-inline'`, or `'unsafe-hashes'` with one hash per anchor name. It adds
nothing to the library. It also tells a strict-CSP application to weaken its policy for one
component, or to compute per-page hashes of values kdaisyui derives, which is the opposite of
"works from the own origin". Rejected.

What this change removes: both inline `style` writes and the style-merging branch in
`popoverTarget`.

## Out of scope

- **The strict-CSP guarantee and its test** — `guarantee-strict-csp` owns it. That change
  depends on this one: its browser check would fail on the anchored pages until the attributes
  land, which is why this change comes first.
- **The stylesheet compile task** — `compile-the-stylesheet-in-the-plugin`. The import of
  `kdaisyui.css` is the same there; that change only moves where the compile runs.
- **Browsers without typed `attr()`.** Anchor positioning is itself Chromium 125, Safari 26 and
  Firefox 147, and DaisyUI's CSS falls back where it is missing; the docs already say
  `daisyDropdown` is the choice that behaves the same everywhere. This change does not widen that.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | Chromium applies `anchor-name: attr(data-anchor-name type(<custom-ident>))` and the matching `position-anchor`, so a popover anchors through data attributes alone | code | confirmed at proposal - `assets/anchor-probe.html` in this change, run in headless Chromium 156 on 2026-11-07, its command and result in its header comment: computed `anchorName` and `positionAnchor` both `--dash_menu`, `CSS.supports` true, and the shown popover's top and left equal the trigger's bottom and left (240, 300). Typed `attr()` shipped in Chrome 133 |
| A2 | The browser the E2E suite runs is at least Chromium 133, locally and in CI | code | to check in task 1.1 - locally `which chromium` finds 156; `ci.yml` pins no browser, so CI's `google-chrome` and Playwright's bundled build are read there. Fallback if CI is below 133: the E2E build prefers Playwright's bundled Chromium in CI |
| A3 | A consumer's Tailwind 4 entry can `@import` a plain CSS file written by the build, relative to the entry | code | to check in task 1.2 - the example app's compile, with `@import "../kdaisyui/kdaisyui.css"` |
| A4 | No requirement of a main spec this builds on contradicts it. It builds on `css-delivery` ("the library's contract ends at the class name"; "the library ships the class list a consumer's CSS build needs"), `daisyui-component-coverage` (the popover trigger and the popover dropdown), `component-model` (static attributes come from the model) and `api-change-detection` | code | confirmed at proposal - `css-delivery` already has the library ship a resource the consumer's CSS build consumes, and this adds a second one of the same kind; the contract sentence is MODIFIED below to say so. `daisyui-component-coverage` names `style="position-anchor:…"` and `anchor-name` in two requirements, both MODIFIED below. `component-model` lists static attributes as a model fact; the anchored-popover fact keeps writing one, with another name. The API baselines do not change: no signature moves |
| A5 | Generation reads the attribute name from one place, so the trigger's and the popover's attribute names cannot drift | code | confirmed at proposal - the popover side is one line in `generator-new.ts:280`, the trigger side one line in `Popover.kt:14`; neither is derived from the other today, and the pairing is tested end to end by `dropdown_popover.feature` |
| A6 | The two rules belong in a jar resource the plugin writes, not in a snippet the docs ask the reader to paste | user | open - ask at review. What cannot be removed: the two rules must reach every consumer's compiled stylesheet. Everything else in block 2 of `tasks.md`, the resource, the plugin's second file, the lookup parameter and their tests, is machinery so that no consumer keeps a copy that drifts; a pasted snippet drops all of it and keeps a copy per consumer |

## Capabilities

### Modified Capabilities

- `daisyui-component-coverage`: the popover dropdown writes `data-position-anchor`, its trigger
  `data-anchor-name`; the anchoring still holds in the browser, now through the shipped rules.
- `css-delivery`: the library ships `kdaisyui.css` beside the class list, the plugin writes both,
  and the contract sentence says what the library's CSS covers.

## Impact

- `lib/src/main/kotlin/…/core/Popover.kt` and `PopoverTest.kt`.
- `codegen/src/generator-new.ts` and `codegen/src/test-generator.ts:113`, which hard-codes the
  expected inline style of the custom-part test; `codegen/test/generator-new.test.ts:185` and
  the snapshots; regenerated `lib/generated/**`, `docs/reference/dropdown.md`, `llms-full.txt`.
- `lib/src/main/resources/kdaisyui.css`, new, and a test that the jar carries it.
- `gradle-plugin`: `KdaisyuiClasses` writes the second file; its tests.
- `example-app/src/main/css/app.css`; `e2e-tests/.../dropdown_popover.feature` (the markup
  assertions).
- `README.md` (CSS section), `docs/how-to.md` (dropdown recipe), `docs/upgrading.md`.
