# Results

Task 3.2. Measured 2026-09-09 on the implementation branch.

## Code Health, the files this change touched

| File | Before | After |
|---|---|---|
| `codegen/src/test-generator.js` | **8.26** (yellow) | **10.0** (optimal) |
| `codegen/src/test-generator-heroicons.js` | 10.0 | 10.0 |

`analyze_change_set` reports `test-generator.js` as **improved**, with every finding
`fixed` rather than merely reduced:

| Category | Fixed | Was |
|---|---|---|
| Complex Method | `parseTestCases`, `buildClassMappings`, `main` | cc 13 / 11 / 10, threshold 9 |
| Complex Conditional | `parseTestCases` | 2 expressions, threshold 2 |
| Bumpy Road Ahead | `parseTestCases`, `main` | 3 bumps / 2 bumps |
| Deep, Nested Complexity | `parseTestCases`, `buildClassMappings` | depth 4, threshold 4 |

Not one byte of generated output changed while doing it, checked by regenerating after every
task and requiring an empty diff.

## Tests

19 tests in `codegen/test/`, ~55 ms, no npm dependency — `node:test` ships with the Node
pinned in `.tool-versions`. Two of them exist because a defect was found, not to describe
intent (see below). Run with `./gradlew :lib:testCodegen` or `npm test` in `codegen/`; CI runs
them as the `codegen-tests` job.

## Defects found by writing the tests

Neither was known when the change was proposed. Both were reproduced with a failing test
before being fixed.

1. **`test-generator-heroicons.js` wrote into the hand-written tree.** `DEFAULT_OUTPUT_DIR`
   resolved to `lib/src/test/…` instead of `lib/generated/test/…`. Reachable via
   `npm run generate:heroicon-tests` — which is the command the generator prints into the
   header of its own output. Hidden because Gradle always passes `--output-dir`.
2. **`parseTestCases` silently dropped a test case** when a `### ~heading` sat inside an
   unclosed fence: the case was flushed with an empty body and its real content was lost.
   An empty case reaches a generated Kotlin test that asserts nothing. **Measured impact on
   current input: none** — no doc page at the pinned DaisyUI tag triggers it.

## The change-set gate FAILED, and not because of this change

`analyze_change_set` against `origin/main` returns `quality_gates: failed`. Every degradation
is in `lib/generated/**`, and the category is always **Code Duplication**:

> `ModalCoverageTest`: introduced similar code in `modalAction_defaults`,
> `modalBackdrop_defaults`, `modalBox_defaults`, `modalPopover_defaults` and 2 more

**Cause: `add-mutation-testing`, not this change.** Tasks 4.1 and 4.2 taught the generator to
append the same two assertions — element-closed, attributes-present — to every generated
`*_defaults` and `*_all_flags` test. Uniform assertions across 66 components are *structurally*
uniform by construction. Roughly a dozen files degraded, a handful improved, and the branch
carries both changes, so the scan cannot separate them by commit.

This is not a defect to fix here, and it is worth being precise about why:

- **The duplication is in generated code.** Nobody edits it; `just generate` rewrites it
  wholesale. The maintenance cost Code Duplication measures — change one copy, forget the
  others — cannot occur, because there is exactly one place to change and it is the generator.
- **Removing it would make the tests worse.** The obvious fix is a shared helper in the
  generated file, which is the thing that makes a failing generated test hard to read.

Three options, for a decision that belongs to `add-mutation-testing` or a change of its own:

1. **Exclude `lib/generated/**` from Code Duplication** via `.codescene/code-health-rules.json`
   with a `matching_content_path`. No such file exists yet — this would create it. Mirrors what
   the Kover config already does for generated and bridge code, on the same reasoning.
2. **Exclude `lib/generated/**` from analysis entirely.** Simpler, and loses the signal for
   genuinely bad generator output.
3. **Accept a failing gate on this branch** and record why. Honest, but a gate that is expected
   to fail stops being read — the same failure mode as the stale CI job counts in the skills.

Option 1 is the recommendation: it is the narrowest, and it states the actual reason rather
than hiding the whole tree.

## Full verification

- `./gradlew check --warning-mode all` — BUILD SUCCESSFUL, `Problems: 0`, e2e included,
  `koverVerify` at 100% line and branch, `checkKotlinAbi` unchanged.
- `./gradlew :lib:testCodegen` — 19 tests green.
- All four generator tasks re-run against an already-generated tree — no diff, so generation
  is idempotent and `generated-sources-drift` stays green.
