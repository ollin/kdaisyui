<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/stack/+page.md
Regenerate: just generate
-->

# Stack

[DaisyUI documentation →](https://daisyui.com/components/stack/)

Stack overlapping elements. Renders `<div class="stack ...">`.

```kotlin
fun FlowContent.daisyStack(
    id: HtmlId? = null,
    bottom: Boolean = false,  // Aligns the children elements to the bottom
    end: Boolean = false,  // Aligns the children elements to the end (horizontally)
    start: Boolean = false,  // Aligns the children elements to the start (horizontally)
    top: Boolean = false,  // Aligns the children elements to the top
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
