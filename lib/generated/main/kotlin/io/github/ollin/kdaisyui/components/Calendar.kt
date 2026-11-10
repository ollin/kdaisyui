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
 * The same classes on another element: `a { daisyCalendarClasses(…) }`; see [daisyCalendarClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyCalendar(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (CALENDAR_DATE.() -> Unit) = {},
) {
    calendarDate {
        if (id != null) attributes["id"] = id.id
        addClassNames("cally")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyCalendar` writes, on the element this is called on. */
fun Tag.daisyCalendarClasses() {
    addClassNames("cally")
}

/**
 * Renders `<div class="vc ...">`.
 * The same classes on another element: `a { daisyVcClasses(…) }`; see [daisyVcClasses].
 */
fun FlowContent.daisyVc(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("vc")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyVc` writes, on the element this is called on. */
fun Tag.daisyVcClasses() {
    addClassNames("vc")
}
