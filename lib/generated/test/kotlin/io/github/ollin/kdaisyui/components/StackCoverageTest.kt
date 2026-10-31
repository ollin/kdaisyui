package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class StackCoverageTest {

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
    fun stack_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(
                content = { },
            )
        }
        assertRendered(html, Expected("Stack defaults", "stack", closes = "</div></div>"))
    }

    @Test
    fun stack_all_flags() {
        val html = allFlags(Expected("Stack all flags", "stack zz-extra", closes = "</div></div>")) { daisyStack(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun stack_placement() = assertEachEntry("Stack placement", mapOf(StackPlacement.Top to "stack stack-top", StackPlacement.Bottom to "stack stack-bottom", StackPlacement.Start to "stack stack-start", StackPlacement.End to "stack stack-end")) { daisyStack(placement = it, content = { }) }

    @Test
    fun stackClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStackClasses()
        }
        assertRendered(html, Expected("StackClasses defaults", "stack"))
    }

    @Test
    fun stackClasses_all_flags() = assertRenders(Expected("StackClasses all flags", "stack", content = false)) { daisyStackClasses() }

    @Test
    fun stackClasses_placement() = assertEachEntry("StackClasses placement", mapOf(StackPlacement.Top to "stack stack-top", StackPlacement.Bottom to "stack stack-bottom", StackPlacement.Start to "stack stack-start", StackPlacement.End to "stack stack-end")) { daisyStackClasses(placement = it) }
}
