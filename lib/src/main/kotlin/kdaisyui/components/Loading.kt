// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/loading/+page.md
// Regenerate: cd codegen && npm run generate

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.span
import kotlinx.html.SPAN

enum class LoadingSize(internal val className: String) {
    Xs("loading-xs"),
    Sm("loading-sm"),
    Md("loading-md"),
    Lg("loading-lg"),
    Xl("loading-xl"),
}


fun FlowContent.daisyLoading(
    size: LoadingSize? = null,
    ball: Boolean = false,
    bars: Boolean = false,
    dots: Boolean = false,
    infinity: Boolean = false,
    ring: Boolean = false,
    spinner: Boolean = false,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
) {
    span {
        addClassNames("loading")
        if (size != null) addClassNames(size.className)
        if (ball) addClassNames("loading-ball")
        if (bars) addClassNames("loading-bars")
        if (dots) addClassNames("loading-dots")
        if (infinity) addClassNames("loading-infinity")
        if (ring) addClassNames("loading-ring")
        if (spinner) addClassNames("loading-spinner")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
