// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/filter/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.form
import kotlinx.html.FORM

/**
 * Filter is a group of radio buttons. Choosing one of the options will hide the others and shows a reset button next to the chosen option. Renders `<form class="filter ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyFilter(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (FORM.() -> Unit)? = null,
    content: (FORM.() -> Unit),
) {
    form {
        if (id != null) attributes["id"] = id.id
        addClassNames("filter")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="filter-reset ...">`. */
fun FlowContent.daisyFilterReset(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("filter-reset")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
