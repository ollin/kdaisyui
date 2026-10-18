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
// LoadingStyle: Spinner | Dots | Ring | Ball | Bars | Infinity
fun FlowContent.daisyLoading(
    id: HtmlId? = null,
    size: ClassValues<LoadingSize>? = null,
    style: ClassValues<LoadingStyle>? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyLoadingClasses(size: ClassValues<LoadingSize>? = null, style: ClassValues<LoadingStyle>? = null)
```
