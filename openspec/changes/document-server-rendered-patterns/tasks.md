# Tasks

**Estimate, frozen 2026-11-07:** 165 minutes developer-only, `1h 30min (2h 45min DevOnly)` by
the ledger's `forecast` (ratio 0.53 over 153 runs). Block 1: 15, block 2: 60, block 3: 60,
block 4: 30.

Ordered by uncertainty: A2 is the one thing only a browser can answer, so the page and its
scenario come before the prose that describes them.

## 1. Open assumptions

- [ ] 1.1 `. d` (A4) Read `daisyui-component-coverage`'s modal requirements,
  `documentation-snippets` and `css-delivery` against a page, a scenario and four fences;
  record the result.

## 2. The page and its scenario, under TCR

- [ ] 2.1 `^ F` A fragment route renders `daisyModal` with the `open` attribute, a box, and a
  `form method="dialog"` Close button; a demo page with a button that `hx-get`s it into a slot.
- [ ] 2.2 `^ F` E2E: the click shows the dialog; Close hides it; the markup assertion reads
  `<dialog class="modal" open`. If the dialog does not show or does not close, revise the change
  before writing the prose (A1, A2).

## 3. Docs

- [ ] 3.1 `. d` `docs/how-to.md`, "Render a modal the server already opened": the fragment and
  the trigger, and the sentence on `open` attribute versus `modal-open` class.
- [ ] 3.2 `. d` "Put a form in a dropdown": `daisyDropdown`, `summary { daisyButtonClasses(…) }`,
  `daisyDropdownContent` with a form of `daisyFieldset`, `daisyLabel`, `daisyInput`, `daisyButton`.
- [ ] 3.3 `. d` "Behaviour without a script": the three that need none, with their existing
  recipes linked, and the clipboard listener in a `js` fence.
- [ ] 3.4 `. d` README, "Give those classes some CSS": the paragraph on what the compiled
  stylesheet carries.

## 4. Gate and adoption

- [ ] 4.1 Full green: `check` with E2E, `koverVerify`, `:doc-snippets:compileSnippets`,
  `analyze_change_set`.
- [ ] 4.2 `openspec validate document-server-rendered-patterns --strict`.
- [ ] 4.3 The evaluation under `tmp/document-server-rendered-patterns/` for Oliver to adopt.
