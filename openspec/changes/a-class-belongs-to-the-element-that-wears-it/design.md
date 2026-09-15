## Context

See `proposal.md` for why. What shapes the approach:

- `codegen/src/element-cross-check.ts` already compares the generator's chosen element against
  DaisyUI's documented one for the **component class**, with three failure modes (disagreement
  without exception, undocumented element, stale exception). It is the mechanism to generalise,
  not to duplicate.
- `codegen/src/parser/documented-classes.ts` (2026-09-14) reads, for every `$$`-marked class in
  every fenced example, the element that carries it. The census in `tasks.md` block 0 was a script
  over that parser; block 1 makes the same reading a build failure.
- `codegen/src/class-groups.ts` decides enums from `codegen/exclusivity.json` and checks the
  declared axes in `enumNames` against it. The checks stay; what they check changes from a
  declaration to a derivation.
- `codegen/src/exclusivity-verdicts.js` runs in the browser and cannot import TypeScript. It
  computes one signature per case and derives `exclusive` / `compose` / `same` per pair. A
  baseline case per group does not exist yet.
- Everything in `lib/generated/**` and `docs/reference/**` is regenerated wholesale; the
  `generated-sources-drift` job, `:lib:apiCheck` and `:lib:checkComponentApi` guard the result.
- 0.6.0 is declared and untagged. Every API this change withdraws is unreleased.

## Goals / Non-Goals

**Goals:**

- One rule, mechanically checked: a class is declared on the function whose element wears it.
- Every hand-maintained mapping between DaisyUI and the generated API that this change touches
  is removed or derived — `parameterNames`, single-axis `enumNames`, the axis partition, the
  join-item list.
- Every decision that remains (multi-axis names, `same` → boolean) is forced by a build failure
  and checked against the measurement.

**Non-Goals:**

- Unparking `at()` / Tailwind-variant composition — `extract-used-classes-from-bytecode`.
- A typed `join-item` for elements DaisyUI does not document as join items; they stay on
  `extraClasses`.
- Re-measuring anything but the `inert` outcome. The 310 existing verdicts are kept unless the
  re-measurement contradicts them, which the **Assumed** mark on the spec says would revise this
  change.

## Decisions

Enablers are behaviour-preserving and provable by an empty `git status lib/generated`; desired
changes alter the generated API. Each block in `tasks.md` says which it is.

### D1. Generalise the existing cross-check rather than add a lint

**Decision:** `element-cross-check.ts` takes one observation per *(class, declaring function's
element)* instead of one per component, fed by `documented-classes.ts`. Exceptions are keyed by
class, carry reason and issue, and expire on their own — the three failure modes are unchanged.

**Alternatives:**
- A separate census script run in CI (the block-0 script, committed). Rejected: it would be a
  second list of components-with-known-defects, without the expiry rule that keeps the existing
  exception list honest — the hand-maintained list this project has deleted twice.
- Fixing the 13 and 18 without a check. Rejected: the defect has been found by hand three times;
  nothing stops the fourth.

Enabler first (1.1 lands green because 1.2 files the 31 as exceptions), then each fix in blocks
2–4 removes one exception and the check proves it.

### D2. `join-item` is emitted by member overloads on a join scope

**Decision:** `daisyJoin`'s `content` becomes `JoinScope.() -> Unit`, where `JoinScope` is
`FlowContent` by delegation to the `DIV` and carries kotlinx.html's `@HtmlTagMarker`. `JoinScope`
declares **members** with the exact signature of the top-level `daisyButton`, `daisyInput`,
`daisySelect`, `daisyCard`, `daisyCollapse`, each adding `join-item`. The member list is derived
at generation time: every component class that co-occurs with `join-item` on one element in
DaisyUI's markup.

Why it holds at compile time: a member beats an extension of the same name (Kotlin resolution);
`@DslMarker` on the scope type hides it inside nested lambdas, so a button inside a button, or an
input inside a wrapper `div`, is not marked unless written `this@daisyJoin.daisyInput()`; outside
`daisyJoin` no scope exists, so `join-item` — harmful without a `.join`, see the spec — is
unwritable.

**Alternatives** (full comparison in `tmp/non-deterministic-v2.md` §3):
- `joinItem: Boolean` on the five components. Rejected: writable outside a join, where it strips
  the element's corners; scoping cannot help a parameter.
- `daisyJoinItem { … }` wrapper injecting the class into the next opened tag via a delegating
  `TagConsumer`. Rejected: "exactly one tag in the lambda" is a runtime rule with a compile-time
  alternative available; and it would be the only class-setting function without an element.
- A constant for `extraClasses`. Rejected: undiscoverable, and `extraClasses` is the escape hatch
  for classes the library does not know.
- Marking every direct child automatically. Rejected by measurement: 3 of 74 documented children
  are wrappers.

The member signatures are generated from the same component shape the extensions come from, so
they cannot drift; `lib/api/components.api` records both.

### D3. Remove `parameterNames`; a parameter is its class

**Decision:** delete the config section, its reader and the `?? toCamelCase(cls)` fallback
becomes the only path. Desired change: five parameters return to their 0.5.x names.

**Alternative:** keep the renames and emit the CSS class into each `@param` line so it is
searchable. Rejected: it keeps the mapping and adds text; removing the mapping serves both readers
— the DaisyUI reader finds the class name, the call-site reader finds DaisyUI's `desc`.

### D4. Single-axis enum names are derived

**Decision:** `<Component><CategoryWord>` — `Direction`, `Style`, `Modifier`, `Placement`,
`Behavior` — computed by the generator. `enumNames` keeps only multi-axis groups, and each
entry is a list of axis names in component order. `requireName` and the "declared booleans beside
an axis" form go.

**Alternative:** keep the entries and validate them against the rule. Rejected: a config that
must restate a rule is a mapping with drift potential and no information.

Enabler except for `MaskShape` → `MaskStyle`, which is the one desired change and the proof the
rule was not already universal.

### D4b. Multi-axis names are derived from DaisyUI's property table where it names a direction

**Decision:** the generator reads the CSS custom-property table in
`packages/docs/src/routes/(routes)/docs/utilities/+page.md` (a Markdown table: variable,
description). For each derived axis it collects the custom properties its members declare
(block 8 records them from the CSSOM for every member, not only inert ones) and looks each up.
If every property of the axis is described with one word from {vertical, horizontal} and the
other axis with the other, the axis is `<Component><Word>Placement`. `enumNames` then holds
**only** groups where that yields nothing — today `tooltip` — and an entry for a derivable
group fails the build.

**Why a table and not the `desc` prose:** the frontmatter `desc` lines also carry "align
vertically"/"align horizontally", but in three different phrasings across four components. The
utilities table is a reference document with one row per variable; a row disappearing or its
word changing fails loudly instead of silently reparsing.

**Why the tooltip stays declared:** its `--tt-trans`/`--tt-inset` feed `translateX`/`inset-inline`
under top/bottom and `translateY`/`inset-block` under left/right — the alignment axis is
relative to the side, and DaisyUI's table describes the variables without a direction word. That
is the `side + alignment` model, and `Side`/`Align` is its vocabulary.

**Alternatives:**
- Declare all eight (the state before this decision). Rejected 2026-09-15: six of them restate
  a document DaisyUI maintains.
- Derive from the property *name* suffix (`-x`/`-y`, `-h`/`-v`) without the table. Rejected:
  a naming convention is the kind of source that was wrong six times for exclusivity; the table
  is where DaisyUI states the meaning.

Desired change: `DropdownAlignPlacement` becomes `DropdownHorizontalPlacement` — DaisyUI's word,
not the Floating UI analogy. Cost: one Markdown-table reader (~40 lines) and one more input the
drift job depends on.

### D5. A fourth verdict, `inert`, measured against a baseline case

**Decision:** the probe renders one additional case per group — the example with every member of
the group stripped and nothing injected — and `exclusivity-verdicts.js` marks a member `inert`
when its single case has the baseline's signature. For each inert member the browser also records
the CSS property names its rules declare, read from the CSSOM (`document.styleSheets`, rules
whose selector names the class), and writes them beside the verdict. `:lib:verifyExclusivity`
guards both.

**Alternatives:**
- Read declared properties from the CSS source with a parser. Rejected: the project's record on
  deriving anything about exclusivity from CSS text is six for six wrong; the CSSOM is what the
  browser actually applied.
- Assign the inert member's axis by hand in `enumNames`. Rejected: one more mapping, and the one
  case it would cover (`tooltip-top`) is exactly the case the measurement can settle.
- Drop inert members from the enum. Rejected: `tooltip-top` is a documented class; a caller must
  be able to name it even though it is the default.

Cost: 44 extra renders in a run that already does ~400. Order in `tasks.md`: schema and
re-measurement (8.1, 8.2) before anything reads the new outcome.

### D6. Axes are the connected components of the exclusive graph

**Decision:** over non-inert members, a component of one member stays boolean, a component of
two or more becomes an enum, and `checkAxisIsExclusive` runs on each derived component — a
component that is not a clique fails the build as an inconsistent measurement.
`checkSplitIsEarned` becomes a tautology (components compose across by construction) and is
kept as an assertion.

**Alternative:** keep declaring the partition and only check it. Rejected: the declaration was
the thing that contradicted the measurement for `dropdown` (`{top, bottom}` exclusive since
`b8acf18`, declared boolean), and once inert members are visible there is no partition left to
declare.

Desired change: `daisyDropdown` gains `DropdownVerticalPlacement` in place of `top`/`bottom`.
`indicator`, `toast`, `tooltip` must come out unchanged — that empty diff is the evidence 8.3
derived what 0.6.0 declared.

### D7. Order of blocks

6 → 7 → 8 → 1 → 2 → 3 → 4 → 5. The three removals first: they shrink `enumNames` and the config
the later blocks read, and each is provable by diff. Then the check (1), then the fixes it
verifies (2–4), then migration and gate (5). Block 8 needs Chromium and is placed before block 1
so a re-measurement surprise revises the plan before the census check is built.

## Risks / Trade-offs

- [Member-vs-extension resolution or `@DslMarker` behaves differently than the Kotlin docs
  state] → 3.4's four pinned tests are written first and must fail on the plain extension; if
  resolution is ambiguous the design falls back to D2's second alternative and says so in
  `tasks.md`.
- [Re-measurement with `inert` changes more than `tooltip-top`] → the **Assumed** mark on the
  spec names this; 8.2 reads the diff before 8.3 is built; any other change revises the spec.
- [Doubled surface in `lib/api/*` for the five join members] → accepted; it is generated from
  one shape and `checkComponentApi` reads both.
- [Block 8 needs a system Chromium] → same constraint as `just measure-exclusivity` already has;
  documented in `kdaisyui-daisyui-upgrade`.
- [`enumNames` list form breaks a reader expecting the old object form] → the consumption guard
  test is updated in 7.1; a stale entry fails generation rather than being ignored.
- [`tab` item element (4.1) is a decision, not a derivation] → it is listed as an open decision
  in the estimate and taken in 4.1 before 4.2; `customParts` already covers "more than one
  element".

## Migration Plan

Nothing shipped: 0.6.0 is untagged. So:

1. `README.md` "How to migrate from 0.5.x": delete the five-renames entry; correct the enum table
   (`MaskShape` → `MaskStyle`, `DropdownVerticalPlacement` added); add the moved parameters and
   the `tab` swap as 0.6.0 entries.
2. `just update-api` re-dumps both baselines; the diff is read, not accepted (5.2).
3. Rollback is `git revert` of the block; every block leaves `lib/generated/**` regenerable from
   its inputs, so there is no state outside the commit.

## Open Questions

None that would change the specs or the tasks. The `tab` item element (4.1) is scheduled, not
open.
