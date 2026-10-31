package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class AlertCoverageTest {

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
    fun alert_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                content = { },
            )
        }
        assertRendered(html, Expected("Alert defaults", "alert", closes = "</div></div>"))
        assertTrue(html.contains("role=\""), "Alert sets role")
    }

    @Test
    fun alert_all_flags() {
        val html = allFlags(Expected("Alert all flags", "alert alert-soft zz-extra", closes = "</div></div>")) { daisyAlert(id = ID, soft = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
        assertTrue(html.contains("role=\""), "Alert sets role")
    }

    @Test
    fun alert_color() = assertEachEntry("Alert color", mapOf(AlertColor.Info to "alert alert-info", AlertColor.Success to "alert alert-success", AlertColor.Warning to "alert alert-warning", AlertColor.Error to "alert alert-error")) { daisyAlert(color = it, content = { }) }

    @Test
    fun alert_style() = assertEachEntry("Alert style", mapOf(AlertStyle.Outline to "alert alert-outline", AlertStyle.Dash to "alert alert-dash")) { daisyAlert(style = it, content = { }) }

    @Test
    fun alert_direction() = assertEachEntry("Alert direction", mapOf(AlertDirection.Vertical to "alert alert-vertical", AlertDirection.Horizontal to "alert alert-horizontal")) { daisyAlert(direction = it, content = { }) }

    @Test
    fun alertClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses()
        }
        assertRendered(html, Expected("AlertClasses defaults", "alert"))
    }

    @Test
    fun alertClasses_all_flags() = assertRenders(Expected("AlertClasses all flags", "alert alert-soft", content = false)) { daisyAlertClasses(soft = true) }

    @Test
    fun alertClasses_color() = assertEachEntry("AlertClasses color", mapOf(AlertColor.Info to "alert alert-info", AlertColor.Success to "alert alert-success", AlertColor.Warning to "alert alert-warning", AlertColor.Error to "alert alert-error")) { daisyAlertClasses(color = it) }

    @Test
    fun alertClasses_style() = assertEachEntry("AlertClasses style", mapOf(AlertStyle.Outline to "alert alert-outline", AlertStyle.Dash to "alert alert-dash")) { daisyAlertClasses(style = it) }

    @Test
    fun alertClasses_direction() = assertEachEntry("AlertClasses direction", mapOf(AlertDirection.Vertical to "alert alert-vertical", AlertDirection.Horizontal to "alert alert-horizontal")) { daisyAlertClasses(direction = it) }
}
