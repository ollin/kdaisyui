## Why

Four of the Deploy-Radar's proposals to kdaisyui (`~/ai/kdaisyui-improvements-from-deploy-radar.md`,
2026-11-07, items 5, 6, 8 and 9) are patterns the library already supports and no page
documents, so the Deploy-Radar (fwb-application, kdaisyui 0.9.0, htmx 4, strict CSP) wrote them
with raw class strings or its own script:

- **A modal the server renders open.** Its notes dialog is swapped in by htmx already open, as
  `div("modal modal-open")` (`web/NotesDialog.kt`), because `daisyModal(open = true)` only adds
  the class and nobody knew whether `<dialog class="modal modal-open">` shows at all. It does
  (`daisyui/packages/daisyui/src/components/modal.css`: `.modal` is `display: grid` and
  `&.modal-open, &[open]` make it visible); and a `<dialog open>` closes with
  `<form method="dialog">`, with no script.
- **A form inside a dropdown.** `recordDeploymentForm` (`web/Layout.kt`) writes
  `input input-sm`, `dropdown-content …` and `btn btn-sm btn-primary` by hand inside
  `details`/`summary`.
- **Behaviour without inline handlers.** Under the policy there is no `onclick`; it shipped a
  12-line script for `data-copy` and `data-close` (`static/deploy-radar.js`) and proposed that
  kdaisyui ship one. Of what it needed, only the clipboard needs a script: a popover modal
  opens and closes by `popoverTarget`, a `<dialog open>` closes by `form method="dialog"`, a
  `<details>` dropdown by its summary.
- **A stable marker in the compiled stylesheet.** Its test for the stylesheet looked for the
  `daisyUI` banner, which `--minify` removes (its finding F12), and now looks for
  `--color-base-100`.

## What Changes

- **The example app gets a page whose button swaps in a modal rendered open**, built with
  `daisyModal`, `daisyModalBox` and `daisyModalAction`, closed by a `form method="dialog"`
  button; an E2E scenario clicks it open, sees it, clicks Close, sees it gone.
- **Three how-to sections,** each snippet compiled by `:doc-snippets`: "Render a modal the
  server already opened", "Put a form in a dropdown", and "Behaviour without a script", which
  says what needs none and shows the one thing that does, a document-level clipboard listener
  of six lines the application owns.
- **One paragraph in the README's CSS section:** what a consumer can rely on in the compiled
  stylesheet, the theme's custom properties such as `--color-base-100`, and what they cannot,
  the banner comment under `--minify`.
- No API change. The subtractive answer to item 5 is this change: nothing is shipped, because
  three of the four behaviours need no script.

### The subtractive option, stated

Document nothing and answer item 5 by shipping the script. Rejected: a script for behaviours
that need none teaches consumers to reach for it, and the one behaviour that needs a script is
six lines an application should own, since it binds to the application's ids. What this change
removes: in a consumer, raw class strings and a script for closing.

## Out of scope

- **A shipped behaviour script** (item 5 as proposed). Rejected here with the reason above; if
  Oliver wants one, it is its own change with its own assumptions (A5).
- **A `div` variant of the modal.** `<dialog class="modal" open>` renders and closes without
  one; the proposal's "if not" branch does not arise.
- **Badges and buttons as links** — `name-the-class-sibling-in-every-kdoc`; the form recipe
  uses `summary { daisyButtonClasses(…) }`, which that change's how-to also shows.
- **The strict-CSP header and test** — `guarantee-strict-csp`; this change's page passes it.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | `<dialog class="modal" open>` is visible without a script, and a `form method="dialog"` button inside it removes `open` and hides it | code | confirmed at proposal by reading `modal.css`: `.modal` is `display: grid` and `invisible` (line 3), so the user agent's `display: none` for a closed dialog never applies and the closed state is `visibility: hidden` plus `pointer-events: none`; the visible rule (lines 34-39) matches `&[open]`, which the form's submit removes. The browser check is tasks 2.2 and 2.3 |
| A2 | htmx 4 inserts a swapped-in `<dialog open>` such that the browser shows it open | code | to check in task 2.2 - the E2E scenario; a dialog with the `open` attribute at parse time is open by the HTML specification |
| A3 | kotlinx.html 0.12.0 has a `dialog` builder with no `open` property, and `FormMethod` has no `dialog` entry | code | confirmed at proposal - `kotlinx/html/DIALOG.class` and `FormMethod.class` of kotlinx-html-jvm 0.12.0, read 2026-11-07; the snippets write both attributes by name |
| A4 | No requirement of a main spec this builds on contradicts it. It builds on `daisyui-component-coverage` ("Popover modals are expressible from the DSL": `daisyModal` stays unchanged and recommended; "New components have E2E smoke coverage"), `documentation-snippets` and `css-delivery` | code | confirmed at proposal - `daisyModal` is not touched; the new page is asserted by E2E; every Kotlin fence compiles, and the JavaScript fence is not Kotlin; the README paragraph adds to what `css-delivery` already has the docs say |
| A5 | kdaisyui ships no behaviour script | user | confirmed by the user 2026-11-07 - none, as recommended: three of the four behaviours are the browser's, and the clipboard is six lines that must know the application's ids |
| A6 | The `open` parameter of `daisyModal` keeps its meaning, "adds `modal-open`", and the recipe writes the attribute instead | user | confirmed by the user 2026-11-07 - as recommended: the class and the attribute mean different things, and only the attribute is what `form method="dialog"` removes |

## Capabilities

### Modified Capabilities

- `daisyui-component-coverage`: a modal the server renders open shows and closes without a
  script, on a demo route the E2E suite asserts.
- `css-delivery`: the documentation names what a consumer can rely on in the compiled
  stylesheet.

## Impact

- `example-app`: a page loading the stylesheet and the htmx webjar script, its resource class
  in `DashboardResources.kt`, ids in `Ids.kt`, a route and a fragment route in
  `DashboardRoutes.kt`; `e2e-tests`: one feature, steps as needed.
- `docs/how-to.md`: three sections; `README.md`: one paragraph; `codegen/llms-template.md:19`
  and the regenerated `llms.txt`, since the how-to's description there lists its recipes.
