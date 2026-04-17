// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/drawer/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.label
import kotlinx.html.LABEL

fun FlowContent.daisyDrawer(
    end: Boolean = false,
    open: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("drawer")
        if (end) addClassNames("drawer-end")
        if (open) addClassNames("drawer-open")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyDrawerToggle(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("drawer-toggle")
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
    extraClasses: String? = null,
    attrs: (LABEL.() -> Unit)? = null,
    content: (LABEL.() -> Unit),
) {
    label {
        addClassNames("drawer-overlay")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
