# How-to guides

Task-oriented recipes for common goals. Each guide assumes you have kdaisyUI set up and know the basics.

## Add a component to your page

Every component is a `FlowContent` extension function. Call it inside any kotlinx.html block element:

```kotlin
createHTML().div {
    daisyButton("Save", variant = ButtonVariant.Primary)
}
```

Components work inside `div`, `section`, `main`, `form`, `td`, `li` — anywhere kotlinx.html accepts flow content.

## Add custom CSS classes

Use the `extraClasses` parameter. Classes are merged safely — no duplicates, no broken whitespace:

```kotlin
daisyButton("Wide", variant = ButtonVariant.Primary, extraClasses = "w-full mt-4")
// → class="btn btn-primary w-full mt-4"
```

This works on every component:

```kotlin
daisyCard(extraClasses = "bg-base-100 shadow-xl") { /* ... */ }
daisyInput(extraClasses = "rounded-full max-sm:w-24")
daisyBadge("New", extraClasses = "indicator-item")
```

## Add custom HTML attributes

Use the `attrs` lambda to access the raw kotlinx.html tag:

```kotlin
daisyButton(
    attrs = {
        id = "save-btn"
        attributes["data-action"] = "submit"
        attributes["aria-label"] = "Save changes"
    },
    content = { +"Save" }
)
```

Inside `attrs`, `this` is the underlying HTML tag (`BUTTON`, `DIV`, etc.) with full access to all kotlinx.html properties.

## Nest components

Components compose by nesting, just like HTML:

```kotlin
daisyCard(extraClasses = "bg-base-100 shadow-xs") {
    daisyCardBody {
        daisyCardTitle {
            +"Orders"
            daisyBadge("3", variant = BadgeVariant.Success, size = BadgeSize.Sm)
        }
        daisyTable(zebra = true) {
            tbody {
                tr { td { +"Order #1001" }; td { +"$250" } }
                tr { td { +"Order #1002" }; td { +"$180" } }
            }
        }
        daisyButton("View all", variant = ButtonVariant.Primary)
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
    daisyButton("Submit", variant = ButtonVariant.Primary, extraClasses = "w-full")
}
```

Pattern: `daisyFieldset` wraps each field group. `daisyLabel` adds the label. The input component follows.

## Create a sidebar layout

Use the DaisyUI drawer pattern:

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

## Add a notification dropdown

Combine dropdown, indicator, badge, and avatar:

```kotlin
daisyDropdown(end = true, extraClasses = "z-10") {
    div {
        attributes["tabindex"] = "0"
        classes = setOf("btn", "btn-circle", "btn-ghost")
        daisyIndicator {
            daisyBadge(variant = BadgeVariant.Error, size = BadgeSize.Xs, extraClasses = "indicator-item")
            +"🔔"
        }
    }
    daisyDropdownContent(extraClasses = "rounded-box bg-base-100 mt-3 w-80 p-2 shadow-2xl") {
        attributes["tabindex"] = "0"
        li {
            a("gap-4") {
                daisyAvatar {
                    div("w-8 rounded-full") { img(src = "https://example.com/photo.jpg") }
                }
                span { b { +"New message" }; br; +"Alice: Hi!" }
            }
        }
    }
}
```

The `tabindex="0"` on both the trigger and the content is required for DaisyUI's CSS-based dropdown to work.

## Show validation feedback

Use alerts for form-level messages and DaisyUI's built-in input states:

```kotlin
daisyAlert(variant = AlertVariant.Success) {
    span { +"Your payment was successful" }
}

daisyAlert(variant = AlertVariant.Error) {
    span { +"Please fix the errors below" }
}
```

For field-level validation, use `extraClasses` on the input:

```kotlin
daisyInput(placeholder = "Email", extraClasses = "input-error w-full")
```

DaisyUI provides `input-error`, `input-success`, `input-warning` classes.
