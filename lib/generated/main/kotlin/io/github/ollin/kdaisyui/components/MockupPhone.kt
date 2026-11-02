// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/mockup-phone/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.Tag

/**
 * Phone mockup shows a mockup of an iPhone. Renders `<div class="mockup-phone ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyMockupPhone(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("mockup-phone")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyMockupPhone` writes, on the element this is called on. */
fun Tag.daisyMockupPhoneClasses() {
    addClassNames("mockup-phone")
}

/** Renders `<div class="mockup-phone-camera ...">`. */
fun FlowContent.daisyMockupPhoneCamera(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("mockup-phone-camera")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyMockupPhoneCamera` writes, on the element this is called on. */
fun Tag.daisyMockupPhoneCameraClasses() {
    addClassNames("mockup-phone-camera")
}

/** Renders `<div class="mockup-phone-display ...">`. */
fun FlowContent.daisyMockupPhoneDisplay(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("mockup-phone-display")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyMockupPhoneDisplay` writes, on the element this is called on. */
fun Tag.daisyMockupPhoneDisplayClasses() {
    addClassNames("mockup-phone-display")
}
