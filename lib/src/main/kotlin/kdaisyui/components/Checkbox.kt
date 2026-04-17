// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/checkbox/+page.md
// Regenerate: cd codegen && npm run generate

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.InputType

enum class CheckboxVariant(internal val className: String) {
    Primary("checkbox-primary"),
    Secondary("checkbox-secondary"),
    Accent("checkbox-accent"),
    Neutral("checkbox-neutral"),
    Success("checkbox-success"),
    Warning("checkbox-warning"),
    Info("checkbox-info"),
    Error("checkbox-error"),
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
    attrs: (INPUT.() -> Unit)? = null,
) {
    input {
        type = InputType.checkBox
        addClassNames("checkbox")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (checked) this.checked = true
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
        if (attrs != null) attrs()
    }
}
