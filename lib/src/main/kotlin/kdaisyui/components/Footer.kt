// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/footer/+page.md
// Regenerate: cd codegen && npm run generate

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.footer
import kotlinx.html.FOOTER
import kotlinx.html.h2
import kotlinx.html.H2

fun FlowContent.daisyFooter(
    center: Boolean = false,
    horizontal: Boolean = false,
    vertical: Boolean = false,
    extraClasses: String? = null,
    attrs: (FOOTER.() -> Unit)? = null,
    content: (FOOTER.() -> Unit),
) {
    footer {
        addClassNames("footer")
        if (center) addClassNames("footer-center")
        if (horizontal) addClassNames("footer-horizontal")
        if (vertical) addClassNames("footer-vertical")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyFooterTitle(
    text: String? = null,
    extraClasses: String? = null,
    attrs: (H2.() -> Unit)? = null,
    content: (H2.() -> Unit)? = null,
) {
    h2 {
        addClassNames("footer-title")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}
