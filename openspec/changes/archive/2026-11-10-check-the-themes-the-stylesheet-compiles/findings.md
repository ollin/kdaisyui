# Findings

The change comes from `archive/2026-11-10-select-a-theme-in-the-profile/findings.md`, F1.

## F1 - The review found the plan unbuildable in three places

- **What happened:** the review of 2026-11-10 (`fwb-openspec-reviewer`): wiring the task into
  `check` would have pulled a Docker compile into every `check` and failed builds without an entry;
  the plugin had no source for the theme list (it does not depend on `lib`); the planned TestKit
  test would be invisible to Kover and re-run per pitest mutant; tasks were not single commits.
  Revised before any code: a generated `kdaisyui-themes.txt`, a finalizer instead of `check`,
  in-process tests, probes A3, A4, A7 first, tasks split.
- **Cause:** the proposal was written from the example app's view, where a stylesheet always
  exists and the library is a project dependency.
- **Cost:** one review round, about 30 minutes; three probes added.

## F2 - The stylesheet task re-runs once after each compile

- **What happened:** 2026-11-10, probe 1.3. `kdaisyuiStylesheet` writes its output to
  `build/kdaisyui/app.css` by convention (`KdaisyuiPlugin.kt`, `output.convention`), and copies the
  whole `build/kdaisyui` directory in as its `classList` input. Its own output therefore changes its
  input: after every compile the next run is not up to date and compiles once more in Docker.
- **Cause:** a pre-existing defect of `compile-the-stylesheet-in-the-plugin`, found on the way;
  outside this change.
- **Cost:** none here beyond a confusing probe run; in every consumer build, one extra container
  compile after each real one.

## F3 - Two reverts: a dense switch Kover counts gap by gap, and a test left on disk

- **What happened:** 2026-11-10, task 3.3. Adding tags 10 and 11 to `when (tag)` made the cases
  dense; Kotlin compiled a table switch over 1-12, and Kover counted its five gaps (2, 4, 5, 6, 8)
  as branches no test reaches: 92.2 % branch coverage, reverted. The refactoring that avoids the
  dense switch then went red because the reverted step's test was still on disk (TCR keeps tests).
  Stopped as the rule demands, asked Oliver, then: test stashed, `^ r` alone green, the behaviour
  with the test green.
- **Cause:** Kover's per-slot branch counting of table switches was unknown; and the stash before
  a refactoring after a revert was forgotten.
- **Cost:** two red runs and one question round, about 10 minutes.

## F4 - The warning stopped guessing the entry's flags

- **What happened:** 2026-11-10, task 3.11. The first wording rebuilt a whole `themes:` line from
  the carried themes, giving `light` and `dark` DaisyUI's default flags; the run was reverted
  because no test carried a theme without a flag (Kover, one branch). The smaller transformation
  dropped the guess: the warning names the missing themes and shows `themes: …, nord;`, since the
  plugin cannot know which flags the entry really sets. The spec delta says so.
- **Cause:** the plan asked for "the entry line that adds them", which needs knowledge the plugin
  does not have.
- **Cost:** one red run, about 5 minutes.

## Proposals

One row per finding (house rules §9).

| Finding | Proposal | Status |
|---|---|---|
| F1 | - | none - record only: a review round doing its job |
| F2 | The stylesheet task's output convention moves out of the directory it copies in as `classList` (e.g. `build/kdaisyui-css/app.css`), so its own output stops making it out of date; a plugin test asserts the output is not below `classList` | open |
| F3 | A sentence in `kdaisyui-build` (or a `rules:` entry for tasks): a `when` over dense constant tags is a table switch whose gaps Kover counts as branches - use conditions or a set; and stash a reverted step's test before the refactoring that should precede it | open |
| F4 | - | none - record only |
