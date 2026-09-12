<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/tooltip/+page.md
Regenerate: just generate
-->

# Tooltip

[DaisyUI documentation →](https://daisyui.com/components/tooltip/)

Hover tooltip on elements. Renders `<div class="tooltip ...">`.

```kotlin
// TooltipVariant: Primary | Secondary | Accent | Info | Success | Warning | Error
fun FlowContent.daisyTooltip(
    id: HtmlId? = null,
    variant: TooltipVariant? = null,
    bottom: Boolean = false,  // Put tooltip on bottom
    center: Boolean = false,  // Align tooltip on center
    end: Boolean = false,  // Align tooltip on end
    left: Boolean = false,  // Put tooltip on left
    open: Boolean = false,  // Force open tooltip
    right: Boolean = false,  // Put tooltip on right
    start: Boolean = false,  // Align tooltip on start
    top: Boolean = false,  // Put tooltip on top
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
