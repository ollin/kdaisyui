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
