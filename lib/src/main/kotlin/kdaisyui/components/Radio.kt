// GENERATED — DO NOT EDIT
// Source: codegen/src/config/radio.yml + daisyui radio.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.InputType

enum class RadioVariant(internal val className: String) {
    Accent("radio-accent"),
    Error("radio-error"),
    Info("radio-info"),
    Neutral("radio-neutral"),
    Primary("radio-primary"),
    Secondary("radio-secondary"),
    Success("radio-success"),
    Warning("radio-warning"),
}

enum class RadioSize(internal val className: String) {
    Xs("radio-xs"),
    Sm("radio-sm"),
    Md("radio-md"),
    Lg("radio-lg"),
    Xl("radio-xl"),
}


fun FlowContent.daisyRadio(
    variant: RadioVariant? = null,
    size: RadioSize? = null,
    name: String? = null,
    checked: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
) {
    input {
        type = InputType.radio
        addClassNames("radio")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (name != null) this.name = name
        if (checked) this.checked = true
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
    }
}
