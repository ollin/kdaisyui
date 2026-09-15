# Tasks

Estimate frozen 2026-09-14, priced by open decisions per the calibration finding — a block's cost
tracks how many of its decisions already exist, not how much code it touches.

| Block | Open decisions | Estimate |
|---|---|---|
| 1 the census, as a committed check | 1 — what an exception must carry | 1.2 h |
| 2 move the 13 misplaced parameters | 0 — the census says where each goes | 1.5 h |
| 3 the 18 unreachable classes | 2 — `join-item`'s shape, and part-vs-modifier; both decided 2026-09-15, estimate not revised (freeze rule) | 1.5 h |
| 4 `tab`: container and item trade places (#342) | 1 — button, anchor or radio input | 1.0 h |
| 5 migration, baselines, gate | 0 | 0.8 h |
| | **Total, frozen 2026-09-14** | **6.0 h** |
| 6 remove the `parameterNames` indirection — **added 2026-09-15** | 0 — decided | 0.4 h |
| 7 derive single-axis enum names — **added 2026-09-15** | 0 — the rule exists | 0.5 h |
| 8 derive two-axis partitions from an `inert` verdict — **added 2026-09-15** | 0 — measured | 1.5 h |
| 8.5–8.6 derive axis names from DaisyUI's property table — **added 2026-09-15, later the same day** | 0 — documented | 0.5 h |
| | **Total** | **8.9 h** |

Not pre-corrected by the known ratios. Runs so far came in at 0.63 / 0.27 / 0.21 / 0.26 / 0.33
and this change's predecessor at 0.43 / 0.22 / 0.50, so treat 8.9 h as an upper bound — but the
number above is the honest raw one, because a ledger that adjusts its own input measures nothing.

Blocks 6–8 are scope additions that arrived before implementation started; each is priced
separately and dated rather than folded into the frozen 6.0 h, so the ledger can tell the two
apart. All three remove a hand-maintained mapping between DaisyUI and the generated API — the
same defect as blocks 1–4 in a milder form.

**Block 0 is already done and is not estimated**, because it happened before the estimate existed
and retro-fitting is forbidden: the census that produced the 13 and the 18.

**Execution order: 6 → 7 → 8 → 1 → 2 → 3 → 4 → 5** (`design.md` D7). The three removals first,
each provable by diff; block 8 before block 1 so a re-measurement surprise revises the plan
before the census check is built on it.

## 0. The census — DONE, before this change existed

- [x] 0.1 Parse every fenced html example on all 68 component pages and, for each `$$` class,
  record whether it sits on the element carrying the component class. **414 classes examined, 80
  never on the component element.**
- [x] 0.2 Intersect those 80 with `lib/api/components.api` to separate the ones that are
  nevertheless container parameters from the ones that are merely unreachable. **13 and 18.**

## 1. The census becomes a check — 1.2 h

- [x] 1.1 `^ F (internal)` Generalise `element-cross-check.ts` from the component class to every
  class, reusing `parser/documented-classes.ts`. Same three failure modes it already has: a
  disagreement with no exception, a class DaisyUI documents no element for, and an exception no
  longer needed. *Done in five commits (`6801a73`..`e2b1860`). The "undocumented" mode applies
  to the component class only; a modifier shown nowhere or on several elements is unchecked. The
  check reports **60** disagreements, not 31: the census counted container parameters, the
  check also sees parts on the wrong element (`fieldset-legend` as `<div>`) and two components
  whose first example differs from every variant example (`badge`, `status` as `<span>` vs
  `<div>`; `dropdown` as `<details>`). 1.2 files all 60.*
- [ ] 1.2 `. d` Record the 60 disagreements 1.1 reports as exceptions naming this change, so the check lands green and
  then fails as each is fixed. **An exception must expire on its own** — that is what stops the
  list becoming the hand-maintained list this project has deleted twice.
- [ ] 1.3 `^ F (internal)` Wire it beside `checkComponentApi` in CI, NOT into `check`: it reads
  the submodule, and a clone must compile and test without one.

## 2. The 13 misplaced parameters — 1.5 h

Ordered by blast radius, largest first, so the shape is settled before the small ones follow.

- [ ] 2.1 `^ F` `indicator`: both placement enums move from `daisyIndicator` to
  `daisyIndicatorItem`. Six enum members, shipped in 0.6.0.
- [ ] 2.2 `^ F` `menu`: `active`, `disabled` and `dropdownShow` leave `daisyMenu`.
  `menu-dropdown-show` belongs on `daisyMenuDropdown` and `daisyMenuDropdownToggle`, which exist;
  `menu-active` and `menu-disabled` sit on an `<a>`/`<li>` the library does not generate — block 3
  decides whether it should.
- [ ] 2.3 `^ F` `list`: `ListModifier` moves from `daisyList` to the row. Shipped in 0.6.0.
- [ ] 2.4 `^ F` `dock-active` and `tab-content`.

## 3. The 18 unreachable classes — 1.5 h

- [x] 3.1 `. d` **Decide part vs. modifier.** A class on a child that always has the same parent
  is a part and gets a function. `join-item` is not that: DaisyUI puts it on `<button>`, `<input>`
  and `<select>`, so it is a modifier on **any** component that can be a join's child.
  **Decided 2026-09-15 (Oliver): the call is the marking.** `daisyJoin`'s lambda receives a
  `JoinScope` — `FlowContent` by delegation to the `DIV`, annotated with kotlinx.html's
  `@HtmlTagMarker` — carrying **member** overloads of the component functions DaisyUI documents
  with `join-item` (`btn` 52×, `input` 5×, `card` 3×, `collapse` 3×, `select` 1× across all 68
  pages; the list is derived from the markup, not configured). A member beats an extension, so
  `daisyJoin { daisyButton("A") }` emits `join-item` with nothing to type; `@DslMarker` hides the
  scope in nested lambdas, so the three documented wrapper cases (`div > div > input.join-item`)
  are written `this@daisyJoin.daisyInput()` and a nested button is not marked. Outside
  `daisyJoin` no `JoinScope` exists, and `join-item` — which strips an element's corners when no
  `.join` sets the radius variables — cannot be written. Rejected: a `joinItem` parameter
  (writable outside a join), a `daisyJoinItem { }` wrapper (its "exactly one tag" is a runtime
  rule), auto-marking every DOM child (wrong in 3 of 74 measured cases). Known limit, accepted:
  an element outside the five stays on `extraClasses`. Full comparison in
  `tmp/non-deterministic-v2.md` §3.
- [ ] 3.2 `^ F` Generate the parts that are plainly parts: `avatar-group`, `list-row`,
  `carousel-item`, `megamenu-active`, `filter-reset` and the rest of the 18.
- [ ] 3.3 `^ F (internal)` The generator derives the join-child list: every component class that
  co-occurs with `join-item` on one element in DaisyUI's markup. Pin the five with a test; a
  sixth component appearing in DaisyUI's docs must extend the list without a config edit.
- [ ] 3.4 `^ F` Generate `JoinScope` and its member overloads; `daisyJoin`'s `content` becomes
  `JoinScope.() -> Unit`. Test: `daisyJoin { daisyButton("A") }` renders `class="btn join-item"`;
  `daisyJoin { div { daisyInput() } }` renders no `join-item`; `daisyJoin { div {
  this@daisyJoin.daisyInput() } }` does; a `daisyButton("A", extraClasses = "join-item")` outside
  a join is unchanged (still reachable, still a string).
- [ ] 3.5 `. d` Reference page for `join` shows the three shapes: plain child, wrapper with
  `this@daisyJoin.`, and why a nested button is not marked.

## 4. `tab` — container and item trade places — 1.0 h

- [ ] 4.1 `. d` **Decide the item element.** DaisyUI documents `<button>`, `<a>` and
  `<input type="radio">`; #342 suggests `customParts` since there is more than one.
- [ ] 4.2 `^ F` `daisyTab` renders `<div class="tabs">`, the item renders the focusable element
  carrying `tab`, and `tabActive`/`tabDisabled` move to the item.
- [ ] 4.3 `. d` Remove the cross-check exception #341 recorded for `tab`; it must fail if left.

## 5. Migration, baselines, gate — 0.8 h

- [ ] 5.1 `. d` **How to migrate**, naming every moved parameter. This withdraws public API that
  0.6.0 introduced — say so plainly rather than describing it as a refinement. Delete the "Five
  booleans were renamed" entry outright: after block 6 those parameters are identical to 0.5.x,
  so there is nothing to migrate.
- [ ] 5.2 `. d` Re-dump both baselines with `just update-api` and read each diff.
- [ ] 5.3 Full green per `openspec/config.yaml`, plus the new check and `:lib:pitest`.
- [ ] 5.4 Write the evaluation under `tmp/`.

## 6. Remove the `parameterNames` indirection — 0.4 h, added 2026-09-15

Runs before block 5, because 5.1 and 5.2 read the result. Decided by Oliver: no renames — a
boolean is named after DaisyUI's class, and the class is what a user searches for.

- [x] 6.1 `^ r (internal)` Delete the `parameterNames` section from `codegen-config.json`, its
  read in `component-shape.ts` (`readComponentConfig` and the `parameterNames[cls] ??
  toCamelCase(cls)` fallback, which becomes `toCamelCase(cls)` alone), its `config-consumption.ts`
  entry, and the `test-generator.ts` comment that names it. The consumption guard's test list
  shrinks by one; let that test fail first, then pass.
- [x] 6.2 `! F` `just generate` — `Rating`, `Dropdown`, `Menu`, `Timeline` and their tests and
  reference pages change; `git status` shows nothing else. Remove the `parameterNames` row from
  `.opencode/skills/kdaisyui-codegen/SKILL.md`. *Done as `! F`, not `. d`: the regenerated
  Kotlin changes four public signatures, so it is one behaviour over 30 lines. The README's
  five-renames entry and `components.api` went with it, so 5.1/5.2 find them already done.*

## 7. Derive single-axis enum names — 0.5 h, added 2026-09-15

Runs after 6 and before 8: block 8 reshapes the same `enumNames` section.

- [x] 7.1 `^ r (internal)` A single-axis exclusive group is named `<Component><CategoryWord>`
  by the generator; the 13 single-axis entries leave `enumNames`, `requireName` and its test go.
  Pin with a test that an unnamed all-exclusive group produces the derived name instead of
  failing.
- [x] 7.2 `! F` `just generate`: only `MaskShape` → `MaskStyle` and its call sites change
  (`lib/generated/**`, `docs/reference/mask.md`, the README enum table, `lib/api/*`). Update the
  `enumNames` row in `.opencode/skills/kdaisyui-codegen/SKILL.md`.

## 8. Derive two-axis partitions from an `inert` verdict — 1.5 h, added 2026-09-15

Needs the system Chromium (`just measure-exclusivity`). Ordered so the schema change lands with
its measurement, and the derivation only reads a file that already carries the new verdict.

- [x] 8.1 `^ F (internal)` The probe renders one baseline case per group (example stripped of
  every member, nothing injected); `exclusivity-verdicts.js` emits a fourth verdict, `inert`,
  when a member's single case has the baseline's signature, and records for **every** member the
  CSS custom-property names its rules declare, read from the CSSOM. `Measurement` and
  `MeasuredPairs` read both. Test named after the tooltip case.
- [x] 8.2 `. d` Re-measure and commit `codegen/exclusivity.json`. Expected: `tooltip-top` the
  only `inert` member, every group gains `declares`, **no pair verdict changes** — read the diff
  to confirm. *Outcome: no pair changed, `declares` everywhere — and **25** inert members, not
  one. The assumption was refuted; 8.3/8.4 and the spec were revised before either was built.
  Also found: a linked stylesheet hides its `cssRules` from a file:// page; fixed with a test
  (`46ee859`).*
- [x] 8.3 `^ F (internal)` Axes of a group are the connected components of the exclusive graph
  over non-inert members: one member → boolean; a clique of two or more → enum; a component
  that is **not** a clique → boolean (not-established rule — `button.styles`, `aura.styles`).
  Applied to every group. Expected against the re-measured file: `indicator`, `toast`,
  `tooltip` two cliques of three; `dropdown` `{start, center, end}`, `{top, bottom}`, `left`,
  `right`; every single-axis enum unchanged; **and three new enums** — `alert.styles`
  `{outline, dash}`, `avatar.modifiers` `{online, offline}`, `badge.styles` `{outline, dash}`
  and `{soft, ghost}` — the same shape as dropdown, same rule (Oliver, 2026-09-15).
  `enumNames` keeps only axis names; `checkAxisIsExclusive` / `checkSplitIsEarned` become the
  derivation's own assertions. *Revised after 8.2 refuted "fail on a non-clique".*
- [x] 8.4 `^ F (internal)` An inert member joins the clique it is exclusive with every member
  of; when two qualify (`tooltip-top`), the one whose members declare the same CSS properties;
  when none or still two, fail generation naming the member. Pin all four outcomes:
  `loading-spinner` → the one clique; `tooltip-top` → `{bottom, left, right}` by `declares`;
  a default of a boolean-only group (`rating-hidden`) stays boolean; an unplaceable member
  fails. *Done in one commit with 8.3 (`e0434a5`): the placement is the derivation's step 3,
  not a layer on top. Two corrections while building: an inert member exclusive with no
  component stays boolean (carousel-horizontal composes with vertical); and placement is
  decided against the live components all at once, or menu's four invisible classes made a
  spurious enum depending on iteration order.*
- [x] 8.5 `^ F (internal)` A reader for the custom-property table in
  `docs/utilities/+page.md` (variable → description). An axis whose members' declared
  properties are all described with one of {vertical, horizontal}, and whose sibling axis with
  the other, is named `<Component><Word>Placement`; `enumNames` is consulted only when that
  yields nothing, and an entry for a derivable group fails generation. Tests: `indicator`,
  `toast`, `dropdown` derive; `tooltip` does not and reads its entry; a `tooltip` entry removed
  fails naming both axes; an `indicator` entry added fails as redundant.
- [x] 8.6 `! F` `enumNames` shrinks to `tooltip` and `badge` — the two groups whose axis words
  no DaisyUI document carries. Badge: `OutlineStyle` `{outline, dash}` and `FillStyle`
  `{soft, ghost}`, named after what the classes do (border treatment vs. fill treatment) —
  Confirmed by Oliver 2026-09-15. `just generate`; expected diff: `indicator`, `toast`,
  `tooltip` and every single-axis enum unchanged; `daisyDropdown` gains
  `vertical: ClassValues<DropdownVerticalPlacement>?` for `top`/`bottom` and
  `DropdownAlignPlacement` becomes `DropdownHorizontalPlacement`; `daisyAlert` gains
  `style: ClassValues<AlertStyle>?` for `outline`/`dash` (`soft` stays); `daisyAvatar` gains
  `modifier: ClassValues<AvatarModifier>?` for `online`/`offline` (`placeholder` stays);
  `daisyBadge` gains the two badge enums; `daisyDropdown` also gains
  `modifier: ClassValues<DropdownModifier>?` for `hover`/`open` (`close` stays) — found by 8.3,
  not predicted, same shape; `indicator` and `toast` list `horizontalPlacement` before
  `verticalPlacement`, as DaisyUI orders the classes. Both API baselines re-dumped and read;
  README enum table extended. Nothing else.

**Why `dropdown` changes shape in 8.5:** today's config declares `top, bottom, left, right` as
booleans, and `b8acf18` says "the browser says they compose". That is true of `left|right` and of
every side×side pair except `top|bottom`, which the measurement has always recorded as exclusive.
The derivation follows the measurement; a hand-declared exception against it was considered
(2026-09-15) and rejected as the kind of mapping this change exists to remove.

## Not in this change

**Tagging 0.6.0.** `gradle.properties` declares it and no tag exists, so nothing has shipped.
Releasing first would publish two enums on the wrong function and withdraw them one release later.
The release follows this change.
