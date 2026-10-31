package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CalendarCoverageTest {

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
    fun calendar_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCalendar(
                content = { },
            )
        }
        assertRendered(html, Expected("Calendar defaults", "cally"))
    }

    @Test
    fun calendar_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCalendar(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Calendar all flags", "cally zz-extra")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun calendarClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCalendarClasses()
        }
        assertRendered(html, Expected("CalendarClasses defaults", "cally"))
    }

    @Test
    fun calendarClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCalendarClasses()
        }
        val expected = Expected("CalendarClasses all flags", "cally", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun vc_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyVc(
                content = { },
            )
        }
        assertRendered(html, Expected("Vc defaults", "vc", closes = "</div></div>"))
    }

    @Test
    fun vc_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyVc(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Vc all flags", "vc zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun vcClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyVcClasses()
        }
        assertRendered(html, Expected("VcClasses defaults", "vc"))
    }

    @Test
    fun vcClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyVcClasses()
        }
        val expected = Expected("VcClasses all flags", "vc", content = false)
        assertRendered(html, expected)
    }
}
