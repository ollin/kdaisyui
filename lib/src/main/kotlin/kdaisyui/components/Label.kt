// GENERATED — DO NOT EDIT
// Source: codegen/src/config/label.yml + daisyui label.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.label
import kotlinx.html.LABEL
import kotlinx.html.span
import kotlinx.html.SPAN

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
