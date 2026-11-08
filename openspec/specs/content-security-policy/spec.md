# content-security-policy Specification

## Purpose
kdaisyui's markup works under `Content-Security-Policy: default-src 'self'; img-src 'self' data:`: nothing from another
host, no inline script, no inline style; `data:` images only, which DaisyUI's own masks need. The example application
proves it on every page under that header, and the documentation says what the application
supplies.

## Requirements

### Requirement: The markup works under a strict policy

Every element kdaisyui renders SHALL work under `Content-Security-Policy: default-src 'self'; img-src 'self' data:`.
kdaisyui SHALL write no inline `style`, no inline event handler and no `data:` URL itself.

**Verified** that `img-src` needs `data:`: DaisyUI 5.7.50 sets `data:` SVG masks in
`loading.css`, `mask.css`, `chat.css`, `tooltip.css` and `mockup.css`, and the noise in
`base/svg.css`; under `default-src 'self'` the suite logged their refusals, and under this policy
none (F4, F5, A9).

Provenance, in part verified and in part assumed: `grep -rnE 'attributes\["(on|src|style)'` over
`lib/generated/main` and `lib/src/main` finds no write since `csp-safe-popover-anchoring` (#477)
removed the two anchor styles (2026-11-08); and the
324 icon files that write SVG through `raw(` carry no `style=`, no `on…=` and no `data:`
(`grep -rn 'raw(\|unsafe' | grep -E 'style=|on[a-z]+=|data:'`, empty, 2026-11-07). **Assumed**
for the rest; *wrong if* the example application's suite under the header reports a message
naming kdaisyui's output rather than the application's own markup.

#### Scenario: A page of generated components under the header

- **WHEN** a page built from generated components is served with `default-src 'self'; img-src 'self' data:`
- **THEN** the browser logs no refusal

#### Scenario: A popover opens under the header

- **WHEN** a popover modal, popover dropdown or megamenu is opened on such a page
- **THEN** it opens, anchored where it is anchored, and the browser logs no refusal

### Requirement: The example application sends the header and passes

The example application SHALL send `Content-Security-Policy: default-src 'self'; img-src 'self' data:` on every
response, and every E2E scenario SHALL pass with it. It SHALL load nothing from another host
and link an icon of its own origin, so no page load logs a request the policy or the server
refuses.

**Verified** that it does not today, read 2026-11-07: `DashboardShell.kt` writes a `<style>`
element, five files write an `https://picsum.photos` image (`DashboardShell.kt:94`,
`TeamFragment.kt:82`, `RepoFragment.kt:51`, `FormSectionsFragment.kt:43`,
`CardsRow2Fragment.kt:69`), and no page links an icon.

#### Scenario: The header is on every response

- **WHEN** any route of the example application answers
- **THEN** the response carries `Content-Security-Policy: default-src 'self'; img-src 'self' data:`

#### Scenario: Every page is clean

- **WHEN** the E2E suite runs
- **THEN** every scenario passes, and no scenario's page logged an error-level message

### Requirement: A page that logs an error fails its scenario

The E2E suite SHALL fail any scenario, Cucumber or Kotest, whose page logged a console message
of type `error` or raised a page error, and the failure SHALL quote every such message.

**Verified** in task 1.1 (Chromium 156): an inline style refused, a `data:` image refused and a
404 each reach Playwright's `onConsoleMessage` as type `error` before the test ends; the 404's
text names no URL, so the collector adds each message's location (finding F3).

#### Scenario: An inline style is refused

- **WHEN** a test serves itself a page with a `style` attribute under the header
- **THEN** the collector holds the browser's refusal
- **AND** the failure message built from it quotes that refusal

#### Scenario: A probe drains instead of failing

- **WHEN** a test provokes a refusal on purpose and drains the collector itself
- **THEN** its teardown finds nothing and the test passes

#### Scenario: A clean page

- **WHEN** a scenario's page logs nothing
- **THEN** the collector adds nothing to the result

#### Scenario: A page the server refuses

- **WHEN** a scenario's page requests a resource the server answers with 404
- **THEN** the scenario fails, and the failure names the resource

### Requirement: The documentation names the header and what the application supplies

The README SHALL state that kdaisyui's markup works under `default-src 'self'; img-src 'self' data:`, show the header,
say why `img-src` allows `data:` and what breaks without it, and name what the application
supplies: an icon of its own.

#### Scenario: A reader sets up a strict policy

- **WHEN** a developer reads the README's CSS section
- **THEN** it shows the header, the reason for `data:` and the icon, each with its reason
