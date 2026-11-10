// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/dock/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.button
import kotlinx.html.BUTTON
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.span
import kotlinx.html.SPAN
import kotlinx.html.Tag

/** Size variants for this component (CSS prefix: `dock-`) */
enum class DockSize(internal val className: String) : ClassValues<DockSize> {
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
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Dock (also know as Bottom navigation or Bottom bar) is a UI element that provides navigation options to the user. Dock sticks to the bottom of the screen. Renders `<div class="dock ...">`.
 * The same classes on another element: `a { daisyDockClasses(…) }`; see [daisyDockClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyDock(
    id: HtmlId? = null,
    size: ClassValues<DockSize>? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("dock")
        addClassNames(size)
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyDock` writes, on the element this is called on. */
fun Tag.daisyDockClasses(
    size: ClassValues<DockSize>? = null,
) {
    addClassNames("dock")
    addClassNames(size)
}

/**
 * Structural wrapper. Renders `<button>`.
 * The same classes on another element: `a { daisyDockItemClasses(…) }`; see [daisyDockItemClasses].
 */
fun FlowContent.daisyDockItem(
    id: HtmlId? = null,
    active: Boolean = false,
    extraClasses: String? = null,
    block: (BUTTON.() -> Unit) = {},
) {
    button {
        if (id != null) attributes["id"] = id.id
        if (active) addClassNames("dock-active")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyDockItem` writes, on the element this is called on. */
fun Tag.daisyDockItemClasses(
    active: Boolean = false,
) {
    if (active) addClassNames("dock-active")
}

/**
 * Renders `<span class="dock-label ...">`.
 * The same classes on another element: `a { daisyDockLabelClasses(…) }`; see [daisyDockLabelClasses].
 */
fun FlowContent.daisyDockLabel(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (SPAN.() -> Unit) = {},
) {
    span {
        if (id != null) attributes["id"] = id.id
        addClassNames("dock-label")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyDockLabel` writes, on the element this is called on. */
fun Tag.daisyDockLabelClasses() {
    addClassNames("dock-label")
}
