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

## Proposals

| Finding | Proposal | Status |
|---|---|---|
| F1 | - | none - record only |
| F2 | - | none - fixed in this change |
| F3 | - | none - recorded in `stryker.config.mjs` |
