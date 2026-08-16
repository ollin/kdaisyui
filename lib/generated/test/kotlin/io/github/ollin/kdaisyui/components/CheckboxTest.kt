package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class CheckboxTest {

    @Test
    fun checkbox() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox() {
            }
        }
        val expectedClasses = "checkbox"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Checkbox")
    }

    @Test
    fun with_fieldset_and_label() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox() {
            }
        }
        val expectedClasses = "checkbox"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With fieldset and label")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox() {
            }
        }
        val expectedClasses = "checkbox"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox() {
            }
        }
        val expectedClasses = "checkbox"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Colors")
    }

    @Test
    fun disabled() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox() {
            }
        }
        val expectedClasses = "checkbox"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Disabled")
    }

    @Test
    fun indeterminate() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox() {
            }
        }
        val expectedClasses = "checkbox"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Indeterminate")
    }

    @Test
    fun checkbox_with_custom_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox() {
            }
        }
        val expectedClasses = "checkbox"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Checkbox with custom colors")
    }
}
