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

Also removed from the first sketch: a `theme` parameter on `daisyThemeController`. The picker
does not use the theme controller (a checked `.theme-controller` re-themes the whole page, which
the swatch preview must not), and `daisyThemeController { value = DaisyTheme.Nord.value }` is
already typed. A7 puts that to Oliver.

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
| A2 | Themes reach the stylesheet whatever the plugin's narrowed class list holds | code | confirmed at proposal - `pluginOptionsHandler.js` emits each theme with `addBase`, which no `@source` gates; the browser check is task 3.2 |
| A3 | `@plugin "daisyui" { themes: all; }` compiles in the plugin's `kdaisyuiStylesheet` container and grows the minified stylesheet by roughly 30 KB (raw theme CSS is 38,334 bytes) | code | to check in task 1.1 |
| A4 | A type outside `io.github.ollin.kdaisyui.components` does not trip the plugin's "unknown component class" fallback | code | confirmed at proposal - `gradle-plugin/.../ComponentReferences.kt:11` matches only `io/github/ollin/kdaisyui/components/…`; an enum in that package unknown to `kdaisyui-components.txt` would make the plugin write the full list and warn (`ComponentMap.kt:12-15`) |
| A5 | A `data-theme` on a `div` previews its theme inside a page wearing another | code | confirmed at proposal by the selector `[data-theme=<name>]` scoping custom properties, which inherit from the nearest ancestor; the browser check is task 3.2 |
| A6 | No requirement of a main spec this builds on contradicts it: `css-delivery` (the narrowed stylesheet styles the example app, compiled by the plugin's task alone - the entry changes, the task does not), `content-security-policy` (no script, no inline style), `component-model` (no model file changes; the enum is no class axis, so the naming rule does not apply), `generated-sources` and `documentation-snippets` | code | confirmed at proposal - each requirement read on 2026-11-10 |
| A7 | `daisyThemeController` gets no `theme` parameter | user | open - ask at review |
| A8 | The cookie is the example app's choice, not the library's: the library ships no Ktor helper | user | confirmed by the user 2026-11-10 - typed basics chosen |

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
  scope gains `themes.*`; `lib/api/lib.api` re-dumped, additive only.
- `example-app/`: `app.css`, a profile page and routes, `DashboardShell.kt`, `Ids.kt`,
  `DashboardResources.kt`; `e2e-tests/`: one feature with steps.
- `docs/how-to.md`, `codegen/llms-template.md`, `codegen/llms-full-template.md`, regenerated
  `llms*.txt`; `AGENTS.md`'s list of hand-written Kotlin.
- Lands in 0.11.0; not breaking.
