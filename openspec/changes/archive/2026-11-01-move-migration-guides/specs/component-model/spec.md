## MODIFIED Requirements

### Requirement: The model accounts for every class DaisyUI declares

Generation SHALL fail, naming the component and the class, unless every class in a component's
frontmatter is placed in its model: as a function's own class, a part's class, an axis member, a
toggle, a class an extra parameter or a construction writes, or an entry listed as not offered with
a reason. Generation SHALL fail when one function offers the same class twice. Generation SHALL
also fail when the model names a class the frontmatter does not declare, when a DaisyUI component
directory has no model file and is not listed as skipped, and when a model file names no existing
directory.

A class MAY be placed on several functions. Measured 2026-10-18 before the gate was written:
`modal` and `skeleton` are each the own class of two constructions, and `timeline-box` is a toggle
on three parts. All three are correct. The defect the duplicate check exists for is one function
offering one class twice.

This replaces three guards with one set comparison: the unread-configuration guard, the
offered-twice guard and the measured-group completeness check.

**Verified** that the removal direction matters: `mask-square` stayed in the generated API from
DaisyUI 5.0 until 5.7.19 removed it from the documentation, and was withdrawn in kdaisyui only on
the 5.7.42 bump (`docs/upgrading.md`, "How to migrate from 0.5.x", item 12).

**Verified** that the placed-twice direction matters: 0.6.0 shipped `daisyDropdown` with five
placement booleans beside the enums that replaced them (`docs/upgrading.md`, "How to migrate from
0.6.x", item 1).

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

- **WHEN** one function offers one class in two roles, such as a toggle and an axis member
- **THEN** generation fails naming the function and the class

#### Scenario: One class on several functions

- **WHEN** two constructions of one component both write the component's class
- **THEN** generation succeeds

#### Scenario: DaisyUI adds a component

- **WHEN** the submodule has a component directory with neither a model file nor a skip entry
- **THEN** generation fails naming the directory
