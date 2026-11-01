## Context

`coverageTestFor(className, kotlinSource)` in `codegen/src/test-generator.ts` builds one
`<Name>CoverageTest.kt` per component. `writeCoverageTest` reads the source from
`lib/generated/main/**/components/<Name>.kt`, and `index-test-generator.ts` calls it per file. The
test is assembled from a per-function context (`coverageContext`) whose every field is parsed out
of that source text. See proposal.md, Why.

`coverage-test-snapshot.test.ts` already compares `coverageTestFor`'s output with all 66 committed
coverage tests, byte for byte. That is the safety net for every step of this change. Each step
must keep it green, and `just generate` must leave no diff.

## Goals / Non-Goals

**Goals:**

- Every input of the coverage tests comes from `ComponentShape` and `MainFunctionConfig`.
- The textual Kotlin parser and its regexes are deleted.

**Non-Goals:**

- No change to what the coverage tests assert or how they read. A better test is a separate
  change, because byte-identity is what makes this one checkable.
- No model change (D3).

## Decisions

### D1. What each parsed field becomes

| Parsed today | From the shape | Status |
|---|---|---|
| `parseEnumDefinitions`: enum name, entries and class | `shape.enums[].name`, `entries[].name`, `entries[].cssClass` | Assumed: the entries' order and classes match the emitted enum exactly. **Wrong if** a task 1.1 comparison over all 66 components differs. |
| `findFunctions`: each `fun R.daisyX(...)`, in file order, siblings included | for each of `shape.functions`, the function, then its class sibling where `hasSibling`; then `shape.siblingsOnly` | Assumed: the order matches `generateKotlinFile`'s, which is the line `functions.map(fn => [renderFunction, renderSibling])` followed by `siblingsOnly`. **Wrong if** the 1.1 comparison of names differs. |
| `splitParams` + `classifyParam`: name, type, default per parameter | `FunctionShape.parameters`, or `siblingParameters(fn)` for a sibling; type and default are already strings there | Assumed: the shape's `type` string equals the emitted one, e.g. `ClassValues<ButtonSize>?`. **Wrong if** 1.1 differs. |
| `parseBaseClass`: unguarded `addClassNames("…")` | main: `shape.prefix`; part, custom and sibling: `cssClass` and `modifierClasses` | Assumed: read from `generator-new.ts` lines 231, 278 and 294-295. **Wrong if** 1.2 finds a function whose sorted classes differ. |
| `boolClassesFor`: classes a `Boolean` parameter adds | the toggle's `cssClass`, or the extra's `writes` | Assumed. **Wrong if** 1.2 differs. |
| `parseEmittedBuilder` / `parseScopeBuilder`: the builder | `fn.tagBuilder` for a function; none for a sibling | Assumed: a scoped function's element is its own `element`, so its `tagBuilder` is what the scope's superclass resolves to. **Wrong if** 1.2 differs for `Join`. |
| `parseScopeMembers`: name, whether content is required, opener | `shape.scope.members[]`; required = a `content` parameter whose `default` is null; opener = `shape.functions[0].name` | Assumed. **Wrong if** 1.2 differs. |
| `parseAttrProps`: attributes the body sets, guarded or not | unconditional: `role` and `type` from `MainFunctionConfig`; guarded or not: the extras' `apply` fragments (D3) | Assumed: no other line of a body assigns an attribute. **Wrong if** 1.3 differs. |
| receiver | `fn.receiver` | Verified: it is the same string the emitter writes, `generator-new.ts` `renderFunction`. |

### D2. One context builder, switched field by field

`coverageContext` keeps its shape. Its fields are rebuilt from the shape one at a time, each a
separate commit with the snapshot test green. Then the entry point changes, then the parser goes.

- Rejected: write a new shape-based generator beside the old one and switch in one commit. That
  is one large step, and a snapshot failure would not say which field broke.
- Rejected: change the entry point first. Then every field would have to be correct in the same
  step.

### D3. The attributes an extra sets: parse its fragment, not the file

The 19 extras in the committed models each set one attribute, in free-form Kotlin
(`if (disabled) this.disabled = true`). The shape does not have that as data. The coverage tests
keep `ATTR_ASSIGN`, but apply it to the extras' `apply` fragments, not to the whole function body.

- Rejected: declare the attribute in the model (`"sets": "disabled"`). It is the cleaner end state,
  but it is a model change with its own completeness gate, and it does not belong to a change that
  must leave every output byte-identical.
- Rejected: assume an extra sets the attribute of its own name. It holds for all 19 today, but an
  undeclared rule that holds by coincidence is what this change removes.

### Enablers and desired change

Tasks 2.1-2.6 are enablers: each keeps the output byte-identical, which the snapshot test proves.
The desired change is task 3.1, deleting the parser. There is no second desired change.

## Risks / Trade-offs

- [Risk] A field matches the snapshot only by coincidence, for example two functions in the same
  order for a different reason. → Mitigation: the 1.x comparison runs per field across all 66
  components, not only on the final output. The mutation gate on the changed lines must reach
  100 %.
- [Risk] `parseAttrProps` stays as a small parser, with its survivors. → Accepted. It is three
  regex mutants on a fragment the model owns. D3 names the change that would remove it.
- [Trade-off] `coverageTestFor` stops being callable on an arbitrary `.kt` file. Nothing calls it
  that way except its own snapshot test, which moves to the component set.
