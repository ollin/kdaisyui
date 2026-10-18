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
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyDock(
    id: HtmlId? = null,
    size: ClassValues<DockSize>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("dock")
        addClassNames(size)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyDock` writes, on the element this is called on. */
fun Tag.daisyDockClasses(
    size: ClassValues<DockSize>? = null,
) {
    addClassNames("dock")
    addClassNames(size)
}

/** Structural wrapper. Renders `<button>`. */
fun FlowContent.daisyDockItem(
    id: HtmlId? = null,
    active: Boolean = false,
    extraClasses: String? = null,
    attrs: (BUTTON.() -> Unit)? = null,
    content: (BUTTON.() -> Unit),
) {
    button {
        if (id != null) attributes["id"] = id.id
        if (active) addClassNames("dock-active")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyDockItem` writes, on the element this is called on. */
fun Tag.daisyDockItemClasses(
    active: Boolean = false,
) {
    if (active) addClassNames("dock-active")
}

/** Renders `<span class="dock-label ...">`. */
fun FlowContent.daisyDockLabel(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
) {
    span {
        if (id != null) attributes["id"] = id.id
        addClassNames("dock-label")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyDockLabel` writes, on the element this is called on. */
fun Tag.daisyDockLabelClasses() {
    addClassNames("dock-label")
}
