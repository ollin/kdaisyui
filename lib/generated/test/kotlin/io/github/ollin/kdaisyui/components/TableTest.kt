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
    fun table_with_visual_elements_daisyCheckbox() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox()
        }
        val expectedClasses = "checkbox"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Table with visual elements")
    }

    @Test
    fun table_with_visual_elements_daisyAvatar() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar() {
            }
        }
        val expectedClasses = "avatar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Table with visual elements")
    }

    @Test
    fun table_with_visual_elements_daisyMask() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Squircle)
        }
        val expectedClasses = "mask mask-squircle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Table with visual elements")
    }

    @Test
    fun table_with_visual_elements_daisyBadge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(fillStyle = BadgeFillStyle.Ghost, size = BadgeSize.Sm) {
            }
        }
        val expectedClasses = "badge badge-ghost badge-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Table with visual elements")
    }

    @Test
    fun table_with_visual_elements_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Ghost, size = ButtonSize.Xs) {
            }
        }
        val expectedClasses = "btn btn-ghost btn-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Table with visual elements")
    }

    @Test
    fun table_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(size = TableSize.Xs) {
            }
        }
        val expectedClasses = "table table-xs"
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
            daisyTable(size = TableSize.Xs, pinRows = true, pinCols = true) {
            }
        }
        val expectedClasses = "table table-pin-cols table-pin-rows table-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Table with pinned-rows and pinned-cols")
    }
}
