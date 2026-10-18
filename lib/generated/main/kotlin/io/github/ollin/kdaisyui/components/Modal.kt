// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/modal/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.dialog
import kotlinx.html.DIALOG
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.Tag

/** VerticalPlacement variants for this component (CSS prefix: `modal-`) */
enum class ModalVerticalPlacement(internal val className: String) : ClassValues<ModalVerticalPlacement> {
    /** CSS: `modal-top` — Moves the modal to top */
    Top("modal-top"),
    /** CSS: `modal-middle` — Moves the modal to middle */
    Middle("modal-middle"),
    /** CSS: `modal-bottom` — Moves the modal to bottom */
    Bottom("modal-bottom"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** HorizontalPlacement variants for this component (CSS prefix: `modal-`) */
enum class ModalHorizontalPlacement(internal val className: String) : ClassValues<ModalHorizontalPlacement> {
    /** CSS: `modal-start` — Moves the modal to start horizontally */
    Start("modal-start"),
    /** CSS: `modal-end` — Moves the modal to end horizontally */
    End("modal-end"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Modal is used to show a dialog or a box when you click a button. Renders `<dialog class="modal ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param verticalPlacement — VerticalPlacement variant
 * @param horizontalPlacement — HorizontalPlacement variant
 * @param open — Keeps the modal open (you can add this class using JS)
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyModal(
    id: HtmlId? = null,
    verticalPlacement: ClassValues<ModalVerticalPlacement>? = null,
    horizontalPlacement: ClassValues<ModalHorizontalPlacement>? = null,
    open: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIALOG.() -> Unit)? = null,
    content: (DIALOG.() -> Unit),
) {
    dialog {
        if (id != null) attributes["id"] = id.id
        addClassNames("modal")
        addClassNames(verticalPlacement)
        addClassNames(horizontalPlacement)
        if (open) addClassNames("modal-open")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyModal` writes, on the element this is called on. */
fun Tag.daisyModalClasses(
    verticalPlacement: ClassValues<ModalVerticalPlacement>? = null,
    horizontalPlacement: ClassValues<ModalHorizontalPlacement>? = null,
    open: Boolean = false,
) {
    addClassNames("modal")
    addClassNames(verticalPlacement)
    addClassNames(horizontalPlacement)
    if (open) addClassNames("modal-open")
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

/** The classes `daisyModalBox` writes, on the element this is called on. */
fun Tag.daisyModalBoxClasses() {
    addClassNames("modal-box")
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

/** The classes `daisyModalAction` writes, on the element this is called on. */
fun Tag.daisyModalActionClasses() {
    addClassNames("modal-action")
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

/** The classes `daisyModalBackdrop` writes, on the element this is called on. */
fun Tag.daisyModalBackdropClasses() {
    addClassNames("modal-backdrop")
}

/** Renders `<input class="modal-toggle ...">`. */
fun FlowContent.daisyModalToggle(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
) {
    input {
        if (id != null) attributes["id"] = id.id
        addClassNames("modal-toggle")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
    }
}

/** The classes `daisyModalToggle` writes, on the element this is called on. */
fun Tag.daisyModalToggleClasses() {
    addClassNames("modal-toggle")
}

/** Renders `<div class="modal ..." popover>`. */
fun FlowContent.daisyModalPopover(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        attributes["popover"] = ""
        addClassNames("modal")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyModalPopover` writes, on the element this is called on. */
fun Tag.daisyModalPopoverClasses() {
    addClassNames("modal")
}
