## ADDED Requirements

### Requirement: The stylesheet task is up to date after one compile

The stylesheet task SHALL take the class list as a single file, so that its own output, whatever
directory it is written to, is never one of its inputs, and a second run with unchanged inputs is
up to date.

**Assumed** until task 3.1: *wrong if* the example app's second run compiles again.

#### Scenario: Two runs in a row

- **WHEN** `kdaisyuiStylesheet` runs twice with nothing changed in between
- **THEN** the second run is UP-TO-DATE and starts no container

#### Scenario: The entry's paths stay

- **WHEN** an entry writes `@source "../kdaisyui/kdaisyui-classes.txt"`
- **THEN** it resolves to the class list inside the container, as before
