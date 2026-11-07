## Context

See proposal.md, Why. The E2E suite has two runners over one browser: Cucumber scenarios get
their page from `CucumberHooks.setUp`, Kotest specs from `PlaywrightSpec.beforeTest`. Both
create a context, start tracing, and open one page; neither listens to the console. The example
app installs `Webjars` and `Resources` and serves `/static` from the classpath.

## Goals / Non-Goals

**Goals:**

- A page that violates the policy, loads from another host or logs an error fails its scenario,
  with the message in the failure.
- The example app passes on every page, so the suite is the proof the README cites.

**Non-Goals:**

- Firefox or WebKit. The suite runs Chromium; the README says so.
- A policy stricter than `default-src 'self'`, such as nonces on scripts. htmx is served from
  the own origin and needs none.

## Decisions

### 1. One collector, installed where each runner opens its page

**Chosen:** a small class in `e2e-tests`, `ConsoleErrors`, that subscribes to
`page.onConsoleMessage` and `page.onPageError` and holds the error-level texts. Both runners
install it after `newPage()` and, in their teardown, fail when it holds anything, naming every
message. The teardown runs after the scenario's own steps, so a scenario that opens two pages
reports both.

**Rejected: a Cucumber step `Then the browser reports no violation`**, the Deploy-Radar's shape.
It would have to be written into every scenario and would never reach the Kotest specs; a
forgotten step is a page nobody checks.

**Rejected: a Playwright `route` that blocks other hosts.** Blocking the request hides what the
page asked for; the console message names the URL and the directive, which is what a reader
needs.

### 2. Every error-level message fails, not only CSP refusals

**Chosen:** any message of type `error`, any page error. A 404 for a favicon, a blocked image
and a refused inline style are the same class of defect for a library whose claim is "nothing
from another host, nothing inline". The example app's pages log no error today on purpose, so
the rule costs nothing to keep. A6 puts this to Oliver.

**Rejected: a filter on "Refused to".** It would let the favicon 404 through, and the next
non-CSP defect with it.

### 3. The header is the example app's, set with Ktor's `DefaultHeaders`

**Chosen:** `install(DefaultHeaders) { header("Content-Security-Policy", "default-src 'self'") }`
in `configureRouting`, the function the E2E server also calls, so the suite and `just dev` send
the same header. `ktor-server-default-headers` joins the app bundle in `libs.versions.toml`.

**Rejected: the header only in the E2E server.** Then `just dev` would show a page the suite
never sees, and a developer looking at the browser would see no violation the suite would fail.

**Rejected: a helper in `ktor-integration`.** See the proposal's out-of-scope list.

### 4. The example app's fixes, each its own commit

- The indicator rules move into `app.css` verbatim; the `<style>` element goes. Tailwind's
  `@source` does not see them, but they are plain CSS in the entry, which Tailwind passes through.
- The avatar becomes `daisyAvatar(placeholder = true)` with initials, which is what the DaisyUI
  dashboard template shows without an image. No own-origin image to ship.
- `* { --fx-noise: none; }` sits after the `@plugin` line, unlayered, with the comment the
  Deploy-Radar wrote: DaisyUI's rule lives in `@layer daisyui.l1.l2.l3`, and an unlayered
  declaration wins over any layered one.
- A `favicon.svg` under `example-app/src/main/resources/static/`, linked from every page's head
  through the existing `daisyuiStylesheet()` neighbour; a second head helper is not worth a
  file. `/favicon.ico` itself is not served: a page that links an icon is not asked for it.

### 5. The check must be seen to fail

Before the example app's fixes land, the collector is installed and the suite run once with the
header: the measurement in task 1.2 lists the failing pages and their messages, and that list
is what the fix tasks work through. A gate nobody has seen red is the defect this repository has
paid for five times (`AGENTS.md`, "RED is two steps").

### 6. Enablers and the desired change

Enablers: the collector class and its unit test (a fake page is not available; the test opens a
scratch page in the shared browser, as task 1.1 does). Desired changes: the header, the failing
teardown, each example-app fix, the README section.

## Risks / Trade-offs

- [Chromium changes the wording or type of a CSP message] → the check reads the type, not the
  text; task 1.1 records the current text in `findings.md` for a reader.
- [A future demo page loads a third-party asset for a good reason] → it cannot, by this
  guarantee. The README says so; the scenario fails naming the URL.
- [The noise rule changes a theme's look] → with `--noise: 0` nothing is visible; A7 puts the
  themes with noise to Oliver.
- [The popover dropdown page fails until `csp-safe-popover-anchoring` lands] → sequencing; this
  change's task 1.2 is run only after that change is merged, and says so.
