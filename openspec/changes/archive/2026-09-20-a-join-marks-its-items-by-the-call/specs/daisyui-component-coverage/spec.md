## ADDED Requirements

### Requirement: A join marks its items by the call, not by a parameter

Inside the content of `daisyJoin`, calling a component function that DaisyUI documents as a join
item SHALL emit that component with `join-item`, with nothing further to write. Outside
`daisyJoin`, and inside any lambda nested within a join's content, the same call SHALL emit no
`join-item`, and there SHALL be no typed way to write `join-item` there. The set of component
functions that behave this way SHALL be derived from DaisyUI's markup — every component class
that co-occurs with `join-item` on one element — and not configured.

**Verified** what `join-item` does, from `packages/daisyui/src/utilities/join.css`: `.join`
writes four corner-radius variables onto its direct children and `.join-item` reads them and
collapses shared borders. Without an enclosing `.join` the variables are unset and the class
strips the element's own corners — so `join-item` outside a join is harmful, not inert, and a
parameter writable anywhere would let a caller do that silently.

**Verified** the derived set and its completeness, **re-measured 2026-09-19 at v5.7.17, and the
first reading was wrong**. The component classes co-occurring with `join-item` are `btn` (60),
`input` (5), `theme-controller` (5), `collapse` (3), `card` (3), `select` (1), `validator` (1) —
**seven**, not the five read on 2026-09-15, which missed `theme-controller` and `validator` and
counted `btn` at 52. Both are components this library generates, so the earlier list would have
shipped a scope short of two of its members, with a test pinning the same wrong number.

This is the argument for deriving the set rather than configuring it, arriving before the code:
a hand-read list cannot reach the output, and the test pins what DaisyUI says today.

Of 72 direct children of a `.join`, 69 carry `join-item`; the 3 that do not are wrappers whose
grandchild carries it — two bare `<div>`s and one `<div class="indicator">`. (The 2026-09-15
reading said 74 and 71; the count moved, the shape did not.) A rule marking every DOM child would
therefore be wrong in 3 of 72 cases; a rule marking every component call in the join's own scope,
and nothing nested, is wrong in none.

**Verified 2026-09-19 in the Kotlin REPL against the real `:lib` classpath** — previously Assumed
from Kotlin's documented resolution rules, and the assumption was load-bearing enough that none
of 3.3–3.5 should have been written on it unrun. A member function on the scope type does take
precedence over the top-level extension; `@HtmlTagMarker`, which is a `@DslMarker`, does hide the
scope inside a nested lambda, so the nested call reaches the extension and is not marked; and
`this@daisyJoin.` still reaches the member. A fourth fact this requirement leaned on without
stating it also holds: the element lands inside the wrapper rather than at join level, because
kotlinx.html writes positionally into one shared consumer — which is the `div > div >
input.join-item` DaisyUI documents.

*Wrong if:* a nested `daisyButton` inside a join's content emits `join-item`, or `daisyButton`
inside a join resolves ambiguously; the test pinning both is the correction.

*Wrong if:* DaisyUI documents `join-item` on an element inside a join whose component is not in
the derived set and that a caller cannot reach with `extraClasses`. The known limit — an element
DaisyUI does not document as a join item stays on `extraClasses` — is accepted, not hidden.

#### Scenario: A component call inside a join is a join item

- **WHEN** `daisyButton("A")` is called directly in the content of `daisyJoin`
- **THEN** the rendered button carries `btn join-item`

#### Scenario: A nested call is not a join item

- **WHEN** `daisyInput()` is called inside a `div { }` nested in the content of `daisyJoin`
- **THEN** the rendered input carries no `join-item`
- **AND** `this@daisyJoin.daisyInput()` at the same place renders it with `join-item`

#### Scenario: A join item cannot be written outside a join

- **WHEN** code outside `daisyJoin` tries to obtain a join-marking component call
- **THEN** it does not compile

#### Scenario: The item set follows DaisyUI

- **WHEN** a DaisyUI release documents `join-item` on a component it did not before
- **THEN** that component's function marks itself inside a join after regeneration, with no
  configuration edit
