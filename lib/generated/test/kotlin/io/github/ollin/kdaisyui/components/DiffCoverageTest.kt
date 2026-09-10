package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DiffCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
    }

    @Test
    fun diff_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiff(
                content = { },
            )
        }
        assertRendered(html, "diff", "Diff defaults", closes = "</figure></div>")
    }

    @Test
    fun diff_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiff(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "diff zz-extra", "Diff all flags", closes = "</figure></div>")
        assertCommonFlags(html, "Diff")
    }

    @Test
    fun diffItem1_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffItem1(
                content = { },
            )
        }
        assertRendered(html, "diff-item-1", "DiffItem1 defaults", closes = "</div></div>")
    }

    @Test
    fun diffItem1_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffItem1(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "diff-item-1 zz-extra", "DiffItem1 all flags", closes = "</div></div>")
        assertCommonFlags(html, "DiffItem1")
    }

    @Test
    fun diffItem2_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffItem2(
                content = { },
            )
        }
        assertRendered(html, "diff-item-2", "DiffItem2 defaults", closes = "</div></div>")
    }

    @Test
    fun diffItem2_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffItem2(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "diff-item-2 zz-extra", "DiffItem2 all flags", closes = "</div></div>")
        assertCommonFlags(html, "DiffItem2")
    }

    @Test
    fun diffResizer_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffResizer(
                content = { },
            )
        }
        assertRendered(html, "diff-resizer", "DiffResizer defaults", closes = "</div></div>")
    }

    @Test
    fun diffResizer_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffResizer(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "diff-resizer zz-extra", "DiffResizer all flags", closes = "</div></div>")
        assertCommonFlags(html, "DiffResizer")
    }
}
