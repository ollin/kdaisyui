# kdaisyui — llms.txt

> Type-safe DaisyUI component DSL for Kotlin kotlinx.html.
> Wraps DaisyUI's CSS classes into compile-time-checked Kotlin extension functions.
> No JavaScript framework. The library emits class names; your Tailwind build turns them into CSS.

## Quick start

### Dependency

```kotlin
// build.gradle.kts
repositories {
    mavenCentral()
}

dependencies {
    // Import the BOM once (replace VERSION with the latest release), then declare
    // the artifacts without versions — the BOM keeps them aligned.
    implementation(platform("io.github.ollin.kdaisyui:kdaisyui-bom:VERSION"))
    implementation("io.github.ollin.kdaisyui:kdaisyui")

    // Optional Ktor Resources integration. It brings no Ktor and no web assets:
    // add io.ktor:ktor-server-resources at your own Ktor version.
    implementation("io.github.ollin.kdaisyui:kdaisyui-ktor-integration")
}
```

### Import

```kotlin
import io.github.ollin.kdaisyui.components.*
```

### Minimal example

```kotlin
import io.github.ollin.kdaisyui.components.*
import kotlinx.html.div
import kotlinx.html.stream.createHTML

val html = createHTML().div {
    daisyButton("Deploy", variant = ButtonVariant.Primary, size = ButtonSize.Lg)
}
// → <div><button class="btn btn-primary btn-lg">Deploy</button></div>
```

### CSS

kdaisyui emits class names only. The jar ships `kdaisyui-classes.txt`, the list of every class it
can emit; point your Tailwind `@source` at it, because a class assembled at runtime from an enum
appears in none of your source files. See the README's Quick start, step 3.

## Reading the signatures below

Every signature is generated from the library itself, so it is exact for the release this file
shipped with.

- **Enum parameters are typed `ClassValues<E>?`.** Pass the enum constant: `size = ButtonSize.Lg`.
  Every generated enum *is* a `ClassValues` of itself, so nothing needs wrapping.
- **An enum parameter is a choice.** Its constants are DaisyUI classes a browser measured as
  mutually exclusive, so exactly one can apply. Classes that can be combined are separate
  `Boolean` parameters instead.
- **A `Boolean` parameter is named after its DaisyUI class**: `soft = true` emits `alert-soft`.
- **CSS class to Kotlin**: `btn-primary` → `ButtonVariant.Primary`, `btn-lg` → `ButtonSize.Lg`.
- **Always use named arguments.** Parameter order is generated and changes between releases.
- **The lambda receiver is the element the function renders** — `daisyDropdownContent` renders
  `<ul>`, so its `content` lambda runs on `UL`.

Every function also takes these:

| Parameter | Type | Description |
|---|---|---|
| `id` | `HtmlId?` | Type-safe HTML id from the `HtmlId` hierarchy |
| `extraClasses` | `String?` | Additional CSS classes, merged without duplicates |
| `attrs` | `(TAG.() -> Unit)?` | Direct access to the underlying kotlinx.html tag |
| `content` | `(TAG.() -> Unit)` | Nested HTML content — required, except where the function also takes `text: String?` (then optional) or renders an element that cannot hold children (then absent) |

## Type-safe HTML IDs

Define a hierarchy of IDs as nested classes extending `AnnotatedIdBase`:

```kotlin
import io.github.ollin.kdaisyui.core.AnnotatedIdBase
import io.github.ollin.kdaisyui.core.htmlId

class Dashboard : AnnotatedIdBase("dashboard") {
    class Sidebar(parent: Dashboard = Dashboard()) : AnnotatedIdBase("sidebar", parent)
    class Header(parent: Dashboard = Dashboard()) : AnnotatedIdBase("header", parent)
}

// Usage in components:
daisyButton("Save", id = Dashboard.Sidebar())
// → <button id="dashboard-sidebar" class="btn">Save</button>

// String literal IDs also work:
daisyButton("Save", id = htmlId("my-button"))
// → <button id="my-button" class="btn">Save</button>

// CSS target selector for anchor links:
Dashboard.Sidebar().target  // → "#dashboard-sidebar"
```

## Core utility

```kotlin
import io.github.ollin.kdaisyui.core.addClassNames

// Merges CSS classes safely — deduplicates, preserves order
div {
    addClassNames("flex items-center gap-4")
}
```

## Components

{{COMPONENTS}}
## Requirements

- JDK 21+
- Kotlin, kotlinx-html and the DaisyUI version the components were generated from: exact
  versions are in `gradle/libs.versions.toml` at the release tag you depend on.

## Links

- Source: https://github.com/ollin/kdaisyui
- Component reference: https://github.com/ollin/kdaisyui/tree/main/docs/reference
- DaisyUI: https://daisyui.com
- kotlinx.html: https://github.com/Kotlin/kotlinx.html
