<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/status/+page.md
Regenerate: just generate
-->

# Status

[DaisyUI documentation →](https://daisyui.com/components/status/)

Small status indicator dot. Renders `<div class="status ...">`.

```kotlin
// StatusColor: Neutral | Primary | Secondary | Accent | Info | Success | Warning | Error
// StatusSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyStatus(
    id: HtmlId? = null,
    color: ClassValues<StatusColor>? = null,
    size: ClassValues<StatusSize>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
