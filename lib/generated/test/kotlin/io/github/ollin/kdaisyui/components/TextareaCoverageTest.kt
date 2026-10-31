package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TextareaCoverageTest {

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
    fun textarea_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                content = { },
            )
        }
        assertRendered(html, Expected("Textarea defaults", "textarea", closes = "</textarea></div>"))
    }

    @Test
    fun textarea_all_flags() {
        val html = allFlags(Expected("Textarea all flags", "textarea textarea-ghost zz-extra", closes = "</textarea></div>")) { daisyTextarea(id = ID, ghost = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun textarea_color() = assertEachEntry("Textarea color", mapOf(TextareaColor.Neutral to "textarea textarea-neutral", TextareaColor.Primary to "textarea textarea-primary", TextareaColor.Secondary to "textarea textarea-secondary", TextareaColor.Accent to "textarea textarea-accent", TextareaColor.Info to "textarea textarea-info", TextareaColor.Success to "textarea textarea-success", TextareaColor.Warning to "textarea textarea-warning", TextareaColor.Error to "textarea textarea-error")) { daisyTextarea(color = it, content = { }) }

    @Test
    fun textarea_size() = assertEachEntry("Textarea size", mapOf(TextareaSize.Xs to "textarea textarea-xs", TextareaSize.Sm to "textarea textarea-sm", TextareaSize.Md to "textarea textarea-md", TextareaSize.Lg to "textarea textarea-lg", TextareaSize.Xl to "textarea textarea-xl")) { daisyTextarea(size = it, content = { }) }

    @Test
    fun textareaClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses()
        }
        assertRendered(html, Expected("TextareaClasses defaults", "textarea"))
    }

    @Test
    fun textareaClasses_all_flags() = assertRenders(Expected("TextareaClasses all flags", "textarea textarea-ghost", content = false)) { daisyTextareaClasses(ghost = true) }

    @Test
    fun textareaClasses_color() = assertEachEntry("TextareaClasses color", mapOf(TextareaColor.Neutral to "textarea textarea-neutral", TextareaColor.Primary to "textarea textarea-primary", TextareaColor.Secondary to "textarea textarea-secondary", TextareaColor.Accent to "textarea textarea-accent", TextareaColor.Info to "textarea textarea-info", TextareaColor.Success to "textarea textarea-success", TextareaColor.Warning to "textarea textarea-warning", TextareaColor.Error to "textarea textarea-error")) { daisyTextareaClasses(color = it) }

    @Test
    fun textareaClasses_size() = assertEachEntry("TextareaClasses size", mapOf(TextareaSize.Xs to "textarea textarea-xs", TextareaSize.Sm to "textarea textarea-sm", TextareaSize.Md to "textarea textarea-md", TextareaSize.Lg to "textarea textarea-lg", TextareaSize.Xl to "textarea textarea-xl")) { daisyTextareaClasses(size = it) }
}
