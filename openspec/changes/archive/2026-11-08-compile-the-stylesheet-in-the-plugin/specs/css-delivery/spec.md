## ADDED Requirements

### Requirement: The plugin compiles the stylesheet in a container

The Gradle plugin SHALL offer a task that compiles a configured entry stylesheet with Tailwind
and DaisyUI inside a Node container reached through the Docker daemon alone: no docker CLI, no
Node and no npm on the build host. It SHALL copy the inputs in and the stylesheet out, with no
bind mount, and SHALL place them at fixed paths so the entry's `@source` and `@import` lines
are the same on every machine.

**Verified** that the shape works where it is needed: the Deploy-Radar's `compileTailwind`
(fwb-application, `fwb-deploy-radar/server/build.gradle.kts:128-174`) copies in, runs one-shot
and copies out through Testcontainers in a CI job whose image has no docker CLI and no Node
(its finding F14). **Assumed** that the plugin's task does the same on that runner; *wrong if*
it fails there, which only the Deploy-Radar can observe.

#### Scenario: A compile through the daemon

- **WHEN** the task runs on a host with a Docker daemon
- **THEN** the configured output holds the compiled stylesheet, and no process named `docker`,
  `node` or `npm` ran on the host

#### Scenario: The entry's paths are the same everywhere

- **WHEN** the entry writes `@source "../sources/0-kotlin"` and `@source "../kdaisyui/kdaisyui-classes.txt"`
- **THEN** both resolve inside the container, and the compile in the task's own test succeeds with them

#### Scenario: Two source directories with one name

- **WHEN** two configured source directories end in the same segment
- **THEN** each lands at its own mount point, indexed, and both are scanned

### Requirement: The compile takes its registry and credential from configuration

The task SHALL take the Node image, the npm registry and an optional credential as
configuration, so a build with no internet compiles from a mirror. A credential of which only
the user or only the password is given SHALL fail the task, naming the missing half, before any
container starts.

**Verified** that the Deploy-Radar needs exactly this: its npm mirror refuses anonymous
clients, and its build composes an `.npmrc` with the mirror and a base64 `_auth`
(`server/build.gradle.kts:147-150`).

#### Scenario: Behind a mirror

- **WHEN** the registry and a credential are configured
- **THEN** the `.npmrc` the task copies into the container names that registry and carries `_auth` for it

#### Scenario: Half a credential

- **WHEN** a user is configured and no password
- **THEN** the task fails before starting a container, naming the password

### Requirement: The plugin pins the toolchain versions

The plugin SHALL carry the versions of Tailwind, DaisyUI and Node the library was generated and
tested against, read from `gradle/libs.versions.toml` and `.tool-versions` at the plugin's
build, and the compile task SHALL use them. A build SHALL NOT need to state them. The plugin
SHALL warn when the `kdaisyui` jar on the build's runtime classpath is of another version than
its own, naming both.

**Verified** that the plugin's build reads the root's files, 2026-11-07:
`gradle-plugin/settings.gradle.kts` takes the `libs` catalog from `../gradle/libs.versions.toml`;
and that the plugin and the library carry one version number by decision
(`gradle.properties`, last comment line), so a matched pair is the versions the components were
generated against.

#### Scenario: The versions match the catalog

- **WHEN** the plugin jar is built
- **THEN** the versions it carries equal `daisyui`, `webjar-tailwindcss-browser` and the `nodejs`
  line of `.tool-versions` at that commit

#### Scenario: A consumer states no version

- **WHEN** a build configures only the entry stylesheet
- **THEN** the compile installs the plugin's versions

#### Scenario: A plugin paired with another library version

- **WHEN** the `kdaisyui` jar on the runtime classpath is not the plugin's version
- **THEN** the build's log carries a warning naming both versions

## MODIFIED Requirements

### Requirement: The narrowed stylesheet styles the example application

The example application SHALL build its stylesheet from the plugin's list, through the plugin's
compile task and nothing else, and the E2E suite SHALL pass on it.

**Verified** 2026-11-01: with the stylesheet built from the plugin's list of the app's 20
components (55.5 KB gzip before, 27.3 KB after, unminified), all 53 E2E scenarios pass.

**Assumed** that the same scenarios pass on the stylesheet the plugin's task compiles, minified
if that is the default A10 settles, which changes the artifact but not the classes in it. *Wrong
if:* the E2E suite reports a scenario failing after task 5.1 that passed before it.

#### Scenario: The suite on the narrowed stylesheet

- **WHEN** the example application is built with the plugin
- **THEN** its stylesheet is smaller than the full one
- **AND** every E2E scenario passes

#### Scenario: No compile of its own

- **WHEN** the example application's build script is read
- **THEN** it registers no task that runs `docker`, and carries no Dockerfile
