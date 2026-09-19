# Tasks

Split out of `a-class-belongs-to-the-element-that-wears-it` on 2026-09-19. That change
delivered the rule that a class is declared on the function whose element wears it, for
components and for parts. What is left here is a DIFFERENT mechanism: classes that reach no
function at all, made reachable by a DSL scope rather than by moving a parameter.

Its foundation was checked before the split — see `specs/`. Every load-bearing claim about
Kotlin's resolution rules was Assumed until 2026-09-19 and is now Verified in the REPL, and the
member list was re-measured and was wrong (seven components, not five).

## 1. The join scope — 1.5 h

- [x] 1.1 `^ F (internal)` The generator derives the join-child list: every component class that
  co-occurs with `join-item` on one element in DaisyUI's markup. Pin the measured set with a
  test; a further component appearing in DaisyUI's docs must extend it without a config edit.
  **Measured 2026-09-19 at v5.7.17 and it is SEVEN, not the five named in 3.1**: `btn` 60,
  `input` 5, `theme-controller` 5, `collapse` 3, `card` 3, `select` 1, `validator` 1. The two
  the plan missed are real components in this library, and `btn`'s count is 60 rather than 52 —
  so 3.1's list was written from a narrower reading or an older tag. Nothing about the design
  changes; the point of deriving the list is precisely that a stale hand-count cannot reach the
  output. The test pins seven and will fail on the next DaisyUI bump that moves it, which is
  the intent.
- [x] 1.2 `^ F` Generate `JoinScope` and its member overloads; `daisyJoin`'s `content` becomes
  `JoinScope.() -> Unit`. Test: `daisyJoin { daisyButton("A") }` renders `class="btn join-item"`;
  `daisyJoin { div { daisyInput() } }` renders no `join-item`; `daisyJoin { div {
  this@daisyJoin.daisyInput() } }` does; a `daisyButton("A", extraClasses = "join-item")` outside
  a join is unchanged (still reachable, still a string).
- [ ] 1.3 `. d` Reference page for `join` shows the three shapes: plain child, wrapper with
  `this@daisyJoin.`, and why a nested button is not marked.

## 2. The classes that reach no function — inherited

**The claim this block was written on is refuted. Measured 2026-09-19 at v5.7.17, before any
code, and it is false for all six.** The block as written said the six are "the same shape as
`join-item`" and asked only *whether* the scope answer applies. It does not, and the reason is
one number:

| | sightings across all 66 component pages | of those, wearing a sibling daisyUI class |
|---|---|---|
| `join-item` | 72 | **72** |
| the six together | 18 | **0** |

The scope answer needs a member to overload, and `join-item` has one every time — it is always
beside `btn`, `input`, `select`, `collapse`, `card` or `theme-controller`, so a generated
function already renders that element. The six sit on bare tags carrying only Tailwind
utilities: `<button>`, `<a>`, `<li>`, `<input>`, `<div>`, `<p>`. Confirmed against the
generated Kotlin — `List.kt` and `Rating.kt` generate one function each, and neither `Menu.kt`
nor `Dock.kt` generates the element in question. There is no member, so there is nothing to
overload and no scope to put it on.

The repository already knew this: **issue #347, point 3** says `menu-active` / `menu-disabled`
"belong on a menu *item*, which the library does not generate at all", and calls it closer to
#342 than to the parameter moves. The plan contradicted an open issue in its own tracker.

One further finding, which makes `menu-active` worse than the others rather than the same:
**it has no element by design.** DaisyUI's `desc` reads "For the element inside `<li>` to look
active", and the page shows it on a `<button>` in the rendered preview and on an `<a>` in the
`$$`-marked example below. The generator reads only the marked block, so it sees one `<a>`.

What remains is therefore real but different: the six exceptions point at "Block 2 of
`a-class-belongs-to-the-element-that-wears-it`", a change that is now **archived**, so every
one of those reasons names a block no reader can act on.

- [ ] 2.1 `. d` Re-point the six exceptions at issue #347, which is the problem they actually
  belong to, and state the measured reason — a class DaisyUI documents on a bare child tag the
  library generates no function for, which is why no caller can reach it and why the join scope
  cannot help. Do **not** claim this change addresses them.

## 3. Wrap-up

- [ ] 3.1 `. d` **How to migrate**: `daisyJoin`'s `content` changes receiver type.
- [ ] 3.2 `. d` Re-dump both baselines and read each diff.
- [ ] 3.3 Full green per `openspec/config.yaml`, plus `:lib:pitest`.
- [ ] 3.4 `. d` Write the evaluation under `tmp/`.
