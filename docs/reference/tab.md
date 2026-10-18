<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/tab/+page.md
Regenerate: just generate
-->

# Tab

[DaisyUI documentation →](https://daisyui.com/components/tab/)

Tabbed navigation. Renders `<div class="tabs ..." role="tablist">`.

```kotlin
// TabsSize: Xs | Sm | Md | Lg | Xl
// TabsPlacement: Top | Bottom
// TabsStyle: Box | Border | Lift
fun FlowContent.daisyTabs(
    id: HtmlId? = null,
    size: ClassValues<TabsSize>? = null,
    placement: ClassValues<TabsPlacement>? = null,
    style: ClassValues<TabsStyle>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyTabsClasses(size: ClassValues<TabsSize>? = null, placement: ClassValues<TabsPlacement>? = null, style: ClassValues<TabsStyle>? = null)
```

```kotlin
fun FlowContent.daisyTab(
    id: HtmlId? = null,
    active: Boolean = false,  // Makes a single tab look active
    disabled: Boolean = false,  // Makes a single tab look disabled
    extraClasses: String? = null,
    attrs: (A.() -> Unit)? = null,
    content: (A.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyTabClasses(active: Boolean = false, disabled: Boolean = false)
```

```kotlin
fun FlowContent.daisyTabRadio(
    id: HtmlId? = null,
    active: Boolean = false,  // Makes a single tab look active
    disabled: Boolean = false,  // Makes a single tab look disabled
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyTabRadioClasses(active: Boolean = false, disabled: Boolean = false)
```

```kotlin
fun FlowContent.daisyTabContent(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyTabContentClasses()
```
