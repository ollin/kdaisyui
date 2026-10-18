<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/badge/+page.md
Regenerate: just generate
-->

# Badge

[DaisyUI documentation →](https://daisyui.com/components/badge/)

Labels, counts, and status tags. Renders `<span class="badge ...">`.

```kotlin
// BadgeColor: Neutral | Primary | Secondary | Accent | Info | Success | Warning | Error
// BadgeSize: Xs | Sm | Md | Lg | Xl
// BadgeOutlineStyle: Outline | Dash
// BadgeFillStyle: Soft | Ghost
fun FlowContent.daisyBadge(
    text: String? = null,
    id: HtmlId? = null,
    color: ClassValues<BadgeColor>? = null,
    size: ClassValues<BadgeSize>? = null,
    outlineStyle: ClassValues<BadgeOutlineStyle>? = null,
    fillStyle: ClassValues<BadgeFillStyle>? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit)? = null,
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyBadgeClasses(color: ClassValues<BadgeColor>? = null, size: ClassValues<BadgeSize>? = null, outlineStyle: ClassValues<BadgeOutlineStyle>? = null, fillStyle: ClassValues<BadgeFillStyle>? = null)
```
