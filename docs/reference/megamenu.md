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
// MegamenuWidth: Wide | Full
fun FlowContent.daisyMegamenu(
    id: HtmlId? = null,
    size: ClassValues<MegamenuSize>? = null,
    width: ClassValues<MegamenuWidth>? = null,
    vertical: Boolean = false,  // Hides horizontal megamenu so we can open a vertical megamenu in small screens
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyMegamenuClasses(size: ClassValues<MegamenuSize>? = null, width: ClassValues<MegamenuWidth>? = null, vertical: Boolean = false)
```

```kotlin
fun FlowContent.daisyMegamenuActive(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyMegamenuActiveClasses()
```

```kotlin
fun FlowContent.daisyMegamenuPanel(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
