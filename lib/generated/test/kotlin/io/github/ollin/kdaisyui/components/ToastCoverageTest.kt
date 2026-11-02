package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ToastCoverageTest {

    /** What a case must render: its classes, sorted, and the tail it closes with. */
    private class Expected(val label: String, val classes: String, val closes: String = "")

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val BLOCK: Tag.() -> Unit = { attributes["data-block"] = "yes" }

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
        assertTrue(html.contains("data-block=\"yes\""), "${expected.label} block")
    }

    @Test
    fun toast_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast()
        }
        assertRendered(html, Expected("Toast defaults", "toast", closes = "</div></div>"))
    }

    @Test
    fun toast_all_flags() {
        val html = allFlags(Expected("Toast all flags", "toast zz-extra", closes = "</div></div>")) { daisyToast(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun toast_horizontalPlacement() = assertEachEntry("Toast horizontalPlacement", mapOf(ToastHorizontalPlacement.Start to "toast toast-start", ToastHorizontalPlacement.Center to "toast toast-center", ToastHorizontalPlacement.End to "toast toast-end")) { daisyToast(horizontalPlacement = it) }

    @Test
    fun toast_verticalPlacement() = assertEachEntry("Toast verticalPlacement", mapOf(ToastVerticalPlacement.Top to "toast toast-top", ToastVerticalPlacement.Middle to "toast toast-middle", ToastVerticalPlacement.Bottom to "toast toast-bottom")) { daisyToast(verticalPlacement = it) }

    @Test
    fun toastClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyToastClasses()
        }
        assertRendered(html, Expected("ToastClasses defaults", "toast"))
    }

    @Test
    fun toastClasses_all_flags() = assertRenders(Expected("ToastClasses all flags", "toast")) { daisyToastClasses() }

    @Test
    fun toastClasses_horizontalPlacement() = assertEachEntry("ToastClasses horizontalPlacement", mapOf(ToastHorizontalPlacement.Start to "toast toast-start", ToastHorizontalPlacement.Center to "toast toast-center", ToastHorizontalPlacement.End to "toast toast-end")) { daisyToastClasses(horizontalPlacement = it) }

    @Test
    fun toastClasses_verticalPlacement() = assertEachEntry("ToastClasses verticalPlacement", mapOf(ToastVerticalPlacement.Top to "toast toast-top", ToastVerticalPlacement.Middle to "toast toast-middle", ToastVerticalPlacement.Bottom to "toast toast-bottom")) { daisyToastClasses(verticalPlacement = it) }
}
