package io.github.ollin.kdaisyui.components

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
    fun cally_date_picker_example_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput()
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Cally date picker example")
    }

    @Test
    fun cally_date_picker_example_daisyDropdown() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown() {
            }
        }
        val expectedClasses = "dropdown"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Cally date picker example")
    }
}
