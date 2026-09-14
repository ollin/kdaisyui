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
// TooltipSidePlacement: Top | Bottom | Left | Right
// TooltipAlignPlacement: Start | Center | End
fun FlowContent.daisyTooltip(
    id: HtmlId? = null,
    variant: ClassValues<TooltipVariant>? = null,
    sidePlacement: ClassValues<TooltipSidePlacement>? = null,
    alignPlacement: ClassValues<TooltipAlignPlacement>? = null,
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
