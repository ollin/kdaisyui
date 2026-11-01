## Why

The branch-coverage tests (`lib/generated/test/.../*CoverageTest.kt`) are written by reading each
generated component's Kotlin back in with regexes and a delimiter matcher: `findFunctions`,
`splitParams`, `classifyParam`, `parseEnumDefinitions`, `parseScopeMembers`, `parseBaseClass`,
`parseEmittedBuilder`, `parseScopeBuilder`, `parseAttrProps`. All of that is already known, as
data, in the `ComponentShape` the Kotlin is emitted from. The parser re-derives what the
generator has. Its regexes are lenient about Kotlin the generator never writes, and they hold
about 65 of the 104 mutants that survive in `codegen/src/test-generator.ts`, the last large block
of survivors in `codegen/src` (95.4 % on 2026-11-01).

## What Changes

- **Subtractive (chosen):** `coverageTestFor` takes the component's `ComponentShape` and its
  `MainFunctionConfig` instead of the Kotlin source. The Kotlin parser is deleted, along with
  `matchDelimiter` and the regexes only it uses.
- The entry point stops reading `lib/generated/main/**/components/*.kt` to write the coverage
  tests and reads the component set the other generators already use.
- The generated coverage tests come out **byte-identical**. `coverage-test-snapshot.test.ts` and
  CI's `generated-sources-drift` job compare them.
- Additive alternative, rejected: tighten every regex to the exact Kotlin the generator writes.
  That kills mutants but keeps a second, textual description of the generator's output beside the
  shape. That duplication is the defect.
- Minimal alternative, rejected: record the survivors as equivalent. It leaves the duplication,
  and the parser keeps reading a format nothing guarantees it understands.

## Capabilities

### New Capabilities

None.

### Modified Capabilities

None. The generated test files do not change, so no requirement changes; `.openspec.yaml` sets
`skip_specs: true`.

## Impact

- `codegen/src/test-generator.ts`: the coverage half reads the shape; about 300 lines of parsing
  go.
- `codegen/src/index-coverage-tests.ts` (or whichever entry point writes the coverage tests): reads
  `readComponentSet()` instead of the `.kt` files.
- `codegen/test/coverage-test-snapshot.test.ts` and `test-generator.test.ts`: call the new
  signature.
- `lib/build.gradle.kts`: `generateComponentTests` stops passing `--components-dir`, stops
  depending on `generateComponents` and stops declaring its output as an input. **Corrected
  during apply:** this list first said no Gradle file would change; the flag only existed to feed
  the parser this change deletes.
- No generated file changes. No Kotlin and no dependency.

**Out of scope:**

- The markdown half of `test-generator.ts` (test-case headings, `endsInsideUnclosedHtmlBlock`,
  the documented-element tests). It reads DaisyUI's pages, which are real input. Its survivors
  are for a later survivor PR.
- The attributes an extra sets are free-form Kotlin in the model's `apply` fragments. Declaring
  them in the model instead is a model change and belongs to its own change, if ever (see
  design.md, D3).
- The `class: null` guards elsewhere in codegen, left by the decision recorded in #460.

## Estimate

Raw, not corrected by known ratios. Frozen on 2026-11-01 before the first commit.

| Block | Tasks | Estimate |
|---|---|---|
| 1. Verify the assumptions | 1.x | 0.75 h |
| 2. Switch the coverage inputs to the shape | 2.x | 1.5 h |
| 3. Delete the parser and the file reading | 3.x | 0.5 h |
| 4. Gates, pull request, archive | 4.x | 0.25 h |
| **Total** | | **3.0 h** |
