// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/indicator/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.span
import kotlinx.html.SPAN

enum class IndicatorHorizontalPlacement(internal val className: String) : ClassValues<IndicatorHorizontalPlacement> {
    /** CSS: `indicator-start` */
    Start("indicator-start"),
    /** CSS: `indicator-center` */
    Center("indicator-center"),
    /** CSS: `indicator-end` */
    End("indicator-end"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

enum class IndicatorVerticalPlacement(internal val className: String) : ClassValues<IndicatorVerticalPlacement> {
    /** CSS: `indicator-top` */
    Top("indicator-top"),
    /** CSS: `indicator-middle` */
    Middle("indicator-middle"),
    /** CSS: `indicator-bottom` */
    Bottom("indicator-bottom"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Indicators are used to place an element on the corner of another element. Renders `<div class="indicator ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param horizontalPlacement — HorizontalPlacement variant
 * @param verticalPlacement — VerticalPlacement variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyIndicator(
    id: HtmlId? = null,
    horizontalPlacement: ClassValues<IndicatorHorizontalPlacement>? = null,
    verticalPlacement: ClassValues<IndicatorVerticalPlacement>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("indicator")
        addClassNames(horizontalPlacement)
        addClassNames(verticalPlacement)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<span class="indicator-item ...">`. */
fun FlowContent.daisyIndicatorItem(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
) {
    span {
        if (id != null) attributes["id"] = id.id
        addClassNames("indicator-item")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
