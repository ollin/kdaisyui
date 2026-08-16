package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class TableTest {

    @Test
    fun table() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable() {
            }
        }
        val expectedClasses = "table"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Table")
    }

    @Test
    fun table_with_border_and_background() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable() {
            }
        }
        val expectedClasses = "table"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Table with border and background")
    }

    @Test
    fun table_with_an_active_row() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable() {
            }
        }
        val expectedClasses = "table"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Table with an active row")
    }

    @Test
    fun table_with_a_row_that_highlights_on_hover() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable() {
            }
        }
        val expectedClasses = "table"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Table with a row that highlights on hover")
    }

    @Test
    fun zebra() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(zebra = true) {
            }
        }
        val expectedClasses = "table table-zebra"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Zebra")
    }

    @Test
    fun table_with_visual_elements() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable() {
            }
        }
        val expectedClasses = "table"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Table with visual elements")
    }

    @Test
    fun table_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable() {
            }
        }
        val expectedClasses = "table"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Table xs")
    }

    @Test
    fun table_with_pinned_rows() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(pinRows = true) {
            }
        }
        val expectedClasses = "table table-pin-rows"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Table with pinned-rows")
    }

    @Test
    fun table_with_pinned_rows_and_pinned_cols() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(pinRows = true, pinCols = true) {
            }
        }
        val expectedClasses = "table table-pin-cols table-pin-rows"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Table with pinned-rows and pinned-cols")
    }
}
