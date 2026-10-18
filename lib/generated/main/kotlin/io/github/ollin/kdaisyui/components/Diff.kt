// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/diff/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.figure
import kotlinx.html.FIGURE
import kotlinx.html.FlowContent
import kotlinx.html.Tag

/**
 * Diff component shows a side-by-side comparison of two items. Renders `<figure class="diff ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyDiff(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (FIGURE.() -> Unit)? = null,
    content: (FIGURE.() -> Unit),
) {
    figure {
        if (id != null) attributes["id"] = id.id
        addClassNames("diff")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyDiff` writes, on the element this is called on. */
fun Tag.daisyDiffClasses() {
    addClassNames("diff")
}

/** Renders `<div class="diff-item-1 ...">`. */
fun FlowContent.daisyDiffItem1(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("diff-item-1")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyDiffItem1` writes, on the element this is called on. */
fun Tag.daisyDiffItem1Classes() {
    addClassNames("diff-item-1")
}

/** Renders `<div class="diff-item-2 ...">`. */
fun FlowContent.daisyDiffItem2(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("diff-item-2")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyDiffItem2` writes, on the element this is called on. */
fun Tag.daisyDiffItem2Classes() {
    addClassNames("diff-item-2")
}

/** Renders `<div class="diff-resizer ...">`. */
fun FlowContent.daisyDiffResizer(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("diff-resizer")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyDiffResizer` writes, on the element this is called on. */
fun Tag.daisyDiffResizerClasses() {
    addClassNames("diff-resizer")
}
