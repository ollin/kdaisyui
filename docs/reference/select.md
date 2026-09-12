<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/select/+page.md
Regenerate: just generate
-->

# Select

[DaisyUI documentation →](https://daisyui.com/components/select/)

Dropdown option pickers. Renders `<select class="select ...">`.

```kotlin
// SelectVariant: Neutral | Primary | Secondary | Accent | Info | Success | Warning | Error
// SelectSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisySelect(
    id: HtmlId? = null,
    variant: SelectVariant? = null,
    size: SelectSize? = null,
    ghost: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (SELECT.() -> Unit)? = null,
    content: (SELECT.() -> Unit),
)
```
