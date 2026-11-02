## Context

The parameter frame every generated function shares is built in one place,
`escapeHatchParameters` in `codegen/src/component-shape.ts`: `text` where the model says so, `id`,
the function's own parameters, `extraClasses`, `attrs`, and `content` unless the element is void.
`content` is required, except where `text` exists, which makes it optional and lets it win.

Two emitters render the body, `mainFunctionBody` and `secondaryFunctionBody` in
`codegen/src/generator-new.ts`, both ending in `addClassNames(extraClasses)`, `attrs()` and
`contentLines`. The join scope (`codegen/src/join-scope.ts`) rewrites only the receiver of
`content`. Three generators read the frame back: the coverage tests (`test-generator.ts`, through
`ATTRS` and `CONTENT` constants and `content = { }` for required lambdas), the documented-element
tests (`documented-elements.ts`, which passes other components' siblings as `attrs = { … }`), and
the reference pages and `llms-full.txt`.

## Goals / Non-Goals

**Goals:**

- One lambda per function, one name for it in every function, one rule for what it receives.
- No branch in a generated body for the lambda, so neither Kover nor the generated tests have to
  drive one.

**Non-Goals:**

- Inline functions. kotlinx.html's builders are `inline` with a `crossinline` block; ours stay
  ordinary functions, because inlining 138 bodies into every call site changes binary size and
  the ABI dump for a reason unrelated to this change.
- Any change to what a function writes besides the lambda.

## Decisions

### 1. The name is `block`

Decided by Oliver on 2026-11-02 after comparing call sites. `block` is what kotlinx.html calls the
same parameter, it is correct on a void element, and it claims neither "attributes" nor
"children", both of which it does.

*Rejected:* `content`, which reads well on containers but is wrong on `<input>` and invites an
agent to put children there.

### 2. `block` defaults to `{}`, never nullable, never required

One shape everywhere, as kotlinx.html has it. The body calls `block()` unconditionally, which
removes the `if (attrs != null)` branch from every function and the required-lambda case from the
test generator.

*Rejected:* keeping the lambda required on containers. It guarded against `daisyCard()` with no
body, but `daisyCard {}` compiles anyway, so the guard bought nothing and cost a second shape.
*Rejected:* a nullable lambda, which keeps a branch per function that every coverage and mutation
gate has to drive to establish nothing.

### 3. The block runs before the text

Forced by kotlinx.html: once text is written, the tag's start has gone downstream and an attribute
change throws (see the spec's requirement "A function's text and block add up"). So the body is
`block()` then `if (text != null) +text`. A caller who wants the text before a child writes
`+"Save"` inside the block instead of passing `text`.

*Rejected:* text first, which makes the most common combination, text plus an attribute, throw at
render time. *Rejected:* forbidding the combination with two overloads, which doubles the 12 text
functions and their join members to protect an order nobody has asked for yet.

### 4. Enablers before the behaviour change

Every commit has to be green under TCR at the repository root, which compiles every consumer. So
the consumers move ahead of the API wherever today's API already allows the target form:

- **Enablers, no output change:** hand-written calls pass `content` as a trailing lambda, and
  `attrs` moves into that lambda wherever the function has no `text` and its element is not void,
  since `attrs` ran directly before `content` anyway. The test generator and the documented-element
  generator read the lambda names from the shape. Each is provable by unchanged output.
- **Desired change 1:** `text` and `content` add up. Small, and its test is the rewritten
  precedence test.
- **Desired change 2:** the frame drops `attrs`, renames `content` to `block` with a `{}` default,
  and the bodies call it unconditionally. Only the calls the enablers could not move, on void
  elements and on `text` functions, move in the same commit.

*Rejected:* one commit for the whole change. It would be green, but it mixes two behaviour changes,
which no risk marker can describe.

### 5. The documented-element tests put siblings in the block

`daisyDropdownContent { daisyMenuClasses() }` instead of `attrs = { … }`. Where the call also needs
a lambda for nothing else, the sibling calls are the whole block.

## Risks / Trade-offs

- [A trailing lambda that used to drop `text` now renders it] → It is the behaviour the precedence
  test documented as a trap. Task 1 counts the call sites that combine both, and the upgrading
  entry names the change first, as 0.8.0 did with the tab rename.
- [Every consumer call naming `attrs =` or `content =` stops compiling] → A compile error at the
  call site, with a one-line migration. Per the project's stance, API stability ranks below
  correctness.
- [Attributes written in the block after a child throw at render time] → The same rule holds for
  every kotlinx.html builder. The how-to section on attributes says to write them first.
- [The ABI baseline changes on all 138 functions] → Expected. `just update-api` is run once, after
  regeneration, and both diffs are read.

## Migration Plan

Release as 0.9.0, the plugin with it. `docs/upgrading.md` gets "How to migrate from 0.8.x":
`attrs = { … }` and `content = { … }` both become the trailing block; a call that passed both
merges them, attributes first; a call that passed `text` with a trailing lambda now renders the
text too. Rollback is reverting the merge.
