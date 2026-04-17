package com.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class CalendarTest {

    @Test
    fun cally_calendar_example() {
        val html = createHTML(prettyPrint = false).div {
            daisyCalendar() {
            }
        }
        val expectedClasses = "cally"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Cally calendar example")
    }

    @Test
    fun cally_date_picker_example() {
        val html = createHTML(prettyPrint = false).div {
            daisyCalendar() {
            }
        }
        val expectedClasses = "cally"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Cally date picker example")
    }
}
