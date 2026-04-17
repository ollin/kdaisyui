package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class TextareaTest {

    @Test
    fun textarea() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea() {
            }
        }
        val expectedClasses = "textarea"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Textarea")
    }

    @Test
    fun ghost_no_background() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(ghost = true) {
            }
        }
        val expectedClasses = "textarea textarea-ghost"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Ghost (no background)")
    }

    @Test
    fun with_form_control_and_labels() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea() {
            }
        }
        val expectedClasses = "textarea"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With form control and labels")
    }

    @Test
    fun textarea_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea() {
            }
        }
        val expectedClasses = "textarea"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Textarea colors")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea() {
            }
        }
        val expectedClasses = "textarea"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun disabled() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea() {
            }
        }
        val expectedClasses = "textarea"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Disabled")
    }
}
