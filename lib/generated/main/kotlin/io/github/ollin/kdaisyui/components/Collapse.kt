// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/collapse/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.h2
import kotlinx.html.H2

/**
 * Collapse is used for showing and hiding content. Renders `<div class="collapse ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param arrow
 * @param close
 * @param open
 * @param plus
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyCollapse(
    id: HtmlId? = null,
    arrow: Boolean = false,
    close: Boolean = false,
    open: Boolean = false,
    plus: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("collapse")
        if (arrow) addClassNames("collapse-arrow")
        if (close) addClassNames("collapse-close")
        if (open) addClassNames("collapse-open")
        if (plus) addClassNames("collapse-plus")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<h2 class="collapse-title ...">`. */
fun FlowContent.daisyCollapseTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (H2.() -> Unit)? = null,
    content: (H2.() -> Unit)? = null,
) {
    h2 {
        if (id != null) attributes["id"] = id.id
        addClassNames("collapse-title")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}

/** Renders `<div class="collapse-content ...">`. */
fun FlowContent.daisyCollapseContent(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("collapse-content")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
