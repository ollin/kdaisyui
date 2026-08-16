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

- [x] 2.1 `. d` Write down how `codegen-to-kotlin` would verify parity against an
      uncommitted baseline copy, and what specifically fails that a committed one solves.
      **Answer**: snapshot `lib/build/generated/**` (a copy, or hashes as in tasks 1.1-1.4),
      run the Kotlin generator, compare — nothing in that procedure needs git. Nothing fails
      that a committed copy solves; it only makes the baseline ambient instead of ad hoc
- [x] 2.2 `. d` Record the answer in proposal.md — justification (3) struck and demoted to
      a side effect; the change stands on (1) publishable, browsable API and (2) reviewable
      bumps. Decided by Oliver, 2026-08-14

**Assumption 2 resolved by refutation — and the change survives it.** The honest accounting
was the point: (3) was a one-time need that never required committing; (1) and (2) are the
permanent benefits that do.

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

- [x] 4.1 `. r` Point the generator tasks and `sourceSets` at a committed generated source
      root beside `src/` (`lib/generated/{main,test}/kotlin`), build dependencies untouched
- [x] 4.2 `. r` Regenerate and confirm byte-identity: 450/450 files hash-equal to the old
      tree (itself equal to baseline `0b7ddefd…`); `:lib:test` 566 green compiled from the
      new root with `build/generated` deleted, so the classes cannot have come from it
- [x] 4.3 `. f` Commit the generated sources — 450 files, the exact bytes verified in 4.2
- [x] 4.4 `. d` Add `.gitattributes` marking the generated root `linguist-generated` —
      deliberately without `-diff`, since reviewable diffs are justification (2)

## 5. Decouple the build

Desired change. Only after section 4, so a broken build cannot be mistaken for a broken
generator.

- [x] 5.1 `^ F` Remove the `compileKotlin` / `compileTestKotlin` dependencies on the
      generator tasks — and `sourcesJar`'s two, which were the same coupling
- [x] 5.2 `^ F` Verify a build with no Node on `PATH` — `:lib:test` (566 green) and
      `:lib:sourcesJar` (391 sources) both succeed with `~/.asdf/shims`, the only PATH entry
      providing node/npm, removed. The submodule half is verified structurally for now: the
      task graph contains no generate or checkout task at all, so nothing reads `daisyui/`.
      The clean-clone proof arrives in 6.3 when `submodules: recursive` leaves the test jobs
- [x] 5.3 `. d` Add a `just` recipe that regenerates and shows the resulting diff.
      Regenerating after the move left `lib/generated` untouched — the drift check in
      miniature

Found on the way: `just generate` ran `npm run generate`, which passes no `--output-dir`, and
all three generators defaulted to `lib/src/**` — inside the hand-written tree. Fixed in its
own commit (`! b`), since it is a defect and not part of decoupling.

## 6. Reject drift

Desired change. Build this **only if** task 1.5 recorded Verified.

- [x] 6.1 `^ F` Add the CI job: regenerate, then fail if the working tree is dirty. Uses
      `git status --porcelain` rather than `git diff --exit-code`, so a newly added
      component counts as drift instead of slipping through as "no diff"
- [x] 6.2 `^ F` Prove it fails — hand-edit one generated file on a scratch branch and watch
      the job go red. **Confirmed**: one added comment line in `Kbd.kt`, no generator input
      touched → `generated-sources-drift` **failed**, while `unit-tests` and `e2e-tests`
      **passed** on the same commit. That second half is the clean-clone proof: both ran on
      a checkout with no submodules at all (PR #231, closed; branch deleted)

~~Note for whoever adds a workflow next: `ci.yml`'s `pull_request` trigger does **not** fire on
this repository.~~ **Wrong, corrected 2026-08-15.** The trigger is fine; PR #230 from Renovate
ran the full suite on the same workflow file.

The real cause: PRs #229 and #231 had `mergeable_state: "dirty"`, because their scratch
branches sat on `ollins-stuff-at-home` while it still conflicted with `main` in 23 files.
`pull_request` workflows run against the PR's **merge commit**, which cannot be computed for a
conflicted PR, so they are skipped — not failed, skipped. `pull_request_target` runs from the
base branch and is unaffected, which is why the title check still appeared.

I generalised from two PRs that shared an unexamined property, and that property was the
cause. One look at somebody else's PR would have settled it. The durable lesson is recorded in
the `kdaisyui-release` skill: a conflicted PR shows one green check and no indication that the
real ones never ran.
- [x] 6.3 `. r` Drop `submodules: recursive` from `unit-tests` and `e2e-tests`; only the
      drift job still checks them out. This is also the clean-clone proof task 5.2 could
      not do locally — CI now builds and tests from a checkout with no submodules at all

## 7. Documentation

- [x] 7.1 `. d` Update `AGENTS.md`: the "never edit generated code" rule names the new
      committed path — and rests on the drift job rather than on futility, since a hand edit
      now survives locally. Same correction applied to `openspec/config.yaml` and the
      `kdaisyui-codegen` / `kdaisyui-testing` skills, which described the old build output
- [x] 7.2 `. d` Fold in the remaining working-tree edits — `.tool-versions`, `README.md`,
      `docs/how-to.md` and the `justfile` header, all of which claimed `asdf install` brings
      Node and `just`. It now pins the JDK and nothing else

## 8. Verification

- [x] 8.1 Full suite, everything re-executed: **611 tests green**
      (`:lib:test`, `:ktor-integration:test`, `:e2e-tests:test` with `--rerun-tasks`,
      25/25 tasks executed)
- [x] 8.2 CI on a clean runner: `unit-tests` and `e2e-tests` green **without submodules**
