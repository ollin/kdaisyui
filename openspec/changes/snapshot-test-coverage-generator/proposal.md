## Why

`codegen/src/test-generator.ts` holds 1,024 of the 1,897 mutants that survive in `codegen/`
(full StrykerJS run, 2026-11-01). Its unit test covers four parsers. Everything else is proven only
by the drift job, which regenerates with the DaisyUI submodule and diffs the committed output:
slow, and invisible to the mutation gate, so every change to the file pays for the old survivors
on the lines it touches.

The file has two halves with different inputs:

| Half | Reads | Writes |
|---|---|---|
| Documented-element tests | DaisyUI's `+page.md` from the submodule, the model | `*Test.kt` |
| Branch-coverage tests | the committed component sources in `lib/generated/main` | `*CoverageTest.kt` |

The second half's input **and** its expected output are both committed, in every checkout and in
the `codegen-tests` job. The snapshot already exists, and the drift job already keeps it honest.

## What Changes

- **The coverage half becomes a pure function of a component source:** `coverageTestFor(className,
  source)` returns the Kotlin of `<Class>CoverageTest.kt`; reading and writing stay in the entry
  point. Done as TCR refactorings, proven by `just generate` without a diff.
- **One snapshot test**: for every committed component file in `lib/generated/main`, the function
  returns exactly the committed `*CoverageTest.kt` beside it in `lib/generated/test`. 66 files, no
  new fixture, no submodule.
- **What survives afterwards is measured and listed**, per function, in `findings.md`. Survivors
  the snapshot cannot see are either code that cannot matter, which is deleted, or a missing
  case, which gets a test.

**Subtractive option considered:** delete the coverage half and let the generated components'
own tests reach 100 % Kover without it. Rejected: the coverage tests exist because the documented
examples do not drive every branch, and the aggregated 100 % line and branch gate depends on them
(`generated-sources`, "Every generated category carries its tests").

## Deliberately out of scope

- **The documented-element half.** Its input is the DaisyUI submodule, which `codegen-tests` does
  not check out. A snapshot there needs either vendored copies of a few `+page.md` files as
  fixtures or a job with the submodule; that choice is its own change.
- **`generator-new.ts` and the three heroicons files**, the next generators in the agreed order.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | The coverage half's output for the committed sources equals the committed `*CoverageTest.kt` byte for byte | drift job | verified: `generated-sources-drift` regenerates all of them on every full run, and `just generate` left no diff on 2026-11-01 |
| A2 | The coverage half reads nothing but the component source it is given: no model, no frontmatter, no submodule | reading | verified in task 1.1: the coverage half's only file access is `generateCoverageForFile` reading one component and writing its test; everything it calls is a pure function of that text |
| A3 | The snapshot test kills most of the coverage half's mutants | measurement | open, task 3.2; *wrong if* more than a quarter of that half's mutants survive |
| A4 | The test runs in well under a second, so StrykerJS's per-mutant reruns stay cheap | measurement | open, task 3.1 |

## Capabilities

### New Capabilities

<!-- none -->

### Modified Capabilities

<!-- none: the generated output does not change, and the mutation gate is unchanged. -->

## Impact

- **Edited**: `codegen/src/test-generator.ts` (the coverage half extracted to a pure function).
- **New**: `codegen/test/coverage-test-snapshot.test.ts`.
- No generated file changes; `just generate` must leave no diff after every step.
