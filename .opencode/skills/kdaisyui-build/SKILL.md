---
name: kdaisyui-build
description: >-
  Changing kdaisyui's Gradle build - the convention plugin, buildSrc, settings, toolchains,
  compiler flags, or anything that makes `gradle check --warning-mode all` say something.
  Read this before adding a plugin, touching buildSrc, or explaining a deprecation warning.
  NOT for what the build produces (see kdaisyui-release) or what it tests (kdaisyui-testing).
---

# kdaisyui — The Gradle Build

## The standard: no Gradle problem of our own, and CI checks it

```
./gradlew check -x :e2e-tests:test && ./gradlew :build-checks:checkGradleProblems
```

The second call reads `build/reports/problems/problems-report.html`, the report the first one
wrote, and fails, naming each, on any problem `gradle/allowed-gradle-problems.txt` does not accept.
CI's `unit-tests` job runs exactly this. It has to be a second invocation: Gradle writes the report
when a build ends, so no task can read its own build's.

**Since 2026-10-31 the build causes none.** The only accepted problems are the deprecated
`Configuration.setVisible` that Kover 0.9.11 and the PIT plugin 1.19.0 call, each the latest release
that day, each with its reason in the allowlist. A problem the build itself causes is attributed to
no plugin, so it can never be listed: fix it. The last one was the E2E build's `which` lookup of
Chromium through `ProcessBuilder` at configuration time; `providers.exec` replaced it. When a
plugin release drops `setVisible`, delete its line.

An allowlist line names a problem by the anchor of its documentation link,
`deprecate-visible-property`, not by its own name: since Gradle 9.9 that name is the generic
`Method usage` for every deprecated method, so allowing it would accept any method a plugin
calls (`read-the-gradle-9-9-problems-report`).

The report caps its listed diagnostics and folds the rest into `summaries`, which carry no
location and no link, so a summary matches a listed problem by name. Up to Gradle 9.8 they sat
under the data's `problemsReport` key; since 9.9 in a script of their own, `problems-summary`. The check counts a summary as the listed problem it repeats, and one whose id nothing
lists as the build's own.

Until then this section said `gradle check --warning-mode all` "must end with `Problems: 0`", and it
had stopped being true without anyone noticing: a sentence is not a check.

Use `--rerun-tasks` when you hunt for a warning by eye. An up-to-date task emits no warnings, so a
cached build can show none while the problem is still there.

### IntelliJ's sync warning is not ours — do not chase it

Syncing the project in IntelliJ ends with "Deprecated Gradle features were used in this
build, making it incompatible with Gradle 10." The repository is clean; the warning is four
`Project.getProperties` deprecations raised by **IntelliJ's own injected init scripts**.

Confirmed 2026-09-09, and worth stating so the next reader stops here rather than searching:

- `prepareKotlinBuildScriptModel --warning-mode all` run from the command line, i.e. the same
  task without the IDE's init scripts, reports `Problems: 0`.
- `getProperties` / `project.properties` appear nowhere in any `.gradle.kts` or in `buildSrc`.
- Gradle attached **no source location** to any of the four diagnostics, which is what an
  init script looks like in `build/reports/problems/problems-report.html`.

It never reaches CI, which does not run the IDE sync path. To re-verify after an IDEA or
Gradle upgrade, put `systemProp.org.gradle.deprecation.trace=true` in `gradle.properties`,
re-sync, and read the stack traces — then take the line out again, since that file
deliberately holds only the project version.

The general lesson is the reason this subsection exists: `check` and `help` do not exercise
every configuration path. A claim of "no warnings anywhere" needs the path it was measured on
named, or it is broader than the measurement.

## Where build logic lives

| File | What it decides |
|---|---|
| `settings.gradle.kts` | module list, the Kotlin plugin version (parsed out of the catalog TOML by hand, because `libs.*` does not exist yet in `pluginManagement`), the foojay toolchain resolver |
| `buildSrc/settings.gradle.kts` | buildSrc's *own* catalog import and *own* toolchain resolver — see below |
| `buildSrc/.../kdaisyui.kotlin-library-conventions.gradle.kts` | the JVM toolchain, jar manifest, compiler args and test-suite wiring shared by every module |
| `build.gradle.kts` (root) | the release pipeline and the aggregated coverage gate |
| each module's `build.gradle.kts` | only what is genuinely module-specific |

## buildSrc is a separate build, and that is the trap

`buildSrc` has its own `settings.gradle.kts`, so it is a build in its own right. Almost nothing
declared in the root settings reaches it — which is why buildSrc has to import the version
catalog itself, and why the foojay toolchain resolver is applied twice.

Two rules follow, and they pull in opposite directions:

- **Settings-level configuration must be repeated in `buildSrc/settings.gradle.kts`.** It does
  not inherit.
- **A plugin repeated there must be declared WITHOUT a version.** The plugin *classpath* IS
  inherited from the root settings, so naming a version fails the build with "the plugin is
  already on the classpath with an unknown version, so compatibility cannot be checked".

So the foojay resolver appears in both files, with the version in the root one only. Adding a
settings plugin means doing the same, in that order, or the build breaks in a way whose message
does not obviously point at buildSrc.

## Toolchains: two JDKs, on purpose

`.tool-versions` pins the JDK that **runs** Gradle (25). The convention plugin pins the JDK that
**compiles** the code (`jvmToolchain(21)`), and buildSrc does the same for itself. The two
numbers differ deliberately and are not in conflict.

Because they differ, a JDK 21 has to come from somewhere. Gradle 9 deprecated *reusing an
auto-provisioned JDK in a build that declares no toolchain repository* — which is what the
foojay resolver declares. A machine whose system JDK 21 is already discoverable never sees this
warning, so it is easy to believe it is environmental and leave the declaration missing. CI
hits it.

## Gradle 10 readiness: the Kotlin DSL delegates are gone

Gradle 9.6 deprecated **every** Kotlin DSL property delegate for removal in Gradle 10 — not a
subset. The build used four of them; all are now replaced. The replacement table, from the
upgrade guide:

| Deprecated | Replacement |
|---|---|
| `val x by container.registering(T::class) { }` | `val x = container.register<T>("x") { }` |
| `val x by container.creating(T::class) { }` | `val x = container.create<T>("x") { }` |
| `val x by container.existing(T::class) { }` | `val x = container.named<T>("x") { }` |
| `val x by container.getting(T::class) { }` | `val x = container.getByName<T>("x") { }` |
| `val p: String by project` | `project.property("p")` |
| `val v: String by extra` | `extra["v"] as String` |

`getting` maps to `getByName` (eager) and `existing` maps to `named` (lazy). **Do not "improve"
a `getting` into `named` while migrating** — swapping eagerness is a behaviour change wearing a
refactoring's clothes, and a test suite configured lazily may never be realised.

Gradle's own reason for the removal is the useful one to remember: the delegate took the entity
name from the *variable* name, so renaming the variable silently retargeted the lookup or
no-opped. The replacements state the name as a string.

## Compiler flags

Set once in the convention plugin, extended per module only where a module genuinely needs it.
Two facts worth knowing before adding one:

- `-Xreturn-value-checker=full` is deliberate and shared (convention plugin).
- `-Xcontext-parameters` was removed in September 2026: context parameters are on by default at
  Kotlin language version 2.4, and the flag produced `REDUNDANT_CLI_ARG`. `ktor-integration`
  still *uses* context parameters (`Resolvable`) — the feature stayed, the flag went. If a
  Kotlin downgrade ever happens, the compiler will say so by failing, which is the intended
  signal.

An opt-in flag that has become the default is not harmless: it is a warning that trains everyone
to ignore the warning list, which is what makes the "zero problems" standard above worth having.

## The build cache, in CI

Every Gradle call in `ci.yml` passes `--build-cache`. `gradle/actions/setup-gradle` restores the
local build cache, together with the rest of Gradle's user home, in every Gradle job. `main` writes
it and a pull request only reads it, so no branch can poison what another branch restores. The
provider is `cache-provider: basic`, the MIT wrapper around `actions/cache`: Oliver decided against
the proprietary default on 2026-11-02. `gradle.properties` does not set `org.gradle.caching`,
because it holds only the version line.

So **a cached result now replaces a run**. A task that reads a file it does not declare as an input
returns a stale result in CI whenever that file changes alone. The audit of 2026-11-02 found none:

- Two clean `check -x :e2e-tests:test` runs. The second one restored compile, every unit-test task,
  the Kover reports and the ABI dump from the cache, and re-ran only jars, Kover artifacts,
  lifecycle tasks and the Docker Tailwind tasks.
- `KdaisyuiClasses`, the only cacheable task the repository writes itself, declares its classes,
  its runtime classpath and its output.
- The tests read only classpath resources or temp files they write themselves.

Keep it that way. A new `@CacheableTask` declares every file it reads. A test that reads a file
from the repository gets it onto its classpath or declares it with `inputs.file`, the way the
codegen tasks in `lib/build.gradle.kts` do. Plain `Exec` tasks, which every generator is, are not
cacheable and always run.

## Adding a plugin

1. Version into `gradle/libs.versions.toml` `[versions]`, id into `[plugins]`. Nothing anywhere
   else — see `AGENTS.md`.
2. Name the version key after **what it versions**. A `pitest` leaf next to a `pitest-junit5`
   node forces `libs.versions.pitest.asProvider()` at every call site; `pitest-plugin` keeps
   both as leaves under one prefix.
3. Do not pin a tool's core version alongside its Gradle plugin unless something forces you to.
   Two numbers that must agree will eventually disagree, and nothing will notice.
4. Re-run `gradle check --warning-mode all --rerun-tasks` and confirm it is still `Problems: 0`.
