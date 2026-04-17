// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/stack/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

fun FlowContent.daisyStack(
    bottom: Boolean = false,
    end: Boolean = false,
    start: Boolean = false,
    top: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("stack")
        if (bottom) addClassNames("stack-bottom")
        if (end) addClassNames("stack-end")
        if (start) addClassNames("stack-start")
        if (top) addClassNames("stack-top")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
