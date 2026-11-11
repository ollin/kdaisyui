## ADDED Requirements

### Requirement: CI fails when the specs do not validate strictly

Every CI run of a pull request and of a push to `main` SHALL fail when `openspec validate --all
--strict`, at the OpenSpec version the check pins, reports an error or a warning, and its log SHALL
name the failing items.

**Verified**: the command exits 1 on a warning and 0 on the same evidence as a metadata line (proposal A1, A2); `openspec-config` is a required check of `main` (A3).

#### Scenario: A requirement grows past the limit

- **WHEN** a pull request leaves a requirement whose text exceeds 500 characters
- **THEN** its required `openspec-config` check fails and names the spec or change
- **AND** the pull request cannot merge

#### Scenario: The strict check can fail

- **WHEN** the control runs the check on a fixture with an overlong requirement
- **THEN** the check fails, and on the same fixture with its evidence as a metadata line it passes
