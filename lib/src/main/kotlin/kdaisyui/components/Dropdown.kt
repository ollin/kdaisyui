// GENERATED — DO NOT EDIT
// Source: codegen/src/config/dropdown.yml + daisyui dropdown.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.ul
import kotlinx.html.UL

fun FlowContent.daisyDropdown(
    bottom: Boolean = false,
    center: Boolean = false,
    close: Boolean = false,
    end: Boolean = false,
    hover: Boolean = false,
    left: Boolean = false,
    open: Boolean = false,
    right: Boolean = false,
    start: Boolean = false,
    top: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("dropdown")
        if (bottom) addClassNames("dropdown-bottom")
        if (center) addClassNames("dropdown-center")
        if (close) addClassNames("dropdown-close")
        if (end) addClassNames("dropdown-end")
        if (hover) addClassNames("dropdown-hover")
        if (left) addClassNames("dropdown-left")
        if (open) addClassNames("dropdown-open")
        if (right) addClassNames("dropdown-right")
        if (start) addClassNames("dropdown-start")
        if (top) addClassNames("dropdown-top")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyDropdownContent(
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
) {
    ul {
        addClassNames("menu dropdown-content")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
