# daisyui-component-coverage Specification

## Purpose

Keeping the Kotlin DSL faithful to the DaisyUI release it is pinned to — and keeping every
part of the build that has an opinion about that version agreeing on one number.

Four things must name the same DaisyUI version: the git submodule the generator reads, the
generated components, the webjar `:example-app` serves, and the entry in
`gradle/libs.versions.toml`. They can drift independently and the failure is quiet — a
component emitting a class the served CSS does not define renders as unstyled markup, with
no test failing. The webjar-backed constraint exists because only a subset of DaisyUI
releases publish one, so the newest tag is usually not a usable version.

This capability also carries what regeneration **cannot** see. The class-based safety net —
generated tests, `generated-sources-drift`, `api-baseline` — keys on class names, so a new
way to *construct* a component catches none of it: the popover modal was recorded as
delivered while `daisyModal` still emitted only `<dialog>`, because that method adds no CSS
class. Requirements here name those blind spots explicitly rather than trusting the net.

**A note on drafting requirements in this capability:** the first one originally fixed the
version at `5.6.3`, and reality moved past it twice. Naming a version made the requirement
expire. State the durable constraint; leave the number to `gradle/libs.versions.toml`.

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

> **Satisfied by `support-popover-megamenu`, 2026-09-07.** From 2026-09-04 this scenario carried
> a NOT SATISFIED note: the last clause was untrue, `daisyMegamenu` emitted no `popover`, and the
> panels had no wrapper. `daisyMegamenu` now carries `popover` and `daisyMegamenuPanel` renders
> the panels; a `@nojs` end-to-end scenario asserts one reaches `:popover-open`.

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

### Requirement: Megamenu is constructible as DaisyUI documents it
The library SHALL provide generated wrappers sufficient to build DaisyUI's documented megamenu:
a root carrying the `megamenu` class **and** the `popover` attribute, a mandatory
`megamenu-active` indicator, and one popover panel per item. The panels SHALL be reachable from
the DSL even though they carry no DaisyUI class. None of it SHALL require the caller to set the
`popover` attribute through the raw-attribute escape hatch.

**Verified:** `daisyui/skills/daisyui/components/megamenu.md:16` documents the root as
`<div class="megamenu … {MODIFIER} …" id="my-megamenu-1" popover>`, `:19`-`:22` document each item
as a `popovertarget` button paired with `<div id="item-1" popover>`, and `:62` states that the
`megamenu-active` span is mandatory. Before this change `lib/generated/…/components/Megamenu.kt`
emitted no `popover` attribute anywhere, so the component as generated could not open.

**Verified** (was Assumed; settled by task 1.1): the root's `popover` is unconditional rather
than viewport-dependent. DaisyUI's documented markup was rendered at 1280x800 and 390x844 and the
screenshots reviewed — the root carries `popover` at both, and DaisyUI's own CSS is what makes it
a visible horizontal bar on desktop. Checked in a browser rather than by reading, because
`megamenu.css` is too intricate to settle that way.

**Verified** (found while implementing): `megamenu-active` must be a `<span>`. Not for its own
styling — it is `position: absolute` and styled by class alone — but because
`megamenu.css` selects the open panel with `[popover]:nth-of-type(N)`, and `:nth-of-type` counts
among siblings of the same tag. A `<div>` indicator takes div index 1 and shifts every
`<div popover>` panel by one, anchoring the indicator to the wrong trigger. Measured on
`/megamenu-reference`, which reported `--mm-anchor: --mm2` while panel one was open.

#### Scenario: The documented markup is reachable
- **WHEN** a megamenu is built from the generated wrappers
- **THEN** the root element carries the `megamenu` class and the `popover` attribute
- **AND** each item's panel is a generated wrapper carrying `popover` and its own id
- **AND** no caller supplies `popover` themselves

#### Scenario: A megamenu opens without JavaScript
- **WHEN** the example application serves a megamenu and its `popovertarget` trigger is clicked
  in a browser with JavaScript disabled
- **THEN** the corresponding panel reaches the `:popover-open` state

### Requirement: A construction method is not delivered until a consumer uses it
A generated wrapper for an alternative construction method SHALL be exercised by the example
application and asserted end-to-end before the change that adds it is considered complete.

This is stated as a requirement rather than left as practice because the failure it prevents has
now occurred twice. A construction method that introduces no new CSS class is invisible to the
generated tests, which assert class strings; to `generated-sources-drift`, which compares the
generator's output against itself; and to the coverage gate, which cannot miss code that was
never generated. A consumer is the only check that fails.

**Verified:** `modal`'s popover method was recorded as delivered by the archived
`adapt-daisyui-5-6` while nothing implemented it, and `megamenu` carried the same defect until
this change — the example app rendered it and the E2E asserted it, both passing, because both
only looked at classes.

This change produced a third instance of the same blind spot, which is why the requirement is
worth its weight: turning `megamenu-active` into a `<span>` changed no class, and so was invisible
to the generated tests, to `generated-sources-drift`, **and** to `api-baseline` — the last because
`(DIV.() -> Unit)` and `(SPAN.() -> Unit)` both erase to `Function1`. Only an end-to-end
assertion that measured the rendered page caught it.

#### Scenario: Absence is detected by a consumer
- **WHEN** a component gains a construction method that adds no new CSS class
- **THEN** an example-app route uses it
- **AND** an end-to-end assertion covers the attribute that defines it, not only its classes

### Requirement: A generated function never requires content its element cannot hold

A generated component function SHALL NOT declare a `content` parameter when the element it
renders is an HTML void element. This SHALL be derived from the element, not configured per
component.

The void elements are fixed by the HTML specification: `area`, `base`, `br`, `col`, `embed`,
`hr`, `img`, `input`, `link`, `meta`, `param`, `source`, `track`, `wbr`.

**Verified** that deriving lost nothing. Measured over all 66 non-skipped components on
2026-09-12, before the list was deleted: its eight entries were exactly the components whose
element is void, and no listed component rendered a non-void element.

**Verified** that transcribing it by hand failed in both possible directions. Two entries were
written as DaisyUI directory names (`file-input`, `theme-controller`) while the lookup used the
lower-cased PascalCase name, so neither ever matched; and `mask`, which renders `<img>`, was never
listed. All three declared a **required** trailing `content` lambda on an element that cannot have
children.

*Wrong if:* a component must suppress `content` for a reason other than its element being void.
The rule is then too narrow and an exception list has to return — carrying a stated reason per
entry, rather than restating HTML.

#### Scenario: A component rendering a void element

- **WHEN** the codegen generates a component whose element is `<input>`, `<img>` or any other
  HTML void element
- **THEN** the generated function declares no `content` parameter
- **AND** its body emits no content call

#### Scenario: A component rendering an element that may have children

- **WHEN** the codegen generates a component whose element is not a void element
- **THEN** the generated function declares a `content` parameter

#### Scenario: A DaisyUI release adds a component rendering a void element

- **WHEN** a new component's element is a void element
- **THEN** its generated function takes no `content` parameter without anyone configuring it

### Requirement: The generated element matches the one DaisyUI documents

The codegen SHALL compare the element it chose for each component against the element DaisyUI's
own documentation shows, and SHALL fail when they disagree without a recorded exception.

The authority is the fenced ```html example in the component's
`packages/docs/src/routes/(routes)/components/<name>/+page.md`, in which daisyUI class names carry
a `$$` marker. The tag bearing `$$<componentClass>` is the documented root element. The marker is
what makes this a statement rather than a heuristic: it separates daisyUI classes from Tailwind
utilities without inference.

**Verified** that this route is complete where the previously-used one is not. Measured on
2026-09-12 over all 66 non-skipped components: the docs route answers **66**; the
`skills/daisyui/components/*.md` `#### Syntax` block answers 57 and is absent for `calendar`,
`dropdown`, `fab`, `filter`, `label`, `menu`, `modal`, `swap` and `tab`.

**Verified** that it disagreed with the generator exactly three times when introduced, and that
all three were defects rather than false positives. `otp` rendered `<div>` where the docs show
`<label>`, so a click did not focus the wrapped input — **fixed**, and the cross-check now agrees.
Two remain, each carrying a recorded exception with a reason and a tracking issue: `tab` renders
`<button>` carrying the container class `tabs` where the docs show `<div>` (#342), and `calendar`
renders `<div>` where the docs show Cally's custom element `<calendar-date>`, which kotlinx.html
has no tag class for (#343).

*Wrong if:* DaisyUI stops marking class names with `$$`, or the first documented example ceases to
be the canonical construction. Either makes the cross-check fail rather than answer wrongly.

#### Scenario: The chosen element matches the documentation

- **WHEN** the codegen runs and every component's element matches its documented one
- **THEN** generation succeeds

#### Scenario: The chosen element contradicts the documentation

- **WHEN** a component's chosen element differs from the one DaisyUI documents
- **AND** no exception is recorded for that component
- **THEN** generation fails, naming the component, the chosen element and the documented one

#### Scenario: A disagreement that is recorded and tracked

- **WHEN** a component's element disagrees and an exception records a reason and a tracking issue
- **THEN** generation succeeds

#### Scenario: An exception that is no longer needed

- **WHEN** a component with a recorded exception stops disagreeing
- **THEN** generation fails, so the stale exception is removed rather than accumulating

### Requirement: A mutually exclusive class group is one typed choice

Where a browser confirms that every pair of classes in a DaisyUI class group is mutually
exclusive, the generated wrapper SHALL expose that group as ONE parameter taking a Kotlin enum,
rather than as one boolean per class. Where it does not, the classes SHALL remain individual
booleans.

The decision SHALL be a recorded measurement, not a category rule. The measurement SHALL
distinguish four outcomes for a pair or a member: `exclusive`, `compose`, `same` (the probe could
not tell) and `inert` (the class alone changes nothing against the baseline). The axes of a group
SHALL be derived from the measurement as the connected components of its exclusive graph over
non-inert members; an inert member SHALL join the axis whose members declare the CSS properties
it declares. A single-axis group SHALL be named by its component and DaisyUI's category word.
The axes of a multi-axis group SHALL be named from DaisyUI's reference table of CSS custom
properties where that table describes every property the axis's members declare with one
direction word — "vertical" or "horizontal" — and the two axes with different words. Only where
that derivation yields nothing SHALL the axis names be configured, and a configured name for an
axis the derivation can name SHALL fail the build. The build SHALL also fail when a derived axis
is not a clique, or when a multi-axis group has an axis that is neither derived nor configured.

**Verified** that the measurement is the only route to the answer. Six attempts derived
exclusivity from the CSS or from the shape of the class names, and all six were wrong. The
recorded measurement is `codegen/exclusivity.json`: 310 pairwise verdicts over 44 groups (193
`exclusive`, 103 `compose`, 14 `same`), written wholesale by `just measure-exclusivity` and
guarded by `:lib:verifyExclusivity`.

**Verified** that a category rule would have been wrong in both directions. The rule this replaces
made `styles`, `directions` and `placements` enums by virtue of their DaisyUI frontmatter
category; the measurement says `chat.placements` and `carousel.directions` **compose**, while
`card.modifiers` and `list.modifiers` — the category that rule distrusted — are exclusive.

**Verified** the asymmetric cost that makes `same` fall to boolean rather than to enum: a wrong
enum makes a combination DaisyUI permits **inexpressible**, which defeats the library's purpose;
a wrong boolean merely permits a combination that does nothing, which `extraClasses` permits
anyway. This is a decision, not a measurement, and it is recorded as one — **confirmed
2026-09-15 (Oliver): boolean, because it takes nothing away.** The rule is code, not
configuration, so no entry can silently promote a `same` pair to an enum.

**Verified** where `same` occurs and that half of it is this change's own defect, read
2026-09-15 from `codegen/exclusivity.json`: 14 pairs — `footer.directions` 1,
`menu.modifiers` 6, `stack.modifiers` 6, `tab.modifiers` 1. The 7 under `menu` and `tab` are
not genuine: the probe puts the class on the component element while DaisyUI puts `menu-active`
and `tab-active` on a child, so no case renders differently. After the parameters move to the
element that wears them, those 7 re-measure as `exclusive` or `compose`; the decision above
applies to whatever `same` remains.

*Wrong if:* a `same` pair appears in DaisyUI's documentation with both classes on one element —
it then composes measurably and the verdict, not the rule, was wrong.

*Tested 2026-09-19 and it did not fire.* All 14 `same` pairs — still exactly `footer.directions`
1, `menu.modifiers` 6, `stack.modifiers` 6, `tab.modifiers` 1 — were checked against every
`$$`-marked element on all 68 pages, and no element wears both members of any of them. The
browser measurement and the documentation agree, from two independent directions.

**Verified** that the apparent non-transitivity under `tooltip.placements` was an instrument
artefact, not a property of the classes. `tooltip-top` declares exactly what `.tooltip` already
sets (`tooltip.css:20-21` against `:76-77`), so it changes nothing; the probe calls a pair
`exclusive` when "together equals one alone", and a class that changes nothing equals everything
alone. Its six `exclusive` verdicts against the other members are that artefact. With inert
members removed, the exclusive graphs of `indicator`, `toast` and `tooltip` each fall into
exactly two connected components with all 3×3 cross-pairs composing, and `dropdown` into
`{start, center, end}`, `{top, bottom}` and two singletons — `top|bottom` having measured
`exclusive` since the measurement was first committed, while the configuration declared both as
booleans.

**Verified** that single-axis names carry no information beyond the category: 13 of the 14
configured single-axis names were the component plus DaisyUI's category word, and the fourteenth
(`MaskShape`) was the one invented name.

**Verified** that DaisyUI documents the axis word for three of the four multi-axis groups, read
2026-09-15: `docs/utilities/+page.md:152-268` is a table of CSS custom properties with a
description each — `--anchor-v` "vertical position of the anchor", `--anchor-h` "horizontal
position of the anchor", `--indicator-y` / `--indicator-x`, `--toast-y` / `--toast-x` likewise —
and in `indicator.css`, `toast.css`, `dropdown.css` every member of one axis sets only the `-y`/`-v`
property and every member of the other only `-x`/`-h`. The tooltip's properties (`--tt-trans`,
`--tt-inset`) are described as "transform offset" and "inset position" with no direction word,
and correctly so: they feed `translateX`/`inset-inline` under `tooltip-top` and
`translateY`/`inset-block` under `tooltip-left` (`tooltip.css:76,113` against `:171,174`), so the
alignment axis is relative to the side. The tooltip's two names are the only ones a human writes.

*Wrong if:* the utilities table drops a row or changes a description, or a DaisyUI release adds a
multi-axis group whose properties the table describes with a word other than vertical or
horizontal. Both fail generation until a name is configured or the derivation is extended.

**Verified** by re-measuring on 2026-09-15 (task 8.2), which **refuted** the earlier assumption
that only `tooltip-top` would read inert: 25 members in 17 groups did, and not one of the 310
pair verdicts changed. An inert member is a default (`loading-spinner`, `mask-square`,
`alert-horizontal`) or a class the probe cannot see on the container (`menu-active`,
`tab-active`, `rating-hidden`). Both are honest, so the derivation places every inert member
rather than treating it as rare: with the clique it is exclusive with every member of, then by
declared properties, then it fails.

**Verified** that the derivation, computed over all 44 groups, reproduces every enum 0.6.0
declared and adds four: `dropdown.placements` gains `{top, bottom}`, and `alert.styles`,
`avatar.modifiers` and `badge.styles` — the same shape, a clique beside members that compose
with everything — gain theirs. Decided (Oliver, 2026-09-15): one rule for every group; no
measurement separates those three from dropdown.

*Wrong if:* a member reads inert in one DaisyUI release and not the next without its CSS
changing — the baseline would then be measuring the example rather than the class.

*Wrong if:* a group every pair of which measures exclusive nevertheless has a documented call site
wearing two of its members at once. The measurement would then be describing the probe's markup
rather than the CSS, and the failing case is the correction.

#### Scenario: An exclusive group becomes one parameter

- **WHEN** every pair in a group measures `exclusive`
- **THEN** the generated wrapper takes one enum-typed parameter for the whole group
- **AND** naming two members of it at once is a compile error rather than a silent no-op

#### Scenario: A composing group stays boolean

- **WHEN** any pair in a group measures `compose` or `same`
- **THEN** each class stays an individual boolean parameter
- **AND** the combination DaisyUI permits remains expressible

#### Scenario: A single-axis enum is named without configuration

- **WHEN** a group's non-inert members form one clique
- **THEN** the enum is named by the component and DaisyUI's category word — `LoadingStyle`,
  `MaskStyle`, `AlertDirection` — with no configured entry

#### Scenario: A group with two independent axes

- **WHEN** a group's exclusive graph over non-inert members has more than one component of two or
  more members, as `indicator`, `toast`, `tooltip` and `dropdown` do
- **THEN** each such component becomes one enum and a singleton component stays boolean

#### Scenario: An axis is named from DaisyUI's property table

- **WHEN** every CSS custom property an axis's members declare is described in DaisyUI's
  utilities table with the same direction word, and the group's other axis with a different one
- **THEN** the enum is named by that word — `IndicatorVerticalPlacement`,
  `ToastHorizontalPlacement`, `DropdownVerticalPlacement` — with no configured entry
- **AND** a configured name for such an axis fails the build

#### Scenario: A measurement nobody named

- **WHEN** the measurement derives more than one axis for a group, the property table yields no
  direction word for them, and no name is configured
- **THEN** generation fails naming the group and each axis's members, because only a human can
  say what each axis is called — as for `tooltip`, whose alignment is relative to its side

#### Scenario: An inert member has a home

- **WHEN** a member measures `inert` in a multi-axis group
- **THEN** it joins the axis whose members declare the CSS properties it declares, as
  `tooltip-top` joins the side axis
- **AND** an inert member matching neither axis, or both, fails the build

#### Scenario: A connected component that is not a clique stays boolean

- **WHEN** a connected component of the exclusive graph holds a pair that does not measure
  `exclusive`, as `button.styles` does
- **THEN** every member of that component stays a boolean, because the group is not
  established as a choice — the same rule as for a composing group

#### Scenario: A clique beside composing members is an enum

- **WHEN** a group holds a clique of two or more beside members that compose with everything,
  as `dropdown.placements`, `alert.styles`, `avatar.modifiers` and `badge.styles` do
- **THEN** the clique becomes one enum and the other members stay boolean
- **AND** the rule is the same for every group; no group is exempt by being unlisted

#### Scenario: A new DaisyUI class does not become a boolean by omission

- **WHEN** an upstream release adds a class to a measured group
- **THEN** the build fails until the measurement is re-run, so the class is placed by the browser
  rather than by omission

### Requirement: A typed class can be applied at a Tailwind variant

> **SCOPE, 2026-09-13: BUILT AND PARKED, NOT SHIPPED.** Everything this requirement describes
> exists in `lib/src/…/core/Variants.kt` and is exercised by `VariantsTest.kt`, but `Variant`,
> `Breakpoint`, `State`, `DrawerVariant`, `at` and `and` are `internal`. Only `ClassValues`, the
> parameter type, is public. The scenarios below therefore describe a capability that compiles
> inside the module and that **no consumer can reach**. See the refutation below for why, and the
> gate for unparking.

A DaisyUI class this library exposes as a typed value SHALL be applicable at a Tailwind variant —
a breakpoint, a state, or DaisyUI's own — without leaving the type system for a raw string.

Several applications of the SAME group SHALL be combinable, so that DaisyUI's documented
responsive pattern is expressible. Combinations drawn from DIFFERENT groups SHALL NOT be
expressible.

**REFUTED 2026-09-13, by the thing this requirement exists to enable.** `at()` was built first and
measured against the CSS afterwards. A call site of `at(Breakpoint.Xl, ButtonSize.Lg)` renders
`class="btn xl:btn-lg"` and leaves the compiled stylesheet **byte-identical** — sha256 `3ac649e1`,
412052 bytes, before and after. Tailwind emits CSS per candidate **string** found while scanning
files as text; a class composed at run time from two halves appears in no file. `extraClasses =
"lg:btn-lg"` works precisely because the literal IS the candidate.

A public `at` would therefore hand a caller a class attribute that styles nothing, with no compile
error and no log — the exact failure this library exists to prevent. Hence the parking.

**The gate for unparking:** a build-time extractor over the CONSUMER's compiled classes, emitting
only the combinations that consumer names. Verified feasible at its crux the same day — a
consumer's constant pool carries a `Fieldref` to `ButtonSize.Lg` AND to `Breakpoint.Xl`, so no
dataflow analysis is needed. It is its own change. Unparking is then a visibility flip, which is
why `ClassValues` is public already.

**Corrected 2026-09-14:** this requirement previously read *"Variants that Tailwind stacks SHALL be
expressible together; combinations that cannot hold at once SHALL NOT be expressible."* Both
clauses were wrong about what was built.

Stacking a variant ON a variant — `at(Breakpoint.Lg, at(Breakpoint.Md, x))`, yielding `lg:md:x` —
**is** expressible and was deliberately left so. Forbidding it would need a second type for the
un-prefixed case, and under this change's asymmetric-cost rule permitting a useless combination is
the cheap direction. `lg:md:x` styles nothing, which costs a caller a no-op; making a reachable
combination inexpressible costs the library its purpose.

What is actually prevented is a **cross-group** combination — `ButtonSize.Lg and
ToastVerticalPlacement.Top`, a button size answered with a toast placement. `ClassValues<T>` is
invariant in `T` for that reason; under `out T` the compiler infers the common supertype `Any` and
the combination compiles. Checked both ways.

**Corrected 2026-09-14:** the scenario *"Two variants that cannot hold at once — applying a class
at two breakpoints does not compile"* asserted the opposite of both the built API and DaisyUI's own
documentation. `btn-xs sm:btn-sm md:btn-md lg:btn-lg xl:btn-xl` is one class at five breakpoints
and is the documented responsive pattern; `and` exists precisely to express it. The scenario is
replaced by the two below.

**Verified** that this is where the escape hatch is actually used. Measured 2026-09-12 over every
`extraClasses` and `addClassNames` call in `example-app`, `e2e-tests`, `lib/src` and `docs`: of 47
distinct tokens, 12 are known DaisyUI classes and **10 of those 12 carry a variant prefix** —
`lg:btn-lg`, `max-sm:megamenu-vertical`, `xl:stats-horizontal`. The remaining 35 are Tailwind
utilities this library does not type.

**Verified** that the alternative shape does not solve it. Of DaisyUI's 555 classes, **536 are
already reachable** through an enum entry or boolean parameter on their own component, so an enum
over all classes would restate the existing API and still not express a prefix.

**Verified 2026-09-14** (was Assumed) that the variant set can be closed. Measured over all 68
component pages: DaisyUI's documented examples apply 8 distinct variants to its own classes — `sm`,
`md`, `lg`, `xl`, `max-sm`, `max-md`, `max-lg` and `is-drawer-close` — every one of them inside the
closed set. No `group-*`, no `peer-*`, no arbitrary `data-[…]`. The assumption's *wrong if* did not
fire.

**Resolved 2026-09-13** (was Assumed, *"that stacking is worth supporting"*): dropped. The
measurement found no call site combining two variants on one class, so nothing supports it beyond
the cost of forbidding it — and it is permitted rather than supported, which is a different claim.

**Assumed:** that the ten breakpoints are the right closed set rather than the seven observed.
*Wrong if:* a consumer needs a Tailwind variant this library does not name and, having no variant
escape hatch by decision, cannot reach it — the failing report is the correction. `2xl` is in the
set on exactly this reasoning and appears in no DaisyUI example.

#### Scenario: A size applied at a breakpoint

- **WHEN** a caller applies a typed size at a breakpoint
- **THEN** the rendered class carries the prefix, e.g. `lg:btn-lg`
- **AND** no raw string appears at the call site

#### Scenario: A variant that cannot be misspelled

- **WHEN** a caller names a breakpoint or state
- **THEN** it is a typed value, so a misspelling is a compile error rather than a class that
  silently styles nothing

#### Scenario: One class at several breakpoints

- **WHEN** a caller combines several applications of the same group, as in
  `ButtonSize.Xs and at(Breakpoint.Sm, ButtonSize.Sm) and at(Breakpoint.Lg, ButtonSize.Lg)`
- **THEN** every class is rendered, in the order written
- **AND** this is the pattern DaisyUI documents as `btn-xs sm:btn-sm md:btn-md lg:btn-lg xl:btn-xl`

#### Scenario: Two groups that cannot answer one another

- **WHEN** a caller combines an application of one group with an application of another, such as a
  button size with a toast placement
- **THEN** it does not compile

#### Scenario: The escape hatch is unchanged

- **WHEN** a caller passes a Tailwind utility such as `col-span-12`
- **THEN** `extraClasses` accepts it exactly as before, because typing Tailwind's utilities is not
  this library's business

#### Scenario: The parked API is unreachable from outside the library

- **WHEN** a consumer of the published jar tries to name `at`, `Breakpoint`, `State` or
  `DrawerVariant`
- **THEN** it does not resolve, and none of them appears in `lib/api/lib.api`
- **AND** `ClassValues` DOES appear there, as the parameter type of every enum-backed parameter
- **AND** a variant-prefixed class remains reachable as a literal through `extraClasses`, which is
  the form Tailwind can actually see

> **NOT YET FALSIFIABLE, 2026-09-14 — recorded rather than left to look verified.** `lib/api/lib.api`
> was last dumped on 2026-09-12 (`3bc0cfb`), which predates `ClassValues` and every parked name. So
> the first clause passes today for the wrong reason: `at` is absent because the baseline is stale,
> not because it is internal. The second clause is what makes the scenario falsifiable at all — it
> currently **fails**, since `ClassValues` appears 0 times and `daisyMenu` is still recorded as
> `(…MenuSize;ZZZZZZZ…)`, the pre-enum signature. Re-dumping the baseline is this change's own
> remaining task; until it runs, this scenario is a claim and not a check.
>
> This is the third instance in this change of the same failure: an assertion that passes for a
> reason other than the one it names. The other two were a substring grep where `max-lg:btn-lg`
> satisfied `lg:btn-lg`, and a KDoc comment keeping `lg:btn-lg` alive as a Tailwind candidate.

### Requirement: A class is declared on the function whose element wears it

Every DaisyUI class a generated function emits SHALL land on an element DaisyUI's own
documentation puts it on. The codegen SHALL compare, for every documented class, the element the
function declaring it renders against **every** element carrying that class in the documented
markup, and SHALL fail generation on a disagreement that has no recorded exception. A class a
function writes on every call SHALL NOT also be offered as a parameter of another function.

An exception SHALL name a reason and a tracking issue, SHALL state the condition under which the
disagreement would go away, and SHALL fail generation the moment it does. The condition is what
separates an exception deferring a defect from one recording a deliberate divergence; without it
the two are one list and the second kind quietly outlives its reason.

The authority is the same as for the component element: the fenced ```html examples of
`packages/docs/src/routes/(routes)/components/<name>/+page.md`, in which every daisyUI class is
marked `$$`. The marker is what makes the element of any class readable without inference.

**Verified** the defect exists and its extent, measured 2026-09-14 over all 68 component pages by
parsing the markup rather than reading frontmatter categories: 414 marked classes, 80 never on the
element carrying the component class, and of those **13 across 5 components are parameters on
the container** while DaisyUI puts them on a child — `dock-active`; the six `indicator-*`
placements; `list-col-grow`, `list-col-wrap`; `menu-active`, `menu-disabled`,
`menu-dropdown-show`; `tab-content`. A further 18 sit on a child and are reachable from no
function at all. The defect had been found by hand three times (#342, #347, the deferred block 6
of `apply-classes-at-variants`) and each time only because someone looked.

**Verified** that the category-to-function rule this replaces cannot be repaired by reading: a
frontmatter category says what a class *means*, not which element wears it, and `indicator` and
`toast` carry the identical two-axis category shape with the class on different elements.

**Verified, and it corrected this requirement** — measured 2026-09-19: DaisyUI documents many
classes on SEVERAL elements, choosing by the surrounding HTML content model rather than
arbitrarily. `badge` is a `<div>` 47 times and a `<span>` 7, and all seven `<span>`s sit inside an
`<h1>`–`<h5>` or a `<p>`, where a `<div>` is invalid HTML. `dropdown` is documented on `<div>`,
`<details>` and `<ul>`; `skeleton` on `<div>` and `<span>`. This requirement first said "**the**
element", singular, and on that reading 47 disagreements were all defects. Against the set, 19 of
them were never disagreements at all.

**Verified** that one generated function cannot follow DaisyUI here, tested 2026-09-19 in the
Kotlin REPL: `createHTML().h1 { div { } }` compiles and renders, because kotlinx.html makes `H1`,
`P` and `BUTTON` themselves `FlowContent` — the DSL does not model content categories. An overload
pair on `FlowContent` and `PhrasingContent` is ambiguous inside any tag that is both, which is all
of them. So where a component's documented set has more than one member, the generator picks one
and the classes documented only on the others diverge permanently.

*Wrong if:* DaisyUI stops marking classes with `$$`, or documents the same class on the container
in one example and on a child in another with no wrapper in between. The first makes the check
fail rather than answer; the second would need a per-example rule the check does not have, and
the failing component is the correction.

*This falsifier fired on 2026-09-19, and not in either shape it predicted.* What actually happened
is the paragraph above: the same class on several elements of the same example, chosen by context.
The prediction was about a container and a child disagreeing; the reality was one element wearing
the class in two valid ways. A falsifier that fires in an unforeseen shape is the requirement
being corrected, which is what it is for.

#### Scenario: Every class sits where DaisyUI puts it

- **WHEN** the codegen runs and every emitted class lands on the element DaisyUI documents for it
- **THEN** generation succeeds

#### Scenario: A parameter would emit a class onto the wrong element

- **WHEN** a function's parameter emits a class that DaisyUI documents on a different element
- **AND** no exception is recorded for that class
- **THEN** generation fails, naming the class, the declaring function's element and the documented
  one

#### Scenario: A misplaced parameter moves to the element that wears the class

- **WHEN** DaisyUI documents a class on a child the library already generates a function for
- **THEN** the parameter is declared on that function and removed from the container's

#### Scenario: DaisyUI documents the class on several elements

- **WHEN** DaisyUI shows a class on more than one element
- **AND** the declaring function renders one of them
- **THEN** generation succeeds, because DaisyUI itself offers that element for the class

#### Scenario: The component's element excludes a class documented only elsewhere

- **WHEN** a component renders one of the several elements DaisyUI documents it on
- **AND** one of its modifier classes is documented only on the others
- **THEN** generation fails without an exception, and the exception that resolves it records the
  measurement and the condition under which the divergence would end — not a date by which it
  will be fixed, because one function renders one element

#### Scenario: A construction writes a class, so no parameter offers it

- **WHEN** a generated function writes a class on every call
- **THEN** no other function offers that class as a parameter, so the class cannot be asked for on
  an element DaisyUI does not show it on

#### Scenario: A misplaced parameter has no function to move to

- **WHEN** DaisyUI documents a class on a child the library generates no function for
- **THEN** the class gets the function it needs, or an exception with a reason and a tracking
  issue, and is not left on the container

### Requirement: A generated parameter is named after the DaisyUI class it emits

A boolean parameter SHALL be named by the DaisyUI class suffix it emits, converted to camelCase
and nothing else: `hidden` for `rating-hidden`, `hover` for `dropdown-hover`. The codegen SHALL
carry no configured mapping from a DaisyUI class to a differently named parameter. The
parameter's documentation SHALL be DaisyUI's own description of the class.

**Verified** the cost of the alternative, read 2026-09-15: after `apply-classes-at-variants`
renamed five booleans, the generated documentation for `clearOption` in
`lib/generated/main/kotlin/io/github/ollin/kdaisyui/components/Rating.kt:37` never named
`rating-hidden` — only the function body did — so a reader holding DaisyUI's documentation had
nothing to search for. The five renames were 0.6.0 API and 0.6.0 is untagged, so withdrawing
them changes nothing that shipped.

**Verified** that DaisyUI's description already explains the cases a rename was meant to fix:
`rating-hidden`'s `desc` reads "For the first radio to make it hidden so user can clear the
rating" (`rating/+page.md:14`), and that text is what the generator emits as the parameter's
documentation.

*Wrong if:* a DaisyUI class suffix camelCases to a Kotlin keyword or to a name that collides with
another parameter of the same function. Keywords are already escaped; a collision would fail
compilation of the generated sources, which is the correction.

*Checked 2026-09-19 and this requirement holds in the shipped output:* `codegen-config.json`
carries no `parameterNames` section and no code reads one, and `Rating.kt` declares `hidden` with
DaisyUI's own sentence as its documentation. This is the one requirement of the four that the
day's measurements confirmed rather than corrected.

#### Scenario: A boolean carries its class's name

- **WHEN** a component declares a boolean for a DaisyUI class
- **THEN** the parameter is the class suffix in camelCase, and its documentation is DaisyUI's
  description of that class

#### Scenario: No parameter is renamed by configuration

- **WHEN** the codegen configuration is read
- **THEN** it contains no section mapping a DaisyUI class to a parameter name
