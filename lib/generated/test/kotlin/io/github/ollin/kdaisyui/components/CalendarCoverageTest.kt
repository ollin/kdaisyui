package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CalendarCoverageTest {

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
    fun calendar_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCalendar(
                content = { },
            )
        }
        assertRendered(html, "cally", "Calendar defaults")
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
        assertRendered(html, "cally zz-extra", "Calendar all flags")
        assertCommonFlags(html, "Calendar")
    }

    @Test
    fun calendarClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCalendarClasses()
        }
        assertRendered(html, "cally", "CalendarClasses defaults")
    }

    @Test
    fun calendarClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCalendarClasses()
        }
        assertRendered(html, "cally", "CalendarClasses all flags")
    }

    @Test
    fun vc_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyVc(
                content = { },
            )
        }
        assertRendered(html, "vc", "Vc defaults", closes = "</div></div>")
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
        assertRendered(html, "vc zz-extra", "Vc all flags", closes = "</div></div>")
        assertCommonFlags(html, "Vc")
    }

    @Test
    fun vcClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyVcClasses()
        }
        assertRendered(html, "vc", "VcClasses defaults")
    }

    @Test
    fun vcClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyVcClasses()
        }
        assertRendered(html, "vc", "VcClasses all flags")
    }
}
