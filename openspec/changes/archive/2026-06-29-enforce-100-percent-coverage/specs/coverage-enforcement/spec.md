## ADDED Requirements

### Requirement: Aggregated coverage measurement
The build SHALL measure code coverage for the published library modules (`:lib` and `:ktor-integration`) and aggregate them into a single merged report at the root project. The aggregation SHALL NOT include `:example-app`, `:e2e-tests`, or `:bom`.

#### Scenario: Aggregated report covers in-scope modules
- **WHEN** a coverage report is generated at the root project
- **THEN** it includes measured coverage for `:lib` and `:ktor-integration`
- **AND** it excludes `:example-app`, `:e2e-tests`, and `:bom`

#### Scenario: Report formats produced
- **WHEN** the root coverage report task runs
- **THEN** an HTML report and an XML report are produced for the aggregated scope

### Requirement: Hard 100% coverage gate
The build SHALL fail when aggregated line coverage OR aggregated branch coverage for the in-scope modules is below 100%. The threshold SHALL be exactly 100% for both metrics.

#### Scenario: Build fails below the threshold
- **WHEN** aggregated line or branch coverage is below 100%
- **THEN** the coverage verification task fails with a non-zero exit code
- **AND** the failure message identifies the metric and the measured value

#### Scenario: Build passes at the threshold
- **WHEN** aggregated line AND branch coverage are both exactly 100%
- **THEN** the coverage verification task succeeds

### Requirement: Gate integrated into check
The coverage verification SHALL run as part of the standard `check` task, so that `./gradlew check` and the CI pipeline fail on any coverage shortfall without requiring a separately named task.

#### Scenario: check enforces coverage locally
- **WHEN** a developer runs `./gradlew check` with in-scope coverage below 100%
- **THEN** the command exits non-zero due to the coverage gate

#### Scenario: CI enforces coverage
- **WHEN** the CI pipeline runs its verification step on a change that drops in-scope coverage below 100%
- **THEN** the CI run fails

### Requirement: Generated code is accounted for
The coverage scope SHALL account for the generated component sources in `:lib` (the 63 generated components under `lib/build/generated/`). Generated classes that are in scope MUST be exercised by tests; any generated class excluded from measurement MUST be excluded by an explicit, documented Kover filter rather than by silent omission.

#### Scenario: In-scope generated code is measured
- **WHEN** the aggregated report is generated and a generated component class is not excluded by a documented filter
- **THEN** that class contributes to the measured line and branch coverage

#### Scenario: Exclusions are explicit
- **WHEN** a generated class is excluded from coverage measurement
- **THEN** the exclusion is expressed as an explicit Kover filter in the build configuration
