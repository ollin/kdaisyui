# Findings

## F1 - The working implementation lives in another repository

- **What happened:** at proposal, 2026-11-07. The Deploy-Radar's `compileTailwind` in
  fwb-application is the implementation this change generalises; it was read from the worktree
  `fwb-application--work-on--deployment-risk-tool`, since the `main` checkout did not yet carry
  it. The proposal cites it by path and line; the code itself is the employer's and is not copied.
- **Cause:** the proposal arrived before the Deploy-Radar's branch reached fwb-application's main.
- **Cost:** none; one extra directory read.

## F2 - The review found the task bundled, the citations off, and the pairing risk unstated

- **What happened:** the review of 2026-11-07 (`fwb-openspec-reviewer`): task 4.1 held the
  task class, the copy-in, the one-shot run, the copy-out and a container test in one commit,
  and no task wired the half-credential check; the cited line range of the Deploy-Radar's
  script was off by fourteen lines (128-174, not 114-160); five references to the deleted
  `compileTailwind` had no task; Testcontainers' libraries on a plugin classpath were an
  unlisted risk, since the Deploy-Radar carries it on a `buildscript` classpath instead; a
  newer plugin paired with an older library would compile against versions the components
  were not generated against; the no-CLI and mirror scenarios were not observable here; and
  `<name>` in the mount points was undefined.
- **Cause:** the tasks were written from the design's shape, not sized per commit; the line
  range was read from a `sed` offset; the risk list was written from the daemon's side only.
- **Cost:** about 25 minutes; block 4 is four steps, the version-mismatch warning is task
  2.3, the stale references task 5.3, the mount points are indexed, the spec is three
  requirements with scenarios the tests observe, and A11 records the PIT exclusion decision.

## F3 - Nine steps planned `^` that count above eight lines

- **What happened:** 2026-11-08, before the first code step, against the planning rule added the
  same day (plan a marker from counted lines). Tasks 2.1, 2.2, 2.3, 3.1, 3.2, 3.4, 4.1, 4.4 and
  5.1 each hold a class or a configuration block with its test, estimated at ten to thirty counted
  lines. They are `!` now; the remaining `^` steps (1.1, 3.3, 3.5, 4.3) stay at or under eight.
- **Cause:** the plan of 2026-11-07 predates the rule and sized by behaviour.
- **Cost:** five minutes; one bookkeeping commit.

## F4 - The plugin loads in a consumer with Testcontainers on its classpath (A2)

- **What happened:** task 1.1, 2026-11-08. `:lib:publishToMavenLocal` and the plugin's
  `publishToMavenLocal`, both with `-Pversion=0.0.0-probe` so no released version in `~/.m2` was
  overwritten; a consumer in `/tmp/opencode/` with Kotlin 2.4.10 applied the plugin from
  `mavenLocal()`. `kdaisyuiClasses` wrote 27 lines for one `daisyButton`, and a build-script
  task loading `org.testcontainers.DockerClientFactory` printed `Docker available = true`.
  No classpath conflict with Gradle 9.8.2 or the Kotlin plugin. The probe artifacts were removed
  from `~/.m2` afterwards.
- **Cause:** record only; A9's fallback, a second artifact, is not needed.
- **Cost:** none.

## F5 - A TCR revert caused by the test, not the code

- **What happened:** task 2.1, 2026-11-08. The first run reverted the production step on a
  `NullPointerException` in the test's initialiser: inside `Properties().apply { … }`,
  `javaClass` is `Properties`'s class, loaded by the bootstrap loader, whose `classLoader` is
  `null`. The test never reached its assertion. Fixed in the test alone
  (`VersionsResourceTest::class.java.getResourceAsStream`), and the same production step went
  green unchanged - a stop that TCR reports as red, since the harness raised it after compiling.
- **Cause:** a receiver mistake in Kotlin's scope functions; the test was written with the code
  in one run, so its own defect looked like the code's.
- **Cost:** one revert, about three minutes.

## F6 - `mcp_Tcr` reverts on a test that does not compile

- **What happened:** task 3.1, 2026-11-08. The test wrote `ProjectBuilder.builder().build().apply { … }`;
  on a `Project`, the Kotlin DSL's `Project.apply(…)` wins over Kotlin's scope function, so the
  test did not compile. `mcp_Tcr` reverted the production code - which compiled and was right -
  and kept the test. Fixed in the test (`also`), the production code restored from the tool's
  rescue copy and the one tracked edit rewritten unchanged; green. Together with F5, two of the
  change's first eight production steps were reverted for a defect in the step's test.
- **Cause:** the tool's guard compiles production and test sources in one Gradle invocation
  (`build`), so a test compile error reaches it as a red build, not as the stop the TCR skill
  describes ("compile error: STOP, nothing reverted").
- **Cost:** about five minutes and one revert, plus the rescue-copy restore.

## F7 - Tasks 3.2 and 3.3 could not be two green steps

- **What happened:** 2026-11-08, before task 3.2. "`_auth` when both halves are given" holds the
  branch "only one half", whose behaviour is task 3.3. A 3.2 commit without it would leave that
  branch unexecuted, and the plugin's 100% branch gate would fail the step. Merged into one task.
- **Cause:** the plan split a function by its outputs, not by its branches.
- **Cost:** two minutes; one bookkeeping commit.

## F8 - "Register the task only when an entry is set" needs `afterEvaluate`

- **What happened:** 2026-11-08, before task 4.4. A build sets the extension's entry after the
  plugin is applied, so registering conditionally means `afterEvaluate`, and a `ProjectBuilder`
  test of it means internal API to evaluate the project. Registering always and wiring lazily is
  the idiom; a build that runs the task without an entry gets Gradle's own message naming
  `entry`. Task 4.4 changed accordingly; no spec requirement names the condition.
- **Cause:** the plan described the user's view ("absent when unused"), not Gradle's lifecycle.
- **Cost:** three minutes; one bookkeeping commit.

## F9 - Task 4.4 reverted once: a task's output cannot be mapped before it ran

- **What happened:** 2026-11-08. The first wiring derived the stylesheet task's class-list
  directory from `kdaisyuiClasses`' output (`flatMap { it.output }.map { … parent }`); Gradle
  refused to query that mapped value before `kdaisyuiClasses` completed, and the test asking for
  the configured directory failed. Reverted; the smaller step names the directory both tasks use,
  `build/kdaisyui`, and adds an explicit `dependsOn`. Green; a `^ r` then named the directory once.
- **Cause:** `map` on a task output provider is only valid at execution, which a configuration
  test never reaches.
- **Cost:** one revert, about four minutes.
