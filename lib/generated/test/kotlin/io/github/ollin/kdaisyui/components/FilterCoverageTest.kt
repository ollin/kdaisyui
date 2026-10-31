package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class FilterCoverageTest {

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
        val html = createHTML(prettyPrint = false).div {
            daisyFilter(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Filter all flags", "filter zz-extra", closes = "</form></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
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
        val html = createHTML(prettyPrint = false).div {
            daisyFilterReset(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
            )
        }
        val expected = Expected("FilterReset all flags", "filter-reset zz-extra", content = false)
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
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
