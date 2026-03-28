package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.div

fun FlowContent.daisyAvatar(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("avatar")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
