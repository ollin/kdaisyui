package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.role

enum class AlertVariant(internal val className: String) {
    Info("alert-info"),
    Success("alert-success"),
    Warning("alert-warning"),
    Error("alert-error"),
}

fun FlowContent.daisyAlert(
    variant: AlertVariant? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        role = "alert"
        addClassNames("alert")
        if (variant != null) addClassNames(variant.className)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
