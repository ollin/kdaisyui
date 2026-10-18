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
    fun indicatorClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorClasses()
        }
        assertRendered(html, "indicator", "IndicatorClasses defaults")
    }

    @Test
    fun indicatorClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorClasses()
        }
        assertRendered(html, "indicator", "IndicatorClasses all flags")
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

    @Test
    fun indicatorItem_horizontalPlacement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(
                horizontalPlacement = IndicatorItemHorizontalPlacement.Start,
                content = { },
            )
        }
        assertRendered(html, "indicator-item indicator-start", "IndicatorItem horizontalPlacement Start")
    }

    @Test
    fun indicatorItem_horizontalPlacement_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(
                horizontalPlacement = IndicatorItemHorizontalPlacement.Center,
                content = { },
            )
        }
        assertRendered(html, "indicator-center indicator-item", "IndicatorItem horizontalPlacement Center")
    }

    @Test
    fun indicatorItem_horizontalPlacement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(
                horizontalPlacement = IndicatorItemHorizontalPlacement.End,
                content = { },
            )
        }
        assertRendered(html, "indicator-end indicator-item", "IndicatorItem horizontalPlacement End")
    }

    @Test
    fun indicatorItem_verticalPlacement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(
                verticalPlacement = IndicatorItemVerticalPlacement.Top,
                content = { },
            )
        }
        assertRendered(html, "indicator-item indicator-top", "IndicatorItem verticalPlacement Top")
    }

    @Test
    fun indicatorItem_verticalPlacement_middle() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(
                verticalPlacement = IndicatorItemVerticalPlacement.Middle,
                content = { },
            )
        }
        assertRendered(html, "indicator-item indicator-middle", "IndicatorItem verticalPlacement Middle")
    }

    @Test
    fun indicatorItem_verticalPlacement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(
                verticalPlacement = IndicatorItemVerticalPlacement.Bottom,
                content = { },
            )
        }
        assertRendered(html, "indicator-bottom indicator-item", "IndicatorItem verticalPlacement Bottom")
    }

    @Test
    fun indicatorItemClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItemClasses()
        }
        assertRendered(html, "indicator-item", "IndicatorItemClasses defaults")
    }

    @Test
    fun indicatorItemClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItemClasses()
        }
        assertRendered(html, "indicator-item", "IndicatorItemClasses all flags")
    }

    @Test
    fun indicatorItemClasses_horizontalPlacement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItemClasses(
                horizontalPlacement = IndicatorItemHorizontalPlacement.Start,
            )
        }
        assertRendered(html, "indicator-item indicator-start", "IndicatorItemClasses horizontalPlacement Start")
    }

    @Test
    fun indicatorItemClasses_horizontalPlacement_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItemClasses(
                horizontalPlacement = IndicatorItemHorizontalPlacement.Center,
            )
        }
        assertRendered(html, "indicator-center indicator-item", "IndicatorItemClasses horizontalPlacement Center")
    }

    @Test
    fun indicatorItemClasses_horizontalPlacement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItemClasses(
                horizontalPlacement = IndicatorItemHorizontalPlacement.End,
            )
        }
        assertRendered(html, "indicator-end indicator-item", "IndicatorItemClasses horizontalPlacement End")
    }

    @Test
    fun indicatorItemClasses_verticalPlacement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItemClasses(
                verticalPlacement = IndicatorItemVerticalPlacement.Top,
            )
        }
        assertRendered(html, "indicator-item indicator-top", "IndicatorItemClasses verticalPlacement Top")
    }

    @Test
    fun indicatorItemClasses_verticalPlacement_middle() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItemClasses(
                verticalPlacement = IndicatorItemVerticalPlacement.Middle,
            )
        }
        assertRendered(html, "indicator-item indicator-middle", "IndicatorItemClasses verticalPlacement Middle")
    }

    @Test
    fun indicatorItemClasses_verticalPlacement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItemClasses(
                verticalPlacement = IndicatorItemVerticalPlacement.Bottom,
            )
        }
        assertRendered(html, "indicator-bottom indicator-item", "IndicatorItemClasses verticalPlacement Bottom")
    }
}
