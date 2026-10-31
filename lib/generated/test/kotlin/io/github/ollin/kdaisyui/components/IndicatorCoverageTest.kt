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

    /** Renders one call and asserts the classes it writes. */
    private fun assertRenders(expected: Expected, render: FlowContent.() -> Unit) {
        assertRendered(createHTML(prettyPrint = false).div { render() }, expected)
    }

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
    fun indicatorClasses_all_flags() = assertRenders(Expected("IndicatorClasses all flags", "indicator", content = false)) { daisyIndicatorClasses() }

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
    fun indicatorItem_horizontalPlacement() = assertEachEntry("IndicatorItem horizontalPlacement", mapOf(IndicatorItemHorizontalPlacement.Start to "indicator-item indicator-start", IndicatorItemHorizontalPlacement.Center to "indicator-center indicator-item", IndicatorItemHorizontalPlacement.End to "indicator-end indicator-item")) { daisyIndicatorItem(horizontalPlacement = it, content = { }) }

    @Test
    fun indicatorItem_verticalPlacement() = assertEachEntry("IndicatorItem verticalPlacement", mapOf(IndicatorItemVerticalPlacement.Top to "indicator-item indicator-top", IndicatorItemVerticalPlacement.Middle to "indicator-item indicator-middle", IndicatorItemVerticalPlacement.Bottom to "indicator-bottom indicator-item")) { daisyIndicatorItem(verticalPlacement = it, content = { }) }

    @Test
    fun indicatorItemClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItemClasses()
        }
        assertRendered(html, Expected("IndicatorItemClasses defaults", "indicator-item"))
    }

    @Test
    fun indicatorItemClasses_all_flags() = assertRenders(Expected("IndicatorItemClasses all flags", "indicator-item", content = false)) { daisyIndicatorItemClasses() }

    @Test
    fun indicatorItemClasses_horizontalPlacement() = assertEachEntry("IndicatorItemClasses horizontalPlacement", mapOf(IndicatorItemHorizontalPlacement.Start to "indicator-item indicator-start", IndicatorItemHorizontalPlacement.Center to "indicator-center indicator-item", IndicatorItemHorizontalPlacement.End to "indicator-end indicator-item")) { daisyIndicatorItemClasses(horizontalPlacement = it) }

    @Test
    fun indicatorItemClasses_verticalPlacement() = assertEachEntry("IndicatorItemClasses verticalPlacement", mapOf(IndicatorItemVerticalPlacement.Top to "indicator-item indicator-top", IndicatorItemVerticalPlacement.Middle to "indicator-item indicator-middle", IndicatorItemVerticalPlacement.Bottom to "indicator-bottom indicator-item")) { daisyIndicatorItemClasses(verticalPlacement = it) }
}
