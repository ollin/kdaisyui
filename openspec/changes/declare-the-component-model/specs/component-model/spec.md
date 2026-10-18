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
editorial summary. From DaisyUI, generation SHALL read only the frontmatter: the class names and
their descriptions. It SHALL NOT read the fenced HTML examples.

Whether a function takes content stays derived from its declared element by the HTML
specification's void-element list. That is a rule of HTML, not an inference about DaisyUI.

**Assumed** that the model can carry every fact today's inferred shape holds, so the switch is
behaviour-preserving before any desired change is made. *Wrong if:* generating from the
bootstrapped model produces any difference under `lib/generated/`, `docs/reference/`, `llms.txt`
or `lib/api/`.

#### Scenario: Generating without reading example markup

- **WHEN** the generators run
- **THEN** they read the model and the frontmatter
- **AND** no fenced HTML example from DaisyUI's documentation is read

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

**Assumed** that the frontmatter lists every class a component's examples use. *Wrong if:* a
`$$`-marked class in a page's fenced examples is absent from that page's frontmatter at the pinned
tag.

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
camelCase. A class-bearing boolean SHALL exist only for a toggle. An enum that exists in 0.7.0
SHALL keep its name and entries.

Which classes share an axis is a design decision recorded in the model, not a measurement. Two
members of one axis cannot be passed together in typed form; that combination stays reachable
through `extraClasses`.

**Verified** which combinations the declared axes give up, against `codegen/exclusivity.json` at
DaisyUI 5.7.42 before it is deleted: 24 pairs measured `compose` and land on one axis. Twelve are
opposite values of one property, such as `chat-start` with `chat-end`. Twelve combine two looks,
such as `btn-outline` with `btn-ghost`, and those are the ones a caller might want. All 24 are
listed in `design.md`.

**Verified** as a decision, Oliver, 2026-10-18: one enum-typed parameter per axis with defaults;
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
