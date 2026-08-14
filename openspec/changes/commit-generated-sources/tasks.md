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

- [x] 1.1 `. r` Capture a reference copy of `lib/build/generated/**` after a clean
      regeneration — 450 files (63 components, 63 tests, 324 icons), hashed in memory
      rather than copied, so nothing was written into the repository
- [x] 1.2 `. r` Regenerate a second time and diff against 1.1 — same machine, same locale.
      **Identical**: 0 files added, 0 removed, 0 differing
- [x] 1.3 `. r` Regenerate under `LANG=C` and under `LANG=de_DE.UTF-8`, diff against 1.1.
      **Identical in both cases.** The test has power: the emitted order is demonstrably
      ICU-collated rather than byte-ordered (`addClassNames` precedes `HtmlId`, `button`
      precedes `BUTTON` — byte order would reverse both), so the fall-through is real. It
      simply does not vary across these locales for pure-ASCII identifiers
- [x] 1.4 `. r` Regenerate on the CI runner and diff against the local 1.1 baseline, via a
      temporary workflow on a scratch branch. **Identical**: the job asserted the aggregate
      SHA-256 over all 450 files against the local `0b7ddefd…` and passed on a clean
      `ubuntu-latest` (PR #229, since closed; scratch branch and workflow deleted). The
      assertion had to live in the job's exit code because no tool available here can read
      workflow logs
- [x] 1.5 `. d` Record the outcome — the requirement is now **Verified** across runs,
      locales and machines

**Assumption 1 holds.** Sections 4-6 are unblocked; the drift check is viable.

## 2. Check assumption 2 — does the port actually need committed sources?

*Refuted if a throwaway baseline serves equally well. Then justification (3) in the proposal
drops out, and the change must stand on justifications (1) and (2) alone — which is a
smaller case and may not carry ~450 committed files.*

- [ ] 2.1 `. d` Write down how `codegen-to-kotlin` would verify parity against an
      uncommitted baseline copy, and what specifically fails that a committed one solves
- [ ] 2.2 `. d` Record the answer in proposal.md — either strike justification (3) or state
      what makes the committed form necessary

## 3. Remove the dead comparator branches

**Revised after section 1** (2026-08-14). This was "fix the comparator before capturing the
baseline, so a later fix does not bury the signal in noise". Section 1 refuted the premise:
the fix would produce **no diff at all**, so it is neither urgent nor a blocker, and it is a
deletion rather than a fix.

Measured: all 1911 import lines across the generated output fall under exactly three roots —
`io`, `kotlin`, `kotlinx`. Since `io` sorts before both, the grouping the branches intend
("kdaisyui imports first") is already produced by plain alphabetical order. Zero imports
begin with the literal `kdaisyui` the predicate tests, so the branches cannot fire, and
removing them cannot change the output.

- [x] 3.1 `. r` Delete the two unreachable branches in `codegen/src/generator-new.js:344-348`
      and `codegen/src/generator.js:216-219`, leaving the `localeCompare` they fall through
      to. **Confirmed unchanged**: regenerated with and without the branches, aggregate hash
      over all 450 files identical (`0b7ddefd…`), `:lib:test` 566 green

Considered and rejected: replacing `localeCompare` with a locale-independent comparison, to
remove the ICU dependency rather than verify it. Tasks 1.2-1.3 measured the locale variance
at zero, so the swap would flip the import order in all 63 components — a real diff bought
against a risk that was already measured away. If task 1.4 finds cross-machine drift this
comes back on the table; until then it is a speculative fix.

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
