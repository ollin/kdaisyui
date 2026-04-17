// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/menu/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.h2
import kotlinx.html.H2
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
    dropdownShow: Boolean = false,
    focus: Boolean = false,
    horizontal: Boolean = false,
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
        if (dropdownShow) addClassNames("menu-dropdown-show")
        if (focus) addClassNames("menu-focus")
        if (horizontal) addClassNames("menu-horizontal")
        if (vertical) addClassNames("menu-vertical")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyMenuTitle(
    text: String? = null,
    extraClasses: String? = null,
    attrs: (H2.() -> Unit)? = null,
    content: (H2.() -> Unit)? = null,
) {
    h2 {
        addClassNames("menu-title")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}

fun FlowContent.daisyMenuDropdown(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("menu-dropdown")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyMenuDropdownToggle(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("menu-dropdown-toggle")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
