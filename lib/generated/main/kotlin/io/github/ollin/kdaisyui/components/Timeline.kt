// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/timeline/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.ul
import kotlinx.html.UL

/**
 * Timeline component shows a list of events in chronological order. Renders `<ul class="timeline ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param box
 * @param compact
 * @param horizontal
 * @param snapIcon
 * @param vertical
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyTimeline(
    id: HtmlId? = null,
    box: Boolean = false,
    compact: Boolean = false,
    horizontal: Boolean = false,
    snapIcon: Boolean = false,
    vertical: Boolean = false,
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
) {
    ul {
        if (id != null) attributes["id"] = id.id
        addClassNames("timeline")
        if (box) addClassNames("timeline-box")
        if (compact) addClassNames("timeline-compact")
        if (horizontal) addClassNames("timeline-horizontal")
        if (snapIcon) addClassNames("timeline-snap-icon")
        if (vertical) addClassNames("timeline-vertical")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="timeline-start ...">`. */
fun FlowContent.daisyTimelineStart(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("timeline-start")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="timeline-middle ...">`. */
fun FlowContent.daisyTimelineMiddle(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("timeline-middle")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="timeline-end ...">`. */
fun FlowContent.daisyTimelineEnd(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("timeline-end")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
