# Tasks

**Estimate, frozen 2026-11-07:** 210 minutes developer-only, `1h 55min (3h 30min DevOnly)` by
the ledger's `forecast` (ratio 0.53 over 153 runs). Block 1: 25, block 2: 65, block 3: 60,
block 4: 30, block 5: 30.

Ordered by uncertainty, then by what keeps every step green under root TCR, which runs the E2E
suite: A2 and A3 are settled first; the rules reach the example app's stylesheet before any
attribute moves, so the "sits below" scenario holds throughout; and each attribute move changes
the feature's markup assertion in the same step, since the feature asserts the old inline style
today (`dropdown_popover.feature:7-8`).

## 1. Open assumptions

- [ ] 1.1 `. d` (A2) Read the Chromium version the E2E suite runs locally (`which chromium`) and
  in CI (`google-chrome --version` on `ubuntu-latest`, and the Playwright build
  `playwrightInstall` fetches); record both in `findings.md`. Floor: 133. If CI's system browser
  is below it, the E2E build prefers Playwright's bundled Chromium in CI, which it installs
  already; that becomes a task here.
- [ ] 1.2 `. d` (A3, A6) Put the two rules into `example-app/src/main/css/app.css` behind a
  relative `@import` of a scratch file, compile, and read the output for `attr(`; record the
  result. Revert the scratch edit.
- [ ] 1.3 `. d` (A4) Read the four main specs named in A4 against a data attribute in place of
  the inline style; record the result.

## 2. The rules, shipped and imported, before anything depends on them

- [ ] 2.1 `^ F` `lib/src/main/resources/kdaisyui.css` with the two rules; a test asserts the
  built jar carries it (the shape of `TailwindClassListTest`).
- [ ] 2.2 `^ r (internal)` In the plugin, the lookup of a resource on the runtime classpath takes
  the resource name as a parameter (today it knows only the component map).
- [ ] 2.3 `^ F (internal)` `kdaisyuiClasses` writes `build/kdaisyui/kdaisyui.css` from the
  artifact's resource; a test asserts the bytes.
- [ ] 2.4 `^ F` `example-app/src/main/css/app.css` imports the plugin's `kdaisyui.css` from its
  mount point; the compiled stylesheet carries both rules, which nothing uses yet. E2E unchanged.

## 3. The attribute pair, one side per step, under TCR at the repository root

- [ ] 3.1 `! F (internal)` The popover side: `generator-new.ts` writes `data-position-anchor`
  in place of the inline style, `test-generator.ts:113` expects that attribute in the custom-part
  test, `codegen/test/generator-new.test.ts:185` and the snapshot follow, `just generate`, and
  `dropdown_popover.feature:8` asserts `data-position-anchor="--…"`. Risky by size: the
  regeneration is part of the diff. The trigger's inline `anchor-name` still resolves, so "sits
  below" stays green.
- [ ] 3.2 `^ F` The trigger side: `popoverTarget(…, anchored = true)` writes `data-anchor-name`
  in place of the `style`, and the merging of a caller's `style` goes with it; `PopoverTest`
  asserts the attribute, no `style`, and that a caller's `style` survives;
  `dropdown_popover.feature:7` asserts `data-anchor-name="--…"` and no `style=` in the body.
  If "sits below" fails here, revise the change before going on.

## 4. Docs

- [ ] 4.1 `. d` README's CSS section: the import beside the class list, and the contract
  sentence's exception. `docs/how-to.md`, "Add a dropdown": Chromium 133 as the floor for the
  popover form.
- [ ] 4.2 `. d` `docs/upgrading.md`, "How to migrate from 0.9.x": the import, and the symptom
  without it.

## 5. Gate, adoption and archive

- [ ] 5.1 Full green: `check` with E2E and the E2E result files asserted on disk under
  `e2e-tests/build/`, `koverVerify`, `:lib:pitest`, `:doc-snippets:pitest`,
  `:gradle-plugin:pitest`, StrykerJS on the changed `codegen/src` lines, `analyze_change_set`.
- [ ] 5.2 `openspec validate csp-safe-popover-anchoring --strict`.
- [ ] 5.3 The evaluation under `tmp/csp-safe-popover-anchoring/` for Oliver to adopt.
- [ ] 5.4 After adoption: archive on this branch, `. d (internal)`, `tmp/` of the change deleted.
