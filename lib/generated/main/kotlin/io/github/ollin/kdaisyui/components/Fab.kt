// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/fab/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/**
 * FAB (Floating Action Button) stays in the bottom corner of screen. It includes a focusable and accessible element with button role. Clicking or focusing it shows additional buttons (known as Speed Dial buttons) in a vertical arrangement or a flower shape (quarter circle). Renders `<div class="fab ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param flower
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyFab(
    id: HtmlId? = null,
    flower: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("fab")
        if (flower) addClassNames("fab-flower")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="fab-close ...">`. */
fun FlowContent.daisyFabClose(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("fab-close")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="fab-main-action ...">`. */
fun FlowContent.daisyFabMainAction(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("fab-main-action")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
