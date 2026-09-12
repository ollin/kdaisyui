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
- [x] 1.3 Add the first real annotations to that file: the parsed-icon shape and the exported `DEFAULT_OUTPUT_DIR` (feature-test; small) — the point at which we learn whether types say anything the code did not already say. **If they do not, stop and reconsider the change** rather than continuing out of consistency.

  **Verdict: continue, with the argument narrowed.** `IconPaths` and
  `IconSize = keyof typeof SIZE_DIMENSION` each say something the code did not — the first is
  the branch table `solidViewBox` consults, the second stops the size union and the dimension
  map drifting apart. `renderAssertion(…: string ×4)` says nothing, and it is the same defect
  class the proposal argued from: plain annotations cannot tell a builder name from a tag
  name, and only branded types would. The proposal has been corrected.

  `DEFAULT_OUTPUT_DIR: string` was deliberately NOT annotated — inference already says
  `string`, and writing it adds a token without adding a claim.

  **Consequence for section 2:** types pay where there is shape, not where everything is a
  string. 2.5 (the parser boundary) is the high-value task; 2.1-2.4 are mostly mechanical
  renames whose annotations will restate themselves. Sequence accordingly, and do not treat a
  thin annotation set in 2.1-2.4 as a failure — it is the predicted outcome.

## 2. Port the remaining generators, one per commit

Each: rename, update its Gradle task and npm script, annotate the parser boundary, regenerate,
require an empty diff.

- [x] 2.1 `codegen/src/test-generator.js` → `.ts`, plus its `tool-logic`-style helpers (refactoring; the largest file, and the one whose defects motivated this) — `BuilderName` / `TagName` branded, three casts total. Branding forced a real simplification: four inlined copies of `receiver === 'FlowContent' ? 'div' : receiver.toLowerCase()` became one named `wrapperTagOf`, because each would otherwise have needed its own cast. Everything else left to inference, per 1.3.
- [x] 2.2 `codegen/src/index-new.js` → `.ts` (refactoring) — mechanical, as predicted.
- [x] 2.3 `codegen/src/index-heroicons.js` → `.ts` (refactoring) — mechanical. **No `.js` remains in `codegen/src/`.**
- [x] 2.4 `codegen/src/classifier.js`, `generator-new.js`, `generator-heroicons.js` → `.ts` (refactoring; one commit each if any needs real thought, one commit total if they are mechanical) — two commits, because two of the three needed real thought:

  - `classifier`'s `ClassifiedComponent` typedef listed **thirteen** fields where the function
    returns **fourteen**. `componentClass` had been returned all along, undocumented. An
    interface cannot drift like that.
  - `generator-new.generateKotlinFile` took `elementRules`, a name wrong twice over: not
    plural, not a rule. The only caller passes `{ primaryElement: element }` and that is all
    it reads. Now `chosenElement: Pick<ElementRule, 'primaryElement'>`. **Writing the type is
    what made the name's wrongness visible** — `elementRules.primaryElement` reads perfectly
    well and had been wrong since the function was written.
  - `generator-heroicons` was genuinely mechanical.

  `config` is left to inference throughout: it is all of `codegen-config.json`, and modelling
  it is its own piece of work rather than a side effect of a rename.
- [x] 2.5 `codegen/src/parser/*.js` → `.ts` — frontmatter, llms-txt, svg-heroicons (refactoring) — **the highest-value annotations in the change**: this is the boundary where hand-written YAML from a submodule becomes typed data, and where every defect found so far actually lived. **Confirmed.** All three ported; every one had a JSDoc `@typedef` describing shapes that nothing checked, and converting those was the bulk of the value. `html-names.ts` was added because brands are nominal and two `TagName` declarations would be two incompatible types.

  **`svg-heroicons.ts` done, taken out of order** to test branded types on the smallest file
  where two string KINDS coexist. `KebabName` / `PascalName`, two casts, and `IconPaths`
  moved here from `test-generator-heroicons.ts` where 1.3 had duplicated it.

  **`frontmatter.ts` done.** Its three JSDoc `@typedef` blocks became real interfaces —
  knowledge that existed and was unenforced. `ClassCategory = keyof Classnames` pays at once:
  `MODIFIER_CATEGORIES` in `test-generator.ts` is now `ClassCategory[]`, so the typo that
  silently dropped a whole category can no longer be written.

  **The finding of this change, recorded because it generalises:** annotating the arguments
  raised CodeScene's **String Heavy Function Arguments at 41.7%, `introduced`, previous value
  zero** — while nothing about the code had changed. The `.js` version scored zero because
  nothing could see the arguments were strings. *The smell was always there; writing the types
  made it measurable.* Its remedy is Object Calisthenics rule 3, and branding
  `ComponentName` / `PascalComponentName` cleared it (7.02 → 7.24).

  **`llms-txt.ts` done.** Its `ElementRule.elements` / `primaryElement` turned out to be TAG
  names — the concept `test-generator.ts` had already branded — which is what forced
  `html-names.ts` into existence. Its `ComponentName` is the same identifier as the
  directory name on disk, and the type now says so.

  Section 2.5 complete.

- [x] 2.6 Refactor `parseYamlFrontmatter` — cc 32, nesting depth 5, eight bumps — and `parseValue` — cc 10 plus a complex conditional (refactoring) — **pre-existing complexity, surfaced not caused by the port.** A hand-rolled YAML reader in one loop with four mutable locals; the same shape `parseTestCases` had before `test-and-clean-codegen` split it, and the same fix applies. Deliberately NOT done inside a rename commit, where the byte-identical criterion could not distinguish a port from a restructuring. Write characterization tests first: this file currently has none.

  **Done. 7.24 → 9.38, verdict `improved`, five categories fixed** — Complex Method (cc 32
  and cc 10), Complex Conditional, Bumpy Road (8 bumps), Deep Nested Complexity (depth 5),
  Overall Code Complexity 6.09 → 3.04. Thirteen characterization tests first, green on the
  first run, built from the shape DaisyUI actually ships rather than an invented one — the
  parser dispatches on indent being exactly 0, 2 or 4, so a guessed fixture would have
  pinned a different branch than production uses.

  Most of the win was a single duplication: the `- ` list-item block was written out twice,
  character for character, for indent 2 and indent 4 — four of the eight bumps.

  **Two findings were introduced and are declined, with reason.** Primitive Obsession and
  String Heavy Function Arguments both went 25% → 51.4%, because the module went from 11
  functions to 24 and small functions take strings. *The two metrics pull against each
  other:* decomposing a large function necessarily multiplies its primitive parameters. The
  one that improved is the one that was hurting a reader, and the remaining arguments are
  `trimmed`, `value`, `key` — text in a text parser, with no kinds left to separate once
  line-versus-trimmed was removed by giving `scanLine` a single parameter.

## 3. Make the new constraints enforceable by something other than memory

- [ ] 3.1 Record the erasable-syntax rules in `codegen/README.md` or `AGENTS.md` — no `enum`/`namespace`/parameter properties/decorators, `import type` mandatory, `.ts` extensions mandatory in specifiers (documentation)
- [ ] 3.2 Confirm `just generate` runs end to end from a clean checkout and that `generated-sources-drift` is green in CI (documentation; evidence into the change)

## Explicitly NOT in this change

- **No `tsc`, no `noEmit`, no type-check step, no devDependency.** Decided by Oliver
  2026-09-11. `codegen/package.json` stays at zero dependencies. The consequence — CI rejects
  no type error — is stated in the proposal rather than left to be discovered.
- **No behaviour change.** If a type reveals a latent defect, record it as a separate task or
  change; do not fix it inside a rename commit, where the empty-diff criterion could not hold.
