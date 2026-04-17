// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/textrotate/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.span
import kotlinx.html.SPAN

fun FlowContent.daisyTextRotate(
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
) {
    span {
        addClassNames("text-rotate")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
