## Context

See proposal.md. The two rules join the existing `rules.proposal` and `rules.tasks` lists.

## Decisions

### 1. Rules, not a check

**Chosen:** entries under `rules:`. **Rejected:** a validator. Whether a task bundles its
regeneration, or a count was measured rather than copied, is not visible in the artifact's text.

### 2. One rule per finding, worded as the action

Each rule says what to do, not what went wrong, so it reads as an instruction when served. Each
names its source finding, as the existing rules do.
