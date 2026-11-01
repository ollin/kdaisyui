## Why

The documented-element half of `codegen/src/test-generator.ts` is the largest remaining blind spot
of the mutation gate in `codegen/`: 267 of the file's 418 survivors (full run, 2026-11-01). It
writes the 68 committed `<Name>Test.kt` files, one per DaisyUI page with examples (66 components
and two skipped pages), from the page's `+page.md`, the committed models and the class set of
every page. Since `snapshot-test-generators`, `codegen-tests` checks out the DaisyUI submodule, so
this half can be compared with its committed output the same way the others are.

## What Changes

- **The half becomes a pure function of its inputs:** `documentedTestFor(componentName, page,
  model, context)` returns the Kotlin of `<Name>Test.kt`, or null where the page has no examples;
  `generateForComponent` and `generateForSkippedPage` only read and write.
- **The pages are found through `src/submodules.ts`**, not `../../daisyui` from the source file,
  which points nowhere inside StrykerJS's sandbox (`snapshot-test-generators`, finding F2).
- **One snapshot test:** every committed `<Name>Test.kt` is reproduced byte for byte from the
  pinned pages and the committed models.
- **What still survives is measured**, per surviving group deleted where it cannot matter or
  killed by a case.

**Subtractive option considered:** delete the documented-element tests and keep only the coverage
tests. Rejected: they are the only check that a model expresses what DaisyUI documents, with the
classes DaisyUI wrote (`component-model`, "Every expressible documented element is a generated
test").

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | Generated in memory, all 68 test files equal their committed files, quickly enough to rerun per mutant (under 2 s) | probe | open, task 1.1 |
| A2 | The half reads nothing but the page, the models and the pages' class set | reading | open, task 1.1 |
| A3 | The snapshot test kills most of the half's 267 survivors | measurement | open, task 4.1; *wrong if* more than a third survive |

## Capabilities

<!-- skip_specs: generated output and every gate are unchanged. -->

## Impact

- **Edited**: `codegen/src/test-generator.ts`; maybe `index-daisyui-delta.ts` if it shares the path.
- **New**: `codegen/test/documented-test-snapshot.test.ts`.
- No generated file changes.
