package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.LABEL
import kotlinx.html.SPAN
import kotlinx.html.label
import kotlinx.html.span

fun FlowContent.daisyLabel(
    text: String? = null,
    extraClasses: String? = null,
    attrs: (LABEL.() -> Unit)? = null,
    content: (LABEL.() -> Unit)? = null,
) {
    label {
        addClassNames("label")
        addClassNames(extraClasses)
        if (attrs != null) attrs()

        when {
            content != null -> content()
            text != null -> +text
        }
    }
}

fun FlowContent.daisyLabelText(
    text: String,
    extraClasses: String? = null,
) {
    span {
        addClassNames("label-text")
        addClassNames(extraClasses)
        +text
    }
}
