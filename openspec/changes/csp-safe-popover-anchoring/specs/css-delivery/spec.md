## MODIFIED Requirements

### Requirement: The library's contract ends at the class name
The documentation SHALL state that `kdaisyui` produces HTML carrying DaisyUI class names and does
**not** produce the stylesheet, and that obtaining working CSS is the consuming application's
responsibility. The one exception SHALL be named: the few rules the library's own markup needs
beyond DaisyUI's, shipped as `kdaisyui.css` and imported by the application's entry stylesheet.

This is not a disclaimer. It is the fact that makes every other requirement here necessary: a
component can be generated correctly, pass every generated test, and render completely unstyled,
because nothing in the library or its test suite ever evaluates CSS.

**Verified:** the generated component tests assert rendered class strings, and until this change
no test in any module evaluated a computed style except
`NavigationSteps.firstNavHasBackground`, a single check on one element.

#### Scenario: A consumer learns what they must supply
- **WHEN** a developer reads the getting-started documentation
- **THEN** it states that the library emits class names only, plus one small stylesheet of its own
- **AND** it names what the application must provide to turn them into styles

## ADDED Requirements

### Requirement: The library ships the rules its markup needs beyond DaisyUI's

The published `kdaisyui` artifact SHALL contain `kdaisyui.css`, every CSS rule the library's
markup needs that DaisyUI's stylesheet does not provide. The Gradle plugin's `kdaisyuiClasses`
task SHALL write it to `build/kdaisyui/kdaisyui.css` beside the class list, and the
documentation SHALL show the import.

At this change the resource holds two rules, which turn `data-anchor-name` and
`data-position-anchor` into the CSS anchor properties.

**Verified** that the rules are what the markup needs: without them, the popover dropdown of the
example application opens in the middle of the viewport (the measurement the
`daisyui-component-coverage` E2E scenario makes); with them in the page, the probe of 2026-11-07
in headless Chromium 156 shows it at its trigger.

**Assumed** that a Tailwind 4 entry stylesheet applies a relative `@import` of a plain CSS file
unchanged. *Wrong if:* the compiled stylesheet of the example application does not contain the
`attr(` rules after task 1.2.

#### Scenario: The resource cannot silently stop being packaged
- **WHEN** `kdaisyui.css` is missing from the built artifact
- **THEN** a test fails

#### Scenario: The plugin writes it beside the class list
- **WHEN** `kdaisyuiClasses` runs against a build that depends on `kdaisyui`
- **THEN** `build/kdaisyui/kdaisyui.css` holds the artifact's `kdaisyui.css` byte for byte

#### Scenario: The example application's stylesheet carries the rules
- **WHEN** the example application's stylesheet is compiled
- **THEN** it contains a rule for `[data-anchor-name]` and one for `[data-position-anchor]`
