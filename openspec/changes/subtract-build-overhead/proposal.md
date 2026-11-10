## Why

The build and CI carry things that cost reading time, runner time or cache hits and buy nothing
any more: a CI job that repeats a check `unit-tests` already runs, a jar that changes on every
commit, the same five generator task names in two places, settings duplicated across scripts,
two comments that say the coverage and mutation gates do not fail when both do, and directories
nothing reads. Every item here is a removal; none adds a mechanism.

Measured for this proposal on 2026-11-10:

- `./gradlew check -x :e2e-tests:test --dry-run -q | grep Abi` lists `:lib:checkKotlinAbi`, so
  `unit-tests` already runs what the `api-baseline` job runs. The job took 34 s of runner time on
  run 42891959411 (job 130095190533, `started_at` to `completed_at`), off the critical path.
- The main ruleset (id 14483746, read through `repository_ruleset_read`
  `get_rules_for_branch main`) lists `api-baseline` among its eight required status checks, so
  deleting the job without editing the ruleset leaves every pull request pending forever.
- `grep -rn generateHeroiconTests` outside `build/`, `node_modules/` and the archive: the full
  list of five generator tasks is spelled out in `justfile:20` and `.github/workflows/ci.yml:129`.
- `grep -rln "mavenCentral()" --include=*.kts`: seven build scripts declare it; five of them belong
  to the root build (`build.gradle.kts`, `lib`, `ktor-integration`, `e2e-tests`, and the convention
  plugin), two to their own builds (`buildSrc`, `gradle-plugin/settings.gradle.kts`).

## What Changes

- **Delete the `api-baseline` CI job.** `checkKotlinAbi` keeps running in `unit-tests` through
  `check`. Its required status check is removed from ruleset 14483746 in the same step that merges
  this, by Oliver or with his confirmation.
- **Drop the git hash from every jar manifest** (`SCM-Revision`, and the `GitHashValueSource` that
  computes it), so an unchanged library builds an identical jar on two commits.
- **One `generate` task** in `:lib` that depends on the five generators; `justfile` and the drift
  job call it instead of listing them. **One checkout task class** replaces the identical
  `CheckoutDaisyuiTag` and `CheckoutHeroiconsTag`.
- **Build-script duplication removed:** repositories declared once in `settings.gradle.kts`; the
  `java { toolchain }` block and `jvmTarget` that repeat `jvmToolchain(21)`; the catalog regex in
  `settings.gradle.kts` if `buildSrc` already supplies the Kotlin plugin (A3).
- **Two false comments removed:** the root Kover block says nothing fails on a shortfall "until"
  the gate is added, and the PIT block says the scope is one class with no threshold. Both gates
  exist and fail at 100.
- **Leftovers deleted:** `.codeseeker/` (committed, and ignored by `.gitignore` since), together
  with its pattern in `.github/ci-scope/classify.sh` and its test; the empty
  `codegen-kotlin/codegen-kotlin/`; the untracked `.playwright-mcp/` logs and the empty `tmp/`.

**Additive option considered and rejected:** keeping `api-baseline` and making it faster (no
Gradle setup). It would stay a second copy of a gate `check` already enforces.

## Deliberately out of scope

Each is a decision of its own, raised in the analysis that led here:

- **Removing the generated component tests** (135 files, ~26,000 lines). Needs a measurement of
  their compile and test time first, and touches the 100% coverage rule. Its own change.
- **One E2E style instead of Kotest plus Cucumber.** A migration, not a removal of dead weight.
- **Pruning `docs/upgrading.md` and shortening the README's CSS section.** User documentation,
  reviewed differently; its own `docs/` change.
- **Compiling the stylesheet without Docker.** Conflicts with the air-gapped consumer the plugin
  was built for. What this change does not do either: write down why the standalone binary lost,
  which `compile-the-stylesheet-in-the-plugin` promised in its proposal and its `design.md` lacks.
- **Shortening rationale comments in `ci.yml` and the build files** beyond the two that are false.

**Not breaking.** No published API changes. The jar manifest loses one attribute, `SCM-Revision`,
which no code in this repository reads (A2).

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | `check` runs `:lib:checkKotlinAbi`, so `unit-tests` fails on an undeclared API change without `api-baseline` | dry run | verified at proposal: `./gradlew check -x :e2e-tests:test --dry-run` lists `:lib:internalDumpKotlinAbi` and `:lib:checkKotlinAbi` |
| A2 | Nothing reads `SCM-Revision` | grep | verified at proposal: the only hits are its definition in the convention plugin |
| A3 | The `pluginManagement` Kotlin version in `settings.gradle.kts` is redundant, because `buildSrc` puts the Kotlin Gradle plugin on every project's classpath | build | **assumed**; *wrong if* any module fails to apply `kotlin("jvm")` or `kotlin("plugin.serialization")` without it. Task 1.1 |
| A4 | A git hash in the manifest makes `:lib:jar` differ on every commit, and so costs cache hits downstream | build | **assumed**; *wrong if* two commits that touch only a comment produce byte-identical `lib/build/libs/*.jar` today. Task 1.2 measures it; the removal is justified even if it is wrong, but the proposal would then claim no speed gain |
| A5 | Repositories in `dependencyResolutionManagement` serve every project including the root's Kover toolchain, and no plugin adds a project repository that `PREFER_SETTINGS` would hide | build | **assumed**; *wrong if* `./gradlew check` fails to resolve an artifact after the move. Task 1.3 |

## Capabilities

### New Capabilities

<!-- none -->

### Modified Capabilities

- `api-change-detection`: "A diverging API fails the build" names the job that runs the check in
  CI; it becomes `unit-tests` alone.
- `ci-job-selection`: two scenarios name `api-baseline` or count six jobs.

## Impact

- **Edited**: `.github/workflows/ci.yml`, `justfile`, `settings.gradle.kts`, `build.gradle.kts`,
  `lib/build.gradle.kts`, `ktor-integration/build.gradle.kts`, `e2e-tests/build.gradle.kts`,
  `buildSrc/src/main/kotlin/kdaisyui.kotlin-library-conventions.gradle.kts`,
  `buildSrc/build.gradle.kts`, `.github/ci-scope/classify.sh`, `.github/ci-scope/classify.test.sh`.
- **Edited where they name the job** (`grep -rn api-baseline`, 2026-11-10): `AGENTS.md:323`,
  `docs/upgrading.md:70`, `.opencode/skills/kdaisyui-release/SKILL.md:98,164`,
  `kdaisyui-testing/SKILL.md:186`, `kdaisyui-daisyui-upgrade/SKILL.md:6,25,103`. The hits in
  `openspec/specs/daisyui-component-coverage` and `generated-sources` are dated observations of
  past runs and stay true as written.
- **Deleted**: `.codeseeker/`, `codegen-kotlin/`.
- **Ruleset 14483746**: `api-baseline` leaves the required status checks.
- No published API, no generated output, no dependency version changes.
