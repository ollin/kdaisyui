# kdaisyui

> Type-safe DaisyUI component DSL for Kotlin kotlinx.html: compile-time-checked extension functions that emit DaisyUI class names for server-rendered HTML, with no JavaScript framework. Published on Maven Central as `io.github.ollin.kdaisyui:kdaisyui`. This index describes the `main` branch; for a released version, replace `main` in every URL below with its tag, such as `v0.8.1`.

Five rules before following a link:

- Always use named arguments. Parameter order is generated and changes between releases.
- An enum parameter is a choice of one DaisyUI class group (`color = ButtonColor.Primary` emits `btn-primary`). A `Boolean` parameter is named after the class it toggles (`soft = true` emits `alert-soft`).
- Every function takes `id: HtmlId?`, `extraClasses: String?`, `attrs` for the underlying kotlinx.html tag, and a `content` lambda whose receiver is the element the function renders.
- Every function has a class sibling, `Tag.daisy<Name>Classes(…)`, which adds the same classes to an element you write yourself.
- The library emits class names only. Your Tailwind build needs the class list, which the Gradle plugin `io.github.ollin.kdaisyui` writes (README, Quick start, step 3).

## Docs

- [README](https://raw.githubusercontent.com/ollin/kdaisyui/main/README.md): Quick start with the Gradle dependency block, CSS delivery through the Gradle plugin, and how to migrate between releases
- [Getting started](https://raw.githubusercontent.com/ollin/kdaisyui/main/docs/tutorials/getting-started.md): Set up a project and render buttons, cards, stats, a form and an alert, in ten minutes
- [Build a dashboard with Ktor](https://raw.githubusercontent.com/ollin/kdaisyui/main/docs/tutorials/build-a-dashboard.md): A full admin dashboard with a sidebar, header, stats fragments and htmx lazy loading, in thirty minutes
- [Tutorials](https://raw.githubusercontent.com/ollin/kdaisyui/main/docs/tutorials/index.md): The learning path through the two tutorials
- [How-to guides](https://raw.githubusercontent.com/ollin/kdaisyui/main/docs/how-to.md): Recipes: add a component, custom classes and attributes, nest components, build a form, a sidebar layout, htmx partial loading, a dropdown, validation feedback
- [Explanation](https://raw.githubusercontent.com/ollin/kdaisyui/main/docs/explanation.md): What DaisyUI, kotlinx.html and htmx are, how class merging works, the common parameters, and why the wrappers are thin
- [Component reference](https://raw.githubusercontent.com/ollin/kdaisyui/main/docs/reference.md): The hand-written entry point to the per-component pages
- [Reference index](https://raw.githubusercontent.com/ollin/kdaisyui/main/docs/reference/index.md): One table of every component, its summary and the element it renders

## Components

{{COMPONENTS}}

## Full API

- [llms-full.txt](https://raw.githubusercontent.com/ollin/kdaisyui/main/llms-full.txt): Every component's enums and function signatures in one file, generated from the library, with the full reading rules

## Optional

- [DaisyUI components](https://daisyui.com/components/): The CSS classes the functions wrap, with rendered examples
- [kotlinx.html](https://github.com/Kotlin/kotlinx.html): The HTML builder DSL the functions extend
- [Releases](https://github.com/ollin/kdaisyui/releases): Release notes, one per tag
