# Tasks

Split out of `a-class-belongs-to-the-element-that-wears-it` on 2026-09-19. That change
delivered the rule that a class is declared on the function whose element wears it, for
components and for parts. What is left here is a DIFFERENT mechanism: classes that reach no
function at all, made reachable by a DSL scope rather than by moving a parameter.

Its foundation was checked before the split — see `specs/`. Every load-bearing claim about
Kotlin's resolution rules was Assumed until 2026-09-19 and is now Verified in the REPL, and the
member list was re-measured and was wrong (seven components, not five).

## 1. The join scope — 1.5 h

- [ ] 1.1 `^ F (internal)` The generator derives the join-child list: every component class that
  co-occurs with `join-item` on one element in DaisyUI's markup. Pin the measured set with a
  test; a further component appearing in DaisyUI's docs must extend it without a config edit.
  **Measured 2026-09-19 at v5.7.17 and it is SEVEN, not the five named in 3.1**: `btn` 60,
  `input` 5, `theme-controller` 5, `collapse` 3, `card` 3, `select` 1, `validator` 1. The two
  the plan missed are real components in this library, and `btn`'s count is 60 rather than 52 —
  so 3.1's list was written from a narrower reading or an older tag. Nothing about the design
  changes; the point of deriving the list is precisely that a stale hand-count cannot reach the
  output. The test pins seven and will fail on the next DaisyUI bump that moves it, which is
  the intent.
- [ ] 1.2 `^ F` Generate `JoinScope` and its member overloads; `daisyJoin`'s `content` becomes
  `JoinScope.() -> Unit`. Test: `daisyJoin { daisyButton("A") }` renders `class="btn join-item"`;
  `daisyJoin { div { daisyInput() } }` renders no `join-item`; `daisyJoin { div {
  this@daisyJoin.daisyInput() } }` does; a `daisyButton("A", extraClasses = "join-item")` outside
  a join is unchanged (still reachable, still a string).
- [ ] 1.3 `. d` Reference page for `join` shows the three shapes: plain child, wrapper with
  `this@daisyJoin.`, and why a nested button is not marked.

## 2. The classes that reach no function — inherited

- [ ] 2.1 `. d` The six exceptions inherited from the parent change — `dock-active` (on a
  `<button>`), `menu-active` (`<a>`), `menu-disabled` (`<li>`), `rating-hidden` (`<input>`),
  `list-col-grow` (`<div>`), `list-col-wrap` (`<p>`) — are the same shape as `join-item`: a
  class on a child the library generates no function for. Decide per case whether the scope
  answer applies, and re-point each exception's reason and condition at this change.

## 3. Wrap-up

- [ ] 3.1 `. d` **How to migrate**: `daisyJoin`'s `content` changes receiver type.
- [ ] 3.2 `. d` Re-dump both baselines and read each diff.
- [ ] 3.3 Full green per `openspec/config.yaml`, plus `:lib:pitest`.
- [ ] 3.4 `. d` Write the evaluation under `tmp/`.
