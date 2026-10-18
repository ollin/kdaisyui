// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/megamenu/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.span
import kotlinx.html.SPAN
import kotlinx.html.Tag

/** Size variants for this component (CSS prefix: `megamenu-`) */
enum class MegamenuSize(internal val className: String) : ClassValues<MegamenuSize> {
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
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Width variants for this component (CSS prefix: `megamenu-`) */
enum class MegamenuWidth(internal val className: String) : ClassValues<MegamenuWidth> {
    /** CSS: `megamenu-wide` — megamenu dropdown will be as wide as the megamenu container */
    Wide("megamenu-wide"),
    /** CSS: `megamenu-full` — megamenu dropdown will fill the entire width of the page */
    Full("megamenu-full"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * A megamenu is a large, horizontal menu where each item opens a popover to show a large block of navigation links. Megamenu must be used once, on top of the page. Inside each popover, you can use a daisyUI menu, or any custom content. Megamenu fits better on large screens only, and for small screens, you can hide the megamenu and show the content in a dropdown or a drawer. Renders `<div class="megamenu ..." popover>`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param width — Width variant
 * @param vertical — Hides horizontal megamenu so we can open a vertical megamenu in small screens
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyMegamenu(
    id: HtmlId? = null,
    size: ClassValues<MegamenuSize>? = null,
    width: ClassValues<MegamenuWidth>? = null,
    vertical: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        attributes["popover"] = ""
        addClassNames("megamenu")
        addClassNames(size)
        addClassNames(width)
        if (vertical) addClassNames("megamenu-vertical")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyMegamenu` writes, on the element this is called on. */
fun Tag.daisyMegamenuClasses(
    size: ClassValues<MegamenuSize>? = null,
    width: ClassValues<MegamenuWidth>? = null,
    vertical: Boolean = false,
) {
    addClassNames("megamenu")
    addClassNames(size)
    addClassNames(width)
    if (vertical) addClassNames("megamenu-vertical")
}

/** Renders `<span class="megamenu-active ...">`. */
fun FlowContent.daisyMegamenuActive(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
) {
    span {
        if (id != null) attributes["id"] = id.id
        addClassNames("megamenu-active")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyMegamenuActive` writes, on the element this is called on. */
fun Tag.daisyMegamenuActiveClasses() {
    addClassNames("megamenu-active")
}

/** Structural wrapper. Renders `<div popover>`. */
fun FlowContent.daisyMegamenuPanel(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        attributes["popover"] = ""
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
