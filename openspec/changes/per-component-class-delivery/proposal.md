## Why

A consumer that renders 19 of the 66 components receives CSS for all 66. The probe
`extract-used-classes-from-bytecode` (archived 2026-10-31) measured it on `example-app`: **46.0 KB**
gzip today, **22.6 KB** if the stylesheet covered only the components the app calls, even under the
most pessimistic rule (every class of every touched component). Half of the stylesheet is CSS for
components the page never renders.

The probe also settled what the saving does not need: resolving the enum entries a consumer names
(`ButtonSize.Lg` → `btn-lg`) would save another 4.9 KB, under its own dampen threshold. Component
granularity is the prize.

Today's documented setup cannot narrow it. A consumer extracts `kdaisyui-classes.txt` from the jar
and hands Tailwind all 551 classes, and `css-delivery` rightly forbids telling them to keep a
narrower list by hand.

## What Changes

- **A Gradle plugin, `io.github.ollin.kdaisyui`**, published as `kdaisyui-gradle-plugin` with
  its plugin marker. Its task `kdaisyuiClasses` writes `build/kdaisyui/kdaisyui-classes.txt`, the
  file today's README already points Tailwind at. The file holds the classes of the components the
  consumer's compiled code references, not all 551. Oliver chose a plugin over a main class in the
  jar and over a declared list, 2026-10-31.
- **Detection by name, at component granularity.** Calling `daisyButton` or naming `ButtonColor`
  leaves `io/github/ollin/kdaisyui/components/ButtonKt` or `…/ButtonColor` in the consumer's
  `.class` files. A byte scan finds those names; nothing is resolved.
- **The codegen ships the map.** A generated resource in the `kdaisyui` jar, `kdaisyui-components.txt`,
  gives each component its JVM classes and its CSS classes. The plugin reads it from the `kdaisyui`
  version on the consumer's classpath, so its lists always match that version.
- **It fails safe.** A reference to a kdaisyui class the map does not know, or a build that
  references none, gets the full list and a warning. The failure mode of a narrower list stays a
  bigger stylesheet, never an unstyled page.
- **`example-app` applies the plugin** through an included build, so the E2E suite runs against a
  narrowed stylesheet. Its `app.css` says `source(none)` (probe finding F3).
- **The full `kdaisyui-classes.txt` stays** in the jar, for consumers without Gradle.

**Not breaking.** A new artifact, a new resource; the existing resource and setup keep working.

**Subtractive option:** say nothing and leave the 46 KB. Rejected: it is half the stylesheet, and
narrowing it is exactly the tooling `css-delivery` names as missing.

## Out of scope

- Enum-level precision, rejected by the probe.
- DaisyUI's `include:` plugin option. It drops base layers nobody named, which the probe observed
  and did not explore.
- Publishing to the Gradle Plugin Portal; see A5.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | No requirement of a main spec this builds on contradicts it. It builds on `css-delivery`, whose size requirement forbids a hand-maintained narrower list, and on `generated-sources` for the new resource | code | confirmed at proposal - detection keeps nothing by hand; the size requirement is modified to name the plugin as its remedy |
| A2 | A consumer's compiled classes name the JVM class of every component it uses | code | confirmed at proposal - a byte scan of `example-app/build/classes` found 20 component file classes (`ButtonKt` …) and 13 enums, the probe's 19 components plus `Dropdown`, which the popover page added since |
| A3 | The 22.6 KB of one demo app generalises to real consumers | external | open - only `example-app` has been measured |
| A4 | Prefix matching on class names cannot map a class to its component | code | confirmed at proposal - `Stat` is a prefix of `Status` and `Menu` of `MenuDropdown…`; the map lists exact JVM names |
| A5 | Publishing to Maven Central only is enough: a consumer adds `mavenCentral()` to `pluginManagement` | user | open - ask at review; the Plugin Portal needs an account and a key of its own |
| A6 | The plugin, built as an included build so `example-app` can apply it, can still be staged into the root build's `staging-deploy` and released by JReleaser with the others | code | to check in task 1.1 |
| A7 | The plugin's marker artifact stages under `io/github/ollin/kdaisyui/`, so `verifyStagingComplete` can guard it | code | to check in task 1.1 |

## Capabilities

### Modified Capabilities

- `css-delivery`: a Gradle plugin narrows the class list to the components a build uses, from a
  map the library ships, and fails safe to the full list.

## Impact

- New included build `gradle-plugin/`, a published artifact plus its marker; root release wiring
  (`publishedArtifacts`, `stageAll`).
- `codegen/`: one generated resource, `kdaisyui-components.txt`.
- `example-app`: applies the plugin; `app.css` sources the narrowed list.
- README's CSS section, `docs/how-to.md`, the `kdaisyui-release` skill.
