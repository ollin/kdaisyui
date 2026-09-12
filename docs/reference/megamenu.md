<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/megamenu/+page.md
Regenerate: just generate
-->

# Megamenu

[DaisyUI documentation →](https://daisyui.com/components/megamenu/)

Large horizontal menu where each item opens a popover holding a block of navigation links. Intended to be used once, at the top of the page, and on large screens only — on small screens hide it and use a dropdown or drawer instead. Renders `<div class="megamenu ..." popover>`.

```kotlin
// MegamenuSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyMegamenu(
    id: HtmlId? = null,
    size: MegamenuSize? = null,
    full: Boolean = false,  // megamenu dropdown will fill the entire width of the page
    vertical: Boolean = false,  // Hides horizontal megamenu so we can open a vertical megamenu in small screens
    wide: Boolean = false,  // megamenu dropdown will be as wide as the megamenu container
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyMegamenuActive(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyMegamenuPanel(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
