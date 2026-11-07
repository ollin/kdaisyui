# Tasks

**Estimate, frozen 2026-11-07:** `1h 30min (2h 45min DevOnly)` by the ledger's `forecast`
(ratio 0.53 over 153 runs); 165 minutes developer-only is the frozen raw figure. Block 1: 15,
block 2: 60, block 3: 60, block 4: 30. The review of the same day split block 2 into three
steps and added the `llms.txt` task (finding F2); the figure stands, the miss is recorded.

Ordered by uncertainty: A2 is the one thing only a browser can answer, so the page and its
scenarios come before the prose that describes them. Each step carries its own scenario, so
root TCR, which runs the E2E suite, sees test and code together.

## 1. Open assumptions

- [ ] 1.1 `. d` (A5, A6) Record Oliver's decisions in `proposal.md`: no shipped script, and
  whether `daisyModal`'s `open` parameter stays "the class only". The recipe writes the
  attribute either way, since a dialog the class keeps visible cannot be closed by the form; a
  "yes" on the parameter would be its own change.

## 2. The page and its scenarios, under TCR

- [ ] 2.1 `^ F` A fragment route renders `daisyModal` with the `open` attribute, a box, and a
  `form method="dialog"` Close button; an endpoint scenario, in the shape of
  `popover_modal.feature:4-7`, asserts status 200 and that the body contains `class="modal"`
  and `open=""` as separate strings, since `daisyModal` writes `id` first.
- [ ] 2.2 `^ F` A demo page, its resource class, id and route, loading the stylesheet and
  `/webjars/htmx.org/dist/htmx.min.js` as `DashboardShell.kt:17` does, with a button that
  `hx-get`s the fragment into a slot; the scenario clicks it and asserts the dialog is visible.
- [ ] 2.3 `^ F` The close scenario: Close hides the dialog, waiting out DaisyUI's 0.3 s
  visibility transition, and the page's request log holds no request after the click. If the
  dialog does not show or does not close, revise the change before writing the prose (A1, A2).

## 3. Docs

- [ ] 3.1 `. d` `docs/how-to.md`, "Render a modal the server already opened": the fragment
  (`in Route`) and the trigger (`in FlowContent`), the sentence on the `open` attribute versus
  the `modal-open` class, and that such a dialog is not modal: no top layer, no focus trap, the
  page behind stays reachable by keyboard while DaisyUI paints the overlay itself.
- [ ] 3.2 `. d` "Put a form in a dropdown": `daisyDropdown`, `summary { daisyButtonClasses(…) }`,
  `daisyDropdownContent` with a form of `daisyFieldset`, `daisyLabel`, `daisyInput`, `daisyButton`.
- [ ] 3.3 `. d` "Behaviour without a script": the three that need none, with their existing
  recipes linked, and the clipboard listener in a `js` fence.
- [ ] 3.4 `. d` README, "Give those classes some CSS": the paragraph on what the compiled
  stylesheet carries, reviewed against the Deploy-Radar's corrected test.
- [ ] 3.5 `. d` `codegen/llms-template.md:19` names the three new recipes; `just generate`;
  `llms.txt` regenerated and the working tree clean afterwards.

## 4. Gate, adoption and archive

- [ ] 4.1 Full green: `check` with E2E and the E2E result files asserted on disk,
  `koverVerify`, `:doc-snippets:compileSnippets`, `analyze_change_set`. The evaluation says
  that no mutation tool covers `e2e-tests` or `example-app`.
- [ ] 4.2 `openspec validate document-server-rendered-patterns --strict`.
- [ ] 4.3 The evaluation under `tmp/document-server-rendered-patterns/` for Oliver to adopt.
- [ ] 4.4 After adoption: archive on this branch, `. d (internal)`, `tmp/` of the change deleted.
