## Why

`csp-safe-popover-anchoring` (archived 2026-11-08) lost most of its time to three planning
gaps, its findings F2, F5 and F6, and Oliver adopted one proposal for each on 2026-11-08:

- **F2:** the first task order would have reverted under root TCR, which runs the E2E suite,
  and a test generator that hard-coded the replaced string was missing from every task. The
  review caught both; about 20 minutes of rewriting.
- **F5:** three tasks were planned `^ F` and counted 12-15 lines, because a changed line counts
  twice and a CSS comment counts. Three bookkeeping commits.
- **F6:** an assumption about a browser feature was confirmed in one engine, with the mechanism
  present, never without it. Built and removed again: blocks 2-3 of that change.

## What Changes

Three rules in `openspec/config.yaml`, which OpenSpec serves with every artifact's
instructions:

- `proposal`: an assumption about a browser or platform feature cites compat data for every
  engine the documentation names, and a control run with the mechanism removed. (F6)
- `tasks`: before ordering, grep the repository for each literal the change replaces and name
  every file that holds it in a task; order the steps so each is green under the TCR run at the
  repository root, E2E included. (F2)
- `tasks`: plan a step's marker from its counted lines - a changed line counts as removed plus
  added, a comment counts in a file type without a line comment - and plan `!` above eight. (F5)

### The subtractive option, stated

Write nothing and rely on the reviewer, who caught F2. It caught neither F5 nor F6, and F6 was
the costly one. A rule is three short entries; no check can carry these, because each is a
judgement about a plan, not a property of a file (house rules §9, order 3).

## Out of scope

- F4 (a check for cited task numbers): rejected by Oliver 2026-11-08.
- F7: carried as tasks in `guarantee-strict-csp` and `compile-the-stylesheet-in-the-plugin`.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | `rules.proposal` and `rules.tasks` in `openspec/config.yaml` reach the agent writing those artifacts | code | to check in task 1.1 - `openspec instructions tasks --change <any> --json` prints them |
| A2 | No requirement of a main spec this builds on contradicts it. It builds on none: it touches only `openspec/config.yaml`, which no spec under `openspec/specs/` describes | code | confirmed at proposal - `grep -rl "config.yaml" openspec/specs` names no rule set for planning |

## Impact

- `openspec/config.yaml` only.
