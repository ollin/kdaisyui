# Findings

## F1 - `DaisyTheme` can name a theme the stylesheet does not carry

- **What happened:** at proposal, 2026-11-10. The enum lists all 35 built-in themes, while a
  compiled entry carries only `light` and `dark` unless it names more
  (`daisyui/packages/daisyui/functions/pluginOptionsHandler.js:9`). `daisyTheme(DaisyTheme.Nord)`
  against such a stylesheet renders the default theme, with no error anywhere - the same silent
  gap `css-delivery` describes for classes.
- **Cause:** themes are not classes, so the plugin's class list never sees them, and the entry's
  `@plugin` block is the consumer's text, not the plugin's.
- **Cost:** none here; the how-to states the limit (task 4.1). A plugin check, or a plugin option
  that writes the `themes` line, would close it.

## F2 - One question round at proposal

- **What happened:** four questions put to Oliver together on 2026-11-10: library scope,
  persistence, what "preview" means, which themes compile. All four answered as recommended.
- **Cause:** each changed scope or observable behaviour (the picker with or without a live
  preview, a library helper or none).
- **Cost:** one round, before the ledger run opened, so no wait is recorded.

## F3 - A7 decided against the recommendation; the plan grew by two tasks

- **What happened:** 2026-11-10, after the proposal was committed. Asked at review whether
  `daisyThemeController` stays without a `theme` parameter, Oliver chose the parameter (A7),
  after a detailed explanation with usage examples. Tasks 2.8 (model and regeneration) and 4.4
  (the migration note for the binary change) were added; the frozen estimate stands.
- **Cause:** the proposal had removed the parameter as subtraction, while the scope question at
  the start had offered it as part of the typed basics - the two said different things.
- **Cost:** two question rounds (the A7 question and the request for the explanation), recorded
  as decision waits; about 45 minutes of unplanned work.

## F4 - The review split three tasks and corrected two assumption statuses

- **What happened:** the review of 2026-11-10 (`fwb-openspec-reviewer`) found task 2.3 bundling a
  build declaration with the generator wiring, and 3.3 bundling the `themes: all` stylesheet with
  the preview cards; A2 and A5 marked `confirmed at proposal` on a reading, with their browser
  checks deferred and pointed at the wrong task; A6 written without the constraints list. Fixed:
  2.4/2.5 and 3.3/3.4 split, a refusal task 2.2 for theme names the entry rule cannot spell, A2 and
  A5 `to check`, A6 compared with `fwb_tasks.constraints list` once `affected_areas` was declared
  in `.openspec.yaml` (it printed nothing before).
- **Cause:** a reading of DaisyUI's source was recorded as confirmation of what a browser does;
  and `affected_areas` was not declared, so the constraints list had nothing to match.
- **Cost:** one review round, about 25 minutes of rework.

## F5 - The re-review found a wrong citation and an API baseline step too late

- **What happened:** the re-review of 2026-11-10 found A9 citing `radio.json` for
  `this.value = value`, which lives in `input.json:59`; and the `lib.api` re-dump planned as a
  last task 2.10, although `checkKotlinAbi` is part of `check`, so root TCR would revert every
  step that adds public API before it. Fixed: the citation; each API-changing step re-dumps the
  baseline in its own run, and 2.10 is gone. Tasks 3.2 and 3.4 are planned `!` by their size.
- **Cause:** the precedent was quoted from memory of the grep output instead of the file; the
  baseline was treated as bookkeeping, not as part of the build TCR runs.
- **Cost:** one more review round, about 10 minutes.

## F6 - Task 2.1 planned file reading inside a mutated module

- **What happened:** 2026-11-10, at the start of block 2. Task 2.1 had `themeNames(directory)`
  read a directory, but the codegen keeps reading and writing files in `index-*.ts` entry points
  and the logic in pure functions beside them, which StrykerJS mutates. Corrected before
  implementing: `themeNames(fileNames)`, the listing done by `index-new.ts` in task 2.5.
- **Cause:** the task was written before reading `kdaisyui-codegen`'s mutation-testing section.
- **Cost:** about 3 minutes; no code was written against the old task.

## F7 - The lib.api re-dump pushes the small API steps over eight lines

- **What happened:** 2026-11-10, task 2.6. `Tag.daisyTheme` and its test are eight counted
  lines, and the `lib/api/lib.api` re-dump F5 folded into the same run adds three more, so the
  step is `! F`, not the planned `^ F`. 2.7 was replanned `!` and then counted eight lines with
  its one-line re-dump, so it stayed `^`.
- **Cause:** F5 moved the baseline into each step without recounting the steps' markers.
- **Cost:** none in time; two markers one level lower than planned.

## F8 - A9 was false: the coverage-test generator knew only `ButtonType`

- **What happened:** 2026-11-10, task 2.8. With the `theme` extra in the model, `just generate`
  wrote `ThemeControllerCoverageTest` asserting `value="` in its all-flags case without passing a
  theme, because `test-generator.ts` drives a nullable external enum only when it is listed in
  `EXTERNAL_ENUM_VALUES`, which held `ButtonType` alone. The model edit was stashed, the generator
  taught `DaisyTheme` in its own `! F (internal)` run with a unit test, and the model edit then
  went green.
- **Cause:** A9 named `button.json` as the precedent without reading how the generator reaches
  `ButtonType`; the table is a second place an external type must be named.
- **Cost:** about 10 minutes, one extra commit; no revert.
