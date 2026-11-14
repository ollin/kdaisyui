## ADDED Requirements

### Requirement: The documentation names what a consumer can rely on in the compiled stylesheet

The README SHALL state what a consumer's own check of the compiled stylesheet can look for: the
theme's custom properties DaisyUI documents, such as `--color-base-100`, which survive
minification; and that the banner comment does not, since `--minify` removes it.

**Verified**: the distinction cost a consumer a reverted step: the Deploy-Radar's stylesheet test looked for the `daisyUI` banner under `--minify` (fwb-application, add-deploy-radar F12). This repository's own compile does not minify today; `compile-the-stylesheet-in-the-plugin` proposes minification as the task's default (its A10), which is where the paragraph's claim becomes observable here. Checked by reading the paragraph against that test's correction.

#### Scenario: A consumer writes a stylesheet test

- **WHEN** a developer reads the README's CSS section for what to assert
- **THEN** it names the theme custom properties as the stable marker and the banner as removed by minification
