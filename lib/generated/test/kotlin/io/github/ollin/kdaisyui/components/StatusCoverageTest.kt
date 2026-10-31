package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class StatusCoverageTest {

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
    fun status_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus(
                content = { },
            )
        }
        assertRendered(html, Expected("Status defaults", "status", closes = "</div></div>"))
        assertTrue(html.contains("role=\""), "Status sets role")
    }

    @Test
    fun status_all_flags() {
        val html = allFlags(Expected("Status all flags", "status zz-extra", closes = "</div></div>")) { daisyStatus(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
        assertTrue(html.contains("role=\""), "Status sets role")
    }

    @Test
    fun status_color() = assertEachEntry("Status color", mapOf(StatusColor.Neutral to "status status-neutral", StatusColor.Primary to "status status-primary", StatusColor.Secondary to "status status-secondary", StatusColor.Accent to "status status-accent", StatusColor.Info to "status status-info", StatusColor.Success to "status status-success", StatusColor.Warning to "status status-warning", StatusColor.Error to "status status-error")) { daisyStatus(color = it, content = { }) }

    @Test
    fun status_size() = assertEachEntry("Status size", mapOf(StatusSize.Xs to "status status-xs", StatusSize.Sm to "status status-sm", StatusSize.Md to "status status-md", StatusSize.Lg to "status status-lg", StatusSize.Xl to "status status-xl")) { daisyStatus(size = it, content = { }) }

    @Test
    fun statusClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatusClasses()
        }
        assertRendered(html, Expected("StatusClasses defaults", "status"))
    }

    @Test
    fun statusClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatusClasses()
        }
        val expected = Expected("StatusClasses all flags", "status", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun statusClasses_color() = assertEachEntry("StatusClasses color", mapOf(StatusColor.Neutral to "status status-neutral", StatusColor.Primary to "status status-primary", StatusColor.Secondary to "status status-secondary", StatusColor.Accent to "status status-accent", StatusColor.Info to "status status-info", StatusColor.Success to "status status-success", StatusColor.Warning to "status status-warning", StatusColor.Error to "status status-error")) { daisyStatusClasses(color = it) }

    @Test
    fun statusClasses_size() = assertEachEntry("StatusClasses size", mapOf(StatusSize.Xs to "status status-xs", StatusSize.Sm to "status status-sm", StatusSize.Md to "status status-md", StatusSize.Lg to "status status-lg", StatusSize.Xl to "status status-xl")) { daisyStatusClasses(size = it) }
}
