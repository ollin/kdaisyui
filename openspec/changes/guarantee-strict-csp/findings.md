# Findings

## F1 - The example application breaks the policy it is about to guarantee

- **What happened:** at proposal, 2026-11-07. Reading `DashboardShell.kt` for the proposal
  found an inline `<style>` element, an avatar image from `https://picsum.photos`, and no icon
  link, so every page load 404s on `/favicon.ico`. The review of the same day found four more
  picsum images, in the fragments the shell loads over htmx (F2). None of the ten proposals
  from the Deploy-Radar named these; they are what a check nobody had would have found.
- **Cause:** the E2E suite sends no policy and listens to no console.
- **Cost:** seven fix tasks (2.2 to 2.7, 2.9) that would not exist for a library whose demo
  already loaded nothing from elsewhere; about 55 minutes of the estimate, 15 of them not in
  the frozen figure.

## F2 - The review found the measurement incomplete and the order reverting

- **What happened:** the review of 2026-11-07 (`fwb-openspec-reviewer`): the proposal named
  one picsum avatar where `grep -rn 'https\?://' example-app/src/main/kotlin` finds five; the
  enforcing teardown came before the example app's fixes, so root TCR would have reverted it on
  the favicon 404; the probe scenario provoked a refusal that the same teardown would then fail;
  task 1.2 needed code from later tasks; the header test had no source set to live in; htmx's
  own style injection was not in A2; and the provenance grep could not see the icons' raw SVG.
- **Cause:** the example app was read for the proposal in one file; the tasks were ordered by
  component, not by what keeps the suite green; the probe's opt-out was not designed.
- **Cost:** about 25 minutes of rewriting before implementation. The fixes are one task per
  avatar file, the grep in 1.2, the fixes before the enforcing teardown, `drain()` and the
  `@csp-probe` tag, the probe as a self-serving Kotest spec through `page.route`, the header
  asserted by an E2E HTTP step, A2 extended (htmx 4.0.1 injects no style element, read in its
  `dist/htmx.js`), and the grep extended to `raw(` (empty for `style=`, handlers and `data:`).

## F3 - What Chromium reports, read for A1

- **What happened:** task 1.1, 2026-11-08, Chromium 156, a page fulfilled through `page.route`
  with `default-src 'self'`. `onConsoleMessage` received, all of type `error`:
  - `Applying inline style violates the following Content Security Policy directive 'default-src 'self''. … The action has been blocked.`
  - `Loading the image 'data:image/gif;base64,…' violates the following Content Security Policy directive: "default-src 'self'". … The action has been blocked.`
  - `Failed to load resource: the server responded with a status of 404 (Not Found)` - with no URL in the text.
  No `onPageError`. A1 holds.
- **Cause:** record only. One consequence for task 3.1: the 404 message names no URL, so
  `failureMessage` should add the message's location (`ConsoleMessage.location()`) to name the
  resource, as the spec's 404 scenario asks.
- **Cost:** none.
