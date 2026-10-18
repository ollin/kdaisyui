package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class TimelineTest {

    @Test
    fun timeline_with_text_on_both_sides_and_icon() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline() {
            }
        }
        val expectedClasses = "timeline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Timeline with text on both sides and icon")
    }

    @Test
    fun timeline_with_text_on_both_sides_and_icon_daisyTimelineStart() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineStart() {
            }
        }
        val expectedClasses = "timeline-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Timeline with text on both sides and icon")
    }

    @Test
    fun timeline_with_text_on_both_sides_and_icon_daisyTimelineMiddle() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineMiddle() {
            }
        }
        val expectedClasses = "timeline-middle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Timeline with text on both sides and icon")
    }

    @Test
    fun timeline_with_text_on_both_sides_and_icon_daisyTimelineEnd() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineEnd(box = true) {
            }
        }
        val expectedClasses = "timeline-box timeline-end"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Timeline with text on both sides and icon")
    }

    @Test
    fun timeline_with_top_side_only_daisyTimelineStart() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineStart(box = true) {
            }
        }
        val expectedClasses = "timeline-box timeline-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Timeline with top side only")
    }

    @Test
    fun vertical_timeline_with_text_on_both_sides_and_icon() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(direction = TimelineDirection.Vertical) {
            }
        }
        val expectedClasses = "timeline timeline-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Vertical timeline with text on both sides and icon")
    }

    @Test
    fun responsive_vertical_by_default_horizontal_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(direction = TimelineDirection.Vertical, extraClasses = "lg:timeline-horizontal") {
            }
        }
        val expectedClasses = "lg:timeline-horizontal timeline timeline-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive: vertical by default, horizontal on large screen")
    }

    @Test
    fun timeline_with_icon_snapped_to_the_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(snapIcon = true, direction = TimelineDirection.Vertical, extraClasses = "max-md:timeline-compact") {
            }
        }
        val expectedClasses = "max-md:timeline-compact timeline timeline-snap-icon timeline-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Timeline with icon snapped to the start")
    }

    @Test
    fun timeline_with_icon_snapped_to_the_start_daisyTimelineEnd() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineEnd() {
            }
        }
        val expectedClasses = "timeline-end"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Timeline with icon snapped to the start")
    }
}
