# Tasks

Ordered by uncertainty. Section 1 decides whether this change takes a dependency at all, and
section 2 can still refuse the premise that `example-app` is a sufficient source. Both come
before anything is wired up.

**Precondition:** `generate-component-reference` has landed. See that change's proposal for why
the order is substantive rather than tidy.

## 1. Decide the tool by measuring, not by preference

- [ ] 1.1 `. d` Count the fenced code blocks in `docs/how-to.md`, `docs/tutorials/**`,
  `docs/explanation.md` and `README.md`. Classify each: Kotlin that could come from
  `example-app`, Kotlin that could not, and non-Kotlin (Gradle, shell, HTML). Record the counts.
- [ ] 1.2 `. d` Install `markdown-magic` under `./tmp/` and record its actual installed
  footprint — package count and disk size. Speculation about "a tree" is not a measurement.
- [ ] 1.3 `. d` Decide: `markdown-magic` or a purpose-built transform. Write the decision and
  the three numbers it rests on into `design.md`. **If the counts from 1.1 are small enough that
  injection is not worth any dependency, say so and revise the proposal** — including reducing
  this change to the subtractive option of shortening the snippets.

## 2. Check that `example-app` can actually be the source

- [ ] 2.1 `. r (internal)` Take the three largest Kotlin snippets from 1.1 and find their
  honest equivalent in `example-app`. Record for each: exists already / needs a small addition /
  would have to be invented purely to be quoted.
- [ ] 2.2 `. d` Record the finding. **If a material share fall into "invented purely to be
  quoted", revise the spec's first requirement** — that is the falsifier it names, and the
  answer is a marked-unverified snippet, not a contorted example app.

## 3. One snippet, end to end

- [ ] 3.1 `^ F (internal)` Mark one region in `example-app` and inject it into one document.
  Smallest possible slice, wired for real.
- [ ] 3.2 `^ F (internal)` Prove the loud failure: rename the region in the source, observe the
  build fail naming document, file and region, then restore. Record the output.
- [ ] 3.3 `. d` Only now write down the marker convention — region naming, granularity, where
  markers are allowed — since 3.1 and 3.2 will have taught more than guessing would.

## 4. Convert the documentation

- [ ] 4.1 `^ F (internal)` Convert `docs/tutorials/getting-started.md`.
- [ ] 4.2 `^ F (internal)` Convert `docs/tutorials/build-a-dashboard.md`.
- [ ] 4.3 `^ F (internal)` Convert `docs/how-to.md`. Before converting a recipe, check whether
  a tutorial already shows it — **delete the duplicate rather than injecting it twice.**
- [ ] 4.4 `^ F (internal)` Convert `docs/explanation.md`, including the sections
  `generate-component-reference` moved there from `reference/index.md`.
- [ ] 4.5 `^ F (internal)` Convert `README.md`'s snippets.

## 5. Take over the README component table

- [ ] 5.1 `^ F (internal)` Inject the 66-row table from the same model the reference pages use,
  and remove the hand-maintained-copy marker `generate-component-reference` left behind.

## 6. Close the loop

- [ ] 6.1 `^ F (internal)` Add the CI check that re-runs the injection and fails on a diff.
- [ ] 6.2 `. r (internal)` Prove it bites: change a marked region without re-injecting, observe
  the failure name the document, revert. Record the run.
- [ ] 6.3 `. d` Update `AGENTS.md` and the `kdaisyui-testing` skill with the marker convention
  and the new check.

## 7. Gate and adoption

- [ ] 7.1 Full green per `openspec/config.yaml` — repo-wide compile, complete suite including
  e2e, coverage, `analyze_change_set`.
- [ ] 7.2 `openspec validate --all --strict`.
- [ ] 7.3 Write the evaluation under `./tmp/` for Oliver to adopt.
