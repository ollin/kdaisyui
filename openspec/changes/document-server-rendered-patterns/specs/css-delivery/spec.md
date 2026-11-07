## ADDED Requirements

### Requirement: The documentation names what a consumer can rely on in the compiled stylesheet

The README SHALL state what a consumer's own check of the compiled stylesheet can look for: the
theme's custom properties DaisyUI documents, such as `--color-base-100`, which survive
minification; and that the banner comment does not, since `--minify` removes it.

**Verified** that the distinction cost a consumer a reverted step: the Deploy-Radar's stylesheet
test looked for the `daisyUI` banner under `--minify` (fwb-application, add-deploy-radar F12).

#### Scenario: A consumer writes a stylesheet test

- **WHEN** a developer reads the README's CSS section for what to assert
- **THEN** it names the theme custom properties as the stable marker and the banner as removed by minification
