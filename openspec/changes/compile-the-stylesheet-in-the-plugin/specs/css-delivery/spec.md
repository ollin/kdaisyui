## ADDED Requirements

### Requirement: The plugin compiles the stylesheet in a container

The Gradle plugin SHALL offer a task that compiles a configured entry stylesheet with Tailwind
and DaisyUI inside a Node container reached through the Docker daemon alone: no docker CLI, no
Node and no npm on the build host. It SHALL copy the inputs in and the stylesheet out, and
SHALL take the Node image, the npm registry and an optional credential as configuration.

The inputs are the entry, the configured source directories and the output directory of the
class-list task. A half credential, user or password alone, fails the task naming the missing
half.

**Verified** that the shape works where it is needed: the Deploy-Radar's `compileTailwind`
(fwb-application, `fwb-deploy-radar/server/build.gradle.kts:114-160`) copies in, runs one-shot
and copies out through Testcontainers in a CI job whose image has no docker CLI and no Node
(its finding F14), green since 2026-11-06.

**Assumed** that a consumer's build resolves the plugin's container dependency from the
published POM. *Wrong if:* a scratch consumer resolving the plugin from `mavenLocal()` in task
1.1 fails to find it.

#### Scenario: A compile with no CLI on the host

- **WHEN** the task runs on a host with a Docker daemon and no docker CLI, Node or npm
- **THEN** the configured output holds the compiled stylesheet

#### Scenario: Behind a mirror

- **WHEN** the registry and a credential are configured
- **THEN** the install inside the container reads that registry with that credential
- **AND** nothing is fetched from npmjs.org

#### Scenario: Half a credential

- **WHEN** a user is configured and no password
- **THEN** the task fails before starting a container, naming the password

#### Scenario: The entry's paths are the same everywhere

- **WHEN** the entry writes `@source "../kdaisyui/kdaisyui-classes.txt"`
- **THEN** that path resolves inside the container on every machine the task runs on

### Requirement: The plugin pins the toolchain versions

The plugin SHALL carry the versions of Tailwind, DaisyUI and Node the library was generated and
tested against, read from `gradle/libs.versions.toml` and `.tool-versions` at the plugin's
build, and the compile task SHALL use them. A build SHALL NOT need to state them.

**Verified** that the plugin's build reads the root's files, 2026-11-07:
`gradle-plugin/settings.gradle.kts` takes the `libs` catalog from `../gradle/libs.versions.toml`.

#### Scenario: The versions match the catalog

- **WHEN** the plugin jar is built
- **THEN** the versions it carries equal `daisyui`, `webjar-tailwindcss-browser` and the `nodejs`
  line of `.tool-versions` at that commit

#### Scenario: A consumer states no version

- **WHEN** a build configures only the entry stylesheet
- **THEN** the compile installs the plugin's versions

## MODIFIED Requirements

### Requirement: The narrowed stylesheet styles the example application

The example application SHALL build its stylesheet from the plugin's list, through the plugin's
compile task and nothing else, and the E2E suite SHALL pass on it.

**Verified** 2026-11-01: with the stylesheet built from the plugin's list of the app's 20
components (55.5 KB gzip before, 27.3 KB after, unminified), all 53 E2E scenarios pass.

**Assumed** that the same scenarios pass on the stylesheet the plugin's task compiles. *Wrong
if:* the E2E suite reports a scenario failing after task 5.1 that passed before it.

#### Scenario: The suite on the narrowed stylesheet

- **WHEN** the example application is built with the plugin
- **THEN** its stylesheet is smaller than the full one
- **AND** every E2E scenario passes

#### Scenario: No compile of its own

- **WHEN** the example application's build script is read
- **THEN** it registers no task that runs `docker`, and carries no Dockerfile
