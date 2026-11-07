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

- [x] 1.1 `. d` (A2) Read the Chromium version the E2E suite runs locally (`which chromium`) and
  in CI (`google-chrome --version` on `ubuntu-latest`, and the Playwright build
  `playwrightInstall` fetches); record both in `findings.md`. Floor: 133. If CI's system browser
  is below it, the E2E build prefers Playwright's bundled Chromium in CI, which it installs
  already; that becomes a task here.
- [x] 1.2 `. d` (A3) Put the two rules into `example-app/src/main/css/app.css` behind a
  relative `@import` of a scratch file, compile, and read the output for `attr(`; record the
  result. Revert the scratch edit.
- [x] 1.3 `. d` (A4) Read the four main specs named in A4 against a data attribute in place of
  the inline style; record the result.

**Revised 2026-11-07 after F6:** the control after task 3.2 showed the menu anchored with no
rules, and typed `attr()` unshipped in Safari. Oliver chose to subtract: blocks 2 and 3 stay as
the record of what was built, block 4 removes it again and drops the anchor altogether. Each step
of block 4 stays green under root TCR: the writes go first, the stylesheet last, consumer before
producer.

## 2. The rules, shipped and imported, before anything depends on them (superseded by block 4)

- [x] 2.1 `! F` `lib/src/main/resources/kdaisyui.css` with the two rules; a test asserts the
  built jar carries it (the shape of `TailwindClassListTest`).
- [x] 2.2 `^ r (internal)` In the plugin, the lookup of a resource on the runtime classpath takes
  the resource name as a parameter (today it knows only the component map).
- [x] 2.3 `! F (internal)` `kdaisyuiClasses` writes `build/kdaisyui/kdaisyui.css` from the
  artifact's resource; a test asserts the bytes.
- [x] 2.4 `^ F` `example-app/src/main/css/app.css` imports the plugin's `kdaisyui.css` from its
  mount point; the compiled stylesheet carries both rules, which nothing uses yet. E2E unchanged.

## 3. The attribute pair, one side per step, under TCR at the repository root (superseded by block 4)

- [x] 3.1 `! F (internal)` The popover side: `generator-new.ts` writes `data-position-anchor`
  in place of the inline style, `test-generator.ts:113` expects that attribute in the custom-part
  test, `codegen/test/generator-new.test.ts:185` and the snapshot follow, `just generate`, and
  `dropdown_popover.feature:8` asserts `data-position-anchor="--…"`. Risky by size: the
  regeneration is part of the diff. The trigger's inline `anchor-name` still resolves, so "sits
  below" stays green.
- [x] 3.2 `! F` The trigger side: `popoverTarget(…, anchored = true)` writes `data-anchor-name`
  in place of the `style`, and the merging of a caller's `style` goes with it; `PopoverTest`
  asserts the attribute, no `style`, and that a caller's `style` survives;
  `dropdown_popover.feature:7` asserts `data-anchor-name="--…"` and no `style=` in the body.
  If "sits below" fails here, revise the change before going on.

## 4. Rely on the implicit anchor of `popovertarget`

- [x] 4.1 `! F` The popover side writes no anchor: `generator-new.ts` drops the
  `data-position-anchor` line and its `anchorName` import, `test-generator.ts` expects `popover`
  only, `generator-new.test.ts` asserts no anchor and no import, `just generate`, and
  `dropdown_popover.feature` asserts the body does not contain `data-position-anchor`.
- [x] 4.2 `! F` The trigger side: `popoverTarget(popover, action)` writes no anchor; the
  three-parameter form delegates to it under `@Deprecated(ReplaceWith)`; `PopoverTest` covers both
  forms; the example app and the how-to snippet drop `anchored = true`; the feature asserts no
  `data-anchor-name`; `lib/api/lib.api` re-dumped with the added overload.
- [x] 4.3 `^ F` `HtmlId.anchorName` deprecated; `HtmlIdTest` keeps its two cases under
  `@Suppress("DEPRECATION")`.
- [x] 4.4 `^ F` The example app stops importing `kdaisyui.css`: the `@import`, the compile
  task's input and the stylesheet scenario go.
- [x] 4.5 `^ F (internal)` `kdaisyuiClasses` stops writing `kdaisyui.css`: the `stylesheet`
  output, its wiring, `STYLESHEET` and their tests go.
- [x] 4.6 `^ F` `lib/src/main/resources/kdaisyui.css` and `KdaisyuiCssTest` go.

## 5. Docs

- [x] 5.1 `. d` `docs/how-to.md`, "Add a dropdown": the popover form is anchored to the button
  that opens it by the browser (Chrome 133, Safari 26, Firefox 147), and a popover opened from
  script needs `source`.
- [x] 5.2 `. d` `docs/upgrading.md`, "How to migrate from 0.9.x": `anchored` is deprecated and
  does nothing; drop it; no inline style is written any more.

## 6. Gate, adoption and archive

- [x] 6.1 Full green: `check` with E2E and the E2E result files asserted on disk under
  `e2e-tests/build/`, `koverVerify`, `:lib:pitest`, `:doc-snippets:pitest`,
  `:gradle-plugin:pitest`, StrykerJS on the changed `codegen/src` lines, `analyze_change_set`.
- [x] 6.2 `openspec validate csp-safe-popover-anchoring --strict`.
- [ ] 6.3 The evaluation under `tmp/csp-safe-popover-anchoring/` for Oliver to adopt.
- [ ] 6.4 After adoption: archive on this branch, `. d (internal)`, `tmp/` of the change deleted.
