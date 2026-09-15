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
// AlertStyle: Outline | Dash
// AlertDirection: Vertical | Horizontal
fun FlowContent.daisyAlert(
    id: HtmlId? = null,
    variant: ClassValues<AlertVariant>? = null,
    style: ClassValues<AlertStyle>? = null,
    direction: ClassValues<AlertDirection>? = null,
    soft: Boolean = false,  // soft style
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
