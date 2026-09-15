// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/validator/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.p
import kotlinx.html.P

/**
 * Validator class changes the color of form elements to error or success based on input's validation rules. Renders `<input class="validator ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 */
fun FlowContent.daisyValidator(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
) {
    input {
        if (id != null) attributes["id"] = id.id
        addClassNames("validator")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
    }
}

/** Renders `<p class="validator-hint ...">`. */
fun FlowContent.daisyValidatorHint(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (P.() -> Unit)? = null,
    content: (P.() -> Unit),
) {
    p {
        if (id != null) attributes["id"] = id.id
        addClassNames("validator-hint")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
