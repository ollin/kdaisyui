## Why

**The documentation promises something the documented setup cannot do.**

`docs/explanation.md:137` tells consumers that `extraClasses` takes "responsive variants
(`"lg:btn-lg"`)". `docs/tutorials/build-a-dashboard.md:113-116` teaches the CSS setup: link the
prebuilt `daisyui.css` webjar, then load Tailwind's **browser** build. In that setup `lg:btn-lg`
compiles to nothing at all and the class silently does nothing.

Measured on the running example app at 390px (evidence in `support-popover-megamenu/notes.md`):

| Where | `.megamenu` | `.megamenu-vertical` | `max-sm:megamenu-vertical` | `sm:hidden` |
|---|---|---|---|---|
| `daisyui.css` webjar, 1.1 MB prebuilt | present | present | **absent** | absent |
| Tailwind browser `<style>`, 6 KB runtime | absent | absent | **absent** | present |

Tailwind can only build a variant of a class it owns. `hidden` is its own utility, so `sm:hidden`
is generated. `megamenu-vertical` arrives in a stylesheet Tailwind never sees, so no variant of it
can ever be generated. This is not specific to megamenu — it holds for **every**
`variant:daisy-class` pair: `lg:btn-lg`, `md:card-side`, `hover:badge-primary`, `dark:alert-info`.

The failure mode is the one this project has now paid for three times: **nothing breaks.** No test
fails, no console error, no missing file. The class sits in the HTML and does nothing, and the
only way to notice is for a person to look at a rendered page and say "that should have moved".
That is exactly how it was found.

Two audiences are affected and only one of them is ours to fix directly:

1. **The example app** renders a megamenu that cannot go vertical, and would silently ignore any
   responsive DaisyUI class added to it. It is the reference consumers copy.
2. **Library consumers** get class names from `kdaisyui` and must produce CSS themselves. Nothing
   in the documentation tells them their CSS pipeline has to be able to see the class names
   `kdaisyui` generates — which is a real constraint, because those names are produced by Kotlin
   at runtime and never appear in a file Tailwind scans.

Point 2 is the more important half and the reason this is not merely a demo cleanup.

## What Changes

- Compile Tailwind at build time for `:example-app`, so `variant:daisy-class` works there.
- Document, for consumers, how to set up CSS for a kdaisyui project — including the part nobody
  would guess: **Tailwind's content scanner cannot see class names that only exist inside compiled
  Kotlin**, so a safelist or an equivalent mechanism is required.
- Correct `docs/explanation.md` and `docs/tutorials/build-a-dashboard.md`, which currently teach a
  setup that cannot keep the promise made a few lines earlier.
- Keep a no-build path documented and honest about its limits, rather than deleting it — it is
  genuinely the right choice for a prototype, and the CDN/browser setup is what DaisyUI itself
  offers for that case.

**Explicitly not in scope:** `:lib` gains no Node dependency and no CSS pipeline. A clone still
builds and tests with no Node and no submodules. This changes the demo and the docs only.

## Capabilities

### Added Capabilities

- `css-delivery`: what a kdaisyui consumer must do to get working CSS, what the library does and
  does not provide, and the scanner limitation that makes this non-obvious.

### Modified Capabilities

- `daisyui-component-coverage`: the coverage requirements assume a generated class reaching the
  page as working CSS. Worth stating that the library's contract ends at the class name, so a
  component can be correct and still render unstyled.

## Assumptions

Ordered by how much rests on them. `tasks.md` checks them in this order.

1. **Tailwind can be compiled in this Gradle build without adding Node to it.** DaisyUI documents
   a [standalone executable](https://daisyui.com/docs/install/standalone/) for exactly this case,
   and `daisyui/skills/daisyui/install/SKILL.md:41` names it. *Wrong if:* the standalone CLI
   cannot resolve the DaisyUI plugin, or there is no sane way to obtain the binary per-platform
   from Gradle — then the options are an npm-based Gradle step (Node in the demo build, which the
   project has deliberately avoided) or checking a compiled CSS file into the repo. **Check this
   first; it decides the shape of everything after it.**
2. **A safelist is required, and works.** Tailwind scans source files for class names. kdaisyui
   emits its classes from compiled Kotlin, so a scan of `.kt` files finds string fragments at
   best and nothing at all for classes assembled from enums. *Wrong if:* scanning the generated
   Kotlin sources happens to be sufficient — worth testing, because it would be a much nicer
   answer than a safelist.
3. **The generated components give us the complete class list for free.** Every class the library
   can emit is known at codegen time, so the safelist can be generated rather than hand-kept.
   *Wrong if:* classes assembled at runtime from `extraClasses` escape it — they do, by
   definition, which is a limit to document rather than solve.
4. **The E2E suite will catch a regression here once the setup is right.** *Wrong if:* the
   existing assertions pass either way — which is the current situation, so a new assertion that
   fails on unstyled output is part of the work, not a side effect of it.

## Impact

- `example-app/build.gradle.kts` — a CSS compilation step, and the served stylesheet changes from
  the webjar to the compiled output.
- `example-app` page heads — the browser-Tailwind `<script>` goes away.
- `e2e-tests` — an assertion that a `variant:daisy-class` actually applies, which fails today.
- `docs/explanation.md`, `docs/tutorials/build-a-dashboard.md`, `README.md` — the consumer story.
- `openspec/specs/` — the new `css-delivery` capability.
- Possibly `codegen/` — if the safelist is generated from the same data the components are.
- **Not** `:lib`, `:ktor-integration`, `:bom`, or the release pipeline.
