package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.INPUT
import kotlinx.html.InputType
import kotlinx.html.input

enum class RadioSize(internal val className: String) {
    Xs("radio-xs"),
    Sm("radio-sm"),
    Md("radio-md"),
    Lg("radio-lg"),
}

fun FlowContent.daisyRadio(
    name: String? = null,
    size: RadioSize? = null,
    checked: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
) {
    input {
        type = InputType.radio
        addClassNames("radio")
        if (name != null) this.name = name
        if (size != null) addClassNames(size.className)
        if (checked) this.checked = true
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
        if (attrs != null) attrs()
    }
}
