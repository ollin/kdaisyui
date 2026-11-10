// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/hover-3d/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.Tag

/**
 * Hover 3D is a wrapper component that adds a 3D hover effect to its content. When we hover over the component, it tilts and rotates based on the mouse position, creating an interactive 3D effect. Renders `<div class="hover-3d ...">`.
 * The same classes on another element: `a { daisyHover3dClasses(…) }`; see [daisyHover3dClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyHover3d(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("hover-3d")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyHover3d` writes, on the element this is called on. */
fun Tag.daisyHover3dClasses() {
    addClassNames("hover-3d")
}
