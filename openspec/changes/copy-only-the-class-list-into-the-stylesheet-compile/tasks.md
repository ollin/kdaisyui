# Tasks

**Estimate, frozen 2026-11-10:** `40min (1h 15min DevOnly)` by the ledger's `forecast`; 75 minutes
developer-only is the frozen raw figure. Ledger run
`2026-11-10-kdaisyui-copy-only-the-class-list-into-the-stylesheet-compile`.

## 1. Open assumptions

A1 and A2 are checked by the step that needs them (2.1, 3.1); A3 and A4 were settled at proposal.

## 2. Under TCR at the root

- [x] 2.1 `! F` `KdaisyuiStylesheet.classList` is a `RegularFileProperty` copied to
  `/deps/project/kdaisyui/kdaisyui-classes.txt`; `KdaisyuiPlugin` sets it to the class-list file;
  `KdaisyuiStylesheetTest`, `KdaisyuiPluginTest` assert the file; `NodeContainerRunTest` copies a
  class list naming `badge`, which no source names, and asserts `.badge` in the output (A1).

## 3. Checks and docs

- [ ] 3.1 A2: the example app's `kdaisyuiStylesheet` twice in a row; the second is UP-TO-DATE.
  `. d` records it in the A2 row and the spec's Assumed line.
- [ ] 3.2 `. d` `docs/upgrading.md`, 0.10.x section: the task's `classList` is a file now.

## 4. Gate, adoption and archive

- [ ] 4.1 Full green: `check` with E2E, Kover (root and plugin), `:gradle-plugin:pitest`, CodeScene.
- [ ] 4.2 The evaluation under `tmp/copy-only-the-class-list-into-the-stylesheet-compile/`.
- [ ] 4.3 After adoption: archive on this branch, Proposals table, `. d (internal)`.
