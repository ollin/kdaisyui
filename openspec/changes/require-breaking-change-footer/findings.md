# Findings

## F1 - Two releases since v0.9.0 broke the release-note rule, not one

- **What happened:** checking A3 found #492 (v0.11.0) besides #471 (v0.9.0): a signature change in
  `lib/api/lib.api` with a migration entry, under a title without `!`, so the release page did not
  mark it breaking.
- **Cause:** the rule lived in a skill read at tag time; nothing checked a pull request.
- **Cost:** none in this change; it widened the gate from "footer on `!` titles" to "a removed API
  line also requires `!`".

## F2 - The heading step was reverted once, for a branch no test could reach

- **What happened:** task 2.4 went green in the tests and red in `koverVerify`: 99.95% branch
  coverage. `find(...)?.value?.trimEnd('.')` compiles a second null check whose null branch only a
  `null` from `find` reaches, and that path already returned.
- **Cause:** the step bundled two behaviours, the heading check and the full-stop handling.
- **Cost:** one TCR run (about 1 minute). The full stop moved into the regex, with no branch.

## F3 - PIT mutated the inlined stdlib `any` into an equivalent

- **What happened:** `:build-checks:pitest` reported 98%: one negated conditional on a line past
  the end of `BreakingChange.kt`, the `is Collection && isEmpty()` fast path of `Iterable.any`,
  inlined into the check. No test can tell it apart.
- **Cause:** an inline stdlib function on a `List` carries a branch the caller cannot observe.
- **Cost:** about 2 minutes; `lineSequence().any` has no such path, and PIT went to 100%.
