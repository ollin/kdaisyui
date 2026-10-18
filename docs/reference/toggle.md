<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/toggle/+page.md
Regenerate: just generate
-->

# Toggle

[DaisyUI documentation →](https://daisyui.com/components/toggle/)

Switch-style boolean input. Renders `<input class="toggle ...">`.

```kotlin
// ToggleColor: Primary | Secondary | Accent | Neutral | Success | Warning | Info | Error
// ToggleSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyToggle(
    id: HtmlId? = null,
    color: ClassValues<ToggleColor>? = null,
    size: ClassValues<ToggleSize>? = null,
    checked: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyToggleClasses(color: ClassValues<ToggleColor>? = null, size: ClassValues<ToggleSize>? = null)
```
