# Coverage baseline (tasks 2.1 + 2.2)

Captured from the aggregated Kover XML report (`build/reports/kover/report.xml`) at
the start of the test-writing work, **before** any new tests were added (Kover wired,
reports only, no gate). Reproduce with `./gradlew koverHtmlReport koverXmlReport`.

## 2.1 — Aggregated baseline (`:lib` + `:ktor-integration`)

| Metric | Covered | Missed | Total | Coverage |
|--------|--------:|-------:|------:|---------:|
| LINE   | 1268 | 8529 | 9797 | **12.94%** |
| BRANCH | 547  | 4279 | 4826 | **11.33%** |

(Reference counters: INSTRUCTION 20.16%, METHOD 12.67%, CLASS 10.66%.)

## 2.2 — Uncovered surface, grouped by package

| Package | LINE cov/total | LINE % | BRANCH cov/total | BRANCH % | Share of missed |
|---------|---------------:|-------:|-----------------:|---------:|-----------------|
| `…/icons`      | 0/7774   | 0.0%  | 0/3834   | 0.0%  | **~79% of missed lines, ~89% of missed branches** |
| `…/components` | 1218/1956 | 62.3% | 521/946 | 55.1% | already partly covered by codegen-generated tests |
| `…/core`       | 46/52    | 88.5% | 26/34    | 76.5% | small gap (ClassNames + TagId) |
| `…/ktor`       | 4/15     | 26.7% | 0/12     | 0.0%  | small (Resolvable) |

### Key findings shaping the work

- **The `icons` package dominates everything.** 324 generated heroicon functions, each
  with two parallel `when (variant) { Outline -> …; Solid -> when (size) { Sm/Md/Lg } }`
  blocks plus an `extraClasses != null` guard. Reaching **100% branch** means each icon
  must be rendered along Outline + Solid×{Sm,Md,Lg} + the extraClasses path. That is
  ~324 × ~5 paths — only feasible as a **generated** test (task 3.4), not hand-written.
  The codegen already ships `codegen/src/test-generator.js`; the icon test must be driven
  from there, mirroring how component tests are generated.
- **Components are already ~62/55%** because `generateComponentTests` emits a test per
  component. Batches 4–11 close the residual component branches (variant/size/boolean
  modifier paths the generated tests don't yet exercise), rather than starting from zero.
- **`core` and `ktor` are small, hand-written targets** (tasks 3.1, 3.2, 12.1).

### Batch-ordering confirmation (task 2.2)

The tasks.md batch ordering (core+icons first, then components A–H, then ktor) matches
reality: the icons block is correctly front-loaded as the largest single lever, and the
component batches are the long mechanical tail. No reordering needed.
