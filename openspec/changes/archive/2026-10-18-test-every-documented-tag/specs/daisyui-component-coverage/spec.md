## MODIFIED Requirements

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
- **THEN** generation succeeds, and the documented-element test for it asserts the classes only

#### Scenario: The calendar renders its web component
- **WHEN** `daisyCalendar { }` is rendered
- **THEN** the output is `<calendar-date class="cally">`, the element the cally web component
  defines, and not a `<div>`
