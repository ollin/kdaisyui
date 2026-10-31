package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class InputCoverageTest {

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
    fun input_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput()
        }
        assertRendered(html, Expected("Input defaults", "input"))
        assertTrue(html.contains("type=\""), "Input sets type")
    }

    @Test
    fun input_all_flags() {
        val html = allFlags(Expected("Input all flags", "input input-ghost zz-extra", content = false)) { daisyInput(id = ID, ghost = true, disabled = true, placeholder = "x", value = "x", extraClasses = EXTRA, attrs = ATTRS) }
        assertTrue(html.contains("placeholder=\"x\""), "Input placeholder")
        assertTrue(html.contains("value=\"x\""), "Input value")
        assertTrue(html.contains("type=\""), "Input sets type")
        assertTrue(html.contains("placeholder=\""), "Input sets placeholder")
        assertTrue(html.contains("value=\""), "Input sets value")
        assertTrue(html.contains("disabled=\""), "Input sets disabled")
    }

    @Test
    fun input_color() = assertEachEntry("Input color", mapOf(InputColor.Neutral to "input input-neutral", InputColor.Primary to "input input-primary", InputColor.Secondary to "input input-secondary", InputColor.Accent to "input input-accent", InputColor.Info to "input input-info", InputColor.Success to "input input-success", InputColor.Warning to "input input-warning", InputColor.Error to "input input-error")) { daisyInput(color = it) }

    @Test
    fun input_size() = assertEachEntry("Input size", mapOf(InputSize.Xs to "input input-xs", InputSize.Sm to "input input-sm", InputSize.Md to "input input-md", InputSize.Lg to "input input-lg", InputSize.Xl to "input input-xl")) { daisyInput(size = it) }

    @Test
    fun inputClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyInputClasses()
        }
        assertRendered(html, Expected("InputClasses defaults", "input"))
    }

    @Test
    fun inputClasses_all_flags() = assertRenders(Expected("InputClasses all flags", "input input-ghost", content = false)) { daisyInputClasses(ghost = true) }

    @Test
    fun inputClasses_color() = assertEachEntry("InputClasses color", mapOf(InputColor.Neutral to "input input-neutral", InputColor.Primary to "input input-primary", InputColor.Secondary to "input input-secondary", InputColor.Accent to "input input-accent", InputColor.Info to "input input-info", InputColor.Success to "input input-success", InputColor.Warning to "input input-warning", InputColor.Error to "input input-error")) { daisyInputClasses(color = it) }

    @Test
    fun inputClasses_size() = assertEachEntry("InputClasses size", mapOf(InputSize.Xs to "input input-xs", InputSize.Sm to "input input-sm", InputSize.Md to "input input-md", InputSize.Lg to "input input-lg", InputSize.Xl to "input input-xl")) { daisyInputClasses(size = it) }
}
