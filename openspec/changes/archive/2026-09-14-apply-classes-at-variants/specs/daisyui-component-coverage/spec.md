# daisyui-component-coverage

## ADDED Requirements

### Requirement: A mutually exclusive class group is one typed choice

Where a browser confirms that every pair of classes in a DaisyUI class group is mutually
exclusive, the generated wrapper SHALL expose that group as ONE parameter taking a Kotlin enum,
rather than as one boolean per class. Where it does not, the classes SHALL remain individual
booleans.

The decision SHALL be a recorded measurement, not a category rule, and the build SHALL fail when
the recorded measurement and the configured enum names disagree.

**Verified** that the measurement is the only route to the answer. Six attempts derived
exclusivity from the CSS or from the shape of the class names, and all six were wrong. The
recorded measurement is `codegen/exclusivity.json`: 310 pairwise verdicts over 44 groups, written
wholesale by `just measure-exclusivity` and guarded by `:lib:verifyExclusivity`.

**Verified** that a category rule would have been wrong in both directions. The rule this replaces
made `styles`, `directions` and `placements` enums by virtue of their DaisyUI frontmatter
category; the measurement says `chat.placements` and `carousel.directions` **compose**, while
`card.modifiers` and `list.modifiers` — the category that rule distrusted — are exclusive.

**Verified** the outcome: of the 44 measured groups, 15 are a single choice, 4 carry two
independent axes and split, and 25 stay boolean. That yields 14 enums rather than 15, because
`pagination` is in `skip`.

**Verified** that exclusivity is not transitive, so an axis is a clique and not a connected
component: under `tooltip.placements` both `{top, bottom, left, right}` and `{start, center, end}`
are cliques and `top` belongs to both. A partition therefore cannot be derived from the pairs, and
the axes of a group carrying two are declared and CHECKED against the measurement.

**Verified** the asymmetric cost that makes `same` — "the probe could not tell" — fall to boolean
rather than to enum: a wrong enum makes a combination DaisyUI permits **inexpressible**, which
defeats the library's purpose; a wrong boolean merely permits a combination that does nothing,
which `extraClasses` permits anyway.

*Wrong if:* a group every pair of which measures exclusive nevertheless has a documented call site
wearing two of its members at once. The measurement would then be describing the probe's markup
rather than the CSS, and the failing case is the correction.

#### Scenario: An exclusive group becomes one parameter

- **WHEN** every pair in a group measures `exclusive`
- **THEN** the generated wrapper takes one enum-typed parameter for the whole group
- **AND** naming two members of it at once is a compile error rather than a silent no-op

#### Scenario: A composing group stays boolean

- **WHEN** any pair in a group measures `compose` or `same`
- **THEN** each class stays an individual boolean parameter
- **AND** the combination DaisyUI permits remains expressible

#### Scenario: A group with two independent axes

- **WHEN** a group holds two cliques that compose across the axis boundary, as `indicator`,
  `toast`, `dropdown` and `tooltip` do
- **THEN** it becomes one enum per axis
- **AND** a split whose cross-axis pairs are all exclusive fails the build, because the group is
  then a single choice and two enums would let a caller answer one question twice

#### Scenario: A measurement nobody named

- **WHEN** the measurement says a group is a choice and no enum name is configured for it
- **THEN** generation fails naming the group, because only a human can say what the question is

#### Scenario: A new DaisyUI class does not become a boolean by omission

- **WHEN** an upstream release adds a class to a group that already has declared axes
- **THEN** generation fails unless the class is assigned to an axis or listed as a declared boolean

### Requirement: A typed class can be applied at a Tailwind variant

> **SCOPE, 2026-09-13: BUILT AND PARKED, NOT SHIPPED.** Everything this requirement describes
> exists in `lib/src/…/core/Variants.kt` and is exercised by `VariantsTest.kt`, but `Variant`,
> `Breakpoint`, `State`, `DrawerVariant`, `at` and `and` are `internal`. Only `ClassValues`, the
> parameter type, is public. The scenarios below therefore describe a capability that compiles
> inside the module and that **no consumer can reach**. See the refutation below for why, and the
> gate for unparking.

A DaisyUI class this library exposes as a typed value SHALL be applicable at a Tailwind variant —
a breakpoint, a state, or DaisyUI's own — without leaving the type system for a raw string.

Several applications of the SAME group SHALL be combinable, so that DaisyUI's documented
responsive pattern is expressible. Combinations drawn from DIFFERENT groups SHALL NOT be
expressible.

**REFUTED 2026-09-13, by the thing this requirement exists to enable.** `at()` was built first and
measured against the CSS afterwards. A call site of `at(Breakpoint.Xl, ButtonSize.Lg)` renders
`class="btn xl:btn-lg"` and leaves the compiled stylesheet **byte-identical** — sha256 `3ac649e1`,
412052 bytes, before and after. Tailwind emits CSS per candidate **string** found while scanning
files as text; a class composed at run time from two halves appears in no file. `extraClasses =
"lg:btn-lg"` works precisely because the literal IS the candidate.

A public `at` would therefore hand a caller a class attribute that styles nothing, with no compile
error and no log — the exact failure this library exists to prevent. Hence the parking.

**The gate for unparking:** a build-time extractor over the CONSUMER's compiled classes, emitting
only the combinations that consumer names. Verified feasible at its crux the same day — a
consumer's constant pool carries a `Fieldref` to `ButtonSize.Lg` AND to `Breakpoint.Xl`, so no
dataflow analysis is needed. It is its own change. Unparking is then a visibility flip, which is
why `ClassValues` is public already.

**Corrected 2026-09-14:** this requirement previously read *"Variants that Tailwind stacks SHALL be
expressible together; combinations that cannot hold at once SHALL NOT be expressible."* Both
clauses were wrong about what was built.

Stacking a variant ON a variant — `at(Breakpoint.Lg, at(Breakpoint.Md, x))`, yielding `lg:md:x` —
**is** expressible and was deliberately left so. Forbidding it would need a second type for the
un-prefixed case, and under this change's asymmetric-cost rule permitting a useless combination is
the cheap direction. `lg:md:x` styles nothing, which costs a caller a no-op; making a reachable
combination inexpressible costs the library its purpose.

What is actually prevented is a **cross-group** combination — `ButtonSize.Lg and
ToastVerticalPlacement.Top`, a button size answered with a toast placement. `ClassValues<T>` is
invariant in `T` for that reason; under `out T` the compiler infers the common supertype `Any` and
the combination compiles. Checked both ways.

**Corrected 2026-09-14:** the scenario *"Two variants that cannot hold at once — applying a class
at two breakpoints does not compile"* asserted the opposite of both the built API and DaisyUI's own
documentation. `btn-xs sm:btn-sm md:btn-md lg:btn-lg xl:btn-xl` is one class at five breakpoints
and is the documented responsive pattern; `and` exists precisely to express it. The scenario is
replaced by the two below.

**Verified** that this is where the escape hatch is actually used. Measured 2026-09-12 over every
`extraClasses` and `addClassNames` call in `example-app`, `e2e-tests`, `lib/src` and `docs`: of 47
distinct tokens, 12 are known DaisyUI classes and **10 of those 12 carry a variant prefix** —
`lg:btn-lg`, `max-sm:megamenu-vertical`, `xl:stats-horizontal`. The remaining 35 are Tailwind
utilities this library does not type.

**Verified** that the alternative shape does not solve it. Of DaisyUI's 555 classes, **536 are
already reachable** through an enum entry or boolean parameter on their own component, so an enum
over all classes would restate the existing API and still not express a prefix.

**Verified 2026-09-14** (was Assumed) that the variant set can be closed. Measured over all 68
component pages: DaisyUI's documented examples apply 8 distinct variants to its own classes — `sm`,
`md`, `lg`, `xl`, `max-sm`, `max-md`, `max-lg` and `is-drawer-close` — every one of them inside the
closed set. No `group-*`, no `peer-*`, no arbitrary `data-[…]`. The assumption's *wrong if* did not
fire.

**Resolved 2026-09-13** (was Assumed, *"that stacking is worth supporting"*): dropped. The
measurement found no call site combining two variants on one class, so nothing supports it beyond
the cost of forbidding it — and it is permitted rather than supported, which is a different claim.

**Assumed:** that the ten breakpoints are the right closed set rather than the seven observed.
*Wrong if:* a consumer needs a Tailwind variant this library does not name and, having no variant
escape hatch by decision, cannot reach it — the failing report is the correction. `2xl` is in the
set on exactly this reasoning and appears in no DaisyUI example.

#### Scenario: A size applied at a breakpoint

- **WHEN** a caller applies a typed size at a breakpoint
- **THEN** the rendered class carries the prefix, e.g. `lg:btn-lg`
- **AND** no raw string appears at the call site

#### Scenario: A variant that cannot be misspelled

- **WHEN** a caller names a breakpoint or state
- **THEN** it is a typed value, so a misspelling is a compile error rather than a class that
  silently styles nothing

#### Scenario: One class at several breakpoints

- **WHEN** a caller combines several applications of the same group, as in
  `ButtonSize.Xs and at(Breakpoint.Sm, ButtonSize.Sm) and at(Breakpoint.Lg, ButtonSize.Lg)`
- **THEN** every class is rendered, in the order written
- **AND** this is the pattern DaisyUI documents as `btn-xs sm:btn-sm md:btn-md lg:btn-lg xl:btn-xl`

#### Scenario: Two groups that cannot answer one another

- **WHEN** a caller combines an application of one group with an application of another, such as a
  button size with a toast placement
- **THEN** it does not compile

#### Scenario: The escape hatch is unchanged

- **WHEN** a caller passes a Tailwind utility such as `col-span-12`
- **THEN** `extraClasses` accepts it exactly as before, because typing Tailwind's utilities is not
  this library's business

#### Scenario: The parked API is unreachable from outside the library

- **WHEN** a consumer of the published jar tries to name `at`, `Breakpoint`, `State` or
  `DrawerVariant`
- **THEN** it does not resolve, and none of them appears in `lib/api/lib.api`
- **AND** `ClassValues` DOES appear there, as the parameter type of every enum-backed parameter
- **AND** a variant-prefixed class remains reachable as a literal through `extraClasses`, which is
  the form Tailwind can actually see

> **NOT YET FALSIFIABLE, 2026-09-14 — recorded rather than left to look verified.** `lib/api/lib.api`
> was last dumped on 2026-09-12 (`3bc0cfb`), which predates `ClassValues` and every parked name. So
> the first clause passes today for the wrong reason: `at` is absent because the baseline is stale,
> not because it is internal. The second clause is what makes the scenario falsifiable at all — it
> currently **fails**, since `ClassValues` appears 0 times and `daisyMenu` is still recorded as
> `(…MenuSize;ZZZZZZZ…)`, the pre-enum signature. Re-dumping the baseline is this change's own
> remaining task; until it runs, this scenario is a claim and not a check.
>
> This is the third instance in this change of the same failure: an assertion that passes for a
> reason other than the one it names. The other two were a substring grep where `max-lg:btn-lg`
> satisfied `lg:btn-lg`, and a KDoc comment keeping `lg:btn-lg` alive as a Tailwind candidate.
