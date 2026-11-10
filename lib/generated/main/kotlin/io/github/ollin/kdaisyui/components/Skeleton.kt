// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/skeleton/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.span
import kotlinx.html.SPAN
import kotlinx.html.Tag

/**
 * Skeleton is a component that can be used to show a loading state of a component. Renders `<div class="skeleton ...">`.
 * The same classes on another element: `a { daisySkeletonClasses(…) }`; see [daisySkeletonClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisySkeleton(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("skeleton")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisySkeleton` writes, on the element this is called on. */
fun Tag.daisySkeletonClasses() {
    addClassNames("skeleton")
}

/**
 * Renders `<span class="skeleton skeleton-text ...">`.
 * The same classes on another element: `a { daisySkeletonTextClasses(…) }`; see [daisySkeletonTextClasses].
 */
fun FlowContent.daisySkeletonText(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (SPAN.() -> Unit) = {},
) {
    span {
        if (id != null) attributes["id"] = id.id
        addClassNames("skeleton")
        addClassNames("skeleton-text")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisySkeletonText` writes, on the element this is called on. */
fun Tag.daisySkeletonTextClasses() {
    addClassNames("skeleton")
    addClassNames("skeleton-text")
}
