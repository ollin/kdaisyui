# Findings

## F1 - The four generator files drop from 464 survivors to 38

- **What happened:** task 4.1. With every component and icon compared against its committed file:

  | File | Before | After |
  |---|---|---|
  | `generator-new.ts` | 199 | 16 |
  | `generator-heroicons.ts` | 101 | 10 |
  | `test-generator-heroicons.ts` | 88 | 2 |
  | `parser/svg-heroicons.ts` | 76 | 10 |

  The snapshot test file runs 393 tests in about 0.15 s (A1).

## F2 - The generators found the submodules relative to their own file

- **What happened:** task 1.1. `frontmatter.ts` and the two Heroicons entry points resolved
  `../../daisyui` and `../../heroicons` from their source file. Inside StrykerJS's sandbox under
  `codegen/.stryker-tmp/` that points nowhere, so the snapshot tests would have died in the dry run.
- **Cost:** one helper, `src/submodules.ts`, and three refactorings; the two entry points lost
  their own copies of the four Heroicons directories and of the existence check.

## F3 - The icon test generator reads the SVGs, not the committed icons (A3)

- **What happened:** task 1.2. Assumed to be a pure function of the committed icon files; it parses
  the Heroicons SVGs like the icon emitter. With the submodule in the job it is tested the same way.

## F4 - Entry-point code in a generator module fails the changed-line gate

- **What happened:** task 4.1. `main()` of `test-generator-heroicons.ts` wrote the file and logged;
  no unit test runs that, so its new lines survived. Moved to `index-heroicon-tests.ts`, which the
  gate leaves out by design; the Gradle task and the npm script call it.

## F5 - CodeScene's pre-push check caught a regression in a file the change only touched

- **What happened:** the first use of the new pre-push step. `analyze_change_set` reported
  `svg-heroicons.ts` degraded to 9.38 for code duplication between `processOutlineSvg` and
  `processSolidSvg`, code this change had not edited. Refactored to one `svgPaths` with the two
  rewrites as its only difference: 10.0, change set passed. Two tests then covered what no pinned
  icon does: two paths, and a missing `<svg>` wrapper.

## Proposals

| Finding | Proposal | Status |
|---|---|---|
| F1 | The documented-element half of `test-generator.ts`, now testable the same way | open - its own change |
| F2 | The remaining `../../daisyui` paths in `index-daisyui-delta.ts` and `test-generator.ts` through `submodules.ts` | open - with the documented-element change, which needs it |
| F3-F5 | - | none - fixed in this change |
