// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/list/+page.md
// Regenerate: cd codegen && npm run generate

package com.github.ollin.kdaisyui.components

import com.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.ul
import kotlinx.html.UL

fun FlowContent.daisyList(
    colGrow: Boolean = false,
    colWrap: Boolean = false,
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
) {
    ul {
        addClassNames("list")
        if (colGrow) addClassNames("list-col-grow")
        if (colWrap) addClassNames("list-col-wrap")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
