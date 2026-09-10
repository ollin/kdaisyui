package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class IndicatorCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun indicator_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(
                content = { },
            )
        }
        assertRendered(html, "indicator", "Indicator defaults", closes = "</div></div>")
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
        assertRendered(html, "indicator indicator-bottom indicator-center indicator-end indicator-middle indicator-start indicator-top zz-extra", "Indicator all flags", closes = "</div></div>")
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
        assertRendered(html, "indicator-item", "IndicatorItem defaults", closes = "</div></div>")
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
        assertRendered(html, "indicator-item zz-extra", "IndicatorItem all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "IndicatorItem id")
        assertTrue(html.contains("data-attrs=\"yes\""), "IndicatorItem attrs")
        assertTrue(html.contains("data-content=\"yes\""), "IndicatorItem content")
    }
}
