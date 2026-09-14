<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/alert/+page.md
Regenerate: just generate
-->

# Alert

[DaisyUI documentation →](https://daisyui.com/components/alert/)

Status messages and notifications. Renders `<div class="alert ...">`.

```kotlin
// AlertVariant: Info | Success | Warning | Error
// AlertDirection: Vertical | Horizontal
fun FlowContent.daisyAlert(
    id: HtmlId? = null,
    variant: ClassValues<AlertVariant>? = null,
    direction: ClassValues<AlertDirection>? = null,
    dash: Boolean = false,  // dash outline style
    outline: Boolean = false,  // outline style
    soft: Boolean = false,  // soft style
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
