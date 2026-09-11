## 1. Wire PIT + smoke-test the kotlin-test bridge (report-only, build green)

- [x] 1.1 Add `pitest` + `pitest-junit5` to `[versions]` and the `pitest` plugin to `[plugins]` in `gradle/libs.versions.toml` (refactoring; catalog only) — the version key is `pitest-plugin`, not `pitest`, so the leaf does not collide with `pitest-junit5`
- [x] 1.2 Apply `alias(libs.plugins.pitest)` to `lib/build.gradle.kts` with a minimal `PitestPluginExtension` scoped to `io.github.ollin.kdaisyui.core.ClassNamesKt` ONLY, `junit5PluginVersion` set, `avoidCallsTo` kotlin.jvm.internal, NO `mutationThreshold` (refactoring; wiring) — also sets `outputFormats` to HTML+XML, since task 2.3 has to read the surviving-mutant list mechanically
- [x] 1.3 SMOKE TEST: run `./gradlew :lib:pitest` and confirm PIT generates mutants AND runs the kotlin-test suite (not "0 tests"); if the JUnit5 bridge fails, fix `junit5PluginVersion` before proceeding (documentation; capture evidence — this de-risks the whole change)

  **PASSED.** PIT 1.19.0 plugin, `pitest-junit5-plugin` 1.2.3, JDK 21, Kotlin 2.4.20.
  Evidence from the run on `ClassNamesKt`:
  - `Created 1 mutation test units in pre scan` / `Generated 17 mutations`
  - tests execute on the JUnit Platform: `[engine:junit-jupiter]/[class:io.github.ollin.kdaisyui.core.ClassNamesTest]`
  - `Ran 20 tests (1.18 tests per mutation)` — the "0 tests" failure mode did NOT occur
  - `Line Coverage (for mutated classes only): 34/34 (100%)`
  - `Killed 16 (94%)`, 1 survivor (`ConditionalsBoundaryMutator`), 0 without coverage

  Two plan corrections came out of this and are recorded on 1.2 and 2.1: the target is
  `ClassNamesKt`, and `targetTests` must be set explicitly. Both failure modes report as
  a clean run rather than an error, so neither would have been caught by a green build.

  The bridge assumption the whole change rested on is now **Verified**, not Assumed.

## 2. Expand scope + baseline measurement

- [x] 2.1 Expand `targetClasses` to the full core-logic scope (`io.github.ollin.kdaisyui.core.*`) (refactoring; config) — the wildcard is required, not stylistic: file-level functions compile to `<File>Kt` classes, so a name taken from a `.kt` filename matches nothing (see 1.3). `targetTests` had to widen to `io.github.ollin.kdaisyui.*` in the same step, or every test outside `core` counts as non-existent when deciding whether a mutant was killed.
- [x] 2.2 From the compiled output, identify the 3–5 most branch-rich components (most variant/size/boolean-modifier combinations) and add their `...Kt` classes to `targetClasses`; verify the class names against actual compiled output (refactoring; config + verification) — chose `ButtonKt` (18 conditionals), `ModalKt` (23 across 7 functions), `DropdownKt` (12), `TooltipKt` (11), `RangeKt` (10); all five verified present in `lib/build/classes/kotlin/main/...`
- [x] 2.3 Run `./gradlew :lib:pitest` report-only; record the baseline mutation score and list every surviving mutant (class/line/mutator) into the change notes (documentation; no code) — written to `baseline.md`: 223 mutants, 197 killed (88%), 18 survived, 8 uncovered, 12s wall clock. Read it before starting section 3: the survivors group into five causes, not eighteen problems, and section 4 turns out to be the larger half rather than section 3.

## 3. Kill surviving mutants in core logic

**3.1 corrected 2026-09-09.** The task assumed a weak test. It was redundant code. `ClassNames`
had exactly one survivor — a changed conditional boundary on `.filter { it.isNotEmpty() }`
in the nullable overload — and no test could ever have killed it, because the vararg overload
it delegates to trims and drops empties on every element it receives. The mutant was
equivalent: it changed which values reached the callee, not what the callee produced.

So the fix is subtraction, not a test. Removing the redundant `.map {}.filter {}` deletes
the branch instead of asserting around it, and the surviving mutant goes with it. The
alternative — an `excludedMethods` entry under 5.1 — would have kept unreachable code and
added configuration to hide it.

Worth generalising: a surviving mutant is not always a missing assertion. It can be code
that cannot matter, and then the honest response is to delete it.

- [x] 3.1 Remove the redundant trim/filter from the nullable `addClassNames` overload, which was the sole `ClassNames` survivor and an equivalent mutant (refactoring; NOT the feature-test the task originally described) — 223 → 220 mutants, 18 → 17 survivors, 1494 tests still green
- [x] 3.2 Strengthen `TagId`/HtmlId tests to kill all its surviving mutants AND assert (feature-test; small) — one survivor (`AnnotatedIdBase::hashCode`, replaced int return with 0) plus 2 of the 8 uncovered mutants that ARE reachable (`AnnotatedIdBase::getParent`, `NamedAnnotatedIdBase::getName` — public accessors no test reads). The other 6 uncovered are compiler-emitted bridges and belong to 5.1 as exclusions, not here. Landed as `. r`, not `^ f`: all three tests were green on the first run, so they pin existing behaviour rather than changing any.

## 4. Kill surviving mutants in scoped components

**Rewritten 2026-09-09, before any of it was implemented.** The original 4.1-4.4 read
"strengthen tests for scoped component #1 / #2 / #3 / the rest, one component per commit".
That cannot be done: every component test lives under `lib/generated/**`, which
`AGENTS.md` forbids editing and which CI's `generated-sources-drift` job fails on. Five
hand-edits would have been overwritten by the next `just generate` and rejected before
that by CI.

The edit point is `codegen/src/test-generator.js`. Three consequences, all of which change
the shape of the work rather than its goal:

- **One change per cause, not per component.** `baseline.md` groups the 16 component
  survivors into two habits, and a generator fix addresses each once.
- **The fix reaches all 63 components**, not the 5 in `targetClasses`. Widening the
  mutation scope later therefore gets cheaper, not more expensive.
- **This section needs Node**, which nothing else in this change does — `just generate` is
  the only step in the repository that requires it.

Each task below lands the generator change and its regenerated output in ONE commit: the
drift job compares the two, so a commit holding only one of them is red by construction.
The authored diff stays small; the regenerated diff will not be, and that is expected.

- [x] 4.1 Teach the test generator to assert the **exact rendered markup** rather than a substring of it, and regenerate (feature-test; kills group A — 11 survivors across `ModalKt`, `DropdownKt`, `TooltipKt`, `RangeKt`, all `removed call to TagConsumer::onTagEnd`. A substring assertion is satisfied by an element left hanging open, which is exactly what deleting `onTagEnd` produces.) — landed as `html.endsWith("</tag></wrapper>")`, not a full exact-markup assertion: the closing tag is the only part the class assertions could not already see. 10 of the 11 died; the 11th is `daisyRange`, whose `<input>` is void and has no closing tag, so it moves to 4.2. 198 → 208 killed, test strength 93% → 97%.
- [x] 4.2 Teach the test generator to assert the **attributes** a component sets, not only its classes, and regenerate (feature-test; kills groups B and C — 5 survivors: `BUTTON::setDisabled`, `BUTTON::setType`, `INPUT::setType`, `INPUT::setDisabled`, and the negated `disabled` conditional in `daisyRange` that guards one of them) — all 5 dead. Asserts attribute PRESENCE by name, not value: the rendered value is not the enum entry name (`InputType.checkBox` emits `checkbox`), and predicting it would be a second source of truth free to disagree with kotlinx.html. Presence is exactly strong enough, because every one of these mutants removes the setter call. 208 → 213 killed, test strength 97% → 99%.
- [x] 4.3 Re-run `./gradlew :lib:pitest` and confirm all 16 component survivors are dead; confirm `just generate` is idempotent so the drift job stays green (documentation; evidence into `baseline.md`) — 15 of 16 dead. Idempotence confirmed by running all four generator tasks against an already-generated tree: no diff. Full `check` green throughout.
- [x] 4.4 Kill any component survivor the two generator changes did not reach — per cause if it is one, per component only if it genuinely is (feature-test; may be empty, which is the intended outcome) — **empty, as predicted, but not because nothing survived.** One mutant remains and no component test can reach it: it is the `onTagEnd` on `daisyRange`'s void `<input>`, which kotlinx.html's `HTMLStreamBuilder` skips entirely for an empty tag at `prettyPrint = false`. That makes it a mutant-equivalence question, which is 5.1's subject, not a gap in the generated tests. Evidence and the three resolution options are in `baseline.md`.

## 5. Resolve residual / equivalent mutants

**Sections 5 and 6 corrected 2026-09-09, before implementing, after measuring that the
original gate is unreachable.** Decision by Oliver: gate on **test strength**, not mutation
score.

A 100% mutation score cannot be reached, and not because of weak tests. Four mutants are
Kotlin-emitted interface-default bridges in `AnnotatedIdBase` and `StringHtmlId` that no
source-level test can call, and PIT offers no surgical way to drop them: `excludedMethods`
matches by name alone, so excluding `getTarget` would also delete the mutant on
`HtmlId::getTarget` — the one place the real logic lives, and one that IS killed. Excluding
those classes would discard their genuine killed mutants. Removing the bridges themselves
means `-Xjvm-default=no-compatibility`, an ABI change to a published artifact, already
rejected in the Kover config for the same reason.

**Test strength** is killed ÷ (killed + survived); it ignores mutants no test covers. Paired
with the existing Kover gate the two say exactly the intended thing and nothing more:
*everything reachable is executed* (Kover, 100% line and branch) and *everything executed is
asserted* (PIT, 100% test strength). The 4 bridges are excluded by the same reasoning Kover
already applies to `$DefaultImpls`.

- [x] 5.1 Re-run `./gradlew :lib:pitest`; for each remaining surviving mutant, either add an assertion that kills it OR, if provably an equivalent mutant, exclude it via `excludedMethods`/`excludedClasses` with a written justification (feature-test/refactoring; iterate until score is 100%) — one survivor remained and it got an assertion rather than an exclusion. `VoidElementNestingTest` renders `daisyRange` with `prettyPrint = true` and asserts a sibling's indentation. `HTMLStreamBuilder.onTagEnd` decrements `level` BEFORE checking `emptyTag`, so a void `<input>` that skips it indents everything after it one level too deep — a real contract ("a component leaves the nesting level as it found it"), not a formatting assertion.
- [x] 5.2 Confirm the report shows 100% mutation score on the full scope before sharpening the gate (documentation; evidence captured) — **test strength 100%**, not mutation score: 218 mutants, 214 killed, 0 survived, 4 uncovered. Mutation score reads 98% and cannot go higher, per the note above.

## 6. Sharpen the gate (LAST — build now fails below threshold)

- [x] 6.1 Add `testStrengthThreshold.set(100)` to the `PitestPluginExtension` — NOT `mutationThreshold`, which cannot reach 100 on this scope (feature; the gate goes hard, run `./gradlew :lib:pitest` — must pass) — passes at 218 mutants, 214 killed, 0 survived.
- [x] 6.2 Confirm the gate bites: revert one assertion so a mutant survives, run pitest, expect non-zero exit, then restore (feature-test; proves the threshold fails the build) — **verified.** `VoidElementNestingTest`'s assertion was weakened to `assertEquals(html, html)`; PIT reported test strength 99% and PIT's own `throwErrorIfScoreBelowTestStrengthThreshold` raised `Test strength score of 99 is below threshold of 100`, exit 1, BUILD FAILED. Assertion restored, back to 100%.

  **The part worth keeping:** the weakened test still PASSED. `:lib:test` was green, Kover was
  green, and the only thing that objected was the mutation gate. A test that asserts nothing
  is invisible to every other check this project has — which is the entire argument for this
  change, demonstrated rather than asserted.

## 7. CI + docs

- [x] 7.1 Add a dedicated `mutation-tests` job to `.github/workflows/ci.yml` running `./gradlew :lib:pitest` (parallel to unit-tests/e2e-tests, NOT bound to local check) (refactoring; CI wiring) — no submodules, uploads the report on success as well as failure. The threshold stays in `lib/build.gradle.kts` next to its justification rather than being passed on the command line.
- [x] 7.2 Document the mutation-testing gate, its scope, and how to run + read the PIT report locally in `AGENTS.md` (documentation) — plus two anti-patterns (weakening an assertion; swapping to `mutationThreshold`) and the note that a surviving mutant is sometimes code to delete rather than a missing assertion. The CI job count in `kdaisyui-testing` and `kdaisyui-release` was corrected too — five to six.

## 9. Undo the duplication this change introduced (added after 7.2)

**Reopened deliberately.** `analyze_change_set` fails on `lib/generated/**` Code Duplication:
tasks 4.1 and 4.2 append the same two assertions to every generated `*_defaults` and
`*_all_flags` test, so ~12 coverage-test files degraded and several more were newly flagged.
The duplication partly pre-dates this change; 4.1/4.2 made it worse.

The first answer written down — exclude `lib/generated/**` via a new
`.codescene/code-health-rules.json` — was **rejected on challenge**, and the reasoning it
rested on did not survive being checked:

- "A shared helper makes a failing generated test hard to read" is false. A labelled helper
  reports `ModalBox defaults expected:<modal-box> but was:<modal>`, which is exactly as
  readable as the inline form.
- Excluding is **addition**: a new config file whose only job is to silence a signal. Fixing
  the generator is subtraction.
- Generated-ness makes duplication free to MAINTAIN, not free to READ — and these files are
  read precisely when a test fails.
- The safety net now exists. Restructuring generated assertions risks silently weakening
  them, which is what `:lib:pitest` at 100% test strength catches. Before section 6 this
  refactoring would have been unsafe; after it, it is not.

- [x] 9.1 Emit one `assertRendered` helper per generated coverage-test file and collapse the repeated extract-classes + assert block into a single call (refactoring; generator change + regenerated output in ONE commit, drift compares them)
- [x] 9.2 Re-measure and confirm: `analyze_change_set` no longer degrades on the coverage tests, 1498 unit tests still green, test strength still 100% (documentation; evidence)

  **Partly achieved, and the remainder is not worth buying.**

  About twenty coverage-test files went from `degraded` to `improved` or `fixed` — Chat and
  Steps 5.0 → 2.0; Select, Textarea, Otp, Status, FileInput, Button, Badge, Alert, Table,
  Rating, Kbd, Divider, Progress and Tooltip out of the findings entirely. Modal, Drawer,
  Stat, Timeline, Swap, Navbar, Diff and Link no longer degrade.

  **Four still do:** `BreadcrumbsCoverageTest`, `FabCoverageTest`, `HeroCoverageTest`,
  `MockupPhoneCoverageTest` — each with three `*_all_flags` methods flagged as similar. What
  repeats there is the *argument list*, not the assertions: the same parameters set to the
  same values for three parts of one component. That similarity IS the exhaustive-test shape.
  Removing it means making the call indirect, which costs the readability these files exist
  to provide when a test fails. All four score 9.38 — green.

  A second helper covering the id/attrs/content trio was tried three ways, and the sequence
  is worth keeping because the first conclusion drawn from it was too comfortable:

  1. **Folded into `assertRendered`** — five parameters against Kotlin's threshold of four.
     Traded the duplication for an `Excess Number of Function Arguments` smell. 9.09.
  2. **Reverted**, and the residue described as "the argument list, inherent to the
     exhaustive-test shape". That was an over-generalisation: `fab_all_flags` does carry a
     component-specific flag, but `fabClose_all_flags` and `fabMainAction_all_flags` are
     identical apart from the function name and the expected class string.
  3. **Separate `assertCommonFlags`, three parameters** — kept. Removes two assertion lines
     from roughly a hundred generated tests and trips no rule.

  **Step 3 did not clear the finding either: still 9.38, still the same three functions.**
  That is the measurement that settles it. With every shared assertion gone, what CodeScene
  still sees is the seven-line component call — `daisyFabClose(id = …, extraClasses = …,
  attrs = …, content = …)` — which differs between the three tests only in the function
  being called. Collapsing that means passing the component as a lambda, and the lambda's
  receiver differs per component (`DIV`, `UL`, `BUTTON`, …), so the helper would have to be
  generic over the wrapper type in generated code that exists to be read when a test fails.

  Not claimed to be impossible — it is a cost, and the cost is legibility in exactly the
  files that need it most. Recorded so the trade is Oliver's to overturn rather than mine to
  have quietly decided.

  Verified not to have weakened anything: 218 mutants, 214 killed, **test strength still
  exactly 100%**, identical counts. That is the check CodeScene cannot make — a helper that
  quietly dropped an assertion would leave the duplication finding just as fixed.

  `analyze_change_set` therefore still reports `quality_gates: failed` on those four. The
  honest reading is that the metric is right about the similarity and wrong about it being
  worth removing.

**The gate must still fail if the assertions weaken.** 9.2 is not satisfied by CodeScene going
quiet — the mutation score is what says the tests still assert what they did before.

## 8. Gradle 10 readiness (added after section 1; no spec delta)

Not planned up front. Running `:lib:pitest` surfaced the build's existing deprecation
warnings, and the message they end with — "making it incompatible with Gradle 10" — is
not something to leave standing while adding a Gradle plugin and a CI job (7.1) to the
same build. Numbered 8 rather than inserted before 2 so the mirrored issues #135–#152
keep pointing at the tasks they were written for.

These four carry no requirement of their own: nothing a consumer can observe changes, so
there is no delta in `specs/`. They are recorded here rather than left in the git log
because `tasks.md` is what the archive keeps.

Gate for all four: `gradle check --warning-mode all --rerun-tasks` → 0 problems, 1555
tests passing, e2e included.

- [x] 8.1 Replace the `val x by getting(Type::class)` test-suite delegates with `getByName<Type>("x")` in `buildSrc`, `:lib` and `:ktor-integration` (refactoring) — Gradle 9.6 deprecated every Kotlin DSL property delegate for removal in 10
- [x] 8.2 Replace the `val x by tasks.registering(Type::class)` jar delegates with `tasks.register<Type>("x")` in `:lib` and `:ktor-integration` (refactoring)
- [x] 8.3 Apply the foojay resolver in `buildSrc/settings.gradle.kts` (refactoring) — buildSrc is a separate build, so the root settings' resolver never reached it; declared without a version because buildSrc inherits the root's plugin classpath
- [x] 8.4 Drop `-Xcontext-parameters` from `:ktor-integration` and `:example-app` (refactoring) — redundant at Kotlin language version 2.4, which is what the compiler now reports

No issues mirrored for 8.1–8.4: all four were implemented before being written down, and
a mirror that exists only to be closed in the same merge request is noise rather than
tracking.
