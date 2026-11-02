# How-to guides

Task-oriented recipes for common goals. Each guide assumes you have kdaisyui set up and know the basics.

## Set up a development environment

Building and testing needs **one JDK, 21 or newer**, and Docker for `./gradlew check`, which
compiles the example app's stylesheet in a container. The Gradle wrapper downloads Gradle (exact
version in [`gradle/wrapper/gradle-wrapper.properties`](../gradle/wrapper/gradle-wrapper.properties)),
and the generated sources are committed, so no Node and no git submodules are involved:

```bash
git clone https://github.com/ollin/kdaisyui
cd kdaisyui
./gradlew :lib:test
```

### The exact JDK CI uses

[`.tool-versions`](../.tool-versions) names the JDK that runs Gradle in CI and in the release, and
the Node that `just generate` needs. CI reads it directly, so it is the one pin. To run exactly
those versions, let a tool that reads the file install them, [asdf](https://asdf-vm.com/) or
[mise](https://mise.jdx.dev/):

```bash
asdf install    # or: mise install
```

Any other JDK 21 or newer builds the same code: the JDK that *compiles* it is a separate pin,
`jvmToolchain(21)`, which Gradle provisions itself.

## Run the E2E tests

The E2E tests drive a real Chromium through Playwright for Java, with Kotest and Cucumber. They
need no Node: Gradle runs them, and the example app's Ktor server starts in the test process, so
there is nothing to start first.

```bash
just e2e                         # or: ./gradlew :e2e-tests:test
```

The build uses a Chromium already on the machine: the first of `chromium`, `chromium-browser`,
`google-chrome-stable` and `google-chrome` on the `PATH`, or the one
`PLAYWRIGHT_CHROMIUM_EXECUTABLE_PATH` names. On a machine with none, install Playwright's own
once:

```bash
./gradlew :e2e-tests:playwrightInstall
```

Like `check`, the run needs Docker, because the example app compiles its stylesheet in a
container. The report lands in `e2e-tests/build/reports/tests/test/`.

## Add a component to your page

Every component is a `FlowContent` extension function. Call it inside any kotlinx.html block element:

```kotlin
createHTML().div {
    daisyButton("Save", color = ButtonColor.Primary)
}
```

Components work inside `div`, `section`, `main`, `form`, `td`, `li` — anywhere kotlinx.html accepts flow content.

## Add custom CSS classes

Use the `extraClasses` parameter. Classes are merged safely — no duplicates, no broken whitespace:

```kotlin
daisyButton("Wide", color = ButtonColor.Primary, extraClasses = "w-full mt-4")
// → class="btn btn-primary w-full mt-4"
```

This works on every component:

```kotlin
daisyCard(extraClasses = "bg-base-100 shadow-xl") { /* ... */ }
daisyInput(extraClasses = "rounded-full max-sm:w-24")
daisyBadge("New", extraClasses = "indicator-item")
```

## Add custom HTML attributes

Set them at the start of the component's lambda, where `this` is the raw kotlinx.html tag:

```kotlin
daisyButton {
    id = "save-btn"
    attributes["data-action"] = "submit"
    attributes["aria-label"] = "Save changes"
    +"Save"
}
```

`this` is the underlying HTML tag (`BUTTON`, `DIV`, etc.) with full access to all kotlinx.html properties. Write attributes before the first child or text: kotlinx.html rejects an attribute once the element's content has started.

## Nest components

Components compose by nesting, just like HTML:

```kotlin
daisyCard(extraClasses = "bg-base-100 shadow-xs") {
    daisyCardBody {
        daisyCardTitle {
            +"Orders"
            daisyBadge("3", color = BadgeColor.Success, size = BadgeSize.Sm)
        }
        daisyTable(zebra = true) {
            tbody {
                tr { td { +"Order #1001" }; td { +"$250" } }
                tr { td { +"Order #1002" }; td { +"$180" } }
            }
        }
        daisyButton("View all", color = ButtonColor.Primary)
    }
}
```

Since every component is a `FlowContent` extension, they nest naturally inside each other.

## Build a form

Combine fieldsets, labels, inputs, and form controls:

```kotlin
form {
    daisyFieldset {
        daisyLabel("Email")
        daisyInput(placeholder = "you@example.com", extraClasses = "w-full")
    }
    daisyFieldset {
        daisyLabel("Role")
        daisySelect(extraClasses = "w-full") {
            option { disabled = true; selected = true; +"Choose..." }
            option { +"Admin" }
            option { +"Editor" }
            option { +"Viewer" }
        }
    }
    daisyFieldset {
        label("flex cursor-pointer justify-between py-2") {
            span("label") { +"Public profile" }
            daisyToggle(size = ToggleSize.Sm, checked = true)
        }
    }
    daisyFieldset {
        label("flex cursor-pointer gap-4") {
            daisyCheckbox(size = CheckboxSize.Sm, checked = true)
            span("label-text") { +"Accept terms of use" }
        }
    }
    daisyFieldset {
        daisyLabel("Priority")
        daisyRange(size = RangeSize.Xs, min = "0", max = "100", value = "50", step = "25")
    }
    daisyButton("Submit", color = ButtonColor.Primary, extraClasses = "w-full")
}
```

Pattern: `daisyFieldset` wraps each field group. `daisyLabel` adds the label. The input component follows.

## Create a sidebar layout

Use the DaisyUI drawer pattern:

<!-- snippet: in HTML -->
```kotlin
body("drawer lg:drawer-open min-h-screen") {
    // Hidden checkbox toggles the drawer on mobile
    input {
        id = "my-drawer"
        type = InputType.checkBox
        classes = setOf("drawer-toggle")
    }
    // Main content area
    main("drawer-content") {
        div("p-6") {
            h1 { +"Page content" }
        }
    }
    // Sidebar
    aside("drawer-side z-10") {
        label {
            htmlFor = "my-drawer"
            classes = setOf("drawer-overlay")
        }
        nav("bg-base-100 min-h-screen w-72 px-6 py-10") {
            daisyMenu(extraClasses = "w-full") {
                li { a("menu-active") { +"Dashboard" } }
                li { a { +"Users" } }
                li { a { +"Settings" } }
                li {
                    details {
                        summary { +"Products" }
                        ul {
                            li { a { +"All Products" } }
                            li { a { +"Add New" } }
                        }
                    }
                }
            }
        }
    }
}
```

The drawer is responsive: on large screens (`lg:drawer-open`) the sidebar is always visible. On mobile, the checkbox toggle shows/hides it.

![Sidebar layout with DaisyUI drawer](../screenshots/sidebar.png)

## Use htmx for partial loading

Split your page into a shell (loaded immediately) and fragments (loaded on demand).

**Step 1**: Add a placeholder div with htmx attributes:

```kotlin
div {
    attributes["hx-get"] = "/fragments/stats"
    attributes["hx-trigger"] = "load"     // fetch immediately
    attributes["hx-swap"] = "outerHTML"   // replace this div entirely
    span("loading loading-spinner loading-lg") {}
}
```

**Step 2**: Create a fragment endpoint that returns raw HTML (not a full document):

<!-- snippet: in Route -->
```kotlin
import kotlinx.html.stream.appendHTML

suspend fun RoutingCall.respondHtmlFragment(block: TagConsumer<*>.() -> Unit) {
    val html = buildString { appendHTML(prettyPrint = false).apply(block) }
    respondText(html, ContentType.Text.Html)
}

get("/fragments/stats") {
    call.respondHtmlFragment {
        section {
            addClassNames("stats bg-base-100 col-span-12 w-full shadow-xs")
            daisyStat {
                daisyStatTitle("Page Views")
                daisyStatValue("89,400")
            }
        }
    }
}
```

**Trigger options**:

| Trigger | Behavior |
|---|---|
| `"load"` | Fetch immediately when the page loads |
| `"load delay:200ms"` | Fetch after a 200ms delay |
| `"revealed"` | Fetch when the element scrolls into view |
| `"click"` | Fetch when the user clicks |

Use `"load"` for above-the-fold content, `"revealed"` for below-the-fold sections.

## Add a dropdown

DaisyUI documents two dropdowns that open without JavaScript, and kdaisyui offers both.

**`daisyDropdown`** renders `<details>`, which the browser opens and closes on its `<summary>`:

```kotlin
daisyDropdown(align = DropdownAlign.End) {
    summary {
        daisyButtonClasses(style = ButtonStyle.Ghost, shape = ButtonShape.Circle)
        daisyIndicator {
            daisyIndicatorItem { daisyBadge(color = BadgeColor.Error, size = BadgeSize.Xs) }
            +"🔔"
        }
    }
    daisyDropdownContent(extraClasses = "rounded-box bg-base-100 w-80 p-2 shadow-2xl") {
        daisyMenuClasses()
        li { a { b { +"New message" }; +" Alice: Hi!" } }
    }
}
```

**`daisyDropdownPopover`** is the menu itself, opened on the browser's top layer by a trigger that
names it, so no ancestor's `overflow` can clip it and it needs no `z-index`. `anchored = true`
places it next to its trigger through CSS anchor positioning:

<!-- snippet: in FlowContent -->
```kotlin
val notifications = htmlId("notifications")
daisyButton("🔔", attrs = { popoverTarget(notifications, anchored = true) })
daisyDropdownPopover(id = notifications, align = DropdownAlign.End, extraClasses = "rounded-box bg-base-100 w-80 p-2 shadow-2xl") {
    daisyMenuClasses()
    li { a { b { +"New message" }; +" Alice: Hi!" } }
}
```

Anchor positioning is new: Chromium 125, Safari 26, Firefox 147. Where it is missing, DaisyUI's CSS
falls back, and `daisyDropdown` is the choice that behaves the same everywhere.

## Show validation feedback

Use alerts for form-level messages and DaisyUI's built-in input states:

```kotlin
daisyAlert(color = AlertColor.Success) {
    span { +"Your payment was successful" }
}

daisyAlert(color = AlertColor.Error) {
    span { +"Please fix the errors below" }
}
```

For field-level validation, use `extraClasses` on the input:

```kotlin
daisyInput(placeholder = "Email", extraClasses = "input-error w-full")
```

DaisyUI provides `input-error`, `input-success`, `input-warning` classes.
