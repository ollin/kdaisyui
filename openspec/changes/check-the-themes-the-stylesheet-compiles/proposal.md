## Why

`DaisyTheme` names all 35 built-in themes, but a compiled stylesheet carries only those its entry's
`@plugin "daisyui"` block names, `light` and `dark` by default. `daisyTheme(DaisyTheme.Nord)`
against such a stylesheet renders the default theme, with no error anywhere — the silent gap the
`css-delivery` capability exists to close for classes, open again for themes
(`select-a-theme-in-the-profile`, finding F1, adopted by Oliver 2026-11-10).

## What Changes

- **A Gradle plugin task `kdaisyuiThemes`** reads which themes the build's compiled classes name
  and which the compiled stylesheet carries, and **warns**, naming the missing themes and the
  entry line that adds them. It runs after `kdaisyuiStylesheet` and joins `check`.
- **What counts as naming a theme:** a reference to an entry (`DaisyTheme.Nord`, a `Fieldref` in
  the class file) names that theme; a reference to the whole set (`DaisyTheme.getEntries`,
  `values`, `valueOf`) or to `daisyThemeOf` names every theme, because the choice is made at
  runtime. Reading `.value` names none.
- **What counts as carrying one:** a `[data-theme=<name>]` selector in the compiled output.
- The how-to's theme section names the warning.

### The subtractive option, stated

Document the gap and check nothing, as `select-a-theme-in-the-profile` did. Rejected: that is the
state F1 recorded as a defect; a consumer learns of a missing theme from a page that looks wrong.
The alternative of **writing the `themes` line for the consumer** (a plugin option) is rejected in
design.md: the entry is the consumer's file.

## Out of scope

- Custom themes (`@plugin "daisyui/theme"`) and `data-theme` strings written by hand: not
  `DaisyTheme`, invisible to the check.
- Failing the build. A warning, as the plugin's other "when in doubt" signals; making it fatal is a
  later decision once the false-positive rate is known.
- The webjar setup, which carries every theme.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | An entry reference compiles to a `Fieldref` naming `io/github/ollin/kdaisyui/themes/DaisyTheme` and the entry | code | confirmed at proposal - `javap -v` of `lib/.../themes/DaisyThemeTest.class`: `Fieldref …DaisyTheme.Nord` and `…DaisyTheme.Dark`, 2026-11-10 |
| A2 | Runtime choice compiles to `DaisyTheme.getEntries`, or a call to `DaisyThemesKt.daisyThemeOf`, and `.value` to `getValue` | code | confirmed at proposal - `javap -v` of the example app: `ProfilePageKt$appearance$2$1` names `DaisyTheme.getEntries`, `ThemeCookieKt` names `DaisyThemesKt.daisyThemeOf` and `DaisyTheme.getValue` |
| A3 | Pairing a `Fieldref` with its class and name needs the constant pool parsed; the byte scan `componentClassesNamedIn` uses cannot pair them | code | to check in task 1.1 - a reader of the class-file constant pool in the plugin, no new dependency; if it exceeds ~80 lines, revise toward ASM |
| A4 | The minified stylesheet keeps the selector as `[data-theme=nord]` | code | confirmed at proposal - the compile of `select-a-theme-in-the-profile` task 1.1 printed `,[data-theme=nord]{` |
| A5 | No requirement of a main spec this builds on contradicts it: `css-delivery` ("When in doubt the plugin writes the full list", "The narrowed stylesheet styles the example application"), `theme-selection` ("The documentation states which themes exist") | code | confirmed at proposal - read 2026-11-10; the task adds a warning and changes no output |
| A6 | A warning, not a failure | user | open - ask at review |

## Capabilities

### Modified Capabilities

- `theme-selection`: the plugin warns when compiled code names a theme the stylesheet lacks.

## Impact

- `gradle-plugin/`: a constant-pool reader, theme references, a stylesheet-theme reader, the task,
  its registration; tests incl. a TestKit run; Kover 100 % and pitest there as for the rest.
- `docs/how-to.md`; `llms-full-template.md` if it names the plugin's tasks.
