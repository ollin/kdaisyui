## Purpose

kdaisyui's markup works under `Content-Security-Policy: default-src 'self'`: nothing from
another host, no inline script, no inline style, no `data:` image. The example application
proves it on every page under that header, and the documentation says what the application
supplies.

## ADDED Requirements

### Requirement: The markup works under a strict policy

Every element kdaisyui renders SHALL work under `Content-Security-Policy: default-src 'self'`
once the application's stylesheet carries `kdaisyui.css` and DaisyUI's noise texture is switched
off. kdaisyui SHALL write no inline `style`, no inline event handler and no `data:` URL.

Provenance, in part verified and in part assumed: `grep -rnE 'attributes\["(on|src|style)'` over
`lib/generated/main` finds one write, the `position-anchor` style of `Dropdown.kt:114`, which
`csp-safe-popover-anchoring` removes together with the trigger's in `core/Popover.kt`; and the
324 icon files that write SVG through `raw(` carry no `style=`, no `on…=` and no `data:`
(`grep -rn 'raw(\|unsafe' | grep -E 'style=|on[a-z]+=|data:'`, empty, 2026-11-07). **Assumed**
for the rest; *wrong if* the example application's suite under the header reports a message
naming kdaisyui's output rather than the application's own markup.

#### Scenario: A page of generated components under the header

- **WHEN** a page built from generated components is served with `default-src 'self'`
- **THEN** the browser logs no refusal

#### Scenario: A popover opens under the header

- **WHEN** a popover modal, popover dropdown or megamenu is opened on such a page
- **THEN** it opens, anchored where it is anchored, and the browser logs no refusal

### Requirement: The example application sends the header and passes

The example application SHALL send `Content-Security-Policy: default-src 'self'` on every
response, and every E2E scenario SHALL pass with it. It SHALL load nothing from another host
and link an icon of its own origin, so no page load logs a request the policy or the server
refuses.

**Verified** that it does not today, read 2026-11-07: `DashboardShell.kt` writes a `<style>`
element, five files write an `https://picsum.photos` image (`DashboardShell.kt:94`,
`TeamFragment.kt:82`, `RepoFragment.kt:51`, `FormSectionsFragment.kt:43`,
`CardsRow2Fragment.kt:69`), and no page links an icon.

#### Scenario: The header is on every response

- **WHEN** any route of the example application answers
- **THEN** the response carries `Content-Security-Policy: default-src 'self'`

#### Scenario: Every page is clean

- **WHEN** the E2E suite runs
- **THEN** every scenario passes, and no scenario's page logged an error-level message

### Requirement: A page that logs an error fails its scenario

The E2E suite SHALL fail any scenario, Cucumber or Kotest, whose page logged a console message
of type `error` or raised a page error, and the failure SHALL quote every such message.

**Assumed** that Chromium delivers a policy refusal and a blocked `data:` image as console
messages of type `error` through Playwright. *Wrong if:* the scratch page of task 1.1, with one
inline style and one `data:` image under the header, produces no error-level message. The
Deploy-Radar's `BrowserSteps.kt:39` collects exactly these messages for its refusal check.

#### Scenario: An inline style is refused

- **WHEN** a test serves itself a page with a `style` attribute under the header
- **THEN** the collector holds the browser's refusal
- **AND** the failure message built from it quotes that refusal

#### Scenario: A probe drains instead of failing

- **WHEN** a scenario is tagged as a probe of the policy
- **THEN** its teardown drains the collector and the scenario passes

#### Scenario: A clean page

- **WHEN** a scenario's page logs nothing
- **THEN** the collector adds nothing to the result

#### Scenario: A page the server refuses

- **WHEN** a scenario's page requests a resource the server answers with 404
- **THEN** the scenario fails, and the failure names the resource

### Requirement: The documentation names the header and what the application supplies

The README SHALL state that kdaisyui's markup works under `default-src 'self'`, show the header
and name what the application supplies: the `kdaisyui.css` import, the rule that switches
DaisyUI's noise texture off and why, and an icon of its own.

**Verified** that the noise rule is needed: `daisyui/packages/daisyui/src/base/svg.css` declares
`--fx-noise` as a `data:` URL, and `components/badge.css:9` reads it with no condition on
`--noise`; the Deploy-Radar's refusals stopped with `* { --fx-noise: none; }` in its entry.

#### Scenario: A reader sets up a strict policy

- **WHEN** a developer reads the README's CSS section
- **THEN** it shows the header, the import, the noise rule and the icon, each with its reason
