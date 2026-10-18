<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/list/+page.md
Regenerate: just generate
-->

# List

[DaisyUI documentation →](https://daisyui.com/components/list/)

Styled list layout. Renders `<ul class="list ...">`.

```kotlin
fun FlowContent.daisyList(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyListClasses()
```

```kotlin
fun UL.daisyListRow(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (LI.() -> Unit)? = null,
    content: (LI.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyListRowClasses()
```

```kotlin
// Classes only, for a child DaisyUI gives no class of its own:
fun Tag.daisyListCellClasses(colGrow: Boolean = false, colWrap: Boolean = false)
```
