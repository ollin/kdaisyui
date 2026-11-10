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
 * The same classes on another element: `a { daisyDiffClasses(…) }`; see [daisyDiffClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyDiff(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (FIGURE.() -> Unit) = {},
) {
    figure {
        if (id != null) attributes["id"] = id.id
        addClassNames("diff")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyDiff` writes, on the element this is called on. */
fun Tag.daisyDiffClasses() {
    addClassNames("diff")
}

/**
 * Renders `<div class="diff-item-1 ...">`.
 * The same classes on another element: `a { daisyDiffItem1Classes(…) }`; see [daisyDiffItem1Classes].
 */
fun FlowContent.daisyDiffItem1(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("diff-item-1")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyDiffItem1` writes, on the element this is called on. */
fun Tag.daisyDiffItem1Classes() {
    addClassNames("diff-item-1")
}

/**
 * Renders `<div class="diff-item-2 ...">`.
 * The same classes on another element: `a { daisyDiffItem2Classes(…) }`; see [daisyDiffItem2Classes].
 */
fun FlowContent.daisyDiffItem2(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("diff-item-2")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyDiffItem2` writes, on the element this is called on. */
fun Tag.daisyDiffItem2Classes() {
    addClassNames("diff-item-2")
}

/**
 * Renders `<div class="diff-resizer ...">`.
 * The same classes on another element: `a { daisyDiffResizerClasses(…) }`; see [daisyDiffResizerClasses].
 */
fun FlowContent.daisyDiffResizer(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("diff-resizer")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyDiffResizer` writes, on the element this is called on. */
fun Tag.daisyDiffResizerClasses() {
    addClassNames("diff-resizer")
}
