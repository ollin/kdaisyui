package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FIELDSET
import kotlinx.html.FlowContent
import kotlinx.html.fieldSet

fun FlowContent.daisyFieldset(
    extraClasses: String? = null,
    attrs: (FIELDSET.() -> Unit)? = null,
    content: (FIELDSET.() -> Unit),
) {
    fieldSet {
        addClassNames("fieldset")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
