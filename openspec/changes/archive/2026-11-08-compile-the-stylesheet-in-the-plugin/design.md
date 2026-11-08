## Context

See proposal.md, Why. The plugin is an included build with its own 100% Kover gate and PIT
gate, tested with `ProjectBuilder`; it has no dependency today. It reads the component map from
the `kdaisyui` jar on the consumer's runtime classpath. The example app's compile is two `Exec`
tasks around a Dockerfile that bakes `@tailwindcss/cli`, `tailwindcss` and `daisyui` at the
versions `gradle/libs.versions.toml` and `.tool-versions` give.

## Goals / Non-Goals

**Goals:**

- One task any consumer applies, with the toolchain versions supplied by the plugin.
- Works with a Docker daemon and nothing else on the host, behind a mirror, with no internet.
- The example app is the first consumer and loses its own compile.

**Non-Goals:**

- Watch mode or incremental compiles. Gradle's up-to-date check is the incrementality.
- Supporting a consumer's own Tailwind plugins beyond DaisyUI. The entry CSS is theirs; a
  further `@plugin` needs its package installed, which this task does not do. Said in the docs.

## Decisions

### 1. Testcontainers, not the docker CLI, docker-java alone, or the Engine API by hand

**Chosen:** `org.testcontainers:testcontainers`, a `GenericContainer` of the Node image with
the inputs copied in, a one-shot startup strategy, and `copyFileFromContainer` for the output.
The Deploy-Radar proved the shape (A1).

**Rejected: the docker CLI** (today's `Exec` tasks). A CLI the host must carry is the
constraint this change exists to remove.

**Rejected: docker-java directly.** It is what Testcontainers wraps; using it alone saves one
layer and costs the copy, wait and log plumbing Testcontainers has already written and tested.

**Rejected: Docker's HTTP API over the socket by hand.** Nothing to recommend it but zero
dependencies, and the tar framing of the copy endpoints alone is more code than the task.

### 2. Copy in, copy out; no bind mount

A bind mount names a path on the daemon's host. In the Deploy-Radar's CI the daemon is not on
the job's machine, so the mount would be empty (its F14). Copying costs a tar of a few source
directories per compile and works everywhere a daemon answers. Today's `--user` workaround for
root-owned output goes with the mounts: a copied-out file is written by the Gradle process.

### 3. Packages are installed per run, with npm's cache in a named volume

**Chosen:** the container runs `npm install` of the three pinned packages, then the compile.
npm's cache directory is a named volume, `kdaisyui-npm-cache`, mounted through the container
create command, which a remote daemon serves like a local one; `--prefer-offline` makes the
second run a cache hit.

**Rejected: baking an image as today's Dockerfile does.** Testcontainers can build one through
the daemon (`ImageFromDockerfile`), so the CLI is not the obstacle. The credential is: an
`.npmrc` with `_auth` inside an image layer is a secret in an artifact the daemon keeps. Per-run
install copies the `.npmrc` into a container that is removed.

**Rejected: no cache.** Three packages per compile, an estimated 20 s against the 70 ms of
today's baked image (task 5.4 measures both), on every source change. A8 puts the volume to
Oliver; without it, the slower loop is the fallback, not a blocker. The task is a
`@CacheableTask` like `kdaisyuiClasses`, so with `--build-cache` an unchanged input set skips
the container altogether.

### 4. The versions come from the plugin jar, written at its build

**Chosen:** `kdaisyui-versions.properties` in the plugin's resources, expanded by
`processResources` from a version catalog the included build creates from
`../gradle/libs.versions.toml` and from `.tool-versions`. A `KdaisyuiVersions` object reads it.
The versions are then one place, as the root `AGENTS.md` demands.

The plugin and the library are released together under one version number
(`gradle.properties`), so a matched pair is the versions the components were generated
against. A consumer can still pair a newer plugin with an older library; the plugin already
opens the library jar for its component map, and warns when that jar's version is not its own.

**Rejected: the consumer passes them.** That is what the Deploy-Radar does, and what the
proposal exists to stop.

**Rejected: hard-coded in the plugin source.** A second place, bumped by hand, and
`generated-sources-drift` cannot see it.

### 5. The registry and its credential are Gradle providers

`npmRegistry: Property<String>` defaulting to `https://registry.npmjs.org/`; `npmUser` and
`npmPassword` as `Property<String>` the consumer wires to an environment variable or a Gradle
property. A pure function composes the `.npmrc` text: the registry line, and the `_auth` line
only when both halves of the credential are present; a half credential fails the task naming
the missing half. Pure, so it is unit-tested without Docker and its mutants die cheaply.

### 6. The mount points are fixed and documented

The entry lands at `/deps/project/css/app.css`, each source directory under
`/deps/project/sources/<index>-<last segment>` (`0-kotlin` for the first `src/main/kotlin`),
so two directories that end alike cannot collide, and the `kdaisyuiClasses` output directory at
`/deps/project/kdaisyui/`. The entry therefore writes `@source "../sources"`
and `@source "../kdaisyui/kdaisyui-classes.txt"`. Fixed paths are what let the README show one entry file
for everyone; a consumer compiling by hand uses repository paths instead, and the README says
which is which.

### 7. The container run is one thin method; everything around it is pure

`StylesheetCompile` holds what to run: the image, the `.npmrc`, the command line, the files to
copy and where. `NodeContainerRun.compile(StylesheetCompile)` is the only code that touches
Testcontainers, a dozen lines with no branch. The pure parts are tested without Docker; the
thin method by one test that compiles a two-line entry in a real container and reads the
output. A6 checks that the split leaves the gates reachable.

### 8. Enablers and the desired change

Enablers: the versions resource and `KdaisyuiVersions` (no behaviour until something reads
them); the `.npmrc` and command composition; the extension with defaults. Desired changes: the
task, its registration, the example app's switch, the deletions.

## Risks / Trade-offs

- [Testcontainers' first start pulls the Node image] → the image is configurable, and a mirror
  is the documented answer; the pull happens once per daemon.
- [PIT runs the thin method's mutants against a real container] → `NodeContainerRun` is
  excluded from PIT's target classes with its reason in `gradle-plugin/build.gradle.kts`, as
  `lib/build.gradle.kts` does for the interface-default bridges; its one real-container test
  keeps Kover at 100% (A11, task 1.3 confirms the split leaves nothing else uncovered).
- [The real-container test needs Docker and the network] → so does `check` today, for the
  example app's compile; no new host requirement. Offline, the test fails rather than skips,
  because a skipped test would hide a hole in the 100% gate.
- [Testcontainers' libraries conflict with another plugin's on the consumer's classpath] →
  task 1.1 applies the plugin from `mavenLocal()` in a scratch consumer; A9's alternative is a
  second artifact for the stylesheet task alone.
- [A consumer's own `@plugin` beyond DaisyUI] → not installed; the README says that a build
  with a further Tailwind plugin keeps its own compile.
- [The named volume accumulates] → it holds one npm cache; `docker volume rm` is named in the
  README.
