// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/tooltip/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.Tag

/** Color variants for this component (CSS prefix: `tooltip-`) */
enum class TooltipColor(internal val className: String) : ClassValues<TooltipColor> {
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
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Side variants for this component (CSS prefix: `tooltip-`) */
enum class TooltipSide(internal val className: String) : ClassValues<TooltipSide> {
    /** CSS: `tooltip-top` — Put tooltip on top */
    Top("tooltip-top"),
    /** CSS: `tooltip-bottom` — Put tooltip on bottom */
    Bottom("tooltip-bottom"),
    /** CSS: `tooltip-left` — Put tooltip on left */
    Left("tooltip-left"),
    /** CSS: `tooltip-right` — Put tooltip on right */
    Right("tooltip-right"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Align variants for this component (CSS prefix: `tooltip-`) */
enum class TooltipAlign(internal val className: String) : ClassValues<TooltipAlign> {
    /** CSS: `tooltip-start` — Align tooltip on start */
    Start("tooltip-start"),
    /** CSS: `tooltip-center` — Align tooltip on center */
    Center("tooltip-center"),
    /** CSS: `tooltip-end` — Align tooltip on end */
    End("tooltip-end"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Tooltip can be used to show a message when hovering over an element. Renders `<div class="tooltip ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param color — Color variant
 * @param side — Side variant
 * @param align — Align variant
 * @param open — Force open tooltip
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyTooltip(
    id: HtmlId? = null,
    color: ClassValues<TooltipColor>? = null,
    side: ClassValues<TooltipSide>? = null,
    align: ClassValues<TooltipAlign>? = null,
    open: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("tooltip")
        addClassNames(color)
        addClassNames(side)
        addClassNames(align)
        if (open) addClassNames("tooltip-open")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyTooltip` writes, on the element this is called on. */
fun Tag.daisyTooltipClasses(
    color: ClassValues<TooltipColor>? = null,
    side: ClassValues<TooltipSide>? = null,
    align: ClassValues<TooltipAlign>? = null,
    open: Boolean = false,
) {
    addClassNames("tooltip")
    addClassNames(color)
    addClassNames(side)
    addClassNames(align)
    if (open) addClassNames("tooltip-open")
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

/** The classes `daisyTooltipContent` writes, on the element this is called on. */
fun Tag.daisyTooltipContentClasses() {
    addClassNames("tooltip-content")
}
