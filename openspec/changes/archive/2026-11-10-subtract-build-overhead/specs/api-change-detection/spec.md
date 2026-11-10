## MODIFIED Requirements

### Requirement: A diverging API fails the build
The build SHALL fail when the compiled public API of `lib` no longer matches its committed
baseline, and that check SHALL run in CI on every pull request rather than only on a developer's
machine.

**Verified:** `checkKotlinAbi` inserts itself into `:lib:check` — `:lib:check --dry-run` lists
`:lib:internalDumpKotlinAbi` and `:lib:checkKotlinAbi`, and on 2026-11-10
`./gradlew check -x :e2e-tests:test --dry-run` listed both. **Verified** that CI runs it: the
`unit-tests` job runs `check`, a required status check of the main ruleset. Until
`subtract-build-overhead` a second job, `api-baseline`, ran `./gradlew :lib:checkKotlinAbi` on its
own; it closed a gap only while `unit-tests` ran a hand-picked task list, which ended on
2026-10-31. **Assumed** for the removal: *wrong if* a public declaration added to `lib` without
re-dumping the baseline leaves `./gradlew check -x :e2e-tests:test`, the command `unit-tests`
runs, green. Task 1.4 of `subtract-build-overhead` makes that observation.

#### Scenario: An undeclared API change is rejected
- **WHEN** the public API of `lib` differs from the committed baseline
- **THEN** the check task fails
- **AND** the failure names the divergence

#### Scenario: The gate runs on a pull request
- **WHEN** a pull request changes the public API without updating the baseline
- **THEN** the `unit-tests` job fails
- **AND** the pull request cannot be merged on that basis alone
