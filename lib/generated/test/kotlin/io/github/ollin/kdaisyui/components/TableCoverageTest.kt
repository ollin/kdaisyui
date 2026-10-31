package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TableCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun table_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                content = { },
            )
        }
        assertRendered(html, Expected("Table defaults", "table", closes = "</table></div>"))
    }

    @Test
    fun table_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                id = htmlId("x-cov-id"),
                pinCols = true,
                pinRows = true,
                zebra = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Table all flags", "table table-pin-cols table-pin-rows table-zebra zz-extra", closes = "</table></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun table_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                size = TableSize.Xs,
                content = { },
            )
        }
        assertRendered(html, Expected("Table size Xs", "table table-xs"))
    }

    @Test
    fun table_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                size = TableSize.Sm,
                content = { },
            )
        }
        assertRendered(html, Expected("Table size Sm", "table table-sm"))
    }

    @Test
    fun table_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                size = TableSize.Md,
                content = { },
            )
        }
        assertRendered(html, Expected("Table size Md", "table table-md"))
    }

    @Test
    fun table_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                size = TableSize.Lg,
                content = { },
            )
        }
        assertRendered(html, Expected("Table size Lg", "table table-lg"))
    }

    @Test
    fun table_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                size = TableSize.Xl,
                content = { },
            )
        }
        assertRendered(html, Expected("Table size Xl", "table table-xl"))
    }

    @Test
    fun tableClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTableClasses()
        }
        assertRendered(html, Expected("TableClasses defaults", "table"))
    }

    @Test
    fun tableClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTableClasses(
                pinCols = true,
                pinRows = true,
                zebra = true,
            )
        }
        val expected = Expected("TableClasses all flags", "table table-pin-cols table-pin-rows table-zebra", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun tableClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTableClasses(
                size = TableSize.Xs,
            )
        }
        assertRendered(html, Expected("TableClasses size Xs", "table table-xs"))
    }

    @Test
    fun tableClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyTableClasses(
                size = TableSize.Sm,
            )
        }
        assertRendered(html, Expected("TableClasses size Sm", "table table-sm"))
    }

    @Test
    fun tableClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyTableClasses(
                size = TableSize.Md,
            )
        }
        assertRendered(html, Expected("TableClasses size Md", "table table-md"))
    }

    @Test
    fun tableClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyTableClasses(
                size = TableSize.Lg,
            )
        }
        assertRendered(html, Expected("TableClasses size Lg", "table table-lg"))
    }

    @Test
    fun tableClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyTableClasses(
                size = TableSize.Xl,
            )
        }
        assertRendered(html, Expected("TableClasses size Xl", "table table-xl"))
    }
}
