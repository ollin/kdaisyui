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
