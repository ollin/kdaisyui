// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/hovergallery/+page.md
// Regenerate: cd codegen && npm run generate

package com.github.ollin.kdaisyui.components

import com.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.figure
import kotlinx.html.FIGURE
import kotlinx.html.FlowContent

fun FlowContent.daisyHoverGallery(
    extraClasses: String? = null,
    attrs: (FIGURE.() -> Unit)? = null,
    content: (FIGURE.() -> Unit),
) {
    figure {
        addClassNames("hover-gallery")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
