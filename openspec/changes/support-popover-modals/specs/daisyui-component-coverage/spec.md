## MODIFIED Requirements

### Requirement: New modifiers on existing components are exposed
Regeneration against the pinned DaisyUI release SHALL surface the new modifiers added to existing components — at minimum `range-vertical` and `tooltip-start` / `tooltip-center` / `tooltip-end` — as new enum entries or parameters on the corresponding existing wrappers, with backward-compatible defaults.

A *modifier* is a CSS class toggled on a component's existing element. An alternative *construction method*, which renders a different element, is not a modifier and cannot be expressed as a parameter; those are governed by the popover-modal requirement below.

**Corrected 2026-09-04:** this requirement previously also named "the `modal` popover attribute" among the modifiers to be surfaced. That was unbuildable: DaisyUI's popover modal is `<div class="modal" popover>`, a different root element from the `<dialog>` the wrapper emits, so no parameter on that wrapper could ever satisfy it. The clause was archived as satisfied while nothing implemented it, because no test and no drift check can detect a construction method that introduces no new class name.

**Verified:** `lib/generated/main/kotlin/io/github/ollin/kdaisyui/components/Modal.kt:40` emits `dialog { }` and contains no `popover` reference; `daisyui/packages/docs/src/routes/(routes)/components/modal/+page.md:310` shows the popover method as `<div class="modal" id="…" popover>`.

#### Scenario: New modifier becomes available
- **WHEN** a DaisyUI modifier class is declared in an existing component's frontmatter
- **THEN** the regenerated wrapper exposes it as a new enum entry or parameter
- **AND** existing call sites that omit it compile unchanged

#### Scenario: A new construction method is not mistaken for a modifier
- **WHEN** an upstream release adds a way to build a component from a different root element
- **THEN** that capability is not recorded as a modifier of the existing wrapper
- **AND** it is specified separately, with its own element and its own generated function

## ADDED Requirements

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

**Assumed:** the generated-test path produces branch-complete tests for a `customParts` entry as it does for a class-derived part. *Wrong if:* regenerating with the popover entry configured yields a test file that leaves any branch of the new function uncovered, and `koverVerify` drops below 100%.

#### Scenario: Coverage gate holds
- **WHEN** the suite runs after the popover wrapper is generated
- **THEN** aggregated line coverage is 100% and aggregated branch coverage is 100%
- **AND** the coverage verification task exits zero

#### Scenario: Popover modal has E2E smoke coverage
- **WHEN** the E2E suite runs against the example application
- **THEN** a popover modal is rendered on a demo route
- **AND** an assertion verifies the served HTML carries the `modal` class and the `popover` attribute
