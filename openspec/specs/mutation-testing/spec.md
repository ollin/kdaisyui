# mutation-testing Specification

## Purpose

Coverage answers whether a line **ran**. This capability answers whether it was **asserted**.

The two are independent, and the gap between them is invisible to every other check this
project has: a test asserting `x == x` executes exactly the lines and branches it used to, so
`koverVerify` cannot distinguish it from a real one. Measured here rather than assumed — a
deliberately weakened assertion left `:lib:test` and the 100% coverage gate green, and only
the mutation gate objected.

Mutation testing closes that gap by changing the code and requiring a test to notice. It is
scoped and gated, not exhaustive: it covers the hand-written core plus a documented set of
branch-rich components, because mutating all 63 generated components would take hours and
measure the generator rather than anything a human wrote.

Read together with `coverage-enforcement`, the pair states the whole property and nothing
more: **everything reachable is executed** (Kover, 100% line and branch) and **everything
executed is asserted** (PIT, 100% test strength).

## Requirements

### Requirement: Scoped mutation testing of core logic
The build SHALL run PIT mutation testing over the project's core-logic classes (`core/ClassNames`, the `core/TagId` hierarchy, and a documented set of branch-rich components) and SHALL NOT mutate the full 63-component surface, demo code (`:example-app`), test code (`:e2e-tests`), or the BOM. The scoped classes SHALL be expressed explicitly via `targetClasses`.

#### Scenario: Only scoped classes are mutated
- **WHEN** the mutation task runs
- **THEN** mutants are generated only for classes matching the configured `targetClasses` scope
- **AND** classes outside that scope are not mutated

#### Scenario: kotlin-test suites execute the mutants
- **WHEN** PIT runs the project's kotlin-test suites against the mutants
- **THEN** the suites execute on the JUnit Platform via the configured `pitest-junit5-plugin`
- **AND** generated Kotlin internal calls (e.g. `kotlin.jvm.internal`) are excluded via `avoidCallsTo`

### Requirement: Test-strength threshold gate
The build SHALL fail when the **test strength** for the scoped classes falls below the configured `testStrengthThreshold`, which SHALL be 100%. Test strength is `killed / (killed + survived)`: every mutant a test *reaches* must be killed.

The gate SHALL NOT be expressed as `mutationThreshold`. That metric counts mutants no test can reach, and 100% is unreachable for this scope: Kotlin emits interface-default bridges into `AnnotatedIdBase` and `StringHtmlId` that no source-level test can call. PIT cannot exclude them surgically — `excludedMethods` matches by name alone, so excluding `getTarget` would also drop the killed mutant on `HtmlId::getTarget`, the one place the real logic lives — and removing the bridges requires `-Xjvm-default=no-compatibility`, an ABI change to a published artifact that `coverage-enforcement` already rejected for the same reason.

**Verified** 2026-09-09: 218 mutants, 214 killed, 0 survived, 4 without coverage; test strength 100%, mutation score 98% and unable to rise. The gate was proven to fail by weakening one assertion — PIT reported `Test strength score of 99 is below threshold of 100` and exited non-zero.

#### Scenario: Build fails when a reachable mutant survives
- **WHEN** a mutant covered by at least one test is not killed
- **THEN** the mutation task fails with a non-zero exit code
- **AND** the failure message reports the measured test strength and the threshold

#### Scenario: Build passes when every reachable mutant is killed
- **WHEN** every mutant covered by a test is killed
- **THEN** the mutation task succeeds
- **AND** mutants with no coverage do not fail the gate

#### Scenario: A weakened assertion is caught here and nowhere else
- **WHEN** an assertion is weakened so that a previously killed mutant survives
- **THEN** the unit-test suite still passes and the coverage gate still passes
- **AND** the mutation task fails

### Requirement: Surviving-mutant reports
The build SHALL produce PIT HTML and XML reports identifying surviving mutants so contributors can see which mutations were not killed and strengthen the corresponding tests.

#### Scenario: Reports list surviving mutants
- **WHEN** the mutation task completes (pass or fail)
- **THEN** an HTML report and an XML report are produced
- **AND** each surviving mutant is identified by class, line, and applied mutator

### Requirement: Mutation gate depends on full coverage
Mutation testing SHALL build on the `coverage-enforcement` capability: the scoped classes are assumed to already have 100% line+branch coverage, and mutation testing strengthens those tests rather than substituting for coverage.

#### Scenario: Coverage precedes mutation enforcement
- **WHEN** mutation testing is enabled for a scoped class
- **THEN** that class is already within the 100% line+branch coverage scope
- **AND** surviving mutants indicate weak assertions in already-covering tests, not missing coverage
