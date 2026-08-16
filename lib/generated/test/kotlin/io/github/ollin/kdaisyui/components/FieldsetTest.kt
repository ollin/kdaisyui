package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

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
    fun fieldset_with_background_and_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset() {
            }
        }
        val expectedClasses = "fieldset"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Fieldset with background and border")
    }

    @Test
    fun fieldset_with_multiple_inputs() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset() {
            }
        }
        val expectedClasses = "fieldset"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Fieldset with multiple inputs")
    }

    @Test
    fun fieldset_with_multiple_join_items() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset() {
            }
        }
        val expectedClasses = "fieldset"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Fieldset with multiple join items")
    }

    @Test
    fun login_form_with_fieldset() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset() {
            }
        }
        val expectedClasses = "fieldset"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Login form with fieldset")
    }
}
