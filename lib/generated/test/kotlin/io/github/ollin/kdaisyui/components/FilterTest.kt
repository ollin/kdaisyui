package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.input

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
        assertEquals("form", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Filter using HTML form, radio buttons and reset button")
    }

    @Test
    fun filter_using_html_form_radio_buttons_and_reset_button_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            input { daisyButtonClasses(shape = ButtonShape.Square) }
        }
        val expectedClasses = "btn btn-square"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Filter using HTML form, radio buttons and reset button")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Filter using HTML form, radio buttons and reset button")
    }

    @Test
    fun filter_using_html_form_radio_buttons_and_reset_button_daisyButton_2() {
        val html = createHTML(prettyPrint = false).div {
            input { daisyButtonClasses() }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Filter using HTML form, radio buttons and reset button")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Filter using HTML form, radio buttons and reset button")
    }

    @Test
    fun filter_without_html_form_daisyFilterReset() {
        val html = createHTML(prettyPrint = false).div {
            daisyFilterReset(attrs = { daisyButtonClasses() })
        }
        val expectedClasses = "btn filter-reset"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Filter without HTML form")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Filter without HTML form")
    }
}
