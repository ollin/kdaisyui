package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ProgressCoverageTest {

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
    fun progress_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                content = { },
            )
        }
        assertRendered(html, Expected("Progress defaults", "progress", closes = "</progress></div>"))
    }

    @Test
    fun progress_all_flags() {
        val html = allFlags(Expected("Progress all flags", "progress zz-extra", closes = "</progress></div>")) { daisyProgress(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun progress_color() = assertEachEntry("Progress color", mapOf(ProgressColor.Neutral to "progress progress-neutral", ProgressColor.Primary to "progress progress-primary", ProgressColor.Secondary to "progress progress-secondary", ProgressColor.Accent to "progress progress-accent", ProgressColor.Info to "progress progress-info", ProgressColor.Success to "progress progress-success", ProgressColor.Warning to "progress progress-warning", ProgressColor.Error to "progress progress-error")) { daisyProgress(color = it, content = { }) }

    @Test
    fun progressClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses()
        }
        assertRendered(html, Expected("ProgressClasses defaults", "progress"))
    }

    @Test
    fun progressClasses_all_flags() = assertRenders(Expected("ProgressClasses all flags", "progress", content = false)) { daisyProgressClasses() }

    @Test
    fun progressClasses_color() = assertEachEntry("ProgressClasses color", mapOf(ProgressColor.Neutral to "progress progress-neutral", ProgressColor.Primary to "progress progress-primary", ProgressColor.Secondary to "progress progress-secondary", ProgressColor.Accent to "progress progress-accent", ProgressColor.Info to "progress progress-info", ProgressColor.Success to "progress progress-success", ProgressColor.Warning to "progress progress-warning", ProgressColor.Error to "progress progress-error")) { daisyProgressClasses(color = it) }
}
