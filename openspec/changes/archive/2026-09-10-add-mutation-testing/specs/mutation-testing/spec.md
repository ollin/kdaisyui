## ADDED Requirements

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

### Requirement: Mutation-score threshold gate
The build SHALL fail when the mutation score for the scoped classes falls below the configured `mutationThreshold`. The threshold SHALL be 100% for the scoped classes (every generated mutant must be killed).

#### Scenario: Build fails when mutants survive below threshold
- **WHEN** the scoped mutation score is below the configured threshold
- **THEN** the mutation task fails with a non-zero exit code
- **AND** the failure message reports the measured mutation score and the threshold

#### Scenario: Build passes when all scoped mutants are killed
- **WHEN** the scoped mutation score meets or exceeds the threshold
- **THEN** the mutation task succeeds

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
