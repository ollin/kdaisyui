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

## F6 - The menu sits below its trigger without the rules: `popovertarget` anchors it implicitly

- **What happened:** after task 3.2, 2026-11-07, a positive control: with the `@import` of
  `kdaisyui.css` removed from the example app, `:e2e-tests:test` failed only the stylesheet
  scenario; "The menu opens below its trigger" stayed green in Chromium 156. A popover opened by
  `popovertarget` has its invoker as its implicit anchor (MDN, "Using CSS anchor positioning",
  "Implicit anchor association"), so once no inline `position-anchor` names a missing anchor,
  DaisyUI's `position-area` places the menu at the trigger by itself. The 2026-10-31 control
  failed for the opposite reason: an explicit `position-anchor` naming an anchor that did not
  exist overrode the implicit one. The `css-delivery` delta's claim that without the rules the
  menu "opens in the middle of the viewport" is false for this markup in Chromium.
  MDN's browser-compat data (8.1.8, 2026-11-02) makes it decisive:
  `html.elements.button.popovertarget.implicit_anchor_reference` is Chrome 133, Safari 26,
  Firefox 147 - the floors anchor positioning itself has - while `css.types.attr.type_function.custom-ident`,
  which `kdaisyui.css` needs, is Chrome 133, Safari **preview only**, Firefox 155. The shipped
  rules would anchor nothing in any released Safari; the implicit anchor works in all three.
- **Cause:** the probe behind A1 measured the attribute route with the rules, never without
  them, so it could not tell "the rules anchor it" from "nothing needs to"; and A1 checked one
  engine for a rule meant for all of them.
- **Cost:** a question round with Oliver, and possibly the removal of block 2.

## F7 - The revision after F6 leaves two other changes naming `kdaisyui.css`

- **What happened:** 2026-11-07, Oliver chose the subtraction of F6. `guarantee-strict-csp`
  (proposal, design, tasks 4.1, spec `content-security-policy`) and
  `compile-the-stylesheet-in-the-plugin` (proposal, design) were written against the first design
  and name the `kdaisyui.css` import as something the application supplies. This change does not
  edit them; each revises its own artifacts when it is applied.
- **Cause:** five changes were proposed on one day against a design none of them had built yet,
  and they cite one another's mechanism rather than only its outcome.
- **Cost:** none here; one revision task in each of the two changes.

## Proposals

One row per finding (house rules §9).

| Finding | Proposal | Status |
|---|---|---|
| F1 | - | none - record only |
| F2 | A rule in `openspec/config.yaml` for `tasks`: before ordering, grep the repository for the literal string the change replaces and name every hit in a task, and order the steps so each is green under root TCR, which runs the E2E suite | adopted -> planning-rules-from-popover-anchoring |
| F3 | - | none - record only |
| F4 | A check beside `openspec validate`: every "task N.M" a change's proposal, design or spec cites exists in its `tasks.md` | rejected - two minutes of cost; a cross-reference validator is more to maintain than it saves (Oliver, 2026-11-08) |
| F5 | A rule in `openspec/config.yaml` for `tasks`: a step's marker is planned from its counted lines - a changed line counts twice, a CSS comment counts - and a behaviour step above eight is planned `!` | adopted -> planning-rules-from-popover-anchoring |
| F6 | A rule in `openspec/config.yaml` for `proposal`: an assumption about a browser feature cites compat data for every engine the docs name, and a control run with the mechanism removed | adopted -> planning-rules-from-popover-anchoring |
| F7 | A revision task in `guarantee-strict-csp` (1.4) and in `compile-the-stylesheet-in-the-plugin` (1.3): drop the `kdaisyui.css` import, which no release ships | adopted -> guarantee-strict-csp |
