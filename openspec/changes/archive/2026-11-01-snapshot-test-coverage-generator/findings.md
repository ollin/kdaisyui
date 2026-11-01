# Findings

## F1 - The snapshot test lifts test-generator.ts from 9 % to 62 %

- **What happened:** task 3.2. One test over the 66 committed component files: 1,024 survivors
  before, 418 after, at 62.1 % (680 killed, 5 timeouts). The test file runs in 113 ms (A4).
- **Where the 418 are:** 267 in the documented-element half (out of scope), about 70 in the
  entry and argument code no unit test imports, and about 80 in the coverage half.

## F2 - The coverage half's remaining survivors are input-domain equivalents

- **What happened:** task 4.1. Of the coverage half's survivors, most are regex anchors and
  whitespace classes (`^`, `$`, `\s*` against `\S*`) in parsers whose only input is this
  generator's own Kotlin, which never varies its formatting; the rest are labels nothing reads and
  conditions the 66 components never reach.
- **Cause:** a parser written tolerant for a closed input. A mutant that only loosens it cannot
  change any output while the input stays the generator's own.
- **Cost:** none to the gate, which mutates changed lines only. Deleting the tolerance would make
  every regex stricter for no observed input; the snapshot test already fails loudly the day the
  generator's formatting changes.

## F3 - The snapshot test failed StrykerJS's initial run

- **What happened:** task 3.2. StrykerJS copies `codegen/` into `codegen/.stryker-tmp/sandbox-…/`,
  where the test's fixed `../../lib/generated` found nothing, so the dry run failed before any
  mutant.
- **Cost:** about 3 minutes. The test now looks upward for the nearest `lib/generated`.

## F4 - Two pieces of the coverage half were dead or duplicated

- **What happened:** task 4.1. `VOID_TAGS` restated `isVoidElement`'s fourteen elements in lower
  case; two `PARAM_KIND_RULES` entries (`attrs`, `presetNonNull`) assigned kinds no code reads, to
  parameters that fall to `other` anyway.
- **Cost:** none; three refactorings, each proven by `just generate` without a diff.

## Proposals

| Finding | Proposal | Status |
|---|---|---|
| F1 | A snapshot test for the documented-element half, from vendored `+page.md` fixtures | open - its own change |
| F2 | - | none - recorded; the gate is unaffected |
| F3 | - | none - fixed in this change |
| F4 | - | none - fixed in this change |
