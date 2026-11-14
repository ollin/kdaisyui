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

The E2E tests drive a real Chromium through Playwright for Java, with Kotest. They
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

## Put a component's classes on another element

Every function that writes a class has a sibling, `daisy<Name>Classes(…)`, which puts the same
classes on the element you call it in, from the same typed parameters. Use it where the element
is not the one the function renders: a badge or a button that is a link.

<!-- snippet: in FlowContent -->
```kotlin
a(href = "/deployments/42") {
    daisyBadgeClasses(color = BadgeColor.Info)
    +"#42"
}
```

<!-- snippet: in FlowContent -->
```kotlin
a(href = "/deployments/new") {
    daisyButtonClasses(color = ButtonColor.Primary, size = ButtonSize.Sm)
    +"New deployment"
}
```

The sibling writes only classes: no attribute, no content. A button that opens a `<details>`
dropdown is its `<summary>`, shown in [Add a dropdown](#add-a-dropdown). The KDoc of every
function names its sibling, so completion in the IDE leads there too.

### Let the build point at classes written as strings

The Gradle plugin checks your compiled code for DaisyUI classes written by hand, which the
compiler cannot check. After `kdaisyuiClasses`, its task `kdaisyuiRawClasses` warns about every
string it reports and names the components the classes belong to:

```
kdaisyui: strings that write DaisyUI classes the typed API writes for you:
  com/example/LayoutKt: "btn btn-sm btn-ghost" (button)
  com/example/LayoutKt: "card bg-base-100 col-span-12" (card)
  Use the component's function, or a { daisy<Component>Classes(…) } on another element.
```

A string counts when every word in it is shaped like a CSS class, one of them holds `-` or `:`, and
one without a variant prefix is a DaisyUI class. That leaves out three things:

- **Variants**, such as `extraClasses = "lg:btn-lg"`: a string is how a variant is written.
- **Single words**, such as `"dropdown"`: they cannot be told apart from text.
- **Prose**, such as `"Open the link"`.

An id or a `data-` value that looks like a class list is reported too. Rename it.

To make the check a gate, let it fail the build:

<!-- snippet: skip - a Gradle build script, not code against the library -->
```kotlin
kdaisyui {
    rawClasses {
        failBuild.set(true)
    }
}
```

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
names it, so no ancestor's `overflow` can clip it and it needs no `z-index`. The browser anchors
it to that trigger, so it opens beside it through CSS anchor positioning:

<!-- snippet: in FlowContent -->
```kotlin
val notifications = htmlId("notifications")
daisyButton("🔔") { popoverTarget(notifications) }
daisyDropdownPopover(id = notifications, align = DropdownAlign.End, extraClasses = "rounded-box bg-base-100 w-80 p-2 shadow-2xl") {
    daisyMenuClasses()
    li { a { b { +"New message" }; +" Alice: Hi!" } }
}
```

No anchor name and no inline style is written, so this works under a strict
`Content-Security-Policy`: the trigger that opens a popover is its anchor. That implicit anchor
needs Chrome 133, Safari 26 or Firefox 147; where anchor positioning is missing, DaisyUI's CSS
falls back, and `daisyDropdown` is the choice that behaves the same everywhere. A popover opened
from script instead of `popovertarget` has no such trigger: pass `source` to `showPopover()`.

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

## Let users choose a theme

DaisyUI ships 35 themes. `DaisyTheme` names each of them, `daisyTheme` puts one on an element,
and `daisyThemeOf` reads a stored one back. The choice needs no script: a theme is CSS that
applies to whatever carries `data-theme`, and the server writes that attribute.

**Step 1: put the themes in the stylesheet.** The webjar's `themes.css` carries all 35. A
compiled stylesheet carries only the themes its entry names, and DaisyUI's default is `light`
and `dark`:

```css
@plugin "daisyui" { themes: all; }
```

That adds about 36 KB to a minified stylesheet. To offer fewer, name them:
`themes: light --default, dark --prefersdark, nord;`.

`DaisyTheme` lists every built-in theme whatever your stylesheet holds. A theme the stylesheet
lacks does not fail at runtime: the element renders in the default theme. With the Gradle plugin
compiling your stylesheet, its task `kdaisyuiThemes` runs after every `kdaisyuiStylesheet` and
warns when your code names a theme the stylesheet lacks — `DaisyTheme.Nord`, or every theme when
the code chooses at runtime through `DaisyTheme.entries` or `daisyThemeOf`:

```
kdaisyui: the code names themes the stylesheet does not carry: nord.
  Add them to the themes of the entry's @plugin "daisyui" block: themes: …, nord;
```

**A theme of your own.** Declare it in the same entry, after `@plugin "daisyui"`. DaisyUI's
[theme page](https://daisyui.com/docs/themes/#how-to-add-a-new-custom-theme) lists every variable
a theme sets, and its [theme generator](https://daisyui.com/theme-generator/) writes them for you:

```css
@plugin "daisyui/theme" {
  name: "acme";
  color-scheme: light;
  --color-base-100: oklch(98% 0.02 240);
  --color-primary: oklch(55% 0.3 240);
  --color-primary-content: oklch(98% 0.01 240);
  /* …the remaining variables from DaisyUI's theme page */
}
```

It has no `DaisyTheme` entry, so name it once in Kotlin and write that name where a built-in
theme would go — on the page, on a preview, or as a theme controller's value:

```kotlin
const val ACME_THEME = "acme"   // the `name` of the @plugin "daisyui/theme" block

fun HTML.acmePage() {
    attributes["data-theme"] = ACME_THEME
    body {
        daisyThemeController { type = InputType.checkBox; value = ACME_THEME }
    }
}
```

Two things that know only the built-in themes do not know it: `daisyThemeOf("acme")` returns
`null`, so a stored choice that may be your theme needs its own comparison, and `kdaisyuiThemes`
does not check it, so a misspelt name renders the default theme without a warning.

**Step 2: render the page in the user's theme.** Without the attribute, DaisyUI follows the
browser's light or dark preference:

```kotlin
import io.github.ollin.kdaisyui.themes.*

fun HTML.themedPage(theme: DaisyTheme?) {
    if (theme != null) daisyTheme(theme)     // <html data-theme="nord">
    body { +"…" }
}
```

**Step 3: preview each theme.** On any other element the attribute themes only that element, so
a card shows a theme inside a page wearing another:

```kotlin
import io.github.ollin.kdaisyui.themes.*

fun FlowContent.themeChoice(theme: DaisyTheme, current: DaisyTheme?) {
    label("flex items-center gap-3") {
        daisyRadio(name = "theme", checked = theme == current) { value = theme.value }
        daisyCard(extraClasses = "bg-base-100 text-base-content w-48") {
            daisyTheme(theme)
            daisyCardBody {
                +theme.value
                div("flex gap-1") {
                    span("bg-primary size-4 rounded") {}
                    span("bg-secondary size-4 rounded") {}
                    span("bg-accent size-4 rounded") {}
                }
            }
        }
    }
}
```

Put one per `DaisyTheme.entries` inside a `form(action = "/profile/theme", method = FormMethod.post)`.

**Step 4: keep the choice.** A cookie lets the server render the theme on the first byte, so the
page never flashes the default. `daisyThemeOf` turns the stored string back into a theme, and
`null` for anything that is not one:

<!-- snippet: in Route -->
```kotlin
import io.github.ollin.kdaisyui.themes.*
import io.ktor.server.request.receiveParameters

post("/profile/theme") {
    val theme = daisyThemeOf(call.receiveParameters()["theme"].orEmpty())
        ?: return@post call.respond(HttpStatusCode.BadRequest)
    call.response.cookies.append(Cookie("theme", theme.value, maxAge = 31_536_000, path = "/", httpOnly = true))
    call.respondRedirect("/profile")
}

get("/profile") {
    val theme = call.request.cookies["theme"]?.let(::daisyThemeOf)
    call.respondHtml { themedPage(theme) }
}
```

**A light/dark switch in the page.** The theme controller re-themes the page while it is checked,
with CSS alone, and forgets it on reload:

```kotlin
import io.github.ollin.kdaisyui.themes.*

daisyThemeController(theme = DaisyTheme.Dark) { type = InputType.checkBox }

// Or as a toggle, the way DaisyUI's own page shows it:
daisyToggle {
    daisyThemeControllerClasses()
    value = DaisyTheme.Dark.value
}
```

The example application's profile does all four steps, with a "System default" choice that
deletes the cookie: `example-app/src/main/kotlin/kdaisyui/example/ProfilePage.kt` and
`ThemeCookie.kt`.
