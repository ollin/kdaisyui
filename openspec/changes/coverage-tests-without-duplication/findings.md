# Findings

## F1 - The class siblings' all-flags tests were a fourth duplication

- **What happened:** task 2.4's measurement. After the three planned moves, 63 of 66 coverage tests
  scored 10.0. Menu and Timeline stayed at 9.38: their class siblings' all-flags tests take no escape
  hatches, so task 2.2 left them written out, and they were identical apart from the call. The
  probe's four files had not shown it, because none of them has three or more siblings.
- **Cause:** the probe covered four components, chosen for their findings, not for every shape.
- **Cost:** one more task (2.3a), about 5 minutes.

## F2 - The generator itself crossed the argument threshold

- **What happened:** task 3.1. `analyze_change_set` rated `test-generator.ts` degraded:
  `expectedValue` took five arguments. Its two flags became one options object; regeneration was
  unchanged.
- **Cause:** a flag added in task 2.1 for the all-flags case.
- **Cost:** about 3 minutes.

## Proposals

One row per finding (house rules §9).

| Finding | Proposal | Status |
|---|---|---|
| F1 | - | fixed - in this change (task 2.3a) |
| F2 | - | fixed - in this change |
