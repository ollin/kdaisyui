## Why

`DaisyTheme` names all 35 built-in themes, but a compiled stylesheet carries only those its entry's
`@plugin "daisyui"` block names, `light` and `dark` by default. `daisyTheme(DaisyTheme.Nord)`
against such a stylesheet renders the default theme, with no error anywhere — the silent gap the
`css-delivery` capability exists to close for classes, open again for themes
(`archive/2026-11-10-select-a-theme-in-the-profile/findings.md`, F1, adopted by Oliver 2026-11-10).

## What Changes

- **The library ships `kdaisyui-themes.txt`**, a generated resource beside `kdaisyui-components.txt`:
  each `DaisyTheme` entry and its `data-theme` value. The plugin does not depend on the library,
  so this is how it learns the themes of the library version a build uses.
- **A plugin task `kdaisyuiThemes` finalizes `kdaisyuiStylesheet`**: whenever the stylesheet is
  compiled (or found up to date), it compares the themes the compiled classes name with the
  `[data-theme=…]` selectors of the output and **warns**, naming the missing themes and the entry
  line that adds them. It is not added to `check`, so a build without a stylesheet entry, or one
  using the webjar, is unaffected and no Docker compile is pulled into `check`.
- **What names a theme:** a `Fieldref` to `DaisyTheme.<Entry>` names that entry's theme; a
  reference to `DaisyTheme.getEntries`, `values`, `valueOf` or `DaisyThemesKt.daisyThemeOf` names
  every theme; `getValue` names none.
- The how-to's theme section and `llms-full-template.md`'s plugin paragraph name the warning.

### The subtractive option, stated

Document the gap and check nothing, as `select-a-theme-in-the-profile` did. Rejected: that is the
state F1 recorded as a defect. Writing the `themes` line for the consumer is rejected in design.md.

## Out of scope

- Custom themes and `data-theme` strings written by hand: not `DaisyTheme`, invisible to the check.
- Failing the build (A6).
- Classes of the build's dependencies: the same scope as `kdaisyuiClasses`.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | An entry reference compiles to a `Fieldref` naming `io/github/ollin/kdaisyui/themes/DaisyTheme` and the entry | code | confirmed at proposal - `javap -v` of `lib/.../themes/DaisyThemeTest.class`: `Fieldref …DaisyTheme.Nord`, `…DaisyTheme.Dark`, 2026-11-10 |
| A2 | Runtime choice compiles to `DaisyTheme.getEntries` or `DaisyThemesKt.daisyThemeOf`, and `.value` to `getValue` | code | confirmed at proposal - `javap -v` of the example app: `ProfilePageKt$appearance$2$1` names `DaisyTheme.getEntries`, `ThemeCookieKt` names `DaisyThemesKt.daisyThemeOf` and `DaisyTheme.getValue` |
| A3 | A constant-pool reader of the plugin's own pairs a `Fieldref`/`Methodref` with its class and member in a size the 8-line steps can carry, rather than ASM | code | to check in task 1.1 - a probe over the `DaisyThemeTest.class` bytes, thrown away; it decides reader or ASM before block 2 |
| A4 | The stylesheet carries a theme as `[data-theme=nord]` minified and in some form unminified | code | half confirmed at proposal - minified `,[data-theme=nord]{` (select-a-theme-in-the-profile task 1.1); unminified to check in task 1.2 |
| A5 | No requirement of a main spec this builds on contradicts it: `css-delivery` ("A Gradle plugin narrows the class list…", "When in doubt the plugin writes the full list", "The narrowed stylesheet styles the example application"), `theme-selection` ("Every built-in DaisyUI theme is a typed value", "The documentation states which themes exist"), `generated-sources` | code | confirmed at proposal - read 2026-11-10 (the constraints list was not runnable in the review); the task adds a warning and a resource, and changes no output the specs describe |
| A6 | A warning, not a failure | user | open - asked with this review round |
| A7 | A finalizer runs when `kdaisyuiStylesheet` is up to date or from cache, not only when it executes | code | to check in task 1.3 - a scratch build of the example app run twice |
| A8 | Every `DaisyTheme` entry's value is its name lowercased today, but the plugin must not rely on it | code | confirmed at proposal - all 35 entries; hence the generated resource |

## Capabilities

### Modified Capabilities

- `theme-selection`: the library ships its theme map, and the plugin warns when compiled code names
  a theme the stylesheet lacks.

## Impact

- `codegen/` (`themes.ts`, `index-new.ts`), `lib/build.gradle.kts`, the generated
  `lib/generated/main/resources/kdaisyui-themes.txt`.
- `gradle-plugin/`: the pool reader, theme references, the theme map reader, the stylesheet
  reader, the task, its wiring; tests in-process (`ProjectBuilder` and pure functions), under the
  plugin's own Kover 100 % and pitest gates.
- `docs/how-to.md`, `codegen/llms-full-template.md` and the regenerated `llms-full.txt`.
