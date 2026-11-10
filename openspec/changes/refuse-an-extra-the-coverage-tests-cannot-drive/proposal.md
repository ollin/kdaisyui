## Why

A nullable model `extra` whose type is neither `String` nor a generated enum — today
`ButtonType?` on the button and `DaisyTheme?` on the theme controller — is driven in the generated
coverage tests only if `test-generator.ts` lists it in its two tables `EXTERNAL_ENUM_VALUES` and
`EXTERNAL_ENUM_IMPORTS`. A new one that is not listed generates a coverage test that asserts the
attribute and never passes a value, and the failure surfaces as a red Kotlin test after
regeneration, not as a named generation error (`select-a-theme-in-the-profile`, finding F8,
adopted by Oliver 2026-11-10).

## What Changes

- **A model `extra` declares its own `sample`**, the Kotlin expression a test passes to drive it:
  `"sample": "ButtonType.button"`, `"sample": "DaisyTheme.Dark"`. The coverage-test generator passes
  it, and imports the extra's `imports` **only for an extra that declares a sample** — `input.json`'s
  non-nullable `type: InputType` carries `imports` too, and `InputCoverageTest.kt` imports nothing
  of it today.
- **The two tables in `test-generator.ts` go.** The model becomes the one place, as `AGENTS.md`
  asks of every structural fact.
- **Generation fails**, naming component and parameter, when a nullable extra is neither `String`,
  `Boolean` nor one of its component's generated enums and declares no `sample`. A `sample` on a
  type that needs none is ignored, since the generated-enum rule comes first.

### The subtractive option, stated

Keep the tables and only add the check that fails on an unlisted type. Smaller, but it keeps a
second place, outside the model, that each new type must be added to — the cause of F8. This
change removes the tables; nothing is added that the model did not already almost carry.

## Out of scope

- Non-nullable extras with a default (`type: InputType = InputType.text`): their `apply` runs
  unconditionally and the defaults test covers it.
- Any change to the generated Kotlin components: only the coverage tests' arguments come from the
  new field, and they must regenerate byte-identical.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | Today exactly two extras need a sample: `button.json` `type: ButtonType?` and `theme-controller.json` `theme: DaisyTheme?` | code | confirmed at proposal - the extra types in `codegen/model/*.json`: 10 `Boolean`, 7 `String?`, 1 `InputType` (non-nullable, with `imports`), 1 `ButtonType?`, 1 `DaisyTheme?`, 2026-11-10 |
| A2 | The coverage-test generator can reach an extra's `sample` and `imports` once two sites carry them: an optional field on `ParameterShape` (`component-shape.ts`) and its filling in `parameterShape` (`model.ts`) | code | confirmed at proposal - `classifyParam` (`test-generator.ts`) receives a `ParameterShape` and reads `name`, `type`, `cssClass`, `writes`; `parameterShape` builds an extra's shape from `name`, `type`, `default`, `writes` only; the generator never sees `ModelExtraParameter` |
| A3 | With samples replacing the tables, `just generate` reproduces `lib/generated/test/**` byte-identical, given imports are read only for sampled extras | code | to check in task 2.5 - the snapshot tests and the drift check; review 2026-11-10 showed that reading every extra's `imports` would add `InputType` to `InputCoverageTest.kt` |
| A4 | No requirement of a main spec this builds on contradicts it: `component-model` ("The component API is declared in a committed model", "Every expressible documented element is a generated test"), `mutation-testing` ("Changed codegen lines are mutation-tested at 100 %") | code | confirmed at proposal - read 2026-11-10 |

## Capabilities

### Modified Capabilities

- `component-model`: an extra the coverage tests cannot drive without help declares a sample;
  generation fails without one.

## Impact

- `codegen/src/` (model type, shape, test generator, a finding), two model files, their tests;
  `lib/generated/test/**` regenerated unchanged; the `kdaisyui-codegen` skill.
