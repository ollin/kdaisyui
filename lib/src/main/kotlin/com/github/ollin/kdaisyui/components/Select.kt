// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/select/+page.md
// Regenerate: cd codegen && npm run generate

package com.github.ollin.kdaisyui.components

import com.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.select
import kotlinx.html.SELECT

enum class SelectVariant(internal val className: String) {
    Neutral("select-neutral"),
    Primary("select-primary"),
    Secondary("select-secondary"),
    Accent("select-accent"),
    Info("select-info"),
    Success("select-success"),
    Warning("select-warning"),
    Error("select-error"),
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
    ghost: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (SELECT.() -> Unit)? = null,
    content: (SELECT.() -> Unit),
) {
    select {
        addClassNames("select")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (ghost) addClassNames("select-ghost")
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
