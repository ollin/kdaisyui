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
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param icon — Icon variant
 * @param state — State variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyCollapse(
    id: HtmlId? = null,
    icon: ClassValues<CollapseIcon>? = null,
    state: ClassValues<CollapseState>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("collapse")
        addClassNames(icon)
        addClassNames(state)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="collapse-title ...">`. */
fun FlowContent.daisyCollapseTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit)? = null,
) {
    div {
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
