<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/button/+page.md
Regenerate: just generate
-->

# Button

[DaisyUI documentation →](https://daisyui.com/components/button/)

Actions and triggers. Renders `<button class="btn ...">`.

```kotlin
// ButtonColor: Neutral | Primary | Secondary | Accent | Info | Success | Warning | Error
// ButtonSize: Xs | Sm | Md | Lg | Xl
// ButtonStyle: Outline | Dash | Soft | Ghost | Link
// ButtonShape: Square | Circle
// ButtonWidth: Wide | Block
fun FlowContent.daisyButton(
    text: String? = null,
    id: HtmlId? = null,
    color: ClassValues<ButtonColor>? = null,
    size: ClassValues<ButtonSize>? = null,
    style: ClassValues<ButtonStyle>? = null,
    shape: ClassValues<ButtonShape>? = null,
    width: ClassValues<ButtonWidth>? = null,
    active: Boolean = false,  // looks active
    disabled: Boolean = false,
    type: ButtonType? = null,
    extraClasses: String? = null,
    attrs: (BUTTON.() -> Unit)? = null,
    content: (BUTTON.() -> Unit)? = null,
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyButtonClasses(color: ClassValues<ButtonColor>? = null, size: ClassValues<ButtonSize>? = null, style: ClassValues<ButtonStyle>? = null, shape: ClassValues<ButtonShape>? = null, width: ClassValues<ButtonWidth>? = null, active: Boolean = false, disabled: Boolean = false)
```
