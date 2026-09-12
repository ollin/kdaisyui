<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/loading/+page.md
Regenerate: just generate
-->

# Loading

[DaisyUI documentation →](https://daisyui.com/components/loading/)

Loading spinners and indicators. Renders `<span class="loading ...">`.

```kotlin
// LoadingSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyLoading(
    id: HtmlId? = null,
    size: LoadingSize? = null,
    ball: Boolean = false,  // ball animation
    bars: Boolean = false,  // bars animation
    dots: Boolean = false,  // dots animation
    infinity: Boolean = false,  // infinity animation
    ring: Boolean = false,  // ring animation
    spinner: Boolean = false,  // spinner animation
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
)
```
