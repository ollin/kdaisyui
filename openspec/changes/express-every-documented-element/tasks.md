## Estimate

Frozen 2026-10-18 before the first commit, raw, ledger run
`2026-10-18-kdaisyui-express-every-documented-element`: **375 min**. Block 1 checks 30, block 2
siblings 90, block 3 composition rule 60, block 4 class-only functions and the #347 moves 90,
block 5 the four new parts 45, block 6 docs and gate 60. Known ratios for kdaisyui codegen work,
not folded in: 0.06, 0.12, 0.21, 0.26.

Every fix of a gap starts by deleting its `notExpressed` declaration, so the gate fails first;
the model change then makes it pass. Each behaviour is one TCR run at the repository root, after
regenerating.

## 1. Check the assumptions first

- [x] 1.1 `. d` **Count what remains** under the composition rule of `design.md` D4, with D3's
  model edits applied in memory: the spec assumes no declaration remains. Check on the way that no
  two own classes on one element offer the same modifier, which D4's single pass relies on. Any
  remaining gap refutes the Assumed; revise the plan with Oliver before block 2.
  **Simulated 2026-10-18: it holds, after one refinement of D4.** No gap and no overlap; 2,564
  elements expressible, 117 as compositions. The first pass left `skeleton skeleton-text`, because
  it took the first of two functions with own class `skeleton`; D4 now takes the one offering the
  most of the element's classes. A rendering part with no own class (`daisyDockItem`) claims a
  remaining group like a class-only function; spec and D4 say so.
- [x] 1.2 `. d` **Check the receiver assumption**: every receiver the generated functions give
  their `attrs` and `content` lambdas is a kotlinx.html `Tag`, read from the kotlinx.html sources.
  **Read 2026-10-18 in kotlinx-html-jvm 0.12.0's sources: it holds.** Every element class the model
  uses (24 of them) is generated as `open class X(…) : HTMLTag(…)`, and `HTMLTag` implements `Tag`.

## 2. A class sibling for every function

- [x] 2.1 `^ r (internal)` The shape carries each function's class-bearing parameters as a list
  the sibling can be emitted from. Zero diff.
- [x] 2.2 `^ F` Emit `Tag.daisy<Name>Classes` after every function that writes a class (D1), its
  generator unit test first, and its coverage tests (D5) in the same step so `koverVerify` holds.
- [x] 2.3 `^ F` Reference pages and `llms.txt` list each sibling under its function.
- [x] 2.4 ~~`. d` Re-dump both API baselines and read the diff: siblings only.~~
  **Folded into 2.2 on 2026-10-18.** `./gradlew build`, which TCR runs, includes `checkKotlinAbi`,
  so 2.2's first run was reverted on the stale `lib/api/lib.api`, not on a test. The baselines are
  re-dumped in the step that changes them, after reading that the diff only adds.

## 3. Two components on one element

- [x] 3.1 `^ r (internal)` The classifier returns the claiming groups of D4, still accepting only
  a single group. Zero diff.
- [x] 3.2 `^ F` Generate a composition's test as the call plus its siblings in `attrs`, while the
  gate still accepts one group: every composite element DaisyUI documents is rendered and its
  classes asserted before the gate relies on it.
- [x] 3.3 `^ F (internal)` Accept several groups in the gate, and delete the 27 composition
  declarations in the same commit. Red first, observed and recorded in the commit body:
  regeneration with the rule and the declarations still in fails naming all 27 as stale.
  Reordered 2026-10-18 from "rule, then tests and deletions per page": with the rule in and any
  declaration left, generation fails, so no commit in between would regenerate cleanly.

## 4. Classes where DaisyUI puts them (#347)

- [x] 4.1 `^ F (internal)` `kind: "classes"` (D2): generates only the sibling, its test first.
- [x] 4.2 `! F` Menu: delete the four menu declarations (red), then `daisyMenuItem(active,
  disabled)` and the `show`/`dropdownShow` toggles on the dropdown parts; `daisyMenu` loses
  `active`, `disabled`, `dropdownShow`. Example app and README migration in the same step.
  No call site in the example app or the hand-written tests used the three; the README entries for
  every move are written together in 6.1.
- [x] 4.3 `! F` Dock: delete its declaration, then part `daisyDockItem`; `daisyDock` loses `active`.
- [x] 4.4 `! F` Rating: delete its declaration, then `daisyRatingItem(hidden)`; `daisyRating` loses
  `hidden`.
- [x] 4.5 `! F` List: delete the two column declarations, then `daisyListCell(colGrow, colWrap)`;
  `daisyList` loses `column` and `ListColumn` goes.

## 5. The four classes no function renders

- [x] 5.1 `^ F` `daisyListRow`: delete its declaration, then the part; `list-row` leaves
  `notOffered`.
- [x] 5.2 `^ F` `daisyFloatingLabel`, the same way.
- [x] 5.3 `^ F` `daisyAvatarGroup`, the same way.
- [x] 5.4 `^ F` `daisyVc`, the same way.
- [x] 5.5 `. d` Confirm no `notExpressed` declaration is left in `codegen/model/`, and record it
  against the spec's Assumed.
  **Counted 2026-10-18 after 5.4:** no model file declares `notExpressed`; generation passes; of
  2,571 marked elements 2,564 are expressible (117 as compositions), 7 count no class, 0 gaps.
  Exactly 1.1's simulation.

## 6. Documentation and the gate

- [ ] 6.1 `. d` README "How to migrate from 0.8.x": one row per moved parameter, and the sibling.
- [ ] 6.2 `. d` Skills: `kdaisyui-codegen` (sibling, `kind: "classes"`, the rule), the testing and
  upgrade skills where they name the gate. Prose in `README.md`, `docs/` and the llms template
  that uses a moved parameter.
- [ ] 6.3 `. d` Re-dump both API baselines after block 5 and read the diff.
- [ ] 6.4 `. d` The full gate: `./gradlew check` with E2E, `koverVerify`, `:lib:pitest`,
  `:lib:testCodegen`, `:lib:checkComponentApi`, regeneration drift, CodeScene. No mutation tool for
  the TypeScript codegen; say so.
- [ ] 6.5 `. d` Write `tmp/express-every-documented-element/EVALUATION-express-every-documented-element.md`.
