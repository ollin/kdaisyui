// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/tooltip/+page.md
// Regenerate: cd codegen && npm run generate

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

enum class TooltipVariant(internal val className: String) {
    Neutral("tooltip-neutral"),
    Primary("tooltip-primary"),
    Secondary("tooltip-secondary"),
    Accent("tooltip-accent"),
    Info("tooltip-info"),
    Success("tooltip-success"),
    Warning("tooltip-warning"),
    Error("tooltip-error"),
}


fun FlowContent.daisyTooltip(
    variant: TooltipVariant? = null,
    bottom: Boolean = false,
    left: Boolean = false,
    open: Boolean = false,
    right: Boolean = false,
    top: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("tooltip")
        if (variant != null) addClassNames(variant.className)
        if (bottom) addClassNames("tooltip-bottom")
        if (left) addClassNames("tooltip-left")
        if (open) addClassNames("tooltip-open")
        if (right) addClassNames("tooltip-right")
        if (top) addClassNames("tooltip-top")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyTooltipContent(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("tooltip-content")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
