## ADDED Requirements

### Requirement: Changed codegen lines are mutation-tested at 100 %

CI SHALL mutate every line a pull request adds or changes in `codegen/src/`, entry points
excluded, and SHALL fail when any of those mutants survives. Lines the pull request does not touch
SHALL NOT be mutated by this gate, and a pull request that changes no such line SHALL pass it
without running mutants.

**Verified** that a whole-directory gate cannot be the rule: a full run on 2026-11-01 scored 43.7 %
(3,530 mutants, 1,986 survived), most of the survivors in generator code no unit test runs.

**Verified** that the scope can be lines: `--mutate src/llms-index.ts:60-70` instrumented 18
mutants and ran in 3 s (2026-11-01).

**Assumed**: the gate takes under a minute for a typical pull request. *Wrong if:* the
`codegen-tests` job of a pull request changing fewer than 50 lines of `codegen/src/` takes longer
than 90 s.

#### Scenario: A new function whose test cannot fail

- **WHEN** a pull request adds a function to `codegen/src/` and a test that executes it but
  asserts nothing it returns
- **THEN** the `codegen-tests` job fails
- **AND** the report names the surviving mutants with file and line

#### Scenario: A change outside codegen sources

- **WHEN** a pull request changes no line in `codegen/src/`
- **THEN** no mutant runs and the gate passes

#### Scenario: A change next to old survivors

- **WHEN** a pull request changes one line of a file whose other lines have surviving mutants
- **THEN** only the mutants on the changed line decide the result

### Requirement: The whole codegen can be mutated on demand

`npm run mutate` in `codegen/` SHALL mutate all of `codegen/src/`, entry points excluded, and
report the score per file without failing on it.

**Verified**: the full run of 2026-11-01 took 418 s on 8 workers and printed the score per file.

#### Scenario: Measuring the baseline

- **WHEN** a contributor runs `npm run mutate` in `codegen/`
- **THEN** every file's mutation score is reported
- **AND** the command does not fail for a score below 100 %
