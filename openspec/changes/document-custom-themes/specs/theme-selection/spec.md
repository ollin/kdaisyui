## MODIFIED Requirements

### Requirement: The documentation states which themes exist

The how-to SHALL state that `DaisyTheme` names every built-in theme while the stylesheet decides
which exist, that a theme the stylesheet lacks renders as the default without an error, and how a
theme of one's own is added and used: the `@plugin "daisyui/theme"` block in the stylesheet
entry, and its name written as `data-theme` and as a theme controller's `value`, in a snippet
`:doc-snippets` compiles. It SHALL state that such a theme has no `DaisyTheme` entry, that
`daisyThemeOf` returns `null` for it, and that the plugin's theme check does not see it.

**Verified** that the paths differ: the webjar's `themes.css` (tutorial, line 118) carries every
theme; a compiled entry gets `light` and `dark` unless it names more (`pluginOptionsHandler.js:9`).

**Verified** 2026-11-11 (proposal A1) that a custom theme compiles in the plugin's stylesheet task
to `:root:has(input.theme-controller[value=<name>]:checked),[data-theme=<name>]`, and (A2) that
`ThemeCheck.kt` derives the themes it checks only from `DaisyTheme` and `daisyThemeOf`.

#### Scenario: A reader compiles the themes they offer

- **WHEN** a developer follows the how-to with the compiled stylesheet
- **THEN** it has told them to name the themes in the entry's `@plugin "daisyui"` block
- **AND** that a theme the entry does not name renders as the default theme

#### Scenario: A reader adds a theme of their own

- **WHEN** a developer follows the how-to to add a branded theme
- **THEN** it has shown the `@plugin "daisyui/theme"` block and a compiled snippet that writes the
  theme's name on the page and on a theme controller
- **AND** it has told them that `daisyThemeOf` and the plugin's theme check do not know the theme

## ADDED Requirements

### Requirement: The full API file names custom themes

`llms-full.txt` SHALL state, in its Themes section, that a theme of one's own is declared with
`@plugin "daisyui/theme"` and written as a `data-theme` string, and that `DaisyTheme`,
`daisyThemeOf` and the plugin's theme check know only the built-in themes.

**Assumed**: an agent reading `llms-full.txt` for a branded theme follows it rather than inventing
a `DaisyTheme` entry. *Wrong if:* an agent given only `llms-full.txt` still writes
`DaisyTheme.Acme` or `daisyTheme("acme")`.

#### Scenario: An agent looks up custom themes

- **WHEN** an agent reads the Themes section of `llms-full.txt`
- **THEN** it finds the `@plugin "daisyui/theme"` declaration and the `data-theme` string
- **AND** it learns that the typed theme API covers built-in themes only
