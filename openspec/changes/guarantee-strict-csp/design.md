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
- A policy stricter than `default-src 'self'; img-src 'self' data:`, such as nonces on scripts, or `img-src 'self'` without
  `data:` (decision 8). htmx is served from
  the own origin and needs none.

## Decisions

### 1. One collector, installed where each runner opens its page

**Chosen:** a small class in `e2e-tests`, `ConsoleErrors`, that subscribes to
`page.onConsoleMessage` and `page.onPageError` and holds the error-level texts; `drain()`
returns and clears them, and a pure `failureMessage(texts)` names every one. Both runners
install it after `newPage()` and, in their teardown, fail when it holds anything. The check
runs after the screenshot and the trace, and `context.close()` sits in a `finally`, so a failing
check skips no cleanup. A test that provokes a refusal on purpose drains the collector before the
teardown reads it. The teardown runs after the scenario's own
steps, so a scenario that opens two pages reports both.

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

**Chosen:** `install(DefaultHeaders) { header("Content-Security-Policy", "default-src 'self'; img-src 'self' data:") }`
in `configureRouting`, the function the E2E server also calls, so the suite and `just dev` send
the same header. `ktor-server-default-headers` joins the app bundle in `libs.versions.toml`.

`DefaultHeaders` applies to every response, static resources and webjars included, which are
same-origin and fine; the header test reads `/` and `/static/app.css` so that the "every
response" scenario is observed on a static route too.

**Rejected: the header only in the E2E server.** Then `just dev` would show a page the suite
never sees, and a developer looking at the browser would see no violation the suite would fail.

**Rejected: a helper in `ktor-integration`.** See the proposal's out-of-scope list.

### 4. The example app's fixes, each its own commit

- The indicator rules move into `app.css` verbatim; the `<style>` element goes. Tailwind's
  `@source` does not see them, but they are plain CSS in the entry, which Tailwind passes through.
- Each of the five avatars becomes `daisyAvatar(placeholder = true)` with initials, which is
  what the DaisyUI dashboard template shows without an image, one file per commit. No
  own-origin image to ship.
- A `favicon.svg` under `example-app/src/main/resources/static/`, linked from every page's head
  through the existing `daisyuiStylesheet()` neighbour (renamed `pageAssets()` in task 2.9); a second head helper is not worth a
  file. `/favicon.ico` itself is not served: a page that links an icon is not asked for it.

### 5. The check must be seen to fail

Twice. Before anything is committed, a throwaway header and collector on a scratch branch run
the suite once: the measurement in task 1.2 lists the failing pages and their messages, and
that list is what the fix tasks work through. And permanently: `ConsoleErrorsSpec` fulfils a request
for `/csp-probe` through `page.route` with the header and an inline style, so no route of the
example app violates the policy on purpose, and asserts that the collector held the refusal
and that `failureMessage` names it. A gate nobody has seen red is the defect this repository
has paid for five times (`AGENTS.md`, "RED is two steps").

### 8. `img-src 'self' data:`, not `default-src 'self'` alone

**Chosen** (Oliver, 2026-11-08, after F5): DaisyUI 5.7.50 sets `data:` SVGs as `mask-image` in
`loading`, `mask-*`, `chat`, `tooltip` and `mockup`, and the noise as a background. A refused
mask makes the element invisible. A `data:` image is fetched from nowhere and cannot run script;
allowing it keeps scripts and styles at `'self'`.

**Rejected: switching the noise off with `* { --fx-noise: none; }`** (the first plan, from the
Deploy-Radar). It fixes the noise only; `daisyLoading` and every mask would still vanish.
**Rejected: kdaisyui ships the masks as own-origin SVG files** and overrides DaisyUI's rules.
The library would have to serve files, which it does not (`css-delivery`, `ktor-integration`),
and every DaisyUI bump that changes a mask would need a copy. **Rejected: excluding the five
components from the guarantee.** They would stay broken for every user of a strict policy.

### 6. Order, so every step is green under root TCR

Root TCR runs the E2E suite. The example app's fixes come first, while nothing enforces; the
collector and the enforcing teardowns come last, when every page is clean. The reverse order
would revert each step on the favicon 404 alone.

### 7. Enablers and the desired change

Enablers: the collector class with its pure `failureMessage`, and the probe spec. Desired
changes: the header, each example-app fix, the failing teardowns, the README section.

## Risks / Trade-offs

- [Chromium changes the wording or type of a CSP message] → the check reads the type, not the
  text; task 1.1 records the current text in `findings.md` for a reader.
- [A future demo page loads a third-party asset for a good reason] → it cannot, by this
  guarantee. The README says so; the scenario fails naming the URL.
- [The popover dropdown page fails until `csp-safe-popover-anchoring` lands] → landed in #477;
  kdaisyui writes no inline style, so task 1.2 can run now.
