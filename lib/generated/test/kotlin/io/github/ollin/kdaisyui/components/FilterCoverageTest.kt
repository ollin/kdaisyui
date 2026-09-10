package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class FilterCoverageTest {

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
    fun filter_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFilter(
                content = { },
            )
        }
        assertRendered(html, "filter", "Filter defaults", closes = "</form></div>")
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
        assertRendered(html, "filter zz-extra", "Filter all flags", closes = "</form></div>")
        assertCommonFlags(html, "Filter")
    }

    @Test
    fun filterReset_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFilterReset(
                content = { },
            )
        }
        assertRendered(html, "filter-reset", "FilterReset defaults", closes = "</div></div>")
    }

    @Test
    fun filterReset_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFilterReset(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "filter-reset zz-extra", "FilterReset all flags", closes = "</div></div>")
        assertCommonFlags(html, "FilterReset")
    }
}
