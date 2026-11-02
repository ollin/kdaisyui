## MODIFIED Requirements

### Requirement: Every expressible documented element is a generated test

For every element the gate above finds expressible, the generated tests SHALL contain a test that
renders it on DaisyUI's tag and asserts both the tag and that the element carries exactly the
counted classes, with two adjustments. Where the claiming function of one group renders the
documented tag, the test calls that function, the page's own functions first, with each class of
the group through its typed parameter, and adds every other group through its function's class
sibling in the call's `block`. Otherwise the test writes the documented tag with its kotlinx.html
builder, inside the parent the builder needs (`ul` for `li`, `fieldSet` for `legend`), and adds
every group through its class sibling. The element's variant-prefixed classes that some component
declares, such as `lg:$$menu-horizontal`, are passed through `extraClasses` and expected as
written. `join-item` is neither passed nor expected: only a join's content adds it, and the test
calls the function on its own. Elements that yield the same call SHALL yield one test.

**Verified** how often the tag differs, measured 2026-10-18 at the pinned tag before this
requirement changed: of the tested elements, 2,196 sit on the tag their function renders, 357 in
45 shapes on another (`mask` on `<input>` 60, `badge` on `<div>` 50, `btn` on `<div>`, `<a>` and
`<input>` 86, `dropdown` on `<div>` 29, `tab` on `<input>` 19), and 11 are class-only. Every tag but
`<calendar-date>` has a kotlinx.html builder; `li` needs `ul` or `ol` as its parent and `legend`
needs `fieldSet`, `details` or `figure`, read the same day in kotlinx-html-jvm 0.12.0's sources.

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

### Requirement: Every function has a typed class sibling

For every generated function that writes a class, the generated sources SHALL contain an
extension on kotlinx.html's `Tag`, named the function's name followed by `Classes`, that adds to
the element it is called on exactly the classes the function would write for the same arguments:
its own class, the classes it writes on every call, and the classes of its axes, toggles and
extra parameters. An extra parameter that writes a class also sets an attribute or a property in
its function; the sibling writes only the class. The sibling writes no attribute and no content.

A sibling puts one component's typed classes on an element another function renders, or on an
element no function renders: `daisyMenu { daisyDropdownContentClasses() }`,
`a { daisyButtonClasses(color = ButtonColor.Primary) }`.

**Verified** that the names are free, read 2026-10-18 from `lib/generated/main`: no generated
function ends in `Classes`, and the 132 rendering functions have 131 distinct names (`daisyDiffItem`
is two overloads for two receivers). Generated 2026-10-18 (task 2.2): 129 siblings, because three
structural wrappers write no class and get none.

**Verified** that one extra parameter writes a class, read from `codegen/model/` the same day:
`daisyButton`'s `disabled`, which sets `disabled` and writes `btn-disabled`. Every other extra sets
a property and writes no class.

**Verified** that a sibling can be called in every function's `block`, read
2026-10-18 in kotlinx-html-jvm 0.12.0's sources: every element class the model uses is an
`HTMLTag`, and `HTMLTag` implements `Tag`.

#### Scenario: A sibling adds the same classes as its function
- **WHEN** `div { daisyButtonClasses(color = ButtonColor.Primary, active = true) }` is rendered
- **THEN** the `div` carries `btn btn-primary btn-active`, and no attribute besides `class`

#### Scenario: Two components on one element
- **WHEN** `daisyMenu { daisyDropdownContentClasses() }` is rendered
- **THEN** the `ul` carries `menu dropdown-content`

#### Scenario: An extra's class without its property
- **WHEN** `a { daisyButtonClasses(disabled = true) }` is rendered
- **THEN** the `a` carries `btn btn-disabled` and no `disabled` attribute

