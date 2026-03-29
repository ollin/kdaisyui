package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.INPUT
import kotlinx.html.InputType
import kotlinx.html.input

enum class ToggleSize(internal val className: String) {
    Xs("toggle-xs"),
    Sm("toggle-sm"),
    Md("toggle-md"),
    Lg("toggle-lg"),
}

fun FlowContent.daisyToggle(
    size: ToggleSize? = null,
    checked: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
) {
    input {
        type = InputType.checkBox
        addClassNames("toggle")
        if (size != null) addClassNames(size.className)
        if (checked) this.checked = true
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
        if (attrs != null) attrs()
    }
}
