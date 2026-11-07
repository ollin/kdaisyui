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
