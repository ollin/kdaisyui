# daisyui-component-coverage

## ADDED Requirements

### Requirement: A generated function never requires content its element cannot hold

A generated component function SHALL NOT declare a `content` parameter when the element it
renders is an HTML void element. This SHALL be derived from the element, not configured per
component.

The void elements are fixed by the HTML specification: `area`, `base`, `br`, `col`, `embed`,
`hr`, `img`, `input`, `link`, `meta`, `param`, `source`, `track`, `wbr`.

**Verified** that deriving loses nothing. Measured over all 66 non-skipped components on
2026-09-12: the eight entries of the hand-maintained `noContent` list are exactly the components
whose element is void, and no listed component renders a non-void element.

**Verified** that transcribing it by hand failed in both possible directions. Two entries were
written as DaisyUI directory names (`file-input`, `theme-controller`) while the lookup used the
lower-cased PascalCase name, so neither ever matched; and `mask`, which renders `<img>`, was never
listed. All three declared a **required** trailing `content` lambda on an element that cannot have
children.

*Wrong if:* a component must suppress `content` for a reason other than its element being void.
The rule is then too narrow and an exception list has to return — carrying a stated reason per
entry, rather than restating HTML.

#### Scenario: A component rendering a void element

- **WHEN** the codegen generates a component whose element is `<input>`, `<img>` or any other
  HTML void element
- **THEN** the generated function declares no `content` parameter
- **AND** its body emits no content call

#### Scenario: A component rendering an element that may have children

- **WHEN** the codegen generates a component whose element is not a void element
- **THEN** the generated function declares a `content` parameter

#### Scenario: A DaisyUI release adds a component rendering a void element

- **WHEN** a new component's element is a void element
- **THEN** its generated function takes no `content` parameter without anyone configuring it

### Requirement: The generated element matches the one DaisyUI documents

The codegen SHALL compare the element it chose for each component against the element DaisyUI's
own documentation shows, and SHALL fail when they disagree without a recorded exception.

The authority is the fenced ```html example in the component's
`packages/docs/src/routes/(routes)/components/<name>/+page.md`, in which daisyUI class names carry
a `$$` marker. The tag bearing `$$<componentClass>` is the documented root element. The marker is
what makes this a statement rather than a heuristic: it separates daisyUI classes from Tailwind
utilities without inference.

**Verified** that this route is complete where the previously-used one is not. Measured on
2026-09-12 over all 66 non-skipped components: the docs route answers **66**; the
`skills/daisyui/components/*.md` `#### Syntax` block answers 57 and is absent for `calendar`,
`dropdown`, `fab`, `filter`, `label`, `menu`, `modal`, `swap` and `tab`.

**Verified** that it disagrees with the generator exactly three times, and that all three are
defects rather than false positives: `otp` renders `<div>` where the docs show `<label>`, so a
click does not focus the wrapped input; `tab` renders `<button>` carrying the container class
`tabs` where the docs show `<div>`; `calendar` renders `<div>` where the docs show a custom
element.

*Wrong if:* DaisyUI stops marking class names with `$$`, or the first documented example ceases to
be the canonical construction. Either makes the cross-check fail rather than answer wrongly.

#### Scenario: The chosen element matches the documentation

- **WHEN** the codegen runs and every component's element matches its documented one
- **THEN** generation succeeds

#### Scenario: The chosen element contradicts the documentation

- **WHEN** a component's chosen element differs from the one DaisyUI documents
- **AND** no exception is recorded for that component
- **THEN** generation fails, naming the component, the chosen element and the documented one

#### Scenario: A disagreement that is recorded and tracked

- **WHEN** a component's element disagrees and an exception records a reason and a tracking issue
- **THEN** generation succeeds

#### Scenario: An exception that is no longer needed

- **WHEN** a component with a recorded exception stops disagreeing
- **THEN** generation fails, so the stale exception is removed rather than accumulating
