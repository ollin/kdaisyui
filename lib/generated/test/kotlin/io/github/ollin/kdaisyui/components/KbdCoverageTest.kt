package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class KbdCoverageTest {

    @Test
    fun kbd_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd()
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("kbd", actualClasses, "Kbd defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("kbd zz-extra", actualClasses, "Kbd all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Kbd id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Kbd attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Kbd content")
    }

    @Test
    fun kbd_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                size = KbdSize.Xs,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("kbd kbd-xs", actualClasses, "Kbd size Xs")
    }

    @Test
    fun kbd_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                size = KbdSize.Sm,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("kbd kbd-sm", actualClasses, "Kbd size Sm")
    }

    @Test
    fun kbd_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                size = KbdSize.Md,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("kbd kbd-md", actualClasses, "Kbd size Md")
    }

    @Test
    fun kbd_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                size = KbdSize.Lg,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("kbd kbd-lg", actualClasses, "Kbd size Lg")
    }

    @Test
    fun kbd_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                size = KbdSize.Xl,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("kbd kbd-xl", actualClasses, "Kbd size Xl")
    }

    @Test
    fun kbd_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(
                text = "txtmark",
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("kbd", actualClasses, "Kbd text")
        assertTrue(html.contains("txtmark"), "Kbd text content")
    }
}
