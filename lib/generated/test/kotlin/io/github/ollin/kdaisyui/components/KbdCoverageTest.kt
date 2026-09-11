package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class KbdCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
    }

    @Test
    fun kbd_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd()
        }
        assertRendered(html, "kbd", "Kbd defaults", closes = "</kbd></div>")
    }

    @Test
    fun kbd_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "kbd zz-extra", "Kbd all flags", closes = "</kbd></div>")
        assertCommonFlags(html, "Kbd")
    }

    @Test
    fun kbd_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                size = KbdSize.Xs,
            )
        }
        assertRendered(html, "kbd kbd-xs", "Kbd size Xs")
    }

    @Test
    fun kbd_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                size = KbdSize.Sm,
            )
        }
        assertRendered(html, "kbd kbd-sm", "Kbd size Sm")
    }

    @Test
    fun kbd_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                size = KbdSize.Md,
            )
        }
        assertRendered(html, "kbd kbd-md", "Kbd size Md")
    }

    @Test
    fun kbd_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                size = KbdSize.Lg,
            )
        }
        assertRendered(html, "kbd kbd-lg", "Kbd size Lg")
    }

    @Test
    fun kbd_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                size = KbdSize.Xl,
            )
        }
        assertRendered(html, "kbd kbd-xl", "Kbd size Xl")
    }

    @Test
    fun kbd_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                text = "txtmark",
            )
        }
        assertRendered(html, "kbd", "Kbd text")
        assertTrue(html.contains("txtmark"), "Kbd text content")
    }
}
