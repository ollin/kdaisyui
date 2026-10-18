package io.github.ollin.kdaisyui.core

import kotlinx.html.FlowContent
import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit

/**
 * `<calendar-date>`, the cally web component that DaisyUI's `cally` class styles.
 *
 * kotlinx.html has no class for it. A `<div class="cally">` is styled as nothing, because the
 * component is what renders the calendar; DaisyUI documents the class on this element only.
 */
class CALENDAR_DATE(consumer: TagConsumer<*>) :
    HTMLTag("calendar-date", consumer, emptyMap(), null, false, false), HtmlBlockTag

/** Opens a `<calendar-date>`, as kotlinx.html's own builders open theirs. */
fun FlowContent.calendarDate(block: CALENDAR_DATE.() -> Unit) {
    CALENDAR_DATE(consumer).visit(block)
}
