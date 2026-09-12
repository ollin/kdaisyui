<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/radio/+page.md
Regenerate: just generate
-->

# Radio

[DaisyUI documentation →](https://daisyui.com/components/radio/)

Single-choice option groups. Renders `<input class="radio ...">`.

```kotlin
// RadioVariant: Neutral | Primary | Secondary | Accent | Success | Warning | Info | Error
// RadioSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyRadio(
    id: HtmlId? = null,
    variant: RadioVariant? = null,
    size: RadioSize? = null,
    name: String? = null,
    checked: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
)
```
