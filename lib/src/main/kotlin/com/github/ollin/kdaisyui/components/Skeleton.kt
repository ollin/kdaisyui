// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/skeleton/+page.md
// Regenerate: cd codegen && npm run generate

package com.github.ollin.kdaisyui.components

import com.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

fun FlowContent.daisySkeleton(
    text: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("skeleton")
        if (text) addClassNames("skeleton-text")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
