// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/indicator/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/**
 * Indicators are used to place an element on the corner of another element. Renders `<div class="indicator ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param bottom
 * @param center
 * @param end
 * @param middle
 * @param start
 * @param top
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyIndicator(
    id: HtmlId? = null,
    bottom: Boolean = false,
    center: Boolean = false,
    end: Boolean = false,
    middle: Boolean = false,
    start: Boolean = false,
    top: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("indicator")
        if (bottom) addClassNames("indicator-bottom")
        if (center) addClassNames("indicator-center")
        if (end) addClassNames("indicator-end")
        if (middle) addClassNames("indicator-middle")
        if (start) addClassNames("indicator-start")
        if (top) addClassNames("indicator-top")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="indicator-item ...">`. */
fun FlowContent.daisyIndicatorItem(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("indicator-item")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
