# Tasks

Ordered by uncertainty: section 1 settles A3, A4 and A5, and the evidence the removal of
`api-baseline` rests on, before anything is built on them. Every build-script step runs under
`mcp_Tcr` at the repository root. `.github/workflows/ci.yml` is proven only by this pull request's
own run, and `classify.sh` by its own test, run by hand and said out loud.

No production Kotlin under a PIT or Kover scope changes, so the mutation gate has nothing new to
measure; the pull request says so.

**Estimate, frozen 2026-11-10 at proposal, raw and uncorrected:** 210 minutes developer-only;
forecast `1h 50min (3h 30min DevOnly)` (ratio 0.52 over 177 runs).

| Section | Minutes |
|---|---|
| 1. Open assumptions | 45 |
| 2. The CI job | 25 |
| 3. Build scripts | 70 |
| 4. Leftovers and the texts that name the job | 30 |
| 5. Gates, pull request, ruleset, archive | 40, plus CI waits logged as `machine` |

## 1. Open assumptions

- [x] 1.1 `. r (internal)` (A3) Delete the catalog parser and the `plugins` block from
  `pluginManagement` in `settings.gradle.kts`, keeping `includeBuild("gradle-plugin")`. If TCR
  reverts it, record the error here, mark D7 rejected in `design.md`, and go on.
  A3 verified: TCR green (`./gradlew build` and the codegen suite) without the block.
- [x] 1.2 `. d (internal)` (A4) On a scratch worktree, build `:lib:jar` on two commits that differ
  only in a comment and compare the jars' SHA-256; repeat with `SCM-Revision` removed. Record both
  results here. A refutation drops the speed claim from the proposal, not the removal.
  A4 verified, 2026-11-10, detached worktree at `2fb556a`: an empty commit changed
  `kdaisyui-0.11.0.jar` from `e12da469…` to `5a027271…`. `check -x :e2e-tests:test` after a
  second empty commit executed, beyond the ten tasks an idle rerun executes, the jars of `lib`,
  `ktor-integration`, `doc-snippets`, `build-checks` and `example-app`, `:ktor-integration:test`,
  its Kover artifact, and `:example-app:kdaisyuiClasses` and `kdaisyuiThemes`. A real but small
  cost; the repeat without `SCM-Revision` is task 3.3's check.
- [x] 1.3 `. r (internal)` (A5) `dependencyResolutionManagement { repositories { mavenCentral() } }`
  in `settings.gradle.kts`; delete the `repositories` blocks of `build.gradle.kts`,
  `lib/build.gradle.kts`, `ktor-integration/build.gradle.kts`, `e2e-tests/build.gradle.kts` and the
  convention plugin.
  A5 verified: TCR green. The Gradle problems check runs in task 5.1.
- [x] 1.4 `. d (internal)` (api-change-detection) Add a public function to `lib`'s core without
  re-dumping, run `./gradlew check -x :e2e-tests:test`, record that it fails at
  `:lib:checkKotlinAbi` naming the declaration, then discard the probe edit. This is the command
  `unit-tests` runs.
  Verified 2026-11-10: `fun apiProbe(): Int = 1` in `ClassNames.kt` failed the build. Without
  `--continue` Gradle stopped at `:koverVerify` first (the probe had no test); with it,
  `:lib:checkKotlinAbi` failed too and printed `+ public static final fun apiProbe ()I`. Either
  way `unit-tests` goes red, and a tested API change reaches the ABI failure directly.

## 2. The CI job

- [x] 2.1 `! F (internal)` Delete the `api-baseline` job from `.github/workflows/ci.yml`, and the
  `mutation-tests` comment's reference to it (line 314).

## 3. Build scripts

- [x] 3.1 `! r (internal)` One `CheckoutSubmoduleTag` class in `lib/build.gradle.kts`, registered
  as `checkoutDaisyuiTag` and `checkoutHeroiconsTag` (D4).
- [x] 3.2 `! F (internal)` A `:lib:generate` task depending on the five generators; `justfile:20`
  and the drift job's *Regenerate* step call it (D3). Checked by `./gradlew :lib:generate --dry-run`
  listing all five.
  The dry run listed all five; `just generate` then ran them and left `lib/generated`,
  `docs/reference` and both `llms` files unchanged.
- [x] 3.3 `! F (internal)` Remove `SCM-Revision` and `GitHashValueSource` from the convention
  plugin (D2).
  A4's repeat: after an empty commit `:lib:jar` stayed `UP-TO-DATE` and its SHA-256 unchanged
  (`6c03eb3e…`); the manifest carries no `SCM-Revision`.
- [x] 3.4 `. r (internal)` `jvmToolchain(21)` alone: drop `java { toolchain }` and `jvmTarget` from
  the convention plugin and `buildSrc/build.gradle.kts` (D6).
  Class files of `lib` and `ktor-integration` still carry major version 65 (Java 21).
- [x] 3.5 `. d (internal)` Delete the two false comments: the staged-rollout paragraph in the root
  Kover block and the one-class/no-threshold paragraph in `lib`'s PIT block (D8).

## 4. Leftovers and the texts that name the job

- [x] 4.1 `^ F (internal)` `git rm -r .codeseeker`; drop its pattern from `.github/ci-scope/classify.sh`
  and its fixture from `classify.test.sh`; run `bash .github/ci-scope/classify.test.sh` by hand.
- [ ] 4.2 `. d (internal)` `api-baseline` out of `AGENTS.md:323`, `docs/upgrading.md:70`, and the
  skills `kdaisyui-release` (98, 164), `kdaisyui-testing` (186), `kdaisyui-daisyui-upgrade`
  (6, 25, 103); `generate` in `kdaisyui-codegen` where it lists the five tasks.
- [ ] 4.3 Remove the untracked `codegen-kotlin/`, `.playwright-mcp/` and `tmp/` from the working
  tree. No commit: none of them is tracked.

## 5. Gates, pull request, ruleset, archive

- [ ] 5.1 `pre_commit_code_health_safeguard` and the project values on CodeScene (`kdaisyui`, id 14)
  before the push; the full suite, `./gradlew check` including E2E, green on the rebased branch.
- [ ] 5.2 Archive the change on this branch (`openspec archive subtract-build-overhead`), commit.
- [ ] 5.3 Rebase onto `origin/main`, push, open the pull request through `github-mcp`
  (`chore: subtract build and CI overhead`). Wait for every job but the pending `api-baseline`.
- [ ] 5.4 Oliver removes `api-baseline` from ruleset 14483746's required checks, or confirms the
  agent doing it; then merge (merge commit, never squash) and confirm the run on `main` shows five
  test jobs.
