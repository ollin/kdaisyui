// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/countdown/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.span
import kotlinx.html.SPAN
import kotlinx.html.Tag

/**
 * Countdown gives you a transition effect when you change a number between 0 to 999. Renders `<span class="countdown ...">`.
 * The same classes on another element: `a { daisyCountdownClasses(…) }`; see [daisyCountdownClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyCountdown(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (SPAN.() -> Unit) = {},
) {
    span {
        if (id != null) attributes["id"] = id.id
        addClassNames("countdown")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyCountdown` writes, on the element this is called on. */
fun Tag.daisyCountdownClasses() {
    addClassNames("countdown")
}
