package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TimelineCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun timeline_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(
                content = { },
            )
        }
        assertRendered(html, "timeline", "Timeline defaults", closes = "</ul></div>")
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
        assertRendered(html, "timeline timeline-box timeline-compact timeline-horizontal timeline-snap-icon timeline-vertical zz-extra", "Timeline all flags", closes = "</ul></div>")
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
        assertRendered(html, "timeline-start", "TimelineStart defaults", closes = "</div></div>")
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
        assertRendered(html, "timeline-start zz-extra", "TimelineStart all flags", closes = "</div></div>")
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
        assertRendered(html, "timeline-middle", "TimelineMiddle defaults", closes = "</div></div>")
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
        assertRendered(html, "timeline-middle zz-extra", "TimelineMiddle all flags", closes = "</div></div>")
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
        assertRendered(html, "timeline-end", "TimelineEnd defaults", closes = "</div></div>")
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
        assertRendered(html, "timeline-end zz-extra", "TimelineEnd all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "TimelineEnd id")
        assertTrue(html.contains("data-attrs=\"yes\""), "TimelineEnd attrs")
        assertTrue(html.contains("data-content=\"yes\""), "TimelineEnd content")
    }
}
