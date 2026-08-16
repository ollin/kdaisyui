// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/toast/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/**
 * Toast is a wrapper to stack elements, positioned on the corner of page. Renders `<div class="toast ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param bottom — align vertically to bottom
 * @param center — align horizontally to the center
 * @param end — align horizontally to the right
 * @param middle — align vertically to middle
 * @param start — align horizontally to the left
 * @param top — align vertically to top
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyToast(
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
        addClassNames("toast")
        if (bottom) addClassNames("toast-bottom")
        if (center) addClassNames("toast-center")
        if (end) addClassNames("toast-end")
        if (middle) addClassNames("toast-middle")
        if (start) addClassNames("toast-start")
        if (top) addClassNames("toast-top")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
