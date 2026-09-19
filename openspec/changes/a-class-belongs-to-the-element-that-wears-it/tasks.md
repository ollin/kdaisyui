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
- [x] 1.2 `. d` Record the 58 disagreements 1.1 reports as exceptions naming this change, so the check lands green and
  then fails as each is fixed. **An exception must expire on its own** — that is what stops the
  list becoming the hand-maintained list this project has deleted twice. *Done (`8ce04d6`):
  58, not 60 — the tick commit miscounted. Needed one enabling step first (`7db7916`): the
  config-consumption guard did not know the `directory/class` key form. Exceptions point at
  #347 until this change has its own issue; no GitHub access from this session.*
- [x] 1.3 `. d` Wire it beside `checkComponentApi` in CI, NOT into `check`: it reads
  the submodule, and a clone must compile and test without one.

## 2. The 13 misplaced parameters — 1.5 h

**Revised 2026-09-15 after 2.0 landed.** The move is not per component; it is one rule in
the shape (`ClassPlacement`, `f7ff2d8`): a boolean is declared on every part whose element is
the one DaisyUI shows the class on, and on main otherwise. Regenerating applies it everywhere at
once — and moved exactly one class, `timeline-box`, because for every other one the part
renders the **wrong element** (`daisyIndicatorItem` is a `<div>`, DaisyUI shows a `<span>`).
So the parts' elements come first (block 3), and each fix there moves its classes for free.
Enum moves (indicator's two, list's one) are a separate rule not yet written: an enum whose
every member DaisyUI shows on a part's element belongs to that part whole.

- [x] 2.0 `^ F (internal)` The placement rule, derived from the documented elements the
  cross-check already reads; the Kotlin body follows the signature. (`88675be`, `f7ff2d8`)
- [x] 2.4 `^ F` `timeline-box` → `daisyTimelineStart`/`Middle`/`End`; its exception expired
  (`89b0519`). *`dock-active` and `tab-content` wait for their parts' elements.*
- [x] 2.1 `! F (internal)` An enum moves whole to the part whose element DaisyUI shows every
  member on (`fef29d6`, `5d2b357`, `4ae9d1b`, `0d4c3d5`, `26a3a06`). **The "regenerates
  nothing" above was written before 3.2a landed and is wrong**: 3.2a gave `daisyIndicatorItem`
  its `<span>`, which was the only thing the rule was waiting for, so both placements moved at
  once. Second finding: the body derived its enum lines from the measured groups while the
  signature derived them from the placement, so the container kept applying a parameter it no
  longer had and the generated Kotlin did not compile — the body now reads the signature, as
  the booleans already did.
  *`:lib:pitest` fails at test strength 99 on a surviving mutant in `daisyModalToggle`,
  verified identical on `d690848`: pre-existing on this branch, not this task's. See 5.3.*
- [x] 2.2 `. d` Done 2026-09-19 as part of the census above. Every
  class that moved has expired its exception, every one that did not is still excused with a
  reason that still holds. `menu-active` and `menu-disabled` sit on an `<a>`/`<li>` the library
  does not generate — 3.1's decision.

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
  **Verified 2026-09-19** in the Kotlin REPL against the real `:lib` classpath, before writing
  any of 3.3–3.5. Every load-bearing claim was a Kotlin language claim and none had been run:
  a member does beat an extension (`daisyJoin { daisyButton() }` reaches the member); a scope
  annotated `@HtmlTagMarker` — which IS a `@DslMarker` — is hidden inside a nested `div { }`,
  so the nested button reaches the extension and is not marked; and `this@daisyJoin.` still
  reaches the member. A fourth fact the plan assumed without saying so also holds: the element
  lands INSIDE the wrapper rather than at join level, because kotlinx.html writes positionally
  into one shared consumer — which is exactly the `div > div > input.join-item` DaisyUI
  documents.
- [x] 3.2a `^ F` A part renders the element DaisyUI shows it on, with the documented parent
  as receiver where FlowContent cannot open it (`0fea0e5`, `373cc35`, `ce97341`, `dc718a2`,
  `2a3021f`). Regenerated: 12 parts changed element, 12 exceptions expired (`86c040e`,
  `38b5172`). Two more rules learnt and pinned: a part shown on several elements keeps the
  heuristic; a part on `<legend>`/`<li>` needs its parent and gets it from the docs.
- [x] 3.2b `. d` **What is left is a decision, not a rule** — done 2026-09-19 (`0b56f3a`,
  `d446c53`, `e7c1ff7`, `52759d2`, `f506789`). Kind 2 became a rule (the cross-check reads the
  SET of documented elements) and it retired far less than predicted — 4 entries, not 36,
  because it only helps a class DaisyUI shows on several elements. Kind 4 was already fixed by
  2.1 and only the observer had not noticed. Kind 1 split three ways on measurement: `status`
  moved to `<div>` (13 gone), `skeleton-text` became `daisySkeletonText` (1 gone), `badge`
  keeps `<span>` as the only element valid in all 54 documented positions (12 permanent, #356).
  Kind 3 stays excused and moves to `a-join-marks-its-items-by-the-call`; `dropdown` is
  permanent too (#357). Exceptions 47 → 28, of which 20 are now decisions rather than debts.
  ORIGINAL TEXT, left standing because the four kinds were a good decomposition even where the
  estimates under them were not: — read 22:09 from the generator's
  47 excused. Four kinds:
  1. `badge` ×12, `status` ×13, `skeleton-text`: the component renders the `<span>` of
     DaisyUI's first example, every modifier example is a `<div>`. Element choice for the
     component itself. Options: keep `<span>` and re-reason the exceptions as permanent; or
     switch to `<div>` (breaking; both are inline-usable).
  2. `dropdown` ×8: `<details>` is the no-JS shape; every placement is shown only on the
     `<div>` shape. The classes work on `<details>` too (same CSS). Re-reason as permanent, or
     read `documentedElementSetsIn` for the component class — `dropdown` IS shown on `<div>` —
     and let the cross-check accept any element the component is shown on.
  3. `dock-active` (on `<button>`), `menu-active` (`<a>`), `menu-disabled` (`<li>`),
     `rating-hidden` (`<input>`), `list-col-grow` (`<div>`), `list-col-wrap` (`<p>`): a class
     on a child the library generates no function for. Same shape as `join-item` → the 3.1
     answer applies: member overloads in the container's scope. Which children? Measured:
     `dock-active` on `btn` only; `menu-active` on `a`/`btn`; `rating-hidden` on `input`;
     `list-col-*` on `div`/`p` — raw tags, no component. Needs a per-case look.
  4. `indicator` ×5: `indicator-item` is shown on `<span>` and `<div>`, so the part keeps the
     heuristic `<div>` and the placements (shown on `<span>`) cannot move. Fix: let a part
     shown on several elements take the element its *classes* are shown on, when that is one.
  Kind 2 and kind 4 are rules (~20 min each). Kinds 1 and 3 are decisions.
- [x] 3.2c **Nothing to do — measured 2026-09-19, the task was already stale when written.**
  `carousel-item` and `megamenu-active` had functions by then, and checking DaisyUI's own `part`
  frontmatter against `lib/api/components.api` across all 68 pages leaves exactly ONE part with
  no function: `pagination/join-item`, and `pagination` is in `skip` as an alias. 3.2a generated
  the rest. A task that dissolves when measured is the cheapest possible outcome, and it would
  have been bought earlier by measuring before listing.
## 5. Migration, baselines, gate — 0.8 h

- [ ] 5.0 `. d` Create the change's issue (§5 of the house rules; this session had no GitHub
  access) and re-point the 58 exceptions from #347 to it.
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
