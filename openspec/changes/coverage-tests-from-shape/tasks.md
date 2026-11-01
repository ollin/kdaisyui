## 1. Verify the assumptions (no commit; findings in `tmp/coverage-tests-from-shape/EVALUATION-coverage-tests-from-shape.md`)

A throwaway script under `tmp/coverage-tests-from-shape/` computes each field both ways, parsed
from the committed `.kt` and derived from `readComponentSet()`, for all 66 components, and lists
every difference. A refuted assumption revises design.md D1 before section 2 starts.

- [x] 1.1 Compare enums (name, entry order, entry class), function order including siblings, and each parameter's name, type and default (D1 rows 1-3)
- [x] 1.2 Compare base classes, Boolean parameter classes, the builder (`Join` included) and the scope members (D1 rows 4-7)
- [x] 1.3 Compare the attribute sets: body parse against `role`, `inputType` and the extras' `apply` fragments (D1 row 8, D3)

## 2. Switch the coverage inputs to the shape, one field per commit

Each task changes `codegen/src/test-generator.ts` and keeps `coverage-test-snapshot.test.ts` green
and `just generate` diff-free. `coverageTestFor` gains the shape and config as extra parameters
in 2.1 and still receives the source until 3.1.

- [x] 2.1 `^ r` Pass the component's shape and main config into `coverageTestFor` beside the source, and read the enums from `shape.enums`
- [x] 2.2 `^ r` Read the functions, their siblings and their parameters from the shape instead of `findFunctions` and `splitParams`
- [x] 2.3 `^ r` Read each function's base classes and Boolean classes from the shape instead of its body
- [x] 2.4 `^ r` Read the builder from `tagBuilder` instead of `parseEmittedBuilder` and `parseScopeBuilder`
- [x] 2.5 `^ r` Read the scope members from `shape.scope` instead of `parseScopeMembers`
- [x] 2.6 `^ r` Read the attributes from the main config and the extras' fragments instead of the whole body

## 3. Delete the parser and the file reading

- [x] 3.1 `^ r` Drop the source parameter, `writeCoverageTest`'s file read and every parser and regex only it used; the entry point iterates `readComponentSet()`
- [x] 3.2 `. r` Move the unit tests of the deleted parsers onto the shape-based context, keeping what they pinned where it still exists
  - Done differently: the parsers' tests went with the parsers in 3.1, because what they pinned (a scope's superclass, members read from Kotlin text) no longer exists. `coverage-test-generator.test.ts` instead pins the rules the mutation gate showed no committed model distinguishes: the main class, the role's owner, extras in body order and a member's required parameters.

## 4. Gates, pull request, archive

- [x] 4.1 Run the StrykerJS gate on the changed lines at 100 %, then a full `codegen` run, and record the before and after survivors of `test-generator.ts`
- [ ] 4.2 Run `code_health_review` on every changed file (10.0) and `analyze_change_set`, and read the server values for project 14
- [ ] 4.3 Archive the change on this branch, then push and open the pull request
