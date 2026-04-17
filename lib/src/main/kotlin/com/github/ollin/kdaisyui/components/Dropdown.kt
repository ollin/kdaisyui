// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/dropdown/+page.md
// Regenerate: cd codegen && npm run generate

package com.github.ollin.kdaisyui.components

import com.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.details
import kotlinx.html.DETAILS
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

fun FlowContent.daisyDropdown(
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

fun FlowContent.daisyDropdownContent(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("dropdown-content")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
