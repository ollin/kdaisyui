package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CollapseCoverageTest {

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
    fun collapse_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(
                content = { },
            )
        }
        assertRendered(html, Expected("Collapse defaults", "collapse", closes = "</div></div>"))
    }

    @Test
    fun collapse_all_flags() {
        val html = allFlags(Expected("Collapse all flags", "collapse zz-extra", closes = "</div></div>")) { daisyCollapse(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun collapse_icon() = assertEachEntry("Collapse icon", mapOf(CollapseIcon.Arrow to "collapse collapse-arrow", CollapseIcon.Plus to "collapse collapse-plus")) { daisyCollapse(icon = it, content = { }) }

    @Test
    fun collapse_state() = assertEachEntry("Collapse state", mapOf(CollapseState.Open to "collapse collapse-open", CollapseState.Close to "collapse collapse-close")) { daisyCollapse(state = it, content = { }) }

    @Test
    fun collapseClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseClasses()
        }
        assertRendered(html, Expected("CollapseClasses defaults", "collapse"))
    }

    @Test
    fun collapseClasses_all_flags() = assertRenders(Expected("CollapseClasses all flags", "collapse", content = false)) { daisyCollapseClasses() }

    @Test
    fun collapseClasses_icon() = assertEachEntry("CollapseClasses icon", mapOf(CollapseIcon.Arrow to "collapse collapse-arrow", CollapseIcon.Plus to "collapse collapse-plus")) { daisyCollapseClasses(icon = it) }

    @Test
    fun collapseClasses_state() = assertEachEntry("CollapseClasses state", mapOf(CollapseState.Open to "collapse collapse-open", CollapseState.Close to "collapse collapse-close")) { daisyCollapseClasses(state = it) }

    @Test
    fun collapseTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseTitle()
        }
        assertRendered(html, Expected("CollapseTitle defaults", "collapse-title", closes = "</div></div>"))
    }

    @Test
    fun collapseTitle_all_flags() {
        val html = allFlags(Expected("CollapseTitle all flags", "collapse-title zz-extra", closes = "</div></div>")) { daisyCollapseTitle(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun collapseTitle_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseTitle(
                text = "txtmark",
            )
        }
        assertRendered(html, Expected("CollapseTitle text", "collapse-title"))
        assertTrue(html.contains("txtmark"), "CollapseTitle text content")
    }

    @Test
    fun collapseTitleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseTitleClasses()
        }
        assertRendered(html, Expected("CollapseTitleClasses defaults", "collapse-title"))
    }

    @Test
    fun collapseTitleClasses_all_flags() = assertRenders(Expected("CollapseTitleClasses all flags", "collapse-title", content = false)) { daisyCollapseTitleClasses() }

    @Test
    fun collapseContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseContent(
                content = { },
            )
        }
        assertRendered(html, Expected("CollapseContent defaults", "collapse-content", closes = "</div></div>"))
    }

    @Test
    fun collapseContent_all_flags() {
        val html = allFlags(Expected("CollapseContent all flags", "collapse-content zz-extra", closes = "</div></div>")) { daisyCollapseContent(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun collapseContentClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseContentClasses()
        }
        assertRendered(html, Expected("CollapseContentClasses defaults", "collapse-content"))
    }

    @Test
    fun collapseContentClasses_all_flags() = assertRenders(Expected("CollapseContentClasses all flags", "collapse-content", content = false)) { daisyCollapseContentClasses() }
}
