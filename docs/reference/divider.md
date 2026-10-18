<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/divider/+page.md
Regenerate: just generate
-->

# Divider

[DaisyUI documentation →](https://daisyui.com/components/divider/)

Visual separator between sections. Renders `<div class="divider ...">`.

```kotlin
// DividerColor: Neutral | Primary | Secondary | Accent | Success | Warning | Info | Error
// DividerDirection: Vertical | Horizontal
// DividerPlacement: Start | End
fun FlowContent.daisyDivider(
    id: HtmlId? = null,
    color: ClassValues<DividerColor>? = null,
    direction: ClassValues<DividerDirection>? = null,
    placement: ClassValues<DividerPlacement>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
