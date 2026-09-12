<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/toggle/+page.md
Regenerate: just generate
-->

# Toggle

[DaisyUI documentation →](https://daisyui.com/components/toggle/)

Switch-style boolean input. Renders `<input class="toggle ...">`.

```kotlin
// ToggleVariant: Primary | Secondary | Accent | Neutral | Success | Warning | Info | Error
// ToggleSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyToggle(
    id: HtmlId? = null,
    variant: ToggleVariant? = null,
    size: ToggleSize? = null,
    checked: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
)
```
