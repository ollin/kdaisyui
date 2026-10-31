package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class RadioCoverageTest {

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
    fun radio_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio()
        }
        assertRendered(html, Expected("Radio defaults", "radio"))
        assertTrue(html.contains("type=\""), "Radio sets type")
    }

    @Test
    fun radio_all_flags() {
        val html = allFlags(Expected("Radio all flags", "radio zz-extra", content = false)) { daisyRadio(id = ID, checked = true, disabled = true, name = "x", extraClasses = EXTRA, attrs = ATTRS) }
        assertTrue(html.contains("name=\"x\""), "Radio name")
        assertTrue(html.contains("type=\""), "Radio sets type")
        assertTrue(html.contains("name=\""), "Radio sets name")
        assertTrue(html.contains("checked=\""), "Radio sets checked")
        assertTrue(html.contains("disabled=\""), "Radio sets disabled")
    }

    @Test
    fun radio_color() = assertEachEntry("Radio color", mapOf(RadioColor.Neutral to "radio radio-neutral", RadioColor.Primary to "radio radio-primary", RadioColor.Secondary to "radio radio-secondary", RadioColor.Accent to "radio radio-accent", RadioColor.Success to "radio radio-success", RadioColor.Warning to "radio radio-warning", RadioColor.Info to "radio radio-info", RadioColor.Error to "radio radio-error")) { daisyRadio(color = it) }

    @Test
    fun radio_size() = assertEachEntry("Radio size", mapOf(RadioSize.Xs to "radio radio-xs", RadioSize.Sm to "radio radio-sm", RadioSize.Md to "radio radio-md", RadioSize.Lg to "radio radio-lg", RadioSize.Xl to "radio radio-xl")) { daisyRadio(size = it) }

    @Test
    fun radioClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses()
        }
        assertRendered(html, Expected("RadioClasses defaults", "radio"))
    }

    @Test
    fun radioClasses_all_flags() = assertRenders(Expected("RadioClasses all flags", "radio", content = false)) { daisyRadioClasses() }

    @Test
    fun radioClasses_color() = assertEachEntry("RadioClasses color", mapOf(RadioColor.Neutral to "radio radio-neutral", RadioColor.Primary to "radio radio-primary", RadioColor.Secondary to "radio radio-secondary", RadioColor.Accent to "radio radio-accent", RadioColor.Success to "radio radio-success", RadioColor.Warning to "radio radio-warning", RadioColor.Info to "radio radio-info", RadioColor.Error to "radio radio-error")) { daisyRadioClasses(color = it) }

    @Test
    fun radioClasses_size() = assertEachEntry("RadioClasses size", mapOf(RadioSize.Xs to "radio radio-xs", RadioSize.Sm to "radio radio-sm", RadioSize.Md to "radio radio-md", RadioSize.Lg to "radio radio-lg", RadioSize.Xl to "radio radio-xl")) { daisyRadioClasses(size = it) }
}
