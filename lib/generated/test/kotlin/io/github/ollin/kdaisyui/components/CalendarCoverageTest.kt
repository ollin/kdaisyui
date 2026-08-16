package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CalendarCoverageTest {

    @Test
    fun calendar_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCalendar(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("cally", actualClasses, "Calendar defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("cally zz-extra", actualClasses, "Calendar all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Calendar id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Calendar attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Calendar content")
    }
}
