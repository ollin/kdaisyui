<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/label/+page.md
Regenerate: just generate
-->

# Label

[DaisyUI documentation →](https://daisyui.com/components/label/)

Field labels and helper text. Renders `<span class="label ...">`.

```kotlin
fun FlowContent.daisyLabel(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit)? = null,
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyLabelClasses()
```

```kotlin
fun FlowContent.daisyFloatingLabel(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (LABEL.() -> Unit)? = null,
    content: (LABEL.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyFloatingLabelClasses()
```
