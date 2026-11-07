# Findings

## F1 - The request came from another repository's findings, not from an issue here

- **What happened:** at proposal, 2026-11-07. The Deploy-Radar of fwb-application, built on
  kdaisyui 0.9.0 behind `Content-Security-Policy: default-src 'self'`, posted ten proposals to
  this project on the agent board (`~/ai/kdaisyui-improvements-from-deploy-radar.md`). Item 2 is
  this change. No kdaisyui issue or E2E scenario had seen the inline style, because no page here
  sends the header.
- **Cause:** kdaisyui's E2E suite runs without a Content-Security-Policy; `guarantee-strict-csp`
  is the change that closes that gap.
- **Cost:** none here; the proposal cites the Deploy-Radar's evidence directly.

## F2 - The first task order would have reverted under root TCR, and the test generator was missing

- **What happened:** the review of 2026-11-07 (`fwb-openspec-reviewer`) found that the core
  and codegen steps came before the rules reached the example app's stylesheet and before the
  E2E feature's markup assertions changed, so each would have gone red on
  `dropdown_popover.feature:7-8` and the "sits below" measurement; and that
  `codegen/src/test-generator.ts:113` hard-codes the expected inline style, which no task named.
  The review also found the probe behind A1 deleted from `/tmp`, so "confirmed" could not be
  re-checked.
- **Cause:** the tasks were ordered by component, not by what keeps the E2E suite green at
  each step; the Impact list was read from the emitter alone; and the house rule to keep
  evidence beside the proposal was not applied to a scratch file.
- **Cost:** about 20 minutes of rewriting before any implementation: block 2 now ships the
  rules first, block 3 moves one side of the pair per step with its feature line, the test
  generator is named in task 3.1 and the Impact list, and the probe is kept as
  `assets/anchor-probe.html` with its command and result.

## F3 - The browsers the E2E suite runs, read for A2

- **What happened:** task 1.1, 2026-11-07. Locally `/usr/bin/chromium` is 156.0.8078.79. In CI
  the E2E job (run 42393356804) ran on `ubuntu-26.04` image 20261101.186, which ships Google
  Chrome 156.0.8078.79 (the browser `systemChromium` finds there) and Chromium 156.0.8078.0;
  `playwrightInstall` fetched Chrome for Testing 156.0.8078.4. Every one is above the floor of
  133 for typed `attr()`, so A2 holds and no CI task was added.
- **Cause:** record only; the task asked for both versions to be written down.
- **Cost:** none.

## F4 - A spec delta named a task that does not exist

- **What happened:** task 1.3, 2026-11-07. Re-reading the specs found the delta of
  `daisyui-component-coverage` saying the anchoring assumption is wrong "after task 4.3"; the
  reorder of F2 had made it task 3.2. Corrected in the 1.3 commit. The four main specs
  themselves hold nothing against the change.
- **Cause:** the reorder of F2 renumbered the tasks, and nothing checks a task number cited in a
  spec against `tasks.md`.
- **Cost:** two minutes.

## F5 - Task 2.1 could not be `^ F`

- **What happened:** task 2.1, 2026-11-07. The resource is two rules plus a header that tells
  whoever opens it what it is and how to import it, and its test reads it and compares the rules:
  about fifteen counted lines, so the planned `^ F` would have broken the eight-line ceiling.
  The task now says `! F`. Task 2.3 met the same: a second output property, its write, the
  plugin's wiring and the two tests are 14 counted lines; it says `! F (internal)` now. Task 3.2
  too: the attribute write, two rewritten `PopoverTest` cases, two feature lines and a new E2E
  step for "the body does not contain" are about twelve; it says `! F` now.
- **Cause:** the plan sized the step by its behaviour, one resource, not by its counted lines; a
  CSS file has no line comment, so its header counts in full, and a Gradle task output needs its
  declaration, its wiring and a test in two files.
- **Cost:** seven minutes and three bookkeeping commits.
