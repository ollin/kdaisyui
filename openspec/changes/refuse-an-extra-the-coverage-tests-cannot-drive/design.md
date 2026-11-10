## Context

See proposal.md. `test-generator.ts:369-372` holds `EXTERNAL_ENUM_VALUES` and
`EXTERNAL_ENUM_IMPORTS`; `select-a-theme-in-the-profile` added `DaisyTheme` to both.

## Decisions

### 1. The sample lives in the model

**Chosen:** an optional `sample` on `ModelExtraParameter`, passed through the shape to the
coverage generator. **Rejected: keep the tables and add a check** (proposal, subtractive option).
**Rejected: derive a value** (`Foo.entries.first()`): the generator cannot know `Foo` is an enum.

### 2. The finding runs with the model's other findings

In `model-completeness.ts`, beside `classFindings` and `namingFindings`, so it fails generation
the same way and is unit-tested without the submodule.

## Enablers and desired changes

- Enabler (behaviour-preserving, provable by the snapshot tests): carry `sample` from model to
  shape; switch the generator from the tables to the samples with the two model files declaring
  them — byte-identical output.
- Desired change: the finding.

## Risks / Trade-offs

- [A sample that does not compile] → the generated test fails to compile, which `check` reports;
  the same as a wrong table entry today.
