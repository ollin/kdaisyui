// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/status/+page.md
// Regenerate: cd codegen && npm run generate

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.role
import kotlinx.html.span
import kotlinx.html.SPAN

enum class StatusVariant(internal val className: String) {
    Neutral("status-neutral"),
    Primary("status-primary"),
    Secondary("status-secondary"),
    Accent("status-accent"),
    Info("status-info"),
    Success("status-success"),
    Warning("status-warning"),
    Error("status-error"),
}

enum class StatusSize(internal val className: String) {
    Xs("status-xs"),
    Sm("status-sm"),
    Md("status-md"),
    Lg("status-lg"),
    Xl("status-xl"),
}


fun FlowContent.daisyStatus(
    variant: StatusVariant? = null,
    size: StatusSize? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
) {
    span {
        role = "status"
        addClassNames("status")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
