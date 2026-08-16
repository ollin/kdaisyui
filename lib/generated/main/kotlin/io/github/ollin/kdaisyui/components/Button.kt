// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/button/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.button
import kotlinx.html.BUTTON
import kotlinx.html.ButtonType
import kotlinx.html.FlowContent

/** Color variants for this component (CSS prefix: `btn-`) */
enum class ButtonVariant(internal val className: String) {
    /** CSS: `btn-neutral` — neutral color */
    Neutral("btn-neutral"),
    /** CSS: `btn-primary` — primary color */
    Primary("btn-primary"),
    /** CSS: `btn-secondary` — secondary color */
    Secondary("btn-secondary"),
    /** CSS: `btn-accent` — accent color */
    Accent("btn-accent"),
    /** CSS: `btn-info` — info color */
    Info("btn-info"),
    /** CSS: `btn-success` — success color */
    Success("btn-success"),
    /** CSS: `btn-warning` — warning color */
    Warning("btn-warning"),
    /** CSS: `btn-error` — error color */
    Error("btn-error"),
}

/** Size variants for this component (CSS prefix: `btn-`) */
enum class ButtonSize(internal val className: String) {
    /** CSS: `btn-xs` — Extra small size */
    Xs("btn-xs"),
    /** CSS: `btn-sm` — Small size */
    Sm("btn-sm"),
    /** CSS: `btn-md` — Medium size (default) */
    Md("btn-md"),
    /** CSS: `btn-lg` — Large size */
    Lg("btn-lg"),
    /** CSS: `btn-xl` — Extra large size */
    Xl("btn-xl"),
}


/**
 * Buttons allow the user to take actions or make choices. Renders `<button class="btn ...">`.
 * @param text — Shortcut for inline text content (mutually exclusive with [content])
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param size — Size variant
 * @param active — looks active
 * @param block — Full width
 * @param circle — 1:1 ratio with rounded corners
 * @param dash — dash style
 * @param ghost — ghost style
 * @param link — looks like a link
 * @param outline — outline style
 * @param soft — soft style
 * @param square — 1:1 ratio
 * @param wide — more horizontal padding
 * @param disabled
 * @param type
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content (takes precedence over [text] if both are set)
 */
fun FlowContent.daisyButton(
    text: String? = null,
    id: HtmlId? = null,
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
        if (id != null) attributes["id"] = id.id
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
        if (disabled) { this.disabled = true; addClassNames("btn-disabled") }
        if (type != null) this.type = type
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}
