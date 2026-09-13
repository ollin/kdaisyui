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

/** Direction variants for this component (CSS prefix: `alert-`) */
enum class AlertDirection(internal val className: String) {
    /** CSS: `alert-vertical` — Vertical layout, good for mobile */
    Vertical("alert-vertical"),
    /** CSS: `alert-horizontal` — Horizontal layout, good for desktop */
    Horizontal("alert-horizontal"),
}


/**
 * Alert informs users about important events. Renders `<div class="alert ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param direction — Direction variant
 * @param dash — dash outline style
 * @param outline — outline style
 * @param soft — soft style
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyAlert(
    id: HtmlId? = null,
    variant: AlertVariant? = null,
    direction: AlertDirection? = null,
    dash: Boolean = false,
    outline: Boolean = false,
    soft: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        role = "alert"
        addClassNames("alert")
        if (variant != null) addClassNames(variant.className)
        if (direction != null) addClassNames(direction.className)
        if (dash) addClassNames("alert-dash")
        if (outline) addClassNames("alert-outline")
        if (soft) addClassNames("alert-soft")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
