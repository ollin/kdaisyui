<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/link/+page.md
Regenerate: just generate
-->

# Link

[DaisyUI documentation →](https://daisyui.com/components/link/)

Styled anchor elements. Renders `<a class="link ...">`.

```kotlin
// LinkVariant: Neutral | Primary | Secondary | Accent | Success | Info | Warning | Error
fun FlowContent.daisyLink(
    text: String? = null,
    id: HtmlId? = null,
    variant: LinkVariant? = null,
    hover: Boolean = false,  // Only shows underline on hover
    extraClasses: String? = null,
    attrs: (A.() -> Unit)? = null,
    content: (A.() -> Unit)? = null,
)
```
