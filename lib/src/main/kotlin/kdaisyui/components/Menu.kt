// GENERATED — DO NOT EDIT
// Source: codegen/src/config/menu.yml + daisyui menu.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.ul
import kotlinx.html.UL

enum class MenuSize(internal val className: String) {
    Xs("menu-xs"),
    Sm("menu-sm"),
    Md("menu-md"),
    Lg("menu-lg"),
    Xl("menu-xl"),
}


fun FlowContent.daisyMenu(
    size: MenuSize? = null,
    active: Boolean = false,
    disabled: Boolean = false,
    dropdown: Boolean = false,
    dropdownToggle: Boolean = false,
    focus: Boolean = false,
    horizontal: Boolean = false,
    title: Boolean = false,
    vertical: Boolean = false,
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
) {
    ul {
        addClassNames("menu")
        if (size != null) addClassNames(size.className)
        if (active) addClassNames("menu-active")
        if (disabled) addClassNames("menu-disabled")
        if (dropdown) addClassNames("menu-dropdown")
        if (dropdownToggle) addClassNames("menu-dropdown-toggle")
        if (focus) addClassNames("menu-focus")
        if (horizontal) addClassNames("menu-horizontal")
        if (title) addClassNames("menu-title")
        if (vertical) addClassNames("menu-vertical")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
