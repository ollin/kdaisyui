// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/input/+page.md
// Regenerate: cd codegen && npm run generate

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.InputType

enum class InputVariant(internal val className: String) {
    Neutral("input-neutral"),
    Primary("input-primary"),
    Secondary("input-secondary"),
    Accent("input-accent"),
    Info("input-info"),
    Success("input-success"),
    Warning("input-warning"),
    Error("input-error"),
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
    ghost: Boolean = false,
    type: InputType = InputType.text,
    placeholder: String? = null,
    value: String? = null,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
) {
    input {
        this.type = type
        addClassNames("input")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (ghost) addClassNames("input-ghost")
        if (placeholder != null) this.placeholder = placeholder
        if (value != null) this.value = value
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
        if (attrs != null) attrs()
    }
}
