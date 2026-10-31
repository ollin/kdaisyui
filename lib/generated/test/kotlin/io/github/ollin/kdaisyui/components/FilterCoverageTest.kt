package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class FilterCoverageTest {

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

    /** Renders one call per entry of an axis and asserts the classes each must write. */
    private fun <E> assertEachEntry(label: String, classesOf: Map<E, String>, render: FlowContent.(E) -> Unit) {
        for ((entry, classes) in classesOf) {
            assertRendered(createHTML(prettyPrint = false).div { render(entry) }, Expected("$label $entry", classes))
        }
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun filter_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFilter(
                content = { },
            )
        }
        assertRendered(html, Expected("Filter defaults", "filter", closes = "</form></div>"))
    }

    @Test
    fun filter_all_flags() {
        val html = allFlags(Expected("Filter all flags", "filter zz-extra", closes = "</form></div>")) { daisyFilter(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun filterClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFilterClasses()
        }
        assertRendered(html, Expected("FilterClasses defaults", "filter"))
    }

    @Test
    fun filterClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFilterClasses()
        }
        val expected = Expected("FilterClasses all flags", "filter", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun filterReset_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFilterReset()
        }
        assertRendered(html, Expected("FilterReset defaults", "filter-reset"))
    }

    @Test
    fun filterReset_all_flags() {
        val html = allFlags(Expected("FilterReset all flags", "filter-reset zz-extra", content = false)) { daisyFilterReset(id = ID, extraClasses = EXTRA, attrs = ATTRS) }
    }

    @Test
    fun filterResetClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFilterResetClasses()
        }
        assertRendered(html, Expected("FilterResetClasses defaults", "filter-reset"))
    }

    @Test
    fun filterResetClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFilterResetClasses()
        }
        val expected = Expected("FilterResetClasses all flags", "filter-reset", content = false)
        assertRendered(html, expected)
    }
}
