# css-delivery Specification

## Purpose
To make the gap between "the class name is in the HTML" and "the element is styled" visible, since
nothing else in this project can see it.

kdaisyui produces class names and no CSS. That division is deliberate and fine — but it means the
library's entire test suite can be green while a component renders unstyled, because every
generated test asserts a class **string** and none evaluates a stylesheet. Two defects have now
reached users through that gap.

It is worse for consumers than for us. A consumer's Tailwind cannot see the class names this
library emits at all: `btn-primary` is assembled at runtime from an enum value, so it appears in no
file a content scanner reads, and the published jar carries compiled classes and no Kotlin sources.
Nothing in the API hints at this, and the failure is silent in both directions — no error when the
class is missing from the stylesheet, and no error when a Tailwind variant of a DaisyUI class
quietly fails to compile.

This capability covers what the library must ship so a consumer can produce correct CSS, what the
documentation must tell them, and what must be checked automatically rather than noticed by
someone looking at a page.

## Requirements

### Requirement: The library's contract ends at the class name
The documentation SHALL state that `kdaisyui` produces HTML carrying DaisyUI class names and does
**not** produce CSS, and that obtaining working CSS is the consuming application's responsibility.

This is not a disclaimer. It is the fact that makes every other requirement here necessary: a
component can be generated correctly, pass every generated test, and render completely unstyled,
because nothing in the library or its test suite ever evaluates CSS.

**Verified:** the generated component tests assert rendered class strings, and until this change
no test in any module evaluated a computed style except
`NavigationSteps.firstNavHasBackground`, a single check on one element.

#### Scenario: A consumer learns what they must supply
- **WHEN** a developer reads the getting-started documentation
- **THEN** it states that the library emits class names only
- **AND** it names what the application must provide to turn them into styles

### Requirement: The supported CSS setup makes every Tailwind variant work
The documentation SHALL describe a CSS setup in which any Tailwind variant of a DaisyUI class —
`lg:btn-lg`, `max-sm:card-side`, `dark:alert-info` — actually applies, and the example application
SHALL use that setup.

**Verified by measurement.** The prebuilt `daisyui.css` webjar ships exactly five variant prefixes
pre-generated — `sm:` `md:` `lg:` `xl:` (1198 rules each) and `hover:` (60) — and nothing else.
Tailwind's browser build contributes no DaisyUI variants at all, because it can only generate
variants of utilities it owns. So `lg:btn-lg` works and `max-sm:card-side` silently does nothing.
Compiling with the DaisyUI plugin produces both; the example app's stylesheet is 23 KB against the
webjar's 1123 KB, because a compile emits only what is reachable.

**The failure this prevents is a wrong general rule, not a broken build.** A developer tries
`lg:btn-lg`, sees it work, concludes that variants are supported, and later loses `dark:alert-info`
with no error anywhere. The boundary between the two is not a contract — it is whatever DaisyUI
chose to pre-generate in the pinned release.

#### Scenario: A variant of a DaisyUI class applies
- **WHEN** an element carries a Tailwind variant of a DaisyUI class
- **AND** the viewport or state matches that variant's condition
- **THEN** the corresponding CSS rule applies to the element

#### Scenario: A variant that fails to compile is caught automatically
- **WHEN** a variant of a DaisyUI class does not reach the stylesheet
- **THEN** an automated check fails
- **AND** the failure is not left to be noticed by someone looking at a rendered page

### Requirement: The library ships the class list a consumer's CSS build needs
The published `kdaisyui` artifact SHALL contain a generated, complete list of every DaisyUI class
the library can emit, as a resource a consumer can extract and hand to their own CSS build. The
documentation SHALL show how.

**A consumer cannot obtain these class names any other way.** `btn-primary` is assembled at
runtime from `ButtonVariant.Primary`, so it appears in no file a content scanner reads, and the
published jar carries compiled classes and no Kotlin sources.

**Verified end to end** from the published jar alone: extracting `kdaisyui-classes.txt`, compiling
against it plus a consumer source file in which `btn-primary` occurs zero times, produces a
stylesheet containing `.btn-primary`, `.btn-lg` and `.max-sm\:card-side`. 555 classes, 7 KB in the
jar, 552 KB of resulting CSS.

**Assumed:** that the list stays in step with the components. *Wrong if:* a regeneration changes
the components without changing the list — which `generated-sources-drift` is what stands behind,
since both are generated into `lib/generated/` by the same task.

#### Scenario: A class only reachable through an enum reaches the stylesheet
- **WHEN** an application uses a generated component whose class name is assembled at runtime
- **AND** its CSS build follows the documented setup
- **THEN** that class is present in the compiled stylesheet

#### Scenario: The resource cannot silently stop being packaged
- **WHEN** the class list is missing from the built artifact
- **THEN** a test fails

### Requirement: The size and its remedy are stated honestly
The documentation SHALL state the approximate size of the full stylesheet and of a narrowed one,
SHALL name the Gradle plugin as the way to narrow it, and SHALL NOT instruct a reader to
hand-maintain a narrower list.

A hand-kept safelist is followed until the day it is not, and fails silently. Narrowing needs to
know which components a codebase references: tooling, not diligence, and the plugin is that tooling.

#### Scenario: A reader can predict the cost
- **WHEN** a developer follows the documented setup
- **THEN** the documentation has told them the approximate size of the result
- **AND** why it covers more than their application uses, or how the plugin narrows it

#### Scenario: The escape hatch's limit is stated
- **WHEN** a consumer passes an arbitrary class through `extraClasses`
- **THEN** the documentation says that class must be visible to their own Tailwind setup
- **AND** does not imply the library can account for it

### Requirement: A Gradle plugin narrows the class list to the components a build uses

The library SHALL publish a Gradle plugin whose task writes `build/kdaisyui/kdaisyui-classes.txt`
with the CSS classes of exactly those components whose JVM classes the build's compiled code
references.

**Verified** that compiled code names them: a byte scan of `example-app`'s classes found the file
class of each of the 20 components it uses, and the enums it names, 2026-10-31.

#### Scenario: Only the components called

- **WHEN** a build's code calls `daisyButton` and nothing else of the library
- **THEN** the written list holds the button's classes
- **AND** no class of any other component

#### Scenario: The lists match the library version in use

- **WHEN** the plugin runs
- **THEN** it reads the component map from the `kdaisyui` artifact on the build's runtime classpath

### Requirement: When in doubt the plugin writes the full list

When the build references a kdaisyui component class the map does not know, or references none,
the plugin SHALL write every class the library can emit and warn, naming the reason.

#### Scenario: A version out of step

- **WHEN** compiled code references a component class the map does not list
- **THEN** the written list is the full list
- **AND** the warning names the class

### Requirement: The narrowed stylesheet styles the example application

The example application SHALL build its stylesheet from the plugin's list, through the plugin's
compile task and nothing else, and the E2E suite SHALL pass on it.

**Verified** 2026-11-01: with the stylesheet built from the plugin's list of the app's 20
components (55.5 KB gzip before, 27.3 KB after, unminified), all 53 E2E scenarios pass.

**Verified** 2026-11-08 that the same scenarios pass on the stylesheet the plugin's task compiles,
minified (150,330 bytes): 62 E2E tests, 0 failures, with the served `app.css` byte-identical to
the task's output (change `compile-the-stylesheet-in-the-plugin`, task 5.1).

#### Scenario: The suite on the narrowed stylesheet

- **WHEN** the example application is built with the plugin
- **THEN** its stylesheet is smaller than the full one
- **AND** every E2E scenario passes

#### Scenario: No compile of its own

- **WHEN** the example application's build script is read
- **THEN** it registers no task that runs `docker`, and carries no Dockerfile

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

### Requirement: The stylesheet task is up to date after one compile

The stylesheet task SHALL take the class list as a single file, so that its own output, whatever
directory it is written to, is never one of its inputs, and a second run with unchanged inputs is
up to date.

**Verified** 2026-11-10 on the example app: after a compile the next run is UP-TO-DATE.

#### Scenario: Two runs in a row

- **WHEN** `kdaisyuiStylesheet` runs twice with nothing changed in between
- **THEN** the second run is UP-TO-DATE and starts no container

#### Scenario: The entry's paths stay

- **WHEN** an entry writes `@source "../kdaisyui/kdaisyui-classes.txt"`
- **THEN** it resolves to the class list inside the container, as before
