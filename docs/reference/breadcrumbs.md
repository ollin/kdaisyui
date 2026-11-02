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
    block: (DIV.() -> Unit) = {},
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyBreadcrumbsClasses()
```

```kotlin
fun FlowContent.daisyBreadcrumbsItems(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (UL.() -> Unit) = {},
)
```

```kotlin
fun UL.daisyBreadcrumbsItem(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (LI.() -> Unit) = {},
)
```
