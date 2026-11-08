## Why

`planning-rules-from-popover-anchoring` (archived 2026-11-08) committed an `openspec/config.yaml`
that no longer parsed: a `: ` inside a rule's prose made YAML read it as a key. Its finding F1
probed what follows: `openspec instructions` prints `Warning: could not parse … ignoring it` on
stderr and serves **no** project rules at all, and `openspec validate --all --strict` does not fail.
Every rule in that file - what "green" means here, the commit cadence, the planning rules - would
silently stop reaching the agents that plan and implement. Oliver adopted a check on 2026-11-08.

## What Changes

- **`.github/openspec-config/check.sh`** parses `openspec/config.yaml` and fails with the parser's
  message when it does not parse.
- **`.github/openspec-config/check.test.sh`** proves it: a good file passes, the broken line of F1
  fails, in the style of `.github/ci-scope/classify.test.sh`.
- **The `scope` job runs both**, before it classifies. It is the one job a pull request that
  touches only `openspec/` runs (`ci-job-selection`), so it is the only place that sees such a
  pull request.

### The subtractive option, stated

Nothing in CI; the agent runs `openspec instructions` and reads stderr. That is what failed in F1:
the warning is one line among JSON on another stream. A check is the house rules' first choice.

## Out of scope

- **Validating the file's schema** (unknown keys, wrong types). The failure seen was a parse error;
  the CLI's own schema handling is unread.
- **Other YAML in the repository.** One file has this consequence.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | The runner's `yq` (mikefarah 4.54.1, `ubuntu-26.04` image 20261101.186) rejects F1's broken line as the CLI's parser does | code | to check in task 1.1 - locally only the Python `yq` 0.0.0 (PyYAML) was available: exit 1 on the broken line, 0 on the good file |
| A2 | No requirement of a main spec this builds on contradicts it. It builds on `ci-job-selection` (the `scope` job runs for every pull request; planning-only pull requests run nothing else) | code | to check in task 1.2 |

## Capabilities

### New Capabilities

- `openspec-config-check`: CI fails when `openspec/config.yaml` does not parse.

## Impact

- New: `.github/openspec-config/check.sh`, `.github/openspec-config/check.test.sh`.
- Edited: `.github/workflows/ci.yml`, the `scope` job.
