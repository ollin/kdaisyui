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

## Rejected alternative: Approvals.NodeJS

Considered and analysed 2026-09-09 (Apache-2.0, 125 stars, actively maintained; Mocha and
Jest integrations, plus a runner-agnostic `approvals.verify(dirName, testName, data)`).

**Rejected, because this repository already has the approval test that matters.**
`lib/generated/**` committed alongside CI's `generated-sources-drift` job *is* the
golden-master pattern: expected output under version control, build fails on any diff. Its
oracle is stronger than anything a library would give us here — the entire pipeline output,
66 components × 2 files plus 324 icons, byte for byte. Adding Approvals.NodeJS would be a
second, weaker copy of a check that already passes.

Four further costs, in descending order:

1. It would be codegen's **first npm dependency**. `package.json` declares none today, and
   that is load-bearing for the promise that a clone needs no Node.
2. Its documented paths add a second test runner (Mocha or Jest) where `node:test` ships
   inside the pinned Node 26 runtime at zero cost.
3. The functions being tested are the wrong shape for it. `parseTestCases` returns
   `[{name, html}]`; an expectation that small belongs in the test where a reviewer sees it,
   not in a separate approved file.
4. Default reporters launch GUI diff tools, so CI needs `gitdiff`/`donothing`, plus
   `*.received.*` ignored and `eol=lf` pinned in `.gitattributes`.

**Where it would be the right tool**, if this comes up again: pinning per-component generated
Kotlin snippets as documents — `generateKotlinTest("button", …)` across a 66-case matrix — is
genuinely document- and matrix-shaped, which is approval testing's home ground. Drift already
*detects* every such regression, so the marginal value is **localisation**, not detection.
Revisit only if the refactorings in section 2 prove hard to verify function-by-function.

## This change cannot merge without `add-mutation-testing`

Decided 2026-09-09, after checking rather than assuming.

Task 1.1 exports `parseEmittedBuilder`, `htmlTagForFn`, `parseAttrProps`, `attrAssert` and
`closesTagAssert` — every one of them created by `add-mutation-testing` tasks 4.1 and 4.2. So
this change's first commit does not apply without that change's commits, and cherry-picking it
onto `main` fails.

That rules out the split it would otherwise invite: `test-and-clean-codegen` is complete while
`add-mutation-testing` is not. Merging the finished one first would also break the house rule
that a change's archived OpenSpec state travels in the **same** merge request as its code —
`add-mutation-testing`'s code would land in one MR and its archive in another.

**Both changes therefore archive on the same branch and merge in one request.** The coupling is
an artefact of doing them back to back on one branch, not something either change needs.

## Relationship to the TypeScript migration

Decided separately: `codegen/` moves to TypeScript as its own change, after `add-mutation-testing`
archives. These tests are not a prerequisite for that port — drift is already a stronger oracle for
a mechanical translation — but they make the *shape* of the code better before it is translated,
which is cheaper than translating and then refactoring.
