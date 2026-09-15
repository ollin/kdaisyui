<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/toast/+page.md
Regenerate: just generate
-->

# Toast

[DaisyUI documentation →](https://daisyui.com/components/toast/)

Toast notification container. Renders `<div class="toast ...">`.

```kotlin
// ToastHorizontalPlacement: Start | Center | End
// ToastVerticalPlacement: Top | Middle | Bottom
fun FlowContent.daisyToast(
    id: HtmlId? = null,
    horizontalPlacement: ClassValues<ToastHorizontalPlacement>? = null,
    verticalPlacement: ClassValues<ToastVerticalPlacement>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
