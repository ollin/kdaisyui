package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.SELECT
import kotlinx.html.select

enum class SelectSize(internal val className: String) {
    Xs("select-xs"),
    Sm("select-sm"),
    Md("select-md"),
    Lg("select-lg"),
}

fun FlowContent.daisySelect(
    size: SelectSize? = null,
    ghost: Boolean = false,
    bordered: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (SELECT.() -> Unit)? = null,
    content: (SELECT.() -> Unit),
) {
    select {
        addClassNames("select")
        if (size != null) addClassNames(size.className)
        if (ghost) addClassNames("select-ghost")
        if (bordered) addClassNames("select-bordered")
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
