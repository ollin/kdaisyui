## Why

A DaisyUI class written as a string escapes the compiler, and documentation has not stopped it.
The Deploy-Radar (fwb-application, kdaisyui 0.11.0) still writes 15 such strings by hand. It
wrote a raw `details("dropdown")` with `summary("btn btn-sm btn-ghost")` on 2026-11-12, two days
after 0.11.0 put the class sibling into every KDoc and while `docs/how-to.md` "Add a dropdown"
showed the typed form.

Its agents copied neighbouring code, and the code they started from is ours. Its
`add-deploy-radar` F14 records that task 2.3 copied kdaisyui's example app. That app writes 33
raw DaisyUI strings itself, every one with a typed equivalent. Only a check that runs in the
consumer's build reaches a consumer who does not read the docs.

## What Changes

- **A plugin task `kdaisyuiRawClasses` finalizes `kdaisyuiClasses`.** It reads the string
  constants of the build's compiled main classes and warns about each one that is a class list
  holding a bare DaisyUI class. The warning names the class file, the string, and the components
  the classes belong to.
- **What counts as a class list:**
  - every whitespace-separated token is class-shaped (lowercase, digits and `-:/[]().%_#!`);
  - at least one token contains `-` or `:`;
  - at least one token without a variant prefix is in the library's `kdaisyui-components.txt`.

  A variant-prefixed class such as `lg:btn-lg` never counts: `extraClasses = "lg:btn-lg"` is the
  documented way to write a variant (`core/Variants.kt`).
- **Opt-in failure:** `kdaisyui { rawClasses { failBuild = true } }` turns the warning into a
  build failure carrying the same text. The default is to warn.
- **The constant-pool reader keeps `CONSTANT_String` entries**, which it skips today.
- **The example app uses the typed API everywhere**: 33 sites in 10 files move to the component
  functions or their `…Classes` siblings. Its id `tab-active` is renamed, and the app sets
  `failBuild = true`, so kdaisyui's own build is the gate that the check stays quiet on code
  written as the docs teach.
- **Docs:** `docs/how-to.md` "Put a component's classes on another element" names the warning
  and the option, and `codegen/llms-full-template.md` names it in its plugin paragraph.

### The subtractive option, stated

Do nothing more, since the siblings are documented. Rejected: the measurement above shows the
documentation was available and not used. What this change removes is 33 raw strings from the
example app, the source consumers copy.

## Out of scope

- **A raw class of a single bare word** (`details("dropdown")`, `classes = "link"`): no `-` or
  `:` marks it as a class list, and a word cannot be told from prose. This is the one Deploy-Radar
  site the rule misses (A3).
- **An ignore list for strings that only look like classes.** Rejected in design.md decision 4.
- **Migrating the Deploy-Radar.** It is fwb-application's change, to be done after adoption.
- **`docs/` snippets and the tests' own sources:** the check reads compiled main classes, as
  `kdaisyuiClasses` does.
- **`document-server-rendered-patterns`:** its modal and dropdown-form recipes stay its own.
  This change only points at the raw strings.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | A Kotlin string literal in main code reaches its class file as a `CONSTANT_String` naming a UTF-8 entry, and no kdaisyui call inlines a class string into the consumer's class file | code | confirmed at proposal: `javap -v` over `example-app/build/classes/kotlin/main`, 2026-11-14 (`/tmp/opencode/warn-on-raw-daisyui-classes/probe.py … classes`). The 27 hits are all literals of the app's own sources, and none comes from the library |
| A2 | The rule finds the raw DaisyUI strings of real consumer code and nothing else | code | confirmed at proposal with the same probe over the sources. **Deploy-Radar** `server/src/main/kotlin`: 14 hits in 789 literals, all real. **Example app**: 33 hits in 582 literals, one false (`TabsPage.kt:18`, the id `tab-active`). The class files of the example app give 27, because a class pools equal strings once |
| A3 | The miss of a single bare word is acceptable | user | confirmed by Oliver 2026-11-14: the scope "warning + clean example" was chosen with the measured miss stated |
| A4 | Warn by default, fail by opt-in | user | confirmed by Oliver 2026-11-14 |
| A5 | A finalizer of `kdaisyuiClasses` runs when that task is up to date or from cache | code | confirmed by analogy: `check-the-themes-the-stylesheet-compiles` A7 observed the same Gradle mechanism for `kdaisyuiStylesheet`; re-checked live in task 3.3 |
| A6 | Every one of the 33 example sites has a typed equivalent that renders the same classes | code | confirmed at proposal: `grep "fun Tag.daisy*Classes"` in `lib/generated/main/…/components/` lists siblings for card, drawer, dropdown, loading, mask, menu item, megamenu, stats and button. A changed class *order* is what task block 4 must keep the E2E suite green through |
| A7 | No requirement of a main spec this builds on contradicts it: `css-delivery` ("A Gradle plugin narrows the class list…") and `theme-selection` ("The plugin warns when code names a theme the stylesheet lacks") | code | confirmed at proposal: the task adds a warning and an option, and changes no output those specs describe |

## Capabilities

### New Capabilities

- `raw-class-detection`: the Gradle plugin points at DaisyUI classes written as strings in the
  build's compiled code, as a warning or, on request, a failure.

### Modified Capabilities

None.

## Impact

- `gradle-plugin/`: the pool reader (`ConstantPool.kt`), the detection functions, the task, the
  option in `KdaisyuiExtension.kt` and the wiring in `KdaisyuiPlugin.kt`. Tests run in-process
  under the plugin's own 100% Kover and `:gradle-plugin:pitest` gates.
- `example-app/`: 10 files under `src/main/kotlin/kdaisyui/example/`, plus `build.gradle.kts` for
  `failBuild`. `e2e-tests/` changes only where a step names the renamed id.
- `docs/how-to.md`, `codegen/llms-full-template.md` and the regenerated `llms-full.txt`.
