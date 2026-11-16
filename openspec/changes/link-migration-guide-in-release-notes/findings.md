# Findings

## F1 - The changelog template cannot name the release's tag

- **What happened:** A1 assumed the link could point at `blob/v<version>/docs/upgrading.md`.
  `{{tagName}}` and `{{projectVersion}}` both render empty inside a commit's line.
- **Cause:** JReleaser fills a commit's `format` from commit variables only.
- **Cost:** two probe runs, about 2 minutes; the link points at `main`.
