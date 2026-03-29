// GENERATED — DO NOT EDIT
// Source: codegen/src/config/indicator.yml + daisyui indicator.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

fun FlowContent.daisyIndicator(
    bottom: Boolean = false,
    center: Boolean = false,
    end: Boolean = false,
    middle: Boolean = false,
    start: Boolean = false,
    top: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("indicator")
        if (bottom) addClassNames("indicator-bottom")
        if (center) addClassNames("indicator-center")
        if (end) addClassNames("indicator-end")
        if (middle) addClassNames("indicator-middle")
        if (start) addClassNames("indicator-start")
        if (top) addClassNames("indicator-top")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
