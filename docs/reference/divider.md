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
// DividerDirection: Vertical | Horizontal
fun FlowContent.daisyDivider(
    id: HtmlId? = null,
    variant: ClassValues<DividerVariant>? = null,
    direction: ClassValues<DividerDirection>? = null,
    end: Boolean = false,  // Pushes the divider text to the end
    start: Boolean = false,  // Pushes the divider text to the start
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
