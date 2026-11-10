## Context

See `proposal.md` for why. What shapes the how:

- `mcp_Tcr` at the repository root runs `./gradlew build` and the codegen suite, so every build
  script step is checked by a real build. CI workflow edits are not: a `.github/workflows/ci.yml`
  change is proven only by the pull request's own run.
- `.github/ci-scope/classify.sh` has its own test, `classify.test.sh`, which `mcp_Tcr` does not
  run; its step is run by hand and said out loud, as `ci-run-only-affected-jobs` did.
- `api-baseline` is a required status check of ruleset 14483746 with
  `strict_required_status_checks_policy: true`. A required check whose job no longer exists never
  reports, so the pull request that deletes the job cannot merge until the ruleset drops it.

## Goals / Non-Goals

**Goals:** every item is a deletion or a merge of duplicates; the build's observable outputs stay
the same except the one manifest attribute.

**Non-Goals:** speeding up any job by new means (build cache settings, parallelism), touching
generated output, changing a dependency version.

## Decisions

Enablers (behaviour-preserving, provable by a green build and the diff): D3, D4, D5, D6, D7, D8,
D9. Desired changes: D1 (one CI job fewer) and D2 (one manifest attribute fewer).

### D1. Delete `api-baseline`; drop it from the ruleset right before the merge

**Chosen:** delete the job; once its pull request is otherwise green, remove `api-baseline` from
the ruleset's required checks, then merge. Oliver edits the ruleset or confirms the agent doing it.

**Rejected: keep the job, made cheaper.** It would still be a second copy of a gate `check`
already runs. **Rejected: remove the required check first, in a separate step.** Between the two,
`main` would accept pull requests whose `api-baseline` failed, and the job would still exist.
**Rejected: rename `unit-tests`' step to make the API check visible.** The failure already names
`checkKotlinAbi` and the diverging declaration.

### D2. Remove `SCM-Revision`, keep the other manifest attributes

**Chosen:** delete the attribute and `GitHashValueSource`. `Implementation-Version`,
`Kotlin-Version` and `Created-By` change only when a version changes, which also changes the jar
for a reason.

**Rejected: stamp the hash only on a release build.** A branch in the convention plugin to keep an
attribute nobody reads (A2). The released version is the tag, which names the commit.
**Rejected: also drop `Created-By`.** It is stable between commits, so it costs no cache hit.

### D3. One `generate` task in `:lib`

**Chosen:** `tasks.register("generate")` in the `codegen` group, depending on the five generator
tasks. `just generate` and the drift job call `:lib:generate`.

**Rejected: let CI call `just generate`.** CI would need `just`, and the recipe's `git status`
output is for a person. **Rejected: a root-level task.** All five generators live in `:lib`; a root
task would reach into a subproject for no gain.

### D4. One task class for both submodule checkouts

**Chosen:** `CheckoutSubmoduleTag` with `targetTag` and `submoduleDir`, registered twice under the
existing names `checkoutDaisyuiTag` and `checkoutHeroiconsTag`, so `just sync-daisyui`,
`just sync-heroicons` and every `dependsOn` stay as they are.

**Rejected: one task checking out both.** `generateHeroicons` would then fetch DaisyUI's tags
too, and each `sync-*` recipe would move the other submodule.

### D5. Repositories declared once, in `settings.gradle.kts`

**Chosen:** `dependencyResolutionManagement { repositories { mavenCentral() } }` in the root
settings, and the four project-level `repositories` blocks of the root build removed (root,
`lib`, `ktor-integration`, `e2e-tests`) plus the one in the convention plugin. No
`repositoriesMode`: the default lets a plugin that adds a project repository keep working.

**Rejected: `FAIL_ON_PROJECT_REPOS`.** It would guard against a re-added block, but fails the
build if Kover, PIT or JReleaser add a repository of their own, which nobody has checked.
**Out of reach:** `buildSrc/build.gradle.kts` and `gradle-plugin/settings.gradle.kts` are separate
builds with their own settings; they keep theirs.

### D6. `jvmToolchain(21)` alone

**Chosen:** drop `java { toolchain { … } }` and `jvmTarget.set(JVM_21)` from the convention plugin
and from `buildSrc/build.gradle.kts`. The Kotlin plugin's `jvmToolchain` sets the Java toolchain,
and the JVM target follows the toolchain.

**Rejected: keep `jvmTarget` as documentation.** A second place to change when the toolchain moves,
and a mismatch between them is a build error that teaches nothing.

### D7. No Kotlin version in `settings.gradle.kts`, if A3 holds

**Chosen:** delete the `pluginManagement` catalog parser and its `plugins` block; keep
`includeBuild("gradle-plugin")`. Task 1.1 tries it first. If A3 is refuted, the block stays and
this decision is recorded as rejected with the error that refuted it.

### D8. Delete only the false sentences

**Chosen:** in the root build's Kover block, the paragraph about the staged rollout and "nothing
fails on a shortfall"; in `lib/build.gradle.kts`'s PIT block, the paragraph saying the scope is one
class with no threshold. The reasoning around both stays.

### D9. Leftovers

**Chosen:** `git rm -r .codeseeker`, its pattern in `classify.sh` and its fixture in
`classify.test.sh`; remove the empty `codegen-kotlin/` and the untracked `.playwright-mcp/` and
`tmp/` from the working tree (untracked, so no commit carries them). **Rejected: keep the classifier
pattern.** A rule for a path that no longer exists is a test of nothing.

## Risks / Trade-offs

- [The ruleset edit is forgotten] → the pull request stays pending, which is loud; task 4.2 names it.
- [A3 is wrong] → task 1.1 runs first; the block stays and D7 is rejected.
- [A plugin relied on a project repository] → `./gradlew build` under TCR fails at once; the
  repository goes back where it was needed, with a comment saying why.
- [Removing `SCM-Revision` hides a jar's commit] → a released jar's version is its tag; a local
  snapshot was never published.

## Migration Plan

Merge with the ruleset edit (D1). Rollback: revert the merge commit and add `api-baseline` back to
the required checks.
