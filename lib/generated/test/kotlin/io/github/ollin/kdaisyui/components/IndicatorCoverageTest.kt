package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class IndicatorCoverageTest {

    @Test
    fun indicator_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("indicator", actualClasses, "Indicator defaults")
    }

    @Test
    fun indicator_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(
                id = htmlId("x-cov-id"),
                bottom = true,
                center = true,
                end = true,
                middle = true,
                start = true,
                top = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("indicator indicator-bottom indicator-center indicator-end indicator-middle indicator-start indicator-top zz-extra", actualClasses, "Indicator all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Indicator id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Indicator attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Indicator content")
    }

    @Test
    fun indicatorItem_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("indicator-item", actualClasses, "IndicatorItem defaults")
    }

    @Test
    fun indicatorItem_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("indicator-item zz-extra", actualClasses, "IndicatorItem all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "IndicatorItem id")
        assertTrue(html.contains("data-attrs=\"yes\""), "IndicatorItem attrs")
        assertTrue(html.contains("data-content=\"yes\""), "IndicatorItem content")
    }
}
