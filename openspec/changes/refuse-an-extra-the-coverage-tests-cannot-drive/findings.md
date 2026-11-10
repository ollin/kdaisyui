# Findings

The change comes from `archive/2026-11-10-select-a-theme-in-the-profile/findings.md`, F8.

## F1 - The review found the planned output change and an unsplit generator step

- **What happened:** the review of 2026-11-10 (`fwb-openspec-reviewer`): reading every extra's
  `imports` would have added `InputType` to `InputCoverageTest.kt` (A3 false as planned); A2 was
  answerable from the code; `sample` would have leaked into the Kotlin emitter's config; task 2.3
  bundled four edits; the finding had no call site, no committed-model control and no precedence.
  All folded into proposal, design, spec and tasks before any code.
- **Cause:** the proposal was written from the tables' side without reading every model's
  `imports` or the path from model to coverage generator.
- **Cost:** one review round, about 20 minutes.

## F2 - Deleting the tables was a behaviour change, and a parallel commit swallowed a step

- **What happened:** 2026-11-10, task 2.5 was planned `^ r`, but `coverage-test-generator.test.ts`
  held a test from `select-a-theme-in-the-profile` that drove a `DaisyTheme?` extra with no sample —
  the table behaviour this change removes. It was deleted, and the step committed `! F (internal)`.
  Separately, in task 2.2 the TCR run and the tick commit were started in parallel; the tick commit
  took the model edits, TCR found nothing to commit, and the local commit was split again.
- **Cause:** the plan did not grep the tests for the tables' behaviour; and the second parallel
  start repeats the same slip from `select-a-theme-in-the-profile` (its evaluation noted it once).
- **Cost:** about 5 minutes; no revert, nothing pushed in the wrong shape.
