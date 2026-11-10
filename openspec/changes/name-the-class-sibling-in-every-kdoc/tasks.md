# Tasks

**Estimate, frozen 2026-11-07:** `1h 5min (2h DevOnly)` by the ledger's `forecast` (ratio 0.53
over 153 runs); 120 minutes developer-only is the frozen raw figure. Block 1: 15, block 2: 45,
block 3: 30, block 4: 30.

No assumption is open: the review of 2026-11-07 settled A2 against `component-shape.ts:367`
(finding F2), so the sentence is gated on the existing `hasSibling` and no enabler is needed;
A5, the wording, Oliver decided the same day as recommended.

## 1. Open assumptions

None. Block 1 is kept so the numbering matches the other changes.

## 2. Codegen, under TCR at the repository root

- [x] 2.1 `! F (internal)` `renderKdoc` in `codegen/src/generator-new.ts` adds the sentence
  as the comment's second line for every function for which `hasSibling(shape)` holds, and not
  otherwise. `codegen/test/generator-new.test.ts` asserts the exact string for a function with
  an own class, for one with only modifier classes or class-writing parameters, and its absence
  for a structural wrapper. In the same run, `just generate`: the snapshot test compares the
  emitter with the committed `lib/generated`, so the emitter cannot go green without the
  regeneration (finding F3). Read the diff: in the 66 component files, 129 added sentences
  (`grep -c 'The same classes on another element'`), a one-line comment becoming a block where a
  part had one, and nothing else. `!`, because the regenerated comments are far over eight lines.
- [x] 2.2 Merged into 2.1 (finding F3).

## 3. Docs

- [x] 3.1 `. d` `docs/how-to.md`, "Put a component's classes on another element": a badge that
  is a link and a button that is a link, each fence marked `<!-- snippet: in FlowContent -->`;
  the button as a `<summary>` links to "Add a dropdown", which shows it already.

## 4. Gate, adoption and archive

- [x] 4.1 Full green: `check` with E2E and the E2E result files asserted on disk,
  `koverVerify`, `:lib:testCodegen`, StrykerJS on the changed `codegen/src` lines,
  `:doc-snippets:compileSnippets`, `analyze_change_set`.
- [x] 4.2 `openspec validate name-the-class-sibling-in-every-kdoc --strict`.
- [x] 4.3 The evaluation under `tmp/name-the-class-sibling-in-every-kdoc/` for Oliver to adopt.
- [ ] 4.4 After adoption: archive on this branch, `. d (internal)`, `tmp/` of the change deleted.
