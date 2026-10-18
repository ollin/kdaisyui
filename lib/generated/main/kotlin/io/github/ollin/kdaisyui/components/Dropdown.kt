// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/dropdown/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.details
import kotlinx.html.DETAILS
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.ul
import kotlinx.html.UL

/** Side variants for this component (CSS prefix: `dropdown-`) */
enum class DropdownSide(internal val className: String) : ClassValues<DropdownSide> {
    /** CSS: `dropdown-top` — Open from top */
    Top("dropdown-top"),
    /** CSS: `dropdown-bottom` — Open from bottom */
    Bottom("dropdown-bottom"),
    /** CSS: `dropdown-left` — Open from left */
    Left("dropdown-left"),
    /** CSS: `dropdown-right` — Open from right */
    Right("dropdown-right"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Align variants for this component (CSS prefix: `dropdown-`) */
enum class DropdownAlign(internal val className: String) : ClassValues<DropdownAlign> {
    /** CSS: `dropdown-start` — Align horizontally to start of button */
    Start("dropdown-start"),
    /** CSS: `dropdown-center` — Align horizontally to center of button */
    Center("dropdown-center"),
    /** CSS: `dropdown-end` — Align horizontally to end of button */
    End("dropdown-end"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** State variants for this component (CSS prefix: `dropdown-`) */
enum class DropdownState(internal val className: String) : ClassValues<DropdownState> {
    /** CSS: `dropdown-open` — Force open */
    Open("dropdown-open"),
    /** CSS: `dropdown-close` — Force close */
    Close("dropdown-close"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Dropdown can open a menu or any other element when the button is clicked. Renders `<details class="dropdown ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param side — Side variant
 * @param align — Align variant
 * @param state — State variant
 * @param hover — Opens on hover too
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyDropdown(
    id: HtmlId? = null,
    side: ClassValues<DropdownSide>? = null,
    align: ClassValues<DropdownAlign>? = null,
    state: ClassValues<DropdownState>? = null,
    hover: Boolean = false,
    extraClasses: String? = null,
    attrs: (DETAILS.() -> Unit)? = null,
    content: (DETAILS.() -> Unit),
) {
    details {
        if (id != null) attributes["id"] = id.id
        addClassNames("dropdown")
        addClassNames(side)
        addClassNames(align)
        addClassNames(state)
        if (hover) addClassNames("dropdown-hover")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyDropdown` writes, on the element this is called on. */
fun Tag.daisyDropdownClasses(
    side: ClassValues<DropdownSide>? = null,
    align: ClassValues<DropdownAlign>? = null,
    state: ClassValues<DropdownState>? = null,
    hover: Boolean = false,
) {
    addClassNames("dropdown")
    addClassNames(side)
    addClassNames(align)
    addClassNames(state)
    if (hover) addClassNames("dropdown-hover")
}

/** Renders `<ul class="dropdown-content ...">`. */
fun FlowContent.daisyDropdownContent(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
) {
    ul {
        if (id != null) attributes["id"] = id.id
        addClassNames("dropdown-content")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyDropdownContent` writes, on the element this is called on. */
fun Tag.daisyDropdownContentClasses() {
    addClassNames("dropdown-content")
}
