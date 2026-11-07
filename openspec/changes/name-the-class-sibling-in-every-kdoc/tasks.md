# Tasks

**Estimate, frozen 2026-11-07:** 120 minutes developer-only, `1h 5min (2h DevOnly)` by the
ledger's `forecast` (ratio 0.53 over 153 runs). Block 1: 15, block 2: 45, block 3: 30,
block 4: 30.

Every code assumption is confirmed at proposal; block 1 only records the spec reading. The
enabler comes before the sentence so the regeneration after it proves it changed nothing.

## 1. Open assumptions

- [ ] 1.1 `. d` (A4) Read `component-model`, `generated-sources` and `mutation-testing` against
  a KDoc line that depends on the sibling condition; record the result.

## 2. Codegen, under TCR at the repository root

- [ ] 2.1 `. r (internal)` `hasClassSibling(shape)` names the condition the sibling emitter
  uses; `just generate` shows no diff.
- [ ] 2.2 `^ F (internal)` `renderKdoc` adds the sentence for a function with a sibling, and
  not for a structural wrapper; the emitter test and snapshot assert both.
- [ ] 2.3 `. d` `just generate`; read the diff: 129 files, one added KDoc line each, nothing
  else. Commit the regeneration.

## 3. Docs

- [ ] 3.1 `. d` `docs/how-to.md`, "Put a component's classes on another element": a badge that
  is a link, a button that is a link, a button that is a `<summary>`; each fence compiled.

## 4. Gate and adoption

- [ ] 4.1 Full green: `check` with E2E, `koverVerify`, `:lib:testCodegen`, StrykerJS on the
  changed `codegen/src` lines, `:doc-snippets:compileSnippets`, `analyze_change_set`.
- [ ] 4.2 `openspec validate name-the-class-sibling-in-every-kdoc --strict`.
- [ ] 4.3 The evaluation under `tmp/name-the-class-sibling-in-every-kdoc/` for Oliver to adopt.
