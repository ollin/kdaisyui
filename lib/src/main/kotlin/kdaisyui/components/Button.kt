// GENERATED — DO NOT EDIT
// Source: codegen/src/config/button.yml + daisyui button.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.button
import kotlinx.html.BUTTON
import kotlinx.html.ButtonType
import kotlinx.html.FlowContent

enum class ButtonVariant(internal val className: String) {
    Accent("btn-accent"),
    Error("btn-error"),
    Ghost("btn-ghost"),
    Info("btn-info"),
    Link("btn-link"),
    Neutral("btn-neutral"),
    Primary("btn-primary"),
    Secondary("btn-secondary"),
    Success("btn-success"),
    Warning("btn-warning"),
}

enum class ButtonSize(internal val className: String) {
    Xs("btn-xs"),
    Sm("btn-sm"),
    Md("btn-md"),
    Lg("btn-lg"),
    Xl("btn-xl"),
}


fun FlowContent.daisyButton(
    text: String? = null,
    variant: ButtonVariant? = null,
    size: ButtonSize? = null,
    active: Boolean = false,
    block: Boolean = false,
    circle: Boolean = false,
    dash: Boolean = false,
    outline: Boolean = false,
    soft: Boolean = false,
    square: Boolean = false,
    wide: Boolean = false,
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
        if (active) addClassNames("btn-active")
        if (block) addClassNames("btn-block")
        if (circle) addClassNames("btn-circle")
        if (dash) addClassNames("btn-dash")
        if (outline) addClassNames("btn-outline")
        if (soft) addClassNames("btn-soft")
        if (square) addClassNames("btn-square")
        if (wide) addClassNames("btn-wide")
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
