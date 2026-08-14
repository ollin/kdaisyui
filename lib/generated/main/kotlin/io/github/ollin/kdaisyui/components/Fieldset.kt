// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/fieldset/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.fieldSet
import kotlinx.html.FIELDSET
import kotlinx.html.FlowContent

/**
 * Fieldset is a container for grouping related form elements. It includes fieldset-legend as a title and label as a description. Renders `<fieldSet class="fieldset ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyFieldset(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (FIELDSET.() -> Unit)? = null,
    content: (FIELDSET.() -> Unit),
) {
    fieldSet {
        if (id != null) attributes["id"] = id.id
        addClassNames("fieldset")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="fieldset-legend ...">`. */
fun FlowContent.daisyFieldsetLegend(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("fieldset-legend")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
