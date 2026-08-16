// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/modal/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.dialog
import kotlinx.html.DIALOG
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/**
 * Modal is used to show a dialog or a box when you click a button. Renders `<dialog class="modal ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param bottom — Moves the modal to bottom
 * @param end — Moves the modal to end horizontally
 * @param middle — Moves the modal to middle
 * @param open — Keeps the modal open (you can add this class using JS)
 * @param start — Moves the modal to start horizontally
 * @param top — Moves the modal to top
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyModal(
    id: HtmlId? = null,
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
        if (id != null) attributes["id"] = id.id
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

/** Renders `<div class="modal-box ...">`. */
fun FlowContent.daisyModalBox(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("modal-box")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="modal-action ...">`. */
fun FlowContent.daisyModalAction(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("modal-action")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="modal-backdrop ...">`. */
fun FlowContent.daisyModalBackdrop(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("modal-backdrop")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="modal-toggle ...">`. */
fun FlowContent.daisyModalToggle(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("modal-toggle")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
