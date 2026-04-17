// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/themecontroller/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT

fun FlowContent.daisyThemeController(
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
    content: (INPUT.() -> Unit),
) {
    input {
        addClassNames("theme-controller")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
