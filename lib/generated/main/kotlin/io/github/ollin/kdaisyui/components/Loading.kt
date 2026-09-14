// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/loading/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.span
import kotlinx.html.SPAN

/** Size variants for this component (CSS prefix: `loading-`) */
enum class LoadingSize(internal val className: String) : ClassValues<LoadingSize> {
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
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Style variants for this component (CSS prefix: `loading-`) */
enum class LoadingStyle(internal val className: String) : ClassValues<LoadingStyle> {
    /** CSS: `loading-spinner` — spinner animation */
    Spinner("loading-spinner"),
    /** CSS: `loading-dots` — dots animation */
    Dots("loading-dots"),
    /** CSS: `loading-ring` — ring animation */
    Ring("loading-ring"),
    /** CSS: `loading-ball` — ball animation */
    Ball("loading-ball"),
    /** CSS: `loading-bars` — bars animation */
    Bars("loading-bars"),
    /** CSS: `loading-infinity` — infinity animation */
    Infinity("loading-infinity"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Loading shows an animation to indicate that something is loading. Renders `<span class="loading ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param style — Style variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyLoading(
    id: HtmlId? = null,
    size: ClassValues<LoadingSize>? = null,
    style: ClassValues<LoadingStyle>? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
) {
    span {
        if (id != null) attributes["id"] = id.id
        addClassNames("loading")
        addClassNames(size)
        addClassNames(style)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
