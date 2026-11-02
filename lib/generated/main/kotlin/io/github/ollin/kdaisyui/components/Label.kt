// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/label/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.label
import kotlinx.html.LABEL
import kotlinx.html.span
import kotlinx.html.SPAN
import kotlinx.html.Tag

/**
 * Label is used to provide a name or title for an input field. Label can be placed before or after the field. Renders `<span class="label ...">`.
 * @param text — Inline text, appended after anything [block] writes
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyLabel(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (SPAN.() -> Unit) = {},
) {
    span {
        if (id != null) attributes["id"] = id.id
        addClassNames("label")
        addClassNames(extraClasses)
        block()
        if (text != null) +text
    }
}

/** The classes `daisyLabel` writes, on the element this is called on. */
fun Tag.daisyLabelClasses() {
    addClassNames("label")
}

/** For the parent of an input field (or select) and a span that floats above the input field when the field is focused Renders `<label class="floating-label ...">`. */
fun FlowContent.daisyFloatingLabel(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (LABEL.() -> Unit) = {},
) {
    label {
        if (id != null) attributes["id"] = id.id
        addClassNames("floating-label")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyFloatingLabel` writes, on the element this is called on. */
fun Tag.daisyFloatingLabelClasses() {
    addClassNames("floating-label")
}
