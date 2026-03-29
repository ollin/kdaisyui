// GENERATED — DO NOT EDIT
// Source: codegen/src/config/alert.yml + daisyui alert.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.role

enum class AlertVariant(internal val className: String) {
    Error("alert-error"),
    Info("alert-info"),
    Success("alert-success"),
    Warning("alert-warning"),
}


fun FlowContent.daisyAlert(
    variant: AlertVariant? = null,
    dash: Boolean = false,
    horizontal: Boolean = false,
    outline: Boolean = false,
    soft: Boolean = false,
    vertical: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        role = "alert"
        addClassNames("alert")
        if (variant != null) addClassNames(variant.className)
        if (dash) addClassNames("alert-dash")
        if (horizontal) addClassNames("alert-horizontal")
        if (outline) addClassNames("alert-outline")
        if (soft) addClassNames("alert-soft")
        if (vertical) addClassNames("alert-vertical")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
