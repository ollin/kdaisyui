// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/dropdown/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.details
import kotlinx.html.DETAILS
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/** Modifier variants for this component (CSS prefix: `dropdown-`) */
enum class DropdownModifier(internal val className: String) : ClassValues<DropdownModifier> {
    /** CSS: `dropdown-hover` — Opens on hover too */
    Hover("dropdown-hover"),
    /** CSS: `dropdown-open` — Force open */
    Open("dropdown-open"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** HorizontalPlacement variants for this component (CSS prefix: `dropdown-`) */
enum class DropdownHorizontalPlacement(internal val className: String) : ClassValues<DropdownHorizontalPlacement> {
    /** CSS: `dropdown-start` — Align horizontally to start of button */
    Start("dropdown-start"),
    /** CSS: `dropdown-center` — Align horizontally to center of button */
    Center("dropdown-center"),
    /** CSS: `dropdown-end` — Align horizontally to end of button */
    End("dropdown-end"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** VerticalPlacement variants for this component (CSS prefix: `dropdown-`) */
enum class DropdownVerticalPlacement(internal val className: String) : ClassValues<DropdownVerticalPlacement> {
    /** CSS: `dropdown-top` — Open from top */
    Top("dropdown-top"),
    /** CSS: `dropdown-bottom` — Open from bottom */
    Bottom("dropdown-bottom"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Dropdown can open a menu or any other element when the button is clicked. Renders `<details class="dropdown ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param modifier — Modifier variant
 * @param horizontalPlacement — HorizontalPlacement variant
 * @param verticalPlacement — VerticalPlacement variant
 * @param close — Force close
 * @param end
 * @param start
 * @param top
 * @param bottom
 * @param left
 * @param right
 * @param center
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyDropdown(
    id: HtmlId? = null,
    modifier: ClassValues<DropdownModifier>? = null,
    horizontalPlacement: ClassValues<DropdownHorizontalPlacement>? = null,
    verticalPlacement: ClassValues<DropdownVerticalPlacement>? = null,
    close: Boolean = false,
    end: Boolean = false,
    start: Boolean = false,
    top: Boolean = false,
    bottom: Boolean = false,
    left: Boolean = false,
    right: Boolean = false,
    center: Boolean = false,
    extraClasses: String? = null,
    attrs: (DETAILS.() -> Unit)? = null,
    content: (DETAILS.() -> Unit),
) {
    details {
        if (id != null) attributes["id"] = id.id
        addClassNames("dropdown")
        addClassNames(modifier)
        addClassNames(horizontalPlacement)
        addClassNames(verticalPlacement)
        if (close) addClassNames("dropdown-close")
        if (end) addClassNames("dropdown-end")
        if (start) addClassNames("dropdown-start")
        if (top) addClassNames("dropdown-top")
        if (bottom) addClassNames("dropdown-bottom")
        if (left) addClassNames("dropdown-left")
        if (right) addClassNames("dropdown-right")
        if (center) addClassNames("dropdown-center")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="dropdown-content ...">`. */
fun FlowContent.daisyDropdownContent(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("dropdown-content")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
