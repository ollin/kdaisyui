// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/toast/+page.md
// Regenerate: cd codegen && npm run generate

package com.github.ollin.kdaisyui.components

import com.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

fun FlowContent.daisyToast(
    bottom: Boolean = false,
    center: Boolean = false,
    end: Boolean = false,
    middle: Boolean = false,
    start: Boolean = false,
    top: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("toast")
        if (bottom) addClassNames("toast-bottom")
        if (center) addClassNames("toast-center")
        if (end) addClassNames("toast-end")
        if (middle) addClassNames("toast-middle")
        if (start) addClassNames("toast-start")
        if (top) addClassNames("toast-top")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
