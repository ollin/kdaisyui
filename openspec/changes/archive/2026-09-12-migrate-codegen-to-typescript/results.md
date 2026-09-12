# Results

Task 3.2. Measured 2026-09-11 on the implementation branch.

## The acceptance criterion held

**`lib/generated/**` is byte-identical.** All four generators re-run from the fully ported
pipeline, `git status` clean afterwards — 66 components, 66 coverage tests, 324 icons and the
icon coverage test, **390 files**.

Checked after *every* task, not once at the end. That is what made each step a port rather
than a hopeful edit.

## The full gate

| Check | Result |
|---|---|
| `gradle check --warning-mode all` | BUILD SUCCESSFUL, **`Problems: 0`**, e2e included |
| `koverVerify` | 100% line and branch, aggregated |
| `checkKotlinAbi` | unchanged — no public API moved |
| `:lib:pitest` | **100% test strength**, gate intact |
| `:lib:testCodegen` | **32 tests**, ~85 ms |

## What the port cost

Nothing at runtime. No build step, no emitted JavaScript, and `codegen/package.json` still
declares **zero dependencies** — so `just generate` remains the only thing in the repository
that needs Node.

## What it actually bought

Not what the proposal claimed. It was argued from `closesTagAssert` receiving a kotlinx.html
builder name where an HTML tag name was meant; both are `string`, and **plain annotations
cannot catch that** (corrected in task 1.3). What paid instead:

**1. Shapes that had already drifted.** Every parser carried a JSDoc `@typedef` that nothing
checked, and three no longer matched the code:

- `ClassifiedComponent` documented **13** fields; the function returns **14**. `componentClass`
  had been returned all along, undocumented.
- `generateKotlinFile`'s `elementRules` parameter was neither plural nor a rule — the one
  caller passes `{ primaryElement }`, and that is all it reads.
- `parseIconFiles`' return shape lived only in prose, though its nullability *is* the branch
  table the generator consults.

**2. A union that closes a silent hole.** `ClassCategory = keyof Classnames`.
`MODIFIER_CATEGORIES` is a hand-written list indexed into parsed YAML; a typo returned
`undefined`, was swallowed by `?? []`, and silently dropped every modifier in that category.
Now unwritable.

**3. Branded names where two string KINDS coexist** — `TagName`/`BuilderName`,
`KebabName`/`PascalName`, `ComponentName`/`PascalComponentName`. Each costs one cast at the
point of creation. `html-names.ts` holds the shared vocabulary because brands are **nominal**:
two `TagName` declarations would be two incompatible types, worse than none.

Branding also forced a real simplification: four call sites had inlined the same
`receiver === 'FlowContent' ? 'div' : receiver.toLowerCase()`, and each would have needed its
own cast. Naming it `wrapperTagOf` cost one cast and deleted four copies of a conditional —
duplication that was invisible until a type made someone look.

## Code Health

`parseYamlFrontmatter` was refactored under characterization tests (task 2.6): **7.24 → 9.38**,
five categories fixed — Complex Method (cc 32 and cc 10), Complex Conditional, Bumpy Road
(8 bumps), Deep Nested Complexity (depth 5), Overall Complexity 6.09 → 3.04.

## The change-set gate FAILS against main, and the reason is instructive

`analyze_change_set` reports `quality_gates: failed` on one file,
`codegen/src/parser/frontmatter.ts`, for **Primitive Obsession** and **String Heavy Function
Arguments** at 51.4%, each with `value-before: 0`.

**A `value-before` of zero does not mean the code was clean. It means it was unmeasurable.**
On `main` the file is `.js` with no annotations, so CodeScene cannot see that the arguments
are strings and scores the metric at zero. Writing the types made an existing property
*visible*; it did not create it.

Note the two scans disagree, and both are right about different things:

| Comparison | Verdict |
|---|---|
| `.ts` before → `.ts` after (pre-commit safeguard, task 2.6) | **improved**, five categories fixed |
| `.js` on main → `.ts` here (change-set) | **degraded**, two categories introduced |

The second half of the ratio is real and declined deliberately: the module went from 11
functions to **24**, because 2.6 decomposed a cc-32 function. Decomposition necessarily
multiplies primitive parameters, so Primitive Obsession pulls *directly against* Complex Method
and Bumpy Road. Both cannot be satisfied. The finding that improved is the one that was
hurting a reader; the arguments left are `trimmed`, `value` and `key` — text in a text parser,
with no kinds left to separate once `line`-versus-`trimmed` was removed by giving `scanLine` a
single parameter.

Branding them to move a percentage would be metric-chasing, and this change has already
established where branding pays and where it does not.
