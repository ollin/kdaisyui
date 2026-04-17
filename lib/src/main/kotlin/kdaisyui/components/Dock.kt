// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/dock/+page.md
// Regenerate: cd codegen && npm run generate

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

enum class DockSize(internal val className: String) {
    Xs("dock-xs"),
    Sm("dock-sm"),
    Md("dock-md"),
    Lg("dock-lg"),
    Xl("dock-xl"),
}


fun FlowContent.daisyDock(
    size: DockSize? = null,
    active: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("dock")
        if (size != null) addClassNames(size.className)
        if (active) addClassNames("dock-active")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyDockLabel(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("dock-label")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
