// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/alert/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.role

/** Color variants for this component (CSS prefix: `alert-`) */
enum class AlertVariant(internal val className: String) {
    /** CSS: `alert-info` — info color */
    Info("alert-info"),
    /** CSS: `alert-success` — success color */
    Success("alert-success"),
    /** CSS: `alert-warning` — warning color */
    Warning("alert-warning"),
    /** CSS: `alert-error` — error color */
    Error("alert-error"),
}


/**
 * Alert informs users about important events. Renders `<div class="alert ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param dash — dash outline style
 * @param horizontal — Horizontal layout, good for desktop
 * @param outline — outline style
 * @param soft — soft style
 * @param vertical — Vertical layout, good for mobile
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyAlert(
    id: HtmlId? = null,
    variant: AlertVariant? = null,
    dash: Boolean = false,
    horizontal: Boolean = false,
    outline: Boolean = false,
    soft: Boolean = false,
    vertical: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        role = "alert"
        addClassNames("alert")
        if (variant != null) addClassNames(variant.className)
        if (dash) addClassNames("alert-dash")
        if (horizontal) addClassNames("alert-horizontal")
        if (outline) addClassNames("alert-outline")
        if (soft) addClassNames("alert-soft")
        if (vertical) addClassNames("alert-vertical")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
