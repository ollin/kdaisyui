# Findings

## F1 - Three of the four behaviours the Deploy-Radar scripted need no script

- **What happened:** at proposal, 2026-11-07. Item 5 of the proposals file
  (`~/ai/kdaisyui-improvements-from-deploy-radar.md:67`) asked for a shipped script with
  data-attribute behaviours for "modal open/close, copy, theme toggle"; the Deploy-Radar's own
  `deploy-radar.js` has `data-copy` and `data-close` only. Reading what the browser does alone:
  a popover modal opens and closes by `popovertarget`, a `<dialog open>` closes by
  `form method="dialog"`, DaisyUI's theme controller is a checkbox. Only the clipboard needs a
  script.
- **Cause:** the Deploy-Radar rendered its dialog as a `div`, which no form can close, and so
  needed a script for a behaviour the `dialog` element has.
- **Cost:** none here; the shipped script became a documented six-line snippet (A5).

## F2 - The review split the page task and found the markup assertion wrong

- **What happened:** the review of 2026-11-07 (`fwb-openspec-reviewer`): task 2.1 bundled a
  route, a resource, a page and a fragment with no test of its own, so under one-run TCR it
  could not be committed; the planned assertion `<dialog class="modal" open` fails because
  `daisyModal` writes `id` before `class` (`Modal.kt:62-63`); the "no request" clause had no
  check; `llms.txt` lists the how-to's recipes (`codegen/llms-template.md:19`) and had no
  task; the page needs the htmx script; and A1's reason named `display: grid` where the closed
  state is in fact `visibility: hidden` (`modal.css:3`).
- **Cause:** the page was planned as one unit; the assertion was written from the DaisyUI
  example, not from the generated function's attribute order.
- **Cost:** about 15 minutes; block 2 is three steps with a scenario each, the assertions are
  two separate strings, the request log is checked, 3.5 regenerates `llms.txt`, and A1's
  reason cites the lines.
