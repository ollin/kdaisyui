package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class RangeCoverageTest {

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
    fun range_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange()
        }
        assertRendered(html, Expected("Range defaults", "range"))
        assertTrue(html.contains("type=\""), "Range sets type")
    }

    @Test
    fun range_all_flags() {
        val html = allFlags(Expected("Range all flags", "range range-vertical zz-extra", content = false)) { daisyRange(id = ID, vertical = true, disabled = true, min = "x", max = "x", value = "x", step = "x", extraClasses = EXTRA, attrs = ATTRS) }
        assertTrue(html.contains("min=\"x\""), "Range min")
        assertTrue(html.contains("max=\"x\""), "Range max")
        assertTrue(html.contains("value=\"x\""), "Range value")
        assertTrue(html.contains("step=\"x\""), "Range step")
        assertTrue(html.contains("type=\""), "Range sets type")
        assertTrue(html.contains("min=\""), "Range sets min")
        assertTrue(html.contains("max=\""), "Range sets max")
        assertTrue(html.contains("value=\""), "Range sets value")
        assertTrue(html.contains("step=\""), "Range sets step")
        assertTrue(html.contains("disabled=\""), "Range sets disabled")
    }

    @Test
    fun range_color() = assertEachEntry("Range color", mapOf(RangeColor.Neutral to "range range-neutral", RangeColor.Primary to "range range-primary", RangeColor.Secondary to "range range-secondary", RangeColor.Accent to "range range-accent", RangeColor.Success to "range range-success", RangeColor.Warning to "range range-warning", RangeColor.Info to "range range-info", RangeColor.Error to "range range-error")) { daisyRange(color = it) }

    @Test
    fun range_size() = assertEachEntry("Range size", mapOf(RangeSize.Xs to "range range-xs", RangeSize.Sm to "range range-sm", RangeSize.Md to "range range-md", RangeSize.Lg to "range range-lg", RangeSize.Xl to "range range-xl")) { daisyRange(size = it) }

    @Test
    fun rangeClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRangeClasses()
        }
        assertRendered(html, Expected("RangeClasses defaults", "range"))
    }

    @Test
    fun rangeClasses_all_flags() = assertRenders(Expected("RangeClasses all flags", "range range-vertical", content = false)) { daisyRangeClasses(vertical = true) }

    @Test
    fun rangeClasses_color() = assertEachEntry("RangeClasses color", mapOf(RangeColor.Neutral to "range range-neutral", RangeColor.Primary to "range range-primary", RangeColor.Secondary to "range range-secondary", RangeColor.Accent to "range range-accent", RangeColor.Success to "range range-success", RangeColor.Warning to "range range-warning", RangeColor.Info to "range range-info", RangeColor.Error to "range range-error")) { daisyRangeClasses(color = it) }

    @Test
    fun rangeClasses_size() = assertEachEntry("RangeClasses size", mapOf(RangeSize.Xs to "range range-xs", RangeSize.Sm to "range range-sm", RangeSize.Md to "range range-md", RangeSize.Lg to "range range-lg", RangeSize.Xl to "range range-xl")) { daisyRangeClasses(size = it) }
}
