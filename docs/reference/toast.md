<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/toast/+page.md
Regenerate: just generate
-->

# Toast

[DaisyUI documentation →](https://daisyui.com/components/toast/)

Toast notification container. Renders `<div class="toast ...">`.

```kotlin
fun FlowContent.daisyToast(
    id: HtmlId? = null,
    bottom: Boolean = false,  // align vertically to bottom
    center: Boolean = false,  // align horizontally to the center
    end: Boolean = false,  // align horizontally to the right
    middle: Boolean = false,  // align vertically to middle
    start: Boolean = false,  // align horizontally to the left
    top: Boolean = false,  // align vertically to top
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
