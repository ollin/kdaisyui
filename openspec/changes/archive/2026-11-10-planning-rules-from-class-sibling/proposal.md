## Why

`name-the-class-sibling-in-every-kdoc` (archived 2026-11-10) had two planning gaps, its findings
F3 and F4, and Oliver adopted one proposal for each on 2026-11-10:

- **F3:** the emitter change and its regeneration were planned as two tasks. The snapshot tests
  compare the emitter with the committed `lib/generated`, so the first task alone cannot go green
  under TCR at the repository root; the two were merged at apply.
- **F4:** the proposal said 129 sentences, copied from a verification of 2026-10-18; the
  regeneration added 135.

## What Changes

Two rules in `openspec/config.yaml`, which OpenSpec serves with every artifact's instructions:

- `tasks`: a step that changes a generator includes the regeneration and the regenerated output
  in the same task. (F3)
- `proposal`: a count stated in a proposal is measured for it, with the command that measured
  it, never copied from an older verification. (F4)

### The subtractive option, stated

Write nothing and rely on the apply step, which caught both. Each cost minutes there, not hours;
but F3 would have been a reverted TCR run, and a wrong count is a false statement in a document
that lasts. A rule is two short entries; no check can carry them, because whether a task bundles
its regeneration or a count was measured is not visible in the artifact's text (house rules §9,
order 3).

## Out of scope

- F5 of the same change: fixed in that change, no rule.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | `rules.proposal` and `rules.tasks` in `openspec/config.yaml` reach the agent writing those artifacts | code | confirmed at proposal - `planning-rules-from-popover-anchoring` task 1.1, and task 2.1 here re-reads the served rules |
| A2 | No requirement of a main spec this builds on contradicts it. It builds on none: it touches only `openspec/config.yaml`, which no spec under `openspec/specs/` describes | code | confirmed at proposal - as in `planning-rules-from-popover-anchoring` A2 |

## Impact

- `openspec/config.yaml` only.
