package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class DiffTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun diff() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiff() {
            }
        }
        assertDocumented(html, "figure", "diff", "Diff")
    }

    @Test
    fun diff_daisyDiffItem1() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffItem1() {
            }
        }
        assertDocumented(html, "div", "diff-item-1", "Diff")
    }

    @Test
    fun diff_daisyDiffItem2() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffItem2() {
            }
        }
        assertDocumented(html, "div", "diff-item-2", "Diff")
    }

    @Test
    fun diff_daisyDiffResizer() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffResizer() {
            }
        }
        assertDocumented(html, "div", "diff-resizer", "Diff")
    }
}
