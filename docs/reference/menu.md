<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/menu/+page.md
Regenerate: just generate
-->

# Menu

[DaisyUI documentation →](https://daisyui.com/components/menu/)

Navigation lists with submenus. Renders `<ul class="menu ...">`.

```kotlin
// MenuSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyMenu(
    id: HtmlId? = null,
    size: MenuSize? = null,
    active: Boolean = false,  // For the element inside <li> to look active
    disabled: Boolean = false,  // For the element inside <li> to look disabled
    dropdownShow: Boolean = false,  // Shows the menu-dropdown-toggle and menu-dropdown collapsible submenu using JS
    focus: Boolean = false,  // For the element inside <li> to look focused
    horizontal: Boolean = false,  // Horizontal menu
    paged: Boolean = false,  // Shows one level at a time and turns the open summary into a back button
    vertical: Boolean = false,  // Vertical menu (default)
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyMenuTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (H2.() -> Unit)? = null,
    content: (H2.() -> Unit)? = null,
)
```

```kotlin
fun FlowContent.daisyMenuDropdown(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyMenuDropdownToggle(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
