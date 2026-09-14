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

/** Color variants for this component (CSS prefix: `tooltip-`) */
enum class TooltipVariant(internal val className: String) : ClassValues<TooltipVariant> {
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

/** SidePlacement variants for this component (CSS prefix: `tooltip-`) */
enum class TooltipSidePlacement(internal val className: String) : ClassValues<TooltipSidePlacement> {
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

/** AlignPlacement variants for this component (CSS prefix: `tooltip-`) */
enum class TooltipAlignPlacement(internal val className: String) : ClassValues<TooltipAlignPlacement> {
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
 * @param variant — Color variant
 * @param sidePlacement — SidePlacement variant
 * @param alignPlacement — AlignPlacement variant
 * @param open — Force open tooltip
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyTooltip(
    id: HtmlId? = null,
    variant: ClassValues<TooltipVariant>? = null,
    sidePlacement: ClassValues<TooltipSidePlacement>? = null,
    alignPlacement: ClassValues<TooltipAlignPlacement>? = null,
    open: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("tooltip")
        addClassNames(variant)
        addClassNames(sidePlacement)
        addClassNames(alignPlacement)
        if (open) addClassNames("tooltip-open")
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
