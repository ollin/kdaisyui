// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/menu/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.h2
import kotlinx.html.H2
import kotlinx.html.ul
import kotlinx.html.UL

/** Size variants for this component (CSS prefix: `menu-`) */
enum class MenuSize(internal val className: String) {
    /** CSS: `menu-xs` — Extra small size */
    Xs("menu-xs"),
    /** CSS: `menu-sm` — Small size */
    Sm("menu-sm"),
    /** CSS: `menu-md` — Medium size */
    Md("menu-md"),
    /** CSS: `menu-lg` — Large size */
    Lg("menu-lg"),
    /** CSS: `menu-xl` — Extra large size */
    Xl("menu-xl"),
}


/**
 * Menu is used to display a list of links vertically or horizontally. Renders `<ul class="menu ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param active — For the element inside <li> to look active
 * @param disabled — For the element inside <li> to look disabled
 * @param dropdownShow — Shows the menu-dropdown-toggle and menu-dropdown collapsible submenu using JS
 * @param focus — For the element inside <li> to look focused
 * @param horizontal — Horizontal menu
 * @param paged — Shows one level at a time and turns the open summary into a back button
 * @param vertical — Vertical menu (default)
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyMenu(
    id: HtmlId? = null,
    size: MenuSize? = null,
    active: Boolean = false,
    disabled: Boolean = false,
    dropdownShow: Boolean = false,
    focus: Boolean = false,
    horizontal: Boolean = false,
    paged: Boolean = false,
    vertical: Boolean = false,
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
) {
    ul {
        if (id != null) attributes["id"] = id.id
        addClassNames("menu")
        if (size != null) addClassNames(size.className)
        if (active) addClassNames("menu-active")
        if (disabled) addClassNames("menu-disabled")
        if (dropdownShow) addClassNames("menu-dropdown-show")
        if (focus) addClassNames("menu-focus")
        if (horizontal) addClassNames("menu-horizontal")
        if (paged) addClassNames("menu-paged")
        if (vertical) addClassNames("menu-vertical")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<h2 class="menu-title ...">`. */
fun FlowContent.daisyMenuTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (H2.() -> Unit)? = null,
    content: (H2.() -> Unit)? = null,
) {
    h2 {
        if (id != null) attributes["id"] = id.id
        addClassNames("menu-title")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}

/** Renders `<div class="menu-dropdown ...">`. */
fun FlowContent.daisyMenuDropdown(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("menu-dropdown")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="menu-dropdown-toggle ...">`. */
fun FlowContent.daisyMenuDropdownToggle(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("menu-dropdown-toggle")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
