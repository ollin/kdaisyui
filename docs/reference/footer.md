<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/footer/+page.md
Regenerate: just generate
-->

# Footer

[DaisyUI documentation →](https://daisyui.com/components/footer/)

Page footer layout. Renders `<footer class="footer ...">`.

```kotlin
fun FlowContent.daisyFooter(
    id: HtmlId? = null,
    center: Boolean = false,  // Aligns footer content to center
    horizontal: Boolean = false,  // Puts footer columns next to each other horizontally
    vertical: Boolean = false,  // Puts footer columns under each other vertically
    extraClasses: String? = null,
    attrs: (FOOTER.() -> Unit)? = null,
    content: (FOOTER.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyFooterTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (H2.() -> Unit)? = null,
    content: (H2.() -> Unit)? = null,
)
```
