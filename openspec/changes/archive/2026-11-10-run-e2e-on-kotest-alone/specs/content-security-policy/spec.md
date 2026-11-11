## MODIFIED Requirements

### Requirement: A page that logs an error fails its scenario

The E2E suite SHALL fail any test whose page logged a console message of type `error` or raised
a page error, and the failure SHALL quote every such message. Since `run-e2e-on-kotest-alone`
every E2E test is a Kotest spec built on `PlaywrightSpec`, whose teardown is the one place this
is enforced; until then `CucumberHooks` enforced it a second time for the Cucumber scenarios.

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
