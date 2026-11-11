# Findings

## F1 - One question round at proposal, on scope

- **What happened:** 2026-11-11, before the proposal. Custom themes already worked as strings, so
  the question "can one register a theme" had neither a clean yes nor a clean no; Oliver was asked
  to choose between documentation only, a typed API, and nothing, and chose documentation only.
- **Cause:** the earlier change `select-a-theme-in-the-profile` had put custom themes out of scope
  with one sentence in the how-to, and no capability recorded whether a typed API was wanted.
- **Cost:** one round, before the ledger run opened, so no wait is recorded.
