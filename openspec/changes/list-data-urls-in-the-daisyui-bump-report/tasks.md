# Tasks

**Estimate:** to be made when the change is applied (proposed 2026-11-08, not started).

## 1. Open assumptions

- [ ] 1.1 `. d (internal)` (A1, A2) Read `src/submodules.ts` and `content-security-policy`; record.

## 2. The check

- [ ] 2.1 `! F (internal)` The test in `codegen/test/`, with its control: a component removed from
  the README's list fails it.

## 3. Gate and archive

- [ ] 3.1 Full green, StrykerJS on any changed `codegen/src` line, `openspec validate --strict`,
  the evaluation, archive.
