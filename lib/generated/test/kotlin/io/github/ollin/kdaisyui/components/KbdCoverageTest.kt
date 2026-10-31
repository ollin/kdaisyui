package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class KbdCoverageTest {

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
    fun kbd_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd()
        }
        assertRendered(html, Expected("Kbd defaults", "kbd", closes = "</kbd></div>"))
    }

    @Test
    fun kbd_all_flags() {
        val html = allFlags(Expected("Kbd all flags", "kbd zz-extra", closes = "</kbd></div>")) { daisyKbd(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun kbd_size() = assertEachEntry("Kbd size", mapOf(KbdSize.Xs to "kbd kbd-xs", KbdSize.Sm to "kbd kbd-sm", KbdSize.Md to "kbd kbd-md", KbdSize.Lg to "kbd kbd-lg", KbdSize.Xl to "kbd kbd-xl")) { daisyKbd(size = it) }

    @Test
    fun kbd_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                text = "txtmark",
            )
        }
        assertRendered(html, Expected("Kbd text", "kbd"))
        assertTrue(html.contains("txtmark"), "Kbd text content")
    }

    @Test
    fun kbdClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbdClasses()
        }
        assertRendered(html, Expected("KbdClasses defaults", "kbd"))
    }

    @Test
    fun kbdClasses_all_flags() = assertRenders(Expected("KbdClasses all flags", "kbd", content = false)) { daisyKbdClasses() }

    @Test
    fun kbdClasses_size() = assertEachEntry("KbdClasses size", mapOf(KbdSize.Xs to "kbd kbd-xs", KbdSize.Sm to "kbd kbd-sm", KbdSize.Md to "kbd kbd-md", KbdSize.Lg to "kbd kbd-lg", KbdSize.Xl to "kbd kbd-xl")) { daisyKbdClasses(size = it) }
}
