<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/breadcrumbs/+page.md
Regenerate: just generate
-->

# Breadcrumbs

[DaisyUI documentation →](https://daisyui.com/components/breadcrumbs/)

Navigation trail showing current location. Renders `<div class="breadcrumbs ...">`.

```kotlin
fun FlowContent.daisyBreadcrumbs(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyBreadcrumbsItems(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
)
```

```kotlin
fun UL.daisyBreadcrumbsItem(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (LI.() -> Unit)? = null,
    content: (LI.() -> Unit),
)
```
