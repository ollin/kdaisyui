## Decisions

### 1. All three moves, because each alone leaves a finding

Measured by the probe (2026-10-31, CodeScene `code_health_review` on rewritten copies):

| shape | Card | Dropdown | Button |
|---|---|---|---|
| today | 9.38 duplication | 8.54 duplication | 10.0 |
| all-flags through one helper of 5 parameters | 9.09: excess arguments, primitive obsession | 7.78: duplication | - |
| plus one table test per axis, helper taking `Expected` | 9.68: string-heavy arguments | 9.68 | 9.68 |
| plus every assertion taking `Expected` | **10.0** | **10.0** | **10.0** |

The generator's own comment already records the first row's lesson: folding the assertions into one
five-parameter helper was tried and reverted.

### 2. A table per axis, not a parameterized test

The tests run on kotlin-test, which has no parameterized tests; adding JUnit's params module for
generated tests only is a dependency for a shape. A loop over a map asserts every entry and names
it in the failure message. What is lost: a failing entry stops the loop, so one run reports the
first wrong entry of an axis, not all of them.

### 3. Enablers first, each regenerating all 66 files

Each move is one refactoring of the generator, regenerated and run through TCR: the assertions
take `Expected` (task 2.1), the all-flags tests go through the helper (2.2), the entries become
tables (2.3). Each step changes no assertion's content.
