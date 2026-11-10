## ADDED Requirements

### Requirement: The plugin warns when code names a theme the stylesheet lacks

The Gradle plugin SHALL warn, naming each theme the build's compiled classes name and its compiled
stylesheet has no `[data-theme=…]` selector for, and the `@plugin "daisyui"` line that adds them.
An entry reference names its theme; the whole set or `daisyThemeOf` names every theme.

**Verified** by `javap`, 2026-11-10: an entry is a `Fieldref` to `DaisyTheme.<Entry>`.

#### Scenario: A theme the stylesheet lacks

- **WHEN** compiled code references `DaisyTheme.Nord` and the stylesheet has no `[data-theme=nord]`
- **THEN** the build logs a warning naming `nord` and the entry line to add

#### Scenario: Every theme chosen at runtime

- **WHEN** compiled code calls `DaisyTheme.entries` and the stylesheet carries only `light` and `dark`
- **THEN** the warning names the 33 others

#### Scenario: Nothing to say

- **WHEN** every named theme has its selector, or no class names a theme
- **THEN** no warning is logged
