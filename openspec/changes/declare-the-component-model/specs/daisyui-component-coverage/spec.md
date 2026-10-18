## MODIFIED Requirements

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

### Requirement: A generated parameter is named after the DaisyUI class it emits

A toggle SHALL be named by the DaisyUI class suffix it emits, converted to camelCase and nothing
else: `hidden` for `rating-hidden`, `zebra` for `table-zebra`. An axis member SHALL be an enum
entry named by its class suffix in PascalCase: `ButtonStyle.Ghost` for `btn-ghost`. The axis
parameter and its enum are named by the axis name the component model declares. The codegen SHALL
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

## ADDED Requirements

### Requirement: The model places each class on an element DaisyUI documents it on

Every DaisyUI class a generated function emits SHALL land on an element DaisyUI's own
documentation puts it on, except where the component model records a deliberate divergence with
its reason. A class a function writes on every call SHALL NOT also be offered as a parameter of
another function.

Placement is declared in the component model and is no longer cross-checked during generation.
It is checked once, when the model is bootstrapped, by the cross-check that the requirement "A
class is declared on the function whose element wears it" ran on every generation. After that, a DaisyUI release that moves a class shows up in the bump's
documentation delta, and the model is edited.

The authority stays the fenced ```html examples of
`packages/docs/src/routes/(routes)/components/<name>/+page.md`, in which every daisyUI class is
marked `$$`.

**Verified** the defect this requirement was written against, measured 2026-09-14 over all 68
component pages: 13 classes across 5 components were parameters on the container while DaisyUI
puts them on a child. That was fixed by `a-class-belongs-to-the-element-that-wears-it`.

**Verified** that DaisyUI documents many classes on several elements, choosing by the surrounding
HTML content model, measured 2026-09-19: `badge` is a `<div>` 47 times and a `<span>` 7 times, all
seven inside an `<h1>`–`<h5>` or a `<p>`. kotlinx.html cannot pick an element by context, so one
function renders one element, and the classes documented only on the others diverge. The model
records `badge` (#356) and `dropdown` (#357) as such divergences.

**Assumed** that the cross-check passes against the bootstrapped model with only the divergences
that today carry exceptions. *Wrong if:* the bootstrap check reports a disagreement that no
current exception covers.

#### Scenario: The bootstrapped model places every class where DaisyUI puts it
- **WHEN** the model is bootstrapped and checked once against DaisyUI's marked examples
- **THEN** every disagreement is one the model records as a deliberate divergence with a reason

#### Scenario: A DaisyUI release moves a class to another element
- **WHEN** the newer tag documents a class on a different set of elements
- **THEN** the bump's documentation delta names the class and both element sets
- **AND** the placement changes only by an edit to the model

#### Scenario: A construction writes a class, so no parameter offers it
- **WHEN** a generated function writes a class on every call
- **THEN** no other function offers that class as a parameter, because the model places each class
  exactly once

#### Scenario: A misplaced parameter has no function to move to
- **WHEN** DaisyUI documents a class on a child the library generates no function for
- **THEN** the class gets the function it needs, or stays where it is under a tracking issue, and
  the model says which

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

**Assumed** that two item functions, one rendering `<a role="tab">` and one rendering
`<input type="radio" role="tab">`, cover what callers need. *Wrong if:* the example app or fwb-ssr
needs the `<label>` form, which wraps a radio together with an icon.

#### Scenario: The container and items trade places
- **WHEN** a tab bar is built with the container function and two item functions
- **THEN** the container renders `<div role="tablist" class="tabs">`
- **AND** each item renders an element carrying `tab`

#### Scenario: An active item
- **WHEN** an item function is called with `active = true`
- **THEN** that item carries `tab tab-active`, and the container carries neither class

## REMOVED Requirements

### Requirement: The generated element matches the one DaisyUI documents

**Reason:** each function's element is declared in the component model and no longer inferred, so
there is no inference left to cross-check on every run. The cross-check needed a standing list of
exceptions, 30 entries at removal, for divergences that are deliberate decisions.

**Migration:** the element of every component is checked once against DaisyUI's documentation
when the model is bootstrapped. After that, a DaisyUI release that changes the element it
documents shows up in the bump's documentation delta, under the requirement "A DaisyUI bump is
read as a documentation delta" in `component-model`.

### Requirement: A mutually exclusive class group is one typed choice

**Reason:** which classes share an axis is now declared in the component model as a design
decision. A browser measurement no longer decides it. The measurement, `codegen/exclusivity.json`
with its probe and its verification task, is deleted.

**Migration:** see "A class group is offered as the axes the model declares" in `component-model`.
Every enum that exists in 0.7.0 keeps its name and entries. Class-bearing booleans that belong to
a group become an axis.

### Requirement: A class is declared on the function whose element wears it

**Reason:** placement is declared in the component model, so the per-run cross-check and its
exception list go. The property the requirement protected is kept, under a new name, because its
mechanism changed from a gate on every run to a check at bootstrap plus the bump's delta.

**Migration:** see "The model places each class on an element DaisyUI documents it on" in this
capability.
