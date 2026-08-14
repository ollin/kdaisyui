package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class FilterTest {

    @Test
    fun filter_using_html_form_radio_buttons_and_reset_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyFilter() {
            }
        }
        val expectedClasses = "filter"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Filter using HTML form, radio buttons and reset button")
    }

    @Test
    fun filter_without_html_form() {
        val html = createHTML(prettyPrint = false).div {
            daisyFilter() {
            }
        }
        val expectedClasses = "filter"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Filter without HTML form")
    }

    @Test
    fun filter_using_html_form_checkboxes_and_a_reset_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyFilter() {
            }
        }
        val expectedClasses = "filter"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Filter using HTML form, checkboxes, and a reset button")
    }
}
