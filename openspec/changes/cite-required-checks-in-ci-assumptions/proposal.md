## Why

`fail-ci-on-an-unparsable-openspec-config` (archived 2026-11-08) planned a check as a step of the
`scope` job, assuming a failing `scope` blocks the merge. It does not: `scope` is not a required
check of the `main` ruleset, and a red `scope` makes every test job run. Its finding F1 cost a
question round and a revision before any code. Oliver adopted its proposal on 2026-11-08.

## What Changes

- One rule under `rules.proposal` in `openspec/config.yaml`: an assumption that a CI step or job
  blocks a merge cites the `main` ruleset's required status checks, read for the proposal.

### The subtractive option, stated

None: the rule is the cheapest carrier. No check can read whether a proposal's CI assumption was
checked against the ruleset (house rules §9, order 3).

## Out of scope

- The other two proposals adopted with it: F6 of `compile-the-stylesheet-in-the-plugin` belongs
  to `chezmoi-openspace-dotfiles`; F5 of `guarantee-strict-csp` is its own change,
  `list-data-urls-in-the-daisyui-bump-report`.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | `openspec instructions proposal` serves a new `rules.proposal` entry | code | confirmed at proposal - `planning-rules-from-popover-anchoring` task 3.1 saw its F6 rule served the same way |
| A2 | No requirement of a main spec this builds on contradicts it. It builds on none: it touches only `openspec/config.yaml`, which `openspec-config-check` requires to parse | code | confirmed at proposal - `openspec-config-check` asks only that the file parses; the CI job checks that |

## Impact

- `openspec/config.yaml` only.
