# Findings

## F1 - A failing step in `scope` would not block the merge (A2 false)

- **What happened:** task 1.2, 2026-11-08. `ci-job-selection` makes every test job run when
  `scope` fails, and `scope` is not among the `main` ruleset's required checks
  (`generated-sources-drift`, `unit-tests`, `e2e-tests`, `Validate PR title`, `api-baseline`,
  `codegen-tests`, `mutation-tests`). So the planned check would turn `scope` red, every test job
  would run green, and the pull request would stay mergeable. The same spec also states that
  planning files are read by no job.
- **Cause:** the proposal took "the one job a planning-only pull request runs" for "a job that
  blocks", without reading the ruleset.
- **Cost:** a question round with Oliver on where the check runs, and whether the ruleset changes.

## Proposals

One row per finding (house rules §9).

| Finding | Proposal | Status |
|---|---|---|
| F1 | A rule in `openspec/config.yaml` for `proposal`: an assumption that a CI step blocks a merge cites the `main` ruleset's required checks | open |
