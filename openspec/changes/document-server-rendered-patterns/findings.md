# Findings

## F1 - Three of the four behaviours the Deploy-Radar scripted need no script

- **What happened:** at proposal, 2026-11-07. Item 5 asked for a shipped script with
  data-attribute behaviours for "modal open/close, copy, theme toggle". Reading what the
  Deploy-Radar's `deploy-radar.js` does and what the browser does alone: a popover modal opens
  and closes by `popovertarget`, a `<dialog open>` closes by `form method="dialog"`, DaisyUI's
  theme controller is a checkbox. Only the clipboard needs a script.
- **Cause:** the Deploy-Radar rendered its dialog as a `div`, which no form can close, and so
  needed a script for a behaviour the `dialog` element has.
- **Cost:** none here; the shipped script became a documented six-line snippet (A5).
