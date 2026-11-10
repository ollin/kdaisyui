// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/filter/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.form
import kotlinx.html.FORM
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.Tag

/**
 * Filter is a group of radio buttons. Choosing one of the options will hide the others and shows a reset button next to the chosen option. Renders `<form class="filter ...">`.
 * The same classes on another element: `a { daisyFilterClasses(…) }`; see [daisyFilterClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyFilter(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (FORM.() -> Unit) = {},
) {
    form {
        if (id != null) attributes["id"] = id.id
        addClassNames("filter")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyFilter` writes, on the element this is called on. */
fun Tag.daisyFilterClasses() {
    addClassNames("filter")
}

/**
 * Renders `<input class="filter-reset ...">`.
 * The same classes on another element: `a { daisyFilterResetClasses(…) }`; see [daisyFilterResetClasses].
 */
fun FlowContent.daisyFilterReset(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (INPUT.() -> Unit) = {},
) {
    input {
        if (id != null) attributes["id"] = id.id
        addClassNames("filter-reset")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyFilterReset` writes, on the element this is called on. */
fun Tag.daisyFilterResetClasses() {
    addClassNames("filter-reset")
}
