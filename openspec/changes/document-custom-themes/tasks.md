Estimate, frozen 2026-11-11: **2h 25min (4h 30min DevOnly)** — ledger run
`2026-11-11-kdaisyui-document-custom-themes`.

## 1. Open assumptions

None: A1 and A2 were confirmed by a probe and a reading at proposal, A3 by Oliver, A4 by reading
the specs the constraints list named (proposal, *Assumptions*).

## 2. Documentation

- [x] 2.1 `. d` The how-to's theme recipe shows a theme of one's own: replace the sentence at
  `docs/how-to.md:380-381` with the `@plugin "daisyui/theme"` block, a compiled Kotlin snippet
  (`ACME_THEME` on `html` and on a theme controller), and what `daisyThemeOf` and the theme check
  do not know. TCR at the repository root, which compiles the snippet.
- [x] 2.2 `. d` `llms-full.txt` and `llms.txt` name custom themes: edit
  `codegen/llms-full-template.md` (Themes section) and `codegen/llms-template.md` (the how-to's
  note), run `just generate`, commit templates and outputs together. TCR at the repository root.

## 3. Verification

- [ ] 3.1 The full gate: `./gradlew check` including E2E, the snippet compile and
  `generated-sources` clean after `just generate`; no production code changes, so no mutation run
  and no CodeScene scan apply, stated in `tmp/document-custom-themes/EVALUATION-document-custom-themes.md`.
