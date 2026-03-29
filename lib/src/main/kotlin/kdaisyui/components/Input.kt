package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.INPUT
import kotlinx.html.InputType
import kotlinx.html.input

enum class InputSize(internal val className: String) {
    Xs("input-xs"),
    Sm("input-sm"),
    Md("input-md"),
    Lg("input-lg"),
}

fun FlowContent.daisyInput(
    type: InputType = InputType.text,
    size: InputSize? = null,
    ghost: Boolean = false,
    bordered: Boolean = false,
    placeholder: String? = null,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
) {
    input {
        this.type = type
        addClassNames("input")
        if (size != null) addClassNames(size.className)
        if (ghost) addClassNames("input-ghost")
        if (bordered) addClassNames("input-bordered")
        if (placeholder != null) this.placeholder = placeholder
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
        if (attrs != null) attrs()
    }
}
