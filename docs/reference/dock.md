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
    size: ClassValues<DockSize>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyDockClasses(size: ClassValues<DockSize>? = null)
```

```kotlin
fun FlowContent.daisyDockItem(
    id: HtmlId? = null,
    active: Boolean = false,  // Makes the Dock Item look active
    extraClasses: String? = null,
    attrs: (BUTTON.() -> Unit)? = null,
    content: (BUTTON.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyDockItemClasses(active: Boolean = false)
```

```kotlin
fun FlowContent.daisyDockLabel(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyDockLabelClasses()
```
