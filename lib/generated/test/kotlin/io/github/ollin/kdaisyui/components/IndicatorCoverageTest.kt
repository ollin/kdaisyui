package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class IndicatorCoverageTest {

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
    fun indicator_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(
                content = { },
            )
        }
        assertRendered(html, Expected("Indicator defaults", "indicator", closes = "</div></div>"))
    }

    @Test
    fun indicator_all_flags() {
        val html = allFlags(Expected("Indicator all flags", "indicator zz-extra", closes = "</div></div>")) { daisyIndicator(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun indicatorClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorClasses()
        }
        assertRendered(html, Expected("IndicatorClasses defaults", "indicator"))
    }

    @Test
    fun indicatorClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorClasses()
        }
        val expected = Expected("IndicatorClasses all flags", "indicator", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun indicatorItem_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(
                content = { },
            )
        }
        assertRendered(html, Expected("IndicatorItem defaults", "indicator-item", closes = "</span></div>"))
    }

    @Test
    fun indicatorItem_all_flags() {
        val html = allFlags(Expected("IndicatorItem all flags", "indicator-item zz-extra", closes = "</span></div>")) { daisyIndicatorItem(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun indicatorItem_horizontalPlacement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(
                horizontalPlacement = IndicatorItemHorizontalPlacement.Start,
                content = { },
            )
        }
        assertRendered(html, Expected("IndicatorItem horizontalPlacement Start", "indicator-item indicator-start"))
    }

    @Test
    fun indicatorItem_horizontalPlacement_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(
                horizontalPlacement = IndicatorItemHorizontalPlacement.Center,
                content = { },
            )
        }
        assertRendered(html, Expected("IndicatorItem horizontalPlacement Center", "indicator-center indicator-item"))
    }

    @Test
    fun indicatorItem_horizontalPlacement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(
                horizontalPlacement = IndicatorItemHorizontalPlacement.End,
                content = { },
            )
        }
        assertRendered(html, Expected("IndicatorItem horizontalPlacement End", "indicator-end indicator-item"))
    }

    @Test
    fun indicatorItem_verticalPlacement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(
                verticalPlacement = IndicatorItemVerticalPlacement.Top,
                content = { },
            )
        }
        assertRendered(html, Expected("IndicatorItem verticalPlacement Top", "indicator-item indicator-top"))
    }

    @Test
    fun indicatorItem_verticalPlacement_middle() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(
                verticalPlacement = IndicatorItemVerticalPlacement.Middle,
                content = { },
            )
        }
        assertRendered(html, Expected("IndicatorItem verticalPlacement Middle", "indicator-item indicator-middle"))
    }

    @Test
    fun indicatorItem_verticalPlacement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(
                verticalPlacement = IndicatorItemVerticalPlacement.Bottom,
                content = { },
            )
        }
        assertRendered(html, Expected("IndicatorItem verticalPlacement Bottom", "indicator-bottom indicator-item"))
    }

    @Test
    fun indicatorItemClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItemClasses()
        }
        assertRendered(html, Expected("IndicatorItemClasses defaults", "indicator-item"))
    }

    @Test
    fun indicatorItemClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItemClasses()
        }
        val expected = Expected("IndicatorItemClasses all flags", "indicator-item", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun indicatorItemClasses_horizontalPlacement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItemClasses(
                horizontalPlacement = IndicatorItemHorizontalPlacement.Start,
            )
        }
        assertRendered(html, Expected("IndicatorItemClasses horizontalPlacement Start", "indicator-item indicator-start"))
    }

    @Test
    fun indicatorItemClasses_horizontalPlacement_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItemClasses(
                horizontalPlacement = IndicatorItemHorizontalPlacement.Center,
            )
        }
        assertRendered(html, Expected("IndicatorItemClasses horizontalPlacement Center", "indicator-center indicator-item"))
    }

    @Test
    fun indicatorItemClasses_horizontalPlacement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItemClasses(
                horizontalPlacement = IndicatorItemHorizontalPlacement.End,
            )
        }
        assertRendered(html, Expected("IndicatorItemClasses horizontalPlacement End", "indicator-end indicator-item"))
    }

    @Test
    fun indicatorItemClasses_verticalPlacement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItemClasses(
                verticalPlacement = IndicatorItemVerticalPlacement.Top,
            )
        }
        assertRendered(html, Expected("IndicatorItemClasses verticalPlacement Top", "indicator-item indicator-top"))
    }

    @Test
    fun indicatorItemClasses_verticalPlacement_middle() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItemClasses(
                verticalPlacement = IndicatorItemVerticalPlacement.Middle,
            )
        }
        assertRendered(html, Expected("IndicatorItemClasses verticalPlacement Middle", "indicator-item indicator-middle"))
    }

    @Test
    fun indicatorItemClasses_verticalPlacement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItemClasses(
                verticalPlacement = IndicatorItemVerticalPlacement.Bottom,
            )
        }
        assertRendered(html, Expected("IndicatorItemClasses verticalPlacement Bottom", "indicator-bottom indicator-item"))
    }
}
