## Why

The example app's avatar menu has a "Profile" entry that leads nowhere, and nothing in the
library or its documentation shows how a user picks one of DaisyUI's 35 themes. Everything
needed is CSS that DaisyUI already ships: a theme is a set of custom properties scoped by
`[data-theme="<name>"]` on any element (`daisyui/packages/daisyui/functions/pluginOptionsHandler.js:27-31`),
so a whole page or a single preview card can wear one, with no script. What a consumer lacks is
the names: today they write `attributes["data-theme"] = "nord"`, a string the compiler cannot
check and a DaisyUI bump can silently invalidate.

## What Changes

- **A generated `DaisyTheme` enum** of every built-in theme, read from DaisyUI's
  `src/themes/*.css` by a new codegen step, in a new package `io.github.ollin.kdaisyui.themes`,
  each entry carrying the `data-theme` value. A DaisyUI bump that adds or drops a theme changes
  the enum, and `generated-sources-drift` sees it.
- **Two hand-written functions** beside it, as the icons package has its support files:
  `Tag.daisyTheme(theme)` writes `data-theme`, and `daisyThemeOf(value)` parses a stored value
  back, `null` for anything that is not a theme.
- **`daisyThemeController(theme = DaisyTheme.Dark)`**: a `theme` parameter, declared in
  `codegen/model/theme-controller.json`, writes the input's `value`. Source-compatible; a binary
  change of the function's JVM signature, so `docs/upgrading.md` says to recompile.
- **A profile page in the example app**, reached from the avatar menu's "Profile": one radio
  per theme, each inside a card that renders in that theme (the preview), plus "System default".
  Saving is a plain form POST that sets a cookie and redirects; the dashboard and the profile
  render `<html data-theme=…>` from it. No script, so the strict Content-Security-Policy stands.
- **The example app's stylesheet compiles all 35 themes**: `@plugin "daisyui" { themes: all; }`
  in `example-app/src/main/css/app.css`.
- **A how-to section, "Let users choose a theme"**, compiled by `:doc-snippets`: which themes a
  stylesheet carries (the webjar's `themes.css` has all, a compiled stylesheet only what its
  entry names), the typed attribute, the preview card, the cookie route. `llms` templates name it.
- E2E scenarios for the picker, the preview, the persistence and a refused value.

### The subtractive option, stated

Ship no API and only document `attributes["data-theme"] = "…"`. Rejected: the 35 names are
exactly the kind of string this library exists to type, and the enum is generated, so it costs
no maintenance. What this change removes: a dead menu entry in the example app, and in a
consumer the theme names as strings.


## Out of scope

- **A live whole-page preview** before saving (theme controller radios). Oliver chose swatches,
  2026-11-10; it would be a second, small change.
- **Custom themes** (`@plugin "daisyui/theme" { … }`). The enum lists built-in themes only; a
  consumer's own theme is written as `attributes["data-theme"]`, and the how-to says so.
- **A Gradle-plugin check** that the themes a build names are in its compiled stylesheet. Recorded
  as finding F1; its own change if adopted.
- **The guaranteed Content-Security-Policy as a library value** (a constant and/or a Ktor plugin),
  proposed by the Deploy-Radar on the agent board, 2026-11-10. The profile page only has to pass
  today's policy (A6); shipping the policy is its own change, which would also replace
  `CONTENT_SECURITY_POLICY` in `example-app/.../Application.kt:18`.
- **A ready-made picker component or a Ktor cookie helper** in the library. Oliver chose the typed
  basics, 2026-11-10.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | `src/themes/*.css` is exactly the set `themes: all` compiles | code | confirmed at proposal - the 35 file stems equal the 35 names of `functions/themeOrder.js` (`diff` of both sorted lists is empty), and `pluginOptionsHandler.js` iterates `themeOrder` for `"all"` |
| A2 | Themes reach the stylesheet whatever the plugin's narrowed class list holds | code | to check in task 1.1 - read at proposal: `pluginOptionsHandler.js` emits each theme with `addBase`, which no `@source` gates; observed in the compiled output by task 1.1 and on the served stylesheet by task 3.3 |
| A3 | `@plugin "daisyui" { themes: all; }` compiles in the plugin's `kdaisyuiStylesheet` container and grows the minified stylesheet by roughly 30 KB (raw theme CSS is 38,334 bytes) | code | to check in task 1.1 |
| A4 | A type outside `io.github.ollin.kdaisyui.components` does not trip the plugin's "unknown component class" fallback | code | confirmed at proposal - `gradle-plugin/.../ComponentReferences.kt:11` matches only `io/github/ollin/kdaisyui/components/…`; an enum in that package unknown to `kdaisyui-components.txt` would make the plugin write the full list and warn (`ComponentMap.kt:12-15`) |
| A5 | A `data-theme` on a `div` previews its theme inside a page wearing another | code | to check in task 3.4 - read at proposal: the selector `[data-theme=<name>]` scoping custom properties, which inherit from the nearest ancestor |
| A6 | No requirement of a main spec this builds on contradicts it. `fwb_tasks.constraints list` over the change's `affected_areas` names `api-change-detection`, `ci-job-selection`, `component-model`, `css-delivery`, `daisyui-component-coverage`, `documentation-snippets`, `generated-sources`, `llms-txt` and `mutation-testing`; besides those, read by name: `css-delivery` (the narrowed stylesheet styles the example app, compiled by the plugin's task alone - the entry changes, the task does not), `content-security-policy` (no script, no inline style), `component-model` (the `theme` parameter is declared in the model as an `extra`, as `button.json`'s `type` is; the enum is no class axis, so the naming rule does not apply), `api-change-detection` (the binary change of `daisyThemeController` gets a **How to migrate** entry, task 4.4), `generated-sources` and `documentation-snippets` | code | confirmed at proposal - every listed requirement read on 2026-11-10: the test generator binds classes, not `extra`s (`component-model` 355); `ci-job-selection` and `llms-txt` are served by tasks 4.2 and 5.1; `mutation-testing` 75 by Stryker in 5.1 |
| A7 | `daisyThemeController` gets a typed `theme` parameter | user | confirmed by the user 2026-11-10 - as a parameter, against the recommendation to leave it out (finding F3) |
| A8 | The cookie is the example app's choice, not the library's: the library ships no Ktor helper | user | confirmed by the user 2026-11-10 - typed basics chosen |
| A9 | A model `extra` of type `DaisyTheme?` from another package generates, and the generated tests cover both of its branches | code | to check in task 2.8 - the precedents are `button.json`'s `ButtonType?` with an import and `input.json:59`'s `if (value != null) this.value = value`, which shows `INPUT.value` is writable; revise if `koverVerify` or the test generator objects |

## Capabilities

### New Capabilities

- `theme-selection`: every built-in DaisyUI theme is a typed value, applies to any element, is
  documented with a picker recipe, and the example app's profile lets a user choose one with a
  preview, persisted without a script.

### Modified Capabilities

None.

## Impact

- `codegen/`: a theme generator and its test, wired into `just generate`; Stryker on its lines.
- `lib/`: `lib/generated/main/kotlin/io/github/ollin/kdaisyui/themes/DaisyTheme.kt` (generated),
  `lib/src/main/kotlin/io/github/ollin/kdaisyui/themes/DaisyThemes.kt` and its test; `:lib:pitest`
  scope gains `themes.*`.
- `example-app/`: `app.css`, a profile page and routes, `DashboardShell.kt`, `Ids.kt`,
  `DashboardResources.kt`; `e2e-tests/`: one feature with steps.
- `codegen/model/theme-controller.json` and the regenerated `ThemeController.kt`, its tests and
  `docs/reference/themeController.md`; `lib/api/*.api` re-dumped.
- `docs/how-to.md`, `docs/upgrading.md`, `codegen/llms-template.md`, `codegen/llms-full-template.md`, regenerated
  `llms*.txt`; `AGENTS.md`'s list of hand-written Kotlin.
- Lands in 0.11.0; source-compatible, `daisyThemeController` binary-incompatible (recompile).
