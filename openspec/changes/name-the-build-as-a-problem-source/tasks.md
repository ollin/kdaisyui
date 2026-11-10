# Tasks

**Estimate, frozen 2026-11-10:** `35min (1h DevOnly)` by the ledger's `forecast`; 60 minutes
developer-only is the frozen raw figure. Block 1: 30, block 2: 30.

## 1. Refactoring, under TCR at the repository root

- [x] 1.1 `^ r (internal)` `Source.Plugin` / `Source.ThisBuild` replace `pluginId: String?` in
  `GradleProblem`, the reader, the allowlist and the message; the tests' constructors follow, their
  assertions unchanged (A1).
- [x] 1.2 `^ r (internal)` `ReportEntry` replaces `entriesOf`, `idOf` and `nameOf`; tests untouched.

## 2. Gate and archive

- [ ] 2.1 `check` with E2E, `checkGradleProblems`, `:build-checks:pitest` at 100 % strength,
  CodeScene on the changed files, `openspec validate --strict`.
- [ ] 2.2 Archive on this branch, `. d (internal)`.
