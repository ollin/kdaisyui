## Why

The documented way to compile a stylesheet is "the Tailwind CLI, the standalone binary, or a
container, whatever suits your build" (README, "Give those classes some CSS"), and the example
app's way is `docker build` plus `docker run` through two `Exec` tasks and a Dockerfile
(`example-app/build.gradle.kts:35-88`). That needs a docker CLI on the build host and fetches
Tailwind and DaisyUI from npmjs.org. The Deploy-Radar's CI image (fwb-application,
`redhat/ubi9/openjdk-25`) has no docker CLI, no Node and no internet, only a Docker daemon the
runner provides and an npm mirror that refuses anonymous clients (its finding F14). It rewrote
the compile in its own build script with Testcontainers, copying the inputs in and the stylesheet
out, and pinned Tailwind and DaisyUI itself (`server/build.gradle.kts:128-174`). Every consumer
behind a mirror would write those thirty lines again, and pin two versions kdaisyui already knows.
This is item 4 of its proposals (`~/ai/kdaisyui-improvements-from-deploy-radar.md`, 2026-11-07),
and the build-time half of Oliver's priority: the stylesheet must build from the application's
own infrastructure.

## What Changes

- **A task in the Gradle plugin, `kdaisyuiStylesheet`,** compiles the entry stylesheet with
  Tailwind and DaisyUI inside a Node container started through Testcontainers: the inputs are
  copied in, the stylesheet copied out, no bind mount, no docker CLI, no Node on the host. It
  depends on `kdaisyuiClasses` and copies that task's output directory in, so the entry's
  `@source` and `@import` paths are the same for every consumer.
- **Configuration through an extension,** `kdaisyui { stylesheet { … } }`: the entry CSS, the
  source directories Tailwind scans, the output, the Node image, the npm registry and its
  credential, and whether to minify. Everything but the entry has a default.
- **The plugin pins Tailwind, DaisyUI and Node.** Their versions are written into the plugin jar
  at its build from `gradle/libs.versions.toml` and `.tool-versions`, so a consumer repeats
  nothing and gets the versions the components were generated against. A build that wants to
  read them can. The plugin and the library carry one version number by decision
  (`gradle.properties`); the plugin warns when the `kdaisyui` jar it finds is another version.
- **An npm cache in a named Docker volume,** so the second compile does not download again.
- **The example app switches to the task** and loses its Dockerfile, both `Exec` tasks and the
  `--user` workaround.
- **The README's CSS section** shows the task first and keeps the manual route as the
  alternative.
- **Testcontainers becomes a dependency of the published plugin.**

### The subtractive option, stated

Keep the example app as it is and paste the Deploy-Radar's build script into the README as the
"behind a mirror" recipe. No new dependency, no extension. Rejected: the recipe is forty lines
with a base64 credential in it, pins two versions by hand that drift from the library's, and
every consumer behind a mirror copies it. What this change removes: the Dockerfile, two `Exec`
tasks and `callingUserArgs` from the example app, about 90 lines.

## Out of scope

- **Node on the build host.** The repository's promise that a clone compiles and tests with no
  Node stands; the container is where Node runs.
- **A compile without a container.** Tailwind's standalone binary and DaisyUI's `.mjs` bundles
  would avoid Node, and are downloads from GitHub and npm just the same; the design records why
  this lost.
- **The strict-CSP documentation** — `guarantee-strict-csp`. **`kdaisyui.css`** —
  `csp-safe-popover-anchoring`; this task copies whatever `kdaisyuiClasses` wrote.
- **A stable marker in the compiled stylesheet** (item 9) — a sentence in
  `document-server-rendered-patterns`.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | Testcontainers can copy files into a container before it starts, run one command to completion and copy a file out, from a Gradle task action | code | confirmed at proposal - the Deploy-Radar's `compileTailwind` does exactly this (`withCopyToContainer`, `OneShotStartupCheckStrategy`, `copyFileFromContainer`, `server/build.gradle.kts:143-174` in the worktree `fwb-application--work-on--deployment-risk-tool`); that it is green in its pipeline is Oliver's agent's board post of 2026-11-06 ("fwb-deploy-radar-1.0.4 released green"), not a file here |
| A2 | A consumer's build resolves the plugin's Testcontainers dependency from the plugin's POM and loads the plugin with it beside Gradle's own libraries | code | to check in task 1.1 - `publishToMavenLocal` of the plugin and a scratch consumer applying it from `mavenLocal()` and running `kdaisyuiClasses`; the included build substitutes the project and would hide a POM defect, and the Deploy-Radar puts Testcontainers on a `buildscript` classpath (`build.gradle.kts:21`), which proves nothing about a plugin |
| A3 | GitHub's `ubuntu-latest` gives Testcontainers a Docker daemon | code | confirmed at proposal - `.github/workflows/ci.yml:274` already compiles the example app's stylesheet in Docker in the `unit-tests` job, and the `mutation-tests` job runs on the same image; Testcontainers uses the same socket |
| A4 | The Deploy-Radar's runner gives Testcontainers a daemon with no docker CLI in the job image | external | confirmed at proposal - fwb-application, change `add-deploy-radar`, `findings.md` F14, read in the worktree named in A1 |
| A5 | The included build `gradle-plugin` can read `gradle/libs.versions.toml` and `.tool-versions` at its own build | code | confirmed at proposal - `gradle-plugin/settings.gradle.kts` already creates the `libs` catalog from `../gradle/libs.versions.toml`, and `build.gradle.kts` reads `../gradle.properties` the way `.tool-versions` will be read |
| A6 | The plugin's own 100% line-and-branch gate and its PIT gate can be met with the container run in one thin method and everything else unit-tested without Docker | code | to check in task 1.3 - count the branches of the container adapter after the design's split; PIT runs each of its mutants against a real container |
| A7 | No requirement of a main spec this builds on contradicts it. It builds on `css-delivery` (the plugin narrows the class list; the example app builds from it and the E2E suite passes), `coverage-enforcement` and `mutation-testing` (the plugin's gates) | code | confirmed at proposal - the class-list task stays as it is and the new task consumes it; the example-app requirement is MODIFIED below to name the task; the gates apply to the new code as to any |
| A8 | A named Docker volume for npm's cache is acceptable on a developer machine and in CI | user | open - ask at review, recorded in task 1.2. Without it every compile installs three packages, an estimated 20 s instead of today's 70 ms (task 5.4 measures both); with it the volume outlives the build |
| A9 | Testcontainers on the build classpath of every consumer applying the plugin is acceptable | user | open - ask at review, recorded in task 1.2. The plugin pulls docker-java, JNA, slf4j and commons-compress next to Gradle's embedded libraries; a consumer who only wants `kdaisyuiClasses` carries them unused, and a conflict with another plugin's copy is the risk task 1.1 looks for. The alternative is a second artifact, `kdaisyui-gradle-plugin-stylesheet`, which reshapes block 4 |
| A10 | `minify` defaults to on | user | open - ask at review, recorded in task 1.2. Production wants it; it removes the `daisyUI` banner a test might look for (item 9) |
| A11 | The real-container test and PIT: `check` already needs Docker and the network for the example app, so one more container test adds no host requirement; the thin container method is excluded from PIT's targets with its reason, so no mutant starts a container | code | to check in task 1.3 - count the branches and mutants the split leaves, and record the exclusion or its absence |

## Capabilities

### Modified Capabilities

- `css-delivery`: the plugin compiles the stylesheet in a container and pins the toolchain
  versions; the example application builds its stylesheet with that task.

## Impact

- `gradle-plugin`: a task class, an extension, a versions resource, the npm configuration,
  `settings.gradle.kts` for the catalog, `build.gradle.kts` for Testcontainers and the resource
  expansion; tests for each, one of them starting a container.
- `gradle/libs.versions.toml`: `testcontainers`.
- `example-app/build.gradle.kts`, `example-app/tailwind/Dockerfile` (deleted), `app.css` for
  the mount points and its header comment; the references to the deleted task in
  `Assets.kt:9`, `Application.kt:21` and `.opencode/skills/kdaisyui-testing/SKILL.md:218`.
- `README.md`, "Give those classes some CSS".
- `.github/workflows/ci.yml:274`, a comment.
