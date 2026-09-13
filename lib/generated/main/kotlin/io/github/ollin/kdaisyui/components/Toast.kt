// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/toast/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/** VerticalPlacement variants for this component (CSS prefix: `toast-`) */
enum class ToastVerticalPlacement(internal val className: String) {
    /** CSS: `toast-top` — align vertically to top */
    Top("toast-top"),
    /** CSS: `toast-middle` — align vertically to middle */
    Middle("toast-middle"),
    /** CSS: `toast-bottom` — align vertically to bottom */
    Bottom("toast-bottom"),
}

/** HorizontalPlacement variants for this component (CSS prefix: `toast-`) */
enum class ToastHorizontalPlacement(internal val className: String) {
    /** CSS: `toast-start` — align horizontally to the left */
    Start("toast-start"),
    /** CSS: `toast-center` — align horizontally to the center */
    Center("toast-center"),
    /** CSS: `toast-end` — align horizontally to the right */
    End("toast-end"),
}


/**
 * Toast is a wrapper to stack elements, positioned on the corner of page. Renders `<div class="toast ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param verticalPlacement — VerticalPlacement variant
 * @param horizontalPlacement — HorizontalPlacement variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyToast(
    id: HtmlId? = null,
    verticalPlacement: ToastVerticalPlacement? = null,
    horizontalPlacement: ToastHorizontalPlacement? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("toast")
        if (verticalPlacement != null) addClassNames(verticalPlacement.className)
        if (horizontalPlacement != null) addClassNames(horizontalPlacement.className)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
