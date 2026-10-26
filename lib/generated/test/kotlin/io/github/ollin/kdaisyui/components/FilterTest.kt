package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.input

class FilterTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun filter_using_html_form_radio_buttons_and_reset_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyFilter() {
            }
        }
        assertDocumented(html, "form", "filter", "Filter using HTML form, radio buttons and reset button")
    }

    @Test
    fun filter_using_html_form_radio_buttons_and_reset_button_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            input { daisyButtonClasses(shape = ButtonShape.Square) }
        }
        assertDocumented(html, "input", "btn btn-square", "Filter using HTML form, radio buttons and reset button")
    }

    @Test
    fun filter_using_html_form_radio_buttons_and_reset_button_daisyButton_2() {
        val html = createHTML(prettyPrint = false).div {
            input { daisyButtonClasses() }
        }
        assertDocumented(html, "input", "btn", "Filter using HTML form, radio buttons and reset button")
    }

    @Test
    fun filter_without_html_form_daisyFilterReset() {
        val html = createHTML(prettyPrint = false).div {
            daisyFilterReset(attrs = { daisyButtonClasses() })
        }
        assertDocumented(html, "input", "btn filter-reset", "Filter without HTML form")
    }
}
