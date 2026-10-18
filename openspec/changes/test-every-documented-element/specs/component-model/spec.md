## ADDED Requirements

### Requirement: Every documented element is expressible by its classes, or declared not expressed

Generation SHALL fail, naming the documentation page, the element and its classes, for every
element in the fenced ```html examples of a component's `+page.md` that carries `$$`-marked
classes no generated function can express, unless that page's model file declares the element's
gap as not expressed, with a reason.

The classes that count are the element's marked classes without a variant prefix that some
component's frontmatter declares. A marked class no frontmatter declares is not a component class
and does not count; at the pinned tag that is `rounded-box`, a DaisyUI utility, and six
variant-prefixed drawer tokens.

An element is expressible when one function's own class is among its counted classes and that
function offers every other counted class: as an axis member, a toggle, a class an extra
parameter writes, a class it writes on every call, or `join-item` where the function is one the
join scope marks. The function may belong to any component, because DaisyUI's examples nest
components. The element's tag is not compared; see "Each generated function renders the one element
its model declares".

A gap is declared by its key. Where the counted classes include the own classes of two or more
functions, two components on one element, the key is those own classes, sorted:
`badge badge-secondary indicator-item indicator-start` and `badge indicator-item` share the key
`badge indicator-item`, because whichever modifiers ride along, the gap is the same. Otherwise the
key is all the counted classes, sorted.

Generation SHALL also fail on a declaration that no example of its page needs: one whose key no
element on the page has any more, or whose elements have become expressible. Without that, a gap closed
by a later model edit would stay listed as open.

**Verified** the size of the gap, measured 2026-10-18 at the pinned tag before this requirement
was written: of 2,571 marked elements, 45 in 13 distinct class sets carry no function's own class
(`list-row`, `floating-label`, `dock-active`, `menu-active`, `rating-hidden`, …), and 143 in 45
distinct sets carry a function's class plus one it does not offer. Most of the second kind are two
components on one element: `dropdown-content menu`, `btn theme-controller`, `badge indicator-item`,
`input validator`. Twenty-four of them are `rounded-box`, which the rule above does not count.

**Verified** that no example puts two members of one axis on one element, measured the same day:
the axis rule gives up nothing DaisyUI documents.

**Verified** that the declarations stay a short list a reviewer reads, counted under exactly this
rule on 2026-10-18 (task 1.1): 39 keys. Twelve are a class no function offers (`list-row`,
`floating-label`, `dock-active`, `menu-active`, `menu-disabled`, `rating-hidden`, `avatar-group`,
`vc`, `list-col-grow`, `list-col-wrap`, and `menu-dropdown-show` on two parts); 27 are two
components on one element. Keyed by the whole class set instead, the same gaps were 65, 27 of them
the indicator's placements alone; Oliver chose the key by own classes the same day, over counting
two components as expressible through `extraClasses`.

#### Scenario: A part's element is expressible
- **WHEN** an example shows `<div class="$$card-body">` and `daisyCardBody` offers `card-body`
- **THEN** generation succeeds and the element becomes a test

#### Scenario: An element no function can express
- **WHEN** an example shows `<li class="$$list-row">` and no function's own class is `list-row`
- **THEN** generation fails naming the page `list`, the element `<li>` and the class `list-row`

#### Scenario: Two components on one element
- **WHEN** an example shows `<ul class="$$dropdown-content $$menu">`, the own classes of
  `daisyDropdownContent` and `daisyMenu`
- **THEN** generation fails naming the page, the element and the key `dropdown-content menu`

#### Scenario: One function lacks a class the element carries
- **WHEN** an example shows `<ul class="$$menu-dropdown $$menu-dropdown-show">` and
  `daisyMenuDropdown` does not offer `menu-dropdown-show`
- **THEN** generation fails naming the page, the element and the key
  `menu-dropdown menu-dropdown-show`

#### Scenario: Modifiers do not change a composition's key
- **WHEN** the indicator page shows `badge badge-secondary indicator-item indicator-start` and the
  page's model declares `badge indicator-item`
- **THEN** that element's gap is declared

#### Scenario: A declared gap
- **WHEN** the page's model file declares `dropdown-content menu` as not expressed, with a reason
- **THEN** generation succeeds and no test is generated for that element

#### Scenario: A declaration nothing needs
- **WHEN** a declared class set no longer appears on its page, or a model edit made it expressible
- **THEN** generation fails naming the page and the declared class set

#### Scenario: A class that is no component's
- **WHEN** an element carries `$$rounded-box` beside `$$menu`
- **THEN** only `menu` counts, and `daisyMenu` expresses the element

### Requirement: Every expressible documented element is a generated test

For every element the gate above finds expressible, the generated tests SHALL contain a test that
calls the function expressing it, passes each other counted class through its typed parameter, and
asserts that the rendered element carries exactly the counted classes. Elements that yield the same
call SHALL yield one test. The test SHALL NOT assert the element's tag.

This replaces the example tests that addressed the main function only.

**Verified** what is tested today, read 2026-10-18 in `codegen/src/test-generator.ts`
(`componentCallsIn`): an example yields a test only for elements carrying the component's own class,
called through the main function. 606 such tests exist; parts and nested components are never
compared with what DaisyUI shows.

**Assumed** that every function's receiver can be supplied in a generated test by a wrapping
element, as the custom-part tests already do for their receivers. *Wrong if:* a part's receiver
is a type no single kotlinx.html builder produces, such as a receiver only a sibling function's
content lambda provides.

**Assumed** that a function writes nothing on every call that DaisyUI's element omits, so "exactly
the counted classes" holds. *Wrong if:* the first generation under this rule reports an expressible
element whose call renders an extra class, which is a defect in the model.

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
