package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DiffCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val ATTRS: Tag.() -> Unit = { attributes["data-attrs"] = "yes" }
    private val CONTENT: Tag.() -> Unit = { attributes["data-content"] = "yes" }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun diff_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiff(
                content = { },
            )
        }
        assertRendered(html, Expected("Diff defaults", "diff", closes = "</figure></div>"))
    }

    @Test
    fun diff_all_flags() {
        val html = allFlags(Expected("Diff all flags", "diff zz-extra", closes = "</figure></div>")) { daisyDiff(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun diffClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffClasses()
        }
        assertRendered(html, Expected("DiffClasses defaults", "diff"))
    }

    @Test
    fun diffClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffClasses()
        }
        val expected = Expected("DiffClasses all flags", "diff", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun diffItem1_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffItem1(
                content = { },
            )
        }
        assertRendered(html, Expected("DiffItem1 defaults", "diff-item-1", closes = "</div></div>"))
    }

    @Test
    fun diffItem1_all_flags() {
        val html = allFlags(Expected("DiffItem1 all flags", "diff-item-1 zz-extra", closes = "</div></div>")) { daisyDiffItem1(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun diffItem1Classes_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffItem1Classes()
        }
        assertRendered(html, Expected("DiffItem1Classes defaults", "diff-item-1"))
    }

    @Test
    fun diffItem1Classes_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffItem1Classes()
        }
        val expected = Expected("DiffItem1Classes all flags", "diff-item-1", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun diffItem2_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffItem2(
                content = { },
            )
        }
        assertRendered(html, Expected("DiffItem2 defaults", "diff-item-2", closes = "</div></div>"))
    }

    @Test
    fun diffItem2_all_flags() {
        val html = allFlags(Expected("DiffItem2 all flags", "diff-item-2 zz-extra", closes = "</div></div>")) { daisyDiffItem2(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun diffItem2Classes_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffItem2Classes()
        }
        assertRendered(html, Expected("DiffItem2Classes defaults", "diff-item-2"))
    }

    @Test
    fun diffItem2Classes_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffItem2Classes()
        }
        val expected = Expected("DiffItem2Classes all flags", "diff-item-2", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun diffResizer_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffResizer(
                content = { },
            )
        }
        assertRendered(html, Expected("DiffResizer defaults", "diff-resizer", closes = "</div></div>"))
    }

    @Test
    fun diffResizer_all_flags() {
        val html = allFlags(Expected("DiffResizer all flags", "diff-resizer zz-extra", closes = "</div></div>")) { daisyDiffResizer(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun diffResizerClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffResizerClasses()
        }
        assertRendered(html, Expected("DiffResizerClasses defaults", "diff-resizer"))
    }

    @Test
    fun diffResizerClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffResizerClasses()
        }
        val expected = Expected("DiffResizerClasses all flags", "diff-resizer", content = false)
        assertRendered(html, expected)
    }
}
