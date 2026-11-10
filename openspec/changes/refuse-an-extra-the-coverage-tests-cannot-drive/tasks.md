# Tasks

**Estimate, frozen 2026-11-10:** `1h 5min (2h DevOnly)` by the ledger's `forecast`; 120 minutes
developer-only is the frozen raw figure. Ledger run
`2026-11-10-kdaisyui-refuse-an-extra-the-coverage-tests-cannot-drive`.

## 1. Open assumptions

- [ ] 1.1 A2: read the path from `ModelExtraParameter` to `classifyParam`; record in the A2 row
  which fields reach the coverage generator, `. d`. If `sample` cannot pass without a second
  shape, revise the design first.

## 2. Under TCR (codegen at the root, regenerating first)

- [ ] 2.1 `^ r (internal)` `sample` is an optional field of `ModelExtraParameter` and reaches the
  shape; `model.test.ts` asserts it passes through.
- [ ] 2.2 `^ r (internal)` `button.json` and `theme-controller.json` declare their samples.
- [ ] 2.3 `^ r (internal)` The coverage generator reads `sample` and the extra's `imports`; the two
  tables are deleted; `just generate` leaves `lib/generated/test/**` unchanged (A3).
- [ ] 2.4 `^ F (internal)` `extraSampleFindings(model)` in `model-completeness.ts` fails a nullable
  extra of another type without `sample`; unit test with a fixture model.
- [ ] 2.5 `. d (internal)` `kdaisyui-codegen` skill: the model file section names `sample`, and the
  paragraph about `EXTERNAL_ENUM_VALUES` is replaced.

## 3. Gate, adoption and archive

- [ ] 3.1 Full green: `check`, `:lib:testCodegen`, StrykerJS on the changed `codegen/src/` lines,
  `just generate` clean, CodeScene change set.
- [ ] 3.2 The evaluation under `tmp/refuse-an-extra-the-coverage-tests-cannot-drive/`.
- [ ] 3.3 After adoption: archive on the implementation branch, Proposals table, `. d (internal)`.
