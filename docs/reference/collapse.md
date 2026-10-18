<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/collapse/+page.md
Regenerate: just generate
-->

# Collapse

[DaisyUI documentation →](https://daisyui.com/components/collapse/)

Collapsible content sections. Renders `<div class="collapse ...">`.

```kotlin
// CollapseIcon: Arrow | Plus
fun FlowContent.daisyCollapse(
    id: HtmlId? = null,
    icon: ClassValues<CollapseIcon>? = null,
    close: Boolean = false,
    open: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyCollapseTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit)? = null,
)
```

```kotlin
fun FlowContent.daisyCollapseContent(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
