// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/collapse/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.h2
import kotlinx.html.H2

fun FlowContent.daisyCollapse(
    arrow: Boolean = false,
    close: Boolean = false,
    open: Boolean = false,
    plus: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("collapse")
        if (arrow) addClassNames("collapse-arrow")
        if (close) addClassNames("collapse-close")
        if (open) addClassNames("collapse-open")
        if (plus) addClassNames("collapse-plus")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyCollapseTitle(
    text: String? = null,
    extraClasses: String? = null,
    attrs: (H2.() -> Unit)? = null,
    content: (H2.() -> Unit)? = null,
) {
    h2 {
        addClassNames("collapse-title")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}

fun FlowContent.daisyCollapseContent(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("collapse-content")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
