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

- [x] 2.1 `. r` Merge `origin/main`, resolving per the 1.2 decision list
- [x] 2.2 `^ r` Re-apply the `generated-sources` decoupling on main's `lib/build.gradle.kts`
- [x] 2.3 `^ r` Merge the two `ci.yml` versions

All three landed in the merge commit `40da5ee`, which lists every resolution. **606 tests
green** immediately after — assumption 1 fully discharged, and assumption 2 survives:
`generated-sources` still holds, the decoupling re-applied cleanly onto main's build script.

One correction to the 1.2 map: it said the vendored `.opencode/opsx-*` files should go to
main. Backwards — this branch carries `generatedBy: 1.7.0` against main's `1.4.1`, so ours
are the newer copies. Caught by reading them instead of applying the map.

## 3. Regenerate at DaisyUI 5.7.16

*Assumption 3. The submodule jumps seven minor versions and the parser was reworked.*

- [x] 3.1 `. r` Regenerate with main's codegen and review the diff. **450 → 457 files**:
      three new components (`Aura`, `Megamenu`, `Otp`) with tests, 16 changed, none removed
- [x] 3.2 `. f` Heroicon tests added — `HeroIconsGeneratedTest.kt`, render coverage for all
      324 icons
- [x] 3.3 `. r` Committed as `2297257`; 963 tests green and `example-app` compiles

**Assumption 3 holds** — 5.7.16 regenerates cleanly across a seven-minor jump.

**And the reviewable diff paid for itself on first use.** Three things it caught that would
otherwise have shipped unseen:

1. `TooltipVariant.Neutral` is **gone** — DaisyUI dropped `tooltip-neutral`. Source-breaking.
2. `daisyDropdown` renders `<ul>` instead of `<details>`, changing its `attrs`/`content`
   receiver from `DETAILS` to `UL`. Source-breaking.
3. **That `<ul>` is a codegen defect.** DaisyUI 5.7.16's dropdown page shows
   `<div class="dropdown">` about 25 times, `<details>` twice, and `<ul class="dropdown menu">`
   exactly once (`+page.md:111`) — the element heuristic picked the singleton. The old
   `<details>` was not the dominant form either, so this is a pre-existing weakness that
   changed which wrong answer it gives.

- [x] 3.4 Dropdown defect fixed (`ca2129a`), with a correction to my own report: the parser
      reads `daisyui/skills/daisyui/components/`, not `+page.md`, and there `<ul>` is the
      first of two documented variants rather than an outlier. The real fault is that the
      first variant needs `popover`/`id`/`style` attributes the generator cannot emit, so it
      produces a dropdown that cannot open. Recorded per component in `componentElements`.

## 3b. The silent coverage loss (found while fixing 3.4, not planned)

Regenerating surfaced a warning on stderr: `test-generator.js` was still reading components
from the pre-move `lib/build/generated/...`, and on a missing directory it **warned and
returned**. The source-root move in `commit-generated-sources` therefore deleted 66 coverage
test files and 525 assertions without turning anything red — the suite stayed green while
testing less, through every run reporting 566, 606 and 963.

- [x] 3b.1 `^ b` Restore them (`d51d417`) — `:lib:test` 963 → 1488
- [x] 3b.2 Measure what was actually lost, rather than assume. Reproduced the pre-fix state
      and ran the gate: **line 92.088%, branch 90.909%**, both against a 100% minimum. So the
      loss was material, and `koverVerify` would have caught it — this branch simply had no
      gate until the merge
- [x] 3b.3 `^ b` Remove the cause (`d921d87`): `lib/build.gradle.kts` passes
      `--components-dir`, so the path lives in one place, and the skip becomes a throw.
      Proven by moving the directory aside — the build now fails at the Gradle layer, before
      the generator runs, because `inputs.dir` makes the dependency declarative

## 4. Get the gate green

*Assumption 4, the one Oliver accepted the risk on.*

- [x] 4.1 `^ F` Run `koverVerify` and record the shortfall. **None** — it passes.
- [x] 4.2 `^ F` Nothing to close. The only gap was 3b, and it was a defect rather than
      genuinely uncovered code; no threshold was lowered and no exclusion added. The gate's
      one exclusion (`*$DefaultImpls`) predates this change and carries its justification in
      `build.gradle.kts:29-52`
- [x] 4.3 Full suite with everything re-executed: **1522 tests green**, `koverVerify` green
      (`:lib:test`, `:ktor-integration:test`, `:e2e-tests:test`, `--rerun-tasks`)

**Assumption 4 holds** — the risk Oliver accepted did not materialise. Worth noting *why* it
did not: the shortfall it anticipated was real (92.088% / 90.909%), but caused by 3b rather
than by the committed sources being untestable. Had 3b not been found first, this section
would have read as "the coverage gate cannot cope with 457 generated files" — the wrong
conclusion, and an expensive one.

## 5. Correct the documents that are now wrong

- [x] 5.1 `. d` `AGENTS.md` and the `kdaisyui-release` skill. The skill was rewritten rather
      than patched — nearly every claim in it was false, including "there is no release
      automation" as its opening heading. It now documents the tag trigger, the three
      artifacts, and the `stageAll` / `verifyStagingComplete` guards that exist because 0.1.1
      shipped with only the BOM reaching Central
- [x] 5.2 `. d` The ceiling. `gradle.properties` lost it with the merge; the `kdaisyui-codegen`
      skill's ceiling section is replaced by how the parser actually resolves its input today,
      including that the element choice is a heuristic and how to override it
- [x] 5.3 `. d` `AGENTS.md`, `openspec/config.yaml`, `README.md`, `justfile` — the version
      source is `gradle/libs.versions.toml`; `gradle.properties` holds only the project version

- [x] 5.4 `. d` **`renovate.json`, not in the plan.** It carried a `<=5.5.22` cap on
      `org.webjars.npm:daisyui` and a `customManager` matching `daisyui.version=` in
      `gradle.properties`. Both were added on **this** branch — `origin/main` never had them —
      and both are now actively wrong: the cap blocks every DaisyUI update for a reason that
      no longer exists, and the customManager targets a key that no longer exists. Removed;
      the "never automerge a DaisyUI bump" rule stays

## 6. Verify the openspec state

- [x] 6.1 `. d` Reconciled. Both `specs/` trees coexist without overlap —
      `coverage-enforcement` from main, `generated-sources` from here. `openspec/config.yaml`
      kept this branch's content (main's was the untouched default template) and its facts
      were corrected: five modules, a root build script, `libs.versions.toml` as version
      source, and release automation that exists
- [x] 6.3 `. d` **Yes, a delta was needed.** The capability grew a fourth generated category
      (icon render tests), and the drift requirement now has to cover every generator. Written
      as two MODIFIED requirements; `skip_specs` deliberately not set. The validator caught
      that the MODIFIED block would have dropped an existing scenario — a MODIFIED requirement
      replaces the whole block, so surviving scenarios must be copied in

- [x] 6.2 `. d` **Not archived — I withdrew my own recommendation.** I had suggested archiving
      `adapt-daisyui-5-6` as overtaken. Checking its five requirements against the tree instead
      of its summary showed that is wrong: **requirement 5 is unmet.** Neither `example-app`
      nor the E2E suite mentions `aura`, `otp` or `megamenu`, and `docs/reference.md:7` still
      says 63 components while `llms.txt:561` still says DaisyUI 5.5.20.

      Archiving would have published a requirement pinning `5.6.3` and asserted E2E coverage
      that does not exist. The change was lying in both directions — claiming nothing was done
      when sections 1-6 were, and implying sections 7-8 were covered when they are not.

      Made truthful instead: sections 1-6 ticked with evidence, 7 (E2E) and 8.1/8.3 (docs,
      version) left open as the real remaining work, and the spec's `SHALL be 5.6.3` corrected
      — naming a version in a requirement is what made it expire.

      Two findings recorded there for whoever picks it up: the bump was **not purely additive**
      (`TooltipVariant.Neutral` was removed), and every piece of `codegen-config.json` work the
      plan predicted for the three new components turned out unnecessary, while the component
      that *did* need config — `dropdown` — nobody had flagged.

      **`add-mutation-testing`** is untouched by anything here and stays open on its own merits.
