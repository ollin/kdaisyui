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
import kotlinx.html.ul
import kotlinx.html.UL

/**
 * Breadcrumbs helps users to navigate through the website. Renders `<div class="breadcrumbs ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyBreadcrumbs(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("breadcrumbs")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Structural wrapper. Renders `<ul>`. */
fun FlowContent.daisyBreadcrumbsItems(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
) {
    ul {
        if (id != null) attributes["id"] = id.id
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Structural wrapper. Renders `<li>`. */
fun UL.daisyBreadcrumbsItem(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (LI.() -> Unit)? = null,
    content: (LI.() -> Unit),
) {
    li {
        if (id != null) attributes["id"] = id.id
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
