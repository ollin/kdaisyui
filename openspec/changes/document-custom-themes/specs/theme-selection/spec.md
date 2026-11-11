## ADDED Requirements

### Requirement: The how-to shows a theme of one's own

The how-to SHALL show the `@plugin "daisyui/theme"` block in the stylesheet entry and a snippet
`:doc-snippets` compiles that writes the theme's name as `data-theme` and as a theme controller's
`value`, and SHALL state that `daisyThemeOf` and the plugin's theme check do not know the theme.

**Verified** 2026-11-11 by the proposal's A1 (the block compiles to both selectors) and A2
(`ThemeCheck.kt:7-10`).

#### Scenario: A reader adds a theme of their own

- **WHEN** a developer follows the how-to to add a branded theme
- **THEN** it has shown the CSS block and a compiled snippet using the name on the page and on a
  theme controller
- **AND** it has told them that `daisyThemeOf` and the theme check do not know the theme

### Requirement: The full API file names custom themes

`llms-full.txt` SHALL state, in its Themes section, that a theme of one's own is declared with
`@plugin "daisyui/theme"` and written as a `data-theme` string, and that `DaisyTheme`,
`daisyThemeOf` and the plugin's theme check know only the built-in themes.

**Assumed**: an agent reading only `llms-full.txt` follows it. *Wrong if:* such an agent still
writes `DaisyTheme.Acme` or `daisyTheme("acme")`.

#### Scenario: An agent looks up custom themes

- **WHEN** an agent reads the Themes section of `llms-full.txt`
- **THEN** it finds the `@plugin "daisyui/theme"` declaration and the `data-theme` string
- **AND** it learns that the typed theme API covers built-in themes only
