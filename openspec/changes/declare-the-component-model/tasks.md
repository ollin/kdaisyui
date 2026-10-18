# Tasks

Tracked as #398. Folds in `tab-container-and-item-trade-places` (#342), deleted when this change was proposed.

**Estimate, frozen 2026-10-18, raw and not pre-corrected: 14 h** for blocks 1, 2, 3, 5 and 6. An
informal 13 h was given in session before the scope was settled (9 h for the model, 4 h for the
axes); it is kept here and in the ledger, not overwritten.

**Scope added the same day, before any implementation:** Oliver decided that a clean API outweighs
compatibility with 0.7.x. Block 4 is new, estimated at 3 h, and block 5 changed names but not size.
Running total 17 h; the frozen 14 h stays the original estimate. Ledger run:
`pages/ai-estimates/2026-10-18-kdaisyui-declare-the-component-model.md`.

Every block keeps `check` green after each commit. Blocks 1 to 3 change no published byte; the
proof is regeneration with no diff under `lib/generated`, `docs/reference`, `llms.txt` and
`lib/api`.

## 1. Prove the model can carry today's API — 2.5 h

The three Assumed claims this change rests on are checked here, before anything is built on them.

- [x] 1.1 `. d` **Measure: does the frontmatter list every marked class?** For every component
  page at the pinned tag, compare the `$$`-marked classes in the fenced examples with the
  frontmatter's classes. Record the difference in this task. A non-empty difference refutes the
  completeness requirement's assumption; revise `specs/component-model` before 1.2.
  **Measured 2026-10-18, the assumption holds.** No marked component class is missing from every
  frontmatter. 291 marked classes are missing from their own page's frontmatter, all of them other
  components' classes inside an example. Declared nowhere: six drawer tokens with a variant prefix,
  read as one token by the parser, and the utility `rounded-box`. The requirement is now
  Verified.
- [ ] 1.2 `. r (internal)` Model reader: read one `codegen/model/<dir>.json` into the types
  `component-shape.ts` already uses, with a test on a hand-written fixture. Settle the field
  names from `design.md` D2 here and record them in this task.
- [ ] 1.3 `. r (internal)` Export script: write every component's current inferred shape, its
  config entries and its editorial summary as `codegen/model/<dir>.json`, including the two
  skipped components as skip entries. Commit the script and its 68 output files together.
- [ ] 1.4 `. r (internal)` **Shape equality, the proof for block 3:** a test that builds every
  component's shape from its model file and deep-equals it against the inferred shape, for all
  66. A failure names the missing fact; fix the export, never the comparison.
- [ ] 1.5 `. d` **Run the element and placement cross-check once against the model**, by the code
  that still exists. Record that the only disagreements are the 30 that carry exceptions today.
  Anything else refutes the placement requirement's assumption; revise before block 3. Also
  record why it does not report `ChatVariant` on `daisyChat` or `StepsVariant` on `daisySteps`,
  whose classes DaisyUI documents on the bubble and the step (`design.md` D13).

## 2. The bump report — 1.5 h

Built before block 3 deletes the cross-check, because it is what replaces it on a bump.

- [ ] 2.1 `. r (internal)` Read a component page from a DaisyUI tag through `git show`, without a
  checkout. Test against a fixture repository with two tags.
- [ ] 2.2 `^ F (internal)` Report classes added and removed per component between two tags.
- [ ] 2.3 `^ F (internal)` Report per class the set of documented elements, where it changed.
- [ ] 2.4 `^ F (internal)` Report the `join-item` companion set and components added or removed.
- [ ] 2.5 `. F (internal)` `just daisyui-delta <old> <new>`, exit code 0 whatever it finds.
- [ ] 2.6 `. d` **Positive control:** run it for `v5.7.17` to `v5.7.42`. It must name
  `mask-square` as removed. Record the output in this task. If it does not, the delta
  requirement's assumption is refuted; revise before block 3.

## 3. Generate from the model — 4 h

Enablers. Each commit regenerates and shows no diff.

- [ ] 3.1 `. r (internal)` `component-set.ts` builds each shape from the model instead of
  classifying. Covers the Kotlin, the reference pages, `llms.txt` and the API dump.
- [ ] 3.2 `. r (internal)` `test-generator.ts` reads the model too, ending its second
  classification loop.
- [ ] 3.3 `. r (internal)` Delete the export script and the shape-equality test; the model is now
  the input, and the inferred side no longer exists to compare against.
- [ ] 3.4 `^ F (internal)` Completeness gate: generation fails when a component's frontmatter
  classes and the classes its model places differ, naming component and class. Tests: an unplaced
  class, a stale class.
- [ ] 3.5 `^ F (internal)` Completeness gate: a class placed twice fails, naming both places.
- [ ] 3.6 `^ F (internal)` Completeness gate: a directory with no model file and no skip entry
  fails, and a model file naming no directory fails.
- [ ] 3.7 `. r (internal)` Delete `codegen-config.json`, `config-consumption.ts` and
  `offered-twice.ts` with their tests.
- [ ] 3.8 `. r (internal)` Delete the element heuristic, `element-observation.ts` and
  `element-cross-check.ts` with their tests.
- [ ] 3.9 `. r (internal)` Delete `class-groups.ts`, `axis-derivation.ts`, `axis-names.ts`,
  `parser/property-table.ts` and the classification parts of `component-shape.ts` with their
  tests.
- [ ] 3.10 `. r (internal)` Delete the join-item derivation from `join-scope.ts`; keep the scope
  emitter.
- [ ] 3.11 `. r (internal)` Delete the exclusivity measurement: `exclusivity.json`, the probe
  modules, `:lib:verifyExclusivity`, its CI step, `just measure-exclusivity` and
  `just verify-exclusivity`, the `exclusivity` source set in `e2e-tests`. Cite the last commit
  that carried them in the body.

## 4. One naming rule — 3 h

Each commit edits the model, regenerates, and migrates every call site in `example-app` and
`e2e-tests` in the same commit, so `check` stays green.

- [ ] 4.1 `. d` Run the naming rule from `specs/component-model` over the model as a throwaway
  script and record every function, enum and entry it would rename in this task. Compatibility is
  not a constraint, so the list is the whole of it.
- [ ] 4.2 `^ F (internal)` The naming test, with the model edits for the function names it
  refuses: `daisyStats` and its parts, `daisyStep` and `daisyStepIcon`, and whatever 4.1 found
  besides. Tab is left to 5.9.
- [ ] 4.3 `^ F` Every `variant` parameter becomes `color`, and every `<X>Variant` enum becomes
  `<Stem>Color`.
- [ ] 4.4 `^ F` The `modifier` axes get names: `AvatarPresence`, `CardLayout`, `CarouselSnap`,
  `ListColumn`, `MaskHalf`.
- [ ] 4.5 `^ F` `daisyTooltip`: `side` and `align`.
- [ ] 4.6 `^ B` The chat bubble colors move from `daisyChat` to `daisyChatBubble`. The generated
  test asserting the class on the bubble is the reproducing test, in the same run.
- [ ] 4.7 `^ B` The step colors move from `daisySteps` to `daisyStep`, the same way.

## 5. Declare the axes, trade the tab functions — 4 h

Same cadence as block 4.

- [ ] 5.1 `^ F` `daisyButton`: `style`, `shape`, `width` axes; `active` stays a toggle.
- [ ] 5.2 `^ F` `daisyAura`, `daisySwap`: `style` axes.
- [ ] 5.3 `^ F` `daisyCarousel`, `daisyFooter`, `daisyTimeline`: `direction` axes.
- [ ] 5.4 `^ F` `daisyChat`, `daisyDivider`, `daisyStack`: `placement` axes.
- [ ] 5.5 `^ F` `daisyCollapse`: `icon` and `state` axes.
- [ ] 5.6 `^ F` `daisyModal`: two placement axes. `daisyMegamenu`: `width` axis.
- [ ] 5.7 `^ F` `daisyDropdown`: `side`, `align` and `state` axes; `hover` stays a toggle.
- [ ] 5.8 `. d` **Check the tab item assumption:** list the tab constructions `fwb-ssr` and
  `example-app` use. A `<label>` construction refutes it; revise the tab requirement before 5.9.
- [ ] 5.9 `^ F` Tab: `daisyTabs`, `daisyTab`, `daisyTabRadio`, `daisyTabContent` per
  `design.md` D9; `active`/`disabled` on the items; `style` axis on the container. Closes #342.
- [ ] 5.10 `^ F` Tab demo route in `example-app` and an E2E scenario asserting the container and an
  active item.
- [ ] 5.11 `^ F` `kdaisyui-classes.txt` lists the classes the model offers. Closes #345.

## 6. Wrap-up — 2 h

- [ ] 6.1 `. d` **How to migrate from 0.7.x** in `README.md`: the renamed functions and enums,
  every removed boolean with its replacement, the tab table led by the `daisyTab { }` hazard, and
  the twelve two-look pairs that now need `extraClasses`.
- [ ] 6.2 `. d` Re-dump `lib/api/lib.api` and `lib/api/components.api`; read both diffs against
  the migration note.
- [ ] 6.3 `. d` Rewrite `kdaisyui-codegen` and `kdaisyui-daisyui-upgrade`, the
  `/daisyui-upgrade` command and `AGENTS.md` for the declared model and the bump report.
- [ ] 6.4 `. d` Comment on #356 and #357 that their decisions now live in the model, and close
  both.
- [ ] 6.5 Full green per `openspec/config.yaml`: `./gradlew check` including E2E, `:lib:pitest`,
  `:lib:testCodegen`, `:lib:checkComponentApi`, regeneration with no drift, and CodeScene on the
  change set. `codegen/` has no mutation tool; say so in the evaluation.
- [ ] 6.6 `. d` Write `tmp/declare-the-component-model/EVALUATION-declare-the-component-model.md`.
