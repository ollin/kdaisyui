<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/checkbox/+page.md
Regenerate: just generate
-->

# Checkbox

[DaisyUI documentation →](https://daisyui.com/components/checkbox/)

Boolean toggles for forms. Renders `<input class="checkbox ...">`.

```kotlin
// CheckboxVariant: Primary | Secondary | Accent | Neutral | Success | Warning | Info | Error
// CheckboxSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyCheckbox(
    id: HtmlId? = null,
    variant: CheckboxVariant? = null,
    size: CheckboxSize? = null,
    checked: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
)
```
