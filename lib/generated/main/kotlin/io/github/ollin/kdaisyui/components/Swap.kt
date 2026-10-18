// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/swap/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.label
import kotlinx.html.LABEL

/** Style variants for this component (CSS prefix: `swap-`) */
enum class SwapStyle(internal val className: String) : ClassValues<SwapStyle> {
    /** CSS: `swap-rotate` — Adds rotate effect to swap */
    Rotate("swap-rotate"),
    /** CSS: `swap-flip` — Adds flip effect to swap */
    Flip("swap-flip"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Swap allows you to toggle the visibility of two elements using a checkbox or a class name. Renders `<label class="swap ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param style — Style variant
 * @param active — Activates the swap (no need for checkbox)
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisySwap(
    id: HtmlId? = null,
    style: ClassValues<SwapStyle>? = null,
    active: Boolean = false,
    extraClasses: String? = null,
    attrs: (LABEL.() -> Unit)? = null,
    content: (LABEL.() -> Unit),
) {
    label {
        if (id != null) attributes["id"] = id.id
        addClassNames("swap")
        addClassNames(style)
        if (active) addClassNames("swap-active")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="swap-on ...">`. */
fun FlowContent.daisySwapOn(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("swap-on")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="swap-off ...">`. */
fun FlowContent.daisySwapOff(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("swap-off")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="swap-indeterminate ...">`. */
fun FlowContent.daisySwapIndeterminate(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("swap-indeterminate")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
