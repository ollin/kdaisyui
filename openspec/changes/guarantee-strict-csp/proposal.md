## Why

kdaisyui's pitch is server-rendered HTML that needs no script: popover modals, popover
dropdowns and megamenus open with no handler on the page. Nothing checks the stronger claim an
application behind `Content-Security-Policy: default-src 'self'` needs: that no page loads
anything from another host, runs no inline script, applies no inline style and fetches no
`data:` image. The Deploy-Radar (fwb-application, kdaisyui 0.9.0) runs behind exactly that header
and had to write a browser scenario of its own, "A view loads without breaking its own
Content-Security-Policy" (`bdd_tests/.../filters_in_the_browser.feature:83`), which collects
error-level console messages through Playwright. That scenario found two violations kdaisyui's
own suite could not see: the inline anchor style (`csp-safe-popover-anchoring`) and DaisyUI's
noise texture, a `data:` image every button and badge references even in a theme with
`--noise: 0`. This is item 1 of its proposals (`~/ai/kdaisyui-improvements-from-deploy-radar.md`,
2026-11-07), and Oliver's first priority: nothing may be loaded from another web site.

The example app itself breaks the policy today, read 2026-11-07: an inline `<style>` for the
htmx indicator (`example-app/.../DashboardShell.kt:18`), five avatars from
`https://picsum.photos` (`DashboardShell.kt:94`, `team/TeamFragment.kt:82`,
`repo/RepoFragment.kt:51`, `dashboard/FormSectionsFragment.kt:43`,
`dashboard/CardsRow2Fragment.kt:69`, the last four in fragments the shell loads over htmx), and
no favicon, so every page load logs a 404.

## What Changes

- **The guarantee, stated:** kdaisyui's markup works under `default-src 'self'`. The README says
  so, names the header, and lists what the application supplies: the noise rule and a favicon
  of its own. (No `kdaisyui.css`: `csp-safe-popover-anchoring` shipped none, task 1.4.)
- **The example app sends the header** on every response, through Ktor's `DefaultHeaders`.
- **The E2E suite fails a scenario whose page logs an error-level console message**, in both
  runners (Cucumber hooks and the Kotest base spec). A refused inline style, a blocked image, a
  404 and a script error all arrive that way. The suite runs every page of the example app, so
  every generated component the app uses is covered, under the header, in Chromium.
- **The example app's own violations go:** the inline `<style>` moves into `app.css`; the five
  picsum avatars become DaisyUI avatar placeholders with initials, no image at all; `app.css`
  gains `* { --fx-noise: none; }`, unlayered so it beats DaisyUI's components layer; the app
  serves `/static/favicon.svg` and links it.
- **The check is seen to fail** by a kept Kotest spec that serves itself a page with an inline
  style under the header through Playwright's request routing, and asserts the refusal is held
  and named; no page of the example app violates the policy on purpose.
- **The docs:** README, section "Give those classes some CSS", gains "Under a strict
  Content-Security-Policy".

### The subtractive option, stated

Document the header and the two rules, and add no test. It is what the Deploy-Radar's author
would have needed to read. It is rejected because both violations this change is about were
found only by a test; a sentence in the README would not have found the next one. What this
change removes: the picsum image, the inline `<style>` element, and the 404 on every page load.

## Out of scope

- **The inline anchor styles** — `csp-safe-popover-anchoring`, merged in #477: kdaisyui writes no
  anchor and no inline style any more; the popover is anchored to its trigger by the browser.
- **The stylesheet compile task** — `compile-the-stylesheet-in-the-plugin`.
- **A page-head or CSP helper in `ktor-integration`** (item 10 of the proposals). Rejected: the
  header is one line of Ktor, the favicon is the application's, and `ktor-integration`
  deliberately serves no asset (`ktor-integration/build.gradle.kts`, the webjars comment).
  Documented instead.
- **A shipped behaviour script** (item 5) — `document-server-rendered-patterns` shows what
  needs no script, and what does.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | Chromium reports a CSP refusal, a blocked `data:` image and a 404 as console messages of type `error`, and Playwright's `onConsoleMessage` delivers them before the scenario ends | code | confirmed in task 1.1 - a throwaway Kotest spec fulfilled a page through `page.route` with the header, an inline `style`, a `data:` image and a 404 image; Chromium 156 delivered three `type=error` messages to `onConsoleMessage` before the test ended (texts in `findings.md`, F3) |
| A2 | htmx 4.0.1 serves the example app's triggers (`load`, `load delay:100ms`, `revealed`, `click`) with no `'unsafe-eval'`, and injects no `<style>` element of its own | external, code | confirmed at proposal for `hx-get`/`hx-target`/`hx-include`: the Deploy-Radar runs htmx 4 under the header with no violation; and for the style injection: `dist/htmx.js` of the 4.0.1 webjar has no `includeIndicatorStyles`, `inlineStyleNonce` or `createElement("style")`, read 2026-11-07, so the indicator style htmx 2 injected is gone. The delayed and `revealed` triggers: to check in task 1.2 |
| A3 | `* { --fx-noise: none; }` outside any layer keeps the browser from requesting the noise image | code | confirmed at proposal - `daisyui/packages/daisyui/src/components/badge.css:9` reads `var(--fx-noise)` with no condition on `--noise`, and the Deploy-Radar's `app.css` carries that rule since commit "Keep DaisyUI's noise image out of the pages" (#FWB-6226), after which its violations stopped |
| A4 | After the noise rule, the compiled stylesheet of the example app contains no other `url(` that is not the own origin | code | to check in task 1.2 - grep the compiled `app.css` |
| A5 | No requirement of a main spec this builds on contradicts it. It builds on `css-delivery` (the example app uses the documented setup), `daisyui-component-coverage` (E2E coverage of the popover pages) and `documentation-snippets` (new README fences compile or say why not) | code | confirmed at proposal - the setup stays the plugin plus a compiled stylesheet; the popover scenarios keep their assertions and gain the console check; the README additions are CSS and a Ktor line, both skipped with a reason |
| A6 | The check fails on every error-level message, not only on CSP refusals | user | confirmed by the user 2026-11-07 - every error, as recommended: a 404, a blocked image and a refused style are the same defect, and a word filter would let the favicon 404 through |
| A7 | The DaisyUI themes the example app uses render acceptably without the noise texture | user | confirmed by the user 2026-11-07 - as recommended: with `--noise: 0` the texture is invisible already, so the rule only stops a `data:` request that paints nothing |
| A8 | An exception thrown in Kotest's `afterTest` fails the test it follows | code | to check in task 3.3 - seen once on purpose and recorded |

## Capabilities

### New Capabilities

- `content-security-policy`: kdaisyui's markup works under `default-src 'self'`, the example
  application proves it on every page, and the documentation says what the application supplies.

### Modified Capabilities

None. `css-delivery`'s example-app requirement stays as written; its stylesheet gains one rule.

## Impact

- `example-app`: `Application.kt` (the header), `DashboardShell.kt` (style, avatar, favicon
  link), the four fragment files with an avatar, `src/main/css/app.css` (indicator rules, noise
  rule), a `favicon.svg` resource, `build.gradle.kts` only if the resource needs wiring;
  `gradle/libs.versions.toml` gains `ktor-server-default-headers` in the app bundle. No test
  source set is added to `example-app`: the header is asserted by an E2E HTTP step.
- `e2e-tests`: `CucumberHooks.kt`, `PlaywrightSpec.kt`, one shared collector, a header step in
  `FragmentSteps.kt`, and a kept probe spec that proves the check can fail.
- `README.md`.
