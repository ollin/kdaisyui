// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/calendar/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.CALENDAR_DATE
import io.github.ollin.kdaisyui.core.calendarDate
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.Tag

/**
 * Calendar includes styles for different calendar libraries. Renders `<calendar-date class="cally ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyCalendar(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (CALENDAR_DATE.() -> Unit)? = null,
    content: (CALENDAR_DATE.() -> Unit),
) {
    calendarDate {
        if (id != null) attributes["id"] = id.id
        addClassNames("cally")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyCalendar` writes, on the element this is called on. */
fun Tag.daisyCalendarClasses() {
    addClassNames("cally")
}

/** Renders `<div class="vc ...">`. */
fun FlowContent.daisyVc(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("vc")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyVc` writes, on the element this is called on. */
fun Tag.daisyVcClasses() {
    addClassNames("vc")
}
