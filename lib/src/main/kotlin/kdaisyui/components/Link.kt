package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.A
import kotlinx.html.FlowContent
import kotlinx.html.a

fun FlowContent.daisyLink(
    text: String? = null,
    href: String? = null,
    hover: Boolean = false,
    extraClasses: String? = null,
    attrs: (A.() -> Unit)? = null,
    content: (A.() -> Unit)? = null,
) {
    a {
        addClassNames("link")
        if (hover) addClassNames("link-hover")
        if (href != null) this.href = href
        addClassNames(extraClasses)
        if (attrs != null) attrs()

        when {
            content != null -> content()
            text != null -> +text
        }
    }
}
