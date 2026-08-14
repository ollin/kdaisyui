# Tasks

Ordered by **uncertainty before dependency**. Sections 1 and 2 check the two assumptions
that can invalidate the rest of the plan; nothing in sections 3-6 is worth doing until they
have answered. Stop and revise the change if either refutes its assumption — that is the
expected outcome of putting them first, not a failure.

## 1. Check assumption 1 — is regeneration deterministic?

*Refuted if any diff below is non-empty. Then: requirement "Committed output that no longer
matches its inputs is rejected" is unbuildable as written, and the change either shrinks to a
committed baseline with the build still coupled, or gives way to assumption 2's throwaway
baseline. Revise proposal, specs and these tasks before continuing.*

- [ ] 1.1 `. r` Capture a reference copy of `lib/build/generated/**` after a clean
      regeneration, under `./tmp/` so it is never committed
- [ ] 1.2 `. r` Regenerate a second time into a separate directory and diff against 1.1 —
      same machine, same locale. Record the result in this file
- [ ] 1.3 `. r` Regenerate under a different `LANG` (e.g. `LANG=C` vs `LANG=de_DE.UTF-8`)
      and diff against 1.1. This is the one the `localeCompare` fall-through threatens
- [ ] 1.4 `. r` Regenerate on the CI runner and diff against the local 1.1 baseline, via a
      temporary workflow on a scratch branch. Delete the workflow afterwards
- [ ] 1.5 `. d` Record the outcome on the "Regeneration is deterministic" requirement:
      change **Assumed** to **Verified**, naming the diffs, or revise the change

## 2. Check assumption 2 — does the port actually need committed sources?

*Refuted if a throwaway baseline serves equally well. Then justification (3) in the proposal
drops out, and the change must stand on justifications (1) and (2) alone — which is a
smaller case and may not carry ~450 committed files.*

- [ ] 2.1 `. d` Write down how `codegen-to-kotlin` would verify parity against an
      uncommitted baseline copy, and what specifically fails that a committed one solves
- [ ] 2.2 `. d` Record the answer in proposal.md — either strike justification (3) or state
      what makes the committed form necessary

## 3. Make the ordering deterministic

Enabler. Behaviour-preserving in the sense that matters: output changes exactly once, and is
stable afterwards. Depends on section 1 having found the cause.

- [ ] 3.1 `^ b` Fix the import comparator in `codegen/src/generator-new.js:344-348` so its
      predicate matches the real package prefix, with a test pinning the emitted order
- [ ] 3.2 `^ b` Apply the same fix to the duplicate at `codegen/src/generator.js:216-219`
- [ ] 3.3 `. r` Regenerate and review the resulting diff — it is expected and should be
      import lines only

## 4. Move the sources into the repository

Enabler. Same bytes, different path; provable by diffing against the section 1 baseline.

- [ ] 4.1 `. r` Point the generator tasks and `sourceSets` at a committed generated source
      root beside `src/`, leaving the build dependencies untouched
- [ ] 4.2 `. r` Regenerate and confirm the output is byte-identical to the 1.1 baseline
- [ ] 4.3 `. f` Commit the generated sources
- [ ] 4.4 `. d` Add `.gitattributes` marking the generated root `linguist-generated`

## 5. Decouple the build

Desired change. Only after section 4, so a broken build cannot be mistaken for a broken
generator.

- [ ] 5.1 `^ F` Remove the `compileKotlin` / `compileTestKotlin` dependencies on the
      generator tasks (`lib/build.gradle.kts:194-201`)
- [ ] 5.2 `^ F` Verify a build with uninitialised submodules and no Node on `PATH` — this is
      the scenario the requirement names, so it needs an actual run, not an argument
- [ ] 5.3 `. d` Add a `just` recipe that regenerates and shows the resulting diff

## 6. Reject drift

Desired change. Build this **only if** task 1.5 recorded Verified.

- [ ] 6.1 `^ F` Add the CI job: regenerate, then fail if the working tree is dirty
- [ ] 6.2 `^ F` Prove it fails — hand-edit one generated file on a scratch branch and watch
      the job go red. A gate nobody has seen fail is not known to be a gate
- [ ] 6.3 `. r` Drop `submodules: recursive` from the jobs that no longer need it

## 7. Documentation

- [ ] 7.1 `. d` Update `AGENTS.md`: the "never edit generated code" rule names the new
      committed path
- [ ] 7.2 `. d` Fold in the remaining working-tree edits — `.tool-versions` and the
      `README.md` AI-context table — and correct the nine documentation references to
      `.tool-versions` that no longer match its contents
