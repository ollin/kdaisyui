package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class FilterCoverageTest {

    @Test
    fun filter_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFilter(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("filter", actualClasses, "Filter defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("filter zz-extra", actualClasses, "Filter all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Filter id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Filter attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Filter content")
    }

    @Test
    fun filterReset_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFilterReset(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("filter-reset", actualClasses, "FilterReset defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("filter-reset zz-extra", actualClasses, "FilterReset all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "FilterReset id")
        assertTrue(html.contains("data-attrs=\"yes\""), "FilterReset attrs")
        assertTrue(html.contains("data-content=\"yes\""), "FilterReset content")
    }
}
