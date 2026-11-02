package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class FabCoverageTest {

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
    fun fab_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab()
        }
        assertRendered(html, Expected("Fab defaults", "fab", closes = "</div></div>"))
    }

    @Test
    fun fab_all_flags() {
        val html = allFlags(Expected("Fab all flags", "fab fab-flower zz-extra", closes = "</div></div>")) { daisyFab(id = ID, flower = true, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun fabClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabClasses()
        }
        assertRendered(html, Expected("FabClasses defaults", "fab"))
    }

    @Test
    fun fabClasses_all_flags() = assertRenders(Expected("FabClasses all flags", "fab fab-flower")) { daisyFabClasses(flower = true) }

    @Test
    fun fabClose_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabClose()
        }
        assertRendered(html, Expected("FabClose defaults", "fab-close", closes = "</div></div>"))
    }

    @Test
    fun fabClose_all_flags() {
        val html = allFlags(Expected("FabClose all flags", "fab-close zz-extra", closes = "</div></div>")) { daisyFabClose(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun fabCloseClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabCloseClasses()
        }
        assertRendered(html, Expected("FabCloseClasses defaults", "fab-close"))
    }

    @Test
    fun fabCloseClasses_all_flags() = assertRenders(Expected("FabCloseClasses all flags", "fab-close")) { daisyFabCloseClasses() }

    @Test
    fun fabMainAction_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabMainAction()
        }
        assertRendered(html, Expected("FabMainAction defaults", "fab-main-action", closes = "</button></div>"))
    }

    @Test
    fun fabMainAction_all_flags() {
        val html = allFlags(Expected("FabMainAction all flags", "fab-main-action zz-extra", closes = "</button></div>")) { daisyFabMainAction(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun fabMainActionClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabMainActionClasses()
        }
        assertRendered(html, Expected("FabMainActionClasses defaults", "fab-main-action"))
    }

    @Test
    fun fabMainActionClasses_all_flags() = assertRenders(Expected("FabMainActionClasses all flags", "fab-main-action")) { daisyFabMainActionClasses() }
}
