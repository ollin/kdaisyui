// GENERATED — DO NOT EDIT
// Source: codegen/src/config/checkbox.yml + daisyui checkbox.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.InputType

enum class CheckboxVariant(internal val className: String) {
    Accent("checkbox-accent"),
    Error("checkbox-error"),
    Info("checkbox-info"),
    Neutral("checkbox-neutral"),
    Primary("checkbox-primary"),
    Secondary("checkbox-secondary"),
    Success("checkbox-success"),
    Warning("checkbox-warning"),
}

enum class CheckboxSize(internal val className: String) {
    Xs("checkbox-xs"),
    Sm("checkbox-sm"),
    Md("checkbox-md"),
    Lg("checkbox-lg"),
    Xl("checkbox-xl"),
}


fun FlowContent.daisyCheckbox(
    variant: CheckboxVariant? = null,
    size: CheckboxSize? = null,
    checked: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
) {
    input {
        type = InputType.checkBox
        addClassNames("checkbox")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (checked) this.checked = true
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
    }
}
