package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TimelineCoverageTest {

    /** What a case must render: its classes, sorted, and the tail it closes with. */
    private class Expected(val label: String, val classes: String, val closes: String = "")

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val BLOCK: Tag.() -> Unit = { attributes["data-block"] = "yes" }

    /** Renders one call and asserts the classes it writes. */
    private fun assertRenders(expected: Expected, render: FlowContent.() -> Unit) {
        assertRendered(createHTML(prettyPrint = false).div { render() }, expected)
    }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    /** Renders one call per entry of an axis and asserts the classes each must write. */
    private fun <E> assertEachEntry(label: String, classesOf: Map<E, String>, render: FlowContent.(E) -> Unit) {
        for ((entry, classes) in classesOf) {
            assertRendered(createHTML(prettyPrint = false).div { render(entry) }, Expected("$label $entry", classes))
        }
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-block=\"yes\""), "${expected.label} block")
    }

    @Test
    fun timeline_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimeline()
        }
        assertRendered(html, Expected("Timeline defaults", "timeline", closes = "</ul></div>"))
    }

    @Test
    fun timeline_all_flags() {
        val html = allFlags(Expected("Timeline all flags", "timeline timeline-compact timeline-snap-icon zz-extra", closes = "</ul></div>")) { daisyTimeline(id = ID, compact = true, snapIcon = true, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun timeline_direction() = assertEachEntry("Timeline direction", mapOf(TimelineDirection.Horizontal to "timeline timeline-horizontal", TimelineDirection.Vertical to "timeline timeline-vertical")) { daisyTimeline(direction = it) }

    @Test
    fun timelineClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineClasses()
        }
        assertRendered(html, Expected("TimelineClasses defaults", "timeline"))
    }

    @Test
    fun timelineClasses_all_flags() = assertRenders(Expected("TimelineClasses all flags", "timeline timeline-compact timeline-snap-icon")) { daisyTimelineClasses(compact = true, snapIcon = true) }

    @Test
    fun timelineClasses_direction() = assertEachEntry("TimelineClasses direction", mapOf(TimelineDirection.Horizontal to "timeline timeline-horizontal", TimelineDirection.Vertical to "timeline timeline-vertical")) { daisyTimelineClasses(direction = it) }

    @Test
    fun timelineStart_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineStart()
        }
        assertRendered(html, Expected("TimelineStart defaults", "timeline-start", closes = "</div></div>"))
    }

    @Test
    fun timelineStart_all_flags() {
        val html = allFlags(Expected("TimelineStart all flags", "timeline-box timeline-start zz-extra", closes = "</div></div>")) { daisyTimelineStart(id = ID, box = true, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun timelineStartClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineStartClasses()
        }
        assertRendered(html, Expected("TimelineStartClasses defaults", "timeline-start"))
    }

    @Test
    fun timelineStartClasses_all_flags() = assertRenders(Expected("TimelineStartClasses all flags", "timeline-box timeline-start")) { daisyTimelineStartClasses(box = true) }

    @Test
    fun timelineMiddle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineMiddle()
        }
        assertRendered(html, Expected("TimelineMiddle defaults", "timeline-middle", closes = "</div></div>"))
    }

    @Test
    fun timelineMiddle_all_flags() {
        val html = allFlags(Expected("TimelineMiddle all flags", "timeline-box timeline-middle zz-extra", closes = "</div></div>")) { daisyTimelineMiddle(id = ID, box = true, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun timelineMiddleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineMiddleClasses()
        }
        assertRendered(html, Expected("TimelineMiddleClasses defaults", "timeline-middle"))
    }

    @Test
    fun timelineMiddleClasses_all_flags() = assertRenders(Expected("TimelineMiddleClasses all flags", "timeline-box timeline-middle")) { daisyTimelineMiddleClasses(box = true) }

    @Test
    fun timelineEnd_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineEnd()
        }
        assertRendered(html, Expected("TimelineEnd defaults", "timeline-end", closes = "</div></div>"))
    }

    @Test
    fun timelineEnd_all_flags() {
        val html = allFlags(Expected("TimelineEnd all flags", "timeline-box timeline-end zz-extra", closes = "</div></div>")) { daisyTimelineEnd(id = ID, box = true, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun timelineEndClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTimelineEndClasses()
        }
        assertRendered(html, Expected("TimelineEndClasses defaults", "timeline-end"))
    }

    @Test
    fun timelineEndClasses_all_flags() = assertRenders(Expected("TimelineEndClasses all flags", "timeline-box timeline-end")) { daisyTimelineEndClasses(box = true) }
}
