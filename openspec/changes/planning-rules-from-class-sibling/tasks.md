# Tasks

**Estimate, frozen 2026-11-10:** `20min (30min DevOnly)` by the ledger's `forecast` (ratio 0.53
over 174 runs); 30 minutes developer-only is the frozen raw figure, one block.

## 1. The rules

- [x] 1.1 `. d (internal)` The `tasks` rule of F3 in `openspec/config.yaml`.
- [x] 1.2 `. d (internal)` The `proposal` rule of F4.

## 2. Gate and archive

- [ ] 2.1 `config.yaml` parses; `openspec validate planning-rules-from-class-sibling --strict`;
  both rules appear in `openspec instructions proposal|tasks --json`.
- [ ] 2.2 Archive on this branch, `. d (internal)`, with the statuses of F3 and F4 in
  `archive/2026-11-10-name-the-class-sibling-in-every-kdoc/findings.md`.
