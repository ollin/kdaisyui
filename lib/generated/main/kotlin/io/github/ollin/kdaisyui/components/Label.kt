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
 * @param text — Shortcut for inline text content (mutually exclusive with [content])
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content (takes precedence over [text] if both are set)
 */
fun FlowContent.daisyLabel(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit)? = null,
) {
    span {
        if (id != null) attributes["id"] = id.id
        addClassNames("label")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
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
    attrs: (LABEL.() -> Unit)? = null,
    content: (LABEL.() -> Unit),
) {
    label {
        if (id != null) attributes["id"] = id.id
        addClassNames("floating-label")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyFloatingLabel` writes, on the element this is called on. */
fun Tag.daisyFloatingLabelClasses() {
    addClassNames("floating-label")
}
