<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/dock/+page.md
Regenerate: just generate
-->

# Dock

[DaisyUI documentation →](https://daisyui.com/components/dock/)

macOS-style dock navigation. Renders `<div class="dock ...">`.

```kotlin
// DockSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyDock(
    id: HtmlId? = null,
    size: DockSize? = null,
    active: Boolean = false,  // Makes the Dock Item look active
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyDockLabel(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
