<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/radio/+page.md
Regenerate: just generate
-->

# Radio

[DaisyUI documentation →](https://daisyui.com/components/radio/)

Single-choice option groups. Renders `<input class="radio ...">`.

```kotlin
// RadioColor: Neutral | Primary | Secondary | Accent | Success | Warning | Info | Error
// RadioSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyRadio(
    id: HtmlId? = null,
    color: ClassValues<RadioColor>? = null,
    size: ClassValues<RadioSize>? = null,
    name: String? = null,
    checked: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyRadioClasses(color: ClassValues<RadioColor>? = null, size: ClassValues<RadioSize>? = null)
```
