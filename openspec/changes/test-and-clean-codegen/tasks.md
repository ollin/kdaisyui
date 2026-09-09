# Tasks

Every task verifies the same invariant before it is ticked: **regenerating produces an empty
diff under `lib/generated/`**. That is the check CI runs, and it is what makes each of these
a refactoring rather than a hopeful edit.

Ordering is uncertainty-first. 1.1 is the assumption the whole change rests on — that the
module can be made importable without changing what it emits. If that fails, nothing below it
is reachable and the plan gets rewritten rather than worked around.

## 1. Make the generator testable

- [ ] 1.1 Guard `main()` behind an entry-point check and export the functions under test (refactoring) — the module currently runs on import, so no test can load it. Verify by regenerating: empty diff.
- [ ] 1.2 Add a `test` script running `node --test` and the first characterization tests for `parseTestCases` (refactoring; test-only, and green from the first run — they pin existing behaviour, they do not change any)

## 2. Clear the smells, tests before each refactoring

- [ ] 2.1 Refactor `parseTestCases` — cc 13, nesting depth 4, 3 bumps, plus the complex conditional at line 80. The four mutable locals threading through one loop are the smell; the code-block scan wants to be its own thing (refactoring)
- [ ] 2.2 Characterization tests for `buildClassMappings` (refactoring; test-only, green from the start)
- [ ] 2.3 Refactor `buildClassMappings` — cc 11, nesting depth 4 (refactoring)
- [ ] 2.4 Refactor `main` — cc 10, 2 bumps. Argument dispatch and the reporting loop are separable (refactoring)

## 3. Keep it that way

- [ ] 3.1 Add a `codegen-tests` CI job running the suite — parallel to `generated-sources-drift`, and NOT bound to `./gradlew check`, which must stay runnable with no Node (documentation/CI wiring)
- [ ] 3.2 Re-measure with CodeScene and record the before/after score in this change (documentation; evidence captured)
