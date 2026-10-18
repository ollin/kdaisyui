package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.fieldSet
import kotlinx.html.p

class FieldsetTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun fieldset_fieldset_legend_and_label() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset() {
            }
        }
        assertDocumented(html, "fieldset", "fieldset", "Fieldset fieldset-legend and label")
    }

    @Test
    fun fieldset_fieldset_legend_and_label_daisyFieldsetLegend() {
        val html = createHTML(prettyPrint = false).fieldSet {
            daisyFieldsetLegend() {
            }
        }
        assertDocumented(html, "legend", "fieldset-legend", "Fieldset fieldset-legend and label")
    }

    @Test
    fun fieldset_fieldset_legend_and_label_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput()
        }
        assertDocumented(html, "input", "input", "Fieldset fieldset-legend and label")
    }

    @Test
    fun fieldset_fieldset_legend_and_label_daisyLabel() {
        val html = createHTML(prettyPrint = false).div {
            p { daisyLabelClasses() }
        }
        assertDocumented(html, "p", "label", "Fieldset fieldset-legend and label")
    }

    @Test
    fun fieldset_with_multiple_join_items_daisyJoin() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin() {
            }
        }
        assertDocumented(html, "div", "join", "Fieldset with multiple join items")
    }

    @Test
    fun fieldset_with_multiple_join_items_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        assertDocumented(html, "button", "btn", "Fieldset with multiple join items")
    }

    @Test
    fun login_form_with_fieldset_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Neutral) {
            }
        }
        assertDocumented(html, "button", "btn btn-neutral", "Login form with fieldset")
    }
}
