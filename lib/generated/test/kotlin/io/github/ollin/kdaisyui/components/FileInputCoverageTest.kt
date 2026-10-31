package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class FileInputCoverageTest {

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
    fun fileInput_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput()
        }
        assertRendered(html, Expected("FileInput defaults", "file-input"))
    }

    @Test
    fun fileInput_all_flags() {
        val html = allFlags(Expected("FileInput all flags", "file-input file-input-ghost zz-extra", content = false)) { daisyFileInput(id = ID, ghost = true, extraClasses = EXTRA, attrs = ATTRS) }
    }

    @Test
    fun fileInput_color() = assertEachEntry("FileInput color", mapOf(FileInputColor.Neutral to "file-input file-input-neutral", FileInputColor.Primary to "file-input file-input-primary", FileInputColor.Secondary to "file-input file-input-secondary", FileInputColor.Accent to "file-input file-input-accent", FileInputColor.Info to "file-input file-input-info", FileInputColor.Success to "file-input file-input-success", FileInputColor.Warning to "file-input file-input-warning", FileInputColor.Error to "file-input file-input-error")) { daisyFileInput(color = it) }

    @Test
    fun fileInput_size() = assertEachEntry("FileInput size", mapOf(FileInputSize.Xs to "file-input file-input-xs", FileInputSize.Sm to "file-input file-input-sm", FileInputSize.Md to "file-input file-input-md", FileInputSize.Lg to "file-input file-input-lg", FileInputSize.Xl to "file-input file-input-xl")) { daisyFileInput(size = it) }

    @Test
    fun fileInputClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInputClasses()
        }
        assertRendered(html, Expected("FileInputClasses defaults", "file-input"))
    }

    @Test
    fun fileInputClasses_all_flags() = assertRenders(Expected("FileInputClasses all flags", "file-input file-input-ghost", content = false)) { daisyFileInputClasses(ghost = true) }

    @Test
    fun fileInputClasses_color() = assertEachEntry("FileInputClasses color", mapOf(FileInputColor.Neutral to "file-input file-input-neutral", FileInputColor.Primary to "file-input file-input-primary", FileInputColor.Secondary to "file-input file-input-secondary", FileInputColor.Accent to "file-input file-input-accent", FileInputColor.Info to "file-input file-input-info", FileInputColor.Success to "file-input file-input-success", FileInputColor.Warning to "file-input file-input-warning", FileInputColor.Error to "file-input file-input-error")) { daisyFileInputClasses(color = it) }

    @Test
    fun fileInputClasses_size() = assertEachEntry("FileInputClasses size", mapOf(FileInputSize.Xs to "file-input file-input-xs", FileInputSize.Sm to "file-input file-input-sm", FileInputSize.Md to "file-input file-input-md", FileInputSize.Lg to "file-input file-input-lg", FileInputSize.Xl to "file-input file-input-xl")) { daisyFileInputClasses(size = it) }
}
