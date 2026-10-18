<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/kbd/+page.md
Regenerate: just generate
-->

# Kbd

[DaisyUI documentation →](https://daisyui.com/components/kbd/)

Keyboard key display. Renders `<kbd class="kbd ...">`.

```kotlin
// KbdSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyKbd(
    text: String? = null,
    id: HtmlId? = null,
    size: ClassValues<KbdSize>? = null,
    extraClasses: String? = null,
    attrs: (KBD.() -> Unit)? = null,
    content: (KBD.() -> Unit)? = null,
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyKbdClasses(size: ClassValues<KbdSize>? = null)
```
