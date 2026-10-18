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

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
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
                compact = true,
                snapIcon = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "timeline timeline-compact timeline-snap-icon zz-extra", "Timeline all flags", closes = "</ul></div>")
        assertCommonFlags(html, "Timeline")
    }

    @Test
    fun timeline_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(
                direction = TimelineDirection.Horizontal,
                content = { },
            )
        }
        assertRendered(html, "timeline timeline-horizontal", "Timeline direction Horizontal")
    }

    @Test
    fun timeline_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(
                direction = TimelineDirection.Vertical,
                content = { },
            )
        }
        assertRendered(html, "timeline timeline-vertical", "Timeline direction Vertical")
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
                box = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "timeline-box timeline-start zz-extra", "TimelineStart all flags", closes = "</div></div>")
        assertCommonFlags(html, "TimelineStart")
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
                box = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "timeline-box timeline-middle zz-extra", "TimelineMiddle all flags", closes = "</div></div>")
        assertCommonFlags(html, "TimelineMiddle")
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
                box = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "timeline-box timeline-end zz-extra", "TimelineEnd all flags", closes = "</div></div>")
        assertCommonFlags(html, "TimelineEnd")
    }
}
