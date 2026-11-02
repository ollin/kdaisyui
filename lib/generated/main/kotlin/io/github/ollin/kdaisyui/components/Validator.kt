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
import kotlinx.html.Tag

/**
 * Validator class changes the color of form elements to error or success based on input's validation rules. Renders `<input class="validator ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyValidator(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (INPUT.() -> Unit) = {},
) {
    input {
        if (id != null) attributes["id"] = id.id
        addClassNames("validator")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyValidator` writes, on the element this is called on. */
fun Tag.daisyValidatorClasses() {
    addClassNames("validator")
}

/** Renders `<p class="validator-hint ...">`. */
fun FlowContent.daisyValidatorHint(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (P.() -> Unit) = {},
) {
    p {
        if (id != null) attributes["id"] = id.id
        addClassNames("validator-hint")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyValidatorHint` writes, on the element this is called on. */
fun Tag.daisyValidatorHintClasses() {
    addClassNames("validator-hint")
}
