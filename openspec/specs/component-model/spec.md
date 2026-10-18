# component-model Specification

## Purpose
The generated component API is declared in a committed, reviewed model rather than inferred from
DaisyUI's documentation, so a structural fact changes only by an edit someone reads, and a DaisyUI
release is read as a delta against that model.

## Requirements

### Requirement: The component API is declared in a committed model

Every structural fact the generators emit SHALL come from a committed model with one file per
DaisyUI component: the functions and their names, each function's element and receiver, the
classes it always writes, its axes and their members, its toggles, its static attributes, its
extra parameters, whether it takes inline text, whether it is a join item, and the component's
editorial summary. From DaisyUI, the generators of the library code, the reference pages,
`llms.txt` and the API dump SHALL read only the frontmatter: the class names and their
descriptions. They SHALL NOT read the fenced HTML examples.

The component-test generator is the one reader of the examples that stays. It turns each
documented example into a call against the declared API and asserts the classes rendered, which
makes the examples test input for the model rather than a source of it. It SHALL take every
parameter binding from the model, never from its own classification.

Whether a function takes content stays derived from its declared element by the HTML
specification's void-element list. That is a rule of HTML, not an inference about DaisyUI.

**Verified** that the model carries every fact today's inferred shape holds, 2026-10-18 (task 1.4):
the 68 committed model files rebuild all 66 component shapes deep-equal to the inferred ones, the
join scope included, and reproduce every main function's extras, text flag, role and input type,
every editorial summary and both skip reasons. A corrupted model file fails that test and names the
component. Shape equality is what the emitters render from, so generation cannot differ; block 3
still regenerates after every switch, because the emitters also read the configuration until then.

It took no field beyond the eleven listed in task 1.2 and 1.4, and 65 of 66 components round-tripped
before the join scope was modelled.

#### Scenario: Generating without reading example markup

- **WHEN** the generators of the library code, the reference pages, `llms.txt` and the API dump run
- **THEN** they read the model and the frontmatter
- **AND** no fenced HTML example from DaisyUI's documentation is read

#### Scenario: A documented example becomes a test against the model

- **WHEN** the component-test generator reads a documented example
- **THEN** it builds the call from the model's parameters
- **AND** a class the model does not offer on that function is not silently dropped from the test

#### Scenario: A structural fact changes only by a model edit

- **WHEN** a component must render a different element
- **THEN** the change is an edit to that component's model file
- **AND** the regenerated Kotlin, reference page and API dump follow from it

#### Scenario: The bootstrapped model reproduces today's output

- **WHEN** the model is first written from the inferred shape and the generators switch to it
- **THEN** regeneration produces no difference in any committed generated file

### Requirement: The model accounts for every class DaisyUI declares

Generation SHALL fail, naming the component and the class, unless every class in a component's
frontmatter is placed in its model: as a function's own class, a part's class, an axis member, a
toggle, a class an extra parameter or a construction writes, or an entry listed as not offered with
a reason. Generation SHALL fail when one function offers the same class twice. Generation SHALL
also fail when the model names a class the frontmatter does not declare, when a DaisyUI component
directory has no model file and is not listed as skipped, and when a model file names no existing
directory.

A class MAY be placed on several functions. Measured 2026-10-18 before the gate was written:
`modal` and `skeleton` are each the own class of two constructions, and `timeline-box` is a toggle
on three parts. All three are correct. The defect the duplicate check exists for is one function
offering one class twice.

This replaces three guards with one set comparison: the unread-configuration guard, the
offered-twice guard and the measured-group completeness check.

**Verified** that the removal direction matters: `mask-square` stayed in the generated API from
DaisyUI 5.0 until 5.7.19 removed it from the documentation, and was withdrawn in kdaisyui only on
the 5.7.42 bump (README, "How to migrate from 0.5.x", item 12).

**Verified** that the placed-twice direction matters: 0.6.0 shipped `daisyDropdown` with five
placement booleans beside the enums that replaced them (README, "How to migrate from 0.6.x", item
1).

**Verified** that the frontmatter is a complete class source, measured 2026-10-18 at the pinned tag
(task 1.1): of every `$$`-marked class in the fenced examples of all component pages, none is
missing from every frontmatter. 291 marked classes are absent from their own page's frontmatter,
and all of them are other components' classes used inside an example, such as `btn` inside a card.
The only seven declared nowhere are not component classes: six variant-prefixed drawer tokens such
as `is-drawer-close:$$tooltip`, which the parser reads as one token, and `rounded-box`, a DaisyUI
utility.

*Wrong if:* a later DaisyUI release marks a component class in an example that no frontmatter
declares. The bump report's class delta is built from the frontmatter, so it would not name such a
class; re-running this measurement on a bump is the check.

#### Scenario: DaisyUI adds a class

- **WHEN** the pinned frontmatter declares a class the model does not place
- **THEN** generation fails naming the component and the class

#### Scenario: DaisyUI removes a class

- **WHEN** the model places a class the pinned frontmatter no longer declares
- **THEN** generation fails naming the component and the class

#### Scenario: A class is placed twice

- **WHEN** one function offers one class in two roles, such as a toggle and an axis member
- **THEN** generation fails naming the function and the class

#### Scenario: One class on several functions

- **WHEN** two constructions of one component both write the component's class
- **THEN** generation succeeds

#### Scenario: DaisyUI adds a component

- **WHEN** the submodule has a component directory with neither a model file nor a skip entry
- **THEN** generation fails naming the directory

### Requirement: A DaisyUI bump is read as a documentation delta

A recipe SHALL report, for two DaisyUI tags, what changed in the component documentation between
them: components added or removed; classes added or removed per component; per class, the set of
elements DaisyUI's marked examples put it on; and the set of component classes documented beside
`join-item`. The report SHALL NOT fail the build. It is read during an upgrade, and the model is
edited by hand.

The report compares DaisyUI with itself, not with the model, so a deliberate divergence such as
`daisyBadge` rendering `<span>` (#356) needs no exception: it does not change between releases.

**Verified** in part, 2026-10-18 (task 2.6): replaying the 5.7.17 to 5.7.42 bump reports exactly one
change, `mask-square` removed from `mask`, which is the documentation defect that bump carried.

**Assumed** that these four kinds of delta keep naming what the deleted guards would have caught.
*Wrong if:* a later bump surfaces a defect the deleted element cross-check would have caught and the
report did not name.

#### Scenario: A class is removed upstream

- **WHEN** the report runs between a tag that documents a class and one that does not
- **THEN** it names the component and the removed class

#### Scenario: A class moves to another element upstream

- **WHEN** DaisyUI's marked examples put a class on a different set of elements in the newer tag
- **THEN** the report names the class with both element sets

#### Scenario: Nothing structural changed

- **WHEN** the report runs between two tags whose component documentation differs in prose only
- **THEN** the report says that no class, element or join-item change was found

### Requirement: A class group is offered as the axes the model declares

Each axis the model declares for a function SHALL become one parameter typed
`ClassValues<E>`, defaulting to `null`, where `E` is an enum named by the component and the axis
name the model declares, with one entry per member named after the class suffix in PascalCase.
Each toggle SHALL become one `Boolean = false` parameter named after its class suffix in
camelCase. A class-bearing boolean SHALL exist only for a toggle.

Which classes share an axis is a design decision recorded in the model, not a measurement. Two
members of one axis cannot be passed together in typed form; that combination stays reachable
through `extraClasses`.

**Verified** which combinations the declared axes give up, against `codegen/exclusivity.json` at
DaisyUI 5.7.42 before it is deleted: 29 pairs measured `compose` and land on one axis. Seventeen
are opposite values of one property, such as `chat-start` with `chat-end`, or two sides of a
dropdown, which DaisyUI's examples never combine. Twelve combine two looks, such as `btn-outline`
with `btn-ghost`, and those are the ones a caller might want. All 29 are listed in `design.md`.

**Verified** as a decision, Oliver, 2026-10-18: compatibility with 0.7.x is not a constraint; one
enum-typed parameter per axis with defaults;
real on/off features stay booleans; a typed spelling for the pairs within one axis is given up in
favour of `extraClasses`; a type-state builder was rejected for its error messages and API size.

#### Scenario: Two members of one axis

- **WHEN** a caller writes `daisyButton("Save", style = ButtonStyle.Ghost, style = ButtonStyle.Outline)`
- **THEN** it does not compile, because one parameter is passed twice

#### Scenario: A combination across two axes

- **WHEN** a caller asks for `btn-wide` and `btn-square` together
- **THEN** `width = ButtonWidth.Wide, shape = ButtonShape.Square` compiles and renders both classes

#### Scenario: A combination within one axis

- **WHEN** a caller needs `btn-outline` and `btn-ghost` on one button
- **THEN** one of them is passed through `extraClasses`

#### Scenario: An on/off feature

- **WHEN** a class switches a feature on and has no alternative on its axis, such as `table-zebra`
- **THEN** it stays a boolean parameter named `zebra`

### Requirement: Every generated name follows one naming rule

The component model SHALL name every function, axis, enum and entry by these rules:

- A main function or a part is `daisy` followed by its stem, the PascalCase form of the DaisyUI
  class its element carries: `stats` gives `daisyStats`, `stat-title` gives `daisyStatTitle`. The
  model declares a different stem where the class is not the component's name, which is two
  classes: `btn` gives `Button` and `cally` gives `Calendar`. A construction method, such as
  `daisyModalPopover`, a part without an own class, such as `daisyDockItem`, and a class-only
  function, such as `daisyMenuItem`, have a declared name.
- A class sibling is its function's name followed by `Classes`: `daisyButtonClasses`,
  `daisyMenuItemClasses`.
- An axis is a noun naming what is chosen. It is DaisyUI's frontmatter category word where that
  category forms exactly one axis: `color`, `size`, `style`, `direction`, `placement`. Otherwise it
  is a declared word, such as `side`, `align`, `shape` or `width`. No axis is called `variant` or
  `modifier`, because neither names what is chosen.
- An enum is the function's stem followed by the PascalCase axis name: `ButtonColor`,
  `StatsDirection`.
- An entry is the PascalCase class suffix after the function's own class, or after the
  component's class where that is the one the class starts with: `ButtonColor.Primary` for
  `btn-primary`, `StepColor.Primary` for `step-primary` on the step,
  `IndicatorItemHorizontalPlacement.Start` for `indicator-start` on the indicator item.

Generation SHALL fail when a main function, a part or an enum one of them chooses from is named
otherwise. The check covered functions only until 2026-10-18, and the one rename that slipped
through, the indicator item's two placement enums, is why it covers enums too.

**Verified** that two declared stems suffice, run over the model on 2026-10-18 (task 4.1): the rule
renames 13 functions, and the only rename that would be wrong is `daisyCalendar` to
`daisyCally`, because the calendar's main class is `cally`, a class named after the Cally web
component rather than after the calendar. `btn` is the other. `stats` and `tabs` are the names the
rule should produce.

**Verified** that the 0.7.0 names break these rules, read from `lib/api/components.api` on
2026-10-18: `daisyStatStat`, `daisyStatStatTitle`, `daisyStepsStep`, `daisyTabTab` repeat their
component; 19 color parameters are called `variant`; six enums end in `Modifier`;
`StepsVariant.StepNeutral` and `ChatVariant.BubbleNeutral` carry a class prefix inside the entry.

**Verified** as a decision, Oliver, 2026-10-18: a clean API now outweighs compatibility with
0.7.x.

*Wrong if:* a DaisyUI class name gives a function name that collides with another generated
function. The generated sources then fail to compile, and the model declares a name for one of
them.

#### Scenario: A part named after its own class

- **WHEN** a function renders the element carrying `stat-title`
- **THEN** it is named `daisyStatTitle`

#### Scenario: A color axis

- **WHEN** a function offers DaisyUI's color classes
- **THEN** the parameter is `color` and its enum is `<Class>Color`, such as `ButtonColor`

#### Scenario: A name that breaks the rule

- **WHEN** a model file names a function differently from the class its element carries
- **THEN** the naming test fails, naming the function and the expected name

### Requirement: Every documented element is expressible by its classes, or declared not expressed

Generation SHALL fail, naming the documentation page, the element and its classes, for every
element in the fenced ```html examples of a component's `+page.md` that carries `$$`-marked
classes no generated function can express, unless that page's model file declares the element's
gap as not expressed, with a reason.

The classes that count are the element's marked classes without a variant prefix that some
component's frontmatter declares. A marked class no frontmatter declares is not a component class
and does not count; at the pinned tag that is `rounded-box`, a DaisyUI utility, and six
variant-prefixed drawer tokens.

An element is expressible when its counted classes split into groups such that each group is
offered by one function: a function whose own class is in the group and that offers the rest of
it, as an axis member, a toggle, a class an extra parameter writes, a class it writes on every
call, or `join-item` where the function is one the join scope marks; or a function with no own
class, class-only or rendering, that offers the whole group. The functions may belong to any component, because DaisyUI's examples nest
components, and two of them on one element are one call plus a class sibling. The element's tag is
not compared; see "Each generated function renders the one element its model declares".

A gap is declared by its key. Where the counted classes include the own classes of two or more
functions, the key is those own classes, sorted. Otherwise the key is all the counted classes,
sorted.

Generation SHALL also fail on a declaration that no example of its page needs: one whose key no
element on the page has any more, or whose elements have become expressible. Without that, a gap
closed by a later model edit would stay listed as open.

**Verified** the size of the gap before the composition rule, measured 2026-10-18: 39 keys, 12 a
class no function offers on the element DaisyUI puts it on, 27 two components on one element
(`dropdown-content menu`, `badge indicator-item`, `input validator`, `btn theme-controller`, …).

**Verified** that no example puts two members of one axis on one element, measured the same day:
the axis rule gives up nothing DaisyUI documents.

**Verified** that under the composition rule, with the class-only functions, the moves and the
four new functions of `express-every-documented-element`, no declaration remains, simulated
2026-10-18 with those model edits applied in memory (task 1.1): 2,564 of 2,571 marked elements
expressible, 117 of them as compositions, no gap, and no two own classes on one element offering
the same modifier. The other seven count no class. Re-counted against the real model after block 5
the same day (task 5.5): the same numbers, and no `notExpressed` declaration left in `codegen/model/`.

#### Scenario: A part's element is expressible
- **WHEN** an example shows `<div class="$$card-body">` and `daisyCardBody` offers `card-body`
- **THEN** generation succeeds and the element becomes a test

#### Scenario: An element no function can express
- **WHEN** an example shows an element whose only counted class no function owns and no class-only
  function offers
- **THEN** generation fails naming the page, the element and the class

#### Scenario: Two components on one element
- **WHEN** an example shows `<ul class="$$dropdown-content $$menu">`, the own classes of
  `daisyDropdownContent` and `daisyMenu`
- **THEN** generation succeeds, and the element is expressed by one function and the other's class
  sibling

#### Scenario: One function lacks a class the element carries
- **WHEN** an example shows an element whose function lacks one of its counted classes, and no
  other function or class-only function offers it
- **THEN** generation fails naming the page, the element and its key

#### Scenario: Modifiers do not change a composition's key
- **WHEN** a page shows `badge badge-secondary indicator-item indicator-start` and its model
  declares `badge indicator-item`
- **THEN** that element's gap is declared, and the declaration fails as stale once the element is
  expressible

#### Scenario: A declared gap
- **WHEN** the page's model file declares a key as not expressed, with a reason
- **THEN** generation succeeds and no test is generated for that element

#### Scenario: A declaration nothing needs
- **WHEN** a declared key no longer appears on its page, or a model edit made it expressible
- **THEN** generation fails naming the page and the declared key

#### Scenario: A class that is no component's
- **WHEN** an element carries `$$rounded-box` beside `$$menu`
- **THEN** only `menu` counts, and `daisyMenu` expresses the element

### Requirement: Every expressible documented element is a generated test

For every element the gate above finds expressible, the generated tests SHALL contain a test that
renders it and asserts that the rendered element carries exactly the counted classes, with two
adjustments. Where one group of its classes is offered by a function that renders an element, the
test calls that function, the page's own functions first, with each class of the group through its
typed parameter, and adds every other group through its function's class sibling in the call's
`attrs`. Where every group is offered by a class sibling or a class-only function only, the test
calls the siblings on a plain element. The element's variant-prefixed classes that some component
declares, such as `lg:$$menu-horizontal`, are passed through `extraClasses` and expected as
written. `join-item` is neither passed nor expected: only a join's content adds it, and the test
calls the function on its own. Elements that yield the same call SHALL yield one test. The test
SHALL NOT assert the element's tag.

**Verified** both adjustments against what the tests did before `test-every-documented-element`,
read 2026-10-18 in `componentCallFor`: a prefixed class went to `extraClasses` and was expected,
because `at()` is parked; `join-item`, being no class of the page's own frontmatter, was dropped.

**Verified** that every function's receiver can be supplied in a generated test by a wrapping
element, read from `codegen/model/` on 2026-10-18: the receivers are `FlowContent`, `UL` and
`FIELDSET`, each one kotlinx.html builder.

**Verified** that a function writes nothing on every call that DaisyUI's element omits, so "exactly
the counted classes" holds, read 2026-10-18: the one function writing a second class on every
call, `daisySkeletonText`, expresses only elements that carry both. A generated test that renders
an extra class would be a defect in the model.

#### Scenario: A part becomes a test
- **WHEN** an example shows `<div class="$$stat-title">`
- **THEN** a generated test calls `daisyStatTitle` and asserts the classes `stat-title`

#### Scenario: A nested component becomes a test on its own function
- **WHEN** the card page shows `<button class="$$btn $$btn-primary">`
- **THEN** a generated test calls `daisyButton(color = ButtonColor.Primary)` and asserts
  `btn btn-primary`

#### Scenario: Two identical elements
- **WHEN** two examples on one page show the same function with the same counted classes
- **THEN** one test is generated

#### Scenario: Two components on one element become a call and a sibling
- **WHEN** the dropdown page shows `<ul class="$$dropdown-content $$menu">`
- **THEN** a generated test calls `daisyDropdownContent(attrs = { daisyMenuClasses() })` and
  asserts `dropdown-content menu`

#### Scenario: A child with no own class
- **WHEN** the menu page shows `<a class="$$menu-active">`
- **THEN** a generated test calls `daisyMenuItemClasses(active = true)` on a plain element and
  asserts `menu-active`

### Requirement: Every function has a typed class sibling

For every generated function that writes a class, the generated sources SHALL contain an
extension on kotlinx.html's `Tag`, named the function's name followed by `Classes`, that adds to
the element it is called on exactly the classes the function would write for the same arguments:
its own class, the classes it writes on every call, and the classes of its axes, toggles and
extra parameters. An extra parameter that writes a class also sets an attribute or a property in
its function; the sibling writes only the class. The sibling writes no attribute and no content.

A sibling puts one component's typed classes on an element another function renders, or on an
element no function renders: `daisyMenu(attrs = { daisyDropdownContentClasses() })`,
`a { daisyButtonClasses(color = ButtonColor.Primary) }`.

**Verified** that the names are free, read 2026-10-18 from `lib/generated/main`: no generated
function ends in `Classes`, and the 132 rendering functions have 131 distinct names (`daisyDiffItem`
is two overloads for two receivers). Generated 2026-10-18 (task 2.2): 129 siblings, because three
structural wrappers write no class and get none.

**Verified** that one extra parameter writes a class, read from `codegen/model/` the same day:
`daisyButton`'s `disabled`, which sets `disabled` and writes `btn-disabled`. Every other extra sets
a property and writes no class.

**Verified** that a sibling can be called in every function's `attrs` and `content` lambda, read
2026-10-18 in kotlinx-html-jvm 0.12.0's sources: every element class the model uses is an
`HTMLTag`, and `HTMLTag` implements `Tag`.

#### Scenario: A sibling adds the same classes as its function
- **WHEN** `div { daisyButtonClasses(color = ButtonColor.Primary, active = true) }` is rendered
- **THEN** the `div` carries `btn btn-primary btn-active`, and no attribute besides `class`

#### Scenario: Two components on one element
- **WHEN** `daisyMenu(attrs = { daisyDropdownContentClasses() }) { }` is rendered
- **THEN** the `ul` carries `menu dropdown-content`

#### Scenario: An extra's class without its property
- **WHEN** `a { daisyButtonClasses(disabled = true) }` is rendered
- **THEN** the `a` carries `btn btn-disabled` and no `disabled` attribute

### Requirement: A child without an own class gets a class-only function

The model SHALL be able to declare a function with no own class and no element, under a declared
name. It generates no rendering function, only its class sibling, offering the classes DaisyUI puts
on a child that carries no class of its own.

**Verified** where DaisyUI puts the classes this is for, measured 2026-10-18 at the pinned tag:
`menu-active` on the `<a>` inside a `<li>`; `menu-disabled` on a `<li>`; `dock-active` on a dock's
`<button>` items; `rating-hidden` on the rating's first `<input>`; `list-col-grow` and
`list-col-wrap` on the children of a `list-row`. None of these elements carries a class of its own.

#### Scenario: A menu item's state
- **WHEN** `li { a { daisyMenuItemClasses(active = true) } }` is rendered
- **THEN** the `a` carries `menu-active`

#### Scenario: A class-only function renders nothing
- **WHEN** the model declares a class-only function
- **THEN** no rendering function of that name is generated, only its sibling
