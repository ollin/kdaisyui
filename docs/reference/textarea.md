<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/textarea/+page.md
Regenerate: just generate
-->

# Textarea

[DaisyUI documentation →](https://daisyui.com/components/textarea/)

Multi-line text input. Renders `<textarea class="textarea ...">`.

```kotlin
// TextareaVariant: Neutral | Primary | Secondary | Accent | Info | Success | Warning | Error
// TextareaSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyTextarea(
    id: HtmlId? = null,
    variant: TextareaVariant? = null,
    size: TextareaSize? = null,
    ghost: Boolean = false,
    extraClasses: String? = null,
    attrs: (TEXTAREA.() -> Unit)? = null,
    content: (TEXTAREA.() -> Unit),
)
```
