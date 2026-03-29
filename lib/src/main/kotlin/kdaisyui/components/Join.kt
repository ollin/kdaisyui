// GENERATED — DO NOT EDIT
// Source: codegen/src/config/join.yml + daisyui join.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

fun FlowContent.daisyJoin(
    vertical: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("join")
        if (vertical) addClassNames("join-vertical")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
