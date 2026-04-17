// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/navbar/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

fun FlowContent.daisyNavbar(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("navbar")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyNavbarStart(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("navbar-start")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyNavbarCenter(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("navbar-center")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyNavbarEnd(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("navbar-end")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
