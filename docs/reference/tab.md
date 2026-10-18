<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/tab/+page.md
Regenerate: just generate
-->

# Tab

[DaisyUI documentation →](https://daisyui.com/components/tab/)

Tabbed navigation. Renders `<button class="tabs ...">`.

```kotlin
// TabsSize: Xs | Sm | Md | Lg | Xl
// TabsPlacement: Top | Bottom
fun FlowContent.daisyTabs(
    id: HtmlId? = null,
    size: ClassValues<TabsSize>? = null,
    placement: ClassValues<TabsPlacement>? = null,
    border: Boolean = false,  // bottom border style
    box: Boolean = false,  // box style
    lift: Boolean = false,  // lift style
    extraClasses: String? = null,
    attrs: (BUTTON.() -> Unit)? = null,
    content: (BUTTON.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyTab(
    id: HtmlId? = null,
    active: Boolean = false,  // Makes a single tab look active
    disabled: Boolean = false,  // Makes a single tab look disabled
    extraClasses: String? = null,
    attrs: (A.() -> Unit)? = null,
    content: (A.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyTabContent(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
