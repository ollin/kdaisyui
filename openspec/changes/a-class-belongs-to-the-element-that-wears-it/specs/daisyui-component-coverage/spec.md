## ADDED Requirements

### Requirement: A class is declared on the function whose element wears it

Every DaisyUI class a generated function emits SHALL land on the element DaisyUI's own
documentation puts it on. The codegen SHALL compare, for every documented class, the element the
function declaring it renders against the element carrying that class in the documented markup,
and SHALL fail generation on a disagreement that has no recorded exception. An exception SHALL
name a reason and a tracking issue, and SHALL fail generation the moment the disagreement it
covers goes away.

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

*Wrong if:* DaisyUI stops marking classes with `$$`, or documents the same class on the container
in one example and on a child in another with no wrapper in between. The first makes the check
fail rather than answer; the second would need a per-example rule the check does not have, and
the failing component is the correction.

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

#### Scenario: A misplaced parameter has no function to move to

- **WHEN** DaisyUI documents a class on a child the library generates no function for
- **THEN** the class gets the function it needs, or an exception with a reason and a tracking
  issue, and is not left on the container

#### Scenario: The container and item of `tab` trade places

- **WHEN** `daisyTab` is called
- **THEN** it renders the container carrying `tabs`, and the item function renders the focusable
  element carrying `tab`, with `tab-active` and `tab-disabled` declared on the item

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

#### Scenario: A boolean carries its class's name

- **WHEN** a component declares a boolean for a DaisyUI class
- **THEN** the parameter is the class suffix in camelCase, and its documentation is DaisyUI's
  description of that class

#### Scenario: No parameter is renamed by configuration

- **WHEN** the codegen configuration is read
- **THEN** it contains no section mapping a DaisyUI class to a parameter name

### Requirement: A join marks its items by the call, not by a parameter

Inside the content of `daisyJoin`, calling a component function that DaisyUI documents as a join
item SHALL emit that component with `join-item`, with nothing further to write. Outside
`daisyJoin`, and inside any lambda nested within a join's content, the same call SHALL emit no
`join-item`, and there SHALL be no typed way to write `join-item` there. The set of component
functions that behave this way SHALL be derived from DaisyUI's markup — every component class
that co-occurs with `join-item` on one element — and not configured.

**Verified** what `join-item` does, from `packages/daisyui/src/utilities/join.css`: `.join`
writes four corner-radius variables onto its direct children and `.join-item` reads them and
collapses shared borders. Without an enclosing `.join` the variables are unset and the class
strips the element's own corners — so `join-item` outside a join is harmful, not inert, and a
parameter writable anywhere would let a caller do that silently.

**Verified** the derived set and its completeness, measured 2026-09-15 over all 68 pages: the
classes co-occurring with `join-item` are `btn` (52), `input` (5), `card` (3), `collapse` (3),
`select` (1); and of 74 direct children of a `.join`, 71 carry `join-item` while the 3 that do not
are wrapper `<div>`s whose grandchild carries it (`join/+page.md:94-98`). A rule marking every DOM
child would therefore be wrong in 3 of 74 cases; a rule marking every component call in the
join's own scope, and nothing nested, is wrong in none.

**Assumed** that a member function on the join's scope type takes precedence over the top-level
extension of the same name, and that kotlinx.html's `@HtmlTagMarker` on that scope type hides it
inside nested lambdas — both are Kotlin's documented resolution rules ("Type-safe builders",
*Scope control*). *Wrong if:* a nested `daisyButton` inside a join's content emits `join-item`,
or `daisyButton` inside a join resolves ambiguously; the test pinning both is the correction.

*Wrong if:* DaisyUI documents `join-item` on an element inside a join whose component is not in
the derived set and that a caller cannot reach with `extraClasses`. The known limit — an element
DaisyUI does not document as a join item stays on `extraClasses` — is accepted, not hidden.

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

- **WHEN** a DaisyUI release documents `join-item` on a component it did not before
- **THEN** that component's function marks itself inside a join after regeneration, with no
  configuration edit

## MODIFIED Requirements

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
