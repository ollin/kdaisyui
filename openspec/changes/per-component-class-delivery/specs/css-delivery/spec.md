## ADDED Requirements

### Requirement: A Gradle plugin narrows the class list to the components a build uses

The library SHALL publish a Gradle plugin whose task writes `build/kdaisyui/kdaisyui-classes.txt`
with the CSS classes of exactly those components whose JVM classes the build's compiled code
references.

**Verified** that compiled code names them: a byte scan of `example-app`'s classes found the file
class of each of the 20 components it uses, and the enums it names, 2026-10-31.

#### Scenario: Only the components called

- **WHEN** a build's code calls `daisyButton` and nothing else of the library
- **THEN** the written list holds the button's classes
- **AND** no class of any other component

#### Scenario: The lists match the library version in use

- **WHEN** the plugin runs
- **THEN** it reads the component map from the `kdaisyui` artifact on the build's runtime classpath

### Requirement: When in doubt the plugin writes the full list

When the build references a kdaisyui component class the map does not know, or references none,
the plugin SHALL write every class the library can emit and warn, naming the reason.

#### Scenario: A version out of step

- **WHEN** compiled code references a component class the map does not list
- **THEN** the written list is the full list
- **AND** the warning names the class

### Requirement: The narrowed stylesheet styles the example application

The example application SHALL build its stylesheet from the plugin's list, and the E2E suite SHALL
pass on it.

**Assumed** that component granularity loses no class a page needs. *Wrong if:* an E2E scenario
that passes on the full stylesheet fails on the narrowed one.

#### Scenario: The suite on the narrowed stylesheet

- **WHEN** the example application is built with the plugin
- **THEN** its stylesheet is smaller than the full one
- **AND** every E2E scenario passes

## MODIFIED Requirements

### Requirement: The size and its remedy are stated honestly
The documentation SHALL state the approximate size of the full stylesheet and of a narrowed one,
SHALL name the Gradle plugin as the way to narrow it, and SHALL NOT instruct a reader to
hand-maintain a narrower list.

A hand-kept safelist is followed until the day it is not, and its failure mode is the silent one
this capability exists to remove. Narrowing the output to what an application actually uses
requires knowing which generated functions and enum entries a codebase references: tooling, not
diligence. The plugin is that tooling.

#### Scenario: A reader can predict the cost
- **WHEN** a developer follows the documented setup
- **THEN** the documentation has told them the approximate size of the result
- **AND** why it covers more than their application uses, or how the plugin narrows it

#### Scenario: The escape hatch's limit is stated
- **WHEN** a consumer passes an arbitrary class through `extraClasses`
- **THEN** the documentation says that class must be visible to their own Tailwind setup
- **AND** does not imply the library can account for it
