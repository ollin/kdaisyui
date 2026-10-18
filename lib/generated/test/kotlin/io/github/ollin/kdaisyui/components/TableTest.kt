package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class TableTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun table() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable() {
            }
        }
        assertDocumented(html, "table", "table", "Table")
    }

    @Test
    fun zebra() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(zebra = true) {
            }
        }
        assertDocumented(html, "table", "table table-zebra", "Zebra")
    }

    @Test
    fun table_with_visual_elements_daisyCheckbox() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox()
        }
        assertDocumented(html, "input", "checkbox", "Table with visual elements")
    }

    @Test
    fun table_with_visual_elements_daisyAvatar() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar() {
            }
        }
        assertDocumented(html, "div", "avatar", "Table with visual elements")
    }

    @Test
    fun table_with_visual_elements_daisyMask() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyMaskClasses(style = MaskStyle.Squircle) }
        }
        assertDocumented(html, "div", "mask mask-squircle", "Table with visual elements")
    }

    @Test
    fun table_with_visual_elements_daisyBadge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(fillStyle = BadgeFillStyle.Ghost, size = BadgeSize.Sm) {
            }
        }
        assertDocumented(html, "span", "badge badge-ghost badge-sm", "Table with visual elements")
    }

    @Test
    fun table_with_visual_elements_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Ghost, size = ButtonSize.Xs) {
            }
        }
        assertDocumented(html, "button", "btn btn-ghost btn-xs", "Table with visual elements")
    }

    @Test
    fun table_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(size = TableSize.Xs) {
            }
        }
        assertDocumented(html, "table", "table table-xs", "Table xs")
    }

    @Test
    fun table_with_pinned_rows() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(pinRows = true) {
            }
        }
        assertDocumented(html, "table", "table table-pin-rows", "Table with pinned-rows")
    }

    @Test
    fun table_with_pinned_rows_and_pinned_cols() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(size = TableSize.Xs, pinRows = true, pinCols = true) {
            }
        }
        assertDocumented(html, "table", "table table-pin-cols table-pin-rows table-xs", "Table with pinned-rows and pinned-cols")
    }
}
