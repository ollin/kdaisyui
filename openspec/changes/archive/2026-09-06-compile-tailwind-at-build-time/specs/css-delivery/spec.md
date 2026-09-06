## ADDED Requirements

### Requirement: The library's contract ends at the class name
The documentation SHALL state that `kdaisyui` produces HTML carrying DaisyUI class names and does
**not** produce CSS, and that obtaining working CSS is the consuming application's responsibility.

This is not a disclaimer. It is the fact that makes every other requirement here necessary: a
component can be generated correctly, pass every generated test, and render completely unstyled,
because nothing in the library or its test suite ever evaluates CSS.

**Verified:** the generated component tests assert rendered class strings, and until this change
no test in any module evaluated a computed style except
`NavigationSteps.firstNavHasBackground`, a single check on one element.

#### Scenario: A consumer learns what they must supply
- **WHEN** a developer reads the getting-started documentation
- **THEN** it states that the library emits class names only
- **AND** it names what the application must provide to turn them into styles

### Requirement: The supported CSS setup makes every Tailwind variant work
The documentation SHALL describe a CSS setup in which any Tailwind variant of a DaisyUI class —
`lg:btn-lg`, `max-sm:card-side`, `dark:alert-info` — actually applies, and the example application
SHALL use that setup.

**Verified by measurement.** The prebuilt `daisyui.css` webjar ships exactly five variant prefixes
pre-generated — `sm:` `md:` `lg:` `xl:` (1198 rules each) and `hover:` (60) — and nothing else.
Tailwind's browser build contributes no DaisyUI variants at all, because it can only generate
variants of utilities it owns. So `lg:btn-lg` works and `max-sm:card-side` silently does nothing.
Compiling with the DaisyUI plugin produces both; the example app's stylesheet is 23 KB against the
webjar's 1123 KB, because a compile emits only what is reachable.

**The failure this prevents is a wrong general rule, not a broken build.** A developer tries
`lg:btn-lg`, sees it work, concludes that variants are supported, and later loses `dark:alert-info`
with no error anywhere. The boundary between the two is not a contract — it is whatever DaisyUI
chose to pre-generate in the pinned release.

#### Scenario: A variant of a DaisyUI class applies
- **WHEN** an element carries a Tailwind variant of a DaisyUI class
- **AND** the viewport or state matches that variant's condition
- **THEN** the corresponding CSS rule applies to the element

#### Scenario: A variant that fails to compile is caught automatically
- **WHEN** a variant of a DaisyUI class does not reach the stylesheet
- **THEN** an automated check fails
- **AND** the failure is not left to be noticed by someone looking at a rendered page

### Requirement: The library ships the class list a consumer's CSS build needs
The published `kdaisyui` artifact SHALL contain a generated, complete list of every DaisyUI class
the library can emit, as a resource a consumer can extract and hand to their own CSS build. The
documentation SHALL show how.

**A consumer cannot obtain these class names any other way.** `btn-primary` is assembled at
runtime from `ButtonVariant.Primary`, so it appears in no file a content scanner reads, and the
published jar carries compiled classes and no Kotlin sources.

**Verified end to end** from the published jar alone: extracting `kdaisyui-classes.txt`, compiling
against it plus a consumer source file in which `btn-primary` occurs zero times, produces a
stylesheet containing `.btn-primary`, `.btn-lg` and `.max-sm\:card-side`. 555 classes, 7 KB in the
jar, 552 KB of resulting CSS.

**Assumed:** that the list stays in step with the components. *Wrong if:* a regeneration changes
the components without changing the list — which `generated-sources-drift` is what stands behind,
since both are generated into `lib/generated/` by the same task.

#### Scenario: A class only reachable through an enum reaches the stylesheet
- **WHEN** an application uses a generated component whose class name is assembled at runtime
- **AND** its CSS build follows the documented setup
- **THEN** that class is present in the compiled stylesheet

#### Scenario: The resource cannot silently stop being packaged
- **WHEN** the class list is missing from the built artifact
- **THEN** a test fails

### Requirement: The size and its remedy are stated honestly
The documentation SHALL state that the documented setup produces a stylesheet covering every class
the library can emit, and SHALL NOT instruct a reader to hand-maintain a narrower list.

A hand-kept safelist is followed until the day it is not, and its failure mode is the silent one
this capability exists to remove. Narrowing the output to what an application actually uses
requires knowing which generated functions and enum entries a codebase references — tooling, not
diligence.

#### Scenario: A reader can predict the cost
- **WHEN** a developer follows the documented setup
- **THEN** the documentation has told them the approximate size of the result
- **AND** why it covers more than their application uses

#### Scenario: The escape hatch's limit is stated
- **WHEN** a consumer passes an arbitrary class through `extraClasses`
- **THEN** the documentation says that class must be visible to their own Tailwind setup
- **AND** does not imply the library can account for it
