## MODIFIED Requirements

### Requirement: Coverage gate stays at 100%
Every new component wrapper and every new modifier branch introduced by this change SHALL be exercised by the generated tests so that the aggregated line AND branch coverage of the in-scope modules remains exactly 100% and `./gradlew check` passes. New user-facing component code SHALL NOT be excluded from coverage measurement.

#### Scenario: Aggregated coverage remains 100%
- **WHEN** `./gradlew check` runs after the new components and modifiers are added
- **THEN** aggregated line coverage is 100% and aggregated branch coverage is 100%
- **AND** the coverage verification task passes with a zero exit code

#### Scenario: New components are covered by generated tests
- **WHEN** the codegen test generators run for the pinned DaisyUI release
- **THEN** generated tests exercise every variant, size, modifier, color, `id`, `extraClasses`, `text` and block branch of each new component
- **AND** no new user-facing component class is added to a coverage exclusion filter

## REMOVED Requirements

### Requirement: A generated function never requires content its element cannot hold

**Reason**: Superseded by "Every generated function takes one optional block". No function
requires a lambda any more, and a void element now takes one that sets attributes, so the
distinction this requirement drew between void and non-void elements no longer shapes the
signature.

**Migration**: A call that passed `content = { … }` passes `block = { … }`, or a trailing lambda.
A call on a void element that passed `attrs = { … }` passes the same lambda as `block`.

## ADDED Requirements

### Requirement: Every generated function takes one optional block

Every generated rendering function SHALL take exactly one lambda, named `block`, whose receiver is
the element the function renders, and which defaults to doing nothing. It SHALL be the function's
last parameter, so a trailing lambda binds to it. No generated rendering function SHALL take a
second lambda on the same element.

The block SHALL run after the function has written its own id, attributes and classes and the
caller's `extraClasses`, so an attribute or class the block writes is added to them. On an element
that can hold children the block may also write them. On an HTML void element (`area`, `base`,
`br`, `col`, `embed`, `hr`, `img`, `input`, `link`, `meta`, `param`, `source`, `track`, `wbr`) the
block exists all the same and writes attributes only; whether the element is void SHALL still be
derived from the element, never configured per component.

**Verified** that this is the shape kotlinx.html gives its own builders, read 2026-11-02 in
kotlinx-html-jvm 0.12.0's sources: `FlowContent.div` (`gen-tag-groups.kt:68`),
`button` (`gen-tag-unions.kt:772`) and the void `input` (`gen-tag-unions.kt:896`) each take
`block: T.() -> Unit = {}` as their last parameter.

**Verified** that a block running after the function's own classes reaches the same element as
`attrs` did, read 2026-11-02 in `codegen/src/generator-new.ts:236-238` and `:298-300`: `attrs()`
already ran after `addClassNames(extraClasses)` and directly before `content()`, on the same
receiver.

#### Scenario: A container takes a trailing block
- **WHEN** `daisyCard { daisyCardBody { +"Hello" } }` is rendered
- **THEN** the `div.card` contains the `div.card-body` with `Hello`

#### Scenario: The block is optional
- **WHEN** `daisyCard()` is rendered
- **THEN** an empty `<div class="card"></div>` is emitted and nothing fails

#### Scenario: A void element takes attributes through its block
- **WHEN** `daisyInput { name = "email" }` is rendered
- **THEN** the `input.input` carries `name="email"` and the following sibling stays at the same
  nesting level

#### Scenario: The block adds to the function's classes
- **WHEN** `daisyButton(color = ButtonColor.Primary, extraClasses = "w-full") { daisyMenuClasses() }`
  is rendered
- **THEN** the button carries `btn btn-primary w-full menu`

#### Scenario: No second lambda
- **WHEN** the generated sources are compiled
- **THEN** no generated rendering function declares a parameter named `attrs` or `content`

### Requirement: A function's text and block add up

Where a generated function takes `text`, passing both `text` and a block SHALL render both: the
block runs first, then the text is appended to the element. Neither SHALL replace or drop the
other.

**Verified** why the block has to run first, read 2026-11-02 in kotlinx-html-jvm 0.12.0
`commonMain/delayed-consumer.kt:14-23`: the consumers `createHTML` and `appendHTML` return
(`stream.kt:147`, `:150`) delay a tag's start only until its first content, and an attribute set
after that throws `IllegalStateException`. Text written before the block would make
`daisyButton("Save") { popoverTarget(menu) }` throw.

**Assumed** that appending the text after the block's children is what a caller who combines both
wants, because the block's main use is attributes. *Wrong if:* a call site in the example app,
the documentation or the tests passes `text` together with a block that writes children which
must follow the text; such a call is then rendered in the wrong order, and the order has to be
reconsidered before the change is built on.

#### Scenario: Text with an attribute block
- **WHEN** `daisyButton("Save") { attributes["data-probe"] = "yes" }` is rendered
- **THEN** the button carries `data-probe="yes"` and contains `Save`

#### Scenario: Text with children
- **WHEN** `daisyButton("Save") { span { +"icon" } }` is rendered
- **THEN** the button contains the `span` followed by `Save`

#### Scenario: Text alone
- **WHEN** `daisyButton("Save")` is rendered
- **THEN** the output is `<button class="btn">Save</button>`
