## ADDED Requirements

### Requirement: The plugin warns about a DaisyUI class written as a string

The Gradle plugin SHALL warn about each string constant of the build's compiled main classes that
is a class list holding a class of a kdaisyui component. The warning SHALL name the class file,
the string, and the components.

**Verified** by measurement, 2026-11-14 (proposal A1, A2): 14 of the Deploy-Radar's 789 literals
and 33 of the example app's 582 match, one of them falsely.

#### Scenario: A button written as a string

- **WHEN** main code renders `summary("btn btn-sm btn-ghost")`
- **THEN** the build logs a warning naming the class file, `"btn btn-sm btn-ghost"` and the
  component `button`

#### Scenario: Layout utilities beside a DaisyUI class

- **WHEN** main code writes `classes = "card bg-base-100 col-span-12"`
- **THEN** the warning names that string and the component `card`

### Requirement: Only a string shaped like a class list is reported

The plugin SHALL treat a string as a class list only when every whitespace-separated token is
class-shaped and at least one token contains `-` or `:`.

**Verified** by the same measurement: none of the matches in the Deploy-Radar is prose. The
example app's one false match is the id `tab-active`.

#### Scenario: Prose is not a class list

- **WHEN** main code holds the string `"Open the link"` or `"the link"`
- **THEN** no warning names it

#### Scenario: A utility marks the list

- **WHEN** main code writes `classes = "link font-mono"`
- **THEN** the warning names that string and the component `link`

#### Scenario: A single bare word is not reported

- **WHEN** main code writes `details("dropdown")`
- **THEN** no warning names it, because no token holds `-` or `:` (proposal A3)

### Requirement: A variant-prefixed DaisyUI class is not reported

The plugin SHALL NOT count a DaisyUI class carrying a Tailwind variant prefix, because a string is
the documented way to write a variant.

**Verified** in `lib/src/main/kotlin/io/github/ollin/kdaisyui/core/Variants.kt`: the composition
API stays internal, since a composed `xl:btn-lg` appears in no file Tailwind scans. The string
form `extraClasses = "lg:btn-lg"` is what works.

#### Scenario: Only a variant

- **WHEN** main code writes `extraClasses = "lg:btn-lg"`
- **THEN** no warning names it

#### Scenario: A variant beside a bare class

- **WHEN** main code writes `"stats stats-vertical xl:stats-horizontal"`
- **THEN** the warning names the string and the component `stat`

### Requirement: The check runs with every class list it narrows

The check SHALL run whenever the plugin's class-list task runs, is up to date, or comes from the
cache. It SHALL NOT add a stylesheet compile to a build.

**Verified** by the probe of task 1.2, 2026-11-14 (proposal A5): a finalizer of the class-list
task ran on four builds in a row, and those logged with task names show it up to date.

#### Scenario: A library without the component map

- **WHEN** the runtime classpath holds no `kdaisyui-components.txt`
- **THEN** the check logs at info level that it cannot check, and passes, even with `failBuild`

#### Scenario: An up-to-date build still warns

- **WHEN** a build with a raw DaisyUI string runs twice without a change
- **THEN** both runs log the warning

### Requirement: A build can fail on a raw DaisyUI class

The plugin SHALL fail the build with the same text when the build sets
`kdaisyui { rawClasses { failBuild = true } }` and the check finds a string. Without that setting
it SHALL only warn.

**Verified** as a decision: Oliver chose "warn, opt-in fail", 2026-11-14 (proposal A4).

#### Scenario: Opted in

- **WHEN** `failBuild` is true and main code holds `"btn btn-primary"`
- **THEN** the build fails, and the failure names the string

#### Scenario: Not opted in

- **WHEN** `failBuild` is not set and main code holds `"btn btn-primary"`
- **THEN** the build succeeds and logs the warning

### Requirement: kdaisyui's own example application passes the check as a gate

The example application SHALL build with `failBuild = true`, so a raw DaisyUI string added to it
fails kdaisyui's own build.

**Assumed**: every one of its raw sites has a typed equivalent that renders the same classes
(proposal A6). Wrong if a site needs markup no component function or `…Classes` sibling writes,
or if a page's rendered class sets differ after the move.

#### Scenario: The example builds clean

- **WHEN** `./gradlew :example-app:classes` runs
- **THEN** the check finds no string, and the build succeeds
