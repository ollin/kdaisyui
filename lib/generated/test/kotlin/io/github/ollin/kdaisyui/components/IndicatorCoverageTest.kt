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

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
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
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "indicator zz-extra", "Indicator all flags", closes = "</div></div>")
        assertCommonFlags(html, "Indicator")
    }

    @Test
    fun indicator_horizontalPlacement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(
                horizontalPlacement = IndicatorHorizontalPlacement.Start,
                content = { },
            )
        }
        assertRendered(html, "indicator indicator-start", "Indicator horizontalPlacement Start")
    }

    @Test
    fun indicator_horizontalPlacement_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(
                horizontalPlacement = IndicatorHorizontalPlacement.Center,
                content = { },
            )
        }
        assertRendered(html, "indicator indicator-center", "Indicator horizontalPlacement Center")
    }

    @Test
    fun indicator_horizontalPlacement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(
                horizontalPlacement = IndicatorHorizontalPlacement.End,
                content = { },
            )
        }
        assertRendered(html, "indicator indicator-end", "Indicator horizontalPlacement End")
    }

    @Test
    fun indicator_verticalPlacement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(
                verticalPlacement = IndicatorVerticalPlacement.Top,
                content = { },
            )
        }
        assertRendered(html, "indicator indicator-top", "Indicator verticalPlacement Top")
    }

    @Test
    fun indicator_verticalPlacement_middle() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(
                verticalPlacement = IndicatorVerticalPlacement.Middle,
                content = { },
            )
        }
        assertRendered(html, "indicator indicator-middle", "Indicator verticalPlacement Middle")
    }

    @Test
    fun indicator_verticalPlacement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(
                verticalPlacement = IndicatorVerticalPlacement.Bottom,
                content = { },
            )
        }
        assertRendered(html, "indicator indicator-bottom", "Indicator verticalPlacement Bottom")
    }

    @Test
    fun indicatorItem_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(
                content = { },
            )
        }
        assertRendered(html, "indicator-item", "IndicatorItem defaults", closes = "</span></div>")
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
        assertRendered(html, "indicator-item zz-extra", "IndicatorItem all flags", closes = "</span></div>")
        assertCommonFlags(html, "IndicatorItem")
    }
}
