package com.github.ollin.kdaisyui.components

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
    fun list_third_column_grows() {
        val html = createHTML(prettyPrint = false).div {
            daisyList(colGrow = true) {
            }
        }
        val expectedClasses = "list list-col-grow"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for List (third column grows)")
    }

    @Test
    fun list_third_column_wraps_to_next_row() {
        val html = createHTML(prettyPrint = false).div {
            daisyList(colWrap = true) {
            }
        }
        val expectedClasses = "list list-col-wrap"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for List (third column wraps to next row)")
    }
}
