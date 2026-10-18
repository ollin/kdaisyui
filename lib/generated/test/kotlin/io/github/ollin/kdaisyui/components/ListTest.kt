package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class ListTest {

    @Test
    fun list_second_column_grows_default() {
        val html = createHTML(prettyPrint = false).div {
            daisyList() {
            }
        }
        val expectedClasses = "list"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for List (second column grows - default)")
    }

    @Test
    fun list_second_column_grows_default_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(shape = ButtonShape.Square, style = ButtonStyle.Ghost) {
            }
        }
        val expectedClasses = "btn btn-ghost btn-square"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for List (second column grows - default)")
    }
}
