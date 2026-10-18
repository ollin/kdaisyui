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
// MenuDirection: Vertical | Horizontal
fun FlowContent.daisyMenu(
    id: HtmlId? = null,
    size: ClassValues<MenuSize>? = null,
    direction: ClassValues<MenuDirection>? = null,
    active: Boolean = false,  // For the element inside <li> to look active
    disabled: Boolean = false,  // For the element inside <li> to look disabled
    dropdownShow: Boolean = false,  // Shows the menu-dropdown-toggle and menu-dropdown collapsible submenu using JS
    focus: Boolean = false,  // For the element inside <li> to look focused
    paged: Boolean = false,  // Shows one level at a time and turns the open summary into a back button
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyMenuClasses(size: ClassValues<MenuSize>? = null, direction: ClassValues<MenuDirection>? = null, active: Boolean = false, disabled: Boolean = false, dropdownShow: Boolean = false, focus: Boolean = false, paged: Boolean = false)
```

```kotlin
fun FlowContent.daisyMenuTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (H2.() -> Unit)? = null,
    content: (H2.() -> Unit)? = null,
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyMenuTitleClasses()
```

```kotlin
fun FlowContent.daisyMenuDropdown(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyMenuDropdownClasses()
```

```kotlin
fun FlowContent.daisyMenuDropdownToggle(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyMenuDropdownToggleClasses()
```
