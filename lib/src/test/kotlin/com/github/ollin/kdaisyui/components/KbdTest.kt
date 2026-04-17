package com.github.ollin.kdaisyui.components

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
    }

    @Test
    fun kbd_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd() {
            }
        }
        val expectedClasses = "kbd"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Kbd sizes")
    }

    @Test
    fun in_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd() {
            }
        }
        val expectedClasses = "kbd"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for In text")
    }

    @Test
    fun key_combination() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd() {
            }
        }
        val expectedClasses = "kbd"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Key combination")
    }

    @Test
    fun function_keys() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd() {
            }
        }
        val expectedClasses = "kbd"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Function Keys")
    }

    @Test
    fun a_full_keyboard() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd() {
            }
        }
        val expectedClasses = "kbd"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for A full keyboard")
    }

    @Test
    fun arrow_keys() {
        val html = createHTML(prettyPrint = false).div {
            daisyKbd() {
            }
        }
        val expectedClasses = "kbd"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Arrow Keys")
    }
}
