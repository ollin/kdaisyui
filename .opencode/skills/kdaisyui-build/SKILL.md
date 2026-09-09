---
name: kdaisyui-build
description: >-
  Changing kdaisyui's Gradle build - the convention plugin, buildSrc, settings, toolchains,
  compiler flags, or anything that makes `gradle check --warning-mode all` say something.
  Read this before adding a plugin, touching buildSrc, or explaining a deprecation warning.
  NOT for what the build produces (see kdaisyui-release) or what it tests (kdaisyui-testing).
---

# kdaisyui — The Gradle Build

## The standard: zero problems, and that is checkable

```
gradle check --warning-mode all
```

must end with `Problems: 0`. It does today, so **any warning you see is one you introduced**,
and there is no pre-existing noise to hide in. Without `--warning-mode all` Gradle collapses
deprecations into one line at the bottom of the build, which is easy to read past.

The Gradle MCP server reports the same thing as a `Problems:` count with stable ids, so
`query_build(kind="PROBLEMS")` is how you find out *what* rather than *that*.

Use `--rerun-tasks` when the count matters. An up-to-date task emits no warnings, so a cached
build can report zero while the underlying problem is still there.

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

## Adding a plugin

1. Version into `gradle/libs.versions.toml` `[versions]`, id into `[plugins]`. Nothing anywhere
   else — see `AGENTS.md`.
2. Name the version key after **what it versions**. A `pitest` leaf next to a `pitest-junit5`
   node forces `libs.versions.pitest.asProvider()` at every call site; `pitest-plugin` keeps
   both as leaves under one prefix.
3. Do not pin a tool's core version alongside its Gradle plugin unless something forces you to.
   Two numbers that must agree will eventually disagree, and nothing will notice.
4. Re-run `gradle check --warning-mode all --rerun-tasks` and confirm it is still `Problems: 0`.
