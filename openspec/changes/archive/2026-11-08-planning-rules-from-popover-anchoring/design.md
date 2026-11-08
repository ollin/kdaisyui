## Context

See proposal.md. The three rules join the existing `rules.proposal` and `rules.tasks` lists.

## Decisions

### 1. Rules, not a check

**Chosen:** entries under `rules:`. **Rejected:** a validator. Whether a plan names every file
holding a replaced literal, orders steps by what stays green, or tested a feature in every engine
is not visible in the artifact's text.

### 2. One rule per finding, worded as the action

Each rule says what to do, not what went wrong, so it reads as an instruction when served.
