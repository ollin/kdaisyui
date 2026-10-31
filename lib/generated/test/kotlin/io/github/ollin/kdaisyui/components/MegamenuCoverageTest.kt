package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MegamenuCoverageTest {

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
    fun megamenu_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                content = { },
            )
        }
        assertRendered(html, Expected("Megamenu defaults", "megamenu", closes = "</div></div>"))
    }

    @Test
    fun megamenu_all_flags() {
        val html = allFlags(Expected("Megamenu all flags", "megamenu megamenu-vertical zz-extra", closes = "</div></div>")) { daisyMegamenu(id = ID, vertical = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun megamenu_size() = assertEachEntry("Megamenu size", mapOf(MegamenuSize.Xs to "megamenu megamenu-xs", MegamenuSize.Sm to "megamenu megamenu-sm", MegamenuSize.Md to "megamenu megamenu-md", MegamenuSize.Lg to "megamenu megamenu-lg", MegamenuSize.Xl to "megamenu megamenu-xl")) { daisyMegamenu(size = it, content = { }) }

    @Test
    fun megamenu_width() = assertEachEntry("Megamenu width", mapOf(MegamenuWidth.Wide to "megamenu megamenu-wide", MegamenuWidth.Full to "megamenu megamenu-full")) { daisyMegamenu(width = it, content = { }) }

    @Test
    fun megamenuClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses()
        }
        assertRendered(html, Expected("MegamenuClasses defaults", "megamenu"))
    }

    @Test
    fun megamenuClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses(
                vertical = true,
            )
        }
        val expected = Expected("MegamenuClasses all flags", "megamenu megamenu-vertical", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun megamenuClasses_size() = assertEachEntry("MegamenuClasses size", mapOf(MegamenuSize.Xs to "megamenu megamenu-xs", MegamenuSize.Sm to "megamenu megamenu-sm", MegamenuSize.Md to "megamenu megamenu-md", MegamenuSize.Lg to "megamenu megamenu-lg", MegamenuSize.Xl to "megamenu megamenu-xl")) { daisyMegamenuClasses(size = it) }

    @Test
    fun megamenuClasses_width() = assertEachEntry("MegamenuClasses width", mapOf(MegamenuWidth.Wide to "megamenu megamenu-wide", MegamenuWidth.Full to "megamenu megamenu-full")) { daisyMegamenuClasses(width = it) }

    @Test
    fun megamenuActive_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuActive(
                content = { },
            )
        }
        assertRendered(html, Expected("MegamenuActive defaults", "megamenu-active", closes = "</span></div>"))
    }

    @Test
    fun megamenuActive_all_flags() {
        val html = allFlags(Expected("MegamenuActive all flags", "megamenu-active zz-extra", closes = "</span></div>")) { daisyMegamenuActive(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun megamenuActiveClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuActiveClasses()
        }
        assertRendered(html, Expected("MegamenuActiveClasses defaults", "megamenu-active"))
    }

    @Test
    fun megamenuActiveClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuActiveClasses()
        }
        val expected = Expected("MegamenuActiveClasses all flags", "megamenu-active", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun megamenuPanel_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuPanel(
                content = { },
            )
        }
        assertTrue(!html.contains("class=\""), "MegamenuPanel defaults emits no class")
        assertTrue(html.endsWith("</div></div>"), "MegamenuPanel closes")
    }

    @Test
    fun megamenuPanel_all_flags() {
        val html = allFlags(Expected("MegamenuPanel all flags", "zz-extra", closes = "</div></div>")) { daisyMegamenuPanel(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }
}
