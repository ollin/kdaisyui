## Estimate

Frozen 2026-10-18 before the first commit, raw, ledger run
`2026-10-18-kdaisyui-test-every-documented-tag`: **215 min**. Block 1 checks 20, block 2 owner by
tag 30, block 3 tests at the tag 60, block 4 builders and `<calendar-date>` 60, block 5 docs and
gate 45. Known ratios for kdaisyui codegen work, not folded in: 0.06, 0.09, 0.12, 0.21, 0.26.

## 1. Check first

- [x] 1.1 `. d` **Simulate** D3's tie-break and D1's tag form over every documented element: how
  many tests switch function, how many take the tag form, and which tags have no builder. The
  proposal expects `<calendar-date>` alone; another tag refutes it and the plan is revised first.
  **Simulated 2026-10-18: it holds.** 24 elements switch function (`daisyTab` to `daisyTabRadio` 19,
  `daisyModal` to `daisyModalPopover` 5); 2,220 then sit on their function's tag, 333 take the tag
  form, 11 are class-only; the only tag without a builder is `<calendar-date>`, twice, on the
  calendar page.

## 2. The documented tag decides between equals

- [x] 2.1 `^ F (internal)` `ownerOf` prefers, among functions offering equally much, the one
  rendering the documented tag (D3); unit test first; regenerated tests switch function where it
  applies.

## 3. Tests at DaisyUI's tag

- [x] 3.1 `^ F (internal)` `documentedCall` reports whether its function renders the documented tag.
  Committed as `^ F`, not a zero-diff refactoring: the call gained two fields a test compares.
- [x] 3.2 `! F` Every documented-element test renders DaisyUI's tag and asserts it (D1, D2), inside
  the parent `li` and `legend` need. Red first: the tag assertion added before the tag form must
  fail on the 357 elements on another tag, observed and recorded.

## 4. Tags without a builder

Done before 3.2, reordered 2026-10-18: 3.2's first generation wrote `calendar-date { }`, which
does not compile, because the tag form needs a builder. With `daisyCalendar` rendering
`<calendar-date>`, the calendar's test calls the function and needs none.

- [x] 4.1 `^ r (internal)` The builder table in `html-names.ts` (D4). Zero diff.
  In `component-shape.ts` instead, beside the existing builder exceptions: `html-names.ts` holds
  types only.
- [x] 4.2 `^ F (internal)` The gate fails on a documented tag the table does not know. Red: it names
  `<calendar-date>` on the calendar page.
- [x] 4.3 `! F` `CALENDAR_DATE` and `calendarDate` in core, in the table, and `daisyCalendar` renders
  it. BREAKING; README migration entry. Closes #343.
  Done in two steps after a revert: the first attempt failed `tagFindings`' own test, which used
  `<calendar-date>` as its tag without a builder. That test moved to a fictional tag; then core's
  `CALENDAR_DATE` with its own Kotlin test; then the model change.

## 5. Documentation and the gate

- [x] 5.1 `. d` Skills (`kdaisyui-codegen`, `kdaisyui-testing`) and the llms template, where they say
  the tag is not compared.
- [ ] 5.2 `. d` The full gate: `./gradlew check` with E2E, `koverVerify`, `:lib:pitest`,
  `:lib:testCodegen`, `:lib:checkComponentApi`, regeneration drift, CodeScene. No mutation tool for
  the TypeScript codegen; say so.
- [ ] 5.3 `. d` Write `tmp/test-every-documented-tag/EVALUATION-test-every-documented-tag.md`.
