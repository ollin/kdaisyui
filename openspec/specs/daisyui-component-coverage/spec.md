# daisyui-component-coverage Specification

## Purpose
TBD - created by archiving change adapt-daisyui-5-6. Update Purpose after archive.

## Requirements

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

> **NOT SATISFIED — recorded 2026-09-04.** The last clause is untrue and has been since it was
> written. `daisyMegamenu` emits no `popover` attribute and there is no wrapper for the
> `<div id="…" popover>` panels DaisyUI documents, so the megamenu as generated cannot open.
>
> Found while archiving `support-popover-modals`, which fixed the identical defect on `modal`.
> Same cause, same blind spot: the popover method introduces no new CSS class, so the generated
> tests, `generated-sources-drift` and the coverage gate are all satisfied by markup that does not
> work — and the example app renders a megamenu whose E2E assertion passes on classes alone.
>
> Left standing rather than deleted, because a requirement that names a real gap is worth more
> than a tidy spec. `support-popover-megamenu` is the change that makes it true; this note goes
> when that lands.

#### Scenario: New components are generated, not hand-written
- **WHEN** the codegen pipeline runs for the pinned DaisyUI release
- **THEN** the `aura`, `otp`, and `megamenu` wrappers are produced into the generated sources directory
- **AND** no hand-written wrapper for these components exists in the non-generated source tree

### Requirement: New modifiers on existing components are exposed
Regeneration against the pinned DaisyUI release SHALL surface the new modifiers added to existing components — at minimum `range-vertical` and `tooltip-start` / `tooltip-center` / `tooltip-end` — as new enum entries or parameters on the corresponding existing wrappers, with backward-compatible defaults.

A *modifier* is a CSS class toggled on a component's existing element. An alternative *construction method*, which renders a different element, is not a modifier and cannot be expressed as a parameter; those are governed by the popover-modal requirement below.

**Corrected 2026-09-04:** this requirement previously also named "the `modal` popover attribute" among the modifiers to be surfaced. That was unbuildable: DaisyUI's popover modal is `<div class="modal" popover>`, a different root element from the `<dialog>` the wrapper emits, so no parameter on that wrapper could ever satisfy it. The clause was archived as satisfied while nothing implemented it, because no test and no drift check can detect a construction method that introduces no new class name.

**Verified:** before this change `lib/generated/…/components/Modal.kt` held one wrapper only, `daisyModal`, emitting `dialog { }` with no `popover` reference anywhere in the file; `daisyui/packages/docs/src/routes/(routes)/components/modal/+page.md:310` shows the popover method as `<div class="modal" id="…" popover>`. The file now also holds `daisyModalPopover`, which is what satisfies the requirement added below — `daisyModal` itself is unchanged.

#### Scenario: New modifier becomes available
- **WHEN** a DaisyUI modifier class is declared in an existing component's frontmatter
- **THEN** the regenerated wrapper exposes it as a new enum entry or parameter
- **AND** existing call sites that omit it compile unchanged

#### Scenario: A new construction method is not mistaken for a modifier
- **WHEN** an upstream release adds a way to build a component from a different root element
- **THEN** that capability is not recorded as a modifier of the existing wrapper
- **AND** it is specified separately, with its own element and its own generated function

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

### Requirement: Popover modals are expressible from the DSL
The library SHALL provide a generated wrapper that renders DaisyUI's HTML-popover modal — the `modal` class on a `div` element carrying the `popover` attribute — so that a modal can be opened and closed with no JavaScript. The wrapper SHALL be produced by the codegen pipeline, not hand-written, and SHALL NOT require callers to set the `popover` attribute themselves through the raw-attribute escape hatch.

The existing `<dialog>`-based `daisyModal` SHALL remain unchanged and continue to be the recommended method.

**Verified:** `daisyui/packages/docs/src/routes/(routes)/components/modal/+page.md:49` lists four modal methods; method 2 (Popover) is current, and only methods 3 (Checkbox) and 4 (Anchor Link) are marked Legacy. The same table records that popover opens and closes by HTML attributes rather than JavaScript.

#### Scenario: Popover modal renders the documented markup
- **WHEN** the popover-modal wrapper is rendered with content
- **THEN** it emits a `div` carrying the `modal` class
- **AND** that element carries the `popover` attribute without the caller supplying it

#### Scenario: Popover modal accepts a type-safe id
- **WHEN** the popover-modal wrapper is given an id from the `HtmlId` hierarchy
- **THEN** the rendered element carries that id
- **AND** the id is the value a `popovertarget` control refers to

#### Scenario: The dialog method is unaffected
- **WHEN** the existing `daisyModal` wrapper is rendered
- **THEN** it still emits a `dialog` element carrying the `modal` class
- **AND** existing call sites compile and render unchanged

### Requirement: Unsupported modal methods are named
The specification SHALL record that DaisyUI's checkbox modal (method 3) and anchor-link modal (method 4) are deliberately not wrapped, so their absence is legible as a decision rather than an oversight.

**Verified:** both are labelled Legacy in `daisyui/packages/docs/src/routes/(routes)/components/modal/+page.md:53` and `:54`, and neither locks background interaction nor closes on `Esc` per that table.

#### Scenario: A reader can tell absence from oversight
- **WHEN** a contributor finds no wrapper for the checkbox or anchor-link modal
- **THEN** the specification states that omission is intentional
- **AND** gives the reason: both are upstream-legacy methods

### Requirement: The popover modal is covered like any other component
The popover-modal wrapper SHALL be exercised by generated tests covering every branch it introduces, SHALL keep aggregated line and branch coverage at 100%, and SHALL be rendered by the example application and asserted by an end-to-end test.

**Verified:** regenerating with the popover entry configured produced branch-complete tests without any change to the test generator's coverage path, and `koverVerify --rerun-tasks` held at 100% line and branch over 1491 tests. The reason is structural rather than lucky: a static attribute is emitted unconditionally, so it introduces no branch into the generated Kotlin at all.

#### Scenario: Coverage gate holds
- **WHEN** the suite runs after the popover wrapper is generated
- **THEN** aggregated line coverage is 100% and aggregated branch coverage is 100%
- **AND** the coverage verification task exits zero

#### Scenario: Popover modal has E2E smoke coverage
- **WHEN** the E2E suite runs against the example application
- **THEN** a popover modal is rendered on a demo route
- **AND** an assertion verifies the served HTML carries the `modal` class and the `popover` attribute
