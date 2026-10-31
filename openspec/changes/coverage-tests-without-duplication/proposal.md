## Why

CodeScene rates 27 of the 66 generated coverage tests below 10.0 (9.38, and `DropdownCoverageTest`
8.54), all for Code Duplication, measured 2026-10-31. Every one belongs to a component with several
functions. The duplicated code is the `_all_flags` test each function gets, the same four
escape-hatch arguments and assertions with a different function name, and, where two functions
share an axis, the per-entry enum tests. The change-set gate fails on any change that adds a
function to such a component; `dropdown-popover`'s did (finding F6 there). The files are generated,
so the fix is the generator.

## What Changes

The coverage generator emits, in every `*CoverageTest.kt`:

- **an `Expected` value** (label, classes, closing tag, whether there is content) that every
  assertion takes, instead of three or four loose strings;
- **one `allFlags` helper and four constants** (`ID`, `EXTRA`, `ATTRS`, `CONTENT`) for the escape
  hatches, so an all-flags test is one call;
- **one test per enum axis** with a table from entry to expected classes, instead of one test per
  entry.

What every test asserts stays the same: the same classes per entry, the same flags, the same
attributes. Fewer test methods report it: Card goes from 35 to 23.

**Subtractive option:** exclude `lib/generated/test/` from CodeScene. Rejected, because the
generated tests are what a reader opens when a component breaks, and AGENTS.md reviews a test file
as production code.

**Out of scope:** the documented-element tests (`*Test.kt`), which score 10.0, and the icon tests.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | No requirement of a main spec this builds on contradicts it. It touches the generated tests that `generated-sources` and `coverage-enforcement` count, and neither requires one test per entry | code | confirmed at proposal - read both; `generated-sources` quotes a dated test count (1488) as a measurement, not a requirement |
| A2 | The three moves together reach 10.0 | code | confirmed at proposal - a probe rewrote Card, Dropdown, Menu and Button's coverage tests by script: all four scored 10.0; each move alone left a finding (Excess Number of Function Arguments, then String Heavy Function Arguments) |
| A3 | The rewritten tests compile and pass | code | confirmed at proposal - the probe's Card, Dropdown and Menu files ran in place of the generated ones: 23, 22 and 23 tests, all green |
| A4 | Mutation test strength stays at 100% | code | to check in task 2.4 - a table still asserts every entry |
