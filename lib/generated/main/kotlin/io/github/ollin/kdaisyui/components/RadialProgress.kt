// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/radial-progress/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.Tag

/**
 * Radial progress can be used to show the progress of a task or to show the passing of time. Renders `<div class="radial-progress ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyRadialProgress(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("radial-progress")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyRadialProgress` writes, on the element this is called on. */
fun Tag.daisyRadialProgressClasses() {
    addClassNames("radial-progress")
}
