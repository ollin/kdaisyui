// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/modal/+page.md
// Regenerate: cd codegen && npm run generate

package com.github.ollin.kdaisyui.components

import com.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.dialog
import kotlinx.html.DIALOG
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

fun FlowContent.daisyModal(
    bottom: Boolean = false,
    end: Boolean = false,
    middle: Boolean = false,
    open: Boolean = false,
    start: Boolean = false,
    top: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIALOG.() -> Unit)? = null,
    content: (DIALOG.() -> Unit),
) {
    dialog {
        addClassNames("modal")
        if (bottom) addClassNames("modal-bottom")
        if (end) addClassNames("modal-end")
        if (middle) addClassNames("modal-middle")
        if (open) addClassNames("modal-open")
        if (start) addClassNames("modal-start")
        if (top) addClassNames("modal-top")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyModalBox(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("modal-box")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyModalAction(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("modal-action")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyModalBackdrop(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("modal-backdrop")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyModalToggle(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("modal-toggle")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
