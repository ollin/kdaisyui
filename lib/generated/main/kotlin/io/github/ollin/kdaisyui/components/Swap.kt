// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/swap/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.label
import kotlinx.html.LABEL

/**
 * Swap allows you to toggle the visibility of two elements using a checkbox or a class name. Renders `<label class="swap ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param active — Activates the swap (no need for checkbox)
 * @param flip — Adds flip effect to swap
 * @param rotate — Adds rotate effect to swap
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisySwap(
    id: HtmlId? = null,
    active: Boolean = false,
    flip: Boolean = false,
    rotate: Boolean = false,
    extraClasses: String? = null,
    attrs: (LABEL.() -> Unit)? = null,
    content: (LABEL.() -> Unit),
) {
    label {
        if (id != null) attributes["id"] = id.id
        addClassNames("swap")
        if (active) addClassNames("swap-active")
        if (flip) addClassNames("swap-flip")
        if (rotate) addClassNames("swap-rotate")
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
