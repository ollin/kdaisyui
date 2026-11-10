## ADDED Requirements

### Requirement: The library ships its theme map

The `kdaisyui` artifact SHALL contain a generated resource `kdaisyui-themes.txt` naming each
`DaisyTheme` entry and its `data-theme` value, regenerated with the enum.

#### Scenario: The map follows the enum

- **WHEN** `just generate` runs after a DaisyUI bump that adds a theme
- **THEN** the resource gains the entry and its value, and the drift check fails a commit without it

### Requirement: The plugin warns when code names a theme the stylesheet lacks

The Gradle plugin SHALL warn, after compiling the stylesheet, naming each theme the build's compiled
classes name and the stylesheet has no `[data-theme=…]` selector for, and the `@plugin "daisyui"`
line that adds them. An entry reference names its theme; the whole set or `daisyThemeOf` names all.

**Verified** by `javap`, 2026-11-10: an entry is a `Fieldref` to `DaisyTheme.<Entry>`.

#### Scenario: A theme the stylesheet lacks

- **WHEN** compiled code references `DaisyTheme.Nord` and the stylesheet has no `[data-theme=nord]`
- **THEN** the build logs a warning naming `nord` and the entry line to add

#### Scenario: Every theme chosen at runtime

- **WHEN** compiled code calls `DaisyTheme.entries` and the stylesheet carries only `light` and `dark`
- **THEN** the warning names the other 33

#### Scenario: Nothing to say

- **WHEN** the stylesheet was compiled with `themes: all`, or every named theme has its selector,
  or no class names a theme
- **THEN** no warning is logged

#### Scenario: No stylesheet configured

- **WHEN** a build applies the plugin and configures no stylesheet entry
- **THEN** `check` runs no theme check and no stylesheet compile
