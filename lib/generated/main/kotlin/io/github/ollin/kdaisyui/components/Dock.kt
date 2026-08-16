// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/dock/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/** Size variants for this component (CSS prefix: `dock-`) */
enum class DockSize(internal val className: String) {
    /** CSS: `dock-xs` — Extra Small Dock */
    Xs("dock-xs"),
    /** CSS: `dock-sm` — Small Dock */
    Sm("dock-sm"),
    /** CSS: `dock-md` — Medium Dock */
    Md("dock-md"),
    /** CSS: `dock-lg` — Large Dock */
    Lg("dock-lg"),
    /** CSS: `dock-xl` — Extra Large Dock */
    Xl("dock-xl"),
}


/**
 * Dock (also know as Bottom navigation or Bottom bar) is a UI element that provides navigation options to the user. Dock sticks to the bottom of the screen. Renders `<div class="dock ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param active — Makes the Dock Item look active
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyDock(
    id: HtmlId? = null,
    size: DockSize? = null,
    active: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("dock")
        if (size != null) addClassNames(size.className)
        if (active) addClassNames("dock-active")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="dock-label ...">`. */
fun FlowContent.daisyDockLabel(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("dock-label")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
