// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/timeline/+page.md
// Regenerate: cd codegen && npm run generate

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.ul
import kotlinx.html.UL

fun FlowContent.daisyTimeline(
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

fun FlowContent.daisyTimelineStart(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("timeline-start")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyTimelineMiddle(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("timeline-middle")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyTimelineEnd(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("timeline-end")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
