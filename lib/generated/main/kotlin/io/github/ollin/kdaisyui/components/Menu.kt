// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/menu/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.h2
import kotlinx.html.H2
import kotlinx.html.span
import kotlinx.html.SPAN
import kotlinx.html.Tag
import kotlinx.html.ul
import kotlinx.html.UL

/** Size variants for this component (CSS prefix: `menu-`) */
enum class MenuSize(internal val className: String) : ClassValues<MenuSize> {
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
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Direction variants for this component (CSS prefix: `menu-`) */
enum class MenuDirection(internal val className: String) : ClassValues<MenuDirection> {
    /** CSS: `menu-vertical` — Vertical menu (default) */
    Vertical("menu-vertical"),
    /** CSS: `menu-horizontal` — Horizontal menu */
    Horizontal("menu-horizontal"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Menu is used to display a list of links vertically or horizontally. Renders `<ul class="menu ...">`.
 * The same classes on another element: `a { daisyMenuClasses(…) }`; see [daisyMenuClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param direction — Direction variant
 * @param focus — For the element inside <li> to look focused
 * @param paged — Shows one level at a time and turns the open summary into a back button
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyMenu(
    id: HtmlId? = null,
    size: ClassValues<MenuSize>? = null,
    direction: ClassValues<MenuDirection>? = null,
    focus: Boolean = false,
    paged: Boolean = false,
    extraClasses: String? = null,
    block: (UL.() -> Unit) = {},
) {
    ul {
        if (id != null) attributes["id"] = id.id
        addClassNames("menu")
        addClassNames(size)
        addClassNames(direction)
        if (focus) addClassNames("menu-focus")
        if (paged) addClassNames("menu-paged")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyMenu` writes, on the element this is called on. */
fun Tag.daisyMenuClasses(
    size: ClassValues<MenuSize>? = null,
    direction: ClassValues<MenuDirection>? = null,
    focus: Boolean = false,
    paged: Boolean = false,
) {
    addClassNames("menu")
    addClassNames(size)
    addClassNames(direction)
    if (focus) addClassNames("menu-focus")
    if (paged) addClassNames("menu-paged")
}

/**
 * Renders `<h2 class="menu-title ...">`.
 * The same classes on another element: `a { daisyMenuTitleClasses(…) }`; see [daisyMenuTitleClasses].
 */
fun FlowContent.daisyMenuTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (H2.() -> Unit) = {},
) {
    h2 {
        if (id != null) attributes["id"] = id.id
        addClassNames("menu-title")
        addClassNames(extraClasses)
        block()
        if (text != null) +text
    }
}

/** The classes `daisyMenuTitle` writes, on the element this is called on. */
fun Tag.daisyMenuTitleClasses() {
    addClassNames("menu-title")
}

/**
 * Renders `<ul class="menu-dropdown ...">`.
 * The same classes on another element: `a { daisyMenuDropdownClasses(…) }`; see [daisyMenuDropdownClasses].
 */
fun FlowContent.daisyMenuDropdown(
    id: HtmlId? = null,
    show: Boolean = false,
    extraClasses: String? = null,
    block: (UL.() -> Unit) = {},
) {
    ul {
        if (id != null) attributes["id"] = id.id
        addClassNames("menu-dropdown")
        if (show) addClassNames("menu-dropdown-show")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyMenuDropdown` writes, on the element this is called on. */
fun Tag.daisyMenuDropdownClasses(
    show: Boolean = false,
) {
    addClassNames("menu-dropdown")
    if (show) addClassNames("menu-dropdown-show")
}

/**
 * Renders `<span class="menu-dropdown-toggle ...">`.
 * The same classes on another element: `a { daisyMenuDropdownToggleClasses(…) }`; see [daisyMenuDropdownToggleClasses].
 */
fun FlowContent.daisyMenuDropdownToggle(
    id: HtmlId? = null,
    dropdownShow: Boolean = false,
    extraClasses: String? = null,
    block: (SPAN.() -> Unit) = {},
) {
    span {
        if (id != null) attributes["id"] = id.id
        addClassNames("menu-dropdown-toggle")
        if (dropdownShow) addClassNames("menu-dropdown-show")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyMenuDropdownToggle` writes, on the element this is called on. */
fun Tag.daisyMenuDropdownToggleClasses(
    dropdownShow: Boolean = false,
) {
    addClassNames("menu-dropdown-toggle")
    if (dropdownShow) addClassNames("menu-dropdown-show")
}

/** The classes `daisyMenuItem` writes, on the element this is called on. */
fun Tag.daisyMenuItemClasses(
    active: Boolean = false,
    disabled: Boolean = false,
) {
    if (active) addClassNames("menu-active")
    if (disabled) addClassNames("menu-disabled")
}
