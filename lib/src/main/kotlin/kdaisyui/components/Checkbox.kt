package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.INPUT
import kotlinx.html.InputType
import kotlinx.html.input

enum class CheckboxSize(internal val className: String) {
    Xs("checkbox-xs"),
    Sm("checkbox-sm"),
    Md("checkbox-md"),
    Lg("checkbox-lg"),
}

fun FlowContent.daisyCheckbox(
    size: CheckboxSize? = null,
    checked: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
) {
    input {
        type = InputType.checkBox
        addClassNames("checkbox")
        if (size != null) addClassNames(size.className)
        if (checked) this.checked = true
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
        if (attrs != null) attrs()
    }
}
