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
import kotlinx.html.Tag

enum class IndicatorItemHorizontalPlacement(internal val className: String) : ClassValues<IndicatorItemHorizontalPlacement> {
    /** CSS: `indicator-start` */
    Start("indicator-start"),
    /** CSS: `indicator-center` */
    Center("indicator-center"),
    /** CSS: `indicator-end` */
    End("indicator-end"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

enum class IndicatorItemVerticalPlacement(internal val className: String) : ClassValues<IndicatorItemVerticalPlacement> {
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
 * The same classes on another element: `a { daisyIndicatorClasses(…) }`; see [daisyIndicatorClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyIndicator(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("indicator")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyIndicator` writes, on the element this is called on. */
fun Tag.daisyIndicatorClasses() {
    addClassNames("indicator")
}

/**
 * Renders `<span class="indicator-item ...">`.
 * The same classes on another element: `a { daisyIndicatorItemClasses(…) }`; see [daisyIndicatorItemClasses].
 */
fun FlowContent.daisyIndicatorItem(
    id: HtmlId? = null,
    horizontalPlacement: ClassValues<IndicatorItemHorizontalPlacement>? = null,
    verticalPlacement: ClassValues<IndicatorItemVerticalPlacement>? = null,
    extraClasses: String? = null,
    block: (SPAN.() -> Unit) = {},
) {
    span {
        if (id != null) attributes["id"] = id.id
        addClassNames("indicator-item")
        addClassNames(horizontalPlacement)
        addClassNames(verticalPlacement)
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyIndicatorItem` writes, on the element this is called on. */
fun Tag.daisyIndicatorItemClasses(
    horizontalPlacement: ClassValues<IndicatorItemHorizontalPlacement>? = null,
    verticalPlacement: ClassValues<IndicatorItemVerticalPlacement>? = null,
) {
    addClassNames("indicator-item")
    addClassNames(horizontalPlacement)
    addClassNames(verticalPlacement)
}
