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

## F4 - The red, measured: 40 of 53 tests log errors under the header (task 1.2)

- **What happened:** 2026-11-08, a throwaway header in the test server and a collector that only
  printed; `:e2e-tests:test --rerun`: 53 tests, 0 failures, and 1072 error-level messages in 40
  of them. By kind:

  | Count | Message | Source |
  |---|---|---|
  | 717 | `data:` image refused | DaisyUI's `--fx-noise` |
  | 284 | `https://picsum.photos/…` refused | the five avatars (2.3-2.7) |
  | 32 | inline style refused | `DashboardShell.kt`'s `<style>` (2.2) |
  | 32 | `data:` image refused | DaisyUI's `.loading` / `.loading-spinner` mask |
  | 6 | `data:` image refused | DaisyUI's `.mask-squircle` mask |
  | 1 | 404 `/favicon.ico` | no icon (2.9) - once only, the browser caches the miss |

  `grep -rnE 'https?://' example-app/src` (w3.org namespaces aside) finds exactly the five picsum
  avatars. The compiled `app.css` holds `url("data:` in `:root` (`--fx-noise`), `.loading`,
  `.loading-spinner` and `.mask-squircle`. htmx raised nothing: no `unsafe-eval` refusal, no
  injected style (A2 holds, delayed and `revealed` triggers included).
- **Cause:** see F5 for the two `data:` masks no task planned for.
- **Cost:** none beyond the measurement.

## F5 - DaisyUI itself needs `data:` images in more components than the noise (A4 false)

- **What happened:** task 1.2. Besides `--fx-noise`, DaisyUI 5.7.50's sources set a `data:` SVG
  as a mask in `loading.css` (14), `mask.css` (14), `chat.css` (the bubble tail), `tooltip.css`
  (the arrow) and `mockup.css` (one window control); `status.css` has two, commented out. Under
  `default-src 'self'`, `img-src` falls back to `'self'` and refuses them. A refused mask image
  makes the element invisible, so `daisyLoading` and every `mask-*` shape disappear, a tooltip
  loses its arrow and a chat bubble its tail. The planned noise rule fixes only the first.
- **Cause:** A4 was to be settled by the grep this task ran; the proposal's guarantee, "every
  element kdaisyui renders SHALL work … once DaisyUI's noise texture is switched off", rested on
  it.
- **Cost:** a question round with Oliver on what the guarantee says, before section 2.

## F6 - Section 2 was planned with markers and tests it could not have

- **What happened:** 2026-11-08, before the first code commit, against the rule
  `planning-rules-from-popover-anchoring` added the same day (plan a marker from counted lines).
  Task 2.1 counts about fifteen lines, so `! F`, not `^ F`. Tasks 2.3-2.7 changed visible
  behaviour with no test naming it, which no marker better than `@` allows and TCR refuses; each
  now adds its route to a scenario built from existing steps. Corrected in one commit.
- **Cause:** the plan of 2026-11-07 predates the rule, and counted "the gate in section 3" as
  the test of section 2's fixes, which it cannot be while section 2 has to stay green without it.
- **Cost:** about five minutes; one bookkeeping commit.

## F7 - Section 3 held a duplicate test and a tag nothing used

- **What happened:** 2026-11-08, before section 3. Task 3.1's test of the collector on a
  self-served page with an inline style was the same proof as task 3.4's `CspProbeSpec`, and the
  `@csp-probe` Cucumber tag of 3.2 had no scenario to carry it, since the probe is a Kotest spec.
  3.4 merged into 3.1, the tag went; the steps' markers became `! F (internal)` by counted lines.
- **Cause:** the review of F2 added the probe and the tag separately, each against one finding.
- **Cost:** three minutes; one bookkeeping commit, and one test and one branch fewer.
