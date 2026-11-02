<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/footer/+page.md
Regenerate: just generate
-->

# Footer

[DaisyUI documentation →](https://daisyui.com/components/footer/)

Page footer layout. Renders `<footer class="footer ...">`.

```kotlin
// FooterDirection: Horizontal | Vertical
fun FlowContent.daisyFooter(
    id: HtmlId? = null,
    direction: ClassValues<FooterDirection>? = null,
    center: Boolean = false,  // Aligns footer content to center
    extraClasses: String? = null,
    block: (FOOTER.() -> Unit) = {},
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyFooterClasses(direction: ClassValues<FooterDirection>? = null, center: Boolean = false)
```

```kotlin
fun FlowContent.daisyFooterTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (H6.() -> Unit) = {},
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyFooterTitleClasses()
```
