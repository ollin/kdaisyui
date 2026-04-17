// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/divider/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

enum class DividerVariant(internal val className: String) {
    Neutral("divider-neutral"),
    Primary("divider-primary"),
    Secondary("divider-secondary"),
    Accent("divider-accent"),
    Success("divider-success"),
    Warning("divider-warning"),
    Info("divider-info"),
    Error("divider-error"),
}


fun FlowContent.daisyDivider(
    variant: DividerVariant? = null,
    end: Boolean = false,
    horizontal: Boolean = false,
    start: Boolean = false,
    vertical: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("divider")
        if (variant != null) addClassNames(variant.className)
        if (end) addClassNames("divider-end")
        if (horizontal) addClassNames("divider-horizontal")
        if (start) addClassNames("divider-start")
        if (vertical) addClassNames("divider-vertical")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
