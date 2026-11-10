// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/breadcrumbs/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.li
import kotlinx.html.LI
import kotlinx.html.Tag
import kotlinx.html.ul
import kotlinx.html.UL

/**
 * Breadcrumbs helps users to navigate through the website. Renders `<div class="breadcrumbs ...">`.
 * The same classes on another element: `a { daisyBreadcrumbsClasses(…) }`; see [daisyBreadcrumbsClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyBreadcrumbs(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("breadcrumbs")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyBreadcrumbs` writes, on the element this is called on. */
fun Tag.daisyBreadcrumbsClasses() {
    addClassNames("breadcrumbs")
}

/** Structural wrapper. Renders `<ul>`. */
fun FlowContent.daisyBreadcrumbsItems(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (UL.() -> Unit) = {},
) {
    ul {
        if (id != null) attributes["id"] = id.id
        addClassNames(extraClasses)
        block()
    }
}

/** Structural wrapper. Renders `<li>`. */
fun UL.daisyBreadcrumbsItem(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (LI.() -> Unit) = {},
) {
    li {
        if (id != null) attributes["id"] = id.id
        addClassNames(extraClasses)
        block()
    }
}
