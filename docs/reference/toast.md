<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/toast/+page.md
Regenerate: just generate
-->

# Toast

[DaisyUI documentation →](https://daisyui.com/components/toast/)

Toast notification container. Renders `<div class="toast ...">`.

```kotlin
// ToastVerticalPlacement: Top | Middle | Bottom
// ToastHorizontalPlacement: Start | Center | End
fun FlowContent.daisyToast(
    id: HtmlId? = null,
    verticalPlacement: ClassValues<ToastVerticalPlacement>? = null,
    horizontalPlacement: ClassValues<ToastHorizontalPlacement>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
