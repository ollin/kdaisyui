# Tasks

Every step that touches the generator is a TCR run in `codegen/`, preceded by `just generate` and
a check that it left no diff.

**Estimate, frozen 2026-11-01 at proposal, raw and uncorrected:** 90 minutes.

| Section | Minutes |
|---|---|
| 1. Open assumptions | 10 |
| 2. A pure function and the submodule path, under TCR | 25 |
| 3. The snapshot test | 15 |
| 4. Measure, and what survives | 30 |
| 5. Gate and adoption | 10 |

## 1. Open assumptions

- [x] 1.1 `. d` (A1, A2) Generate all 68 in memory in a scratch script, diff, time; read what the
  half touches besides its inputs.

## 2. A pure function and the submodule path, under TCR

- [x] 2.1 `. r (internal)` The pages through `pinnedSubmodule('daisyui')`.
- [x] 2.2 `. r (internal)` `documentedTestFor` returns the file; the two writers only read and write.

## 3. The snapshot test

- [x] 3.1 `. r (internal)` All 68 committed `<Name>Test.kt` reproduced.

## 4. Measure, and what survives

- [x] 4.1 `. d` StrykerJS on `test-generator.ts` before and after (A3); per surviving group, delete
  or kill. The changed lines reach 100 %.

## 5. Gate and adoption

- [x] 5.1 `just generate` without a diff, `node --test`, the mutation gate, CodeScene before the
  push, `openspec validate --strict`, findings, archive, pull request.
