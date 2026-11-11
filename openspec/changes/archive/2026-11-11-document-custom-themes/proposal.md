## Why

A consumer can already give their application a theme of their own: DaisyUI's
`@plugin "daisyui/theme" { name: "acme"; … }` in the stylesheet entry, and the name written as
`data-theme` or as a theme controller's `value`. Nothing in kdaisyui shows how. The how-to says
it in one sentence (`docs/how-to.md:380-381`) with neither the CSS nor the Kotlin, and
`llms-full.txt`, the file the README tells library users to give their AI tools, does not mention
custom themes at all (`codegen/llms-full-template.md:140-157` names only `DaisyTheme`). An agent
asked for a branded theme therefore has nothing to go on but DaisyUI's own docs, which know
nothing of `daisyTheme`, the plugin's stylesheet task or the theme check.

## What Changes

- **The how-to's theme recipe gains "A theme of your own"**, replacing the one sentence: the
  `@plugin "daisyui/theme"` block in the stylesheet entry, a compiled Kotlin snippet writing the
  name on the page and on a theme controller, and the three things that differ from a built-in
  theme: it has no `DaisyTheme` entry, `daisyThemeOf` returns `null` for it, and the plugin's
  `kdaisyuiThemes` check does not see it.
- **`llms-full.txt`'s Themes section names custom themes** the same way, in a few lines, edited in
  `codegen/llms-full-template.md`; `llms.txt`'s note on the how-to names the new recipe, edited in
  `codegen/llms-template.md`. Both regenerated with `just generate`.

### The subtractive option, stated

Remove the one sentence and link DaisyUI's theme page instead. Rejected: DaisyUI's page cannot say
what kdaisyui adds or leaves out (`daisyThemeOf`, the theme check, the controller's typed
`theme`), and that is exactly what a reader gets wrong. What this change removes: the one
sentence it replaces, and the reader's guess.

## Out of scope

- **A typed API for custom themes** (a theme-name type that `daisyTheme`, `daisyThemeOf` and
  `daisyThemeController` accept, and a theme check that covers it). Oliver chose documentation
  only, 2026-11-11; it would be its own change.
- **A custom theme in the example app**, with E2E. The probe of A1 showed the CSS compiles and
  applies; the example app demonstrates the built-in themes, and a second theme mechanism there
  would be code to maintain for a fact the probe already established.
- **Customising a built-in theme** (`name: "light"` with overrides). DaisyUI's theme page covers
  it, and in Kotlin it changes nothing: the `DaisyTheme` entry stays valid.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | A `@plugin "daisyui/theme"` block compiles in the plugin's `kdaisyuiStylesheet` container and yields a theme reachable by `data-theme` and by a checked theme controller | code | confirmed at proposal - `example-app/src/main/css/app.css` given a block `name: "acme"` (then restored), `./gradlew :example-app:kdaisyuiStylesheet :example-app:kdaisyuiThemes` green; `example-app/build/kdaisyui/app.css` holds `:root:has(input.theme-controller[value=acme]:checked),[data-theme=acme]{color-scheme:light;--color-primary:oklch(55% .3 240)`, 2026-11-11 |
| A2 | The `kdaisyuiThemes` check neither sees nor warns about a theme written as a string | code | confirmed at proposal - `gradle-plugin/src/main/kotlin/io/github/ollin/kdaisyui/gradle/ThemeCheck.kt:7-10` derives the named themes only from references to `DaisyTheme` and `daisyThemeOf`; the A1 run printed no warning |
| A3 | Library users want a documented recipe, not a typed API | user | confirmed by the user 2026-11-11 - "Docs only" chosen |
| A4 | No requirement of a main spec this builds on contradicts it. `fwb_tasks.constraints list --change document-custom-themes` names `ci-job-selection`, `component-model`, `documentation-snippets`, `generated-sources`, `llms-txt` and `mutation-testing`; besides those, read by name: `theme-selection` (its requirement "The documentation states which themes exist" is the one this change extends) | code | confirmed at proposal - each listed requirement read 2026-11-11: the new snippet is compiled (`documentation-snippets`), the index keeps its shape and links (`llms-txt`), both outputs are regenerated in the same commit as their templates (`generated-sources`); `mutation-testing` concerns `codegen/src`, which this change does not touch |

## Capabilities

### New Capabilities

### Modified Capabilities

- `theme-selection`: two added requirements - the how-to shows a theme of one's own, and
  `llms-full.txt` names custom themes. The existing requirement "The documentation states which
  themes exist" stays as it is; its clause on custom themes remains true.

## Estimate

Frozen 2026-11-11, before implementation: **2h 25min (4h 30min DevOnly)**, ledger run
`2026-11-11-kdaisyui-document-custom-themes`. Blocks (developer-only): proposal 1h, probe of A1
45min, how-to and llms templates 1h 30min, gate and evaluation 45min, archive 30min.
