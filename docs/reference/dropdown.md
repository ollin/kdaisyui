<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/dropdown/+page.md
Regenerate: just generate
-->

# Dropdown

[DaisyUI documentation →](https://daisyui.com/components/dropdown/)

Context menus and action lists. Renders `<details class="dropdown ...">`.

```kotlin
// DropdownSide: Top | Bottom | Left | Right
// DropdownAlign: Start | Center | End
// DropdownState: Open | Close
fun FlowContent.daisyDropdown(
    id: HtmlId? = null,
    side: ClassValues<DropdownSide>? = null,
    align: ClassValues<DropdownAlign>? = null,
    state: ClassValues<DropdownState>? = null,
    hover: Boolean = false,  // Opens on hover too
    extraClasses: String? = null,
    attrs: (DETAILS.() -> Unit)? = null,
    content: (DETAILS.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyDropdownContent(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
)
```
