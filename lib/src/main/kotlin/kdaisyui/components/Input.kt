// GENERATED — DO NOT EDIT
// Source: codegen/src/config/input.yml + daisyui input.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.InputType

enum class InputVariant(internal val className: String) {
    Accent("input-accent"),
    Error("input-error"),
    Ghost("input-ghost"),
    Info("input-info"),
    Neutral("input-neutral"),
    Primary("input-primary"),
    Secondary("input-secondary"),
    Success("input-success"),
    Warning("input-warning"),
}

enum class InputSize(internal val className: String) {
    Xs("input-xs"),
    Sm("input-sm"),
    Md("input-md"),
    Lg("input-lg"),
    Xl("input-xl"),
}


fun FlowContent.daisyInput(
    variant: InputVariant? = null,
    size: InputSize? = null,
    type: InputType = InputType.text,
    placeholder: String? = null,
    value: String? = null,
    disabled: Boolean = false,
    extraClasses: String? = null,
) {
    input {
        this.type = type
        addClassNames("input")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (placeholder != null) this.placeholder = placeholder
        if (value != null) this.value = value
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
    }
}
