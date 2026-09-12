<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/badge/+page.md
Regenerate: just generate
-->

# Badge

[DaisyUI documentation →](https://daisyui.com/components/badge/)

Labels, counts, and status tags. Renders `<span class="badge ...">`.

```kotlin
// BadgeVariant: Neutral | Primary | Secondary | Accent | Info | Success | Warning | Error
// BadgeSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyBadge(
    text: String? = null,
    id: HtmlId? = null,
    variant: BadgeVariant? = null,
    size: BadgeSize? = null,
    dash: Boolean = false,  // dash outline style
    ghost: Boolean = false,  // ghost style
    outline: Boolean = false,  // outline style
    soft: Boolean = false,  // soft style
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit)? = null,
)
```
