## ADDED Requirements

### Requirement: The library's contract ends at the class name
The documentation SHALL state that `kdaisyui` produces HTML carrying DaisyUI class names and does
**not** produce CSS, and that obtaining working CSS is the consuming application's responsibility.

This is not a disclaimer. It is the fact that makes every other requirement here necessary: a
component can be generated correctly, pass every generated test, and render completely unstyled,
because nothing in the library or its test suite ever evaluates CSS.

**Verified:** the generated component tests assert rendered class strings
(`lib/generated/test/.../*Test.kt`), and no test in any module asserts a computed style except
`NavigationSteps.firstNavHasBackground`, which is a single E2E check on one element.

#### Scenario: A consumer learns what they must supply
- **WHEN** a developer reads the getting-started documentation
- **THEN** it states that the library emits class names only
- **AND** it names what the application must provide to turn them into styles

### Requirement: A supported CSS setup makes Tailwind variants of DaisyUI classes work
The documentation SHALL describe at least one CSS setup in which a Tailwind variant of a DaisyUI
class — `lg:btn-lg`, `max-sm:megamenu-vertical` — actually applies, and the example application
SHALL use that setup.

**Verified:** the setup currently taught cannot do this. Measured on the running example app:
`max-sm:megamenu-vertical` is absent from both the prebuilt `daisyui.css` webjar and the
Tailwind browser build's output, because Tailwind can only generate a variant of a class it owns
and DaisyUI's classes arrive in a stylesheet it never sees. Evidence in
`support-popover-megamenu/notes.md`.

**Assumed:** that a build-time compilation is achievable here without adding Node to the Gradle
build. *Wrong if:* the Tailwind standalone executable cannot resolve the DaisyUI plugin — in which
case the honest outcome is to keep the browser build and document its limit rather than promise
otherwise. Checked by task 1.2 before anything is wired in.

#### Scenario: A responsive DaisyUI class applies
- **WHEN** an element carries a Tailwind variant of a DaisyUI class
- **AND** the viewport matches that variant's condition
- **THEN** the corresponding CSS rule applies to the element

#### Scenario: The failure is visible when it happens
- **WHEN** a variant of a DaisyUI class fails to compile
- **THEN** an automated check fails
- **AND** the failure is not left to be noticed by someone looking at a rendered page

### Requirement: The scanner limitation is documented
The documentation SHALL state that Tailwind's content scanner cannot see class names that exist
only inside compiled Kotlin, and SHALL name the remedy.

A consumer has no way to discover this from the API. They write `daisyButton(size = ButtonSize.Lg)`,
Tailwind scans their sources, finds no `btn-lg`, and omits the rule — and the button renders
unstyled with no error anywhere. The class name is assembled at runtime from an enum's value and
never appears as a literal in any file a scanner reads.

**Assumed:** that scanning the generated Kotlin sources is insufficient and a safelist is needed.
*Wrong if:* the scan picks up enough — the class values do appear as string literals in the
generated enum constructors, so this may work and would make the consumer story much simpler.
Measured by task 2.1 before anything is documented.

#### Scenario: A consumer's build produces the classes their code can emit
- **WHEN** an application uses a generated component whose class name is assembled at runtime
- **THEN** the documented setup still produces that class in the compiled stylesheet

#### Scenario: The limit of the remedy is stated
- **WHEN** a consumer passes an arbitrary class through `extraClasses`
- **THEN** the documentation says that class must be visible to their own Tailwind setup
- **AND** does not imply the library can account for it
