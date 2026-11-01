# Tasks

Every step that touches `test-generator.ts` is a TCR run in `codegen/`, preceded by `just generate`
and a check that it left no diff: the Bun suite alone cannot see a changed output.

**Estimate, frozen 2026-11-01 at proposal, raw and uncorrected:** 120 minutes.

| Section | Minutes |
|---|---|
| 1. Open assumption | 10 |
| 2. A pure coverage function, under TCR | 40 |
| 3. The snapshot test and its measurement | 30 |
| 4. The survivors that remain | 30 |
| 5. Gate and adoption | 10 |

## 1. Open assumption

- [ ] 1.1 `. d` (A2) Read every function the coverage half calls and list what it reads besides
  the component source. Revise the plan if it reads the model, the frontmatter or the submodule.

## 2. A pure coverage function, under TCR

- [ ] 2.1 `. r (internal)` `coverageTestFor(className, source)` returns the file's Kotlin;
  `generateCoverageForFile` reads, calls it, writes.
- [ ] 2.2 `. r (internal)` Export it beside the parsers, for tests only.

## 3. The snapshot test and its measurement

- [ ] 3.1 `. F (internal)` The snapshot test over all 66 committed component files. Record its
  duration (A4).
- [ ] 3.2 `. d` StrykerJS on `test-generator.ts`, before and after; record per function what
  survives (A3).

## 4. The survivors that remain

- [ ] 4.1 For each surviving group: delete code that cannot matter, or add the case that kills it,
  one commit each. Survivors in the documented-element half stay, as out of scope.

## 5. Gate and adoption

- [ ] 5.1 `just generate` without a diff, `node --test`, the CI gate locally,
  `openspec validate --strict`, findings, archive on the branch, pull request.
