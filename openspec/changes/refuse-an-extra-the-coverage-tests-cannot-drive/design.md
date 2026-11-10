## Context

See proposal.md. `test-generator.ts` holds `EXTERNAL_ENUM_VALUES` and `EXTERNAL_ENUM_IMPORTS`; `select-a-theme-in-the-profile` added `DaisyTheme` to both.

## Decisions

### 1. The sample lives in the model

**Chosen:** an optional `sample` on `ModelExtraParameter` only. `parameterShape` copies it, and the
extra's `imports` as `sampleImports`, onto an extra's `ParameterShape` **only when a sample is
declared**, spread in so that every existing shape stays deep-equal. `mainFunctionConfig` strips
`sample`, as it strips `kind` and `writes`, so nothing reaches the Kotlin emitter. **Rejected: keep the tables and add a check** (proposal, subtractive option).
**Rejected: derive a value** (`Foo.entries.first()`): the generator cannot know `Foo` is an enum.

### 2. The finding runs with the model's other findings

`extraSampleFindings(model)` in `model-completeness.ts`, pushed onto `modelFindings` in
`index-new.ts` beside `classFindings` and `namingFindings`, so it fails generation the same way;
the message is `<component>: <function>.<parameter> needs a sample`. A type counts as a generated
enum when it names one of `model.enums`, bare or inside `ClassValues<…>`. `committed-model.test.ts`
asserts the committed models yield none, so the finding cannot be red on `main`.

### 3. Precedence

The coverage generator's rules keep their order: `Boolean`, `id`, `text`, nullable `String`, a
generated enum, then a sampled extra. A sample on a type an earlier rule drives is ignored, which
the finding mirrors.

## Enablers and desired changes

- Enablers: deleting the tables once the samples drive the same arguments (2.5), provable by the
  snapshot tests and `just generate`.
- Desired changes, each its own behaviour: the shape carries the sample (2.1), the generator passes
  it (2.3) and imports for it (2.4), the finding (2.6) and its wiring (2.7).

## Risks / Trade-offs

- [A sample that does not compile] → the generated test fails to compile, which `check` reports;
  the same as a wrong table entry today.
