package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TableCoverageTest {

    @Test
    fun table_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("table", actualClasses, "Table defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("table table-pin-cols table-pin-rows table-zebra zz-extra", actualClasses, "Table all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Table id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Table attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Table content")
    }

    @Test
    fun table_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                size = TableSize.Xs,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("table table-xs", actualClasses, "Table size Xs")
    }

    @Test
    fun table_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                size = TableSize.Sm,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("table table-sm", actualClasses, "Table size Sm")
    }

    @Test
    fun table_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                size = TableSize.Md,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("table table-md", actualClasses, "Table size Md")
    }

    @Test
    fun table_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                size = TableSize.Lg,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("table table-lg", actualClasses, "Table size Lg")
    }

    @Test
    fun table_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyTable(
                size = TableSize.Xl,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("table table-xl", actualClasses, "Table size Xl")
    }
}
