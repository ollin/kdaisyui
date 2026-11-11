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
editorial summary.

**Verified**: the model carries every fact today's inferred shape holds, 2026-10-18 (task 1.4): the 68 committed model files rebuild all 66 component shapes deep-equal to the inferred ones, the join scope included, and reproduce every main function's extras, text flag, role and input type, every editorial summary and both skip reasons. A corrupted model file fails that test and names the component. Shape equality is what the emitters render from, so generation cannot differ; block 3 still regenerates after every switch, because the emitters also read the configuration until then. It took no field beyond the eleven listed in task 1.2 and 1.4, and 65 of 66 components round-tripped before the join scope was modelled.

#### Scenario: A structural fact changes only by a model edit

- **WHEN** a component must render a different element
- **THEN** the change is an edit to that component's model file
- **AND** the regenerated Kotlin, reference page and API dump follow from it

#### Scenario: The bootstrapped model reproduces today's output

- **WHEN** the model is first written from the inferred shape and the generators switch to it
- **THEN** regeneration produces no difference in any committed generated file

### Requirement: The generators read only DaisyUI's frontmatter

From DaisyUI, the generators of the library code, the reference pages, `llms.txt` and the API dump
SHALL read only the frontmatter: the class names and their descriptions. They SHALL NOT read the
fenced HTML examples.

Whether a function takes content stays derived from its declared element by the HTML
specification's void-element list.

**Why**: That is a rule of HTML, not an inference about DaisyUI.

#### Scenario: Generating without reading example markup

- **WHEN** the generators of the library code, the reference pages, `llms.txt` and the API dump run
- **THEN** they read the model and the frontmatter
- **AND** no fenced HTML example from DaisyUI's documentation is read

### Requirement: The component-test generator takes every binding from the model

The component-test generator is the one reader of the examples that stays. It turns each
documented example into a call against the declared API and asserts the classes rendered, which
makes the examples test input for the model rather than a source of it. It SHALL take every
parameter binding from the model, never from its own classification.

#### Scenario: A documented example becomes a test against the model

- **WHEN** the component-test generator reads a documented example
- **THEN** it builds the call from the model's parameters
- **AND** a class the model does not offer on that function is not silently dropped from the test

### Requirement: The model accounts for every class DaisyUI declares

Generation SHALL fail, naming the component and the class, unless every class in a component's
frontmatter is placed in its model: as a function's own class, a part's class, an axis member, a
toggle, a class an extra parameter or a construction writes, or an entry listed as not offered with
a reason. Generation SHALL also fail when the model names a class the frontmatter does not
declare.

**Why**: This replaces three guards with one set comparison: the unread-configuration guard, the offered-twice guard and the measured-group completeness check.

**Verified**: the removal direction matters: `mask-square` stayed in the generated API from DaisyUI 5.0 until 5.7.19 removed it from the documentation, and was withdrawn in kdaisyui only on the 5.7.42 bump (`docs/upgrading.md`, "How to migrate from 0.5.x", item 12).

**Verified**: the frontmatter is a complete class source, measured 2026-10-18 at the pinned tag (task 1.1): of every `$$`-marked class in the fenced examples of all component pages, none is missing from every frontmatter. 291 marked classes are absent from their own page's frontmatter, and all of them are other components' classes used inside an example, such as `btn` inside a card. The only seven declared nowhere are not component classes: six variant-prefixed drawer tokens such as `is-drawer-close:$$tooltip`, which the parser reads as one token, and `rounded-box`, a DaisyUI utility. *Wrong if:* a later DaisyUI release marks a component class in an example that no frontmatter declares. The bump report's class delta is built from the frontmatter, so it would not name such a class; re-running this measurement on a bump is the check.

#### Scenario: DaisyUI adds a class

- **WHEN** the pinned frontmatter declares a class the model does not place
- **THEN** generation fails naming the component and the class

#### Scenario: DaisyUI removes a class

- **WHEN** the model places a class the pinned frontmatter no longer declares
- **THEN** generation fails naming the component and the class

### Requirement: No function offers one class twice

Generation SHALL fail when one function offers the same class twice.

A class MAY be placed on several functions. Measured 2026-10-18 before the gate was written:
`modal` and `skeleton` are each the own class of two constructions, and `timeline-box` is a toggle
on three parts. All three are correct. The defect the duplicate check exists for is one function
offering one class twice.

**Verified**: the placed-twice direction matters: 0.6.0 shipped `daisyDropdown` with five placement booleans beside the enums that replaced them (`docs/upgrading.md`, "How to migrate from 0.6.x", item 1).

#### Scenario: A class is placed twice

- **WHEN** one function offers one class in two roles, such as a toggle and an axis member
- **THEN** generation fails naming the function and the class

#### Scenario: One class on several functions

- **WHEN** two constructions of one component both write the component's class
- **THEN** generation succeeds

### Requirement: Every DaisyUI component directory has a model file

Generation SHALL fail when a DaisyUI component directory has no model file and is not listed as
skipped, and when a model file names no existing directory.

#### Scenario: DaisyUI adds a component

- **WHEN** the submodule has a component directory with neither a model file nor a skip entry
- **THEN** generation fails naming the directory

### Requirement: A DaisyUI bump is read as a documentation delta

A recipe SHALL report, for two DaisyUI tags, what changed in the component documentation between
them: components added or removed; classes added or removed per component; per class, the set of
elements DaisyUI's marked examples put it on; and the set of component classes documented beside
`join-item`.

**Verified**: in part, 2026-10-18 (task 2.6): replaying the 5.7.17 to 5.7.42 bump reports exactly one change, `mask-square` removed from `mask`, which is the documentation defect that bump carried.

**Assumed**: these four kinds of delta keep naming what the deleted guards would have caught. *Wrong if:* a later bump surfaces a defect the deleted element cross-check would have caught and the report did not name.

#### Scenario: A class is removed upstream

- **WHEN** the report runs between a tag that documents a class and one that does not
- **THEN** it names the component and the removed class

#### Scenario: A class moves to another element upstream

- **WHEN** DaisyUI's marked examples put a class on a different set of elements in the newer tag
- **THEN** the report names the class with both element sets

#### Scenario: Nothing structural changed

- **WHEN** the report runs between two tags whose component documentation differs in prose only
- **THEN** the report says that no class, element or join-item change was found

### Requirement: The bump report informs and does not fail the build

The report SHALL NOT fail the build. It is read during an upgrade, and the model is edited by
hand.

The report compares DaisyUI with itself, not with the model, so a deliberate divergence such as
`daisyBadge` rendering `<span>` (#356) needs no exception: it does not change between releases.

#### Scenario: A deliberate divergence from DaisyUI

- **WHEN** the model deliberately diverges from DaisyUI, such as `daisyBadge` rendering `<span>`
  (#356)
- **THEN** the report needs no exception for it, because the divergence does not change between
  releases

### Requirement: A class group is offered as the axes the model declares

Each axis the model declares for a function SHALL become one parameter typed
`ClassValues<E>`, defaulting to `null`, where `E` is an enum named by the component and the axis
name the model declares, with one entry per member named after the class suffix in PascalCase.

Two members of one axis cannot be passed together in typed form; that combination stays reachable
through `extraClasses`.

**Why**: Which classes share an axis is a design decision recorded in the model, not a measurement.

**Verified**: which combinations the declared axes give up, against `codegen/exclusivity.json` at DaisyUI 5.7.42 before it is deleted: 29 pairs measured `compose` and land on one axis. Seventeen are opposite values of one property, such as `chat-start` with `chat-end`, or two sides of a dropdown, which DaisyUI's examples never combine. Twelve combine two looks, such as `btn-outline` with `btn-ghost`, and those are the ones a caller might want. All 29 are listed in `design.md`.

**Verified**: as a decision, Oliver, 2026-10-18: compatibility with 0.7.x is not a constraint; one enum-typed parameter per axis with defaults; real on/off features stay booleans; a typed spelling for the pairs within one axis is given up in favour of `extraClasses`; a type-state builder was rejected for its error messages and API size.

#### Scenario: Two members of one axis

- **WHEN** a caller writes `daisyButton("Save", style = ButtonStyle.Ghost, style = ButtonStyle.Outline)`
- **THEN** it does not compile, because one parameter is passed twice

#### Scenario: A combination across two axes

- **WHEN** a caller asks for `btn-wide` and `btn-square` together
- **THEN** `width = ButtonWidth.Wide, shape = ButtonShape.Square` compiles and renders both classes

#### Scenario: A combination within one axis

- **WHEN** a caller needs `btn-outline` and `btn-ghost` on one button
- **THEN** one of them is passed through `extraClasses`

### Requirement: A class-bearing boolean exists only for a toggle

Each toggle SHALL become one `Boolean = false` parameter named after its class suffix in
camelCase. A class-bearing boolean SHALL exist only for a toggle.

**Verified**: as a decision, Oliver, 2026-10-18: compatibility with 0.7.x is not a constraint; one enum-typed parameter per axis with defaults; real on/off features stay booleans; a typed spelling for the pairs within one axis is given up in favour of `extraClasses`; a type-state builder was rejected for its error messages and API size.

#### Scenario: An on/off feature

- **WHEN** a class switches a feature on and has no alternative on its axis, such as `table-zebra`
- **THEN** it stays a boolean parameter named `zebra`

### Requirement: Every generated name follows one naming rule

The component model SHALL name every function, axis, enum and entry by the naming rules the six
requirements below state.

Generation SHALL fail when a main function, a part or an enum one of them chooses from is named
otherwise.

**Why**: The check covered functions only until 2026-10-18, and the one rename that slipped through, the indicator item's two placement enums, is why it covers enums too.

**Verified**: the 0.7.0 names break these rules, read from `lib/api/components.api` on 2026-10-18: `daisyStatStat`, `daisyStatStatTitle`, `daisyStepsStep`, `daisyTabTab` repeat their component; 19 color parameters are called `variant`; six enums end in `Modifier`; `StepsVariant.StepNeutral` and `ChatVariant.BubbleNeutral` carry a class prefix inside the entry.

**Verified**: as a decision, Oliver, 2026-10-18: a clean API now outweighs compatibility with 0.7.x.

*Wrong if:* a DaisyUI class name gives a function name that collides with another generated
function. The generated sources then fail to compile, and the model declares a name for one of
them.

#### Scenario: A name that breaks the rule

- **WHEN** a model file names a function differently from the class its element carries
- **THEN** the naming test fails, naming the function and the expected name

### Requirement: A function is named daisy followed by its stem

A main function or a part SHALL be `daisy` followed by its stem, the PascalCase form of the
DaisyUI class its element carries: `stats` gives `daisyStats`, `stat-title` gives
`daisyStatTitle`. The model declares a different stem where the class is not the component's
name, which is two classes: `btn` gives `Button` and `cally` gives `Calendar`.

**Verified**: two declared stems suffice, run over the model on 2026-10-18 (task 4.1): the rule renames 13 functions, and the only rename that would be wrong is `daisyCalendar` to `daisyCally`, because the calendar's main class is `cally`, a class named after the Cally web component rather than after the calendar. `btn` is the other. `stats` and `tabs` are the names the rule should produce.

#### Scenario: A part named after its own class

- **WHEN** a function renders the element carrying `stat-title`
- **THEN** it is named `daisyStatTitle`

### Requirement: A function no class names has a declared name

A construction method, such as `daisyModalPopover`, a part without an own class, such as
`daisyDockItem`, and a class-only function, such as `daisyMenuItem`, SHALL have a declared name.

#### Scenario: A class-only function

- **WHEN** the model declares the class-only function `daisyMenuItem`
- **THEN** it is named by its declared name, not by a class

### Requirement: A class sibling is named after its function

A class sibling SHALL be its function's name followed by `Classes`: `daisyButtonClasses`,
`daisyMenuItemClasses`.

#### Scenario: A sibling's name

- **WHEN** the class sibling of `daisyMenuItem` is generated
- **THEN** it is named `daisyMenuItemClasses`

### Requirement: An axis is a noun naming what is chosen

An axis SHALL be a noun naming what is chosen. It is DaisyUI's frontmatter category word where
that category forms exactly one axis: `color`, `size`, `style`, `direction`, `placement`.
Otherwise it is a declared word, such as `side`, `align`, `shape` or `width`. No axis is called
`variant` or `modifier`, because neither names what is chosen.

#### Scenario: A color axis

- **WHEN** a function offers DaisyUI's color classes
- **THEN** the parameter is `color` and its enum is `<Class>Color`, such as `ButtonColor`

### Requirement: An enum and its entries are named from the stem and the class suffix

An enum SHALL be the function's stem followed by the PascalCase axis name: `ButtonColor`,
`StatsDirection`.

An entry SHALL be the PascalCase class suffix after the function's own class, or after the
component's class where that is the one the class starts with: `ButtonColor.Primary` for
`btn-primary`, `StepColor.Primary` for `step-primary` on the step,
`IndicatorItemHorizontalPlacement.Start` for `indicator-start` on the indicator item.

#### Scenario: An entry after the function's own class

- **WHEN** the button's color axis offers `btn-primary`
- **THEN** its entry is `ButtonColor.Primary`

#### Scenario: An entry after the component's class

- **WHEN** the indicator item offers `indicator-start`
- **THEN** its entry is `IndicatorItemHorizontalPlacement.Start`

### Requirement: Every documented element is expressible by its classes, or declared not expressed

Generation SHALL fail, naming the documentation page, the element and its classes, for every
element in the fenced ```html examples of a component's `+page.md` that carries `$$`-marked
classes no generated function can express, unless that page's model file declares the element's
gap as not expressed, with a reason.

**Verified**: the size of the gap before the composition rule, measured 2026-10-18: 39 keys, 12 a class no function offers on the element DaisyUI puts it on, 27 two components on one element (`dropdown-content menu`, `badge indicator-item`, `input validator`, `btn theme-controller`, …).

**Verified**: no example puts two members of one axis on one element, measured the same day: the axis rule gives up nothing DaisyUI documents.

**Verified**: under the composition rule, with the class-only functions, the moves and the four new functions of `express-every-documented-element`, no declaration remains, simulated 2026-10-18 with those model edits applied in memory (task 1.1): 2,564 of 2,571 marked elements expressible, 117 of them as compositions, no gap, and no two own classes on one element offering the same modifier. The other seven count no class. Re-counted against the real model after block 5 the same day (task 5.5): the same numbers, and no `notExpressed` declaration left in `codegen/model/`.

#### Scenario: An element no function can express
- **WHEN** an example shows an element whose only counted class no function owns and no class-only
  function offers
- **THEN** generation fails naming the page, the element and the class

#### Scenario: A declared gap
- **WHEN** the page's model file declares a key as not expressed, with a reason
- **THEN** generation succeeds and no test is generated for that element

### Requirement: Only declared component classes without a variant prefix count

The classes that count SHALL be the element's marked classes without a variant prefix that some
component's frontmatter declares. A marked class no frontmatter declares is not a component class
and does not count; at the pinned tag that is `rounded-box`, a DaisyUI utility, and six
variant-prefixed drawer tokens.

#### Scenario: A class that is no component's
- **WHEN** an element carries `$$rounded-box` beside `$$menu`
- **THEN** only `menu` counts, and `daisyMenu` expresses the element

### Requirement: An element is expressible when one function offers each group of its classes

An element SHALL count as expressible when its counted classes split into groups such that each
group is offered by one function: a function whose own class is in the group and that offers the
rest of it, as an axis member, a toggle, a class an extra parameter writes, a class it writes on
every call, or `join-item` where the function is one the join scope marks; or a function with no
own class, class-only or rendering, that offers the whole group.

#### Scenario: A part's element is expressible
- **WHEN** an example shows `<div class="$$card-body">` and `daisyCardBody` offers `card-body`
- **THEN** generation succeeds and the element becomes a test

#### Scenario: One function lacks a class the element carries
- **WHEN** an example shows an element whose function lacks one of its counted classes, and no
  other function or class-only function offers it
- **THEN** generation fails naming the page, the element and its key

### Requirement: Functions of any component express an element, and the documented tag breaks ties

Of several functions with the element's own class that offer equally much, the one rendering the
element's documented tag SHALL claim it. The functions may belong to any component, and two of
them on one element are one call plus a class sibling.

**Why**: The functions may belong to any component because DaisyUI's examples nest components.

#### Scenario: Two components on one element
- **WHEN** an example shows `<ul class="$$dropdown-content $$menu">`, the own classes of
  `daisyDropdownContent` and `daisyMenu`
- **THEN** generation succeeds, and the element is expressed by one function and the other's class
  sibling

#### Scenario: Two functions with one own class, and the documented tag decides
- **WHEN** the tab page shows `<input class="$$tab">` and both `daisyTab` (`<a>`) and
  `daisyTabRadio` (`<input>`) have the own class `tab` and offer equally much
- **THEN** `daisyTabRadio` claims the element

### Requirement: Every documented tag has a builder

Generation SHALL also fail, naming the page and the tag, for a documented element whose tag has
neither a kotlinx.html builder nor one the library generates, because no test, and no caller, can
write it. At the pinned tag that was `<calendar-date>`, the cally web component, before
`test-every-documented-tag` gave it a builder (task 1.1).

#### Scenario: A tag nothing can write
- **WHEN** an example shows an element on a tag with no kotlinx.html builder and none generated
- **THEN** generation fails naming the page and the tag

### Requirement: A gap is declared by its key

A gap SHALL be declared by its key. Where the counted classes include the own classes of two or
more functions, the key is those own classes, sorted. Otherwise the key is all the counted
classes, sorted.

#### Scenario: Modifiers do not change a composition's key
- **WHEN** a page shows `badge badge-secondary indicator-item indicator-start` and its model
  declares `badge indicator-item`
- **THEN** that element's gap is declared, and the declaration fails as stale once the element is
  expressible

### Requirement: A declaration nothing needs fails generation

Generation SHALL also fail on a declaration that no example of its page needs: one whose key no
element on the page has any more, or whose elements have become expressible.

**Why**: Without that, a gap closed by a later model edit would stay listed as open.

#### Scenario: A declaration nothing needs
- **WHEN** a declared key no longer appears on its page, or a model edit made it expressible
- **THEN** generation fails naming the page and the declared key

### Requirement: Every expressible documented element is a generated test

For every element the gate above finds expressible, the generated tests SHALL contain a test that
renders it on DaisyUI's tag and asserts both the tag and that the element carries exactly the
counted classes, with the two adjustments stated below. Elements that yield the same call SHALL
yield one test.

**Verified**: a function writes nothing on every call that DaisyUI's element omits, so "exactly the counted classes" holds, read 2026-10-18: the one function writing a second class on every call, `daisySkeletonText`, expresses only elements that carry both. A generated test that renders an extra class would be a defect in the model.

#### Scenario: A part becomes a test
- **WHEN** an example shows `<div class="$$stat-title">`
- **THEN** a generated test calls `daisyStatTitle` and asserts the classes `stat-title`

#### Scenario: Two identical elements
- **WHEN** two examples on one page show the same function with the same counted classes
- **THEN** one test is generated

### Requirement: A generated test calls the claiming function or writes the documented tag

Where the claiming function of one group renders the documented tag, the test SHALL call that
function, the page's own functions first, with each class of the group through its typed
parameter, and add every other group through its function's class sibling in the call's `block`.
Otherwise the test SHALL write the documented tag with its kotlinx.html builder, inside the parent
the builder needs (`ul` for `li`, `fieldSet` for `legend`), and add every group through its class
sibling.

**Verified**: how often the tag differs, measured 2026-10-18 at the pinned tag before this requirement changed: of the tested elements, 2,196 sit on the tag their function renders, 357 in 45 shapes on another (`mask` on `<input>` 60, `badge` on `<div>` 50, `btn` on `<div>`, `<a>` and `<input>` 86, `dropdown` on `<div>` 29, `tab` on `<input>` 19), and 11 are class-only. Every tag but `<calendar-date>` has a kotlinx.html builder; `li` needs `ul` or `ol` as its parent and `legend` needs `fieldSet`, `details` or `figure`, read the same day in kotlinx-html-jvm 0.12.0's sources.

**Verified**: every function's receiver can be supplied in a generated test by a wrapping element, read from `codegen/model/` on 2026-10-18: the receivers are `FlowContent`, `UL` and `FIELDSET`, each one kotlinx.html builder.

#### Scenario: A nested component becomes a test on its own function
- **WHEN** the card page shows `<button class="$$btn $$btn-primary">`
- **THEN** a generated test calls `daisyButton(color = ButtonColor.Primary)` and asserts
  `btn btn-primary`

#### Scenario: Two components on one element become a call and a sibling
- **WHEN** the dropdown page shows `<ul class="$$dropdown-content $$menu">`
- **THEN** a generated test calls `daisyDropdownContent { daisyMenuClasses() }` and
  asserts `dropdown-content menu`

#### Scenario: A child with no own class
- **WHEN** the menu page shows `<a class="$$menu-active">`
- **THEN** a generated test writes `a { daisyMenuItemClasses(active = true) }` and asserts the `a`
  and `menu-active`

#### Scenario: A class on a tag its function does not render
- **WHEN** the button page shows `<a class="$$btn $$btn-primary">`
- **THEN** a generated test writes `a { daisyButtonClasses(color = ButtonColor.Primary) }` and
  asserts the `a` and `btn btn-primary`

#### Scenario: A tag that needs its parent
- **WHEN** the menu page shows `<li class="$$menu-disabled">`
- **THEN** the generated test writes it inside a `ul` and asserts the `li`

### Requirement: A generated test passes prefixed classes through and leaves join-item out

The element's variant-prefixed classes that some component declares, such as
`lg:$$menu-horizontal`, SHALL be passed through `extraClasses` and expected as written.
`join-item` SHALL be neither passed nor expected: only a join's content adds it, and the test
calls the function on its own.

**Verified**: both adjustments against what the tests did before `test-every-documented-element`, read 2026-10-18 in `componentCallFor`: a prefixed class went to `extraClasses` and was expected, because `at()` is parked; `join-item`, being no class of the page's own frontmatter, was dropped.

#### Scenario: A variant-prefixed class
- **WHEN** an element carries `lg:$$menu-horizontal`
- **THEN** the generated test passes it through `extraClasses` and expects it as written

#### Scenario: A join item
- **WHEN** an element carries `join-item`
- **THEN** the generated test calls the function on its own, and neither passes nor expects
  `join-item`

### Requirement: Every function has a typed class sibling

For every generated function that writes a class, the generated sources SHALL contain an
extension on kotlinx.html's `Tag`, named the function's name followed by `Classes`, that adds to
the element it is called on exactly the classes the function would write for the same arguments:
its own class, the classes it writes on every call, and the classes of its axes, toggles and
extra parameters.

**Verified**: the names are free, read 2026-10-18 from `lib/generated/main`: no generated function ends in `Classes`, and the 132 rendering functions have 131 distinct names (`daisyDiffItem` is two overloads for two receivers). Generated 2026-10-18 (task 2.2): 129 siblings, because three structural wrappers write no class and get none.

#### Scenario: A sibling adds the same classes as its function
- **WHEN** `div { daisyButtonClasses(color = ButtonColor.Primary, active = true) }` is rendered
- **THEN** the `div` carries `btn btn-primary btn-active`, and no attribute besides `class`

### Requirement: A sibling writes only classes, on any element

An extra parameter that writes a class also sets an attribute or a property in its function; the
sibling SHALL write only the class. The sibling SHALL write no attribute and no content.

A sibling puts one component's typed classes on an element another function renders, or on an
element no function renders: `daisyMenu { daisyDropdownContentClasses() }`,
`a { daisyButtonClasses(color = ButtonColor.Primary) }`.

**Verified**: one extra parameter writes a class, read from `codegen/model/` the same day: `daisyButton`'s `disabled`, which sets `disabled` and writes `btn-disabled`. Every other extra sets a property and writes no class.

**Verified**: a sibling can be called in every function's `block`, read 2026-10-18 in kotlinx-html-jvm 0.12.0's sources: every element class the model uses is an `HTMLTag`, and `HTMLTag` implements `Tag`.

#### Scenario: Two components on one element
- **WHEN** `daisyMenu { daisyDropdownContentClasses() }` is rendered
- **THEN** the `ul` carries `menu dropdown-content`

#### Scenario: An extra's class without its property
- **WHEN** `a { daisyButtonClasses(disabled = true) }` is rendered
- **THEN** the `a` carries `btn btn-disabled` and no `disabled` attribute

### Requirement: Every function's KDoc names its class sibling

The generated KDoc of every such function SHALL name its sibling in one sentence with the call
shape on another element and a KDoc link, so a reader in an IDE learns of it from the function
itself: ``The same classes on another element: `a { daisyBadgeClasses(…) }`; see [daisyBadgeClasses].``

**Verified**: the KDoc was the one place that did not name the sibling, read 2026-11-07: `docs/reference/badge.md` and `llms-full.txt:85,179` showed it, `lib/generated/main/.../Badge.kt`'s KDoc of `daisyBadge` did not; and that the omission cost a consumer nine raw class strings (the Deploy-Radar, fwb-application, proposal item 7 of 2026-11-07).

**Verified**: it does now, generated 2026-11-10 by `name-the-class-sibling-in-every-kdoc`: 135 sentences across the 66 component files, one per rendering function with a sibling, and `codegen/test/generator-new.test.ts` asserts the sentence and its absence on a structural wrapper.

#### Scenario: The function's KDoc names its sibling
- **WHEN** the Kotlin for a function with a class sibling is generated, whether the sibling comes from an own class, modifier classes or a class-writing parameter
- **THEN** its KDoc contains one sentence with the `a { … }` call shape and a `[name]` link to the sibling

#### Scenario: A structural wrapper names none
- **WHEN** the Kotlin for a function that writes no class is generated
- **THEN** its KDoc contains no such sentence

### Requirement: A child without an own class gets a class-only function

The model SHALL be able to declare a function with no own class and no element, under a declared
name. It generates no rendering function, only its class sibling, offering the classes DaisyUI puts
on a child that carries no class of its own.

**Verified**: where DaisyUI puts the classes this is for, measured 2026-10-18 at the pinned tag: `menu-active` on the `<a>` inside a `<li>`; `menu-disabled` on a `<li>`; `dock-active` on a dock's `<button>` items; `rating-hidden` on the rating's first `<input>`; `list-col-grow` and `list-col-wrap` on the children of a `list-row`. None of these elements carries a class of its own.

#### Scenario: A menu item's state
- **WHEN** `li { a { daisyMenuItemClasses(active = true) } }` is rendered
- **THEN** the `a` carries `menu-active`

#### Scenario: A class-only function renders nothing
- **WHEN** the model declares a class-only function
- **THEN** no rendering function of that name is generated, only its sibling

### Requirement: An extra the coverage tests cannot drive declares a sample

A model `extra` whose type is nullable and neither `String`, `Boolean` nor a generated enum SHALL
declare a `sample`, the Kotlin expression the generated coverage tests pass to drive it, and
generation SHALL fail, naming the component and the parameter, when it does not. The generator
SHALL hold no table of such types of its own.

**Verified**: 2026-11-10: two extras need one today, `ButtonType?` and `DaisyTheme?`.

#### Scenario: An extra without a sample

- **WHEN** a model declares `{ "kind": "extra", "name": "x", "type": "Foo?", … }` with no `sample`
- **THEN** `just generate` fails, naming the component and `x`

#### Scenario: The generator takes the sample from the model

- **WHEN** a fixture model declares `"sample": "Foo.Bar"` on a nullable extra of type `Foo?`
- **THEN** the generated all-flags coverage test passes `x = Foo.Bar` and imports what the extra's
  `imports` name, and an extra without a sample imports nothing of its `imports`

#### Scenario: The sample drives the branch

- **WHEN** `theme-controller.json` declares `"sample": "DaisyTheme.Dark"` for `theme`
- **THEN** the generated all-flags coverage test passes `theme = DaisyTheme.Dark` and imports the enum
