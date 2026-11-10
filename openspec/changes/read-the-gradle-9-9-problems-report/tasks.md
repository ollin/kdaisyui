# Tasks

**Estimate, frozen 2026-11-10:** `40min (1h 15min DevOnly)` by the ledger's `forecast` (ratio 0.53
over 175 runs); 75 minutes developer-only is the frozen raw figure. Block 1: 30, block 2: 15,
block 3: 20, block 4: 10.

## 1. The reader, under TCR at the repository root

- [ ] 1.1 `^ B (internal)` The listed diagnostics end at `id="problems-summary"` as well as at
  `"problemsReport"`. `GradleProblemTest` reads a 9.9-shaped report whose summary repeats a listed
  problem, and expects that problem once, with its plugin.
- [ ] 1.2 `! B (internal)` A problem's id is its `documentationLink` anchor where it has one; a
  summary still matches a listed problem by name. `GradleProblemTest` asserts the anchor, the
  fallback, and the summary match. In the same step, `gradle/allowed-gradle-problems.txt` names
  `deprecate-visible-property` for both plugins.

## 2. Docs

- [ ] 2.1 `. d (internal)` `kdaisyui-build`: the accepted problem is named by its anchor, and why.

## 3. Gate

- [ ] 3.1 `./gradlew check` with E2E, `:build-checks:checkGradleProblems` green on 9.8.2, the same
  on the Renovate branch with this change applied (9.9.0), `:build-checks:pitest` at 100% test
  strength, CodeScene on the changed files.
- [ ] 3.2 `openspec validate read-the-gradle-9-9-problems-report --strict`; the evaluation under
  `tmp/read-the-gradle-9-9-problems-report/`.

## 4. Archive

- [ ] 4.1 After adoption: archive on this branch, `. d (internal)`, `tmp/` of the change deleted.
