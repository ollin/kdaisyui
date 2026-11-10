// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/mockup-window/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.Tag

/**
 * Window mockup shows a box that looks like an operating system window. Renders `<div class="mockup-window ...">`.
 * The same classes on another element: `a { daisyMockupWindowClasses(…) }`; see [daisyMockupWindowClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyMockupWindow(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("mockup-window")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyMockupWindow` writes, on the element this is called on. */
fun Tag.daisyMockupWindowClasses() {
    addClassNames("mockup-window")
}
