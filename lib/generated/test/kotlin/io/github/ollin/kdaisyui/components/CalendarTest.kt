package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.button

class CalendarTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun cally_calendar_example() {
        val html = createHTML(prettyPrint = false).div {
            daisyCalendar() {
            }
        }
        assertDocumented(html, "calendar-date", "cally", "Cally calendar example")
    }

    @Test
    fun cally_date_picker_example_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            button { daisyInputClasses() }
        }
        assertDocumented(html, "button", "input", "Cally date picker example")
    }

    @Test
    fun cally_date_picker_example_daisyDropdown() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses() }
        }
        assertDocumented(html, "div", "dropdown", "Cally date picker example")
    }
}
