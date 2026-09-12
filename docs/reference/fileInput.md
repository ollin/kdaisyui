<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/file-input/+page.md
Regenerate: just generate
-->

# FileInput

[DaisyUI documentation →](https://daisyui.com/components/file-input/)

File upload input field. Renders `<input class="file-input ...">`.

```kotlin
// FileInputVariant: Neutral | Primary | Secondary | Accent | Info | Success | Warning | Error
// FileInputSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyFileInput(
    id: HtmlId? = null,
    variant: FileInputVariant? = null,
    size: FileInputSize? = null,
    ghost: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
    content: (INPUT.() -> Unit),
)
```
