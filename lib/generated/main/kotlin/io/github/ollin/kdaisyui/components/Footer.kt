// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/footer/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.footer
import kotlinx.html.FOOTER
import kotlinx.html.h2
import kotlinx.html.H2

/**
 * Footer can contain logo, copyright notice, and links to other pages. Renders `<footer class="footer ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param center — Aligns footer content to center
 * @param horizontal — Puts footer columns next to each other horizontally
 * @param vertical — Puts footer columns under each other vertically
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyFooter(
    id: HtmlId? = null,
    center: Boolean = false,
    horizontal: Boolean = false,
    vertical: Boolean = false,
    extraClasses: String? = null,
    attrs: (FOOTER.() -> Unit)? = null,
    content: (FOOTER.() -> Unit),
) {
    footer {
        if (id != null) attributes["id"] = id.id
        addClassNames("footer")
        if (center) addClassNames("footer-center")
        if (horizontal) addClassNames("footer-horizontal")
        if (vertical) addClassNames("footer-vertical")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<h2 class="footer-title ...">`. */
fun FlowContent.daisyFooterTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (H2.() -> Unit)? = null,
    content: (H2.() -> Unit)? = null,
) {
    h2 {
        if (id != null) attributes["id"] = id.id
        addClassNames("footer-title")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}
