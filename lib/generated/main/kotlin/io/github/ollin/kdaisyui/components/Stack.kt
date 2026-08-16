// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/stack/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/**
 * Stack visually puts elements on top of each other. Renders `<div class="stack ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param bottom — Aligns the children elements to the bottom
 * @param end — Aligns the children elements to the end (horizontally)
 * @param start — Aligns the children elements to the start (horizontally)
 * @param top — Aligns the children elements to the top
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyStack(
    id: HtmlId? = null,
    bottom: Boolean = false,
    end: Boolean = false,
    start: Boolean = false,
    top: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("stack")
        if (bottom) addClassNames("stack-bottom")
        if (end) addClassNames("stack-end")
        if (start) addClassNames("stack-start")
        if (top) addClassNames("stack-top")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
