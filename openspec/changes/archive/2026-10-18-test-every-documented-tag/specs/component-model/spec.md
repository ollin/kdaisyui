## MODIFIED Requirements

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
class, class-only or rendering, that offers the whole group. Of several functions with the element's own class that offer equally much,
the one rendering the element's documented tag claims it. The functions may belong to any
component, because DaisyUI's examples nest components, and two of them on one element are one call
plus a class sibling.

Generation SHALL also fail, naming the page and the tag, for a documented element whose tag has
neither a kotlinx.html builder nor one the library generates, because no test, and no caller, can
write it. At the pinned tag that was `<calendar-date>`, the cally web component, before
`test-every-documented-tag` gave it a builder (task 1.1).

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

#### Scenario: Two functions with one own class, and the documented tag decides
- **WHEN** the tab page shows `<input class="$$tab">` and both `daisyTab` (`<a>`) and
  `daisyTabRadio` (`<input>`) have the own class `tab` and offer equally much
- **THEN** `daisyTabRadio` claims the element

#### Scenario: A tag nothing can write
- **WHEN** an example shows an element on a tag with no kotlinx.html builder and none generated
- **THEN** generation fails naming the page and the tag

### Requirement: Every expressible documented element is a generated test

For every element the gate above finds expressible, the generated tests SHALL contain a test that
renders it on DaisyUI's tag and asserts both the tag and that the element carries exactly the
counted classes, with two adjustments. Where the claiming function of one group renders the
documented tag, the test calls that function, the page's own functions first, with each class of
the group through its typed parameter, and adds every other group through its function's class
sibling in the call's `attrs`. Otherwise the test writes the documented tag with its kotlinx.html
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
- **THEN** a generated test calls `daisyDropdownContent(attrs = { daisyMenuClasses() })` and
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
