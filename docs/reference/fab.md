<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/fab/+page.md
Regenerate: just generate
-->

# Fab

[DaisyUI documentation →](https://daisyui.com/components/fab/)

Floating action button with expandable actions. Renders `<div class="fab ...">`.

```kotlin
fun FlowContent.daisyFab(
    id: HtmlId? = null,
    flower: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyFabClasses(flower: Boolean = false)
```

```kotlin
fun FlowContent.daisyFabClose(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyFabCloseClasses()
```

```kotlin
fun FlowContent.daisyFabMainAction(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (BUTTON.() -> Unit)? = null,
    content: (BUTTON.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyFabMainActionClasses()
```
