# Findings

## F1 - One question round at proposal, on scope

- **What happened:** 2026-11-11, before the proposal. Custom themes already worked as strings, so
  the question "can one register a theme" had neither a clean yes nor a clean no; Oliver was asked
  to choose between documentation only, a typed API, and nothing, and chose documentation only.
- **Cause:** the earlier change `select-a-theme-in-the-profile` had put custom themes out of scope
  with one sentence in the how-to, and no capability recorded whether a typed API was wanted.
- **Cost:** one round, before the ledger run opened, so no wait is recorded.

## F2 - The spec delta would have broken `theme-selection` under `--strict`

- **What happened:** 2026-11-11, at task 3.1. The delta MODIFIED "The documentation states which
  themes exist", growing its text past 500 characters; `openspec validate --strict` warns on that,
  and a dry-run archive would have taken `theme-selection` from passing to failing. Replaced by
  two short ADDED requirements; the existing one stays, its custom-theme clause still true.
- **Cause:** `openspec validate <change> --strict` at proposal passed, because a MODIFIED block is
  not length-checked as a change delta the way the promoted spec is. Only validating the archived
  result shows it.
- **Cost:** about 10 minutes, no wait. Eleven other items already fail `validate --all --strict`
  on `main` for the same warning, so the repo-wide run could not show it either.

## Proposals

One row per finding (house rules §9).

| Finding | Proposal | Status |
|---|---|---|
| F1 | - | none - record only: a scope question with three defensible answers |
| F2 | `openspec validate --all --strict` fails CI, after the ten main specs over 500 characters are shortened, so a delta that grows a requirement past the limit is caught before archive | adopted -> validate-openspec-strictly |
