# Findings

## F1 - The changelog template cannot name the release's tag

- **What happened:** A1 assumed the link could point at `blob/v<version>/docs/upgrading.md`.
  `{{tagName}}` and `{{projectVersion}}` both render empty inside a commit's line.
- **Cause:** JReleaser fills a commit's `format` from commit variables only.
- **Cost:** two probe runs, about 2 minutes; the link points at `main`.

## Proposals

One row per finding (house rules §9). `openspec/config.yaml` declares no `findings:` causes.

| Finding | Proposal | Status | Cause |
|---|---|---|---|
| F1 | - | none - record only; the link points at `main` | template variable not available |
