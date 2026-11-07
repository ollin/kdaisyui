## MODIFIED Requirements

### Requirement: Every function has a typed class sibling

For every generated function that writes a class, the generated sources SHALL contain an
extension on kotlinx.html's `Tag`, named the function's name followed by `Classes`, that adds to
the element it is called on exactly the classes the function would write for the same arguments:
its own class, the classes it writes on every call, and the classes of its axes, toggles and
extra parameters. An extra parameter that writes a class also sets an attribute or a property in
its function; the sibling writes only the class. The sibling writes no attribute and no content.

The generated KDoc of every such function SHALL name its sibling in one sentence with a KDoc
link and the call shape on another element, so a reader in an IDE learns of it from the
function itself: `The same classes on another element: a { [daisyBadgeClasses](…) }.`

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

**Verified** that the KDoc is the one place that does not name the sibling, read 2026-11-07:
`docs/reference/badge.md` and `llms-full.txt:85,179` show it, `lib/generated/main/.../Badge.kt`'s
KDoc of `daisyBadge` does not; and that the omission cost a consumer nine raw class strings
(the Deploy-Radar, fwb-application, proposal item 7 of 2026-11-07).

#### Scenario: A sibling adds the same classes as its function
- **WHEN** `div { daisyButtonClasses(color = ButtonColor.Primary, active = true) }` is rendered
- **THEN** the `div` carries `btn btn-primary btn-active`, and no attribute besides `class`

#### Scenario: Two components on one element
- **WHEN** `daisyMenu { daisyDropdownContentClasses() }` is rendered
- **THEN** the `ul` carries `menu dropdown-content`

#### Scenario: An extra's class without its property
- **WHEN** `a { daisyButtonClasses(disabled = true) }` is rendered
- **THEN** the `a` carries `btn btn-disabled` and no `disabled` attribute

#### Scenario: The function's KDoc names its sibling
- **WHEN** the Kotlin for a function with a class sibling is generated
- **THEN** its KDoc contains one sentence linking the sibling by name with the `a { … }` call shape

#### Scenario: A structural wrapper names none
- **WHEN** the Kotlin for a function that writes no class is generated
- **THEN** its KDoc contains no such sentence
