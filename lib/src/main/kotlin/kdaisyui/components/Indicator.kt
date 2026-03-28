package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.div

fun FlowContent.daisyIndicator(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("indicator")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
