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

**Revised 2026-11-07 after F6.** The change first moved the anchor pair to data attributes and
shipped two CSS rules turning them back into anchor properties with typed `attr()`. A positive
control then showed the menu anchored without the rules, and the compat data showed the rules
would anchor nothing in any released Safari. Oliver chose to subtract.

- **Neither side writes an anchor.** A popover opened by `popovertarget` has its invoker as its
  implicit anchor (HTML and CSS Anchor Positioning; Chrome 133, Safari 26, Firefox 147), and
  DaisyUI's `position-area` places the menu against it. So `daisyDropdownPopover` writes
  `popover` and its `id` and no `style`, and `popoverTarget` writes `popovertarget` (and
  `popovertargetaction`) and no `style`.
- **`anchored` is deprecated and does nothing.** `popoverTarget(popover, action)` is the
  function to call; the three-parameter form stays, `@Deprecated` with a `ReplaceWith`, so no
  caller breaks. `HtmlId.anchorName`, which nothing in the library uses any more, is deprecated
  too.
- **No stylesheet of kdaisyui's own.** The intermediate `kdaisyui.css`, the plugin's second
  output and the example app's import go again; the library's CSS contract (`css-delivery`) is
  unchanged by this change.
- **The documented-element and coverage tests follow**, generated: the custom-part test asserts
  `popover` and nothing anchor-related.
- **Not breaking:** a call with `anchored = true` compiles with a deprecation warning and
  renders a menu that still opens at its trigger. `docs/upgrading.md` says so.

### The subtractive option, stated

This is it: delete both anchor writes and rely on the browser. What it removes: both inline
`style` writes, the style-merging branch in `popoverTarget`, and every intermediate piece the
first plan added — the data attributes, `kdaisyui.css`, its test and the plugin's second output.
The one addition is the deprecated overload that keeps `anchored` compiling.

## Out of scope

- **The strict-CSP guarantee and its test** — `guarantee-strict-csp` owns it, and depends on this
  change. Its proposal and spec still name `kdaisyui.css`; that change revises them when it is
  applied (finding F7).
- **The stylesheet compile task** — `compile-the-stylesheet-in-the-plugin`. Its design names the
  `kdaisyui.css` import; the same revision applies there (F7).
- **A popover opened from script.** `showPopover()` without `source` sets no invoker, so no
  implicit anchor. kdaisyui's markup opens popovers by attribute only; a caller who opens one
  from script passes `source` or writes their own anchor.
- **Browsers without anchor positioning.** DaisyUI's CSS falls back where it is missing; the docs
  already say `daisyDropdown` is the choice that behaves the same everywhere.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | Chromium applies `anchor-name: attr(data-anchor-name type(<custom-ident>))` and the matching `position-anchor`, so a popover anchors through data attributes alone | code | false - true in Chromium (`assets/anchor-probe.html`, Chromium 156), but typed `attr()` with `<custom-ident>` is Safari preview only and Firefox 155 (MDN BCD 8.1.8), and the anchor is not needed at all: the implicit anchor of `popovertarget` replaced the route (finding F6) |
| A2 | The browser the E2E suite runs is at least Chromium 133, locally and in CI | code | confirmed in task 1.1 - locally `/usr/bin/chromium` is 156.0.8078.79; CI's E2E job of run 42393356804 (2026-11-07) ran on `ubuntu-26.04` image 20261101.186, whose readme lists Google Chrome 156.0.8078.79 and Chromium 156.0.8078.0, and its `playwrightInstall` fetched Chrome for Testing 156.0.8078.4. All above 133; no fallback needed |
| A3 | A consumer's Tailwind 4 entry can `@import` a plain CSS file written by the build, relative to the entry | code | confirmed in task 1.2 - a scratch `example-app/build/kdaisyui/kdaisyui.css` with the two rules, imported by `@import "../kdaisyui/kdaisyui.css";` right after `@import "tailwindcss"`, compiled by `:example-app:compileTailwind` (Tailwind 4.3.4, daisyUI 5.7.50): the output carries both rules verbatim. Moot since F6: nothing is imported |
| A4 | No requirement of a main spec this builds on contradicts it. It builds on `css-delivery` ("the library's contract ends at the class name"), `daisyui-component-coverage` (the popover trigger and the popover dropdown), `component-model` (static attributes come from the model) and `api-change-detection` | code | confirmed in task 1.3 - the four main specs carry no requirement on inline style beyond the two `daisyui-component-coverage` requirements MODIFIED below; `css-delivery` is no longer touched; `component-model` keeps `popover` as a static attribute of the anchored-popover fact; `api-change-detection` sees one added overload and two deprecations, no removal. The megamenu requirement sets `anchor-name` through DaisyUI's CSS, not `popoverTarget`, and stays untouched |
| A5 | Generation reads the attribute name from one place, so the trigger's and the popover's attribute names cannot drift | code | confirmed at proposal - moot since F6: neither side writes a name |
| A6 | The two rules belong in a jar resource the plugin writes, not in a snippet the docs ask the reader to paste | user | confirmed by the user 2026-11-07 - moot since F6: no rules ship |
| A7 | A popover opened by `popovertarget` is anchored to its invoker with no `anchor-name` or `position-anchor` anywhere, in every engine that has anchor positioning | code | confirmed after task 3.2 - E2E "sits below" green in Chromium 156 with `kdaisyui.css` not imported; MDN BCD 8.1.8 `html.elements.button.popovertarget.implicit_anchor_reference`: Chrome 133, Safari 26, Firefox 147 (finding F6) |

## Capabilities

### Modified Capabilities

- `daisyui-component-coverage`: the popover dropdown and its trigger write no anchor; the menu
  is anchored to the trigger that opens it by the browser; `anchored` is deprecated.

## Impact

- `lib/src/main/kotlin/…/core/Popover.kt`, `TagId.kt`, `PopoverTest.kt`; `lib/api/lib.api` (one
  added overload).
- `codegen/src/generator-new.ts`, `codegen/src/test-generator.ts`,
  `codegen/test/generator-new.test.ts`; regenerated `lib/generated/**`.
- `example-app/…/DropdownPopoverPage.kt`; `e2e-tests/.../dropdown_popover.feature`.
- Removed again: `lib/src/main/resources/kdaisyui.css`, `KdaisyuiCssTest.kt`, the plugin's
  `stylesheet` output and its tests, the example app's import and input.
- `docs/how-to.md` (dropdown recipe), `docs/upgrading.md`.
