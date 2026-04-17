// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/diff/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.figure
import kotlinx.html.FIGURE
import kotlinx.html.FlowContent

fun FlowContent.daisyDiff(
    extraClasses: String? = null,
    attrs: (FIGURE.() -> Unit)? = null,
    content: (FIGURE.() -> Unit),
) {
    figure {
        addClassNames("diff")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyDiffItem1(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("diff-item-1")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyDiffItem2(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("diff-item-2")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyDiffResizer(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("diff-resizer")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
