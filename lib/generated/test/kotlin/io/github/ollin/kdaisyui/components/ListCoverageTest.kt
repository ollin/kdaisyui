package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlinx.html.ul

class ListCoverageTest {

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
    fun list_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyList(
                content = { },
            )
        }
        assertRendered(html, Expected("List defaults", "list", closes = "</ul></div>"))
    }

    @Test
    fun list_all_flags() {
        val html = allFlags(Expected("List all flags", "list zz-extra", closes = "</ul></div>")) { daisyList(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun listClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyListClasses()
        }
        assertRendered(html, Expected("ListClasses defaults", "list"))
    }

    @Test
    fun listClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyListClasses()
        }
        val expected = Expected("ListClasses all flags", "list", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun listRow_defaults() {
        val html = createHTML(prettyPrint = false).ul {
            daisyListRow(
                content = { },
            )
        }
        assertRendered(html, Expected("ListRow defaults", "list-row", closes = "</li></ul>"))
    }

    @Test
    fun listRow_all_flags() {
        val html = createHTML(prettyPrint = false).ul {
            daisyListRow(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("ListRow all flags", "list-row zz-extra", closes = "</li></ul>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun listRowClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyListRowClasses()
        }
        assertRendered(html, Expected("ListRowClasses defaults", "list-row"))
    }

    @Test
    fun listRowClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyListRowClasses()
        }
        val expected = Expected("ListRowClasses all flags", "list-row", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun listCellClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyListCellClasses()
        }
        assertTrue(!html.contains("class=\""), "ListCellClasses defaults emits no class")
    }

    @Test
    fun listCellClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyListCellClasses(
                colGrow = true,
                colWrap = true,
            )
        }
        val expected = Expected("ListCellClasses all flags", "list-col-grow list-col-wrap", content = false)
        assertRendered(html, expected)
    }
}
