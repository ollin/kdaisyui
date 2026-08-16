// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/skeleton/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/**
 * Skeleton is a component that can be used to show a loading state of a component. Renders `<div class="skeleton ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param text — Animates the text color instead of background color
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisySkeleton(
    id: HtmlId? = null,
    text: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("skeleton")
        if (text) addClassNames("skeleton-text")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
