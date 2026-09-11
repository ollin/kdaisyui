# Tasks

Every task ends the same way, and it is the only acceptance criterion that matters:
**regenerate and require an empty diff under `lib/generated/`.** A port that changes output is
a failed port, whatever it looks like.

Ordering is uncertainty-first. 1.1 is the assumption everything rests on — that Node runs this
code as `.ts` at all. It is one file and it is reversible; if it fails, nothing below it is
reachable and the plan is rewritten rather than forced.

## 1. Prove the mechanism on one file

- [x] 1.1 Rename `codegen/src/test-generator-heroicons.js` to `.ts` with **no type annotations added**, update the Gradle `Exec` task's filename and the `package.json` script, and run `:lib:generateHeroiconTests` (refactoring) — the pure mechanism check: does Node execute it, does the entry-point guard still fire, is `lib/generated/` byte-identical. Chosen because it is the smallest generator and already has a test.

  **The assumption holds.** 324 icons generated, `lib/generated/` byte-identical, no build
  step and no new dependency. Two things that could have broken quietly did not: the
  entry-point guard still fires (`import.meta.url` is untouched by stripping — the 70 ms suite
  is the evidence), and a `.js` test importing a `.ts` module works, because stripping is
  decided per file by extension rather than by the importer.

- [ ] 1.2 Rename its test to `.ts` and confirm `:lib:testCodegen` still passes (refactoring) — `node --test` must discover and strip a `.ts` test; if it does not, that is a finding worth recording before porting anything else.
- [ ] 1.3 Add the first real annotations to that file: the parsed-icon shape and the exported `DEFAULT_OUTPUT_DIR` (feature-test; small) — the point at which we learn whether types say anything the code did not already say. **If they do not, stop and reconsider the change** rather than continuing out of consistency.

## 2. Port the remaining generators, one per commit

Each: rename, update its Gradle task and npm script, annotate the parser boundary, regenerate,
require an empty diff.

- [ ] 2.1 `codegen/src/test-generator.js` → `.ts`, plus its `tool-logic`-style helpers (refactoring; the largest file, and the one whose defects motivated this)
- [ ] 2.2 `codegen/src/index-new.js` → `.ts` (refactoring)
- [ ] 2.3 `codegen/src/index-heroicons.js` → `.ts` (refactoring)
- [ ] 2.4 `codegen/src/classifier.js`, `generator-new.js`, `generator-heroicons.js` → `.ts` (refactoring; one commit each if any needs real thought, one commit total if they are mechanical)
- [ ] 2.5 `codegen/src/parser/*.js` → `.ts` — frontmatter, llms-txt, svg-heroicons (refactoring) — **the highest-value annotations in the change**: this is the boundary where hand-written YAML from a submodule becomes typed data, and where every defect found so far actually lived

## 3. Make the new constraints enforceable by something other than memory

- [ ] 3.1 Record the erasable-syntax rules in `codegen/README.md` or `AGENTS.md` — no `enum`/`namespace`/parameter properties/decorators, `import type` mandatory, `.ts` extensions mandatory in specifiers (documentation)
- [ ] 3.2 Confirm `just generate` runs end to end from a clean checkout and that `generated-sources-drift` is green in CI (documentation; evidence into the change)

## Explicitly NOT in this change

- **No `tsc`, no `noEmit`, no type-check step, no devDependency.** Decided by Oliver
  2026-09-11. `codegen/package.json` stays at zero dependencies. The consequence — CI rejects
  no type error — is stated in the proposal rather than left to be discovered.
- **No behaviour change.** If a type reveals a latent defect, record it as a separate task or
  change; do not fix it inside a rename commit, where the empty-diff criterion could not hold.
