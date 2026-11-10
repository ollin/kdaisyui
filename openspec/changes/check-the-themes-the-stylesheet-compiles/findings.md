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
