## ADDED Requirements

### Requirement: Every built-in DaisyUI theme is a typed value

The library SHALL offer an enum `DaisyTheme` with one entry per built-in theme of the pinned
DaisyUI, each carrying its `data-theme` value, generated from DaisyUI's theme sources by
`just generate`. An entry is the PascalCase form of the theme name: `nord` gives `DaisyTheme.Nord`.

**Verified** that the theme sources are the set DaisyUI compiles for `themes: all`: the 35 stems
of `daisyui/packages/daisyui/src/themes/*.css` equal the 35 names of `functions/themeOrder.js`,
2026-11-10 (proposal A1).

#### Scenario: The enum follows DaisyUI

- **WHEN** a DaisyUI bump adds or removes a theme file and `just generate` runs
- **THEN** `DaisyTheme` gains or loses that entry
- **AND** CI's `generated-sources-drift` fails a commit that carries the bump without it

### Requirement: The theme types leave the plugin's narrowing intact

`DaisyTheme` and the functions beside it SHALL live in the package
`io.github.ollin.kdaisyui.themes`, outside the package the Gradle plugin reads as components.

**Verified** that this is necessary: the plugin treats every class under
`io/github/ollin/kdaisyui/components/` as a component (`ComponentReferences.kt:11`), and one the
map does not know makes it write the full class list (`ComponentMap.kt:12-15`, proposal A4).

#### Scenario: Using the enum keeps the stylesheet narrow

- **WHEN** an application references `DaisyTheme` and the kdaisyui Gradle plugin writes its class list
- **THEN** the list holds only the classes of the components the application uses
- **AND** the plugin logs no warning about an unknown class

### Requirement: A theme applies to any element by a typed call

The library SHALL offer `Tag.daisyTheme(theme: DaisyTheme)`, which writes `data-theme` with the
theme's value, and `daisyThemeOf(value: String): DaisyTheme?`, which returns the theme whose
value equals its argument and `null` for any other string.

#### Scenario: A page wears a theme

- **WHEN** `daisyTheme(DaisyTheme.Nord)` is called on the `html` element
- **THEN** the element carries `data-theme="nord"`

#### Scenario: A stored value is read back

- **WHEN** `daisyThemeOf("nord")` is called
- **THEN** it returns `DaisyTheme.Nord`
- **AND** `daisyThemeOf("Nord")` and `daisyThemeOf("")` return `null`

### Requirement: The theme controller takes a typed theme

`daisyThemeController` SHALL take a parameter `theme: DaisyTheme? = null`, declared in the
component model, which writes the input's `value` when given and nothing when `null`.

**Verified** that the value is what DaisyUI matches: the selector is
`:root:has(input.theme-controller[value=<name>]:checked)` (`pluginOptionsHandler.js:27`).

#### Scenario: A controller for one theme

- **WHEN** `daisyThemeController(theme = DaisyTheme.Dark)` is rendered
- **THEN** the input carries `class="theme-controller"` and `value="dark"`

#### Scenario: Without a theme

- **WHEN** `daisyThemeController()` is rendered
- **THEN** the input carries no `value`

### Requirement: The documentation shows how a user chooses a theme

The how-to SHALL show, in snippets `:doc-snippets` compiles, how to compile the themes an
application offers, apply one to the page, preview each in a card, and persist the choice on the
server without a script.

#### Scenario: A reader builds a theme picker

- **WHEN** a developer follows the how-to
- **THEN** they have compiled snippets for the stylesheet entry, the page attribute, the preview
  card and the route that stores the choice

### Requirement: The documentation states which themes exist

The how-to SHALL state that `DaisyTheme` names every built-in theme while the stylesheet decides
which exist, that a theme the stylesheet lacks renders as the default without an error, and that
a custom theme is written as a `data-theme` attribute.

**Verified** that the paths differ: the webjar's `themes.css` (tutorial, line 118) carries every
theme; a compiled entry gets `light` and `dark` unless it names more (`pluginOptionsHandler.js:9`).

#### Scenario: A reader compiles the themes they offer

- **WHEN** a developer follows the how-to with the compiled stylesheet
- **THEN** it has told them to name the themes in the entry's `@plugin "daisyui"` block
- **AND** that a theme the entry does not name renders as the default theme

### Requirement: The example app's profile previews every theme

The example application SHALL serve a profile page, linked from the avatar menu, offering every
`DaisyTheme` and "System default" as radio choices, each built-in theme inside a card rendered
in that theme. The page SHALL need no script.

**Assumed** that the compiled stylesheet carries all 35 themes once the entry says `themes: all`.
*Wrong if:* the card of a theme other than `light` computes the default `--color-base-100`, which
the E2E scenario asserts for every card.

#### Scenario: Every theme is previewed in itself

- **WHEN** the profile page is open
- **THEN** it offers one choice per `DaisyTheme` entry and one for the system default
- **AND** each theme's card computes the background of its own theme

#### Scenario: Reached from the menu

- **WHEN** the user opens the avatar menu and clicks "Profile"
- **THEN** the profile page is shown

### Requirement: The example app persists the chosen theme without a script

Saving SHALL be a form POST that stores the choice in a cookie and redirects to the profile. The
dashboard and the profile SHALL render `data-theme` on `html` from that cookie, and no attribute
for "System default".

#### Scenario: A chosen theme persists across pages

- **WHEN** the user chooses `nord` and saves
- **THEN** the response redirects to the profile with the `nord` choice checked
- **AND** the dashboard's `html` element then carries `data-theme="nord"`

#### Scenario: The system default removes the attribute

- **WHEN** the user chooses "System default" and saves
- **THEN** the `html` element of the next page carries no `data-theme`

### Requirement: The example app refuses a value that is not a theme

A POST naming a theme `DaisyTheme` does not have SHALL be answered `400 Bad Request`, and the
cookie SHALL stay unchanged.

#### Scenario: A value that is not a theme

- **WHEN** a POST names the theme `no-such-theme`
- **THEN** the response is `400 Bad Request` and no cookie is set
