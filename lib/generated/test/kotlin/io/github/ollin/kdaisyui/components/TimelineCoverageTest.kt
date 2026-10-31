package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TimelineCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun timeline_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(
                content = { },
            )
        }
        assertRendered(html, Expected("Timeline defaults", "timeline", closes = "</ul></div>"))
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
        val expected = Expected("Timeline all flags", "timeline timeline-compact timeline-snap-icon zz-extra", closes = "</ul></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun timeline_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(
                direction = TimelineDirection.Horizontal,
                content = { },
            )
        }
        assertRendered(html, Expected("Timeline direction Horizontal", "timeline timeline-horizontal"))
    }

    @Test
    fun timeline_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline(
                direction = TimelineDirection.Vertical,
                content = { },
            )
        }
        assertRendered(html, Expected("Timeline direction Vertical", "timeline timeline-vertical"))
    }

    @Test
    fun timelineClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineClasses()
        }
        assertRendered(html, Expected("TimelineClasses defaults", "timeline"))
    }

    @Test
    fun timelineClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineClasses(
                compact = true,
                snapIcon = true,
            )
        }
        val expected = Expected("TimelineClasses all flags", "timeline timeline-compact timeline-snap-icon", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun timelineClasses_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineClasses(
                direction = TimelineDirection.Horizontal,
            )
        }
        assertRendered(html, Expected("TimelineClasses direction Horizontal", "timeline timeline-horizontal"))
    }

    @Test
    fun timelineClasses_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineClasses(
                direction = TimelineDirection.Vertical,
            )
        }
        assertRendered(html, Expected("TimelineClasses direction Vertical", "timeline timeline-vertical"))
    }

    @Test
    fun timelineStart_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineStart(
                content = { },
            )
        }
        assertRendered(html, Expected("TimelineStart defaults", "timeline-start", closes = "</div></div>"))
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
        val expected = Expected("TimelineStart all flags", "timeline-box timeline-start zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun timelineStartClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineStartClasses()
        }
        assertRendered(html, Expected("TimelineStartClasses defaults", "timeline-start"))
    }

    @Test
    fun timelineStartClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineStartClasses(
                box = true,
            )
        }
        val expected = Expected("TimelineStartClasses all flags", "timeline-box timeline-start", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun timelineMiddle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineMiddle(
                content = { },
            )
        }
        assertRendered(html, Expected("TimelineMiddle defaults", "timeline-middle", closes = "</div></div>"))
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
        val expected = Expected("TimelineMiddle all flags", "timeline-box timeline-middle zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun timelineMiddleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineMiddleClasses()
        }
        assertRendered(html, Expected("TimelineMiddleClasses defaults", "timeline-middle"))
    }

    @Test
    fun timelineMiddleClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineMiddleClasses(
                box = true,
            )
        }
        val expected = Expected("TimelineMiddleClasses all flags", "timeline-box timeline-middle", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun timelineEnd_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineEnd(
                content = { },
            )
        }
        assertRendered(html, Expected("TimelineEnd defaults", "timeline-end", closes = "</div></div>"))
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
        val expected = Expected("TimelineEnd all flags", "timeline-box timeline-end zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun timelineEndClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineEndClasses()
        }
        assertRendered(html, Expected("TimelineEndClasses defaults", "timeline-end"))
    }

    @Test
    fun timelineEndClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineEndClasses(
                box = true,
            )
        }
        val expected = Expected("TimelineEndClasses all flags", "timeline-box timeline-end", content = false)
        assertRendered(html, expected)
    }
}
