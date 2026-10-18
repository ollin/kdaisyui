package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.p
import kotlinx.html.ul

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
        assertEquals("ul", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for List (second column grows - default)")
    }

    @Test
    fun list_second_column_grows_default_daisyListRow() {
        val html = createHTML(prettyPrint = false).ul {
            daisyListRow() {
            }
        }
        val expectedClasses = "list-row"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for List (second column grows - default)")
        assertEquals("li", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for List (second column grows - default)")
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
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for List (second column grows - default)")
    }

    @Test
    fun list_third_column_grows_daisyListCell() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyListCellClasses(colGrow = true) }
        }
        val expectedClasses = "list-col-grow"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for List (third column grows)")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for List (third column grows)")
    }

    @Test
    fun list_third_column_wraps_to_next_row_daisyListCell() {
        val html = createHTML(prettyPrint = false).div {
            p { daisyListCellClasses(colWrap = true) }
        }
        val expectedClasses = "list-col-wrap"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for List (third column wraps to next row)")
        assertEquals("p", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for List (third column wraps to next row)")
    }
}
