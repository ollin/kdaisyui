<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/dropdown/+page.md
Regenerate: just generate
-->

# Dropdown

[DaisyUI documentation →](https://daisyui.com/components/dropdown/)

Context menus and action lists. Renders `<details class="dropdown ...">`.

```kotlin
fun FlowContent.daisyDropdown(
    id: HtmlId? = null,
    close: Boolean = false,  // Force close
    hover: Boolean = false,  // Opens on hover too
    open: Boolean = false,  // Force open
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
