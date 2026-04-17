// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/radio/+page.md
// Regenerate: cd codegen && npm run generate

package com.github.ollin.kdaisyui.components

import com.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.InputType

enum class RadioVariant(internal val className: String) {
    Neutral("radio-neutral"),
    Primary("radio-primary"),
    Secondary("radio-secondary"),
    Accent("radio-accent"),
    Success("radio-success"),
    Warning("radio-warning"),
    Info("radio-info"),
    Error("radio-error"),
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
    attrs: (INPUT.() -> Unit)? = null,
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
        if (attrs != null) attrs()
    }
}
