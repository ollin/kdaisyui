// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/megamenu/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/** Size variants for this component (CSS prefix: `megamenu-`) */
enum class MegamenuSize(internal val className: String) {
    /** CSS: `megamenu-xs` — Extra small size */
    Xs("megamenu-xs"),
    /** CSS: `megamenu-sm` — Small size */
    Sm("megamenu-sm"),
    /** CSS: `megamenu-md` — Medium size */
    Md("megamenu-md"),
    /** CSS: `megamenu-lg` — Large size */
    Lg("megamenu-lg"),
    /** CSS: `megamenu-xl` — Extra large size */
    Xl("megamenu-xl"),
}


/**
 * A megamenu is a large, horizontal menu where each item opens a popover to show a large block of navigation links. Megamenu must be used once, on top of the page. Inside each popover, you can use a daisyUI menu, or any custom content. Megamenu fits better on large screens only, and for small screens, you can hide the megamenu and show the content in a dropdown or a drawer. Renders `<div class="megamenu ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param full — megamenu dropdown will fill the entire width of the page
 * @param vertical — Hides horizontal megamenu so we can open a vertical megamenu in small screens
 * @param wide — megamenu dropdown will be as wide as the megamenu container
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyMegamenu(
    id: HtmlId? = null,
    size: MegamenuSize? = null,
    full: Boolean = false,
    vertical: Boolean = false,
    wide: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("megamenu")
        if (size != null) addClassNames(size.className)
        if (full) addClassNames("megamenu-full")
        if (vertical) addClassNames("megamenu-vertical")
        if (wide) addClassNames("megamenu-wide")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="megamenu-active ...">`. */
fun FlowContent.daisyMegamenuActive(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("megamenu-active")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
