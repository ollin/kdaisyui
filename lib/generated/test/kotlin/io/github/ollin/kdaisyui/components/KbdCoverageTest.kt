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

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
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
    fun kbd_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                size = KbdSize.Xs,
            )
        }
        assertRendered(html, Expected("Kbd size Xs", "kbd kbd-xs"))
    }

    @Test
    fun kbd_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                size = KbdSize.Sm,
            )
        }
        assertRendered(html, Expected("Kbd size Sm", "kbd kbd-sm"))
    }

    @Test
    fun kbd_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                size = KbdSize.Md,
            )
        }
        assertRendered(html, Expected("Kbd size Md", "kbd kbd-md"))
    }

    @Test
    fun kbd_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                size = KbdSize.Lg,
            )
        }
        assertRendered(html, Expected("Kbd size Lg", "kbd kbd-lg"))
    }

    @Test
    fun kbd_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                size = KbdSize.Xl,
            )
        }
        assertRendered(html, Expected("Kbd size Xl", "kbd kbd-xl"))
    }

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
    fun kbdClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbdClasses()
        }
        val expected = Expected("KbdClasses all flags", "kbd", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun kbdClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbdClasses(
                size = KbdSize.Xs,
            )
        }
        assertRendered(html, Expected("KbdClasses size Xs", "kbd kbd-xs"))
    }

    @Test
    fun kbdClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbdClasses(
                size = KbdSize.Sm,
            )
        }
        assertRendered(html, Expected("KbdClasses size Sm", "kbd kbd-sm"))
    }

    @Test
    fun kbdClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbdClasses(
                size = KbdSize.Md,
            )
        }
        assertRendered(html, Expected("KbdClasses size Md", "kbd kbd-md"))
    }

    @Test
    fun kbdClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbdClasses(
                size = KbdSize.Lg,
            )
        }
        assertRendered(html, Expected("KbdClasses size Lg", "kbd kbd-lg"))
    }

    @Test
    fun kbdClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbdClasses(
                size = KbdSize.Xl,
            )
        }
        assertRendered(html, Expected("KbdClasses size Xl", "kbd kbd-xl"))
    }
}
