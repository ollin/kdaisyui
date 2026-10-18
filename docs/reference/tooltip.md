<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/tooltip/+page.md
Regenerate: just generate
-->

# Tooltip

[DaisyUI documentation →](https://daisyui.com/components/tooltip/)

Hover tooltip on elements. Renders `<div class="tooltip ...">`.

```kotlin
// TooltipColor: Primary | Secondary | Accent | Info | Success | Warning | Error
// TooltipSide: Top | Bottom | Left | Right
// TooltipAlign: Start | Center | End
fun FlowContent.daisyTooltip(
    id: HtmlId? = null,
    color: ClassValues<TooltipColor>? = null,
    side: ClassValues<TooltipSide>? = null,
    align: ClassValues<TooltipAlign>? = null,
    open: Boolean = false,  // Force open tooltip
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyTooltipContent(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
