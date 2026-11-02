package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DividerCoverageTest {

    /** What a case must render: its classes, sorted, and the tail it closes with. */
    private class Expected(val label: String, val classes: String, val closes: String = "")

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val BLOCK: Tag.() -> Unit = { attributes["data-block"] = "yes" }

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
        assertTrue(html.contains("data-block=\"yes\""), "${expected.label} block")
    }

    @Test
    fun divider_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider()
        }
        assertRendered(html, Expected("Divider defaults", "divider", closes = "</div></div>"))
    }

    @Test
    fun divider_all_flags() {
        val html = allFlags(Expected("Divider all flags", "divider zz-extra", closes = "</div></div>")) { daisyDivider(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun divider_color() = assertEachEntry("Divider color", mapOf(DividerColor.Neutral to "divider divider-neutral", DividerColor.Primary to "divider divider-primary", DividerColor.Secondary to "divider divider-secondary", DividerColor.Accent to "divider divider-accent", DividerColor.Success to "divider divider-success", DividerColor.Warning to "divider divider-warning", DividerColor.Info to "divider divider-info", DividerColor.Error to "divider divider-error")) { daisyDivider(color = it) }

    @Test
    fun divider_direction() = assertEachEntry("Divider direction", mapOf(DividerDirection.Vertical to "divider divider-vertical", DividerDirection.Horizontal to "divider divider-horizontal")) { daisyDivider(direction = it) }

    @Test
    fun divider_placement() = assertEachEntry("Divider placement", mapOf(DividerPlacement.Start to "divider divider-start", DividerPlacement.End to "divider divider-end")) { daisyDivider(placement = it) }

    @Test
    fun dividerClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDividerClasses()
        }
        assertRendered(html, Expected("DividerClasses defaults", "divider"))
    }

    @Test
    fun dividerClasses_all_flags() = assertRenders(Expected("DividerClasses all flags", "divider")) { daisyDividerClasses() }

    @Test
    fun dividerClasses_color() = assertEachEntry("DividerClasses color", mapOf(DividerColor.Neutral to "divider divider-neutral", DividerColor.Primary to "divider divider-primary", DividerColor.Secondary to "divider divider-secondary", DividerColor.Accent to "divider divider-accent", DividerColor.Success to "divider divider-success", DividerColor.Warning to "divider divider-warning", DividerColor.Info to "divider divider-info", DividerColor.Error to "divider divider-error")) { daisyDividerClasses(color = it) }

    @Test
    fun dividerClasses_direction() = assertEachEntry("DividerClasses direction", mapOf(DividerDirection.Vertical to "divider divider-vertical", DividerDirection.Horizontal to "divider divider-horizontal")) { daisyDividerClasses(direction = it) }

    @Test
    fun dividerClasses_placement() = assertEachEntry("DividerClasses placement", mapOf(DividerPlacement.Start to "divider divider-start", DividerPlacement.End to "divider divider-end")) { daisyDividerClasses(placement = it) }
}
