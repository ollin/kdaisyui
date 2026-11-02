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
    block: (DIV.() -> Unit) = {},
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyDockClasses(size: ClassValues<DockSize>? = null)
```

```kotlin
fun FlowContent.daisyDockItem(
    id: HtmlId? = null,
    active: Boolean = false,  // Makes the Dock Item look active
    extraClasses: String? = null,
    block: (BUTTON.() -> Unit) = {},
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyDockItemClasses(active: Boolean = false)
```

```kotlin
fun FlowContent.daisyDockLabel(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (SPAN.() -> Unit) = {},
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyDockLabelClasses()
```
