<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/dropdown/+page.md
Regenerate: just generate
-->

# Dropdown

[DaisyUI documentation →](https://daisyui.com/components/dropdown/)

Context menus and action lists. Renders `<details class="dropdown ...">`.

```kotlin
// DropdownModifier: Hover | Open
// DropdownHorizontalPlacement: Start | Center | End
// DropdownVerticalPlacement: Top | Bottom
fun FlowContent.daisyDropdown(
    id: HtmlId? = null,
    modifier: ClassValues<DropdownModifier>? = null,
    horizontalPlacement: ClassValues<DropdownHorizontalPlacement>? = null,
    verticalPlacement: ClassValues<DropdownVerticalPlacement>? = null,
    close: Boolean = false,  // Force close
    end: Boolean = false,
    start: Boolean = false,
    top: Boolean = false,
    bottom: Boolean = false,
    left: Boolean = false,
    right: Boolean = false,
    center: Boolean = false,
    extraClasses: String? = null,
    attrs: (DETAILS.() -> Unit)? = null,
    content: (DETAILS.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyDropdownContent(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
