# Tasks

**Estimate, frozen 2026-11-07:** 210 minutes developer-only, `1h 55min (3h 30min DevOnly)` by
the ledger's `forecast` (ratio 0.53 over 153 runs). Block 1: 25, block 2: 20, block 3: 40,
block 4: 65, block 5: 30, block 6: 30.

Ordered by uncertainty: A2 and A3 are the two assumptions the code has not answered yet, and
A3 decides whether the resource route (A6) can work at all, so both are settled before any
attribute moves.

## 1. Open assumptions

- [ ] 1.1 `. d` (A2) Read the Chromium version the E2E suite runs locally (`which chromium`) and
  in CI (`google-chrome --version` on `ubuntu-latest`, and the Playwright build
  `playwrightInstall` fetches); record both in `findings.md`. Floor: 133.
- [ ] 1.2 `. d` (A3, A6) Put the two rules into `example-app/src/main/css/app.css` behind a
  relative `@import` of a scratch file, compile, and read the output for `attr(`; record the
  result. Revert the scratch edit.
- [ ] 1.3 `. d` (A4) Read the four main specs named in A4 against a data attribute in place of
  the inline style; record the result.

## 2. Core, under TCR

- [ ] 2.1 `^ F` `popoverTarget(…, anchored = true)` writes `data-anchor-name` with
  `popover.anchorName`; the test asserts the attribute.
- [ ] 2.2 `^ F` It writes no `style`, and a caller's `style` survives; the style-merging branch
  and its test go.

## 3. Codegen, under TCR at the repository root

- [ ] 3.1 `^ F (internal)` An anchored popover writes `data-position-anchor` instead of
  `style="position-anchor:…"`; the emitter test and the snapshot follow.
- [ ] 3.2 `. d` `just generate`; read the diff: `Dropdown.kt`, its coverage test, the reference
  page and `llms-full.txt` change, nothing else. Commit the regeneration.

## 4. The rules, shipped and imported

- [ ] 4.1 `^ F` `lib/src/main/resources/kdaisyui.css` with the two rules; a test asserts the
  built jar carries it (the shape of `TailwindClassListTest`).
- [ ] 4.2 `^ r (internal)` In the plugin, the lookup of a resource on the runtime classpath takes
  the resource name as a parameter (today it knows only the component map).
- [ ] 4.3 `^ F (internal)` `kdaisyuiClasses` writes `build/kdaisyui/kdaisyui.css` from the
  artifact's resource; a test asserts the bytes.
- [ ] 4.4 `^ F` `example-app/src/main/css/app.css` imports the plugin's `kdaisyui.css` from its
  mount point; `dropdown_popover.feature` asserts the data attributes and no `style=` in the
  markup, and the "sits below" scenario passes. If it does not, revise the change before going on.

## 5. Docs

- [ ] 5.1 `. d` README's CSS section: the import beside the class list, and the contract
  sentence's exception. `docs/how-to.md`, "Add a dropdown": Chromium 133 as the floor for the
  popover form.
- [ ] 5.2 `. d` `docs/upgrading.md`, "How to migrate from 0.9.x": the import, and the symptom
  without it.

## 6. Gate and adoption

- [ ] 6.1 Full green: `check` with E2E, `koverVerify`, `:lib:pitest`, `:gradle-plugin:pitest`,
  StrykerJS on the changed `codegen/src` lines, `analyze_change_set`.
- [ ] 6.2 `openspec validate csp-safe-popover-anchoring --strict`.
- [ ] 6.3 The evaluation under `tmp/csp-safe-popover-anchoring/` for Oliver to adopt.
