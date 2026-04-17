// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/indicator/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

fun FlowContent.daisyIndicator(
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
        addClassNames("indicator")
        if (bottom) addClassNames("indicator-bottom")
        if (center) addClassNames("indicator-center")
        if (end) addClassNames("indicator-end")
        if (middle) addClassNames("indicator-middle")
        if (start) addClassNames("indicator-start")
        if (top) addClassNames("indicator-top")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyIndicatorItem(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("indicator-item")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
