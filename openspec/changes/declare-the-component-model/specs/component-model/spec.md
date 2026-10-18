## Purpose

The generated component API is declared in a committed, reviewed model rather than inferred from
DaisyUI's documentation, so a structural fact changes only by an edit someone reads, and a DaisyUI
release is read as a delta against that model.

## ADDED Requirements

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
frontmatter is placed exactly once in its model: as a function's own class, a part's class, an
axis member, a toggle, a class an extra parameter or a construction writes, or an entry listed as
not offered with a reason. Generation SHALL also fail when the model names a class the frontmatter
does not declare, when a DaisyUI component directory has no model file and is not listed as
skipped, and when a model file names no existing directory.

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

- **WHEN** the model places one class in two roles, such as a toggle and an axis member
- **THEN** generation fails naming both places

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

**Assumed** that these four kinds of delta name everything the deleted guards caught on real bumps.
*Wrong if:* replaying the 5.7.17 to 5.7.42 bump does not name `mask-square` as removed, or a later
bump surfaces a defect the deleted element cross-check would have caught and the report did not
name.

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

- A function is `daisy` followed by its stem, the PascalCase form of the DaisyUI class its element
  carries: `stats` gives `daisyStats`, `stat-title` gives `daisyStatTitle`. The model declares a
  different stem in two cases only: an abbreviated class, `btn` giving `Button`, and a function
  whose element carries no DaisyUI class of its own, such as a construction method.
- An axis is a noun naming what is chosen. It is DaisyUI's frontmatter category word where that
  category forms exactly one axis: `color`, `size`, `style`, `direction`, `placement`. Otherwise it
  is a declared word, such as `side`, `align`, `shape` or `width`. No axis is called `variant` or
  `modifier`, because neither names what is chosen.
- An enum is the function's stem followed by the PascalCase axis name: `ButtonColor`,
  `StatsDirection`.
- An entry is the PascalCase class suffix after the function's own class: `ButtonColor.Primary`
  for `btn-primary`, `StepColor.Primary` for `step-primary`.

A test SHALL fail when a function whose element carries a DaisyUI class is named otherwise and
the model declares no stem for it.

**Verified** that one declared stem suffices for the component functions, read 2026-10-18 from the
frontmatter at the pinned tag: of the generated components, only `button` (`btn`), `stat`
(`stats`) and `tab` (`tabs`) carry a component class that differs from their directory name, and
`stats` and `tabs` are the names the rule should produce. `calendar` declares no component class
and gets a declared stem.

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
