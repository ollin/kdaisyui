# openspec-config-check Specification

## Purpose
A broken `openspec/config.yaml` is not an error to the OpenSpec CLI: it warns on stderr, ignores
the file and serves no project rules, so every planning rule silently stops reaching the agents.
CI makes that a failing, required check instead.

## Requirements

### Requirement: CI fails when the OpenSpec config does not parse

Every CI run of a pull request and of a push to `main` SHALL fail when `openspec/config.yaml` is not
valid YAML, and its log SHALL name the file and the parser's error.

**Verified**: an unparsable file silently drops every project rule: finding F1 of `planning-rules-from-popover-anchoring`, `openspec instructions tasks` served 0 rules.

**Assumed**: the runner's `yq` rejects that case (A1). *Wrong if:* task 1.1's control passes the broken fixture.

#### Scenario: A planning-only pull request breaks the file

- **WHEN** a pull request changes only `openspec/config.yaml` and leaves it unparsable
- **THEN** its required `openspec-config` check fails and names `openspec/config.yaml`
- **AND** the pull request cannot merge

#### Scenario: The check can fail

- **WHEN** the control runs the check on a file with F1's broken line
- **THEN** the check fails, and on the good file it passes
