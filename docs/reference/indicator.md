<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/indicator/+page.md
Regenerate: just generate
-->

# Indicator

[DaisyUI documentation →](https://daisyui.com/components/indicator/)

Overlays badges on elements. Renders `<div class="indicator ...">`.

```kotlin
// IndicatorItemHorizontalPlacement: Start | Center | End
// IndicatorItemVerticalPlacement: Top | Middle | Bottom
fun FlowContent.daisyIndicator(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyIndicatorItem(
    id: HtmlId? = null,
    horizontalPlacement: ClassValues<IndicatorItemHorizontalPlacement>? = null,
    verticalPlacement: ClassValues<IndicatorItemVerticalPlacement>? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
)
```
