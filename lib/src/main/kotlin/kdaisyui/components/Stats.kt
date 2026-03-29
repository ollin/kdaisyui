// GENERATED — DO NOT EDIT
// Source: codegen/src/config/stats.yml + daisyui stat.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

fun FlowContent.daisyStats(
    horizontal: Boolean = false,
    vertical: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("stats")
        if (horizontal) addClassNames("stats-horizontal")
        if (vertical) addClassNames("stats-vertical")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyStat(
    actions: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("stat")
        if (actions) addClassNames("stat-actions")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyStatTitle(
    text: String,
    extraClasses: String? = null,
) {
    div {
        addClassNames("stat-title")
        addClassNames(extraClasses)
        +text
    }
}

fun FlowContent.daisyStatValue(
    text: String,
    extraClasses: String? = null,
) {
    div {
        addClassNames("stat-value")
        addClassNames(extraClasses)
        +text
    }
}

fun FlowContent.daisyStatDesc(
    text: String,
    extraClasses: String? = null,
) {
    div {
        addClassNames("stat-desc")
        addClassNames(extraClasses)
        +text
    }
}

fun FlowContent.daisyStatFigure(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("stat-figure")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
