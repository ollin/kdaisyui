<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/tab/+page.md
Regenerate: just generate
-->

# Tab

[DaisyUI documentation →](https://daisyui.com/components/tab/)

Tabbed navigation. Renders `<button class="tabs ...">`.

```kotlin
// TabSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyTab(
    id: HtmlId? = null,
    size: TabSize? = null,
    border: Boolean = false,  // bottom border style
    bottom: Boolean = false,  // Puts tabs on under the tab-content
    box: Boolean = false,  // box style
    lift: Boolean = false,  // lift style
    tabActive: Boolean = false,  // Makes a single tab look active
    tabDisabled: Boolean = false,  // Makes a single tab look disabled
    top: Boolean = false,  // Puts tab buttons on top of the tab-content (default)
    extraClasses: String? = null,
    attrs: (BUTTON.() -> Unit)? = null,
    content: (BUTTON.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyTabTab(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyTabTabContent(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
