package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class KbdTest {

    @Test
    fun kbd() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd() {
            }
        }
        val expectedClasses = "kbd"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Kbd")
        assertEquals("kbd", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Kbd")
    }

    @Test
    fun kbd_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(size = KbdSize.Xs) {
            }
        }
        val expectedClasses = "kbd kbd-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Kbd sizes")
        assertEquals("kbd", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Kbd sizes")
    }

    @Test
    fun kbd_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(size = KbdSize.Sm) {
            }
        }
        val expectedClasses = "kbd kbd-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Kbd sizes")
        assertEquals("kbd", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Kbd sizes")
    }

    @Test
    fun kbd_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(size = KbdSize.Md) {
            }
        }
        val expectedClasses = "kbd kbd-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Kbd sizes")
        assertEquals("kbd", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Kbd sizes")
    }

    @Test
    fun kbd_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(size = KbdSize.Lg) {
            }
        }
        val expectedClasses = "kbd kbd-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Kbd sizes")
        assertEquals("kbd", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Kbd sizes")
    }

    @Test
    fun kbd_sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd(size = KbdSize.Xl) {
            }
        }
        val expectedClasses = "kbd kbd-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Kbd sizes")
        assertEquals("kbd", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Kbd sizes")
    }
}
