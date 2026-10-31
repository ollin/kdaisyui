package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class SelectCoverageTest {

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
    fun select_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                content = { },
            )
        }
        assertRendered(html, Expected("Select defaults", "select", closes = "</select></div>"))
    }

    @Test
    fun select_all_flags() {
        val html = allFlags(Expected("Select all flags", "select select-ghost zz-extra", closes = "</select></div>")) { daisySelect(id = ID, ghost = true, disabled = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
        assertTrue(html.contains("disabled=\""), "Select sets disabled")
    }

    @Test
    fun select_color() = assertEachEntry("Select color", mapOf(SelectColor.Neutral to "select select-neutral", SelectColor.Primary to "select select-primary", SelectColor.Secondary to "select select-secondary", SelectColor.Accent to "select select-accent", SelectColor.Info to "select select-info", SelectColor.Success to "select select-success", SelectColor.Warning to "select select-warning", SelectColor.Error to "select select-error")) { daisySelect(color = it, content = { }) }

    @Test
    fun select_size() = assertEachEntry("Select size", mapOf(SelectSize.Xs to "select select-xs", SelectSize.Sm to "select select-sm", SelectSize.Md to "select select-md", SelectSize.Lg to "select select-lg", SelectSize.Xl to "select select-xl")) { daisySelect(size = it, content = { }) }

    @Test
    fun selectClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySelectClasses()
        }
        assertRendered(html, Expected("SelectClasses defaults", "select"))
    }

    @Test
    fun selectClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySelectClasses(
                ghost = true,
            )
        }
        val expected = Expected("SelectClasses all flags", "select select-ghost", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun selectClasses_color() = assertEachEntry("SelectClasses color", mapOf(SelectColor.Neutral to "select select-neutral", SelectColor.Primary to "select select-primary", SelectColor.Secondary to "select select-secondary", SelectColor.Accent to "select select-accent", SelectColor.Info to "select select-info", SelectColor.Success to "select select-success", SelectColor.Warning to "select select-warning", SelectColor.Error to "select select-error")) { daisySelectClasses(color = it) }

    @Test
    fun selectClasses_size() = assertEachEntry("SelectClasses size", mapOf(SelectSize.Xs to "select select-xs", SelectSize.Sm to "select select-sm", SelectSize.Md to "select select-md", SelectSize.Lg to "select select-lg", SelectSize.Xl to "select select-xl")) { daisySelectClasses(size = it) }
}
