// GENERATED — DO NOT EDIT
// Source: codegen/src/config/card.yml + daisyui card.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.h2
import kotlinx.html.H2

enum class CardSize(internal val className: String) {
    Xs("card-xs"),
    Sm("card-sm"),
    Md("card-md"),
    Lg("card-lg"),
    Xl("card-xl"),
}


fun FlowContent.daisyCard(
    size: CardSize? = null,
    actions: Boolean = false,
    border: Boolean = false,
    dash: Boolean = false,
    side: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("card")
        if (size != null) addClassNames(size.className)
        if (actions) addClassNames("card-actions")
        if (border) addClassNames("card-border")
        if (dash) addClassNames("card-dash")
        if (side) addClassNames("card-side")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyCardBody(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("card-body")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyCardTitle(
    text: String? = null,
    extraClasses: String? = null,
    attrs: (H2.() -> Unit)? = null,
    content: (H2.() -> Unit)? = null,
) {
    h2 {
        addClassNames("card-title")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}
