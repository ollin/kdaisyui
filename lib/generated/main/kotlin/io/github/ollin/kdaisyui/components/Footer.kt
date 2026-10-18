// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/footer/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.footer
import kotlinx.html.FOOTER
import kotlinx.html.h6
import kotlinx.html.H6

/** Direction variants for this component (CSS prefix: `footer-`) */
enum class FooterDirection(internal val className: String) : ClassValues<FooterDirection> {
    /** CSS: `footer-horizontal` — Puts footer columns next to each other horizontally */
    Horizontal("footer-horizontal"),
    /** CSS: `footer-vertical` — Puts footer columns under each other vertically */
    Vertical("footer-vertical"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Footer can contain logo, copyright notice, and links to other pages. Renders `<footer class="footer ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param direction — Direction variant
 * @param center — Aligns footer content to center
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyFooter(
    id: HtmlId? = null,
    direction: ClassValues<FooterDirection>? = null,
    center: Boolean = false,
    extraClasses: String? = null,
    attrs: (FOOTER.() -> Unit)? = null,
    content: (FOOTER.() -> Unit),
) {
    footer {
        if (id != null) attributes["id"] = id.id
        addClassNames("footer")
        addClassNames(direction)
        if (center) addClassNames("footer-center")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<h6 class="footer-title ...">`. */
fun FlowContent.daisyFooterTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (H6.() -> Unit)? = null,
    content: (H6.() -> Unit)? = null,
) {
    h6 {
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
