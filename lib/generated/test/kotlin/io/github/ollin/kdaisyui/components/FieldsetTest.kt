package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.fieldSet

class FieldsetTest {

    @Test
    fun fieldset_fieldset_legend_and_label() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset() {
            }
        }
        val expectedClasses = "fieldset"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Fieldset fieldset-legend and label")
    }

    @Test
    fun fieldset_fieldset_legend_and_label_daisyFieldsetLegend() {
        val html = createHTML(prettyPrint = false).fieldSet {
            daisyFieldsetLegend() {
            }
        }
        val expectedClasses = "fieldset-legend"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Fieldset fieldset-legend and label")
    }

    @Test
    fun fieldset_fieldset_legend_and_label_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput()
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Fieldset fieldset-legend and label")
    }

    @Test
    fun fieldset_fieldset_legend_and_label_daisyLabel() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabel() {
            }
        }
        val expectedClasses = "label"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Fieldset fieldset-legend and label")
    }

    @Test
    fun fieldset_with_multiple_join_items_daisyJoin() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin() {
            }
        }
        val expectedClasses = "join"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Fieldset with multiple join items")
    }

    @Test
    fun fieldset_with_multiple_join_items_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Fieldset with multiple join items")
    }

    @Test
    fun login_form_with_fieldset_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Neutral) {
            }
        }
        val expectedClasses = "btn btn-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Login form with fieldset")
    }
}
