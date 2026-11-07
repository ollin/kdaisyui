# Findings

## F1 - The working implementation lives in another repository

- **What happened:** at proposal, 2026-11-07. The Deploy-Radar's `compileTailwind` in
  fwb-application is the implementation this change generalises; it was read from the worktree
  `fwb-application--work-on--deployment-risk-tool`, since the `main` checkout did not yet carry
  it. The proposal cites it by path and line; the code itself is the employer's and is not copied.
- **Cause:** the proposal arrived before the Deploy-Radar's branch reached fwb-application's main.
- **Cost:** none; one extra directory read.
