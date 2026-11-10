// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/list/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.li
import kotlinx.html.LI
import kotlinx.html.Tag
import kotlinx.html.ul
import kotlinx.html.UL

/**
 * List is a vertical layout to display information in rows. Renders `<ul class="list ...">`.
 * The same classes on another element: `a { daisyListClasses(…) }`; see [daisyListClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyList(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (UL.() -> Unit) = {},
) {
    ul {
        if (id != null) attributes["id"] = id.id
        addClassNames("list")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyList` writes, on the element this is called on. */
fun Tag.daisyListClasses() {
    addClassNames("list")
}

/**
 * Renders `<li class="list-row ...">`.
 * The same classes on another element: `a { daisyListRowClasses(…) }`; see [daisyListRowClasses].
 */
fun UL.daisyListRow(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (LI.() -> Unit) = {},
) {
    li {
        if (id != null) attributes["id"] = id.id
        addClassNames("list-row")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyListRow` writes, on the element this is called on. */
fun Tag.daisyListRowClasses() {
    addClassNames("list-row")
}

/** The classes `daisyListCell` writes, on the element this is called on. */
fun Tag.daisyListCellClasses(
    colGrow: Boolean = false,
    colWrap: Boolean = false,
) {
    if (colGrow) addClassNames("list-col-grow")
    if (colWrap) addClassNames("list-col-wrap")
}
