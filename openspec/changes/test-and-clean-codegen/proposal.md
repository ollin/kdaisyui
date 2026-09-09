# Test and clean the codegen

## Why

`codegen/` has never had a test of its own. Its only safety net is CI's
`generated-sources-drift` job, which regenerates and fails on any diff.

That job is an unusually good oracle — total, mechanical, and impossible to fool — but it
has one property that matters here: **it tells you the output changed, never which function
changed it.** On a 844-line file with three functions above the complexity threshold, that
is the difference between a five-minute fix and an afternoon.

CodeScene scores `codegen/src/test-generator.js` at **8.26**:

| Smell | Function | Detail |
|---|---|---|
| Complex Method | `parseTestCases` | cc = 13 (JS threshold 9) |
| Complex Method | `buildClassMappings` | cc = 11 |
| Complex Method | `main` | cc = 10 |
| Deep Nested Complexity | `parseTestCases`, `buildClassMappings` | depth 4 |
| Bumpy Road | `parseTestCases` (3 bumps), `main` (2) | |
| Complex Conditional | `parseTestCases:80` | 2 expressions |

The house rule is that a file you touch is left healthier than you found it. `add-mutation-testing`
touched this file twice, so this is that debt being paid rather than new appetite.

## What Changes

- Make the module importable: `main()` currently runs at import time and nothing is exported,
  so no test can reach any function. **This is the blocker, and it is why the generator has no
  tests — not neglect.**
- Add unit tests using Node's built-in `node:test`. Zero new dependencies: `codegen/package.json`
  declares none today and that stays true.
- Refactor the three flagged functions, tests first, verifying byte-identical regeneration at
  every step.
- Run the tests in CI as their own job.

## Impact

- **`./gradlew check` must stay Node-free.** `AGENTS.md` promises a clone builds and tests with
  no Node, no npm and no submodules; only `just generate` needs them. These tests therefore run
  as a separate CI job alongside `generated-sources-drift`, never bound to `check`.
- No change to any generated output. **Verified** at each step by regenerating and requiring an
  empty diff — the same check CI runs.
- No new dependency. **Verified** by `node:test` being part of the Node runtime pinned in
  `.tool-versions` (26.8.1).
- **Assumed:** that the three flagged functions can be brought under the complexity threshold
  without changing output. Falsified if a refactoring cannot be expressed without altering
  emitted text — in which case the honest answer is to stop and record why, not to force it.

## Relationship to the TypeScript migration

Decided separately: `codegen/` moves to TypeScript as its own change, after `add-mutation-testing`
archives. These tests are not a prerequisite for that port — drift is already a stronger oracle for
a mechanical translation — but they make the *shape* of the code better before it is translated,
which is cheaper than translating and then refactoring.
