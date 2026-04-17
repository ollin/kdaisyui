// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/button/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.button
import kotlinx.html.BUTTON
import kotlinx.html.ButtonType
import kotlinx.html.FlowContent

enum class ButtonVariant(internal val className: String) {
    Neutral("btn-neutral"),
    Primary("btn-primary"),
    Secondary("btn-secondary"),
    Accent("btn-accent"),
    Info("btn-info"),
    Success("btn-success"),
    Warning("btn-warning"),
    Error("btn-error"),
}

enum class ButtonSize(internal val className: String) {
    Xs("btn-xs"),
    Sm("btn-sm"),
    Md("btn-md"),
    Lg("btn-lg"),
    Xl("btn-xl"),
}


fun FlowContent.daisyButton(
    text: String? = null,
    variant: ButtonVariant? = null,
    size: ButtonSize? = null,
    active: Boolean = false,
    block: Boolean = false,
    circle: Boolean = false,
    dash: Boolean = false,
    ghost: Boolean = false,
    link: Boolean = false,
    outline: Boolean = false,
    soft: Boolean = false,
    square: Boolean = false,
    wide: Boolean = false,
    disabled: Boolean = false,
    type: ButtonType? = null,
    extraClasses: String? = null,
    attrs: (BUTTON.() -> Unit)? = null,
    content: (BUTTON.() -> Unit)? = null,
) {
    button {
        addClassNames("btn")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (active) addClassNames("btn-active")
        if (block) addClassNames("btn-block")
        if (circle) addClassNames("btn-circle")
        if (dash) addClassNames("btn-dash")
        if (ghost) addClassNames("btn-ghost")
        if (link) addClassNames("btn-link")
        if (outline) addClassNames("btn-outline")
        if (soft) addClassNames("btn-soft")
        if (square) addClassNames("btn-square")
        if (wide) addClassNames("btn-wide")
        if (disabled) this.disabled = true
        if (type != null) this.type = type
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}
