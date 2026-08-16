# Megamenu

[DaisyUI documentation →](https://daisyui.com/components/megamenu/)

Large horizontal menu where each item opens a popover holding a block of navigation links.
Intended to be used once, at the top of the page, and on large screens only — on small screens
hide it and use a dropdown or drawer instead. Renders `<div class="megamenu ...">`.

```kotlin
// MegamenuSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyMegamenu(
    id: HtmlId? = null,
    size: MegamenuSize? = null,
    full: Boolean = false,      // dropdown fills the page width
    vertical: Boolean = false,  // hides the horizontal menu, for small screens
    wide: Boolean = false,      // dropdown as wide as the megamenu container
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

fun FlowContent.daisyMegamenuActive(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
