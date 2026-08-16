## ADDED Requirements

### Requirement: DaisyUI version pinned to a webjar-backed release
The pinned DaisyUI version SHALL be a version that has a published Maven webjar under `org.webjars.npm:daisyui`, and the build's DaisyUI submodule, the generated components, and the served webjar SHALL all derive from that same single version.

**Corrected 2026-08-15:** this requirement originally fixed the version at `5.6.3`. Reality moved past it twice and the project is pinned at `5.7.16`. Naming a version in a requirement made the requirement expire; the webjar-backed constraint is the durable part and the only one kept.

#### Scenario: Pinned version has a published webjar
- **WHEN** the `daisyui` version is set in `gradle/libs.versions.toml`
- **THEN** that exact version is available as `org.webjars.npm:daisyui:<version>` on Maven Central
- **AND** the `webjar-daisyui` library entry resolves to the same version via `version.ref`

#### Scenario: Submodule, generated code, and webjar agree
- **WHEN** components are regenerated for the pinned DaisyUI version
- **THEN** the `daisyui` git submodule is checked out at the tag matching the pinned version
- **AND** every generated CSS class is present in the served webjar of that same version

### Requirement: New DaisyUI 5.6 components are wrapped
The library SHALL expose a generated, type-safe `FlowContent.daisyXxx()` DSL function for each new non-skipped component introduced in the pinned DaisyUI release: `aura`, `otp`, and `megamenu`. These wrappers SHALL be produced by the codegen pipeline, not hand-written.

#### Scenario: Aura wrapper renders
- **WHEN** `daisyAura(...) { ... }` is rendered with its style and size variants
- **THEN** it emits a container element carrying the `aura` class plus the selected `aura-*` style/size classes
- **AND** its nested content is rendered inside the container

#### Scenario: OTP wrapper renders
- **WHEN** `daisyOtp(...)` is rendered with its modifier, size, and color variants
- **THEN** it emits the `otp` element carrying the `otp` class plus the selected `otp-*` classes

#### Scenario: Megamenu wrapper renders
- **WHEN** `daisyMegamenu(...) { ... }` is rendered with its modifier, direction, and size variants
- **THEN** it emits the `megamenu` container carrying the `megamenu` class plus the selected `megamenu-*` classes
- **AND** its popover-based sub-parts are expressible from the wrapper

#### Scenario: New components are generated, not hand-written
- **WHEN** the codegen pipeline runs for the pinned DaisyUI release
- **THEN** the `aura`, `otp`, and `megamenu` wrappers are produced into the generated sources directory
- **AND** no hand-written wrapper for these components exists in the non-generated source tree

### Requirement: New modifiers on existing components are exposed
Regeneration against the pinned DaisyUI release SHALL surface the new modifiers added to existing components — at minimum `range-vertical`, `tooltip-start` / `tooltip-center` / `tooltip-end`, and the `modal` popover attribute — as new enum entries or parameters on the corresponding existing wrappers, with backward-compatible defaults.

**Not purely additive, contrary to the proposal:** the same regeneration *removed* `TooltipVariant.Neutral`, because DaisyUI dropped `tooltip-neutral`. A DaisyUI bump can take API away, and a requirement that only anticipates additions will not notice.

#### Scenario: New modifier becomes available
- **WHEN** a DaisyUI 5.6 modifier is declared in an existing component's frontmatter
- **THEN** the regenerated wrapper exposes it as a new enum entry or parameter
- **AND** existing call sites that omit it compile unchanged

### Requirement: Coverage gate stays at 100%
Every new component wrapper and every new modifier branch introduced by this change SHALL be exercised by the generated tests so that the aggregated line AND branch coverage of the in-scope modules remains exactly 100% and `./gradlew check` passes. New user-facing component code SHALL NOT be excluded from coverage measurement.

#### Scenario: Aggregated coverage remains 100%
- **WHEN** `./gradlew check` runs after the new components and modifiers are added
- **THEN** aggregated line coverage is 100% and aggregated branch coverage is 100%
- **AND** the coverage verification task passes with a zero exit code

#### Scenario: New components are covered by generated tests
- **WHEN** the codegen test generators run for the pinned DaisyUI release
- **THEN** generated tests exercise every variant, size, modifier, color, `id`, `extraClasses`, and content/attrs branch of each new component
- **AND** no new user-facing component class is added to a coverage exclusion filter

### Requirement: New components have E2E smoke coverage
Each new component SHALL be rendered by the example application and asserted by an end-to-end test, consistent with the project convention that UI changes ship with E2E coverage.

#### Scenario: New component appears in E2E
- **WHEN** the E2E suite runs against the example application
- **THEN** each of `aura`, `otp`, and `megamenu` is rendered on a demo route
- **AND** an E2E assertion verifies the component's expected class is present in the served HTML
