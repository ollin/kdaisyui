package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.p
import kotlinx.html.ul

class ListTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun list_second_column_grows_default() {
        val html = createHTML(prettyPrint = false).div {
            daisyList() {
            }
        }
        assertDocumented(html, "ul", "list", "List (second column grows - default)")
    }

    @Test
    fun list_second_column_grows_default_daisyListRow() {
        val html = createHTML(prettyPrint = false).ul {
            daisyListRow() {
            }
        }
        assertDocumented(html, "li", "list-row", "List (second column grows - default)")
    }

    @Test
    fun list_second_column_grows_default_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(shape = ButtonShape.Square, style = ButtonStyle.Ghost) {
            }
        }
        assertDocumented(html, "button", "btn btn-ghost btn-square", "List (second column grows - default)")
    }

    @Test
    fun list_third_column_grows_daisyListCell() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyListCellClasses(colGrow = true) }
        }
        assertDocumented(html, "div", "list-col-grow", "List (third column grows)")
    }

    @Test
    fun list_third_column_wraps_to_next_row_daisyListCell() {
        val html = createHTML(prettyPrint = false).div {
            p { daisyListCellClasses(colWrap = true) }
        }
        assertDocumented(html, "p", "list-col-wrap", "List (third column wraps to next row)")
    }
}
