// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/text-rotate/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.span
import kotlinx.html.SPAN
import kotlinx.html.Tag

/**
 * Text Rotate can show up to 6 lines of text, one at a time, with an infinite loop animation. Duration is 10 seconds by default. The animation will pause on hover. Renders `<span class="text-rotate ...">`.
 * The same classes on another element: `a { daisyTextRotateClasses(…) }`; see [daisyTextRotateClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyTextRotate(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (SPAN.() -> Unit) = {},
) {
    span {
        if (id != null) attributes["id"] = id.id
        addClassNames("text-rotate")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyTextRotate` writes, on the element this is called on. */
fun Tag.daisyTextRotateClasses() {
    addClassNames("text-rotate")
}
