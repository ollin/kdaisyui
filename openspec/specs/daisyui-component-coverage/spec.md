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
Regeneration against the pinned DaisyUI release SHALL surface every class DaisyUI adds to an existing component's frontmatter, by failing generation until the component model places it: as an axis member, which becomes an enum entry, as a toggle, which becomes a boolean, or as a class not offered with a stated reason. Call sites that do not use the new class SHALL compile unchanged.

A *modifier* is a CSS class toggled on a component's existing element. An alternative *construction method*, which renders a different element, is not a modifier and cannot be expressed as a parameter; those are governed by the popover-modal requirement below.

**Corrected 2026-09-04:** this requirement previously also named "the `modal` popover attribute" among the modifiers to be surfaced. That was unbuildable: DaisyUI's popover modal is `<div class="modal" popover>`, a different root element from the `<dialog>` the wrapper emits, so no parameter on that wrapper could ever satisfy it. The clause was archived as satisfied while nothing implemented it, because no test and no drift check can detect a construction method that introduces no new class name.

**Corrected by `declare-the-component-model`:** a new class used to reach the API by classification, as a boolean unless a measurement made it an enum member. It now fails generation until a human places it in the model, because a class placed by omission is how the 0.6.0 dropdown shipped its placements twice.

**Assumed** that failing the generation run is the right strength for a new class. *Wrong if:* a routine DaisyUI patch release adds classes often enough that bumps stall on model edits nobody wanted to make.

#### Scenario: New modifier becomes available
- **WHEN** a DaisyUI modifier class is declared in an existing component's frontmatter
- **AND** the model places it
- **THEN** the regenerated wrapper exposes it as a new enum entry or parameter
- **AND** existing call sites that omit it compile unchanged

#### Scenario: A new class nobody placed
- **WHEN** a DaisyUI release adds a class the model does not place
- **THEN** generation fails naming the component and the class

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

### Requirement: A generated parameter is named after the DaisyUI class it emits

A toggle SHALL be named by the DaisyUI class suffix it emits, converted to camelCase and nothing
else: `hidden` for `rating-hidden`, `zebra` for `table-zebra`. An axis member SHALL be an enum
entry named by its class suffix in PascalCase: `ButtonStyle.Ghost` for `btn-ghost`. The axis
parameter and its enum are named by the rule in `component-model`, "Every generated name follows
one naming rule". The codegen SHALL
carry no mapping from a single class to a differently named parameter. The documentation of a
toggle or an enum entry SHALL be DaisyUI's own description of the class.

**Verified** the cost of renaming a class, read 2026-09-15: after `apply-classes-at-variants`
renamed five booleans, the generated documentation for `clearOption` never named `rating-hidden`,
so a reader holding DaisyUI's documentation had nothing to search for.

**Verified** that DaisyUI's description already explains the cases a rename was meant to fix:
`rating-hidden`'s `desc` reads "For the first radio to make it hidden so user can clear the
rating" (`rating/+page.md:14`).

**Verified** as a decision, Oliver, 2026-10-18: the axis name is a declared design choice. It
names a group of classes, such as `style`, `shape` or `width`, and is never a renamed class.

*Wrong if:* a DaisyUI class suffix camelCases to a Kotlin keyword or collides with another
parameter of the same function. Keywords are escaped; a collision fails compilation of the
generated sources, which is the correction.

#### Scenario: A boolean carries its class's name
- **WHEN** a component declares a toggle for a DaisyUI class
- **THEN** the parameter is the class suffix in camelCase, and its documentation is DaisyUI's
  description of that class

#### Scenario: An axis member carries its class's name
- **WHEN** a component declares an axis containing `btn-ghost`
- **THEN** the enum has an entry `Ghost` documented with DaisyUI's description of `btn-ghost`

#### Scenario: No parameter is renamed by configuration
- **WHEN** the component model is read
- **THEN** it contains no mapping from a DaisyUI class to a parameter or entry name

### Requirement: A join marks its items by the call, not by a parameter

Inside the content of `daisyJoin`, calling a component function that the component model declares
as a join item SHALL emit that component with `join-item`, with nothing further to write. Outside
`daisyJoin`, and inside any lambda nested within a join's content, the same call SHALL emit no
`join-item`, and there SHALL be no typed way to write `join-item` there.

The set of join items is declared in the model. It is bootstrapped from DaisyUI's markup, where
it was derived until `declare-the-component-model`, and a DaisyUI release that changes it shows up
in the bump's documentation delta.

**Verified** what `join-item` does, from `packages/daisyui/src/utilities/join.css`: `.join`
writes four corner-radius variables onto its direct children and `.join-item` reads them. Without
an enclosing `.join` the class strips the element's own corners, so `join-item` outside a join is
harmful, not inert.

**Verified** the set, re-measured 2026-09-19 at v5.7.17: the component classes co-occurring with
`join-item` are `btn`, `input`, `theme-controller`, `collapse`, `card`, `select` and `validator`.
Of 72 direct children of a `.join`, 69 carry `join-item`; the 3 that do not are wrappers whose
grandchild carries it.

**Verified 2026-09-19 in the Kotlin REPL against the real `:lib` classpath:** a member function on
the scope type takes precedence over the top-level extension; `@HtmlTagMarker` hides the scope
inside a nested lambda; `this@daisyJoin.` still reaches the member.

*Wrong if:* a nested `daisyButton` inside a join's content emits `join-item`, or `daisyButton`
inside a join resolves ambiguously; the test pinning both is the correction.

#### Scenario: A component call inside a join is a join item
- **WHEN** `daisyButton("A")` is called directly in the content of `daisyJoin`
- **THEN** the rendered button carries `btn join-item`

#### Scenario: A nested call is not a join item
- **WHEN** `daisyInput()` is called inside a `div { }` nested in the content of `daisyJoin`
- **THEN** the rendered input carries no `join-item`
- **AND** `this@daisyJoin.daisyInput()` at the same place renders it with `join-item`

#### Scenario: A join item cannot be written outside a join
- **WHEN** code outside `daisyJoin` tries to obtain a join-marking component call
- **THEN** it does not compile

#### Scenario: The item set follows DaisyUI
- **WHEN** a DaisyUI release documents `join-item` beside a component class it did not before
- **THEN** the bump's documentation delta names it
- **AND** that component marks itself inside a join once the model declares it

### Requirement: `tab`'s container and item are the elements DaisyUI names

The tab container function SHALL render the `<div role="tablist">` carrying `tabs`. Separate item
functions SHALL render the focusable element carrying `tab`, with `tab-active` and `tab-disabled`
declared on the items as toggles.

Folded in from `tab-container-and-item-trade-places` (#342). The generator picked `tab` for the
container from DaisyUI's first example, so the container wore the item's class and no item
function existed.

**Verified** the documented elements, counted 2026-10-18 at the pinned tag in
`components/tab/+page.md`: `tabs` sits on `<div>` 17 times; `tab` on `<a>` 30 times, on
`<input type="radio">` 19 times and on `<label>` 3 times. The radio form is the one DaisyUI's
`tab-content` panels need without JavaScript.

**Verified** 2026-10-18 that two item functions, one rendering `<a role="tab">` and one rendering
`<input type="radio" role="tab">`, cover what callers need: the example app renders no tabs before
this change, and fwb-ssr's client detail page builds `<div role="tablist" class="tabs tabs-lift">`
with `<a role="tab" class="tab tab-active">` items by hand. Neither uses the `<label>` form, which
wraps a radio together with an icon; a caller that needs it is what would reopen this.

#### Scenario: The container and items trade places
- **WHEN** a tab bar is built with the container function and two item functions
- **THEN** the container renders `<div role="tablist" class="tabs">`
- **AND** each item renders an element carrying `tab`

#### Scenario: An active item
- **WHEN** an item function is called with `active = true`
- **THEN** that item carries `tab tab-active`, and the container carries neither class

### Requirement: Each generated function renders the one element its model declares

Every generated rendering function SHALL render the one element its model entry declares. A class
sibling and a class-only function render no element; they add classes to the one they are called
on. A class a function writes on every call SHALL NOT also be offered as a parameter of another
function.

DaisyUI documents many classes on several elements. A function renders one of them; every other is
reached typed through the function's class sibling on that element, such as
`a { daisyButtonClasses() }`, and the generated test for each documented element renders and
asserts DaisyUI's own tag, since `test-every-documented-tag`. Which tag a function renders by
default stays a model decision.

The authority stays the fenced ```html examples of
`packages/docs/src/routes/(routes)/components/<name>/+page.md`, in which every daisyUI class is
marked `$$`.

Replaces "The model places each class on an element DaisyUI documents it on", which said the
model records the divergences; it records none.

**Verified** how many, measured 2026-10-18 at the pinned tag: of 2,571 marked elements, 322 in 80
distinct shapes carry classes a function expresses but sit on another element than that function
renders. The largest are `mask` on `<input>` (60, the rating stars), `badge` on `<div>` (47), `btn`
on `<a>`, `<input>`, `<label>`, `<div>` and `<summary>` (about 90 together), and `dropdown` on
`<div>` (29, the popover form #357 asks for).

**Verified** the defect this requirement was first written against, measured 2026-09-14 over all
68 component pages: 13 classes across 5 components were parameters on the container while DaisyUI
puts them on a child. That was fixed by `a-class-belongs-to-the-element-that-wears-it`.

**Verified** that no class is both written on every call by one function and offered as a
parameter by another, read from `codegen/model/` on 2026-10-18.

#### Scenario: A DaisyUI release moves a class to another element
- **WHEN** the newer tag documents a class on a different set of elements
- **THEN** the bump's documentation delta names the class and both element sets
- **AND** the element a function renders changes only by an edit to the model

#### Scenario: A construction writes a class, so no parameter offers it
- **WHEN** a generated function writes a class on every call
- **THEN** no other function offers that class as a parameter

#### Scenario: A class on another element than DaisyUI's
- **WHEN** DaisyUI shows `<a class="$$btn">` and `daisyButton` renders `<button>`
- **THEN** generation succeeds, and the documented-element test writes `a { daisyButtonClasses() }`
  and asserts the `a` and its classes

#### Scenario: The calendar renders its web component
- **WHEN** `daisyCalendar { }` is rendered
- **THEN** the output is `<calendar-date class="cally">`, the element the cally web component
  defines, and not a `<div>`

### Requirement: No function offers a class for an element it does not render

A generated function SHALL NOT offer, as a parameter, a class DaisyUI's examples put only on
another element than the one the function renders, such as a child. Such a class is offered by the
function, class sibling or class-only function for that element.

Written against #347. **Verified** the defect, read 2026-10-18: `daisyMenu` offers `active`,
`disabled` and `dropdownShow`, `daisyDock` offers `active`, `daisyRating` offers `hidden`, and
`daisyList` offers `column`. Each writes its class on the container, and DaisyUI puts every one of
them on a child (`<a>` or `<li>` in a menu, the dock's `<button>`, the rating's first `<input>`,
the children of a `list-row`), where its CSS applies. The gate's documented-element tests found
all eight.

#### Scenario: A menu's active item
- **WHEN** a caller marks the active entry of a menu
- **THEN** it does so on the item, through `daisyMenuItemClasses(active = true)`, and `daisyMenu`
  has no `active` parameter

#### Scenario: A dock's active item
- **WHEN** a caller marks the active button of a dock
- **THEN** the class lands on that button, and `daisyDock` has no `active` parameter
