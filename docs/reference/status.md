<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/status/+page.md
Regenerate: just generate
-->

# Status

[DaisyUI documentation →](https://daisyui.com/components/status/)

Small status indicator dot. Renders `<span class="status ...">`.

```kotlin
// StatusVariant: Neutral | Primary | Secondary | Accent | Info | Success | Warning | Error
// StatusSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyStatus(
    id: HtmlId? = null,
    variant: StatusVariant? = null,
    size: StatusSize? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
)
```
