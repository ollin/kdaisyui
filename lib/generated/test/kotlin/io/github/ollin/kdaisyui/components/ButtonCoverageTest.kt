package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.ButtonType
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ButtonCoverageTest {

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
    fun button_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton()
        }
        assertRendered(html, Expected("Button defaults", "btn", closes = "</button></div>"))
    }

    @Test
    fun button_all_flags() {
        val html = allFlags(Expected("Button all flags", "btn btn-active btn-disabled zz-extra", closes = "</button></div>")) { daisyButton(id = ID, active = true, disabled = true, type = ButtonType.button, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
        assertTrue(html.contains("disabled=\""), "Button sets disabled")
        assertTrue(html.contains("type=\""), "Button sets type")
    }

    @Test
    fun button_color() = assertEachEntry("Button color", mapOf(ButtonColor.Neutral to "btn btn-neutral", ButtonColor.Primary to "btn btn-primary", ButtonColor.Secondary to "btn btn-secondary", ButtonColor.Accent to "btn btn-accent", ButtonColor.Info to "btn btn-info", ButtonColor.Success to "btn btn-success", ButtonColor.Warning to "btn btn-warning", ButtonColor.Error to "btn btn-error")) { daisyButton(color = it) }

    @Test
    fun button_size() = assertEachEntry("Button size", mapOf(ButtonSize.Xs to "btn btn-xs", ButtonSize.Sm to "btn btn-sm", ButtonSize.Md to "btn btn-md", ButtonSize.Lg to "btn btn-lg", ButtonSize.Xl to "btn btn-xl")) { daisyButton(size = it) }

    @Test
    fun button_style() = assertEachEntry("Button style", mapOf(ButtonStyle.Outline to "btn btn-outline", ButtonStyle.Dash to "btn btn-dash", ButtonStyle.Soft to "btn btn-soft", ButtonStyle.Ghost to "btn btn-ghost", ButtonStyle.Link to "btn btn-link")) { daisyButton(style = it) }

    @Test
    fun button_shape() = assertEachEntry("Button shape", mapOf(ButtonShape.Square to "btn btn-square", ButtonShape.Circle to "btn btn-circle")) { daisyButton(shape = it) }

    @Test
    fun button_width() = assertEachEntry("Button width", mapOf(ButtonWidth.Wide to "btn btn-wide", ButtonWidth.Block to "btn btn-block")) { daisyButton(width = it) }

    @Test
    fun button_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                text = "txtmark",
            )
        }
        assertRendered(html, Expected("Button text", "btn"))
        assertTrue(html.contains("txtmark"), "Button text content")
    }

    @Test
    fun buttonClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses()
        }
        assertRendered(html, Expected("ButtonClasses defaults", "btn"))
    }

    @Test
    fun buttonClasses_all_flags() = assertRenders(Expected("ButtonClasses all flags", "btn btn-active btn-disabled", content = false)) { daisyButtonClasses(active = true, disabled = true) }

    @Test
    fun buttonClasses_color() = assertEachEntry("ButtonClasses color", mapOf(ButtonColor.Neutral to "btn btn-neutral", ButtonColor.Primary to "btn btn-primary", ButtonColor.Secondary to "btn btn-secondary", ButtonColor.Accent to "btn btn-accent", ButtonColor.Info to "btn btn-info", ButtonColor.Success to "btn btn-success", ButtonColor.Warning to "btn btn-warning", ButtonColor.Error to "btn btn-error")) { daisyButtonClasses(color = it) }

    @Test
    fun buttonClasses_size() = assertEachEntry("ButtonClasses size", mapOf(ButtonSize.Xs to "btn btn-xs", ButtonSize.Sm to "btn btn-sm", ButtonSize.Md to "btn btn-md", ButtonSize.Lg to "btn btn-lg", ButtonSize.Xl to "btn btn-xl")) { daisyButtonClasses(size = it) }

    @Test
    fun buttonClasses_style() = assertEachEntry("ButtonClasses style", mapOf(ButtonStyle.Outline to "btn btn-outline", ButtonStyle.Dash to "btn btn-dash", ButtonStyle.Soft to "btn btn-soft", ButtonStyle.Ghost to "btn btn-ghost", ButtonStyle.Link to "btn btn-link")) { daisyButtonClasses(style = it) }

    @Test
    fun buttonClasses_shape() = assertEachEntry("ButtonClasses shape", mapOf(ButtonShape.Square to "btn btn-square", ButtonShape.Circle to "btn btn-circle")) { daisyButtonClasses(shape = it) }

    @Test
    fun buttonClasses_width() = assertEachEntry("ButtonClasses width", mapOf(ButtonWidth.Wide to "btn btn-wide", ButtonWidth.Block to "btn btn-block")) { daisyButtonClasses(width = it) }
}
