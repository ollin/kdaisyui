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
// CollapseState: Open | Close
fun FlowContent.daisyCollapse(
    id: HtmlId? = null,
    icon: ClassValues<CollapseIcon>? = null,
    state: ClassValues<CollapseState>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyCollapseClasses(icon: ClassValues<CollapseIcon>? = null, state: ClassValues<CollapseState>? = null)
```

```kotlin
fun FlowContent.daisyCollapseTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit)? = null,
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyCollapseTitleClasses()
```

```kotlin
fun FlowContent.daisyCollapseContent(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyCollapseContentClasses()
```
