package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class TimelineTest {

    @Test
    fun timeline_with_text_on_both_sides_and_icon() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(box = true) {
            }
        }
        val expectedClasses = "timeline timeline-box"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Timeline with text on both sides and icon")
    }

    @Test
    fun timeline_with_bottom_side_only() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(box = true) {
            }
        }
        val expectedClasses = "timeline timeline-box"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Timeline with bottom side only")
    }

    @Test
    fun timeline_with_top_side_only() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(box = true) {
            }
        }
        val expectedClasses = "timeline timeline-box"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Timeline with top side only")
    }

    @Test
    fun timeline_with_different_sides() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(box = true) {
            }
        }
        val expectedClasses = "timeline timeline-box"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Timeline with different sides")
    }

    @Test
    fun timeline_with_colorful_lines() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(box = true) {
            }
        }
        val expectedClasses = "timeline timeline-box"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Timeline with colorful lines")
    }

    @Test
    fun timeline_without_icons() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(box = true) {
            }
        }
        val expectedClasses = "timeline timeline-box"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Timeline without icons")
    }

    @Test
    fun vertical_timeline_with_text_on_both_sides_and_icon() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(vertical = true, box = true) {
            }
        }
        val expectedClasses = "timeline timeline-box timeline-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Vertical timeline with text on both sides and icon")
    }

    @Test
    fun vertical_timeline_with_right_side_only() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(vertical = true, box = true) {
            }
        }
        val expectedClasses = "timeline timeline-box timeline-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Vertical timeline with right side only")
    }

    @Test
    fun vertical_timeline_with_left_side_only() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(vertical = true, box = true) {
            }
        }
        val expectedClasses = "timeline timeline-box timeline-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Vertical timeline with left side only")
    }

    @Test
    fun vertical_timeline_with_different_sides() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(vertical = true, box = true) {
            }
        }
        val expectedClasses = "timeline timeline-box timeline-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Vertical timeline with different sides")
    }

    @Test
    fun vertical_timeline_with_colorful_lines() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(vertical = true, box = true) {
            }
        }
        val expectedClasses = "timeline timeline-box timeline-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Vertical timeline with colorful lines")
    }

    @Test
    fun vertical_timeline_without_icons() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(vertical = true, box = true) {
            }
        }
        val expectedClasses = "timeline timeline-box timeline-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Vertical timeline without icons")
    }

    @Test
    fun responsive_vertical_by_default_horizontal_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(vertical = true, horizontal = true, box = true) {
            }
        }
        val expectedClasses = "timeline timeline-box timeline-horizontal timeline-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive: vertical by default, horizontal on large screen")
    }

    @Test
    fun timeline_with_icon_snapped_to_the_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(snapIcon = true, compact = true, vertical = true) {
            }
        }
        val expectedClasses = "timeline timeline-compact timeline-snap-icon timeline-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Timeline with icon snapped to the start")
    }
}
