## Context

See `proposal.md` for why. Today `codegen/src/test-generator.ts` turns each `+page.md` example into
calls of the page's **main** function only (`componentCallsIn` keeps elements carrying the
component's own class), binds classes to parameters through `classBindings`, which flattens the
parameters of **all** of a model's functions into one map, and asserts the sorted class list. The
model gates live in `codegen/src/model-completeness.ts` and run from `codegen/src/index-new.ts`.

Constraints that shape the approach:

- Codegen unit tests never read the submodule (`kdaisyui-testing`), so the classification must be
  a pure function over models and markup, testable from fixtures.
- Node runs the TypeScript directly: erasable syntax only.
- One function renders one element; the gate compares classes only (proposal, out of scope).

## Goals / Non-Goals

**Goals:** one classification of every marked element (expressed by which function with which
arguments, or not), used by both the generation gate and the test generator, so the two cannot
disagree.

**Non-Goals:** choosing the element per call site; generating tests for declared gaps; changing
any public Kotlin signature.

## Decisions

### D1. One pure classifier, two callers

A new module `codegen/src/documented-elements.ts` takes the models and one page's example markup
and returns, per marked element, either the call expressing it (function, typed arguments,
counted classes) or the finding. `index-new.ts` turns findings into generation failures beside
the completeness gate; `test-generator.ts` turns calls into tests.

*Rejected:* the gate inside the test generator only. `generateComponents` would then succeed on a
model the tests refuse, and the failure would surface one Gradle task later, under a name that
says nothing about the model.

*Rejected:* extending `componentCallsIn` in place. It is built around one function per page;
every function across every model is a different shape of input.

### D2. A gap is declared per page, by its key

```json
"notExpressed": {
  "dropdown-content menu": "Two components on one element; one call renders one function. Write the ul with daisyDropdownContent(extraClasses = \"menu\")."
}
```

The key is the own classes of the functions involved when two components share the element,
otherwise the element's counted classes, sorted and space-separated (spec). It lives in the model
file of the **page** whose example shows the element, because staleness is judged per page.

*Rejected:* the whole class set as the key. Counted 2026-10-18 (task 1.1): 65 keys instead of 39,
27 of them the indicator's placements, and every new DaisyUI modifier would add declarations for
a gap that did not change. Oliver chose the own-class key.

*Rejected:* counting two components on one element as expressible through `extraClasses`. Twelve
keys would remain, but the gate would accept any class of another component passed as a string,
so it would no longer say what the typed API can express. Oliver chose the declared key over it.

*Rejected:* a key per class, like `notOffered`. `menu` is expressible on its own; only the
combination is not, and a per-class key would excuse every `menu` element on the page.

*Rejected:* a key per example (heading and position). An unrelated edit to DaisyUI's page would
move it, and a bump would report stale declarations that are not stale.

### D3. Which function expresses an element

Candidates are every function, in any model, whose own class is among the counted classes. The
first that offers all the others wins, the page's own model first, then the others in directory
order, each in model-file order. Deterministic, so regeneration stays byte-identical.

*Rejected:* failing when two candidates both express an element. `modal` is the own class of two
constructions; either call is correct, and the test needs only one.

### D4. Bindings per function

`classBindings` becomes per function: a class binds to a parameter of the function under test,
not of any function in the model. Today the flattening is harmless because only the main function
is called; with parts it would hand `daisyCardBody` a parameter of `daisyCard`. This is an enabler,
provable by zero diff while only main functions are called.

### D5. Tests per page, wrapped by receiver

The tests for one page stay in that page's `<Component>Test.kt`, so a test names where DaisyUI
shows it. A part whose receiver is not `FlowContent` is called inside the wrapping builder its
receiver needs, as the custom-part tests already do (`wrapperTagOf`). Identical calls on one page
yield one test.

### D6. The old path goes

The main-only example tests are a subset of the new ones. `componentCallsIn`, the per-page call
model and their stale comments (one still says it calls `classifyGroups` "with the same
measurement", deleted in 0.8.0) are removed once the new tests replace them.

## Enablers and desired changes

| Enabler, zero diff | Desired change |
|---|---|
| D4 bindings per function | the classifier and its gate (D1, D2, D3) |
| the classifier returning today's main-function calls | every expressible element as a test (D5) |
| | the declarations, one model edit per gap |
| | D6, deleting the old path |

## Risks / Trade-offs

- [The declaration list is long and nobody reads it] → counted first (task 1.1): 39 keys.
- [`:lib:test` grows by about a thousand tests and slows] → measured at the gate; the generated
  tests are single-call renders, today 1,679 tests run in seconds.
- [A test name collides across many elements of one page] → `uniqueTestName`, as today.

## Migration Plan

None for library users: no public signature changes, `lib/api/*.api` stay unchanged. Rollback is
reverting the merge.
