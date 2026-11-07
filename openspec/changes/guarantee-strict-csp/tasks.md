# Tasks

**Estimate, frozen 2026-11-07:** 200 minutes developer-only, `1h 50min (3h 20min DevOnly)` by
the ledger's `forecast` (ratio 0.53 over 153 runs). Block 1: 45, block 2: 45, block 3: 50,
block 4: 30, block 5: 30.

**Sequencing:** apply after `csp-safe-popover-anchoring` is merged; task 1.2 says why.

Ordered by uncertainty: A1 decides whether the check can be built at all, and the measurement of
1.2 is the list the fix tasks of block 3 work through.

## 1. Open assumptions

- [ ] 1.1 `. d` (A1) A scratch page with one inline `style` and one `data:` image, served with
  the header by a throwaway route; open it in the suite's browser with `onConsoleMessage` and
  record the messages' type and text in `findings.md`. Remove the route.
- [ ] 1.2 `. d` (A2, A4) With the header installed on a scratch branch and the collector of 2.1
  in place, run the E2E suite once and record every failing scenario and message; grep the
  compiled `app.css` for `url(`. This is the red the gate must be seen in. Requires
  `csp-safe-popover-anchoring` merged, or the popover pages fail for the known reason.
- [ ] 1.3 `. d` (A5) Read `css-delivery`, `daisyui-component-coverage` and
  `documentation-snippets` against the header and the collector; record the result.

## 2. The check, under TCR

- [ ] 2.1 `^ F (internal)` `ConsoleErrors` subscribes to a page's console and page errors and
  holds the error-level texts; its test opens a scratch page in the shared browser.
- [ ] 2.2 `^ F (internal)` `CucumberHooks.tearDown` fails the scenario naming every held message.
- [ ] 2.3 `^ F (internal)` `PlaywrightSpec.afterTest` does the same.
- [ ] 2.4 `^ F (internal)` A Cucumber scenario tagged `@csp-probe` opens the scratch page of 1.1
  and asserts, through the collector's API, that a refusal was held; this is the proof the check
  can go red, kept.

## 3. The example application, under TCR

- [ ] 3.1 `^ F` `ktor-server-default-headers` in the app bundle; `configureRouting` installs
  `DefaultHeaders` with the policy; a Ktor test-host test reads the header.
- [ ] 3.2 `^ r` The htmx indicator rules move from the inline `<style>` into `app.css`; the
  element goes.
- [ ] 3.3 `^ F` The picsum avatar becomes `daisyAvatar(placeholder = true)` with initials.
- [ ] 3.4 `^ F` `* { --fx-noise: none; }` in `app.css`, unlayered, with its reason.
- [ ] 3.5 `^ F` `favicon.svg` under `static/`, linked beside the stylesheet in every page head.
- [ ] 3.6 `. d` The suite is green under the header; record the before/after count of failing
  scenarios from 1.2 in `findings.md`.

## 4. Docs

- [ ] 4.1 `. d` README, "Under a strict Content-Security-Policy": the header, the `kdaisyui.css`
  import, the noise rule, the icon; fences skipped with their reason where they are not Kotlin
  against the library.

## 5. Gate and adoption

- [ ] 5.1 Full green: `check` with E2E, `koverVerify`, `:lib:pitest`, `analyze_change_set`.
- [ ] 5.2 `openspec validate guarantee-strict-csp --strict`.
- [ ] 5.3 The evaluation under `tmp/guarantee-strict-csp/` for Oliver to adopt.
