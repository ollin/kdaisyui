# Tasks

**Estimate, frozen 2026-11-07:** `1h 50min (3h 20min DevOnly)` by the ledger's `forecast`
(ratio 0.53 over 153 runs); 200 minutes developer-only is the frozen raw figure. Block 1: 45,
block 2: 70, block 3: 45, block 4: 20, block 5: 20. The review of the same day widened block 2
from one avatar to five (finding F2); the figure stands, the miss is recorded.

**Sequencing:** apply after `csp-safe-popover-anchoring` is merged; task 1.2 says why.

Ordered so that every step stays green under root TCR, which runs the E2E suite: the
assumptions are settled on a scratch branch with nothing committed; the example app's fixes
land while nothing enforces yet; the collector and the enforcing teardown come last, when every
page is clean, and the probe spec proves the check can go red.

## 1. Open assumptions, on a scratch branch, nothing committed

- [x] 1.1 `. d` (A1) A scratch page with one inline `style`, one `data:` image and one request
  the server answers 404, served by a throwaway route under the header; a throwaway Kotest spec
  prints every console message's type and text. Record them in `findings.md`; discard the scratch.
- [x] 1.2 `. d` (A2, A4) With a throwaway header and collector, run the E2E suite once and
  record every failing scenario and message; grep `example-app/src` for `https?://` and the
  compiled `app.css` for `url(`; record both lists. This is the red the gate must be seen in.
  Requires `csp-safe-popover-anchoring` merged, or the popover pages fail for the known reason.
- [x] 1.3 `. d` (A5) Read `css-delivery`, `daisyui-component-coverage` and
  `documentation-snippets` against the header and the collector; record the result.
- [x] 1.4 `. d` Revise this change after `csp-safe-popover-anchoring` (its finding F7): that
  change ships no `kdaisyui.css`, writes no anchor and no inline style, and relies on the
  implicit anchor of `popovertarget`. Drop the import from proposal (What Changes), task 4.1 and
  the `content-security-policy` spec (lines 13 and 90), and rewrite the spec's source-scan note
  (lines 17-18): `lib/generated/main` writes no `style` at all now.

## 2. The example application, under TCR, nothing enforcing yet

2.8, the noise rule, was dropped after F5: `img-src 'self' data:` lets DaisyUI's noise load.

- [x] 2.1 `! F` `ktor-server-default-headers` in the app bundle; `configureRouting` installs
  `DefaultHeaders` with the policy `default-src 'self'; img-src 'self' data:`; a new
  `content_security_policy.feature` reads the header on `/` and on `/static/app.css` through a
  new step, so static responses are covered too. About 15 counted lines.
- [x] 2.2 `^ r` The htmx indicator rules move from the inline `<style>` into `app.css`; the
  element goes. Its test: the progressive-loading scenarios, unchanged.
- [x] 2.3 `^ F` `DashboardShell.kt:94`: the picsum avatar becomes `daisyAvatar(placeholder = true)`
  with initials; `content_security_policy.feature` gains "The page loads no image from another
  host" for `/`, from the existing steps (`returns status 200`, `does not contain "src=\"http"`).
- [x] 2.4 `^ F` `team/TeamFragment.kt:82`, the same, and the scenario's line for `/fragments/team`.
- [x] 2.5 `^ F` `repo/RepoFragment.kt:51`, the same, for `/fragments/forms`.
- [x] 2.6 `^ F` `dashboard/FormSectionsFragment.kt:43`, the same, for `/fragments/form-sections`.
- [ ] 2.7 `^ F` `dashboard/CardsRow2Fragment.kt:69`, the same, for `/fragments/cards-row2`.
- [ ] 2.9 `^ F` `favicon.svg` under `static/`, linked beside the stylesheet in every page head;
  the feature asserts `/static/favicon.svg` answers 200 and `/` links it.

## 3. The check, under TCR

- [ ] 3.1 `^ F (internal)` `ConsoleErrors`: subscribes to a page's error-level console messages
  and page errors; `drain()` returns and clears what it holds; `failureMessage(texts)` is a pure
  function naming every message. Unit tests on the pure parts; one test opens a page the test
  itself fulfils through `page.route` with the header and an inline style, in the shared browser.
- [ ] 3.2 `^ F (internal)` `CucumberHooks.tearDown` fails the scenario with `failureMessage`
  after the screenshot and the trace, with `context.close()` in a `finally`; a scenario tagged
  `@csp-probe` drains instead of failing.
- [ ] 3.3 `^ F (internal)` `PlaywrightSpec.afterTest` does the same (A8: record in
  `findings.md` that an exception thrown there fails the test, seen once on purpose).
- [ ] 3.4 `^ F (internal)` `CspProbeSpec`, kept: fulfils `/csp-probe` through `page.route` with
  the header and an inline style, asserts the collector held a refusal, and asserts that
  `failureMessage` of that list names it. This is the proof the check can go red.
- [ ] 3.5 `. d` Record the before/after count of failing scenarios from 1.2 in `findings.md`.

## 4. Docs

- [ ] 4.1 `. d` README, "Under a strict Content-Security-Policy": the header and why `img-src`
  allows `data:`, the icon; fences skipped with their reason where they are not Kotlin
  against the library.

## 5. Gate, adoption and archive

- [ ] 5.1 Full green: `check` with E2E and the E2E result files asserted on disk under
  `e2e-tests/build/`, `koverVerify`, `:lib:pitest`, `analyze_change_set`. The evaluation says
  that no mutation tool covers `e2e-tests` or `example-app`.
- [ ] 5.2 `openspec validate guarantee-strict-csp --strict`.
- [ ] 5.3 The evaluation under `tmp/guarantee-strict-csp/` for Oliver to adopt.
- [ ] 5.4 After adoption: archive on this branch, `. d (internal)`, `tmp/` of the change deleted.
