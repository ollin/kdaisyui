# Tasks

**Estimate, frozen 2026-11-10:** `1h 5min (2h DevOnly)` by the ledger's `forecast`; 120 minutes
developer-only is the frozen raw figure. Ledger run
`2026-11-10-kdaisyui-refuse-an-extra-the-coverage-tests-cannot-drive`.

## 1. Open assumptions

None open in the code: A2 was settled at proposal by the review of 2026-11-10; A3 is checked by
task 2.5, which deletes the tables only when the output stays byte-identical.

## 2. Under TCR (codegen at the root, regenerating first)

- [x] 2.1 `^ F (internal)` `ModelExtraParameter.sample`; `parameterShape` copies `sample` and the
  extra's `imports` (as `sampleImports`) onto the shape only when a sample is declared;
  `mainFunctionConfig` strips `sample`. `model.test.ts`: an extra with a sample carries both, one
  without carries neither and stays deep-equal to today's shape.
  Done in two runs (`! F` 9 lines: the shape carries `sample`; `^ F`: the config strips it). The
  extra's imports move to 2.4, the step that reads them, so no unread field is added here.
- [x] 2.2 `. r (internal)` `button.json` and `theme-controller.json` declare `"sample"`. Nothing
  reads it in generation yet: `committed-model.test.ts` stays green and `just generate` leaves the
  tree clean.
- [x] 2.3 `^ F (internal)` `classifyParam` receives `sample`; a rule after the generated-enum rule
  classifies a nullable extra with a sample, and the all-flags arguments pass it. Tables still
  present. `coverage-test-generator.test.ts` (`coverageOf`): a nullable `Foo?` with sample
  `Foo.Bar` is passed `Foo.Bar`; a nullable `Foo?` without a sample is not; a non-nullable extra
  with a sample is not.
- [x] 2.4 `^ F (internal)` The coverage test imports `sampleImports` of sampled extras; the fixture
  asserts the import, and that an unsampled extra's `imports` do not appear.
- [x] 2.5 `! F (internal)` (planned `^ r`) Delete `EXTERNAL_ENUM_VALUES`, `EXTERNAL_ENUM_IMPORTS` and their rule;
  `just generate` leaves `lib/generated/test/**` byte-identical (A3), the snapshot tests stay green.
  Not a pure refactoring after all: the theme change's test that drove `DaisyTheme?` WITHOUT a
  sample asserted exactly the table behaviour removed here, so it was deleted (finding F2).
- [x] 2.6 `^ F (internal)` `extraSampleFindings(model)`: a nullable extra that is not `String`,
  `Boolean` or a `model.enums` type (bare or in `ClassValues<…>`) and has no sample yields
  `<component>: <function>.<parameter> needs a sample`; fixtures for each exemption;
  `committed-model.test.ts` asserts no finding for the committed models.
- [ ] 2.7 `^ F (internal)` `index-new.ts` pushes the finding onto `modelFindings`; checked by hand
  once with a scratch model without a sample (entry points are outside the mutation gate), the
  output quoted in the evaluation.
- [ ] 2.8 `. d (internal)` `.opencode/skills/kdaisyui-codegen/SKILL.md`: the model file section
  names `sample`; the paragraph on `EXTERNAL_ENUM_VALUES` is replaced. A grep found the table names
  nowhere else.

## 3. Gate, adoption and archive

- [ ] 3.1 Full green: `check`, `:lib:testCodegen`, StrykerJS on the changed `codegen/src/` lines
  (the predicates of 2.3, 2.4 and 2.6 each pinned by a case above), `just generate` clean,
  CodeScene change set.
- [ ] 3.2 The evaluation under `tmp/refuse-an-extra-the-coverage-tests-cannot-drive/`.
- [ ] 3.3 After adoption: archive on the implementation branch, Proposals table, `. d (internal)`.
