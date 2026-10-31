package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TooltipCoverageTest {

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
    fun tooltip_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                content = { },
            )
        }
        assertRendered(html, Expected("Tooltip defaults", "tooltip", closes = "</div></div>"))
    }

    @Test
    fun tooltip_all_flags() {
        val html = allFlags(Expected("Tooltip all flags", "tooltip tooltip-open zz-extra", closes = "</div></div>")) { daisyTooltip(id = ID, open = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun tooltip_color() = assertEachEntry("Tooltip color", mapOf(TooltipColor.Primary to "tooltip tooltip-primary", TooltipColor.Secondary to "tooltip tooltip-secondary", TooltipColor.Accent to "tooltip tooltip-accent", TooltipColor.Info to "tooltip tooltip-info", TooltipColor.Success to "tooltip tooltip-success", TooltipColor.Warning to "tooltip tooltip-warning", TooltipColor.Error to "tooltip tooltip-error")) { daisyTooltip(color = it, content = { }) }

    @Test
    fun tooltip_side() = assertEachEntry("Tooltip side", mapOf(TooltipSide.Top to "tooltip tooltip-top", TooltipSide.Bottom to "tooltip tooltip-bottom", TooltipSide.Left to "tooltip tooltip-left", TooltipSide.Right to "tooltip tooltip-right")) { daisyTooltip(side = it, content = { }) }

    @Test
    fun tooltip_align() = assertEachEntry("Tooltip align", mapOf(TooltipAlign.Start to "tooltip tooltip-start", TooltipAlign.Center to "tooltip tooltip-center", TooltipAlign.End to "tooltip tooltip-end")) { daisyTooltip(align = it, content = { }) }

    @Test
    fun tooltipClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses()
        }
        assertRendered(html, Expected("TooltipClasses defaults", "tooltip"))
    }

    @Test
    fun tooltipClasses_all_flags() = assertRenders(Expected("TooltipClasses all flags", "tooltip tooltip-open", content = false)) { daisyTooltipClasses(open = true) }

    @Test
    fun tooltipClasses_color() = assertEachEntry("TooltipClasses color", mapOf(TooltipColor.Primary to "tooltip tooltip-primary", TooltipColor.Secondary to "tooltip tooltip-secondary", TooltipColor.Accent to "tooltip tooltip-accent", TooltipColor.Info to "tooltip tooltip-info", TooltipColor.Success to "tooltip tooltip-success", TooltipColor.Warning to "tooltip tooltip-warning", TooltipColor.Error to "tooltip tooltip-error")) { daisyTooltipClasses(color = it) }

    @Test
    fun tooltipClasses_side() = assertEachEntry("TooltipClasses side", mapOf(TooltipSide.Top to "tooltip tooltip-top", TooltipSide.Bottom to "tooltip tooltip-bottom", TooltipSide.Left to "tooltip tooltip-left", TooltipSide.Right to "tooltip tooltip-right")) { daisyTooltipClasses(side = it) }

    @Test
    fun tooltipClasses_align() = assertEachEntry("TooltipClasses align", mapOf(TooltipAlign.Start to "tooltip tooltip-start", TooltipAlign.Center to "tooltip tooltip-center", TooltipAlign.End to "tooltip tooltip-end")) { daisyTooltipClasses(align = it) }

    @Test
    fun tooltipContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipContent(
                content = { },
            )
        }
        assertRendered(html, Expected("TooltipContent defaults", "tooltip-content", closes = "</div></div>"))
    }

    @Test
    fun tooltipContent_all_flags() {
        val html = allFlags(Expected("TooltipContent all flags", "tooltip-content zz-extra", closes = "</div></div>")) { daisyTooltipContent(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun tooltipContentClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipContentClasses()
        }
        assertRendered(html, Expected("TooltipContentClasses defaults", "tooltip-content"))
    }

    @Test
    fun tooltipContentClasses_all_flags() = assertRenders(Expected("TooltipContentClasses all flags", "tooltip-content", content = false)) { daisyTooltipContentClasses() }
}
