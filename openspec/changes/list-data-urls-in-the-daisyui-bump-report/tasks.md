# Tasks

**Estimate, frozen 2026-11-08:** `20min (35min DevOnly)` by the ledger's `forecast`; 35 minutes developer-only. Block 1: 5, block 2: 20, block 3: 10.

## 1. Open assumptions

- [x] 1.1 `. d (internal)` (A1, A2) Read `src/submodules.ts` and `content-security-policy`; record.

## 2. The check

- [x] 2.1 `! F (internal)` The test in `codegen/test/`, with its control: a component removed from
  the README's list fails it.

## 3. Gate and archive

- [x] 3.1 Full green, StrykerJS on any changed `codegen/src` line, `openspec validate --strict`,
  the evaluation, archive.
