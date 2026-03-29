// GENERATED — DO NOT EDIT
// Source: codegen/src/config/select.yml + daisyui select.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.select
import kotlinx.html.SELECT

enum class SelectVariant(internal val className: String) {
    Accent("select-accent"),
    Error("select-error"),
    Ghost("select-ghost"),
    Info("select-info"),
    Neutral("select-neutral"),
    Primary("select-primary"),
    Secondary("select-secondary"),
    Success("select-success"),
    Warning("select-warning"),
}

enum class SelectSize(internal val className: String) {
    Xs("select-xs"),
    Sm("select-sm"),
    Md("select-md"),
    Lg("select-lg"),
    Xl("select-xl"),
}


fun FlowContent.daisySelect(
    variant: SelectVariant? = null,
    size: SelectSize? = null,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (SELECT.() -> Unit)? = null,
    content: (SELECT.() -> Unit),
) {
    select {
        addClassNames("select")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
