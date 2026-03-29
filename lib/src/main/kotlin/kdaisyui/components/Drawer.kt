// GENERATED — DO NOT EDIT
// Source: codegen/src/config/drawer.yml + daisyui drawer.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.id
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.InputType
import kotlinx.html.label
import kotlinx.html.LABEL

fun FlowContent.daisyDrawer(
    end: Boolean = false,
    open: Boolean = false,
    toggle: Boolean = false,
    drawerId: String = "my-drawer",
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("drawer")
        if (end) addClassNames("drawer-end")
        if (open) addClassNames("drawer-open")
        if (toggle) addClassNames("drawer-toggle")
        input {
            this.id = drawerId
            type = InputType.checkBox
            addClassNames("drawer-toggle")
        }
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyDrawerContent(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("drawer-content")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyDrawerSide(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("drawer-side")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyDrawerOverlay(
    drawerId: String = "my-drawer",
    extraClasses: String? = null,
) {
    label {
        attributes["for"] = drawerId
        addClassNames("drawer-overlay")
        addClassNames(extraClasses)
    }
}

fun FlowContent.daisyDrawerButton(
    text: String? = null,
    drawerId: String = "my-drawer",
    extraClasses: String? = null,
    attrs: (LABEL.() -> Unit)? = null,
    content: (LABEL.() -> Unit)? = null,
) {
    label {
        attributes["for"] = drawerId
        addClassNames("drawer-button")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}
