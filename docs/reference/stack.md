<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/stack/+page.md
Regenerate: just generate
-->

# Stack

[DaisyUI documentation →](https://daisyui.com/components/stack/)

Stack overlapping elements. Renders `<div class="stack ...">`.

```kotlin
// StackPlacement: Top | Bottom | Start | End
fun FlowContent.daisyStack(
    id: HtmlId? = null,
    placement: ClassValues<StackPlacement>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyStackClasses(placement: ClassValues<StackPlacement>? = null)
```
