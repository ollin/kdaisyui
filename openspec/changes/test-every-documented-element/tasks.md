## Estimate

Frozen 2026-10-18 before the first commit, raw, ledger run
`2026-10-18-kdaisyui-test-every-documented-element`: **255 min**. Blocks 1+2 30, block 3 45,
block 4 declarations 45, block 5 tests 90, block 6 docs and gate 45. Known ratios for kdaisyui
codegen work, not folded in: 0.06, 0.21, 0.26.

## 1. Check the assumptions first

- [x] 1.1 `. d` **Count the gap under exactly the spec's rule**: counted classes only, `join-item`
  only where the join scope marks the function, every model's functions as candidates. Record the
  number of distinct class sets here. Above sixty refutes the Assumed in "Every documented element
  is expressible…": stop and revise the rule with Oliver before block 2.
  **Counted 2026-10-18: 65, refuted.** 159 elements: 40 carry no function's class, 119 a class
  their function does not offer. 27 of the 65 sets were the indicator's placements on one
  `badge indicator-item` gap. Oliver chose to key a composition by the own classes of the
  functions involved: **39 keys**, 12 missing classes and 27 compositions. Spec and D2 revised.
  Expressible by class: 2,083 elements (1,336 main, 722 part, 25 custom); 322 of them on another
  element than the function renders, out of scope.
- [x] 1.2 `. d` **Check the receiver assumption**: list every part's receiver and whether a single
  kotlinx.html builder supplies it, as `wrapperTagOf` does for the custom parts. A receiver none
  supplies refutes it; revise the test requirement before block 5.
  **Checked 2026-10-18, it holds.** The model has three receivers: `FlowContent` (129 functions),
  `UL` (2) and `FIELDSET` (1). `wrapperTagOf` maps each to one builder, `div`, `ul` and `fieldSet`.
- [x] 1.3 `. d` **Check "exactly the counted classes"**: for every expressible element, compare the
  classes its call would write on every call (`class`, `always`) with the counted ones. Each extra
  class is a model defect, listed here and fixed in block 5 before the tests that would fail on it.
  **Checked 2026-10-18, it holds: no defect.** One function writes a class beside its own on every
  call, `daisySkeletonText` with `skeleton-text`, and every element it expresses carries both. 5.1
  has nothing to fix unless block 5's generated tests find what this read missed.

## 2. Enablers, zero diff

- [x] 2.1 `^ r (internal)` Bind classes per function, not across the whole model (`design.md` D4).
  Zero diff under regeneration.
- [x] 2.2 `^ F (internal)` The classifier `documented-elements.ts` (D1, D3), with unit tests from
  fixtures: an element is expressed by the first function whose class it carries and that offers
  the rest, with typed arguments.
  Committed as `^ r (internal)`: new code with no caller changes no behaviour yet. Typed arguments
  come with 5.2, which is their first reader.
- [x] 2.3 ~~`^ r (internal)` The test generator takes its main-function calls from the classifier,
  restricted to the page's main function. Zero diff under regeneration.~~
  **Dropped 2026-10-18 before starting it: it cannot be zero diff.** Today's tests keep only the
  classes of the page's own frontmatter, so `btn join-item` on the button page expects `btn`; the
  classifier counts `join-item`. A routing that reproduced today's output would carry that filter
  into the new path only for 5.3 to delete it. 5.2 builds the new path beside the old, 5.3 deletes
  the old.

## 3. The gate

- [x] 3.1 `^ F (internal)` The classifier reports an element no function's class reaches, and one
  whose function lacks a counted class, naming page, element and classes.
- [x] 3.2 `^ F (internal)` Classes no frontmatter declares and variant-prefixed classes do not
  count (`rounded-box`).
- [ ] 3.3 `^ F (internal)` `notExpressed` in the model type (D2): a declared class set silences its
  finding; a declaration nothing on its page needs is a finding.

## 4. Declare the gaps

- [ ] 4.1 `. d` Declare every key 1.1 counted in its page's model file, with its reason and,
  where one exists, its issue (#347 menu, dock and rating children; #343 calendar; #357 dropdown
  popover). One commit per component, so each reason is reviewed with its component.
- [ ] 4.2 `! F (internal)` Wire the gate into `index-new.ts` beside the completeness gate.
  Positive control in the commit body: one declaration removed, generation fails naming it; one
  declaration added for an expressible set, generation fails naming it.

## 5. Every expressible element is a test

- [ ] 5.1 `^ B` Fix each model defect 1.3 listed, one commit each, reproducing test first.
- [ ] 5.2 `^ F` The test generator emits a test for every expressible element of a page, parts and
  nested components included, wrapped by receiver (D5); identical calls once.
- [ ] 5.3 `^ r (internal)` Delete the main-only path, `componentCallsIn` and its stale comments
  (D6). Zero diff under regeneration.

## 6. Documentation and the gate

- [ ] 6.1 `. d` `kdaisyui-codegen`: the classifier, `notExpressed`, what the generated tests now
  cover. `kdaisyui-daisyui-upgrade`: a bump can fail the new gate; step 4 places new gaps.
  `kdaisyui-testing`: the generated test count and what a compile failure there means now.
- [ ] 6.2 `. d` The full gate: `./gradlew check` with E2E, `koverVerify`, `:lib:pitest`,
  `:lib:testCodegen`, `:lib:checkComponentApi` unchanged, regeneration drift, CodeScene
  `analyze_change_set`. No mutation tool for the TypeScript codegen yet; say so.
- [ ] 6.3 `. d` Write `tmp/test-every-documented-element/EVALUATION-test-every-documented-element.md`,
  listing every declared gap so Oliver can choose which deserve an API.
