// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/loading/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.span
import kotlinx.html.SPAN

/** Size variants for this component (CSS prefix: `loading-`) */
enum class LoadingSize(internal val className: String) {
    /** CSS: `loading-xs` — Extra small size */
    Xs("loading-xs"),
    /** CSS: `loading-sm` — Small size */
    Sm("loading-sm"),
    /** CSS: `loading-md` — Medium size */
    Md("loading-md"),
    /** CSS: `loading-lg` — Large size */
    Lg("loading-lg"),
    /** CSS: `loading-xl` — Extra large size */
    Xl("loading-xl"),
}


/**
 * Loading shows an animation to indicate that something is loading. Renders `<span class="loading ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param ball — ball animation
 * @param bars — bars animation
 * @param dots — dots animation
 * @param infinity — infinity animation
 * @param ring — ring animation
 * @param spinner — spinner animation
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyLoading(
    id: HtmlId? = null,
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
        if (id != null) attributes["id"] = id.id
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
