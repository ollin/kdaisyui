// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/dropdown/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.details
import kotlinx.html.DETAILS
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/**
 * Dropdown can open a menu or any other element when the button is clicked. Renders `<details class="dropdown ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param close — Force close
 * @param hover — Opens on hover too
 * @param open — Force open
 * @param end
 * @param start
 * @param top
 * @param bottom
 * @param left
 * @param right
 * @param center
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyDropdown(
    id: HtmlId? = null,
    close: Boolean = false,
    hover: Boolean = false,
    open: Boolean = false,
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
) {
    details {
        if (id != null) attributes["id"] = id.id
        addClassNames("dropdown")
        if (close) addClassNames("dropdown-close")
        if (hover) addClassNames("dropdown-hover")
        if (open) addClassNames("dropdown-open")
        if (end) addClassNames("dropdown-end")
        if (start) addClassNames("dropdown-start")
        if (top) addClassNames("dropdown-top")
        if (bottom) addClassNames("dropdown-bottom")
        if (left) addClassNames("dropdown-left")
        if (right) addClassNames("dropdown-right")
        if (center) addClassNames("dropdown-center")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="dropdown-content ...">`. */
fun FlowContent.daisyDropdownContent(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("dropdown-content")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
