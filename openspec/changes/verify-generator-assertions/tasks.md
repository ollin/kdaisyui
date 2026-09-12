# Tasks

Ordered by uncertainty before dependency. Section 1 checks the three assumptions the change rests
on; a red result there rewrites the plan rather than being worked around.

## 1. Check the assumptions before building on them

**Corrected before starting.** These were written as codegen unit tests. They cannot be: all three
are statements about the real DaisyUI submodule, and `codegen-tests` runs **without submodules** by
an explicit decision whose comment names this exact situation — *"If this job ever needs either,
that is the signal that the tests have drifted into integration territory which
generated-sources-drift already covers end to end."* A test that skips when the submodule is absent
is a test that does not run in CI, which is worse than none.

All three are also one-time facts about the current tree: 1.1 and 1.3 describe a config section
this change deletes, so no permanent test could outlive them.

So Section 1 is **measurement, recorded in `design.md`**, with throwaway probes under `./tmp/`. The
parts that deserve to be permanent are guards inside the generator, exercised by
`generated-sources-drift`, which does have the submodule — see 3.2.

- [x] 1.1 `. d` Measure: is the `noContent` list exactly the set of components whose element is
  void? Anything else means the rule is too narrow and **the change must be rewritten**.
- [x] 1.2 `. d` Measure: does the docs route — the tag carrying `$$<componentClass>` in a fenced
  ```html block of `+page.md` — yield an element for all 66, and disagree with the generator
  exactly three times (`otp`, `tab`, `calendar`)? A fourth disagreement belongs in the proposal
  before the guard lands.
- [x] 1.3 `. d` Measure: are exactly two config entries unreachable, both in `noContent`? More
  would mean the consumption guard cannot land green.

## 0. The codegen's first dependency

Prerequisite for section 3, and a reversal of a recorded property — see `design.md` decision 0.
Landed first and on its own, so the build wiring is provable before anything depends on it.

- [x] 0.1 `. r (internal)` Add the HTML parser to `codegen/package.json`, pin it exactly, commit a
  real `codegen/package-lock.json`.
  **Label corrected before doing it.** This said `! F (internal)`, on the premise that it "cannot
  be proven by a test, only by the build going green". That premise is false: nothing imports the
  package yet, so no behaviour changes and the proof is that regeneration still produces
  byte-identical output. Zero behaviour means a lowercase letter, and provable means `.` — so
  `. r`, not `! F`. Risk is claimed by evidence, not by how large a change feels.
- [x] 0.2 `^ F (internal)` Add an `installCodegenDeps` Gradle task running `npm ci`, and make the
  five generator tasks plus `testCodegen` depend on it. Declare `package-lock.json` as its input
  and `node_modules` as its output so Gradle can skip it when nothing moved.
- [x] 0.2a `^ B (internal)` Declare `package-lock.json` as an input to the five generator tasks
  and `testCodegen`. **Found by running 0.4**: with a dependency, the generators' declared inputs
  no longer cover everything that decides their output. A parser upgrade changes the lockfile and
  `node_modules` but none of `codegen/src`, the submodule, `package.json` or `codegen-config.json`
  — so Gradle calls them up to date and regeneration silently does not happen. The lockfile is the
  right proxy for `node_modules` because `npm ci` makes it exact, and it hashes cheaply.
- [x] 0.3 `^ F (internal)` Add `npm ci` to CI's `codegen-tests` job — the only job that runs `npm
  test` directly rather than through Gradle.
  **Widened while doing it.** `generated-sources-drift` also runs npm now, through the Gradle
  tasks, and it had **no** `setup-node` at all — it used whatever Node the runner shipped. That
  was tolerable while the generators only executed a script; with `npm ci` the npm version
  decides how a lockfile is installed, and `ci.yml` already warns that a Node mismatch is not
  loud. Both Node-running jobs now read `.tool-versions`.
- [x] 0.4 `. r (internal)` Prove regeneration still works end to end from a clean `node_modules`:
  delete it, run `just generate`, confirm `lib/generated` and `docs/reference` come out unchanged.
- [x] 0.5 `. d` Correct the three places that record zero dependencies: `AGENTS.md`,
  `lib/build.gradle.kts:229` and the `kdaisyui-codegen` skill. Say what replaced the property and
  why, not just that it is gone.

## 2. The void-element rule

- [x] 2.1 `^ r (internal)` Add the void-element set to `component-shape.ts` with a comment naming
  the HTML specification, and a predicate on `TagClass`. Nothing reads it yet.
- [x] 2.2 `^ B (internal)` Derive the content parameter from the element instead of
  `ComponentConfig.noContent`. Test first: the failing assertion is that `daisyMask`,
  `daisyFileInput` and `daisyThemeController` declare no `content`.
- [x] 2.3 `. d` Delete the `noContent` section from `codegen-config.json`.
- [x] 2.4 `^ B` Regenerate; read the diff — three component files and their tests lose a parameter,
  nothing else. Commit generated Kotlin and tests.
- [x] 2.5 `^ B` Regenerate `docs/reference/` and commit.

## 3. The element cross-check

- [x] 3.1 `^ F (internal)` Parse the documented root element from `+page.md`'s fenced ```html
  blocks via the `$$` marker, **using the parser from section 0 — no regex over HTML**. Unit-tested
  with inline fixtures against `swap` (`<label>`), `menu` (`<ul>`) and `modal` (`<dialog>`) — three
  of the nine the old route could not answer — plus the three constructs a regex gets wrong: a `>`
  inside a quoted attribute value, an HTML comment, and a `<script>` block.
- [x] 3.2 `^ F (internal)` Fail generation when the chosen element disagrees and no exception is
  recorded, naming component, chosen and documented element.
- [x] 3.2a `^ F (internal)` Fail generation when the docs route has **no opinion** for a component.
  Added by the section 1 correction: the measurement that it answers all 66 today is a fact about
  today, and a future component whose docs page carries no `$$`-marked example would leave the
  cross-check silently unable to check it — the precise failure this change exists to end. This is
  the permanent form of task 1.2, and it lives here because `generated-sources-drift` has the
  submodule that `codegen-tests` deliberately does not.
- [x] 3.3 `^ F (internal)` Fail generation on an exception that is no longer needed, so the list
  cannot accumulate.
- [x] 3.4 `. d` Record the `tab` and `calendar` exceptions with a reason and a tracking issue each.
  Open those two issues first — an exception pointing at nothing is the thing this guard exists to
  prevent.
- [x] 3.5 `^ B` Add `componentElements: {"otp": "label"}`; regenerate; commit Kotlin, tests and
  reference pages.
- [x] 3.6 `^ B` Follow the receiver change in `example-app/.../WhatsNewFragment.kt`, and confirm the
  E2E scenario covering that page still passes.
- [x] 3.7 `! d` Re-dump `lib/api/lib.api` and **read the diff** rather than accepting it. All four
  breaking edits have landed by now, so one dump covers them.
  **Task added after the fact.** The first draft had this as 2.6 and the rewrite lost it, which
  `check` found by failing: `checkKotlinAbi` is part of `check` and had been red since 2.4. Its
  output is also the measured proof of defect 6 — three removed parameters appear in the diff and
  `daisyOtp`'s changed receiver does not — so the dump is evidence, not bookkeeping.

## 4. The citations

- [ ] 4.1 `^ B (internal)` Build the `// Source:` header from `ComponentSource.componentDir`.
  Test first: a multi-word component cites its real directory.
- [ ] 4.2 `^ B (internal)` Use `htmlTag` rather than `tagBuilder` in generated doc-comment prose,
  leaving emitted code on `tagBuilder`. Test first, with `FIELDSET`.
- [ ] 4.3 `. B` Regenerate and commit: 10 headers and 2 doc comments change, no signature does.

## 5. The consumption guard

- [ ] 5.1 `^ F (internal)` Record consumed keys in `readComponentConfig` and fail the run on any
  component-keyed entry nothing read, naming section and key.
- [ ] 5.2 `. r (internal)` Prove it bites: a unit test adds an unread entry and asserts the failure
  names it.

## 6. The Kotlin API baseline

- [ ] 6.1 `^ F (internal)` Emit a stable Kotlin signature dump from `ComponentShape` — name,
  receiver, parameter names, types with lambda receivers, order, defaults. Unit-tested for
  stability: same shape in, byte-identical dump out.
- [ ] 6.2 `^ F (internal)` Add `updateComponentApi` and `checkComponentApi` tasks. `check` depends
  on the second; **`just generate` must not run the first** — that is what makes it a gate.
- [ ] 6.3 `. d` Commit the baseline `lib/api/components.api`.
- [ ] 6.4 `^ F (internal)` Add the CI step, alongside `api-baseline` rather than inside it, so an
  erased-by-JVM change and a JVM-visible one are distinguishable in the checks list.
- [ ] 6.5 `. r (internal)` Prove it bites: flip a lambda receiver locally, observe
  `checkComponentApi` fail where `checkKotlinAbi` passes, revert. Record both results — the
  passing one is half the point.

## 7. Migration and documentation

- [ ] 7.1 `. d` **How to migrate** in `README.md`: `daisyFileInput`, `daisyThemeController` and
  `daisyMask` lose their trailing lambda; `daisyOtp`'s receivers change `DIV` → `LABEL`. Say why
  for each — the element cannot hold children; the element was wrong — and note that the `otp`
  change is invisible in `lib/api/lib.api`.
- [ ] 7.2 `. d` Update the `kdaisyui-codegen` skill: `noContent` is gone and derived, the element
  is cross-checked against the docs route, config entries are consumption-checked, and there is a
  second API baseline with a different question.

## 8. Gate and adoption

- [ ] 8.1 Full green per `openspec/config.yaml` — repo-wide compile, complete suite including e2e,
  coverage, `analyze_change_set`.
- [ ] 8.2 `openspec validate --all --strict`.
- [ ] 8.3 Write the evaluation under `./tmp/` for Oliver to adopt. Name the breaking surface —
  four functions — and the two deferred defects explicitly.
