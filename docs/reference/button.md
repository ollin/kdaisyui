<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/button/+page.md
Regenerate: just generate
-->

# Button

[DaisyUI documentation →](https://daisyui.com/components/button/)

Actions and triggers. Renders `<button class="btn ...">`.

```kotlin
// ButtonVariant: Neutral | Primary | Secondary | Accent | Info | Success | Warning | Error
// ButtonSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyButton(
    text: String? = null,
    id: HtmlId? = null,
    variant: ButtonVariant? = null,
    size: ButtonSize? = null,
    active: Boolean = false,  // looks active
    block: Boolean = false,  // Full width
    circle: Boolean = false,  // 1:1 ratio with rounded corners
    dash: Boolean = false,  // dash style
    ghost: Boolean = false,  // ghost style
    link: Boolean = false,  // looks like a link
    outline: Boolean = false,  // outline style
    soft: Boolean = false,  // soft style
    square: Boolean = false,  // 1:1 ratio
    wide: Boolean = false,  // more horizontal padding
    disabled: Boolean = false,
    type: ButtonType? = null,
    extraClasses: String? = null,
    attrs: (BUTTON.() -> Unit)? = null,
    content: (BUTTON.() -> Unit)? = null,
)
```
