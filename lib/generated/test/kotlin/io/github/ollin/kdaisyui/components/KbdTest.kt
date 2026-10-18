package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class KbdTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun kbd() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd() {
            }
        }
        assertDocumented(html, "kbd", "kbd", "Kbd")
    }

    @Test
    fun kbd_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(size = KbdSize.Xs) {
            }
        }
        assertDocumented(html, "kbd", "kbd kbd-xs", "Kbd sizes")
    }

    @Test
    fun kbd_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(size = KbdSize.Sm) {
            }
        }
        assertDocumented(html, "kbd", "kbd kbd-sm", "Kbd sizes")
    }

    @Test
    fun kbd_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(size = KbdSize.Md) {
            }
        }
        assertDocumented(html, "kbd", "kbd kbd-md", "Kbd sizes")
    }

    @Test
    fun kbd_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(size = KbdSize.Lg) {
            }
        }
        assertDocumented(html, "kbd", "kbd kbd-lg", "Kbd sizes")
    }

    @Test
    fun kbd_sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(size = KbdSize.Xl) {
            }
        }
        assertDocumented(html, "kbd", "kbd kbd-xl", "Kbd sizes")
    }
}
