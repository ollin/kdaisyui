# Baseline mutation report

> **After section 3** (2026-09-09): **220 mutants, 198 killed (90%), 16 survived, 6 uncovered**,
> test strength 93%.
>
> | | Baseline | After section 3 |
> |---|---|---|
> | Mutants | 223 | 220 |
> | Killed | 197 | 198 |
> | Survived | 18 | **16** |
> | No coverage | 8 | **6** |
>
> The core-logic scope now has **zero** survivors. Three of the five causes below are closed:
> D (`ClassNames` conditional boundary) turned out to be an equivalent mutant and was removed
> with the redundant code that produced it; E (`AnnotatedIdBase::hashCode`) got the assertion
> it lacked; and 2 of the 8 uncovered — `getParent`, `getName` — were reachable after all and
> are now tested.
>
> Everything remaining is either a component (groups A, B, C → section 4) or one of the 6
> compiler-emitted bridges (→ 5.1, as exclusions). The mutant counts changed, so the line
> numbers below are the baseline's, not the current file's.

---


Task 2.3. Report-only run, no `mutationThreshold` set — the build cannot fail on these
numbers yet. Reproduce with `./gradlew :lib:pitest`; the machine-readable source is
`lib/build/reports/pitest/mutations.xml`.

## Scope and score

| | |
|---|---|
| Scope | `io.github.ollin.kdaisyui.core.*` + `ButtonKt`, `ModalKt`, `DropdownKt`, `TooltipKt`, `RangeKt` |
| Test filter | `io.github.ollin.kdaisyui.*` (2389 examined, 1514 run) |
| Mutants generated | 223 |
| Killed | 197 (88%) |
| **Survived** | **18** |
| **No coverage** | **8** |
| Test strength | 92% |
| Line coverage of mutated classes | 317/319 (99%) |
| Wall clock | 12s |

Twelve seconds is the number that decides task 7.1: this is affordable as a CI job.

## The headline finding

Every one of these 26 mutants lives in code the `coverage-enforcement` capability already
gates at **100% line and branch**. That is the change's premise, and it is now measured
rather than argued: 26 mutations survive tests that execute every line and every branch of
the code they mutate.

## Survivors, by cause (18)

### A. Removed `TagConsumer::onTagEnd` — 11

| Class | Method | Line |
|---|---|---|
| `ModalKt` | `daisyModal` | 144 |
| `ModalKt` | `daisyModalBox` | 155 |
| `ModalKt` | `daisyModalAction` | 166 |
| `ModalKt` | `daisyModalBackdrop` | 177 |
| `ModalKt` | `daisyModalToggle` | 188 |
| `ModalKt` | `daisyModalPopover` | 199 |
| `DropdownKt` | `daisyDropdown` | 91 |
| `DropdownKt` | `daisyDropdownContent` | 102 |
| `TooltipKt` | `daisyTooltip` | 105 |
| `TooltipKt` | `daisyTooltipContent` | 116 |
| `RangeKt` | `daisyRange` | 101 |

Deleting `onTagEnd` stops the closing tag being emitted. The tests do not notice, which
says they assert on substrings of the rendered output rather than on well-formed markup —
`contains("modal-box")` is equally true of `<div class="modal-box">` and of the same
element left hanging open.

The largest single group, and one fix likely closes all eleven: assert the exact rendered
string for one component per file rather than a fragment of it.

### B. Removed an attribute setter — 4

| Class | Method | Line | Removed call |
|---|---|---|---|
| `ButtonKt` | `daisyButton` | 107 | `BUTTON::setDisabled` |
| `ButtonKt` | `daisyButton` | 108 | `BUTTON::setType` |
| `RangeKt` | `daisyRange` | 78 | `INPUT::setType` |
| `RangeKt` | `daisyRange` | 87 | `INPUT::setDisabled` |

`disabled` and `type` are set and never asserted. A generator that stopped emitting
`type="range"` would ship green. Note these are *attributes*, not classes — the test
generator asserts class strings, so the gap is structural rather than an oversight in one
test.

### C. Negated conditional — 1

`RangeKt.daisyRange` line 87 — the `disabled` branch. Same root cause as B: the attribute
the branch guards is never asserted, so inverting the branch changes nothing observable.

### D. Changed conditional boundary — 1

`ClassNamesKt.addClassNames(Tag, String?)` line 35 — the `filter { it.isNotEmpty() }` in
the nullable overload. Survives 134 executing tests.

### E. Replaced int return with 0 — 1

`AnnotatedIdBase::hashCode` line 71. No test asserts a hash value, so a constant-0
`hashCode` — legal, and quadratic in a `HashMap` — passes.

## No coverage (8) — all in `TagId.kt`

These ran against **zero** tests. They divide into two kinds, and only the first is
already known to the build:

**Compiler-emitted bridges (6).** `target` / `targetGlobal` are interface defaults on
`HtmlId`; Kotlin emits a bridge into every implementor plus a `$DefaultImpls` holder.

| Class | Method | Line |
|---|---|---|
| `HtmlId$DefaultImpls` | `getTarget` | 16 |
| `HtmlId$DefaultImpls` | `getTargetGlobal` | 19 |
| `AnnotatedIdBase` | `getTarget` | 50 |
| `AnnotatedIdBase` | `getTargetGlobal` | 50 |
| `StringHtmlId` | `getTarget` | 25 |
| `StringHtmlId` | `getTargetGlobal` | 25 |

The root `build.gradle.kts` already excludes `*$DefaultImpls` from Kover, with a written
justification that they are unreachable from any source-level test. That reasoning covers
two of these six. **The other four are the same bridges emitted into the implementing
classes**, which that exclusion does not name — so PIT sees them and Kover does not.
Candidates for `excludedMethods` in task 5.1, on the same justification, not for new tests.

**Genuinely unread accessors (2).**

| Class | Method | Line | Declaration |
|---|---|---|---|
| `AnnotatedIdBase` | `getParent` | 52 | `val parent: HtmlId? = null` |
| `NamedAnnotatedIdBase` | `getName` | 96 | `val name: String = ""` |

Both are public API and no test reads either. Line coverage cannot see this: the getter's
line *is* the constructor parameter's declaration line, which the constructor covers. This
is the clearest single example of why the change exists — coverage measures the line,
mutation testing measures the method.

## What this implies for sections 3-5

The plan assumed the core-logic work (section 3) would dominate and the components
(section 4) would be a tail. The measurement says the opposite:

- section 3, `ClassNames` + `TagId`: **2 survivors** (D, E) plus the 8 uncovered
- section 4, the five components: **16 survivors** (A, B, C)

Section 4 also has more shape than "one component per commit" suggests: group A is one
assertion habit repeated eleven times across four files, and groups B and C are one habit
(attributes are never asserted) repeated five times. Fixing them file-by-file would write
the same fix five times.

Neither observation changes what has to be true at the end, so no task is being
rewritten — but 4.1-4.4 should be read as "per cause", not "per component".
