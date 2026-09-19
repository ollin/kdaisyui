// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/alert/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.role

/** Color variants for this component (CSS prefix: `alert-`) */
enum class AlertVariant(internal val className: String) : ClassValues<AlertVariant> {
    /** CSS: `alert-info` — info color */
    Info("alert-info"),
    /** CSS: `alert-success` — success color */
    Success("alert-success"),
    /** CSS: `alert-warning` — warning color */
    Warning("alert-warning"),
    /** CSS: `alert-error` — error color */
    Error("alert-error"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Style variants for this component (CSS prefix: `alert-`) */
enum class AlertStyle(internal val className: String) : ClassValues<AlertStyle> {
    /** CSS: `alert-outline` — outline style */
    Outline("alert-outline"),
    /** CSS: `alert-dash` — dash outline style */
    Dash("alert-dash"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Direction variants for this component (CSS prefix: `alert-`) */
enum class AlertDirection(internal val className: String) : ClassValues<AlertDirection> {
    /** CSS: `alert-vertical` — Vertical layout, good for mobile */
    Vertical("alert-vertical"),
    /** CSS: `alert-horizontal` — Horizontal layout, good for desktop */
    Horizontal("alert-horizontal"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Alert informs users about important events. Renders `<div class="alert ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param style — Style variant
 * @param direction — Direction variant
 * @param soft — soft style
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyAlert(
    id: HtmlId? = null,
    variant: ClassValues<AlertVariant>? = null,
    style: ClassValues<AlertStyle>? = null,
    direction: ClassValues<AlertDirection>? = null,
    soft: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        role = "alert"
        addClassNames("alert")
        addClassNames(variant)
        addClassNames(style)
        addClassNames(direction)
        if (soft) addClassNames("alert-soft")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
