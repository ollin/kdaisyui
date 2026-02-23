package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.BUTTON
import kotlinx.html.ButtonType
import kotlinx.html.FlowContent
import kotlinx.html.button

enum class ButtonVariant(internal val className: String) {
    Neutral("btn-neutral"),
    Primary("btn-primary"),
    Secondary("btn-secondary"),
    Accent("btn-accent"),
    Ghost("btn-ghost"),
    Link("btn-link"),
    Info("btn-info"),
    Success("btn-success"),
    Warning("btn-warning"),
    Error("btn-error"),
}

enum class ButtonSize(internal val className: String) {
    Xs("btn-xs"),
    Sm("btn-sm"),
    Md("btn-md"),
    Lg("btn-lg"),
}

fun FlowContent.daisyButton(
    text: String? = null,
    variant: ButtonVariant? = null,
    size: ButtonSize? = null,
    outline: Boolean = false,
    wide: Boolean = false,
    block: Boolean = false,
    circle: Boolean = false,
    square: Boolean = false,
    disabled: Boolean = false,
    type: ButtonType? = null,
    extraClasses: String? = null,
    attrs: (BUTTON.() -> Unit)? = null,
    content: (BUTTON.() -> Unit)? = null,
) {
    button {
        addClassNames("btn")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (outline) addClassNames("btn-outline")
        if (wide) addClassNames("btn-wide")
        if (block) addClassNames("btn-block")
        if (circle) addClassNames("btn-circle")
        if (square) addClassNames("btn-square")
        if (disabled) this.disabled = true
        if (type != null) this.type = type
        addClassNames(extraClasses)

        if (attrs != null) attrs()

        when {
            content != null -> content()
            text != null -> +text
        }
    }
}
