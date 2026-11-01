# Findings

## F1 - A rewrite assumed every path has two segments

- **What happened:** the second refactoring of `mutation-scope.ts` was reverted. A path check by
  destructuring `file.split('/')` threw on `stryker.config.json`, which has one segment.
- **Cause:** the step rewrote three things at once.
- **Cost:** one revert, about 2 minutes; redone as three single refactorings.

## F2 - The gate's own module shipped with 14 survivors at first

- **What happened:** the first StrykerJS run on `mutation-scope.ts`, the module that computes the
  gate's ranges, left 14 of 53 mutants alive: anchors no test needed, optional chaining on regex
  groups that are always present, an `undefined` guard another check already covered.
- **Cause:** tests written for the behaviour, not against the code's every decision; the same gap
  the gate exists to close.
- **Cost:** about 10 minutes. Six tests and three refactorings removed or pinned each one: 54 of
  54 killed. Half of the survivors were code that could not matter, as `AGENTS.md` warns.

## F3 - StrykerJS has no command-line flag for the break threshold

- **What happened:** task 4.1. `stryker run --help` lists none, so the JSON config became
  `stryker.config.mjs`, reading `STRYKER_BREAK`.
- **Cost:** a few minutes, and one module instead of a JSON file.

## F4 - One process per test file made CI mutation ten times slower

- **What happened:** task 4.2, the first CI run of this pull request. 54 mutants took 77 s on a
  four-core runner, against about 7 s locally. The command runner reruns `node --test test/` per
  mutant, and Node starts one process per test file: 19 per mutant, four mutants at a time.
- **Cause:** process start-up under contention, not the tests, which take 0.2 s.
- **Cost:** about 5 minutes. `--test-isolation=none` in Stryker's command only: locally 7.3 s to
  3.3 s for the same 54 kills, in CI 81 mutants in 13 s. A4 failed by its own falsifier first and
  holds now.

## Proposals

| Finding | Proposal | Status |
|---|---|---|
| F1 | - | none - record only |
| F2 | - | none - fixed in this change |
| F3 | - | none - recorded in `stryker.config.mjs` |
| F4 | - | none - fixed in this change, and recorded in `stryker.config.mjs` |
