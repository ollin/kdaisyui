## ADDED Requirements

### Requirement: An extra the coverage tests cannot drive declares a sample

A model `extra` whose type is nullable and neither `String`, `Boolean` nor a generated enum SHALL
declare a `sample`, the Kotlin expression the generated coverage tests pass to drive it, and
generation SHALL fail, naming the component and the parameter, when it does not. The generator
SHALL hold no table of such types of its own.

**Verified** 2026-11-10: two extras need one today, `ButtonType?` and `DaisyTheme?`.

#### Scenario: An extra without a sample

- **WHEN** a model declares `{ "kind": "extra", "name": "x", "type": "Foo?", … }` with no `sample`
- **THEN** `just generate` fails, naming the component and `x`

#### Scenario: The sample drives the branch

- **WHEN** `theme-controller.json` declares `"sample": "DaisyTheme.Dark"` for `theme`
- **THEN** the generated all-flags coverage test passes `theme = DaisyTheme.Dark` and imports the enum
