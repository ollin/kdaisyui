// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/collapse/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.Tag

enum class CollapseIcon(internal val className: String) : ClassValues<CollapseIcon> {
    /** CSS: `collapse-arrow` */
    Arrow("collapse-arrow"),
    /** CSS: `collapse-plus` */
    Plus("collapse-plus"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

enum class CollapseState(internal val className: String) : ClassValues<CollapseState> {
    /** CSS: `collapse-open` */
    Open("collapse-open"),
    /** CSS: `collapse-close` */
    Close("collapse-close"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Collapse is used for showing and hiding content. Renders `<div class="collapse ...">`.
 * The same classes on another element: `a { daisyCollapseClasses(…) }`; see [daisyCollapseClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param icon — Icon variant
 * @param state — State variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyCollapse(
    id: HtmlId? = null,
    icon: ClassValues<CollapseIcon>? = null,
    state: ClassValues<CollapseState>? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("collapse")
        addClassNames(icon)
        addClassNames(state)
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyCollapse` writes, on the element this is called on. */
fun Tag.daisyCollapseClasses(
    icon: ClassValues<CollapseIcon>? = null,
    state: ClassValues<CollapseState>? = null,
) {
    addClassNames("collapse")
    addClassNames(icon)
    addClassNames(state)
}

/**
 * Renders `<div class="collapse-title ...">`.
 * The same classes on another element: `a { daisyCollapseTitleClasses(…) }`; see [daisyCollapseTitleClasses].
 */
fun FlowContent.daisyCollapseTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("collapse-title")
        addClassNames(extraClasses)
        block()
        if (text != null) +text
    }
}

/** The classes `daisyCollapseTitle` writes, on the element this is called on. */
fun Tag.daisyCollapseTitleClasses() {
    addClassNames("collapse-title")
}

/**
 * Renders `<div class="collapse-content ...">`.
 * The same classes on another element: `a { daisyCollapseContentClasses(…) }`; see [daisyCollapseContentClasses].
 */
fun FlowContent.daisyCollapseContent(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("collapse-content")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyCollapseContent` writes, on the element this is called on. */
fun Tag.daisyCollapseContentClasses() {
    addClassNames("collapse-content")
}
