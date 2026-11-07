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
