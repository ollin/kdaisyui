package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TableCoverageTest {

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
    fun table_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                content = { },
            )
        }
        assertRendered(html, "table", "Table defaults", closes = "</table></div>")
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
        assertRendered(html, "table table-pin-cols table-pin-rows table-zebra zz-extra", "Table all flags", closes = "</table></div>")
        assertCommonFlags(html, "Table")
    }

    @Test
    fun table_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                size = TableSize.Xs,
                content = { },
            )
        }
        assertRendered(html, "table table-xs", "Table size Xs")
    }

    @Test
    fun table_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                size = TableSize.Sm,
                content = { },
            )
        }
        assertRendered(html, "table table-sm", "Table size Sm")
    }

    @Test
    fun table_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                size = TableSize.Md,
                content = { },
            )
        }
        assertRendered(html, "table table-md", "Table size Md")
    }

    @Test
    fun table_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                size = TableSize.Lg,
                content = { },
            )
        }
        assertRendered(html, "table table-lg", "Table size Lg")
    }

    @Test
    fun table_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                size = TableSize.Xl,
                content = { },
            )
        }
        assertRendered(html, "table table-xl", "Table size Xl")
    }

    @Test
    fun tableClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTableClasses()
        }
        assertRendered(html, "table", "TableClasses defaults")
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
        assertRendered(html, "table table-pin-cols table-pin-rows table-zebra", "TableClasses all flags")
    }

    @Test
    fun tableClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTableClasses(
                size = TableSize.Xs,
            )
        }
        assertRendered(html, "table table-xs", "TableClasses size Xs")
    }

    @Test
    fun tableClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyTableClasses(
                size = TableSize.Sm,
            )
        }
        assertRendered(html, "table table-sm", "TableClasses size Sm")
    }

    @Test
    fun tableClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyTableClasses(
                size = TableSize.Md,
            )
        }
        assertRendered(html, "table table-md", "TableClasses size Md")
    }

    @Test
    fun tableClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyTableClasses(
                size = TableSize.Lg,
            )
        }
        assertRendered(html, "table table-lg", "TableClasses size Lg")
    }

    @Test
    fun tableClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyTableClasses(
                size = TableSize.Xl,
            )
        }
        assertRendered(html, "table table-xl", "TableClasses size Xl")
    }
}
