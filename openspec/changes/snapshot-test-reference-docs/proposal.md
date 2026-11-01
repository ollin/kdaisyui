## Why

`codegen/src/generator-docs.ts` writes the 66 committed reference pages under `docs/reference/` and
their index, and is the least-tested file left in `codegen/`: 58 surviving mutants at 68.5 % (full
run, 2026-11-01). Its functions are already pure and exported, and since `snapshot-test-generators`
the `codegen-tests` job has every input they need.

## What Changes

- **One snapshot test** reproduces, from the committed models and the pinned DaisyUI pages, every
  committed reference page, `docs/reference/index.md`, `llms-full.txt` and `llms.txt`.
- No production change.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | The pages, the index and both llms files equal what the exported functions produce in memory | probe | verified in task 1.1: 66 pages, the index and both llms files equal, in 107 ms |
| A2 | The test kills most of `generator-docs.ts`'s 58 survivors | measurement | confirmed in task 2.1: 13 of 58 survive; `generator-llms.ts` reached 100 % |

## Capabilities

<!-- skip_specs: no behaviour changes. -->

## Impact

- **New**: `codegen/test/reference-docs-snapshot.test.ts`. Nothing else.
