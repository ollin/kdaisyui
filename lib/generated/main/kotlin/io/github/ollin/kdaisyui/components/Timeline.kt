// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/timeline/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.ul
import kotlinx.html.UL

enum class TimelineDirection(internal val className: String) : ClassValues<TimelineDirection> {
    /** CSS: `timeline-horizontal` */
    Horizontal("timeline-horizontal"),
    /** CSS: `timeline-vertical` */
    Vertical("timeline-vertical"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Timeline component shows a list of events in chronological order. Renders `<ul class="timeline ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param direction — Direction variant
 * @param compact
 * @param snapIcon
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyTimeline(
    id: HtmlId? = null,
    direction: ClassValues<TimelineDirection>? = null,
    compact: Boolean = false,
    snapIcon: Boolean = false,
    extraClasses: String? = null,
    block: (UL.() -> Unit) = {},
) {
    ul {
        if (id != null) attributes["id"] = id.id
        addClassNames("timeline")
        addClassNames(direction)
        if (compact) addClassNames("timeline-compact")
        if (snapIcon) addClassNames("timeline-snap-icon")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyTimeline` writes, on the element this is called on. */
fun Tag.daisyTimelineClasses(
    direction: ClassValues<TimelineDirection>? = null,
    compact: Boolean = false,
    snapIcon: Boolean = false,
) {
    addClassNames("timeline")
    addClassNames(direction)
    if (compact) addClassNames("timeline-compact")
    if (snapIcon) addClassNames("timeline-snap-icon")
}

/** Renders `<div class="timeline-start ...">`. */
fun FlowContent.daisyTimelineStart(
    id: HtmlId? = null,
    box: Boolean = false,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("timeline-start")
        if (box) addClassNames("timeline-box")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyTimelineStart` writes, on the element this is called on. */
fun Tag.daisyTimelineStartClasses(
    box: Boolean = false,
) {
    addClassNames("timeline-start")
    if (box) addClassNames("timeline-box")
}

/** Renders `<div class="timeline-middle ...">`. */
fun FlowContent.daisyTimelineMiddle(
    id: HtmlId? = null,
    box: Boolean = false,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("timeline-middle")
        if (box) addClassNames("timeline-box")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyTimelineMiddle` writes, on the element this is called on. */
fun Tag.daisyTimelineMiddleClasses(
    box: Boolean = false,
) {
    addClassNames("timeline-middle")
    if (box) addClassNames("timeline-box")
}

/** Renders `<div class="timeline-end ...">`. */
fun FlowContent.daisyTimelineEnd(
    id: HtmlId? = null,
    box: Boolean = false,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("timeline-end")
        if (box) addClassNames("timeline-box")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyTimelineEnd` writes, on the element this is called on. */
fun Tag.daisyTimelineEndClasses(
    box: Boolean = false,
) {
    addClassNames("timeline-end")
    if (box) addClassNames("timeline-box")
}
