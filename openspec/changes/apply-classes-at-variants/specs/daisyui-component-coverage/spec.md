# daisyui-component-coverage

## ADDED Requirements

### Requirement: A typed class can be applied at a Tailwind variant

A DaisyUI class this library exposes as a typed value SHALL be applicable at a Tailwind variant —
a breakpoint, a state, or DaisyUI's own — without leaving the type system for a raw string.

Variants that Tailwind stacks SHALL be expressible together; combinations that cannot hold at once
SHALL NOT be expressible.

**Verified** that this is where the escape hatch is actually used. Measured 2026-09-12 over every
`extraClasses` and `addClassNames` call in `example-app`, `e2e-tests`, `lib/src` and `docs`: of 47
distinct tokens, 12 are known DaisyUI classes and **10 of those 12 carry a variant prefix** —
`lg:btn-lg`, `max-sm:megamenu-vertical`, `xl:stats-horizontal`. The remaining 35 are Tailwind
utilities this library does not type.

**Verified** that the alternative shape does not solve it. Of DaisyUI's 555 classes, **536 are
already reachable** through an enum entry or boolean parameter on their own component, so an enum
over all classes would restate the existing API and still not express a prefix.

**Assumed:** that stacking is worth supporting. *Wrong if:* no real call site combines two
variants, in which case a single-variant API is simpler and sufficient — the check is the
measurement in this change's task 1.1.

**Assumed:** that the variant set can be closed. *Wrong if:* DaisyUI's own documented examples use
`group-*`, `peer-*` or arbitrary `data-[…]` variants, in which case the type needs a documented
escape hatch and this requirement covers only the common case.

#### Scenario: A size applied at a breakpoint

- **WHEN** a caller applies a typed size at a breakpoint
- **THEN** the rendered class carries the prefix, e.g. `lg:btn-lg`
- **AND** no raw string appears at the call site

#### Scenario: A variant that cannot be misspelled

- **WHEN** a caller names a breakpoint or state
- **THEN** it is a typed value, so a misspelling is a compile error rather than a class that
  silently styles nothing

#### Scenario: Two variants that can hold at once

- **WHEN** a caller applies a class at both a state and a breakpoint
- **THEN** both prefixes are rendered, in the order Tailwind expects

#### Scenario: Two variants that cannot hold at once

- **WHEN** a caller tries to apply a class at two breakpoints
- **THEN** it does not compile

#### Scenario: The escape hatch is unchanged

- **WHEN** a caller passes a Tailwind utility such as `col-span-12`
- **THEN** `extraClasses` accepts it exactly as before, because typing Tailwind's utilities is not
  this library's business
