// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/filter/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.form
import kotlinx.html.FORM

fun FlowContent.daisyFilter(
    extraClasses: String? = null,
    attrs: (FORM.() -> Unit)? = null,
    content: (FORM.() -> Unit),
) {
    form {
        addClassNames("filter")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyFilterReset(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("filter-reset")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
