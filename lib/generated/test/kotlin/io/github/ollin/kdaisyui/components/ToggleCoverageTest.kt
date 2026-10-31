package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ToggleCoverageTest {

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
    fun toggle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle()
        }
        assertRendered(html, Expected("Toggle defaults", "toggle"))
        assertTrue(html.contains("type=\""), "Toggle sets type")
    }

    @Test
    fun toggle_all_flags() {
        val html = allFlags(Expected("Toggle all flags", "toggle zz-extra", content = false)) { daisyToggle(id = ID, checked = true, disabled = true, extraClasses = EXTRA, attrs = ATTRS) }
        assertTrue(html.contains("type=\""), "Toggle sets type")
        assertTrue(html.contains("checked=\""), "Toggle sets checked")
        assertTrue(html.contains("disabled=\""), "Toggle sets disabled")
    }

    @Test
    fun toggle_color() = assertEachEntry("Toggle color", mapOf(ToggleColor.Primary to "toggle toggle-primary", ToggleColor.Secondary to "toggle toggle-secondary", ToggleColor.Accent to "toggle toggle-accent", ToggleColor.Neutral to "toggle toggle-neutral", ToggleColor.Success to "toggle toggle-success", ToggleColor.Warning to "toggle toggle-warning", ToggleColor.Info to "toggle toggle-info", ToggleColor.Error to "toggle toggle-error")) { daisyToggle(color = it) }

    @Test
    fun toggle_size() = assertEachEntry("Toggle size", mapOf(ToggleSize.Xs to "toggle toggle-xs", ToggleSize.Sm to "toggle toggle-sm", ToggleSize.Md to "toggle toggle-md", ToggleSize.Lg to "toggle toggle-lg", ToggleSize.Xl to "toggle toggle-xl")) { daisyToggle(size = it) }

    @Test
    fun toggleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggleClasses()
        }
        assertRendered(html, Expected("ToggleClasses defaults", "toggle"))
    }

    @Test
    fun toggleClasses_all_flags() = assertRenders(Expected("ToggleClasses all flags", "toggle", content = false)) { daisyToggleClasses() }

    @Test
    fun toggleClasses_color() = assertEachEntry("ToggleClasses color", mapOf(ToggleColor.Primary to "toggle toggle-primary", ToggleColor.Secondary to "toggle toggle-secondary", ToggleColor.Accent to "toggle toggle-accent", ToggleColor.Neutral to "toggle toggle-neutral", ToggleColor.Success to "toggle toggle-success", ToggleColor.Warning to "toggle toggle-warning", ToggleColor.Info to "toggle toggle-info", ToggleColor.Error to "toggle toggle-error")) { daisyToggleClasses(color = it) }

    @Test
    fun toggleClasses_size() = assertEachEntry("ToggleClasses size", mapOf(ToggleSize.Xs to "toggle toggle-xs", ToggleSize.Sm to "toggle toggle-sm", ToggleSize.Md to "toggle toggle-md", ToggleSize.Lg to "toggle toggle-lg", ToggleSize.Xl to "toggle toggle-xl")) { daisyToggleClasses(size = it) }
}
