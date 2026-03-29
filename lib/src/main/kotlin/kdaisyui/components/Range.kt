// GENERATED — DO NOT EDIT
// Source: codegen/src/config/range.yml + daisyui range.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.InputType

enum class RangeVariant(internal val className: String) {
    Accent("range-accent"),
    Error("range-error"),
    Info("range-info"),
    Neutral("range-neutral"),
    Primary("range-primary"),
    Secondary("range-secondary"),
    Success("range-success"),
    Warning("range-warning"),
}

enum class RangeSize(internal val className: String) {
    Xs("range-xs"),
    Sm("range-sm"),
    Md("range-md"),
    Lg("range-lg"),
    Xl("range-xl"),
}


fun FlowContent.daisyRange(
    variant: RangeVariant? = null,
    size: RangeSize? = null,
    min: String? = null,
    max: String? = null,
    value: String? = null,
    step: String? = null,
    disabled: Boolean = false,
    extraClasses: String? = null,
) {
    input {
        type = InputType.range
        addClassNames("range")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (min != null) this.min = min
        if (max != null) this.max = max
        if (value != null) this.value = value
        if (step != null) this.step = step
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
    }
}
