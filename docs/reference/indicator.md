<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/indicator/+page.md
Regenerate: just generate
-->

# Indicator

[DaisyUI documentation →](https://daisyui.com/components/indicator/)

Overlays badges on elements. Renders `<div class="indicator ...">`.

```kotlin
// IndicatorVerticalPlacement: Top | Middle | Bottom
// IndicatorHorizontalPlacement: Start | Center | End
fun FlowContent.daisyIndicator(
    id: HtmlId? = null,
    verticalPlacement: ClassValues<IndicatorVerticalPlacement>? = null,
    horizontalPlacement: ClassValues<IndicatorHorizontalPlacement>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyIndicatorItem(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
