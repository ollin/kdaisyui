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
// TabPlacement: Top | Bottom
fun FlowContent.daisyTab(
    id: HtmlId? = null,
    size: ClassValues<TabSize>? = null,
    placement: ClassValues<TabPlacement>? = null,
    border: Boolean = false,  // bottom border style
    box: Boolean = false,  // box style
    lift: Boolean = false,  // lift style
    tabActive: Boolean = false,  // Makes a single tab look active
    tabDisabled: Boolean = false,  // Makes a single tab look disabled
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
