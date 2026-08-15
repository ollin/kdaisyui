# Tasks

Ordered by uncertainty. Section 1 is a throwaway experiment that costs minutes and can
rewrite the rest of this plan; nothing is committed to the working branch until it has
answered.

Nothing here is pushed, so a merge that goes badly is discarded with `git merge --abort` or
by deleting the trial branch. That cheapness is why section 1 comes first instead of a long
static analysis of the two trees.

## 1. Find out what the merge actually costs

*Refuted if the conflict set is unmanageable. Then: reconstruct this branch's four
substantive commits on top of `origin/main` instead of merging, and rewrite sections 2-5.*

- [x] 1.1 `. r` On a throwaway branch off `ollins-stuff-at-home`, merge `origin/main` and
      record the conflict list without resolving anything. **23 conflicts**, no resolution
      attempted, merge aborted and the branch deleted
- [x] 1.2 `. d` **Outcome: merge, not reconstruct.** Assumption 1 holds. Only five conflicts
      need thought; the rest are documentation or vendored files with an obvious winner.

      | Conflict | Winner | Why |
      |---|---|---|
      | `lib/build.gradle.kts` | **both** | main's coverage/publishing wiring, this branch's committed source roots and decoupling. The only genuinely hand-written resolution — task 2.2 |
      | `.github/workflows/ci.yml` | **both** | main's `koverVerify` and `checkout@v7`, this branch's drift job and submodule-free test jobs — task 2.3 |
      | `gradle.properties` | **main** | the file is now just `version=0.1.0`; everything else moved to `libs.versions.toml`. This branch's DaisyUI ceiling comment goes with it |
      | `codegen/src/generator-new.js` | **both** | main reworked 44 lines; this branch deleted the two unreachable comparator branches. Re-apply the deletion if main still carries them |
      | `openspec/config.yaml` | **both** | main's context plus this branch's uncertainty-first rules and the gate-free wording |
      | `codegen/package.json` + lock | **this branch** | removing `js-yaml` supersedes main's bump of it. Still unused there |
      | `.opencode/commands/opsx-*.md` (4), `.opencode/skills/openspec-*` (4) | **main** | vendored upstream copies, added on both sides |
      | `AGENTS.md`, `README.md`, `justfile`, `.tool-versions`, `.gitignore`, `example-app/build.gradle.kts` | **merge by hand** | both sides edited; section 5 corrects the content afterwards either way |
      | `.github/copilot-instructions.md`, `CODESEEKER.md` | **decide** | deleted on this branch, modified on main. Delete/modify, so git cannot guess |

      Two things this exposed that the proposal did not anticipate:

      - This branch **deleted** `.github/copilot-instructions.md` and `CODESEEKER.md` at some
        point. Main still maintains both. Whether that deletion was deliberate is unknown and
        needs Oliver.
      - The vendored `openspec-*` skills and `opsx-*` commands exist on both sides with
        different content. Taking main's wholesale may drop local edits — check before
        resolving.

## 2. Do the merge

- [ ] 2.1 `. r` Merge `origin/main`, resolving toward main for build, release and coverage
      infrastructure, and toward this branch for the committed generated sources and the
      drift job
- [ ] 2.2 `^ r` Re-apply the `generated-sources` decoupling on main's `lib/build.gradle.kts`:
      compilation must not depend on the generator tasks, and the source roots must point at
      `lib/generated`
- [ ] 2.3 `^ r` Merge the two `ci.yml` versions: main's coverage gate and `checkout@v7`, this
      branch's `generated-sources-drift` job, and no submodules on the test jobs

## 3. Regenerate at DaisyUI 5.7.16

*Assumption 3. The submodule jumps seven minor versions and the parser was reworked.*

- [ ] 3.1 `. r` Regenerate with main's codegen and review the diff against the committed
      `lib/generated` — this is the first real exercise of the reviewable-bump claim that
      `commit-generated-sources` was built for
- [ ] 3.2 `. f` Add the heroicon tests main's `test-generator-heroicons.js` produces, which
      this branch's committed tree does not have
- [ ] 3.3 `. r` Commit the regenerated tree and confirm the drift job would pass

## 4. Get the gate green

*Assumption 4, the one Oliver accepted the risk on.*

- [ ] 4.1 `^ F` Run `./gradlew :lib:test koverVerify` and record the shortfall, if any
- [ ] 4.2 `^ F` Close the gap, or bring back a documented exclusion — do not lower the
      threshold to make it pass
- [ ] 4.3 Full suite: `:lib:test`, `:ktor-integration:test`, `:e2e-tests:test`, `koverVerify`

## 5. Correct the documents that are now wrong

- [ ] 5.1 `. d` `AGENTS.md` and the `kdaisyui-release` skill: release automation exists —
      JReleaser, `bom/`, `release.yml`, version from the git tag
- [ ] 5.2 `. d` `AGENTS.md`, `gradle.properties`, `renovate.json` and the `kdaisyui-codegen`
      skill: the DaisyUI 5.5.20 ceiling is gone
- [ ] 5.3 `. d` Everywhere that names `gradle.properties` as the version source: it is
      `gradle/libs.versions.toml` now, and `gradle.properties` holds only the project version

## 6. Verify the openspec state

- [ ] 6.1 `. d` Reconcile the two `openspec/config.yaml` files and the two `specs/` trees —
      main brings `coverage-enforcement`, this branch brings `generated-sources`
- [ ] 6.2 `. d` Decide what happens to main's open changes `adapt-daisyui-5-6` and
      `add-mutation-testing`, which this session never saw
- [ ] 6.3 `. d` Decide whether `generated-sources` needs a spec delta — if the merge changes
      what it promises (heroicon tests are a fourth generated category), it does, and this
      change's `.openspec.yaml` must not carry `skip_specs`
