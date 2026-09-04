// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/textrotate/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.span
import kotlinx.html.SPAN

/**
 * Text Rotate can show up to 6 lines of text, one at a time, with an infinite loop animation. Duration is 10 seconds by default. The animation will pause on hover. Renders `<span class="text-rotate ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyTextRotate(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
) {
    span {
        if (id != null) attributes["id"] = id.id
        addClassNames("text-rotate")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
