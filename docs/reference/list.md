<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/list/+page.md
Regenerate: just generate
-->

# List

[DaisyUI documentation →](https://daisyui.com/components/list/)

Styled list layout. Renders `<ul class="list ...">`.

```kotlin
fun FlowContent.daisyList(
    id: HtmlId? = null,
    colGrow: Boolean = false,  // For one of direct children of list-row to make it fill the remaining space
    colWrap: Boolean = false,  // For one of direct children of list-row to push it to the next line
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
)
```
