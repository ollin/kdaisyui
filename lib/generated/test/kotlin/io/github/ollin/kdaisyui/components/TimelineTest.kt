package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class TimelineTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun timeline_with_text_on_both_sides_and_icon() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline() {
            }
        }
        assertDocumented(html, "ul", "timeline", "Timeline with text on both sides and icon")
    }

    @Test
    fun timeline_with_text_on_both_sides_and_icon_daisyTimelineStart() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineStart() {
            }
        }
        assertDocumented(html, "div", "timeline-start", "Timeline with text on both sides and icon")
    }

    @Test
    fun timeline_with_text_on_both_sides_and_icon_daisyTimelineMiddle() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineMiddle() {
            }
        }
        assertDocumented(html, "div", "timeline-middle", "Timeline with text on both sides and icon")
    }

    @Test
    fun timeline_with_text_on_both_sides_and_icon_daisyTimelineEnd() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineEnd(box = true) {
            }
        }
        assertDocumented(html, "div", "timeline-box timeline-end", "Timeline with text on both sides and icon")
    }

    @Test
    fun timeline_with_top_side_only_daisyTimelineStart() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineStart(box = true) {
            }
        }
        assertDocumented(html, "div", "timeline-box timeline-start", "Timeline with top side only")
    }

    @Test
    fun vertical_timeline_with_text_on_both_sides_and_icon() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(direction = TimelineDirection.Vertical) {
            }
        }
        assertDocumented(html, "ul", "timeline timeline-vertical", "Vertical timeline with text on both sides and icon")
    }

    @Test
    fun responsive_vertical_by_default_horizontal_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(direction = TimelineDirection.Vertical, extraClasses = "lg:timeline-horizontal") {
            }
        }
        assertDocumented(html, "ul", "lg:timeline-horizontal timeline timeline-vertical", "Responsive: vertical by default, horizontal on large screen")
    }

    @Test
    fun timeline_with_icon_snapped_to_the_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(snapIcon = true, direction = TimelineDirection.Vertical, extraClasses = "max-md:timeline-compact") {
            }
        }
        assertDocumented(html, "ul", "max-md:timeline-compact timeline timeline-snap-icon timeline-vertical", "Timeline with icon snapped to the start")
    }

    @Test
    fun timeline_with_icon_snapped_to_the_start_daisyTimelineEnd() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineEnd() {
            }
        }
        assertDocumented(html, "div", "timeline-end", "Timeline with icon snapped to the start")
    }
}
