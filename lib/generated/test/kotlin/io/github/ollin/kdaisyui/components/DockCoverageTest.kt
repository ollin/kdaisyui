package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DockCoverageTest {

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
    fun dock_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                content = { },
            )
        }
        assertRendered(html, Expected("Dock defaults", "dock", closes = "</div></div>"))
    }

    @Test
    fun dock_all_flags() {
        val html = allFlags(Expected("Dock all flags", "dock zz-extra", closes = "</div></div>")) { daisyDock(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun dock_size() = assertEachEntry("Dock size", mapOf(DockSize.Xs to "dock dock-xs", DockSize.Sm to "dock dock-sm", DockSize.Md to "dock dock-md", DockSize.Lg to "dock dock-lg", DockSize.Xl to "dock dock-xl")) { daisyDock(size = it, content = { }) }

    @Test
    fun dockClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockClasses()
        }
        assertRendered(html, Expected("DockClasses defaults", "dock"))
    }

    @Test
    fun dockClasses_all_flags() = assertRenders(Expected("DockClasses all flags", "dock", content = false)) { daisyDockClasses() }

    @Test
    fun dockClasses_size() = assertEachEntry("DockClasses size", mapOf(DockSize.Xs to "dock dock-xs", DockSize.Sm to "dock dock-sm", DockSize.Md to "dock dock-md", DockSize.Lg to "dock dock-lg", DockSize.Xl to "dock dock-xl")) { daisyDockClasses(size = it) }

    @Test
    fun dockItem_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockItem(
                content = { },
            )
        }
        assertTrue(!html.contains("class=\""), "DockItem defaults emits no class")
        assertTrue(html.endsWith("</button></div>"), "DockItem closes")
    }

    @Test
    fun dockItem_all_flags() {
        val html = allFlags(Expected("DockItem all flags", "dock-active zz-extra", closes = "</button></div>")) { daisyDockItem(id = ID, active = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun dockItemClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockItemClasses()
        }
        assertTrue(!html.contains("class=\""), "DockItemClasses defaults emits no class")
    }

    @Test
    fun dockItemClasses_all_flags() = assertRenders(Expected("DockItemClasses all flags", "dock-active", content = false)) { daisyDockItemClasses(active = true) }

    @Test
    fun dockLabel_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockLabel(
                content = { },
            )
        }
        assertRendered(html, Expected("DockLabel defaults", "dock-label", closes = "</span></div>"))
    }

    @Test
    fun dockLabel_all_flags() {
        val html = allFlags(Expected("DockLabel all flags", "dock-label zz-extra", closes = "</span></div>")) { daisyDockLabel(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun dockLabelClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockLabelClasses()
        }
        assertRendered(html, Expected("DockLabelClasses defaults", "dock-label"))
    }

    @Test
    fun dockLabelClasses_all_flags() = assertRenders(Expected("DockLabelClasses all flags", "dock-label", content = false)) { daisyDockLabelClasses() }
}
