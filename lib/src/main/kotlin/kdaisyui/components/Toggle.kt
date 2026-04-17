// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/toggle/+page.md
// Regenerate: cd codegen && npm run generate

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.InputType

enum class ToggleVariant(internal val className: String) {
    Primary("toggle-primary"),
    Secondary("toggle-secondary"),
    Accent("toggle-accent"),
    Neutral("toggle-neutral"),
    Success("toggle-success"),
    Warning("toggle-warning"),
    Info("toggle-info"),
    Error("toggle-error"),
}

enum class ToggleSize(internal val className: String) {
    Xs("toggle-xs"),
    Sm("toggle-sm"),
    Md("toggle-md"),
    Lg("toggle-lg"),
    Xl("toggle-xl"),
}


fun FlowContent.daisyToggle(
    variant: ToggleVariant? = null,
    size: ToggleSize? = null,
    checked: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
) {
    input {
        type = InputType.checkBox
        addClassNames("toggle")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (checked) this.checked = true
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
        if (attrs != null) attrs()
    }
}
