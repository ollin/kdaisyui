package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DiffCoverageTest {

    @Test
    fun diff_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiff(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("diff", actualClasses, "Diff defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("diff zz-extra", actualClasses, "Diff all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Diff id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Diff attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Diff content")
    }

    @Test
    fun diffItem1_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffItem1(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("diff-item-1", actualClasses, "DiffItem1 defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("diff-item-1 zz-extra", actualClasses, "DiffItem1 all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "DiffItem1 id")
        assertTrue(html.contains("data-attrs=\"yes\""), "DiffItem1 attrs")
        assertTrue(html.contains("data-content=\"yes\""), "DiffItem1 content")
    }

    @Test
    fun diffItem2_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffItem2(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("diff-item-2", actualClasses, "DiffItem2 defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("diff-item-2 zz-extra", actualClasses, "DiffItem2 all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "DiffItem2 id")
        assertTrue(html.contains("data-attrs=\"yes\""), "DiffItem2 attrs")
        assertTrue(html.contains("data-content=\"yes\""), "DiffItem2 content")
    }

    @Test
    fun diffResizer_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiffResizer(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("diff-resizer", actualClasses, "DiffResizer defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("diff-resizer zz-extra", actualClasses, "DiffResizer all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "DiffResizer id")
        assertTrue(html.contains("data-attrs=\"yes\""), "DiffResizer attrs")
        assertTrue(html.contains("data-content=\"yes\""), "DiffResizer content")
    }
}
