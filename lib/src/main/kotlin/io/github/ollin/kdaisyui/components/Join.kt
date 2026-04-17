// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/join/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

fun FlowContent.daisyJoin(
    horizontal: Boolean = false,
    vertical: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("join")
        if (horizontal) addClassNames("join-horizontal")
        if (vertical) addClassNames("join-vertical")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
