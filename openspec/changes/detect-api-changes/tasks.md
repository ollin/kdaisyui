# Tasks

Ordered by uncertainty. Sections 1 and 2 decide what is buildable and cost minutes; nothing is
wired into the build until they have answered. Specs are written after section 2, because what
the requirements can promise depends on what the tool actually detects.

## 1. Which mechanism is available?

*Assumption 1. Refuted if neither option works here — then the answer is japicmp/Revapi against
the published artifact, and sections 3-5 are rewritten.*

**Correction.** 1.1 was labelled `. r`, as though the DSL could be committed on its own. It
cannot: enabling `abiValidation` puts `checkKotlinAbi` into `:lib:check`'s task graph, so a
commit that enables it without a reference dump leaves `check` red — an `@`, not a `. r`. The
DSL and the baseline therefore land together in **4.1**, and sections 1-2 produce evidence only.
This also keeps the section's own promise that nothing is wired in until it has answered.

- [x] 1.1 `. d` Try Kotlin's built-in `kotlin { abiValidation { } }` on `:lib`. It needs no new
      dependency, so it wins if it exists in 2.4.10
- [x] 1.2 `. d` ~~If it does not, apply `org.jetbrains.kotlinx:binary-compatibility-validator`
      0.18.1 and run its dump task~~ — **not needed**, 1.1 answered yes. Recorded rather than
      deleted: the fallback was the reason assumption 1 was safe to hold
- [x] 1.3 `. d` Record which mechanism is in play and why

## 2. Does it catch the silent case?

*Assumption 2, the one worth having. Refuted if a mid-signature parameter insertion produces no
dump change — then the tool detects removals only, the requirements say exactly that, and the
parameter-shift hazard stays a documentation problem.*

Each of 2.1-2.4 is a working-tree measurement that leaves no committed change, so none of them
is a `. r`; the section's single artefact is 2.5's write-up. Relabelled `. d` to match.

- [x] 2.1 `. d` Take a dump of the current API as the reference
- [x] 2.2 `. d` Insert a `Boolean = false` parameter into the middle of one generated
      function's signature — by hand, in a throwaway working-tree edit, since the generator
      would have to be changed to do it properly — and re-dump
- [x] 2.3 `. d` Diff the two dumps. Record whether the insertion is visible, and what it looks
      like. Revert the edit
- [x] 2.4 `. d` Repeat for a removal: delete an enum entry, re-dump, diff, revert. This is the
      case that is expected to work, so it is the control
- [x] 2.5 `. d` Record both outcomes

## 3. Write the specs

- [x] 3.1 `. d` Write `specs/api-change-detection/spec.md` against what sections 1-2 measured —
      Verified where measured, Assumed with a falsifier where not

## 4. Wire it in

- [x] 4.1 `^ F` Commit the API baseline — **with** the DSL that produces it, per the correction
      in section 1; separately it would leave `check` red
- [x] 4.2 `! F` Add the check to `ci.yml`. It belongs with the drift job conceptually: both
      compare a committed artefact against what the build regenerates
- [ ] 4.3 `^ F` Prove it fails — change the API on a scratch branch and watch the job go red.
      A gate nobody has seen fail is not known to be a gate. Note that the PR must be
      **mergeable** for `pull_request` workflows to run at all; see the `kdaisyui-release`
      skill
- [x] 4.4 `. d` Add a `just` recipe to re-dump the baseline, alongside `just generate`

## 5. Make the migration note a rule

- [ ] 5.1 `. d` Record in `AGENTS.md`: a detected breaking API change requires a **How to
      migrate** entry in `README.md` before the release that ships it. `README.md` already has
      the 0.2.0 one to serve as the shape
- [ ] 5.2 `. d` Note in the `kdaisyui-release` skill that the API check and the migration note
      are part of what "ready to tag" means
