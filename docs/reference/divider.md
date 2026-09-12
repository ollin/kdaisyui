<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/divider/+page.md
Regenerate: just generate
-->

# Divider

[DaisyUI documentation →](https://daisyui.com/components/divider/)

Visual separator between sections. Renders `<div class="divider ...">`.

```kotlin
// DividerVariant: Neutral | Primary | Secondary | Accent | Success | Warning | Info | Error
fun FlowContent.daisyDivider(
    id: HtmlId? = null,
    variant: DividerVariant? = null,
    end: Boolean = false,  // Pushes the divider text to the end
    horizontal: Boolean = false,  // Divide horizontal elements (next to each other)
    start: Boolean = false,  // Pushes the divider text to the start
    vertical: Boolean = false,  // Divide vertical elements (on top of each other)
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
