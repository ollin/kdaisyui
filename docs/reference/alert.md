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
fun FlowContent.daisyAlert(
    id: HtmlId? = null,
    variant: AlertVariant? = null,
    dash: Boolean = false,  // dash outline style
    horizontal: Boolean = false,  // Horizontal layout, good for desktop
    outline: Boolean = false,  // outline style
    soft: Boolean = false,  // soft style
    vertical: Boolean = false,  // Vertical layout, good for mobile
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
