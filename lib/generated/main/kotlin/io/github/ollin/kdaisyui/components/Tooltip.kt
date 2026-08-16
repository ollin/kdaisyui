// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/tooltip/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/** Color variants for this component (CSS prefix: `tooltip-`) */
enum class TooltipVariant(internal val className: String) {
    /** CSS: `tooltip-primary` — primary color */
    Primary("tooltip-primary"),
    /** CSS: `tooltip-secondary` — secondary color */
    Secondary("tooltip-secondary"),
    /** CSS: `tooltip-accent` — accent color */
    Accent("tooltip-accent"),
    /** CSS: `tooltip-info` — info color */
    Info("tooltip-info"),
    /** CSS: `tooltip-success` — success color */
    Success("tooltip-success"),
    /** CSS: `tooltip-warning` — warning color */
    Warning("tooltip-warning"),
    /** CSS: `tooltip-error` — error color */
    Error("tooltip-error"),
}


/**
 * Tooltip can be used to show a message when hovering over an element. Renders `<div class="tooltip ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param bottom — Put tooltip on bottom
 * @param center — Align tooltip on center
 * @param end — Align tooltip on end
 * @param left — Put tooltip on left
 * @param open — Force open tooltip
 * @param right — Put tooltip on right
 * @param start — Align tooltip on start
 * @param top — Put tooltip on top
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyTooltip(
    id: HtmlId? = null,
    variant: TooltipVariant? = null,
    bottom: Boolean = false,
    center: Boolean = false,
    end: Boolean = false,
    left: Boolean = false,
    open: Boolean = false,
    right: Boolean = false,
    start: Boolean = false,
    top: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("tooltip")
        if (variant != null) addClassNames(variant.className)
        if (bottom) addClassNames("tooltip-bottom")
        if (center) addClassNames("tooltip-center")
        if (end) addClassNames("tooltip-end")
        if (left) addClassNames("tooltip-left")
        if (open) addClassNames("tooltip-open")
        if (right) addClassNames("tooltip-right")
        if (start) addClassNames("tooltip-start")
        if (top) addClassNames("tooltip-top")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="tooltip-content ...">`. */
fun FlowContent.daisyTooltipContent(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("tooltip-content")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
