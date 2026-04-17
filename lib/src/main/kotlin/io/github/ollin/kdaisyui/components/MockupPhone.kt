// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/mockupphone/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

fun FlowContent.daisyMockupPhone(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("mockup-phone")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyMockupPhoneCamera(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("mockup-phone-camera")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyMockupPhoneDisplay(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("mockup-phone-display")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
