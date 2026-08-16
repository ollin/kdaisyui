package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TimelineCoverageTest {

    @Test
    fun timeline_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("timeline", actualClasses, "Timeline defaults")
    }

    @Test
    fun timeline_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(
                id = htmlId("x-cov-id"),
                box = true,
                compact = true,
                horizontal = true,
                snapIcon = true,
                vertical = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("timeline timeline-box timeline-compact timeline-horizontal timeline-snap-icon timeline-vertical zz-extra", actualClasses, "Timeline all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Timeline id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Timeline attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Timeline content")
    }

    @Test
    fun timelineStart_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineStart(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("timeline-start", actualClasses, "TimelineStart defaults")
    }

    @Test
    fun timelineStart_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineStart(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("timeline-start zz-extra", actualClasses, "TimelineStart all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "TimelineStart id")
        assertTrue(html.contains("data-attrs=\"yes\""), "TimelineStart attrs")
        assertTrue(html.contains("data-content=\"yes\""), "TimelineStart content")
    }

    @Test
    fun timelineMiddle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineMiddle(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("timeline-middle", actualClasses, "TimelineMiddle defaults")
    }

    @Test
    fun timelineMiddle_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineMiddle(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("timeline-middle zz-extra", actualClasses, "TimelineMiddle all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "TimelineMiddle id")
        assertTrue(html.contains("data-attrs=\"yes\""), "TimelineMiddle attrs")
        assertTrue(html.contains("data-content=\"yes\""), "TimelineMiddle content")
    }

    @Test
    fun timelineEnd_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineEnd(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("timeline-end", actualClasses, "TimelineEnd defaults")
    }

    @Test
    fun timelineEnd_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineEnd(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("timeline-end zz-extra", actualClasses, "TimelineEnd all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "TimelineEnd id")
        assertTrue(html.contains("data-attrs=\"yes\""), "TimelineEnd attrs")
        assertTrue(html.contains("data-content=\"yes\""), "TimelineEnd content")
    }
}
