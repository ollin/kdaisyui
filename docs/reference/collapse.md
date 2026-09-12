<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/collapse/+page.md
Regenerate: just generate
-->

# Collapse

[DaisyUI documentation →](https://daisyui.com/components/collapse/)

Collapsible content sections. Renders `<div class="collapse ...">`.

```kotlin
fun FlowContent.daisyCollapse(
    id: HtmlId? = null,
    arrow: Boolean = false,
    close: Boolean = false,
    open: Boolean = false,
    plus: Boolean = false,
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
    attrs: (H2.() -> Unit)? = null,
    content: (H2.() -> Unit)? = null,
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
