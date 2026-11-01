# Findings

## F1 - generator-docs.ts drops from 58 survivors to 13, generator-llms.ts to none

- **What happened:** task 2.1. The 13 left are branches the pinned DaisyUI pages never reach: the
  sort comparator's tie and order cases (no two components share a lower-cased name), the
  first-sentence fallback on descriptions that end in a period, and the page text of a structural
  wrapper without a class. A case for each would be a hand-built shape fixture; left for the
  changed-line gate to collect when those lines next change.

## Proposals

| Finding | Proposal | Status |
|---|---|---|
| F1 | - | none - recorded |
