<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/input/+page.md
Regenerate: just generate
-->

# Input

[DaisyUI documentation →](https://daisyui.com/components/input/)

Text entry fields. Renders `<input class="input ...">`.

```kotlin
// InputColor: Neutral | Primary | Secondary | Accent | Info | Success | Warning | Error
// InputSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyInput(
    id: HtmlId? = null,
    color: ClassValues<InputColor>? = null,
    size: ClassValues<InputSize>? = null,
    ghost: Boolean = false,
    type: InputType = InputType.text,
    placeholder: String? = null,
    value: String? = null,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyInputClasses(color: ClassValues<InputColor>? = null, size: ClassValues<InputSize>? = null, ghost: Boolean = false)
```
