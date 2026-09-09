# Tasks

Every task verifies the same invariant before it is ticked: **regenerating produces an empty
diff under `lib/generated/`**. That is the check CI runs, and it is what makes each of these
a refactoring rather than a hopeful edit.

Ordering is uncertainty-first. 1.1 is the assumption the whole change rests on — that the
module can be made importable without changing what it emits. If that fails, nothing below it
is reachable and the plan gets rewritten rather than worked around.

## 1. Make the generator testable

- [x] 1.1 Guard `main()` behind an entry-point check and export the functions under test (refactoring) — the module currently runs on import, so no test can load it. Verify by regenerating: empty diff. **Assumption held**: regeneration emits all 66 components and leaves `lib/generated/` byte-identical. Seven functions exported — the two refactoring targets plus the five `add-mutation-testing` helpers that have no tests at all.
- [ ] 1.2 Add a `test` script running `node --test` and the first characterization tests for `parseTestCases` (refactoring; test-only, and green from the first run — they pin existing behaviour, they do not change any)

  **Scope correction found in 1.1:** 1.2 must also add a Gradle task that runs the suite, not
  just an npm script. Every other codegen entry point is driven through Gradle
  (`lib/build.gradle.kts` uses `Exec` with `node …`), and an npm-only script would be
  unrunnable from the build and therefore unrunnable in CI without duplicating the invocation.
  The task must NOT be wired into `check` — see 3.1 and the no-Node promise.

  It also carries the other half of 1.1's proof: a test that imports the module and finishes
  in milliseconds is what shows `main()` did not run.

- [x] 1.2 — done. Ten characterization tests, green on the first run; suite completes in 54 ms, which is 1.1's missing proof. Task is `:lib:testCodegen`, `codegen` group, not on `check`. No dependency added.

## 1a. Defects found by writing the tests

Neither was known before 1.2. Both are recorded here rather than fixed inline, because each
changes behaviour and belongs in its own commit with its own reasoning.

- [ ] 1a.1 `codegen/src/test-generator-heroicons.js` has no entry-point guard and its `DEFAULT_OUTPUT_DIR` points at `lib/src/test/…`, the HAND-WRITTEN tree, not `lib/generated/test/…` (bugfix) — a bare `node --test` executed it as a test file and it wrote `HeroIconsGeneratedTest.kt` into the hand-written tree. Masked until now because Gradle always passes `--output-dir`. Same defect class as the one already commented in `test-generator.js`: "a second copy inside the generator once went stale unnoticed". **Write the reproducing test first.**
- [ ] 1a.2 `parseTestCases` loses a test case when a `### ~heading` appears inside an unclosed fence: the heading branch runs before the in-code-block branch, `inCodeBlock` is never reset, the next ```` ```html ```` is read as a closing fence, and the case is flushed with an empty body while its real content falls outside any block (bugfix) — pinned by the characterization test added in 1.2, so the fix is a deliberate edit to that test plus the code. **Do this AFTER 2.1**: refactoring first makes the fix small, and the current test proves the refactoring changed nothing.

## 2. Clear the smells, tests before each refactoring

- [x] 2.1 Refactor `parseTestCases` — cc 13, nesting depth 4, 3 bumps, plus the complex conditional at line 80. The four mutable locals threading through one loop are the smell; the code-block scan wants to be its own thing (refactoring) — **all four smells cleared**, function gone from the findings. File score 8.26 → 8.70. Verified by the 10 characterization tests and byte-identical regeneration.
- [x] 2.2 Characterization tests for `buildClassMappings` (refactoring; test-only, green from the start) — 9 tests, all green first run. Suite now 19.
- [x] 2.3 Refactor `buildClassMappings` — cc 11, nesting depth 4 (refactoring) — **also delete the dead `componentName` parameter**: 2.2 measured that it is never read, and its two call sites pass a value that cannot matter. Removing it is part of the same restructuring, not a separate behaviour change. **Both smells cleared; file score 8.70 → 9.53, into green.** The now-vacuous dead-parameter test was removed in a separate test-only commit, since a refactoring must not change an assertion.
- [x] 2.4 Refactor `main` — cc 10, 2 bumps. Argument dispatch and the reporting loop are separable (refactoring) — **file score 9.53 → 10.0, zero findings.** Section 2's goal is met: 8.26 → 10.0 with no change to a byte of generated output.

## 3. Keep it that way

- [ ] 3.1 Add a `codegen-tests` CI job running the suite — parallel to `generated-sources-drift`, and NOT bound to `./gradlew check`, which must stay runnable with no Node (documentation/CI wiring)
- [ ] 3.2 Re-measure with CodeScene and record the before/after score in this change (documentation; evidence captured)
