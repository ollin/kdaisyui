<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/range/+page.md
Regenerate: just generate
-->

# Range

[DaisyUI documentation →](https://daisyui.com/components/range/)

Slider for numeric ranges. Renders `<input class="range ...">`.

```kotlin
// RangeVariant: Neutral | Primary | Secondary | Accent | Success | Warning | Info | Error
// RangeSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyRange(
    id: HtmlId? = null,
    variant: RangeVariant? = null,
    size: RangeSize? = null,
    vertical: Boolean = false,  // Vertical slider
    min: String? = null,
    max: String? = null,
    value: String? = null,
    step: String? = null,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
)
```
